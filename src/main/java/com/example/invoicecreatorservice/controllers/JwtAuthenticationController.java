package com.example.invoicecreatorservice.controllers;

import java.util.Base64;
import java.util.Map;

import com.example.invoicecreatorservice.helpers.components.JwtTokenUtil;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ResponseDTO;
import com.example.invoicecreatorservice.objects.models.JwtRequest;
import com.example.invoicecreatorservice.objects.models.JwtUserDetails;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.services.JwtUserDetailsService;
import com.example.invoicecreatorservice.services.UserAccountService;
import com.example.invoicecreatorservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserService userService;

    @PostMapping(path="/authenticate")
    public ResponseEntity<ResponseDTO> createAuthenticationToken(@RequestHeader Map<String, String> header) {
        try {

            // get the base64 string
            String authorizationHeader = header.get("authorization");

            // remove "basic " from the string
            String base64Token = authorizationHeader.substring(6);

            // decode base64 to string
            byte[] decodedBytes = Base64.getDecoder().decode(base64Token);

            // split the string on the binding character
            String[] decodedString = new String(decodedBytes).split(":");

            // create the authentication request element for validation
            JwtRequest authenticationRequest = new JwtRequest(decodedString[0], decodedString[1]);

            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final JwtUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final UserAccountDTO user = userAccountService.getUserAccount(userDetails.getId());

            if(user == null || user.getId() == 0 || user.getUsername().isBlank()){
                return new ResponseEntity<>(new ResponseDTO(false, "Login credentials were incorrect!"), HttpStatus.NOT_FOUND);
            }

            final String token = jwtTokenUtil.generateToken(userDetails);
            user.setToken(token);
            user.setUser(userService.getUser(user.getUserId()));

            return new ResponseEntity<>(new ResponseDTO(true, user), HttpStatus.OK);

        } catch (Exception ex){
            return new ResponseEntity<>(new ResponseDTO(false, "Login credentials were incorrect!"), HttpStatus.BAD_REQUEST);
        }
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}
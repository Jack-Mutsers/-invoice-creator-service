package com.example.invoicecreatorservice.controllers;

import java.util.Base64;
import java.util.Map;

import com.example.invoicecreatorservice.components.JwtTokenUtil;
import com.example.invoicecreatorservice.models.JwtRequest;
import com.example.invoicecreatorservice.models.JwtUserDetails;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.services.JwtUserDetailsService;
import com.example.invoicecreatorservice.services.UserAccountService;
import com.example.invoicecreatorservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestHeader Map<String, String> header) throws Exception {
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
        final String token = jwtTokenUtil.generateToken(userDetails);

        final UserAccountDTO user = userAccountService.getUserAccount(userDetails.getId());
        user.setToken(token);
        user.setUser(userService.getUser(user.getUserId()));

        return ResponseEntity.ok(user);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountForAlterationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserDTO;
import com.example.invoicecreatorservice.services.UserAccountService;
import com.example.invoicecreatorservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/useraccount")
public class UserAccountController {

    @Autowired
    private final UserAccountService service = new UserAccountService();

    @Autowired
    private final UserService userService = new UserService();

    @PostMapping(path="/login")
    public @ResponseBody ResponseEntity<Object> login(@RequestBody UserAccountForAlterationDTO account) {
        if(account.validateLoginData()){
            return new ResponseEntity<>("Invalid login credentials", HttpStatus.BAD_REQUEST);
        }

        UserAccountDTO userAccount = service.login(account);

        if (userAccount == null) {
            return new ResponseEntity<>("Login credentials were incorrect!", HttpStatus.BAD_REQUEST);
        }

        userAccount.setUser(userService.getUser(userAccount.getId()));
        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable int id, @RequestBody UserAccountForAlterationDTO account) {
        boolean success = service.deleteUser(id, account);

        if (!success) {
            if (account.getId() == id) {
                return new ResponseEntity<>("The user you are trying to delete does not exist.", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>("Please login with the account you are trying to delete.", HttpStatus.BAD_REQUEST);
            }
        }else{

            UserDTO user = userService.getUser(account.getUser().getId());
            userService.deleteUser(user.getId());
        }


        return new ResponseEntity<>("Account has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<Object> createUserAccount(@RequestBody UserAccountForAlterationDTO accountDTO) {
        if(accountDTO.getContactCode() == null){
            accountDTO.generateContactCode();
        }

        if(accountDTO.validateForCreation() && !service.validateUsername(accountDTO.getUsername()) && accountDTO.getUser().validateForCreation()){
            return new ResponseEntity<>("Incomplete data or username already exists", HttpStatus.CONFLICT);
        }

        UserDTO user = userService.createUser(accountDTO.getUser());
        UserAccountDTO newObject = service.createUserAccount(accountDTO, user.getId());

        if (newObject == null){
            userService.deleteUser(user.getId());
            return new ResponseEntity<>("something went wrong wile creating a new user account", HttpStatus.CONFLICT);
        }

        newObject.setUser(userService.getUser(user.getId()));

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<String> updateUserAccount(@RequestBody UserAccountForAlterationDTO accountDTO) {
        if(accountDTO.validateForUpdate() && accountDTO.getUser().validateForUpdate()){
            return new ResponseEntity<>("Please provide valid account details.", HttpStatus.NOT_FOUND);
        }

        boolean success = service.updateUserAccount(accountDTO);

        if (!success){
            return new ResponseEntity<>("Something went wrong while updating your account", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Account has successfully been updated.", HttpStatus.OK);
    }
}

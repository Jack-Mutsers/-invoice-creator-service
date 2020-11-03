package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountForAlterationDTO;
import com.example.invoicecreatorservice.services.UserAccountService;
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

    @PostMapping(path="/login")
    public @ResponseBody ResponseEntity<Object> login(@RequestBody UserAccountForAlterationDTO account) {
        UserAccountDTO userAccount = service.login(account);

        if (userAccount == null) {
            return new ResponseEntity<>("Login credentials were incorrect!", HttpStatus.BAD_REQUEST);
        }

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
        }

        return new ResponseEntity<>("Account has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<Object> createUserAccount(@RequestBody UserAccountForAlterationDTO user) {
        UserAccountDTO newObject = service.createUserAccount(user);

        if (newObject == null){
            return new ResponseEntity<>("The account can not be created", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<String> updateUserAccount(@RequestBody UserAccountForAlterationDTO account) {
        boolean success = service.updateUserAccount(account);

        if (!success){
            return new ResponseEntity<>("Please provide valid account details.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Product has successfully been updated.", HttpStatus.OK);
    }
}

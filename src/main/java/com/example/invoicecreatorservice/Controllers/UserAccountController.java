package com.example.invoicecreatorservice.Controllers;

import com.example.invoicecreatorservice.DataTransferObjects.UserAccountDTO;
import com.example.invoicecreatorservice.Models.User;
import com.example.invoicecreatorservice.Models.UserAccount;
import com.example.invoicecreatorservice.Services.PasswordEncoder;
import com.example.invoicecreatorservice.Repositories.UserAccountRepo;
import com.example.invoicecreatorservice.Repositories.UserRepo;
import com.example.invoicecreatorservice.Services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/useraccount")
public class UserAccountController {

    private UserAccountService service = new UserAccountService();

    @PostMapping(path="/login")
    public @ResponseBody ResponseEntity<UserAccountDTO> login(@RequestBody UserAccount account) {
        UserAccountDTO userAccount = service.login(account);

        if (userAccount == null) {
            return new ResponseEntity("Login credentials were incorrect!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity deleteUser(@PathVariable int id, @RequestBody UserAccount account) {
        boolean success = service.deleteUser(id, account);

        if (!success) {
            if (account.getId() == id) {
                return new ResponseEntity("The user you are trying to delete does not exist.", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity("Please login with the account you are trying to delete.", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity("Account has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<UserAccountDTO> createUser(@RequestBody UserAccount user) throws Exception {
        UserAccountDTO newObject = service.createUser(user);

        if (newObject == null){
            return new ResponseEntity("The account can not be created", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="/{id}")
    public @ResponseBody ResponseEntity updateUser(@PathVariable int id, @RequestBody UserAccount account) {
        boolean success = service.updateUser(account);

        if (!success){
            return new ResponseEntity("Please provide valid account details.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Product has successfully been updated.", HttpStatus.OK);
    }
}

package com.example.invoicecreatorservice.Controllers;

import com.example.invoicecreatorservice.DataTransferObjects.UserAccountDTO;
import com.example.invoicecreatorservice.Models.User;
import com.example.invoicecreatorservice.Models.UserAccount;
import com.example.invoicecreatorservice.PasswordEncoder;
import com.example.invoicecreatorservice.Repositories.UserAccountRepo;
import com.example.invoicecreatorservice.Repositories.UserRepo;
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
    private UserAccountRepo userAccountRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping(path="/login")
    public @ResponseBody ResponseEntity<UserAccountDTO> login(@RequestBody UserAccount account) {
        try{
            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = PasswordEncoder.check(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                return new ResponseEntity<UserAccountDTO>(new UserAccountDTO(userAccount), HttpStatus.OK);
            } else {
                return new ResponseEntity("Login credentials were incorrect!", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            return new ResponseEntity("Login credentials were incorrect!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity deleteUser(@PathVariable int id, @RequestBody UserAccount account) {
        try{
            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = PasswordEncoder.check(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                if (userAccount.getId() == id) {
                    User user = userRepo.findById(userAccount.getUserId());

                    userAccountRepo.deleteById(id);
                    userRepo.deleteById(user.getId());

                    return new ResponseEntity("Account has been deleted successfully.", HttpStatus.OK);
                } else {
                    return new ResponseEntity("Please login with the account you are trying to delete.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity("The user you are trying to delete does not exist.", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            return new ResponseEntity("Please login with the account you are trying to delete.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<UserAccountDTO> createUser(@RequestBody UserAccount user) throws Exception {
        user.setPassword(PasswordEncoder.getSaltedHash(user.getPassword()));
        UserAccount newObject = userAccountRepo.save(user);
        if (newObject.equals(null)){
            return new ResponseEntity("The account can not be created", HttpStatus.CONFLICT);
        } else {
            UserAccountDTO accountDTO = new UserAccountDTO(newObject);
            return new ResponseEntity<UserAccountDTO>(accountDTO, HttpStatus.CREATED);
        }
    }

    @PutMapping(path ="/{id}")
    public @ResponseBody ResponseEntity updateUser(@PathVariable int id, @RequestBody UserAccount account) {
        UserAccount newObject = userAccountRepo.save(account);
        if (newObject.equals(null)){
            return new ResponseEntity("Please provide valid account details.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Product has successfully been updated.", HttpStatus.OK);
    }
}

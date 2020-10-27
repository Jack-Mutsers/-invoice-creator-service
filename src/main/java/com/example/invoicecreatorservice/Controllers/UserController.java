package com.example.invoicecreatorservice.Controllers;

import com.example.invoicecreatorservice.Models.User;
import com.example.invoicecreatorservice.Repositories.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    // this has to be static because the service is stateless:
    private UserRepo userRepo;

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userRepo.findById(id);
        if (user == null) {
            return new ResponseEntity("Please provide a valid user identifier.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<Iterable<User>> getAllUser() {
        List<User> users = (List<User>) userRepo.findAll();
        if(users.size() == 0){ return new ResponseEntity("There are currently no users availible", HttpStatus.NOT_FOUND); }

        return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity deleteUser(@PathVariable int id) {
        boolean success = true; userRepo.deleteById(id);
        if(success){
            return new ResponseEntity("User has been deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity("User was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<User> createUser(@RequestBody User user) {
        User newObject = userRepo.save(user);
        if (newObject.equals(null)){
            return new ResponseEntity("The user can not be added", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<User>(newObject, HttpStatus.CREATED);
        }
    }

    @PutMapping(path ="/{id}")
    public @ResponseBody ResponseEntity updateUser(@PathVariable int id, @RequestBody User user) {
        User newObject = userRepo.save(user);
        if (newObject.equals(null)){
            return new ResponseEntity("Please provide a valid user.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("User has successfully been updated.", HttpStatus.OK);

    }
}

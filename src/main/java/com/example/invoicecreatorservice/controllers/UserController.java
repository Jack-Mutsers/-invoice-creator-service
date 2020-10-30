package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.UserForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserForUpdateDTO;
import com.example.invoicecreatorservice.models.User;
import com.example.invoicecreatorservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    // this has to be static because the service is stateless:
    private UserService service = new UserService();

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<User> getUser(@PathVariable int id) {
        User user = service.getUser(id);

        if (user == null) {
            return new ResponseEntity("Please provide a valid user identifier.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<Iterable<User>> getAllUser() {
        Iterable<User> users = service.getAllUser();

        if(users == null){
            return new ResponseEntity("There are currently no users availible", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity deleteUser(@PathVariable int id) {
        boolean success = service.deleteUser(id);

        if(!success){
            return new ResponseEntity("User was not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("User has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<User> createUser(@RequestBody UserForCreationDTO user) {
        User newObject = service.createUser(user);

        if (newObject == null){
            return new ResponseEntity("The user can not be added", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<User>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="/{id}")
    public @ResponseBody ResponseEntity updateUser(@PathVariable int id, @RequestBody UserForUpdateDTO user) {
        boolean success = service.updateUser(user);

        if (!success){
            return new ResponseEntity("Please provide a valid user.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("User has successfully been updated.", HttpStatus.OK);

    }
}

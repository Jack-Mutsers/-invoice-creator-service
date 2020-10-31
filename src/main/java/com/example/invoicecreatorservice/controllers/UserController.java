package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.UserDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserForUpdateDTO;
import com.example.invoicecreatorservice.models.User;
import com.example.invoicecreatorservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService service = new UserService();

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Object> getUser(@PathVariable int id) {
        UserDTO user = service.getUser(id);

        if (user == null) {
            return new ResponseEntity<>("Please provide a valid user identifier.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<Object> getAllUser() {
        Iterable<User> users = service.getAllUser();

        if(users == null){
            return new ResponseEntity<>("There are currently no users availible", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean success = service.deleteUser(id);

        if(!success){
            return new ResponseEntity<>("User was not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("User has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<Object> createUser(@RequestBody UserForCreationDTO user) {
        UserDTO newObject = service.createUser(user);

        if (newObject == null){
            return new ResponseEntity<>("The user can not be added", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<String> updateUser(@RequestBody UserForUpdateDTO user) {
        boolean success = service.updateUser(user);

        if (!success){
            return new ResponseEntity<>("Please provide a valid user.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("User has successfully been updated.", HttpStatus.OK);

    }
}

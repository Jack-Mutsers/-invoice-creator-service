package com.example.invoicecreatorservice.Services;

import com.example.invoicecreatorservice.Models.User;
import com.example.invoicecreatorservice.Repositories.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UserService {
    // this has to be static because the service is stateless:
    private UserRepo userRepo;

    public User getUser(int id) {
        User user = userRepo.findById(id);

        if (user.validateUser()) {
            return null;
        }

        return user;
    }

    public Iterable<User> getAllUser() {
        List<User> users = (List<User>) userRepo.findAll();

        if(users.size() == 0){ return null; }

        return users;
    }

    public boolean deleteUser(int id) {
        try{
            userRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public User createUser(User user) {
        try{
            User newObject = userRepo.save(user);
            return newObject;
        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateUser(User user) {
        try{
            User newObject = userRepo.save(user);
            return true;
        }catch (Exception ex){
            return false;
        }

    }
}

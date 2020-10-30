package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.UserForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserForUpdateDTO;
import com.example.invoicecreatorservice.models.User;
import com.example.invoicecreatorservice.repositories.UserRepo;

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

    public User createUser(UserForCreationDTO userDTO) {
        try{
            User user = new User(userDTO);
            User newObject = userRepo.save(user);
            return newObject;
        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateUser(UserForUpdateDTO userDTO) {
        try{
            User user = new User(userDTO);
            User newObject = userRepo.save(user);
            return true;
        }catch (Exception ex){
            return false;
        }

    }
}

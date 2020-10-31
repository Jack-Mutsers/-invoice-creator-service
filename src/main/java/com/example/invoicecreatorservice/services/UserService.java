package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.UserDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserForUpdateDTO;
import com.example.invoicecreatorservice.models.User;
import com.example.invoicecreatorservice.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserDTO getUser(int id) {
        User user = userRepo.findById(id);

        return new UserDTO(user);
    }

    public Iterable<User> getAllUser() {
        List<User> users = (List<User>) userRepo.findAll();

        if(users.isEmpty()){ return null; }

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

    public UserDTO createUser(UserForCreationDTO userDTO) {
        try{
            if (userDTO.validateUser()) {
                return null;
            }

            User user = new User(userDTO);
            User newObject = userRepo.save(user);
            return new UserDTO(newObject);
        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateUser(UserForUpdateDTO userDTO) {
        try{
            if (userDTO.validateUser()) {
                return false;
            }

            User user = new User(userDTO);
            userRepo.save(user);
            return true;
        }catch (Exception ex){
            return false;
        }

    }
}

package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.IUserService;
import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.User;
import com.example.invoicecreatorservice.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;

    public UserDTO getUser(int id) {
        User user = userRepo.findById(id);

        return new UserDTO(user);
    }

    public Iterable<User> getAllUser() {
        return userRepo.findAll();
    }

    @Transactional
    public boolean deleteUser(int id) {
        try{
            userRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    public UserDTO createUser(UserForAlterationDTO userDTO) {
        try{
            User user = new User(userDTO);

            // set id to 0 to prevent update of existing record on create
            user.setId(0);

            User newObject = userRepo.save(user);
            return new UserDTO(newObject);
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return null;
        }
    }

    public boolean updateUser(int userId, UserForAlterationDTO userDTO) {
        try{
            userDTO.setId(userId);
            User user = new User(userDTO);
            userRepo.save(user);
            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }

    }
}

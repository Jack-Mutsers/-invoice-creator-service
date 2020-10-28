package com.example.invoicecreatorservice.Services;

import com.example.invoicecreatorservice.DataTransferObjects.UserAccountDTO;
import com.example.invoicecreatorservice.Models.User;
import com.example.invoicecreatorservice.Models.UserAccount;
import com.example.invoicecreatorservice.Repositories.UserAccountRepo;
import com.example.invoicecreatorservice.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class UserAccountService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private UserRepo userRepo;

    public UserAccountDTO login(UserAccount account) {
        try{
            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = PasswordEncoder.check(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                return new UserAccountDTO(account);
            } else {
                return null;
            }
        }
        catch (Exception ex){
            return null;
        }
    }

    public boolean deleteUser(int id, UserAccount account) {
        try{
            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = PasswordEncoder.check(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                if (userAccount.getId() == id) {
                    User user = userRepo.findById(userAccount.getUserId());

                    userAccountRepo.deleteById(id);
                    userRepo.deleteById(user.getId());

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        catch (Exception ex){
            return false;
        }
    }

    public UserAccountDTO createUser(UserAccount user) throws Exception {
        if(user.validateUserAccount()){
            return null;
        }

        try{
            String saltedPassword = PasswordEncoder.getSaltedHash(user.getPassword());
            user.setPassword(saltedPassword);
            UserAccount newObject = userAccountRepo.save(user);

            return new UserAccountDTO(newObject);

        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateUser(UserAccount account) {
        try{
            userAccountRepo.save(account);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}

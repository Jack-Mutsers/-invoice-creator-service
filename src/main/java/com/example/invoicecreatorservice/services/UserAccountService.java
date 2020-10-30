package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountForDeletionDTO;
import com.example.invoicecreatorservice.data_transfer_objects.UserAccountForUpdateDTO;
import com.example.invoicecreatorservice.models.User;
import com.example.invoicecreatorservice.models.UserAccount;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import com.example.invoicecreatorservice.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAccountService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private UserRepo userRepo;

    public UserAccountDTO login(UserAccountForCreationDTO account) {
        try{
            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = PasswordEncoder.check(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                return new UserAccountDTO(userAccount);
            } else {
                return null;
            }
        }
        catch (Exception ex){
            return null;
        }
    }

    public boolean deleteUser(int id, UserAccountForDeletionDTO account) {
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

    public UserAccountDTO createUserAccount(UserAccountForCreationDTO accountDTO) throws Exception {

        UserAccount user = new UserAccount(accountDTO);
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

    public boolean updateUserAccount(UserAccountForUpdateDTO accountDTO) {
        try{
            UserAccount account = new UserAccount(accountDTO);
            userAccountRepo.save(account);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}

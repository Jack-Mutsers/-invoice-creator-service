package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.*;
import com.example.invoicecreatorservice.models.User;
import com.example.invoicecreatorservice.models.UserAccount;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import com.example.invoicecreatorservice.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService = new UserService();

    public UserAccountDTO login(UserAccountForCreationDTO account) {
        try{
            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = PasswordEncoder.check(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                UserAccountDTO accountDTO = new UserAccountDTO(userAccount);
                accountDTO.setUser(userService.getUser(userAccount.getUserId()));

                return accountDTO;
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
        if(accountDTO.validateUserAccount()){
            return null;
        }

        try{
            User user = new User(accountDTO.getUser());
            user = userRepo.save(user);

            UserAccount userAccount = new UserAccount(accountDTO, user.getId());
            String saltedPassword = PasswordEncoder.getSaltedHash(userAccount.getPassword());
            userAccount.setPassword(saltedPassword);

            UserAccountDTO newAccountDTO = new UserAccountDTO(userAccountRepo.save(userAccount));
            newAccountDTO.setUser(userService.getUser(userAccount.getUserId()));

            return newAccountDTO;

        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateUserAccount(UserAccountForUpdateDTO accountDTO) {
        if(accountDTO.validateUserAccount()){
            return false;
        }

        try{
            UserAccount account = new UserAccount(accountDTO);
            userAccountRepo.save(account);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}

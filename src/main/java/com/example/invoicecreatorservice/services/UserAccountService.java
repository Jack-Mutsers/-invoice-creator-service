package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.*;
import com.example.invoicecreatorservice.models.UserAccount;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import com.example.invoicecreatorservice.tools.BCryptEncoder;
import com.example.invoicecreatorservice.tools.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepo userAccountRepo;

//    private PasswordEncoder encoder = PasswordEncoder.getInstance();
    private BCryptEncoder encoder = BCryptEncoder.getInstance();

    public UserAccountDTO getUserAccount(int id){
        return new UserAccountDTO(userAccountRepo.findById(id));
    }

    public boolean deleteUser(int id, UserAccountForAlterationDTO account) {
        if(account.validateForUpdate()){
            return false;
        }

        try{
            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = encoder.validatePassword(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                if (userAccount.getId() == id) {
                    userAccountRepo.deleteById(id);

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

    public UserAccountDTO createUserAccount(UserAccountForAlterationDTO accountDTO, int userId) {

        try{
            UserAccount newUserAccount = new UserAccount(accountDTO);
            newUserAccount.setUserId(userId);

            String saltedPassword = encoder.encodePassword(newUserAccount.getPassword());
            newUserAccount.setActive(true);
            newUserAccount.setPassword(saltedPassword);
            newUserAccount.setRole("ROLE_USER");

            // set id to 0 to prevent update of existing record on create
            newUserAccount.setId(0);
            UserAccountDTO newAccountDTO = new UserAccountDTO(userAccountRepo.save(newUserAccount));

            return newAccountDTO;

        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateUserAccount(UserAccountForAlterationDTO accountDTO) {
        try{
            UserAccount account = new UserAccount(accountDTO);
            UserAccount existingAccount = userAccountRepo.findById(account.getId());

            if(existingAccount == null){
                return false;
            }

            account.setRole(existingAccount.getRole());

            if(account.getPassword().isBlank()){
                account.setPassword(existingAccount.getPassword());
            }else{
                String saltedPassword = encoder.encodePassword(account.getPassword());
                account.setPassword(saltedPassword);
            }

            userAccountRepo.save(account);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean validateUsername(String username){
        UserAccount userAccount = userAccountRepo.findByUsername(username);
        return userAccount == null;
    }
}

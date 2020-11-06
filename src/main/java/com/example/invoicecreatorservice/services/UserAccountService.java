package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.*;
import com.example.invoicecreatorservice.models.UserAccount;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import com.example.invoicecreatorservice.tools.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService = new UserService();

    public UserAccountDTO getUserAccount(int id){
        return new UserAccountDTO(userAccountRepo.findById(id));
    }

    public UserAccountDTO login(UserAccountForAlterationDTO account) {
        if(account.validateLoginData()){
            return null;
        }

        try{
            companyService = new CompanyService();

            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = PasswordEncoder.check(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                UserAccountDTO accountDTO = new UserAccountDTO(userAccount);
                accountDTO.setUser(userService.getUser(userAccount.getUserId()));

                if(accountDTO.getCompany() != null && accountDTO.getCompany().getId() > 0){
                    accountDTO.setCompany(companyService.getCompany(accountDTO.getCompany().getId(), account.getId()));
                }

                return accountDTO;
            } else {
                return null;
            }
        }
        catch (Exception ex){
            return null;
        }
    }

    public boolean deleteUser(int id, UserAccountForAlterationDTO account) {
        if(account.validateForUpdate()){
            return false;
        }

        try{
            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = PasswordEncoder.check(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                if (userAccount.getId() == id) {
                    UserDTO user = userService.getUser(userAccount.getUserId());

                    userAccountRepo.deleteById(id);
                    userService.deleteUser(user.getId());

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

    public UserAccountDTO createUserAccount(UserAccountForAlterationDTO accountDTO) {
        if(accountDTO.validateForCreation()){
            return null;
        }

        try{
            UserAccount userAccount = userAccountRepo.findByUsername(accountDTO.getUsername());

            // check if user exists, if so return null
            if(userAccount != null && userAccount.getId() > 0){
                return null;
            }

            UserDTO user = userService.createUser(accountDTO.getUser());

            UserAccount newUserAccount = new UserAccount(accountDTO);
            newUserAccount.setUserId(user.getId());

            String saltedPassword = PasswordEncoder.getSaltedHash(newUserAccount.getPassword());
            newUserAccount.setPassword(saltedPassword);

            UserAccountDTO newAccountDTO = new UserAccountDTO(userAccountRepo.save(newUserAccount));
            newAccountDTO.setUser(userService.getUser(newUserAccount.getUserId()));

            return newAccountDTO;

        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateUserAccount(UserAccountForAlterationDTO accountDTO) {
        if(accountDTO.validateForUpdate()){
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

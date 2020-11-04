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
        UserAccountDTO accountDTO = new UserAccountDTO(userAccountRepo.findById(id));
        return accountDTO;
    }

    public UserAccountDTO login(UserAccountForAlterationDTO account) {
        if(account.validateForUpdate()){
            return null;
        }

        try{
            companyService = new CompanyService();

            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = PasswordEncoder.check(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                UserAccountDTO accountDTO = new UserAccountDTO(userAccount);
                accountDTO.setUser(userService.getUser(userAccount.getUserId()));

                if(accountDTO.getCompanyId() > 0){
                    accountDTO.setCompany(companyService.getCompany(accountDTO.getCompanyId(), account.getId()));
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
            UserDTO user = userService.createUser(accountDTO.getUser());

            UserAccount userAccount = new UserAccount(accountDTO);
            userAccount.setUserId(user.getId());

            String saltedPassword = PasswordEncoder.getSaltedHash(userAccount.getPassword());
            userAccount.setPassword(saltedPassword);

            UserAccountDTO newAccountDTO = new UserAccountDTO(userAccountRepo.save(userAccount));
            newAccountDTO.setUser(userService.getUser(userAccount.getUserId()));

            if(newAccountDTO.getCompanyId() > 0){
                newAccountDTO.setCompany(companyService.getCompany(newAccountDTO.getCompanyId(), newAccountDTO.getId()));
            }

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

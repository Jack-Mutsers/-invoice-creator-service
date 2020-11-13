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


    public UserAccountDTO getUserAccount(int id){
        return new UserAccountDTO(userAccountRepo.findById(id));
    }

    public UserAccountDTO login(UserAccountForAlterationDTO account) {

        try{
            companyService = new CompanyService();

            UserAccount userAccount = userAccountRepo.findByUsername(account.getUsername());
            boolean validPassword = PasswordEncoder.check(account.getPassword(), userAccount.getPassword());

            if (validPassword) {
                UserAccountDTO accountDTO = new UserAccountDTO(userAccount);

                if(accountDTO.getCompany() != null && accountDTO.getCompany().getId() > 0){
                    accountDTO.setCompany(companyService.getCompany(accountDTO.getCompany().getId()));
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

            String saltedPassword = PasswordEncoder.getSaltedHash(newUserAccount.getPassword());
            newUserAccount.setPassword(saltedPassword);

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

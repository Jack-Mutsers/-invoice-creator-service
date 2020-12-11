package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.IUserAccountService;
import com.example.invoicecreatorservice.contracts.tools.IPasswordEncoder;
import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.*;
import com.example.invoicecreatorservice.objects.models.UserAccount;
import com.example.invoicecreatorservice.repositories.UserAccountRepo;
import com.example.invoicecreatorservice.helpers.tools.BCryptEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.example.invoicecreatorservice.objects.models.UserAccount.OWNER_ROLE;
import static com.example.invoicecreatorservice.objects.models.UserAccount.USER_ROLE;

@Service
public class UserAccountService implements IUserAccountService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    private IPasswordEncoder encoder = BCryptEncoder.getInstance();

    public UserAccountDTO getUserAccount(int id){
        return new UserAccountDTO(userAccountRepo.findById(id));
    }

    public Iterable<UserDTO> getEmployees(int companyId){
        List<UserAccount> accountList = userAccountRepo.findAllByCompanyId(companyId);
        List<UserDTO> employees = new ArrayList<>();

        for(UserAccount account : accountList){
            employees.add(new UserDTO(account.getUser()));
        }

        return employees;
    }

    @Transactional
    public boolean deleteUser(int id, String password) {
        try{
            UserAccount userAccount = userAccountRepo.findById(id);
            boolean validPassword = encoder.validatePassword(password, userAccount.getPassword());

            if (!validPassword) {
                return false;
            }

            userAccountRepo.deleteById(id);
            return true;
        }
        catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    public boolean removeAllEmployees(int companyId){
        try{
            List<UserAccount> accountList = userAccountRepo.findAllByCompanyId(companyId);
            for(UserAccount account : accountList){
                account.setCompanyId(0);
                account.setRole(USER_ROLE);
            }

            userAccountRepo.saveAll(accountList);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    public UserAccountDTO createUserAccount(UserAccountForAlterationDTO accountDTO, int userId) {

        try{
            UserAccount newUserAccount = new UserAccount(accountDTO);
            newUserAccount.setUserId(userId);
            newUserAccount.getUser().setId(userId);

            String saltedPassword = encoder.encodePassword(newUserAccount.getPassword());
            newUserAccount.setActive(true);
            newUserAccount.setPassword(saltedPassword);
            newUserAccount.setRole("ROLE_USER");

            // set id to 0 to prevent update of existing record on create
            newUserAccount.setId(0);
            UserAccount account = userAccountRepo.save(newUserAccount);
            return new UserAccountDTO(account);

        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
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
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    public boolean validateUsername(String username){
        UserAccount userAccount = userAccountRepo.findByUsername(username);
        return userAccount == null;
    }

    public boolean addCompanyToUser(int id, int companyId) {
        try{
            UserAccount userAccount = userAccountRepo.findById(id);
            userAccount.setCompanyId(companyId);
            userAccount.setRole(OWNER_ROLE);

            userAccountRepo.save(userAccount);

            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }
}

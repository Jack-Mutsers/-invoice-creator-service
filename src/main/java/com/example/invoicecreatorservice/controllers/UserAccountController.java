package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.contracts.services.ICompanyService;
import com.example.invoicecreatorservice.contracts.services.ICustomerService;
import com.example.invoicecreatorservice.contracts.services.IUserAccountService;
import com.example.invoicecreatorservice.contracts.services.IUserService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.*;
import com.example.invoicecreatorservice.services.CompanyService;
import com.example.invoicecreatorservice.services.CustomerService;
import com.example.invoicecreatorservice.services.UserAccountService;
import com.example.invoicecreatorservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/useraccount")
public class UserAccountController extends BaseController {

    @Autowired
    private final IUserAccountService userAccountService = new UserAccountService();

    @Autowired
    private final IUserService userService = new UserService();

    @Autowired
    private final ICompanyService companyService = new CompanyService();

    @Autowired
    private final ICustomerService customerService = new CustomerService();

    private UserAccountDTO getUserAccount(int userAccountId){
        UserAccountDTO userAccountDTO = userAccountService.getUserAccount(userAccountId);
        userAccountDTO.setUser(userService.getUser(userAccountDTO.getUserId()));

        return userAccountDTO;
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<ResponseDTO> deleteUser(HttpServletRequest request, @PathVariable int id, @RequestBody Map<String, String> obj) {
        int userAccountId = super.getUserId(request);
        String password = obj.get("password");

        if(userAccountId != id){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid account details."), HttpStatus.NOT_FOUND);
        }

        UserAccountDTO accountDTO = userAccountService.getUserAccount(id);

        boolean success = userAccountService.deleteUser(id, password);

        if (!success) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please login with the account you are trying to delete."), HttpStatus.BAD_REQUEST);
        }else{
            userService.deleteUser(accountDTO.getUserId());
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Account has been deleted successfully."), HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> createUserAccount(@RequestBody UserAccountForAlterationDTO accountDTO) {
        if(accountDTO.getContactCode() == null){
            accountDTO.generateContactCode();
        }

        if(accountDTO.validateForCreation() || !userAccountService.validateUsername(accountDTO.getUsername()) || accountDTO.getUser().validateForCreation()){
            return new ResponseEntity<>(new ResponseDTO(false, "Incomplete data or username already exists"), HttpStatus.CONFLICT);
        }

        UserDTO user = userService.createUser(accountDTO.getUser());
        UserAccountDTO newObject = userAccountService.createUserAccount(accountDTO, user.getId());

        if (newObject == null){
            userService.deleteUser(user.getId());
            return new ResponseEntity<>(new ResponseDTO(false, "something went wrong wile creating a new user account"), HttpStatus.CONFLICT);
        }

        newObject.setUser(userService.getUser(newObject.getUserId()));

        return new ResponseEntity<>(new ResponseDTO(true, "User account has been created"), HttpStatus.CREATED);
    }

    @PutMapping(path ="/{id}")
    public @ResponseBody ResponseEntity<ResponseDTO> updateUserAccount(HttpServletRequest request, @PathVariable int id, @RequestBody UserAccountForAlterationDTO accountDTO) {
        int companyId = super.getCompanyId(request);
        int userAccountId = super.getUserId(request);

        if(accountDTO.validateForUpdate() || accountDTO.getUser().validateForUpdate() || userAccountId != id ){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid account details."), HttpStatus.NOT_FOUND);
        }

        boolean success = false;
        if(accountDTO.getPassword().isBlank()){
            int userId = userAccountService.getUserAccount(userAccountId).getUserId();
            success = userService.updateUser(userId, accountDTO.getUser());
        }else{
            success = userAccountService.updateUserAccount(accountDTO);
        }

        if (!success){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong while updating your account"), HttpStatus.NOT_FOUND);
        }

        customerService.updateCustomerByUser(accountDTO.getUser(), accountDTO.getContactCode());

        UserAccountDTO userAccountDTO = this.getUserAccount(userAccountId);
        userAccountDTO.setCompany(companyService.getCompany(companyId));

        return new ResponseEntity<>(new ResponseDTO(true, userAccountDTO), HttpStatus.OK);
    }
}

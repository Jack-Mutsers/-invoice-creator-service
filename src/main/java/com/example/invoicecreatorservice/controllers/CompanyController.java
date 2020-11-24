package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyForAlterationDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountDTO;
import com.example.invoicecreatorservice.services.CompanyService;
import com.example.invoicecreatorservice.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private final CompanyService service = new CompanyService();

    @Autowired
    private UserAccountService userAccountService = new UserAccountService();

    @GetMapping(path="/{companyId}/{userId}")
    public @ResponseBody ResponseEntity<Object> getCompany(@PathVariable int companyId, @PathVariable int userId) {
        UserAccountDTO accountDTO = userAccountService.getUserAccount(userId);

        if(accountDTO.getCompany().getId() != companyId){
            return new ResponseEntity<>("Please provide a valid User.", HttpStatus.FORBIDDEN);
        }

        CompanyDTO company = service.getCompany(companyId);

        if (company == null) {
            return new ResponseEntity<>("Please provide a valid customer identifier.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping(path="")
    public  @ResponseBody ResponseEntity<Object> getAllCompanies() {
        Iterable<CompanyDTO> companies = service.getAllCompanies();

        if(companies == null){
            return new ResponseEntity<>("There are currently no companies available", HttpStatus.OK);
        }

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{companyId}/{userId}")
    public @ResponseBody ResponseEntity<String> deleteCompany(@PathVariable int companyId, @PathVariable int userId) {
        boolean success = service.deleteCompany(companyId, userId);

        if(!success){
            return new ResponseEntity<>("Company could not be found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Company has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="/{userId}")
    public @ResponseBody ResponseEntity<Object> createCompany(@PathVariable int userId, @RequestBody CompanyForAlterationDTO companyDTO) {
        if(companyDTO.validateForCreation()){
            return new ResponseEntity<>("Please provide valid data for the creation", HttpStatus.CONFLICT);
        }

        if(companyDTO.getContactCode() == null){
            companyDTO.generateContactCode();
        }

        CompanyDTO newObject = service.createCompany(companyDTO, userId);

        if (newObject == null){
            return new ResponseEntity<>("Something went wrong with the creation of the company.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="/{userId}")
    public @ResponseBody ResponseEntity<String> updateCompany(@PathVariable int userId, @RequestBody CompanyForAlterationDTO companyDTO) {
        if(companyDTO.validateForUpdate()){
            return new ResponseEntity<>("Please provide valid data for the update", HttpStatus.CONFLICT);
        }

        UserAccountDTO accountDTO = userAccountService.getUserAccount(userId);

        if(accountDTO.getCompany().getId() != companyDTO.getId()){
            return new ResponseEntity<>("Please provide a valid User.", HttpStatus.FORBIDDEN);
        }

        Boolean success = service.updateCompany(companyDTO);

        if (Boolean.FALSE.equals(success)){
            return new ResponseEntity<>("Something went wrong with the update of the company.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Company has successfully been updated.", HttpStatus.OK);
    }
}

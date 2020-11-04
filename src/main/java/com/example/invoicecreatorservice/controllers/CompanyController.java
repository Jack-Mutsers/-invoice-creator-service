package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.data_transfer_objects.CompanyForAlterationDTO;
import com.example.invoicecreatorservice.services.CompanyService;
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

    @GetMapping(path="")
    public  @ResponseBody ResponseEntity<Object> getAllCompanies() {
        Iterable<CompanyDTO> companies = service.getAllCompanies();

        if(companies == null){
            return new ResponseEntity<>("There are currently no companies available", HttpStatus.OK);
        }

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping(path="/{companyId}/{userId}")
    public @ResponseBody ResponseEntity<Object> getCompany(@PathVariable int companyId, @PathVariable int userId) {
        CompanyDTO company = service.getCompany(companyId, userId);

        if (company == null) {
            return new ResponseEntity<>("Please provide a valid customer identifier.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(company, HttpStatus.OK);
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
    public @ResponseBody ResponseEntity<Object> createCompany(@PathVariable int userId, @RequestBody CompanyForAlterationDTO company) {
        CompanyDTO newObject = service.createCompany(company, userId);

        if (newObject == null){
            return new ResponseEntity<>("The Company can not be added because it is not complete", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="/{userId}")
    public @ResponseBody ResponseEntity<String> updateCompany(@PathVariable int userId, @RequestBody CompanyForAlterationDTO company) {
        Boolean success = service.updateCompany(company, userId);

        if (Boolean.FALSE.equals(success)){
            return new ResponseEntity<>("Please provide a valid Company.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Company has successfully been updated.", HttpStatus.OK);
    }
}

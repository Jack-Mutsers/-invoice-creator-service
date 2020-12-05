package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyForAlterationDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ResponseDTO;
import com.example.invoicecreatorservice.objects.models.Company;
import com.example.invoicecreatorservice.services.CompanyService;
import com.example.invoicecreatorservice.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {
    @Autowired
    private final CompanyService service = new CompanyService();

    @Autowired
    private UserAccountService userAccountService = new UserAccountService();

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> getCompany(HttpServletRequest request) {
        int companyId = super.getCompanyId(request);

        if(companyId == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide a valid User."), HttpStatus.FORBIDDEN);
        }

        CompanyDTO company = service.getCompany(companyId);

        if (company == null) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide a valid customer identifier."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, company), HttpStatus.OK);
    }

    @GetMapping(path="/all")
    public  @ResponseBody ResponseEntity<ResponseDTO> getAllCompanies() {
        Iterable<CompanyDTO> companies = service.getAllCompanies();

        if(companies == null){
            return new ResponseEntity<>(new ResponseDTO(false, "There are currently no companies available"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(true, companies), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{userId}")
    public @ResponseBody ResponseEntity<ResponseDTO> deleteCompany(HttpServletRequest request, @PathVariable int userId) {
        int companyId = super.getCompanyId(request);

        if(companyId == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide a valid User."), HttpStatus.FORBIDDEN);
        }

        boolean success = service.deleteCompany(companyId, userId);

        if(!success){
            return new ResponseEntity<>(new ResponseDTO(false, "Company could not be found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Company has been deleted successfully."), HttpStatus.OK);
    }

    @PostMapping(path="/{userId}")
    public @ResponseBody ResponseEntity<ResponseDTO> createCompany(@PathVariable int userId, @RequestBody CompanyForAlterationDTO companyDTO) {
        if(companyDTO.validateForCreation()){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the creation"), HttpStatus.CONFLICT);
        }

        if(companyDTO.getContactCode() == null){
            companyDTO.generateContactCode();
        }

        Company newObject = service.createCompany(companyDTO, userId);

        if (newObject == null){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the creation of the company."), HttpStatus.CONFLICT);
        }

        if (!userAccountService.addCompanyToUser(userId, newObject.getId())){
            service.deleteCompany(newObject.getId(), userId);
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the creation of the company."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, new CompanyDTO(newObject)), HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<ResponseDTO> updateCompany(HttpServletRequest request, @RequestBody CompanyForAlterationDTO companyDTO) {
        int companyId = super.getCompanyId(request);

        if(companyDTO.validateForUpdate() || companyId != companyDTO.getId()){
            return new ResponseEntity<>(new ResponseDTO(false, "Data is invalid or you dont have permissions to perform this action"), HttpStatus.CONFLICT);
        }

        Boolean success = service.updateCompany(companyDTO);

        if (Boolean.FALSE.equals(success)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the update of the company."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Company has successfully been updated."), HttpStatus.OK);
    }
}

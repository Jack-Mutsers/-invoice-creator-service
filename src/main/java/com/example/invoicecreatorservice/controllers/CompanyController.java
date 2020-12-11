package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.contracts.services.*;
import com.example.invoicecreatorservice.objects.data_transfer_objects.*;
import com.example.invoicecreatorservice.objects.models.Company;
import com.example.invoicecreatorservice.services.*;
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
    private final ICompanyService service = new CompanyService();

    @Autowired
    private final IUserAccountService userAccountService = new UserAccountService();

    @Autowired
    private final IProductCategoryService productCategoryService = new ProductCategoryService();

    @Autowired
    private final IProductService productService = new ProductService();

    @Autowired
    private final ICustomerService customerService = new CustomerService();

    @Autowired
    private final IFileRecordService fileRecordService = new FileRecordService();

    private StorageService storageService;

    private static final String ACCESSFORBIDDEN = "You do not have access to this element";
    private static final String ELEMENTNOTFOUND = "Company could not be found.";
    private static final String DATACONFLICT = "Supplied data does not meet the requirements";
    private static String internalErrorMessage(String action){ return "Something went wrong with the " + action + " of the company."; }

    @Autowired
    public CompanyController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> getCompany(HttpServletRequest request) {
        int companyId = super.getCompanyId(request);

        if(companyId == 0){
            return new ResponseEntity<>(new ResponseDTO(false, ACCESSFORBIDDEN), HttpStatus.FORBIDDEN);
        }

        CompanyDTO company = service.getCompany(companyId);

        if (company == null) {
            return new ResponseEntity<>(new ResponseDTO(false, ELEMENTNOTFOUND), HttpStatus.NOT_FOUND);
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

    @GetMapping(path="/employees")
    public @ResponseBody ResponseEntity<ResponseDTO> getAllEmployees(HttpServletRequest request){
        int companyId = super.getCompanyId(request);

        if(companyId == 0){
            return new ResponseEntity<>(new ResponseDTO(false, ACCESSFORBIDDEN), HttpStatus.FORBIDDEN);
        }

        Iterable<UserDTO> employees = userAccountService.getEmployees(companyId);

        if (employees == null) {
            return new ResponseEntity<>(new ResponseDTO(false, "No employees have been found for the specified company."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, employees), HttpStatus.OK);
    }

    @DeleteMapping(path = "")
    public @ResponseBody ResponseEntity<ResponseDTO> deleteCompany(HttpServletRequest request) {
        int companyId = super.getCompanyId(request);
        int userId = super.getUserId(request);

        if(companyId == 0){
            return new ResponseEntity<>(new ResponseDTO(false, ACCESSFORBIDDEN), HttpStatus.FORBIDDEN);
        }

        String companyName = service.getCompany(companyId).getName();
        boolean companyDeleted = service.deleteCompany(companyId, userId);

        if(!companyDeleted){
            return new ResponseEntity<>(new ResponseDTO(false, ELEMENTNOTFOUND), HttpStatus.NOT_FOUND);
        }

        // delete all company information
        customerService.deleteAllCompanyCustomers(companyId);
        productCategoryService.deleteAllCompanyCategories(companyId);
        productService.deleteAllCompanyProducts(companyId);
        userAccountService.removeAllEmployees(companyId);
        storageService.deleteAllByCompany(companyName);
        fileRecordService.deleteCompanyRecords(companyId);

        return new ResponseEntity<>(new ResponseDTO(true, "Company has been deleted successfully."), HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> createCompany(HttpServletRequest request, @RequestBody CompanyForAlterationDTO companyDTO) {
        if(companyDTO.validateForCreation()){
            return new ResponseEntity<>(new ResponseDTO(false, DATACONFLICT), HttpStatus.CONFLICT);
        }

        int userId = super.getUserId(request);

        if(companyDTO.getContactCode() == null){
            companyDTO.generateContactCode();
        }

        Company newObject = service.createCompany(companyDTO, userId);

        if (newObject == null){
            return new ResponseEntity<>(new ResponseDTO(false, internalErrorMessage("creation")), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!userAccountService.addCompanyToUser(userId, newObject.getId())){
            service.deleteCompany(newObject.getId(), userId);
            return new ResponseEntity<>(new ResponseDTO(false, internalErrorMessage("creation")), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ResponseDTO(true, new CompanyDTO(newObject)), HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<ResponseDTO> updateCompany(HttpServletRequest request, @RequestBody CompanyForAlterationDTO companyDTO) {
        int companyId = super.getCompanyId(request);

        if(companyId != companyDTO.getId()){
            return new ResponseEntity<>(new ResponseDTO(false, ACCESSFORBIDDEN), HttpStatus.FORBIDDEN);
        }
        if(companyDTO.validateForUpdate()){
            return new ResponseEntity<>(new ResponseDTO(false, DATACONFLICT), HttpStatus.CONFLICT);
        }

        CompanyDTO company = service.updateCompany(companyDTO);

        if (company == null){
            return new ResponseEntity<>(new ResponseDTO(false, internalErrorMessage("update")), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ResponseDTO(true, company), HttpStatus.OK);
    }
}

package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ResponseDTO;
import com.example.invoicecreatorservice.objects.models.Customer;
import com.example.invoicecreatorservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/customers")
public class CustomerController extends BaseCompanyController {
    @Autowired
    private final CustomerService service = new CustomerService();

    @GetMapping(path="/{customerId}")
    public @ResponseBody ResponseEntity<ResponseDTO> getCustomer(@PathVariable int customerId) {
        CustomerDTO customer = service.getCustomer(customerId);

        if (customer == null) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide a valid customer identifier."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, customer), HttpStatus.OK);
    }

    @GetMapping(path="")
    public  @ResponseBody ResponseEntity<ResponseDTO> getAllCustomers(HttpServletRequest request) {
        int companyId = super.getCompanyId(request);

        Iterable<Customer> customers = service.getAllCustomers(companyId);

        if(customers == null){
            return new ResponseEntity<>(new ResponseDTO(true, "There are currently no customers availible"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(true, customers), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{customerId}")
    public @ResponseBody ResponseEntity<ResponseDTO> deleteCustomer(HttpServletRequest request, @PathVariable int customerId) {
        int companyId = super.getCompanyId(request);

        if(companyId == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "customer not found."), HttpStatus.NOT_FOUND);
        }

        boolean success = service.deleteCustomer(customerId, companyId);

        if(!success){
            return new ResponseEntity<>(new ResponseDTO(false, "customer not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Customer has been deleted successfully."), HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> createCustomer(HttpServletRequest request, @RequestBody CustomerForAlterationDTO customerDTO) {
        int companyId = super.getCompanyId(request);

        customerDTO.setCompanyId(companyId);

        if(customerDTO.validateForCreation()){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the creation"), HttpStatus.CONFLICT);
        }

        CustomerDTO newObject = service.createCustomer(customerDTO);

        if (newObject == null){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the creation of the customer."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "New Customer has been created"), HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<ResponseDTO> updateCustomer(HttpServletRequest request, @RequestBody CustomerForAlterationDTO customerDTO) {
        int companyId = super.getCompanyId(request);
        customerDTO.setCompanyId(companyId);

        if(customerDTO.validateForUpdate()){
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the update"), HttpStatus.CONFLICT);
        }

        Boolean success = service.updateCustomer(customerDTO);

        if (Boolean.FALSE.equals(success)){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the update of the customer."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Customer has successfully been updated."), HttpStatus.OK);
    }
}

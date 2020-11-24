package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Customer;
import com.example.invoicecreatorservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private final CustomerService service = new CustomerService();

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Object> getCustomer(@PathVariable int id) {
        CustomerDTO customer = service.getCustomer(id);

        if (customer == null) {
            return new ResponseEntity<>("Please provide a valid customer identifier.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(path="")
    public  @ResponseBody ResponseEntity<Object> getAllCustomers() {
        Iterable<Customer> customers = service.getAllCustomers();

        if(customers == null){
            return new ResponseEntity<>("There are currently no customers availible", HttpStatus.OK);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        boolean success = service.deleteCustomer(id);

        if(!success){
            return new ResponseEntity<>("customer not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Customer has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<Object> createCustomer(@RequestBody CustomerForAlterationDTO customerDTO) {
        if(customerDTO.validateForCreation()){
            return new ResponseEntity<>("Please provide valid data for the creation", HttpStatus.CONFLICT);
        }

        CustomerDTO newObject = service.createCustomer(customerDTO);

        if (newObject == null){
            return new ResponseEntity<>("Something went wrong with the creation of the customer.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<String> updateCustomer(@RequestBody CustomerForAlterationDTO customerDTO) {
        if(customerDTO.validateForUpdate()){
            return new ResponseEntity<>("Please provide valid data for the update", HttpStatus.CONFLICT);
        }

        Boolean success = service.updateCustomer(customerDTO);

        if (Boolean.FALSE.equals(success)){
            return new ResponseEntity<>("Something went wrong with the update of the customer.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Customer has successfully been updated.", HttpStatus.OK);
    }
}

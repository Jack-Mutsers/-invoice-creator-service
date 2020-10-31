package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.data_transfer_objects.CustomerForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.CustomerForUpdateDTO;
import com.example.invoicecreatorservice.models.Customer;
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
    public @ResponseBody ResponseEntity<Object> createCustomer(@RequestBody CustomerForCreationDTO customer) {
        CustomerDTO newObject = service.createCustomer(customer);

        if (newObject == null){
            return new ResponseEntity<>("The user can not be added because it is not complete", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<String> updateCustomer(@RequestBody CustomerForUpdateDTO customer) {
        Boolean success = service.updateCustomer(customer);

        if (Boolean.FALSE.equals(success)){
            return new ResponseEntity<>("Please provide a valid Customer.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Customer has successfully been updated.", HttpStatus.OK);
    }
}

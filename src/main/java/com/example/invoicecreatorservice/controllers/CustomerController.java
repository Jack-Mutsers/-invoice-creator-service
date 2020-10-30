package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.CustomerForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.CustomerForUpdateDTO;
import com.example.invoicecreatorservice.models.Customer;
import com.example.invoicecreatorservice.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService service = new CustomerService();

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        Customer customer = service.getCustomer(id);

        if (customer == null) {
            return new ResponseEntity("Please provide a valid customer identifier.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping(path="")
    public  @ResponseBody ResponseEntity<Iterable<Customer>> getAllCustomers() {
        Iterable<Customer> customers = service.getAllCustomers();

        if(customers == null){
            return new ResponseEntity("There are currently no customers availible", HttpStatus.OK);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity deleteCustomer(@RequestParam int id) {
        boolean success = service.deleteCustomer(id);

        if(!success){
            return new ResponseEntity("customer not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Customer has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<Customer> createCustomer(@RequestBody CustomerForCreationDTO customer) {
        Customer newObject = service.createCustomer(customer);

        if (newObject == null){
            return new ResponseEntity("The user can not be added because it is not complete", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<Customer>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="/{id}")
    public @ResponseBody ResponseEntity updateCustomer(@PathVariable int id, @RequestBody CustomerForUpdateDTO customer) {
        Boolean success = service.updateCustomer(customer);

        if (!success){
            return new ResponseEntity("Please provide a valid Customer.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Customer has successfully been updated.", HttpStatus.OK);
    }
}

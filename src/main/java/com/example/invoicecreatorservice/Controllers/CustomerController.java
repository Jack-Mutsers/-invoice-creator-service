package com.example.invoicecreatorservice.Controllers;

import com.example.invoicecreatorservice.Models.Customer;
import com.example.invoicecreatorservice.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        Customer customer = customerRepo.findById(id);
        if (customer == null) {
            return new ResponseEntity("Please provide a valid customer identifier.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        }
    }

    @GetMapping(path="")
    public  @ResponseBody ResponseEntity<Iterable<Customer>> getAllCustomers() {
        List<Customer> customers = (List) customerRepo.findAll();
        if(customers.size() == 0){ return new ResponseEntity("There are currently no customers availible", HttpStatus.OK); }

        return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity deleteCustomer(@RequestParam int id) {
        boolean success = true; customerRepo.deleteById(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        if(success){
            return new ResponseEntity("Customer has been deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity("customer not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer newObject = customerRepo.save(customer);
        if (newObject.equals(null)){
            return new ResponseEntity("The user can not be added because it is not complete", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<Customer>(newObject, HttpStatus.CREATED);
        }
    }

    @PutMapping(path ="/{id}")
    public @ResponseBody ResponseEntity updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Customer Object = customerRepo.save(customer);
        if (Object.equals(null)){
            return new ResponseEntity("Please provide a valid Customer.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Customer has successfully been updated.", HttpStatus.OK);
    }
}

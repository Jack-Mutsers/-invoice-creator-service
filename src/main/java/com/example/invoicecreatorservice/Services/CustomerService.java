package com.example.invoicecreatorservice.Services;

import com.example.invoicecreatorservice.DataTransferObjects.ProductDTO;
import com.example.invoicecreatorservice.Models.Customer;
import com.example.invoicecreatorservice.Models.Product;
import com.example.invoicecreatorservice.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public Customer getCustomer(int id){
        Customer customer = customerRepo.findById(id);

        if(customer.validateCustomer()){
            return null;
        }

        return customer;
    }

    public Iterable<Customer> getAllCustomers() {
        List<Customer> customers = (List) customerRepo.findAll();

        if(customers.size() > 0){ return null; }

        return customers;
    }

    public boolean deleteCustomer(int id) {
        try{
            customerRepo.deleteById(id);

            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public Customer createCustomer(Customer customer) {
        try{
            Customer newObject = customerRepo.save(customer);
            return newObject;
        }catch (Exception ex){
            return null;
        }
    }

    public Boolean updateCustomer(Customer customer) {
        try {
            customerRepo.save(customer);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}

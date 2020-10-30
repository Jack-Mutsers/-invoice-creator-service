package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.CustomerForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.CustomerForUpdateDTO;
import com.example.invoicecreatorservice.models.Customer;
import com.example.invoicecreatorservice.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;

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

    public Customer createCustomer(CustomerForCreationDTO customerDTO) {
        try{
            Customer customer = new Customer(customerDTO);
            Customer newObject = customerRepo.save(customer);
            return newObject;
        }catch (Exception ex){
            return null;
        }
    }

    public Boolean updateCustomer(CustomerForUpdateDTO customerDTO) {
        try {
            Customer customer = new Customer(customerDTO);
            customerRepo.save(customer);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}

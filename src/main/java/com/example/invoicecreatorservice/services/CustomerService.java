package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.data_transfer_objects.CustomerForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.CustomerForUpdateDTO;
import com.example.invoicecreatorservice.models.Customer;
import com.example.invoicecreatorservice.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public CustomerDTO getCustomer(int id){
        Customer customer = customerRepo.findById(id);
        return new CustomerDTO(customer);
    }

    public Iterable<Customer> getAllCustomers() {
        List<Customer> customers = (List) customerRepo.findAll();

        if(customers.isEmpty()){ return null; }

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

    public CustomerDTO createCustomer(CustomerForCreationDTO customerDTO) {
        if(customerDTO.validateCustomer()){
            return null;
        }

        try{
            Customer customer = new Customer(customerDTO);
            Customer newObject = customerRepo.save(customer);
            return new CustomerDTO(newObject);
        }catch (Exception ex){
            return null;
        }
    }

    public Boolean updateCustomer(CustomerForUpdateDTO customerDTO) {
        if(customerDTO.validateCustomer()){
            return false;
        }

        try {
            Customer customer = new Customer(customerDTO);
            customerRepo.save(customer);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}

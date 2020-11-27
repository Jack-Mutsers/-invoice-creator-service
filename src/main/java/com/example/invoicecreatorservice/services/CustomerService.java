package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.ICustomerService;
import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Customer;
import com.example.invoicecreatorservice.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
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
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    public CustomerDTO createCustomer(CustomerForAlterationDTO customerDTO) {
        try{
            Customer customer = new Customer(customerDTO);

            // set id to 0 to prevent update of existing record on create
            customer.setId(0);

            Customer newObject = customerRepo.save(customer);
            return new CustomerDTO(newObject);
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return null;
        }
    }

    public boolean updateCustomer(CustomerForAlterationDTO customerDTO) {
        try {
            Customer customer = new Customer(customerDTO);

            customerRepo.save(customer);
            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }
}

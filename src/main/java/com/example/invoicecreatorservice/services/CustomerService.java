package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.ICustomerService;
import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Customer;
import com.example.invoicecreatorservice.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public CustomerDTO getCustomer(int id){
        Customer customer = customerRepo.findById(id);
        return new CustomerDTO(customer);
    }

    public Iterable<Customer> getAllCustomers(int id) {
        List<Customer> customers = customerRepo.findAllByCompanyId(id);

        if(customers.isEmpty()){ return null; }

        return customers;
    }

    public List<Integer> getMyCustomerIds(int userId){
        List<Customer> customers = customerRepo.findAllByUserId(userId);
        List<Integer> ids = new ArrayList<>();

        for(Customer customer : emptyIfNull(customers)){
            ids.add(customer.getId());
        }

        return ids;
    }

    @Transactional
    public boolean deleteCustomer(int customerId, int companyId) {
        try{
            Customer customer = customerRepo.findById(customerId);

            if(customer.getCompanyId() == companyId){
                customerRepo.deleteById(customerId);
            }else{
                return false;
            }

            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteAllCompanyCustomers(int companyId) {
        try{
            customerRepo.deleteAllByCompanyId(companyId);

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

    private <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? List.of() : iterable;
    }
}

package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.ICustomerService;
import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Customer;
import com.example.invoicecreatorservice.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.example.invoicecreatorservice.helpers.tools.Helper.emptyIfNull;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public CustomerDTO getCustomer(int id){
        Customer customer = customerRepo.findById(id);
        return new CustomerDTO(customer);
    }

    public Iterable<Customer> getAllCustomers(int id) {
        return customerRepo.findAllByCompanyId(id);
    }

    public List<Integer> getMyCustomerIds(String contactCode){
        List<Customer> customers = customerRepo.findAllByContactCode(contactCode);
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

            if(customer.getCompanyId() != companyId){
                return false;
            }

            customerRepo.deleteById(customerId);

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

    public boolean updateCustomerByUser(UserForAlterationDTO userDTO, String contactCode) {
        try {
            List<Customer> customerList = customerRepo.findAllByContactCode(contactCode);

            for(Customer customer : customerList){
                customer.setAddress(userDTO.getAddress());
                customer.setCity(userDTO.getCity());
                customer.setName(userDTO.getName());
                customer.setZipcode(userDTO.getZipcode());
                customerRepo.save(customer);
            }

            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }
}

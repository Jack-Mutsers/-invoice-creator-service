package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Customer;

import java.util.List;

public interface ICustomerService {
    CustomerDTO getCustomer(int id);
    Iterable<Customer> getAllCustomers(int id);
    List<Integer> getMyCustomerIds(int userId);
    boolean deleteCustomer(int customerId, int companyId);
    boolean deleteAllCompanyCustomers(int companyId);
    CustomerDTO createCustomer(CustomerForAlterationDTO customerDTO);
    boolean updateCustomer(CustomerForAlterationDTO customerDTO);
}

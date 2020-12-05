package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Customer;

public interface ICustomerService {
    CustomerDTO getCustomer(int id);
    Iterable<Customer> getAllCustomers(int id);
    boolean deleteCustomer(int customerId, int companyId);
    CustomerDTO createCustomer(CustomerForAlterationDTO customerDTO);
    boolean updateCustomer(CustomerForAlterationDTO customerDTO);
}

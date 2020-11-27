package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Customer;

public interface ICustomerService {
    CustomerDTO getCustomer(int id);
    Iterable<Customer> getAllCustomers();
    boolean deleteCustomer(int id);
    CustomerDTO createCustomer(CustomerForAlterationDTO customerDTO);
    boolean updateCustomer(CustomerForAlterationDTO customerDTO);
}

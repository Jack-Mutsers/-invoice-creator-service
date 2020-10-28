package com.example.invoicecreatorservice.Repositories;

import com.example.invoicecreatorservice.Models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
    Customer findById(int id);
}

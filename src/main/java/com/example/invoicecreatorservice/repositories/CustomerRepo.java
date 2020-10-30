package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
    Customer findById(int id);
}

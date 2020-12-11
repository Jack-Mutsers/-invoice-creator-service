package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.objects.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
    Customer findById(int id);
    List<Customer> findAllByCompanyId(int id);
    List<Customer> findAllByUserId(int userId);
    void deleteAllByCompanyId(int companyId);
}

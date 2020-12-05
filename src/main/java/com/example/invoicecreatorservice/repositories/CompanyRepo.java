package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.objects.models.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepo extends CrudRepository<Company, Integer> {
    Company findById(int id);
    List<Company> findByContactCode(String contactCode);
    Company findByIdAndOwnerId(int id, int ownerId);
}

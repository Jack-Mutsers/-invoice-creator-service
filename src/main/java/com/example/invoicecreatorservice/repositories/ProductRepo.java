package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.objects.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Integer> {
    Product findById(int id);
    List<Product> findAllByCompanyId(int companyId);
    void deleteAllByCompanyId(int companyId);
}

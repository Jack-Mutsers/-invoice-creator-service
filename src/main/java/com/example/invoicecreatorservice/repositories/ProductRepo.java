package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.objects.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Integer> {
    Product findById(int id);
}

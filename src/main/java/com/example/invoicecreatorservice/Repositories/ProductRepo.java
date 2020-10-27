package com.example.invoicecreatorservice.Repositories;

import com.example.invoicecreatorservice.Models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Integer> {
    Product findById(int id);
//    Boolean deleteById(int id);
//    Boolean update(Product object);
}

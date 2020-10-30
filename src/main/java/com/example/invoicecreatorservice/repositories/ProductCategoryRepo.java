package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.models.ProductCategory;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryRepo extends CrudRepository<ProductCategory, Integer> {
    ProductCategory findById(int id);
}

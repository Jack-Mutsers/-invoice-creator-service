package com.example.invoicecreatorservice.Repositories;

import com.example.invoicecreatorservice.Models.ProductCategory;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryRepo extends CrudRepository<ProductCategory, Integer> {
    ProductCategory findById(int id);
//    Boolean deleteById(int id);
//    Boolean update(ProductCategory object);
}

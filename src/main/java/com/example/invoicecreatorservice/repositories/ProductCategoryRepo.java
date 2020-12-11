package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.objects.models.ProductCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCategoryRepo extends CrudRepository<ProductCategory, Integer> {
    ProductCategory findById(int id);
    List<ProductCategory> findAllByCompanyId(int id);
    void deleteAllByCompanyId(int companyId);
}

package com.example.invoicecreatorservice.Services;

import com.example.invoicecreatorservice.Models.Customer;
import com.example.invoicecreatorservice.Models.ProductCategory;
import com.example.invoicecreatorservice.Repositories.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    public ProductCategory getCategory(int id) {
        ProductCategory category = productCategoryRepo.findById(id);

        if (category.validateProductCategory()) {
            return null;
        }

        return category;
    }

    public Iterable<ProductCategory> getAllCategory() {
        List<ProductCategory> categories = (List) productCategoryRepo.findAll();

        if(categories.size() == 0){ return null; }

        return categories;
    }

    public boolean deleteCategory(int id) {
        try{
            productCategoryRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public ProductCategory createCategory(ProductCategory category) {
        try{
            ProductCategory newObject = productCategoryRepo.save(category);
            return newObject;
        } catch (Exception ex){
            return null;
        }
    }

    public boolean updateCategory(ProductCategory category) {
        try{
            productCategoryRepo.save(category);
            return true;
        } catch (Exception ex){
            return false;
        }

    }
}

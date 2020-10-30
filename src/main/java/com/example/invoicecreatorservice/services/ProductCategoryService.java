package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.ProductCategoryForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.ProductCategoryForUpdateDTO;
import com.example.invoicecreatorservice.models.ProductCategory;
import com.example.invoicecreatorservice.repositories.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;

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

    public ProductCategory createCategory(ProductCategoryForCreationDTO categoryDTO) {
        try{
            ProductCategory category = new ProductCategory(categoryDTO);
            ProductCategory newObject = productCategoryRepo.save(category);
            return newObject;
        } catch (Exception ex){
            return null;
        }
    }

    public boolean updateCategory(ProductCategoryForUpdateDTO categoryDTO) {
        try{
            ProductCategory category = new ProductCategory(categoryDTO);
            productCategoryRepo.save(category);
            return true;
        } catch (Exception ex){
            return false;
        }

    }
}

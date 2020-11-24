package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.ProductCategory;
import com.example.invoicecreatorservice.repositories.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    public ProductCategoryDTO getCategory(int id) {
        ProductCategory category = productCategoryRepo.findById(id);

        return new ProductCategoryDTO(category);
    }

    public Iterable<ProductCategory> getAllCategory() {
        List<ProductCategory> categories = (List) productCategoryRepo.findAll();

        if(categories.isEmpty()){ return null; }

        return categories;
    }

    public boolean deleteCategory(int id) {
        try{
            productCategoryRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    public ProductCategoryDTO createCategory(ProductCategoryForAlterationDTO categoryDTO) {
        try{

            ProductCategory category = new ProductCategory(categoryDTO);

            // set id to 0 to prevent update of existing record on create
            category.setId(0);

            ProductCategory newObject = productCategoryRepo.save(category);
            return new ProductCategoryDTO(newObject);
        } catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return null;
        }
    }

    public boolean updateCategory(ProductCategoryForAlterationDTO categoryDTO) {
        try{

            ProductCategory category = new ProductCategory(categoryDTO);
            productCategoryRepo.save(category);
            return true;
        } catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }

    }
}

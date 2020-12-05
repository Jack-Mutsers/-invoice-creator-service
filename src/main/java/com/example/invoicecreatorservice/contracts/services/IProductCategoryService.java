package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.ProductCategory;

public interface IProductCategoryService {
    ProductCategoryDTO getCategory(int id);
    Iterable<ProductCategory> getAllCategory(int id);
    boolean deleteCategory(int id, int companyId);
    ProductCategoryDTO createCategory(ProductCategoryForAlterationDTO categoryDTO);
    boolean updateCategory(ProductCategoryForAlterationDTO categoryDTO);
}

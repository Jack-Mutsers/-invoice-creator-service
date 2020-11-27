package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductForAlterationDTO;

public interface IProductService {
    ProductDTO getProduct(int id);
    Iterable<ProductDTO> getAllProducts();
    boolean deleteProduct(int id);
    ProductDTO createProduct(ProductForAlterationDTO productDTO);
    boolean updateProduct(ProductForAlterationDTO productDTO);
}

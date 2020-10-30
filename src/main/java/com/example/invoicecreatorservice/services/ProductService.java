package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.ProductDTO;
import com.example.invoicecreatorservice.data_transfer_objects.ProductForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.ProductForUpdateDTO;
import com.example.invoicecreatorservice.models.Product;
import com.example.invoicecreatorservice.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public ProductDTO getProduct(int id) {
        Product product = productRepo.findById(id);

        return new ProductDTO(product);
    }

    public Iterable<ProductDTO> getAllProducts() {
        ProductDTO productDTO = new ProductDTO();
        List<ProductDTO> products = productDTO.getProductList((List<Product>) productRepo.findAll());

        if(products.isEmpty()){ return null; }

        return products;
    }

    public boolean deleteProduct(int id) {
        try{
            productRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public ProductDTO createProduct(ProductForCreationDTO productDTO) {
        if (!productDTO.validateProduct()) {
            return null;
        }

        try{
            Product product = new Product(productDTO);
            Product newObject = productRepo.save(product);
            return new ProductDTO(newObject);
        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateProduct(ProductForUpdateDTO productDTO) {
        if (!productDTO.validateProduct()) {
            return false;
        }

        try {
            Product product = new Product(productDTO);
            productRepo.save(product);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}

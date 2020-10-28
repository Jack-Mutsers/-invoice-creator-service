package com.example.invoicecreatorservice.Services;

import com.example.invoicecreatorservice.DataTransferObjects.ProductDTO;
import com.example.invoicecreatorservice.Models.Product;
import com.example.invoicecreatorservice.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public ProductDTO getProduct(int id) {
        Product product = productRepo.findById(id);

        if (!product.validateProduct()) {
            return null;
        }

        return new ProductDTO(product);
    }

    public Iterable<ProductDTO> getAllProducts() {
        ProductDTO Dto = new ProductDTO();
        List<ProductDTO> products = Dto.getProductList((List<Product>) productRepo.findAll());

        if(products.size() == 0){ return null; }

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

    public ProductDTO createProduct(Product product) {
        try{
            Product newObject = productRepo.save(product);
            return new ProductDTO(newObject);
        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            productRepo.save(product);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}

package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.data_transfer_objects.ProductDTO;
import com.example.invoicecreatorservice.data_transfer_objects.ProductForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.ProductForUpdateDTO;
import com.example.invoicecreatorservice.models.Product;
import com.example.invoicecreatorservice.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

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

    public ProductDTO createProduct(ProductForCreationDTO productDTO) {
        try{
            Product product = new Product(productDTO);
            Product newObject = productRepo.save(product);
            return new ProductDTO(newObject);
        }catch (Exception ex){
            return null;
        }
    }

    public boolean updateProduct(ProductForUpdateDTO productDTO) {
        try {
            Product product = new Product(productDTO);
            productRepo.save(product);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}

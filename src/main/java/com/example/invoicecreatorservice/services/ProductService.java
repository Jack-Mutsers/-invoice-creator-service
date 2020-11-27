package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.IProductService;
import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductForAlterationDTO;
import com.example.invoicecreatorservice.objects.models.Product;
import com.example.invoicecreatorservice.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductCategoryService categoryService = new ProductCategoryService();

    public ProductDTO getProduct(int id) {
        Product product = productRepo.findById(id);

        ProductDTO newProductDTO = new ProductDTO(product);
        newProductDTO.setCategory(categoryService.getCategory(product.getCategoryId()));
        return newProductDTO;
    }

    public Iterable<ProductDTO> getAllProducts() {
        ProductDTO productDTO = new ProductDTO();
        List<ProductDTO> products = productDTO.getProductList((List<Product>) productRepo.findAll());

        for (ProductDTO product : products)
        {
            product.setCategory(categoryService.getCategory(product.getCategoryId()));
        }

        if(products.isEmpty()){ return null; }

        return products;
    }

    public boolean deleteProduct(int id) {
        try{
            productRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    public ProductDTO createProduct(ProductForAlterationDTO productDTO) {

        try{
            Product product = new Product(productDTO);

            // set id to 0 to prevent update of existing record on create
            product.setId(0);

            Product newObject = productRepo.save(product);
            ProductDTO newProductDTO = new ProductDTO(newObject);

            newProductDTO.setCategory(categoryService.getCategory(newObject.getCategoryId()));
            return newProductDTO;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return null;
        }
    }

    public boolean updateProduct(ProductForAlterationDTO productDTO) {

        try {
            Product product = new Product(productDTO);
            productRepo.save(product);
            return true;
        }catch (Exception ex){
            LoggerService.warn(ex.getMessage());
            return false;
        }
    }
}

package com.example.invoicecreatorservice.Controllers;

import com.example.invoicecreatorservice.DataTransferObjects.ProductDTO;
import com.example.invoicecreatorservice.Models.Product;
import com.example.invoicecreatorservice.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {
        ProductDTO product = new ProductDTO(productRepo.findById(id));
        if (product == null) {
            return new ResponseEntity("Please provide a valid product identifier.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
        }
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<Iterable<ProductDTO>> getAllProducts() {
        ProductDTO Dto = new ProductDTO();
        List<ProductDTO> products = Dto.getProductList((List<Product>) productRepo.findAll());
        if(products.size() == 0){ return new ResponseEntity("There are currently no products availible", HttpStatus.NOT_FOUND); }

        return new ResponseEntity<Iterable<ProductDTO>>(products, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity deleteProduct(@PathVariable int id) {
        boolean success = true;productRepo.deleteById(id);
        if(success){
            return new ResponseEntity("Product has been deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity("Product not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        Product newObject = productRepo.save(product);
        if (newObject.equals(null)){
            return new ResponseEntity("The product can not be added", HttpStatus.CONFLICT);
        } else {
            ProductDTO productDTO = new ProductDTO(newObject);
            return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.CREATED);
        }
    }

    @PutMapping(path ="/{id}")
    public @ResponseBody ResponseEntity updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product newObject = productRepo.save(product);
        if (newObject.equals(null)){
            return new ResponseEntity("Please provide a valid product.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Product has successfully been updated.", HttpStatus.OK);

    }
}

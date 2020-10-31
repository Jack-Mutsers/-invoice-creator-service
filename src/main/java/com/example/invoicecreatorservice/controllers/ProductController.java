package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.ProductDTO;
import com.example.invoicecreatorservice.data_transfer_objects.ProductForCreationDTO;
import com.example.invoicecreatorservice.data_transfer_objects.ProductForUpdateDTO;
import com.example.invoicecreatorservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductService service = new ProductService();

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Object> getProduct(@PathVariable int id) {
        ProductDTO product = service.getProduct(id);

        if (product == null) {
            return new ResponseEntity<>("Please provide a valid product identifier.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<Object> getAllProducts() {
        Iterable<ProductDTO> products = service.getAllProducts();

        if(products == null){
            return new ResponseEntity<>("There are currently no products availible", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteProduct(@PathVariable int id) {
        boolean success = service.deleteProduct(id);

        if(!success){
            return new ResponseEntity<>("Product not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Product has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<Object> createProduct(@RequestBody ProductForCreationDTO product) {
        ProductDTO newObject = service.createProduct(product);

        if (newObject == null){
            return new ResponseEntity<>("The product can not be added", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<String> updateProduct(@RequestBody ProductForUpdateDTO product) {
        boolean success = service.updateProduct(product);

        if (!success){
            return new ResponseEntity<>("Please provide a valid product.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Product has successfully been updated.", HttpStatus.OK);

    }
}

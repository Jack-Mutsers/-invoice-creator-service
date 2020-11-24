package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductForAlterationDTO;
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
    public @ResponseBody ResponseEntity<Object> createProduct(@RequestBody ProductForAlterationDTO productDTO) {
        if (productDTO.validateForCreation()) {
            return new ResponseEntity<>("The product can not be added", HttpStatus.CONFLICT);
        }

        ProductDTO newObject = service.createProduct(productDTO);

        if (newObject == null){
            return new ResponseEntity<>("something went wrong while trying to create the product", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<String> updateProduct(@RequestBody ProductForAlterationDTO productDTO) {
        if (productDTO.validateForUpdate()) {
            return new ResponseEntity<>("Please provide a valid product.", HttpStatus.NOT_FOUND);
        }

        boolean success = service.updateProduct(productDTO);

        if (!success){
            return new ResponseEntity<>("Something went wrong while trying to update the product.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Product has successfully been updated.", HttpStatus.OK);

    }
}

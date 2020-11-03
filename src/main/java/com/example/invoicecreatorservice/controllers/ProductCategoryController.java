package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.data_transfer_objects.ProductCategoryDTO;
import com.example.invoicecreatorservice.data_transfer_objects.ProductCategoryForAlterationDTO;
import com.example.invoicecreatorservice.models.ProductCategory;
import com.example.invoicecreatorservice.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/productcategory")
public class ProductCategoryController {

    @Autowired
    private final ProductCategoryService service = new ProductCategoryService();

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Object> getCategory(@PathVariable int id) {
        ProductCategoryDTO category = service.getCategory(id);

        if (category == null) {
            return new ResponseEntity<>("Please provide a valid category identifier.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<Object> getAllCategory() {
        Iterable<ProductCategory> categories = service.getAllCategory();

        if(categories == null){
            return new ResponseEntity<>("There are currently no customers availible", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<String> deleteCategory(@PathVariable int id) {
        boolean success = service.deleteCategory(id);

        if(!success){
            return new ResponseEntity<>("Product category was not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Product category has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<Object> createCategory(@RequestBody ProductCategoryForAlterationDTO category) {
        ProductCategoryDTO newObject = service.createCategory(category);

        if (newObject == null){
            return new ResponseEntity<>("The product has not been added", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<String> updateCategory(@RequestBody ProductCategoryForAlterationDTO category) {
        boolean success = service.updateCategory(category);

        if (!success){
            return new ResponseEntity<>("Please provide a valid product category.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Product category has successfully been updated.", HttpStatus.OK);

    }
}

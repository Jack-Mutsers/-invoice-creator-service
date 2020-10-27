package com.example.invoicecreatorservice.Controllers;

import com.example.invoicecreatorservice.Models.ProductCategory;
import com.example.invoicecreatorservice.Repositories.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/productcategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<ProductCategory> getCategory(@PathVariable int id) {
        ProductCategory category = productCategoryRepo.findById(id);
        if (category == null) {
            return new ResponseEntity("Please provide a valid category identifier.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<ProductCategory>(category, HttpStatus.OK);
        }
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<Iterable<ProductCategory>> getAllCategory() {
        List<ProductCategory> categories = (List) productCategoryRepo.findAll();
        if(categories.size() == 0){ return new ResponseEntity("There are currently no customers availible", HttpStatus.NOT_FOUND); }
        return new ResponseEntity<Iterable<ProductCategory>>(categories, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity deleteCategory(@PathVariable int id) {
        boolean success = true;productCategoryRepo.deleteById(id);
        if(success){
            return new ResponseEntity("Product category has been deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity("Product category was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategory category) {
        ProductCategory newObject = productCategoryRepo.save(category);
        if (newObject.equals(null)){
            return new ResponseEntity("The user can not be added because it is not complete", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<ProductCategory>(newObject, HttpStatus.CREATED);
        }
    }

    @PutMapping(path ="/{id}")
    public @ResponseBody ResponseEntity updateCategory(@PathVariable int id, @RequestBody ProductCategory category) {
        ProductCategory newObject = productCategoryRepo.save(category);
        if (newObject.equals(null)){
            return new ResponseEntity("Please provide a valid product category.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Product category has successfully been updated.", HttpStatus.OK);

    }
}

package com.example.invoicecreatorservice.Controllers;

import com.example.invoicecreatorservice.Models.ProductCategory;
import com.example.invoicecreatorservice.Repositories.ProductCategoryRepo;
import com.example.invoicecreatorservice.Services.ProductCategoryService;
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

    private ProductCategoryService service = new ProductCategoryService();

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<ProductCategory> getCategory(@PathVariable int id) {
        ProductCategory category = service.getCategory(id);

        if (category == null) {
            return new ResponseEntity("Please provide a valid category identifier.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<Iterable<ProductCategory>> getAllCategory() {
        Iterable<ProductCategory> categories = service.getAllCategory();

        if(categories == null){
            return new ResponseEntity("There are currently no customers availible", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity deleteCategory(@PathVariable int id) {
        boolean success = service.deleteCategory(id);

        if(!success){
            return new ResponseEntity("Product category was not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Product category has been deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategory category) {
        ProductCategory newObject = service.createCategory(category);

        if (newObject == null){
            return new ResponseEntity("The user can not be added because it is not complete", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newObject, HttpStatus.CREATED);
    }

    @PutMapping(path ="/{id}")
    public @ResponseBody ResponseEntity updateCategory(@PathVariable int id, @RequestBody ProductCategory category) {
        boolean success = service.updateCategory(category);

        if (!success){
            return new ResponseEntity("Please provide a valid product category.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("Product category has successfully been updated.", HttpStatus.OK);

    }
}

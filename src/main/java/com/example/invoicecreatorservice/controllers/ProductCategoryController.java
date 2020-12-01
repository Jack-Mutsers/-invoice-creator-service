package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductCategoryForAlterationDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ResponseDTO;
import com.example.invoicecreatorservice.objects.models.ProductCategory;
import com.example.invoicecreatorservice.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/productcategory")
public class ProductCategoryController extends BaseCompanyController {

    @Autowired
    private final ProductCategoryService service = new ProductCategoryService();

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<ResponseDTO> getCategory(@PathVariable int id) {
        ProductCategoryDTO category = service.getCategory(id);

        if (category == null) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide a valid category identifier."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, category), HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> getAllCategory(HttpServletRequest request) {
        int companyId = super.getCompanyId(request);

        if(companyId == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "There are currently no product categories availible"), HttpStatus.NOT_FOUND);
        }

        Iterable<ProductCategory> categories = service.getAllCategory(companyId);

        if(categories == null){
            return new ResponseEntity<>(new ResponseDTO(true, "There are currently no product categories availible"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(true, categories), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<ResponseDTO> deleteCategory(HttpServletRequest request, @PathVariable int id) {
        int companyId = super.getCompanyId(request);

        if(companyId == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "Product category was not found."), HttpStatus.NOT_FOUND);
        }

        boolean success = service.deleteCategory(id, companyId);

        if(!success){
            return new ResponseEntity<>(new ResponseDTO(false, "Product category was not found."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Product category has been deleted successfully."), HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> createCategory(HttpServletRequest request, @RequestBody ProductCategoryForAlterationDTO categoryDTO) {
        int companyId = super.getCompanyId(request);

        categoryDTO.setCompanyId(companyId);

        if (categoryDTO.validateForCreation()) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the creation"), HttpStatus.CONFLICT);
        }

        ProductCategoryDTO newObject = service.createCategory(categoryDTO);

        if (newObject == null){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the creation of the category."), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Category has been added"), HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<ResponseDTO> updateCategory(HttpServletRequest request, @RequestBody ProductCategoryForAlterationDTO categoryDTO) {
        int companyId = super.getCompanyId(request);

        categoryDTO.setCompanyId(companyId);
        if (categoryDTO.validateForUpdate()) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide valid data for the update"), HttpStatus.CONFLICT);
        }

        boolean success = service.updateCategory(categoryDTO);

        if (!success){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong with the update of the category."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Product category has successfully been updated."), HttpStatus.OK);

    }
}

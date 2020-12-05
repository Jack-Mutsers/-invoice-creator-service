package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ProductForAlterationDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ResponseDTO;
import com.example.invoicecreatorservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {

    @Autowired
    private final ProductService service = new ProductService();

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<ResponseDTO> getProduct(@PathVariable int id) {
        ProductDTO product = service.getProduct(id);

        if (product == null) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide a valid product identifier."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, product), HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> getAllProducts(HttpServletRequest request) {
        int companyId = super.getCompanyId(request);

        if(companyId == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "permission denied"), HttpStatus.FORBIDDEN);
        }

        Iterable<ProductDTO> products = service.getAllProducts(companyId);

        if(products == null){
            return new ResponseEntity<>(new ResponseDTO(true, "There are currently no products availible"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(true, products), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<ResponseDTO> deleteProduct(HttpServletRequest request, @PathVariable int id) {
        int companyId = super.getCompanyId(request);

        if(companyId == 0){
            return new ResponseEntity<>(new ResponseDTO(false, "permission denied"), HttpStatus.FORBIDDEN);
        }

        boolean success = service.deleteProduct(id, companyId);

        if(!success){
            return new ResponseEntity<>(new ResponseDTO(false, "Product not found."), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Product has been deleted successfully."), HttpStatus.OK);
    }

    @PostMapping(path="")
    public @ResponseBody ResponseEntity<ResponseDTO> createProduct(HttpServletRequest request, @RequestBody ProductForAlterationDTO productDTO) {
        int companyId = super.getCompanyId(request);

        productDTO.setCompanyId(companyId);

        if (productDTO.validateForCreation()) {
            return new ResponseEntity<>(new ResponseDTO(false, "The product can not be added"), HttpStatus.CONFLICT);
        }

        ProductDTO newObject = service.createProduct(productDTO);

        if (newObject == null){
            return new ResponseEntity<>(new ResponseDTO(false, "something went wrong while trying to create the product"), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Product/service has been added"), HttpStatus.CREATED);
    }

    @PutMapping(path ="")
    public @ResponseBody ResponseEntity<ResponseDTO> updateProduct(HttpServletRequest request, @RequestBody ProductForAlterationDTO productDTO) {
        int companyId = super.getCompanyId(request);

        productDTO.setCompanyId(companyId);

        if (productDTO.validateForUpdate()) {
            return new ResponseEntity<>(new ResponseDTO(false, "Please provide a valid product."), HttpStatus.NOT_FOUND);
        }

        boolean success = service.updateProduct(productDTO);

        if (!success){
            return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong while trying to update the product."), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ResponseDTO(true, "Product has successfully been updated."), HttpStatus.OK);

    }
}

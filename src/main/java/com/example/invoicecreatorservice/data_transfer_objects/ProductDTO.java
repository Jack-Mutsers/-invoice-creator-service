package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.models.Product;
import com.example.invoicecreatorservice.models.ProductCategory;
import com.example.invoicecreatorservice.repositories.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    @Autowired
    ProductCategoryRepo productCategoryRepo;

    private int id;
    private String name;
    private Double price;
    private ProductCategory category;
    private String productCode;

    public ProductDTO(int id, String name, double price, int categoryId, String productCode){

        this.id = id;
        this.name = name;
        this.price = (double)Math.round(price * 100.0);
        this.category = productCategoryRepo.findById(categoryId);
        this.productCode = productCode;
    }

    public ProductDTO(Product product){

        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.category = productCategoryRepo.findById(product.getCategoryId());
        this.productCode = product.getProductCode();
    }

    public ProductDTO(){

    }

    public List<ProductDTO> getProductList(List<Product> products){
        List<ProductDTO> productList = new ArrayList<>();
        for (Product product : products)
        {
            productList.add( new ProductDTO(product) );
        }

        return productList;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String Name){
        this.name = name;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public ProductCategory getCategory(){
        return category;
    }

    public void setCategory(ProductCategory category){
        this.category = category;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
}

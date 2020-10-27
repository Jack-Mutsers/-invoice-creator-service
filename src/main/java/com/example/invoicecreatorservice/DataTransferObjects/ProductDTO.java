package com.example.invoicecreatorservice.DataTransferObjects;

import com.example.invoicecreatorservice.Models.Product;
import com.example.invoicecreatorservice.Models.ProductCategory;
import com.example.invoicecreatorservice.Repositories.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    @Autowired
    ProductCategoryRepo productCategoryRepo;

    private int id;
    private String name;
    private int price;
    private ProductCategory category;
    private String productCode;

    public ProductDTO(int id, String name, float price, int categoryId, String productCode){

        this.id = id;
        this.name = name;
        this.price = (int)Math.round(price * 100.0);
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

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
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

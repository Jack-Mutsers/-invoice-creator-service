package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    private int id;
    private String name;
    private Double price;
    private int categoryId;
    private ProductCategoryDTO category;
    private String productCode;

    public ProductDTO(int id, String name, double price, int categoryId, String productCode){

        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = (double)Math.round(price * 100.0);
        this.productCode = productCode;
    }

    public ProductDTO(Product product){

        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.categoryId = product.getCategoryId();
        this.productCode = product.getProductCode();
    }

    public ProductDTO(){
        this.price = 0.0;
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

    public void setName(String name){
        this.name = name;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public int getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }

    public ProductCategoryDTO getCategory(){
        return category;
    }

    public void setCategory(ProductCategoryDTO category){
        this.category = category;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
}

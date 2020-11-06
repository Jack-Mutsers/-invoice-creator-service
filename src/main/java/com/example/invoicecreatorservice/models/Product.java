package com.example.invoicecreatorservice.models;

import com.example.invoicecreatorservice.data_transfer_objects.ProductForAlterationDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private String productCode;
    private int categoryId;

    public Product(int id, String name, double price, int categoryId, String productCode){
        this.id = id;
        this.name = name;
        this.price = (int)Math.round(price * 100.0);
        this.categoryId = categoryId;
        this.productCode = productCode;
    }

    public Product(ProductForAlterationDTO productDTO){
        this.id = productDTO.getId();
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.categoryId = productDTO.getCategoryId();
        this.productCode = productDTO.getProductCode();
    }

    public Product(){

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
        return price / 100.0;
    }

    public void setPrice(double price){
        this.price = (int)Math.round(price * 100.0);
    }

    public int getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }

    public String getProductCode(){
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}

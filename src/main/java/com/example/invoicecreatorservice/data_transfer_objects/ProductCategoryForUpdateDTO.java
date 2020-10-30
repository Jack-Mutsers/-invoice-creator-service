package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.models.ProductCategory;

public class ProductCategoryForUpdateDTO {
    private int id;
    private String name;
    private int btw;

    public ProductCategoryForUpdateDTO(int id, String name, int btw){
        this.id = id;
        this.name = name;
        this.btw = btw;
    }

    public ProductCategoryForUpdateDTO(ProductCategory productCategory){
        this.id = productCategory.getId();
        this.name = productCategory.getName();
        this.btw = productCategory.getBtw();
    }

    public ProductCategoryForUpdateDTO(){

    }

    public boolean validateProductCategory(){
        return !( this.id == 0 || this.name == null || this.btw == 0 );
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

    public int getBtw(){
        return btw;
    }

    public void setBtw(int btw){
        this.btw = btw;
    }
}

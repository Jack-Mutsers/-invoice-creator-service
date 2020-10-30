package com.example.invoicecreatorservice.data_transfer_objects;

public class ProductCategoryForCreationDTO {
    private String name;
    private int btw;

    public ProductCategoryForCreationDTO(String name, int btw){
        this.name = name;
        this.btw = btw;
    }

    public ProductCategoryForCreationDTO(){

    }

    public boolean validateProductCategory(){
        return !( this.name == null || this.btw == 0 );
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

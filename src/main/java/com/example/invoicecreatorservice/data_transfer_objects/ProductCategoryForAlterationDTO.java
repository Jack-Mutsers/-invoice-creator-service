package com.example.invoicecreatorservice.data_transfer_objects;

public class ProductCategoryForAlterationDTO {
    private int id;
    private String name;
    private int btw;

    public ProductCategoryForAlterationDTO(int id, String name, int btw){
        this.id = id;
        this.name = name;
        this.btw = btw;
    }

    public ProductCategoryForAlterationDTO(){

    }

    public boolean validateForUpdate(){
        return ( this.id == 0 || this.name == null || this.btw == 0 );
    }

    public boolean validateForCreation(){
        return ( this.name == null || this.btw == 0 );
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

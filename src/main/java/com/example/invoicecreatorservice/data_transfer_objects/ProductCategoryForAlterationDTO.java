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
        return ( this.id == 0 || this.validateForCreation() );
    }

    public boolean validateForCreation(){
        return ( this.name == null || this.name.isBlank() || this.btw == 0 );
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getBtw(){
        return btw;
    }

}

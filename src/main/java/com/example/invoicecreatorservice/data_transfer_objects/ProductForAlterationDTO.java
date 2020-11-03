package com.example.invoicecreatorservice.data_transfer_objects;

public class ProductForAlterationDTO {
    private int id;
    private String name;
    private int price;
    private int categoryId;
    private String productCode;

    public ProductForAlterationDTO(int id, String name, double price, int categoryId, String productCode){

        this.id = id;
        this.name = name;
        this.price = (int)Math.round(price * 100.0);
        this.categoryId = categoryId;
        this.productCode = productCode;
    }

    public ProductForAlterationDTO(){

    }

    public boolean validateForUpdate(){
        return ( this.id == 0 || this.name == null || this.price == 0 || this.categoryId == 0 || this.productCode == null );
    }

    public boolean validateForCreation(){
        return ( this.name == null || this.price == 0 || this.categoryId == 0 || this.productCode == null );
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

    public int getPrice(){
        return price;
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

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
}

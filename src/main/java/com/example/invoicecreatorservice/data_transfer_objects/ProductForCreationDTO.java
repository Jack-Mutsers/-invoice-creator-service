package com.example.invoicecreatorservice.data_transfer_objects;

public class ProductForCreationDTO {
    private String name;
    private int price;
    private int categoryId;
    private String productCode;

    public ProductForCreationDTO(String name, double price, int categoryId, String productCode){
        this.name = name;
        this.price = (int)Math.round(price * 100.0);
        this.categoryId = categoryId;
        this.productCode = productCode;
    }

    public ProductForCreationDTO(){

    }

    public boolean validateProduct(){
        return !( this.name == null || this.price == 0 || this.categoryId == 0 || this.productCode == null );
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

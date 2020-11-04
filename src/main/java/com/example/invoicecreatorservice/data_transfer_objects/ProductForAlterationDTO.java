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

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public int getCategoryId(){
        return categoryId;
    }

    public String getProductCode(){
        return productCode;
    }

}

package com.example.invoicecreatorservice.objects.data_transfer_objects;

import lombok.Getter;

import static com.example.invoicecreatorservice.helpers.tools.InputValidator.validateStringValue;

@Getter
public class ProductForAlterationDTO {
    private int id;
    private String name;
    private int price;
    private int categoryId;
    private String productCode;
    private int companyId;

    public ProductForAlterationDTO(int id, String name, double price, int categoryId, String productCode, int companyId){

        this.id = id;
        this.name = name;
        this.price = (int)Math.round(price * 100.0);
        this.categoryId = categoryId;
        this.productCode = productCode;
        this.companyId = companyId;
    }

    public ProductForAlterationDTO(){}

    public boolean validateForUpdate(){
        return ( this.id == 0 || this.validateForCreation() );
    }

    public boolean validateForCreation(){
        return ( this.companyId == 0 || validateStringValue(this.name) || this.price == 0 || this.categoryId == 0 || validateStringValue(this.productCode) );
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = (int)Math.round(price * 100.0);
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

}

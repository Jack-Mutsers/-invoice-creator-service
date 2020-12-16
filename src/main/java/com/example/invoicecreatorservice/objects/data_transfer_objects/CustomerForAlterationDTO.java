package com.example.invoicecreatorservice.objects.data_transfer_objects;

import lombok.Getter;

@Getter
public class CustomerForAlterationDTO {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private int userId;
    private int companyId;

    public CustomerForAlterationDTO(int id, String name, String address, String zipcode, String city, int userId, int companyId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.userId = userId;
        this.companyId = companyId;
    }

    public CustomerForAlterationDTO(){}

    public boolean validateForUpdate(){
        return ( this.id == 0 || this.validateForCreation() );
    }

    public boolean validateForCreation(){
        return (this.companyId == 0 || this.validateValue(this.name) || this.validateValue(this.address) || this.validateValue(this.zipcode) || this.validateValue(this.city));
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    private boolean validateValue(String value){
        return value == null || value.isBlank();
    }
}

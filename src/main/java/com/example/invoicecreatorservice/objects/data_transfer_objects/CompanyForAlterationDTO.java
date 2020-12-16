package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.helpers.tools.ContactGenerator;
import lombok.Getter;

@Getter
public class CompanyForAlterationDTO {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String telephoneNumber;
    private String contactCode;
    private int ownerId;

    public CompanyForAlterationDTO(String name, String address, String zipcode, String city, String telephoneNumber, int ownerId) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.telephoneNumber = telephoneNumber;
        this.ownerId = ownerId;

        this.generateContactCode();
    }

    public CompanyForAlterationDTO(){}

    public CompanyForAlterationDTO(int id, String name, String address, String zipcode, String city, String telephoneNumber, String contactCode, int ownerId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.telephoneNumber = telephoneNumber;
        this.contactCode = contactCode;
        this.ownerId = ownerId;
    }

    public boolean validateForUpdate(){
        return ( this.validateForCreation() || this.ownerId == 0 || this.id == 0 || this.validateValue(this.contactCode));
    }

    public boolean validateForCreation(){
        return (this.validateValue(this.name) || this.validateValue(this.address) || this.validateValue(this.zipcode)
                || this.validateValue(this.city) || this.validateValue(this.telephoneNumber));
    }

    public void setId(int id) {
        this.id = id;
    }

    public void generateContactCode(){
        ContactGenerator generator = new ContactGenerator();
        this.contactCode = generator.generateCode();
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    private boolean validateValue(String value){
        return value == null || value.isBlank();
    }
}

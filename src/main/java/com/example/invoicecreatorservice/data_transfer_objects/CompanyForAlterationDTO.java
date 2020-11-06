package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.tools.ContactGenerator;

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

    public CompanyForAlterationDTO(){

    }

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
        return ( this.id == 0 || this.name == null || this.address == null || this.zipcode == null || this.city == null || this.telephoneNumber == null || this.contactCode == null );
    }

    public boolean validateForCreation(){
        return (this.name == null || this.address == null || this.zipcode == null || this.city == null || this.telephoneNumber == null);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getContactCode() {
        return contactCode;
    }

    public void generateContactCode(){
        ContactGenerator generator = new ContactGenerator();
        this.contactCode = generator.generateCode();
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}

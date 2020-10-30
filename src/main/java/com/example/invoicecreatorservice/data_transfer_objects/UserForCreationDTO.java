package com.example.invoicecreatorservice.data_transfer_objects;

public class UserForCreationDTO {
    private String name;
    private String address;
    private String zipcode;
    private String city;

    public UserForCreationDTO(String name, String address, String zipcode, String city) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    public UserForCreationDTO(){

    }

    public boolean validateUser(){
        return !( this.name == null || this.address == null || this.zipcode == null || this.city == null );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

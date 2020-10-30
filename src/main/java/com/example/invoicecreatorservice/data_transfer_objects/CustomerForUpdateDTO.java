package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.models.Customer;

public class CustomerForUpdateDTO {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;

    public CustomerForUpdateDTO(int id, String name, String address, String zipcode, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    public CustomerForUpdateDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.zipcode = customer.getZipcode();
        this.city = customer.getCity();
    }

    public CustomerForUpdateDTO(){

    }

    public boolean validateCustomer(){
        return ( this.id > 0 || this.name == null || this.address == null || this.zipcode == null || this.city == null );
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.models.Customer;

public class CustomerDTO {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;

    public CustomerDTO(int id, String name, String address, String zipcode, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.zipcode = customer.getZipcode();
        this.city = customer.getCity();
    }

    public CustomerDTO(){

    }

    public boolean validateCustomer(){
        if(
                this.id > 0 ||
                        this.name == null ||
                        this.address == null ||
                        this.zipcode == null ||
                        this.city == null
        ){
            return false;
        }

        return true;
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

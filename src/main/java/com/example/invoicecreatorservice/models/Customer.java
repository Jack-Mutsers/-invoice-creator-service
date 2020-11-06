package com.example.invoicecreatorservice.models;

import com.example.invoicecreatorservice.data_transfer_objects.CustomerForAlterationDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;

    public Customer(int id, String name, String address, String zipcode, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    public Customer(CustomerForAlterationDTO customerDTO) {
        this.id = customerDTO.getId();
        this.name = customerDTO.getName();
        this.address = customerDTO.getAddress();
        this.zipcode = customerDTO.getZipcode();
        this.city = customerDTO.getCity();
    }

    public Customer(){

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

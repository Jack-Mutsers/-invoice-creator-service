package com.example.invoicecreatorservice.models;

import com.example.invoicecreatorservice.data_transfer_objects.CompanyForAlterationDTO;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@SuppressWarnings("WeakerAccess")
//@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable = false)
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String telephoneNumber;

    @Column(unique=true, nullable = false)
    private String contactCode;
    private int ownerId;

    public Company(CompanyForAlterationDTO companyDTO) {
        this.id = companyDTO.getId();
        this.name = companyDTO.getName();
        this.address = companyDTO.getAddress();
        this.zipcode = companyDTO.getZipcode();
        this.city = companyDTO.getCity();
        this.telephoneNumber = companyDTO.getTelephoneNumber();
        this.contactCode = companyDTO.getContactCode();
        this.ownerId = companyDTO.getOwnerId();
    }

    public Company(){

    }

    public Company(int id, String name, String address, String zipcode, String city, String telephoneNumber, String contactCode, int ownerId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.telephoneNumber = telephoneNumber;
        this.contactCode = contactCode;
        this.ownerId = ownerId;
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getContactCode() {
        return contactCode;
    }

    public void setContactCode(String contactCode) {
        this.contactCode = contactCode;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}

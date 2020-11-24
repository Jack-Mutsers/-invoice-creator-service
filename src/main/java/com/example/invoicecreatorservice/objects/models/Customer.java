package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;

    public Customer(CustomerForAlterationDTO customerDTO) {
        this.id = customerDTO.getId();
        this.name = customerDTO.getName();
        this.address = customerDTO.getAddress();
        this.zipcode = customerDTO.getZipcode();
        this.city = customerDTO.getCity();
    }

    public Customer(){}
}

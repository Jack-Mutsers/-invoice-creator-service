package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.base.Person;
import com.example.invoicecreatorservice.objects.data_transfer_objects.CustomerForAlterationDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@SuppressWarnings("WeakerAccess")
@Getter
@Setter
@Entity
public class Customer extends Person {
    private int userId;

    public Customer(int id, String name, String address, String zipcode, String city, int userId) {
        super(id, name, address, zipcode, city);
        this.userId = userId;
    }

    public Customer(CustomerForAlterationDTO customerDTO) {
        super(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getZipcode(), customerDTO.getCity());
        this.userId = customerDTO.getUserId();
    }

    public Customer(){}
}

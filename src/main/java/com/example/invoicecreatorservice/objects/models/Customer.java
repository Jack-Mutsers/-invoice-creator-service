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
    private int companyId;

    public Customer(int id, String name, String address, String zipcode, String city, int userId, int companyId) {
        super(id, name, address, zipcode, city);
        this.userId = userId;
        this.companyId = companyId;
    }

    public Customer(CustomerForAlterationDTO customerDTO) {
        super(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getZipcode(), customerDTO.getCity());
        this.userId = customerDTO.getUserId();
        this.companyId = customerDTO.getCompanyId();
    }

    public Customer(){}
}

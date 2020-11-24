package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.zipcode = customer.getZipcode();
        this.city = customer.getCity();
    }

    public CustomerDTO(){}

}

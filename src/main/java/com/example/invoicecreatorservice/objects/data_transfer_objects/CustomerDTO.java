package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private int companyId;
    private String contactCode;

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.zipcode = customer.getZipcode();
        this.city = customer.getCity();
        this.companyId = customer.getCompanyId();
        this.contactCode = customer.getContactCode();
    }

    public CustomerDTO(){}

}

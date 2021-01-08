package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String telephoneNumber;
    private String contactCode;
    private int ownerId;

    public CompanyDTO(Company company) {
        this.name = company.getName();
        this.address = company.getAddress();
        this.zipcode = company.getZipcode();
        this.city = company.getCity();
        this.telephoneNumber = company.getTelephoneNumber();
        this.contactCode = company.getContactCode();
        this.ownerId = company.getOwnerId();
    }

    public CompanyDTO(){}
}

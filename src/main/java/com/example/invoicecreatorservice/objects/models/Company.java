package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.CompanyForAlterationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@SuppressWarnings("WeakerAccess")
@AllArgsConstructor
@Entity
@Getter
@Setter
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

    public Company(){}

}

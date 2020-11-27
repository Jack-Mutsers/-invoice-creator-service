package com.example.invoicecreatorservice.objects.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String name;
    protected String address;
    protected String zipcode;
    protected String city;

    public Person( int id, String name, String address, String zipcode, String city ){
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    public Person(){}
}

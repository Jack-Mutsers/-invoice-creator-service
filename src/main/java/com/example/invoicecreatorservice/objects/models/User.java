package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.base.Person;
import com.example.invoicecreatorservice.objects.data_transfer_objects.UserForAlterationDTO;

import javax.persistence.Entity;

@SuppressWarnings("WeakerAccess")
@Entity
public class User extends Person {

    public User(int id, String name, String address, String zipcode, String city) {
        super(id, name, address, zipcode, city);
    }

    public User(UserForAlterationDTO userDto) {
        super(userDto.getId(), userDto.getName(), userDto.getAddress(), userDto.getZipcode(), userDto.getCity());
    }

    public User() {}

}

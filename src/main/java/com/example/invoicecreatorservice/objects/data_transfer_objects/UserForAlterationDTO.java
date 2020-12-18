package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.base.Person;

import static com.example.invoicecreatorservice.helpers.tools.InputValidator.validateStringValue;

public class UserForAlterationDTO extends Person {

    public UserForAlterationDTO(int id, String name, String address, String zipcode, String city) {
        super(id, name, address, zipcode, city);
    }

    public UserForAlterationDTO(){}

    public boolean validateForUpdate() {
        return (this.id == 0 || this.validateForCreation());
    }

    public boolean validateForCreation(){
        return ( validateStringValue(this.name) || validateStringValue(this.address) || validateStringValue(this.zipcode) || validateStringValue(this.city));
    }


}

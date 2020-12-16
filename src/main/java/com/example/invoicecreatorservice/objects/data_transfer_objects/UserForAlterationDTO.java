package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.base.Person;

public class UserForAlterationDTO extends Person {

    public UserForAlterationDTO(int id, String name, String address, String zipcode, String city) {
        super(id, name, address, zipcode, city);
    }

    public UserForAlterationDTO(){}

    public boolean validateForUpdate() {
        return (this.id == 0 || this.validateForCreation());
    }

    public boolean validateForCreation(){
        return ( this.validateValue(this.name) || this.validateValue(this.address) || this.validateValue(this.zipcode) || this.validateValue(this.city));
    }

    private boolean validateValue(String value){
        return value == null || value.isBlank();
    }

}

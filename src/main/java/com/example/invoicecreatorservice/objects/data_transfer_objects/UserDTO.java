package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.base.Person;
import com.example.invoicecreatorservice.objects.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends Person {
    private String contactCode;

    public UserDTO(User user) {
        super(
            user.getId(),
            user.getName(),
            user.getAddress(),
            user.getZipcode(),
            user.getCity()
        );
    }

    public UserDTO(User user, String contactCode) {
        super(
            user.getId(),
            user.getName(),
            user.getAddress(),
            user.getZipcode(),
            user.getCity()
        );

        this.contactCode = contactCode;
    }

    public UserDTO(){}

}

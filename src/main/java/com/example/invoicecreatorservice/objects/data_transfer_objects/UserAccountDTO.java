package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountDTO {
    private int id;
    private String username;
    private int userId;
    private UserDTO user;
    private String contactCode;
    private CompanyDTO company;
    private String token;

    public UserAccountDTO(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.username = userAccount.getUsername();
        this.userId = userAccount.getUserId();
        this.contactCode = userAccount.getContactCode();
    }

    public UserAccountDTO(){}

}

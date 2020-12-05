package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountForAlterationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class UserAccount {
    public static final String ADMIN    = "ROLE_ADMIN";
    public static final String OWNER    = "ROLE_OWNER";
    public static final String EMPLOYEE = "ROLE_EMPLOYEE";
    public static final String USER     = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean active;

    @Column(unique=true, nullable = false)
    private String username;
    private String password;

    @Column(unique=true, nullable = false)
    private int userId;

    @Column(unique=true, nullable = false)
    private String contactCode;
    private int companyId;
    private String role;

    public UserAccount(UserAccountForAlterationDTO accountDTO) {
        this.id = accountDTO.getId();
        this.active = accountDTO.getActive();
        this.username = accountDTO.getUsername();
        this.password = accountDTO.getPassword();
        this.contactCode = accountDTO.getContactCode();
        this.companyId = accountDTO.getCompanyId();
        this.role = accountDTO.getRole();

        if(accountDTO.getUser() != null){
            this.userId = accountDTO.getUser().getId();
        }
    }

    public UserAccount(){}

}

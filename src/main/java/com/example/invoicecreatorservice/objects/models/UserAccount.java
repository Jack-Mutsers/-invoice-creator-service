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
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    public static final String OWNER_ROLE = "ROLE_OWNER";
    public static final String EMPLOYEE_ROLE = "ROLE_EMPLOYEE";
    public static final String USER_ROLE = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean active;

    @Column(unique=true, nullable = false)
    private String username;
    private String password;

    @Column(unique=true, nullable = false)
    private int userId;

    @OneToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    User user = new User();

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
            this.user = new User(accountDTO.getUser());
            this.userId = accountDTO.getUser().getId();
        }
    }

    public UserAccount(){}
}

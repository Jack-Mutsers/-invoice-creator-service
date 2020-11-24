package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.UserAccountForAlterationDTO;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserAccount {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String OWNER = "ROLE_OWNER";
    public static final String EMPLOYEE = "ROLE_EMPLOYEE";
    public static final String USER = "ROLE_USER";

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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContactCode(){
        return this.contactCode;
    }

    public void setContactCode(String contactCode){
        this.contactCode = contactCode;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

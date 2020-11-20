package com.example.invoicecreatorservice.models;

import com.example.invoicecreatorservice.data_transfer_objects.UserAccountForAlterationDTO;

import javax.persistence.*;

@SuppressWarnings("WeakerAccess")
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class UserAccount {
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

    public UserAccount(int id, boolean active, String username, String password, int userId, String contactCode, int companyId, String role) {
        this.id = id;
        this.active = active;
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.contactCode = contactCode;
        this.companyId = companyId;
        this.role = role;
    }

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

    public UserAccount(){

    }

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

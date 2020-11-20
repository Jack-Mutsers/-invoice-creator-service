package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.models.UserAccount;

import java.util.UUID;

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

    public UserAccountDTO(){

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO userDTO) {
        this.user = userDTO;
    }

    public String getContactCode(){
        return this.contactCode;
    }

    public void setContactCode(String contactCode){
        this.contactCode = contactCode;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

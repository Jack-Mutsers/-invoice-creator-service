package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.models.UserAccount;

public class UserAccountDTO {
    private int id;
    private String username;
    private UserDTO userDTO;
    private String contactCode;
    private int companyId;
    private CompanyDTO company;

    public UserAccountDTO(int id, String username, UserDTO userDTO, String contactCode, int companyId) {
        this.id = id;
        this.username = username;
        this.userDTO = userDTO;
        this.contactCode = contactCode;
        this.companyId = companyId;
    }

    public UserAccountDTO(UserAccount userAccount) {
        this.id = userAccount.getId();
        this.username = userAccount.getUsername();
        this.contactCode = userAccount.getContactCode();
        this.companyId = userAccount.getCompanyId();
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

    public UserDTO getUser() {
        return userDTO;
    }

    public void setUser(UserDTO userDTO) {
        this.userDTO = userDTO;
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

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }
}

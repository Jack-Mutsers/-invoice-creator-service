package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.tools.ContactGenerator;

public class UserAccountForAlterationDTO {
    private int id;
    private String username;
    private String password;
    private UserForAlterationDTO user;
    private String contactCode;
    private int companyId;

    public UserAccountForAlterationDTO(int id, String username, String password, UserForAlterationDTO user, String contactCode, int companyId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user = user;
        this.contactCode = contactCode;
        this.companyId = companyId;
    }

    public UserAccountForAlterationDTO(){

    }

    public boolean validateForUpdate(){
        return ( this.id == 0 ||  this.validateForCreation() || this.contactCode.isEmpty() );
    }

    public boolean validateForCreation(){
        return ( this.validateLoginData() || this.user == null );
    }

    public boolean validateLoginData(){
        return ( this.username == null || this.password == null ||
                this.username.isBlank() || this.password.isBlank());
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserForAlterationDTO getUser() {
        return user;
    }

    public void setUser(UserForAlterationDTO user) {
        this.user = user;
    }

    public String getContactCode(){
        return this.contactCode;
    }

    public void generateContactCode(){
        ContactGenerator generator = new ContactGenerator();
        this.contactCode = generator.generateCode();
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}

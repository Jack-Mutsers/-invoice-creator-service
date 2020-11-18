package com.example.invoicecreatorservice.data_transfer_objects;

import com.example.invoicecreatorservice.tools.ContactGenerator;

public class UserAccountForAlterationDTO {
    private int id;
    private boolean active;
    private String username;
    private String password;
    private UserForAlterationDTO user;
    private String contactCode;
    private int companyId;
    private String role;

    public UserAccountForAlterationDTO(int id, boolean active, String username, String password, UserForAlterationDTO user, String contactCode, int companyId, String role) {
        this.id = id;
        this.active = active;
        this.username = username;
        this.password = password;
        this.user = user;
        this.contactCode = contactCode;
        this.companyId = companyId;
        this.role = role;
    }

    public UserAccountForAlterationDTO(){

    }

    public boolean validateForUpdate(){
        return ( this.id == 0 || this.validateForCreation() || this.contactCode.isBlank() || this.role.isBlank() );
    }

    public boolean validateForCreation(){
        return ( this.validateLoginData() || this.user == null );
    }

    public boolean validateLoginData(){
        return ( this.username == null || this.password == null ||
                this.username.isBlank() || this.password.isBlank());
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean getActive() {
        return active;
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

    public String getRole() {
        return role;
    }
}

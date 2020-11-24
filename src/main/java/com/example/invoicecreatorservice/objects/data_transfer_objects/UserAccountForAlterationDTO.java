package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.helpers.tools.ContactGenerator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserAccountForAlterationDTO {
    private int id;
    private boolean active;
    private String username;
    private String password;
    private UserForAlterationDTO user;
    private String contactCode;
    private int companyId;
    private String role;

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

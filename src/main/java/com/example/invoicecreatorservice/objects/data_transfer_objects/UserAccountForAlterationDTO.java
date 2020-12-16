package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.helpers.tools.ContactGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.invoicecreatorservice.objects.models.UserAccount.USER_ROLE;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserAccountForAlterationDTO {
    private int id;
    private Boolean active = true;
    private String username;
    private String password;
    private UserForAlterationDTO user;
    private String contactCode;
    private String role = USER_ROLE;
    private int companyId;

    public boolean validateForUpdate(){
        return ( this.id == 0 || this.validateValue(this.username) || this.user == null || this.user.validateForUpdate() || this.validateValue(this.contactCode) || this.validateValue(this.role) );
    }

    public boolean validateForCreation(){
        return ( this.validateLoginData() || this.user == null || this.user.validateForCreation() );
    }

    public boolean validateLoginData(){
        return ( this.validateValue(this.username) || this.validateValue(this.password));
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(UserForAlterationDTO user) {
        this.user = user;
    }

    public void generateContactCode(){
        ContactGenerator generator = new ContactGenerator();
        this.contactCode = generator.generateCode();
    }

    private boolean validateValue(String value){
        return value == null || value.isBlank();
    }

}

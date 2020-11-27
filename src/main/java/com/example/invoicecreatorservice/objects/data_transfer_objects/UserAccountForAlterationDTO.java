package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.helpers.tools.ContactGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.invoicecreatorservice.objects.models.UserAccount.USER;

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
    private int companyId;
    private String role = USER;

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

    public void setUser(UserForAlterationDTO user) {
        this.user = user;
    }

    public void generateContactCode(){
        ContactGenerator generator = new ContactGenerator();
        this.contactCode = generator.generateCode();
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

}

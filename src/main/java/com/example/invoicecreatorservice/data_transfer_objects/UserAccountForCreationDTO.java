package com.example.invoicecreatorservice.data_transfer_objects;

public class UserAccountForCreationDTO {
    private String username;
    private String password;
    private UserForCreationDTO user;

    public UserAccountForCreationDTO(String username, String password, UserForCreationDTO user) {
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public UserAccountForCreationDTO(){

    }

    public boolean validateUserAccount(){
        return ( this.username == null || this.password == null || this.user == null );
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

    public UserForCreationDTO getUser() {
        return user;
    }

    public void setUser(UserForCreationDTO user) {
        this.user = user;
    }
}

package com.example.invoicecreatorservice.data_transfer_objects;

public class UserAccountForAlterationDTO {
    private int id;
    private String username;
    private String password;
    private UserForAlterationDTO user;

    public UserAccountForAlterationDTO(int id, String username, String password, UserForAlterationDTO user) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user = user;
    }

    public UserAccountForAlterationDTO(){

    }

    public boolean validateForUpdate(){
        return ( this.id == 0 ||  this.username == null ||  this.password == null ||  this.user == null );
    }

    public boolean validateForCreation(){
        return ( this.username == null || this.password == null || this.user == null );
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public UserForAlterationDTO getUser() {
        return user;
    }

    public void setUser(UserForAlterationDTO user) {
        this.user = user;
    }
}

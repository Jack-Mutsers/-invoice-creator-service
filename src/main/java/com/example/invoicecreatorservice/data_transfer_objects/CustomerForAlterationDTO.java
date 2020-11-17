package com.example.invoicecreatorservice.data_transfer_objects;

public class CustomerForAlterationDTO {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;

    public CustomerForAlterationDTO(int id, String name, String address, String zipcode, String city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    public CustomerForAlterationDTO(){

    }

    public boolean validateForUpdate(){
        return ( this.id == 0 || this.validateForCreation() );
    }

    public boolean validateForCreation(){
        return (this.name == null || this.address == null || this.zipcode == null || this.city == null ||
                this.name.isBlank() || this.address.isBlank() || this.zipcode.isBlank() || this.city.isBlank());
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

}

package api.service.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
@XmlRootElement
public class User {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private String po_box;

    public User(int id, String name, String address, String zipcode, String city, String po_box) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.po_box = po_box;
    }

    public User(){

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPo_box() {
        return po_box;
    }

    public void setPo_box(String po_box) {
        this.po_box = po_box;
    }

    public String getMailAddress(){
        if(this.address.equals(null)){
            if(this.po_box.equals(null)) {
                return "";
            }
            return this.po_box;
        }

        return this.address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name +
                ", address=" + address +
                ", zipcode=" + zipcode +
                ", city=" + city +
                ", po_box=" + po_box +
                '}';
    }
}

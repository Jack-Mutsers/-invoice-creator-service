package api.service.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
@XmlRootElement
public class UserAccount {
    private int id;
    private String username;
    private String password;
    private int userId;

    public UserAccount(int id, String username, String password, int userId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public UserAccount(){

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



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount user = (UserAccount) o;
        return this.id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", username='" + username +
                ", password=" + password +
                ", userId=" + userId +
                '}';
    }
}

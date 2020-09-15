package api.service.repository;

import api.service.model.Customer;
import api.service.model.User;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRepo {
    private final List<User> userList = new ArrayList<>();

    public UserRepo(){
        userList.add(new User(1, "Jhon Doe", "kerkstraat", "5926 DF", "Asten", ""));
        userList.add(new User(2, "Arnold Schwarzenegger", "titanusstraat", "6943 RC", "Geldrop", ""));
        userList.add(new User(3, "Tommy Blinder", "zonnenbloemlaan", "3496 PL", "heeze", ""));
        userList.add(new User(4, "jhon snow", "st.martinlaan", "8512 BM", "Eindhoven", ""));
    }

    public List<User> getUsers() {
        return userList;
    }

    public List<User> getUsersByCity(String city) {
        List<User> filtered = new ArrayList<>();
        for (User user : userList) {
            if (user.getCity().equals(city)) {
                filtered.add(user);
            }
        }
        return filtered;
    }

    public User getUser(int id) {
        for (User user : userList) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public boolean delete(int id) {
        User user = getUser(id);
        if (user == null){
            return false;
        }

        return userList.remove(user);
    }

    public boolean add(User user) {
        if(user.getId() == 0){
            int oldId = 0;
            for (User user1 : userList){
                int newId = user1.getId();
                if(oldId < newId){
                    oldId = newId;
                }
            }

            user = new User(
                    (oldId+1),
                    user.getName(),
                    user.getAddress(),
                    user.getZipcode(),
                    user.getCity(),
                    user.getPo_box()
            );
        }

        if (this.getUser(user.getId()) != null){
            return false;
        }
        userList.add(user);
        return true;
    }

    public boolean update(int id, User user) {
        User old = this.getUser(id);
        if (old == null) {
            return false;
        }

        old.setName(user.getName());
        old.setAddress(user.getAddress());
        old.setZipcode(user.getZipcode());
        old.setCity(user.getCity());

        return true;
    }
}

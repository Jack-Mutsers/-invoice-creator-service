package api.service.repository;

import api.service.datatransferobjects.UserAccountDTO;
import api.service.model.User;
import api.service.model.UserAccount;

import javax.ws.rs.core.Response;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserAccountRepo {
    private final List<UserAccount> userList = new ArrayList<>();

    public UserAccountRepo(){
        userList.add(new UserAccount(1, "jdoe", "Password1!", 1));
        userList.add(new UserAccount(2, "aschwarzenegger", "Password2!", 2));
        userList.add(new UserAccount(3, "tblinder", "Password3!", 3));
        userList.add(new UserAccount(4, "jsnow", "Password4!", 4));
    }

    public boolean CheckForUsername(String username) {
        List<UserAccount> filtered = new ArrayList<>();
        for (UserAccount user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public UserAccount getUserAccount(int id) {
        for (UserAccount user : userList) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    public UserAccountDTO getByLogin(String username, String password) {
        for (UserAccount userAccount : userList) {
            if(userAccount.getPassword().equals(password) && userAccount.getUsername().equals(username)){
                return new UserAccountDTO(userAccount);
            }
        }
        return null;
    }

    public boolean delete(int id) {
        UserAccount user = getUserAccount(id);
        if (user == null){
            return false;
        }

        return userList.remove(user);
    }

    public boolean add(UserAccount user) {

        if(this.CheckForUsername(user.getUsername())){
            return false;
        }

        int oldId = 0;
        for (UserAccount user1 : userList){
            int newId = user1.getId();
            if(oldId < newId){
                oldId = newId;
            }
        }

        user.setId((oldId+1));

        userList.add(user);
        return true;
    }

    public boolean update(int id, UserAccount user) {
        UserAccount old = this.getUserAccount(id);
        if (old == null) {
            return false;
        }

        old.setUsername(user.getUsername());
        old.setPassword(user.getPassword());
        old.setUserId(user.getUserId());

        return true;
    }
}

package api.service.repository;

import api.service.datatransferobjects.UserAccountDTO;
import api.service.PasswordEncoder;
import api.service.model.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class UserAccountRepo {
    private final List<UserAccount> userList = new ArrayList<>();

    public UserAccountRepo(){
        // userList.add(new UserAccount(1, "jdoe", "Password1!", 1));
        userList.add(new UserAccount(1, "jdoe", "6kBvRHT01OG/kgvbvj7MUEsKENEOadx14q5qClXG/4o=$g3UoCDZkQfWbJykMaqgAYk8YT0n7i+Gu7gnGfJr6nq4=", 1));
        // userList.add(new UserAccount(2, "aschwarzenegger", "Password2!", 2));
        userList.add(new UserAccount(2, "aschwarzenegger", "mKPjMRe8rnvZhZAuOn9YOBzN2177L7Ry0JjnYXRtkXM=$MqxIqtD+q/T1ljfvaWC44myA7ARlcpG3HN5HwIYX8HQ=", 2));
        // userList.add(new UserAccount(3, "tblinder", "Password3!", 3));
        userList.add(new UserAccount(3, "tblinder", "ccP78twIMGmc5dU732AJKJgn/73eTjeKenTkvFCMRT0=$bUqUWy+gYld91m/lzW6eHlR9aR2htlHhFq9jsC4Ob2c=", 3));
        // userList.add(new UserAccount(4, "jsnow", "Password4!", 4));
        userList.add(new UserAccount(4, "jsnow", "r/dvJpjUk3mU1V9wvzNgolSxtQAVGy+Oa2sjVoyGG7U=$OWrgbzHzMd6h5gQm0XmcFbwlvl6n4Qu8gY+FH9d3NNg=", 4));
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

    public UserAccountDTO getByLogin(String username, String password) throws Exception {
        for (UserAccount userAccount : userList) {
            if(/*userAccount.getPassword().equals(password) &&*/ userAccount.getUsername().equals(username)){
                boolean validPassword = PasswordEncoder.check(password, userAccount.getPassword());
                if(validPassword){
                    return new UserAccountDTO(userAccount);
                }
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

    public boolean add(UserAccount user) throws Exception {

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

        String newPassword = PasswordEncoder.getSaltedHash(user.getPassword());

        user.setPassword(newPassword);

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

package api.service.datatransferobjects;

import api.service.model.User;
import api.service.model.UserAccount;
import api.service.repository.UserRepo;

public class UserAccountDTO {
    private int id;
    private String username;
    private User user;

    public UserAccountDTO(int id, String username, int userId) {
        UserRepo userRepo = new UserRepo();

        this.id = id;
        this.username = username;
        this.user = userRepo.getUser(userId);
    }

    public UserAccountDTO(UserAccount userAccount) {
        UserRepo userRepo = new UserRepo();

        this.id = userAccount.getId();
        this.username = userAccount.getUsername();
        this.user = userRepo.getUser(userAccount.getUserId());
    }

    public UserAccountDTO(){

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

    public User getUser() {
        return user;
    }

    public void setUser(int userId) {
        UserRepo userRepo = new UserRepo();
        this.user = userRepo.getUser(userId);
    }

}

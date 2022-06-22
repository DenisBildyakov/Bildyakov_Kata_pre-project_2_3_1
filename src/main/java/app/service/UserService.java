package app.service;

import app.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> findAllUsers();

    public void removeUserById(long id);

    public User findUserById(int id);

    public void updateUser(long id, User user);
}

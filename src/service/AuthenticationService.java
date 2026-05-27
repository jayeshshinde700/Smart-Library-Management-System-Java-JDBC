package service;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import model.User;

public class AuthenticationService {

    private UserDAO userDAO =
            new UserDAOImpl();

    public boolean register(User user) {
        return userDAO.registerUser(user);
    }

    public User login(String email, String password) {
        return userDAO.login(email, password);
    }
}
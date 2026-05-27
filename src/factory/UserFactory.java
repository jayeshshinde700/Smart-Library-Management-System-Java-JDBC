package factory;

import model.User;

public class UserFactory {

    public static User createUser(
            String name,
            String email,
            String password,
            String role) {

        return new User(
                name,
                email,
                password,
                role
        );
    }
}
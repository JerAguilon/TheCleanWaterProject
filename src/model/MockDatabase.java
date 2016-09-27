package model;

import java.util.*;

/**
 * Created by jeremy on 9/21/16.
 */
public class MockDatabase implements IDatabase {
    Map<String, User> database = new HashMap<>();

    public static MockDatabase mockDatabase = new MockDatabase();

    private MockDatabase(){

        Profile profile = new Profile("Test@test.com", "1234", "Sr.");
        User user = new User("user", "pass".hashCode(), AuthorizationLevel.ADMINISTRATOR, profile);
        database.put("user", user);
    }

    @Override
    public User getUser(String name) {
        return database.get(name);
    }

    @Override
    public boolean addUser(String username, int passHash, AuthorizationLevel auth, Profile profile) {

        User newUser =  new User(username, passHash, auth, profile);

        if (getUser(username) == null) {
            return false;
        }

        database.put(username, newUser);

        return true;
    }
}

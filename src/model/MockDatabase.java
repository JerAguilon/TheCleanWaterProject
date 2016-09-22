package model;

import java.util.*;

/**
 * Created by jeremy on 9/21/16.
 */
public class MockDatabase implements IDatabase {
    Map<String, String> userMap = new HashMap<String, String>();

    public static MockDatabase mockDatabase = new MockDatabase();

    private MockDatabase(){}

    @Override
    public boolean checkIfExists(String name) {
        return userMap.containsKey(name);
    }

    @Override
    public void addUser(String name, String password) {
        if (checkIfExists(name)) {
            throw new IllegalStateException("Can't add user that already exists");
        }

        userMap.put(name, password);
    }

    @Override
    public boolean checkPassword(String name, String password) {
        return password.equals(userMap.get(name));
    }
}

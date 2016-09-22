package model;

import java.util.*;

/**
 * Created by jeremy on 9/21/16.
 */
public class MockDatabase implements IDatabase {
    Map<String, String> userMap = new HashMap<String, String>();

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
}

package model;

/**
 * Created by jeremy on 9/21/16.
 */
public interface IDatabase {
    User getUser(String name);
    boolean addUser(String username, int passHash, AuthorizationLevel auth, Profile profile);
}

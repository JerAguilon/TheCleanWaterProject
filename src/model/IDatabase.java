package model;

/**
 * Created by jeremy on 9/21/16.
 */
public interface IDatabase {
    boolean checkIfExists(String name);
    void addUser(String name, String password);
    boolean checkPassword(String name, String password);
}

package model;
import java.security.InvalidParameterException;

/*
 * Created by jeremy on 9/21/16.
 */
public enum AuthorizationLevel {
    USER("User"), WORKER("Worker"), MANAGER("Manager"), ADMINISTRATOR("Administrator");

    private final String name;

    /**
     * Constructor method
     * @param name the name as a String
     */
    AuthorizationLevel(String name) {
        this.name = name;
    }

    /**
     * toString method for the AuthorizationLevel of the user
     * @return the name as a String
     */
    public String toString() {
        return this.name;
    }

    /**
     * Matches the String typed in to an appropriate enum value
     * @param value the String value entered by the user
     * @return the AuthorizationLevel of the user
     */
    public static AuthorizationLevel match(String value) {
        if (value.equals("User")) {
            return USER;
        }

        if (value.equals("Worker")) {
            return WORKER;
        }

        if (value.equals("Manager")) {
            return MANAGER;
        }

        if (value.equals("Administrator")) {
            return ADMINISTRATOR;
        }

        throw new InvalidParameterException("value does not exist in this enum");
    }
}

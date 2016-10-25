package model;
import java.security.InvalidParameterException;

/**
 * Created by jeremy on 9/21/16.
 */
public enum AuthorizationLevel {
    USER("User"), WORKER("Worker"), MANAGER("Manager"), ADMINISTRATOR("Administrator");

    private final String name;

    AuthorizationLevel(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

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

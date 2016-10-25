package database.responses;

/**
 * Created by jeremy on 10/23/16.
 */
public class DatabaseException extends Exception {
    private String message = "Default message";

    public DatabaseException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return message;
    }

}

package exceptions;

/*
 * Created by jeremy on 10/17/16.
 */
public class UserException extends Exception {
    private final String message;


    public UserException() {
        this.message = "There was an error with user";
    }

    public UserException(String message) {

        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

    @Override
    public String getMessage() {
        return this.toString();
    }
}



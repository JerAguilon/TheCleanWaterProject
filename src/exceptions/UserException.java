package exceptions;

/**
 * Created by jeremy on 10/17/16.
 */
public class UserException extends Exception {
    String message;
    UserExceptionType type;


    public UserException() {
        this.message = "There was an error with user";
    }

    public UserException(String message) {
        this.type = UserExceptionType.DEFAULT;

        this.message = message;
    }

    public UserException(String message, UserExceptionType type) {
        this.message = message;
        this.type = type;
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



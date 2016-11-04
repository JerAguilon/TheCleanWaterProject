package controller.interfaces;



/**
 * Created by jeremy on 9/21/16.
 */
public interface ILoginController {
    boolean isLockedOut();
    void validateLogin();
}
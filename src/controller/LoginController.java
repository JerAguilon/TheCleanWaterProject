package controller;

/**
 * Created by jeremy on 9/21/16.
 */
public class LoginController implements ILoginController {
    @Override
    public boolean isLockedOut() {
        return false;
    }

    @Override
    public boolean validate() {
        return false;
    }
}

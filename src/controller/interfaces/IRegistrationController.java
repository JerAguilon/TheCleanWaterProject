package controller.interfaces;

/**
 * Created by jeremy on 9/21/16.
 */
public interface IRegistrationController {
    void register();

    void register(String username, String password);

    void handleCancelPressed();
}

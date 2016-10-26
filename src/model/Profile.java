package model;

/**
 * Created by jeremy on 9/27/16.
 */
public class Profile {
    public final String EMAIL;
    public final String HOME_ADDRESS;
    public final String TITLE;

    /**
     * Constructor class for the profile of a user
     * @param email the email address of the user
     * @param homeAddress the home address of the user
     * @param title the title of the user
     */
    public Profile(String email, String homeAddress, String title) {
        this.EMAIL = email;
        this.HOME_ADDRESS = homeAddress;
        this.TITLE = title;
    }

}

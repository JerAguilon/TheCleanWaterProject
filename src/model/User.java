package model;

import exceptions.UserException;

/*
 * Created by jeremy on 9/27/16.
 */
public class User {
    private static int idCount = 0;

    private final int ID;

    public final String USERNAME;
    public final String PASSWORD;
    public final AuthorizationLevel AUTH;

    public Profile PROFILE;

    /**
     * Test case
     */
    public User() {
        this.ID = -1;
        this.USERNAME = "testadmin";
        this.PASSWORD = "test";
        this.AUTH = AuthorizationLevel.ADMINISTRATOR;
        this.PROFILE = new Profile("test", "test", "test");
    }

    /**
     * Constructor class for the User
     * @param username the username of the user as a string
     * @param password the password of the user as a string
     * @param auth the authorizationLevel of the user as an enum
     * @param profile the users profile
     * @throws UserException if there are empty fields
     */
    public User(String username, String password, AuthorizationLevel auth, Profile profile) throws UserException {
        if (username == null || password == null || auth == null || profile == null) {

            throw new UserException("Please complete all fields before registration");
        }

        if (username.isEmpty() || password.isEmpty() || profile.HOME_ADDRESS.isEmpty()
                || profile.TITLE.isEmpty() || profile.EMAIL.isEmpty()) {
            throw new UserException("Please complete all fields before registration");
        }

        this.ID = idCount++;

        this.USERNAME = username;
        this.PASSWORD = password;
        this.AUTH = auth;
        this.PROFILE = profile;
    }

    /**
     * Set method for the profile of the user
     * @param newProfile the Profile that we want to set
     */
    public void setProfile(Profile newProfile) {
        this.PROFILE = newProfile;
    }

// --Commented out by Inspection START (11/10/2016 7:49 PM):
//    /**
//     * Set method for the authorization level of the user
//     */
//    public void setAuthorizationLevel() {
//        //this.AUTH = auth;
//    }
// --Commented out by Inspection STOP (11/10/2016 7:49 PM)

    public AuthorizationLevel getAuthorizationLevel() {
        return AUTH;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof User)) {
            return false;
        }

        User that = (User) other;

        return that.USERNAME.equals(this.USERNAME);
    }

    @Override
    public int hashCode() {
        return this.USERNAME.hashCode();
    }




}

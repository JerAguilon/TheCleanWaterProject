package model;

import exceptions.UserException;
import exceptions.UserExceptionType;

/**
 * Created by jeremy on 9/27/16.
 */
public class User {
    public static int idCount = 0;

    public final int ID;

    public final String USERNAME;
    public final String PASSWORD;
    public final AuthorizationLevel AUTH;

    public Profile PROFILE;

    public User(String username, String password, AuthorizationLevel auth, Profile profile) throws UserException {
        if (username == null || password == null || auth == null || profile == null) {

            throw new UserException("Please complete all fields before registration");
        }

        if (username.isEmpty() || password.isEmpty() || profile.HOME_ADDRESS.isEmpty()
                || profile.TITLE.isEmpty() || profile.EMAIL.isEmpty()) {
            throw new UserException("Please complete all fields before registration", UserExceptionType.INVALIDFIELDS);
        }

        this.ID = idCount++;

        this.USERNAME = username;
        this.PASSWORD = password;
        this.AUTH = auth;
        this.PROFILE = profile;
    }

    public void setProfile(Profile newProfile) {
        this.PROFILE = newProfile;
    }

    public void setAuthorizationLevel(AuthorizationLevel auth) {
        //this.AUTH = auth;
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

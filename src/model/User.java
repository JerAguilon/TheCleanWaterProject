package model;

/**
 * Created by jeremy on 9/27/16.
 */
public class User {
    public static int idCount = 0;

    public final int ID;

    public final String USERNAME;
    public final int PASS_HASH;

    public final AuthorizationLevel AUTH;

    public Profile PROFILE;

    public User(String username, int passHash, AuthorizationLevel auth, Profile profile) {
        this.ID = idCount;
        this.PASS_HASH = passHash;
        this.AUTH = auth;
        this.PROFILE = profile;
        this.USERNAME = username;

        idCount++;
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

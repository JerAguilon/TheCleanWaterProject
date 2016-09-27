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

    public final Profile PROFILE;

    public User(String username, int passHash, AuthorizationLevel auth, Profile profile) {
        this.ID = idCount;
        this.PASS_HASH = passHash;
        this.AUTH = auth;
        this.PROFILE = profile;
        this.USERNAME = username;

        idCount++;
    }


}

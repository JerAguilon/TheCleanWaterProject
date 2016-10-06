package tests.model;

import model.AuthorizationLevel;
import model.MockDatabase;
import model.Profile;
import model.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jeremy on 9/27/16.
 */
public class MockDatabaseTest {

    @Test
    public void getUser_WhenAUserDoesntExistNullIsReturned() {
        assertEquals(null, MockDatabase.mockDatabase.getUser("foobarwijef"));
    }

    @Test
    public void getUser_WhenAUserExistsTheUserObjectIsReturned() {
        Profile profile = new Profile("Test@test.com", "1234", "Sr.");
        User user = new User("user", "pass".hashCode(), AuthorizationLevel.ADMINISTRATOR, profile);
        MockDatabase.mockDatabase.addUser(user.USERNAME, user.PASS_HASH, user.AUTH, user.PROFILE);

        assertEquals(user, MockDatabase.mockDatabase.getUser("user"));
    }

    @Test
    public void addUser_WhenAUserExistsThenFalseIsReturnedAndDatabaseDoesntChange() {
    }

    @Test
    public void addUser_WhenAUserDoesntExistThenTrueIsReturned() {}
}
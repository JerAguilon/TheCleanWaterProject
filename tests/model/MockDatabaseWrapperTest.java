package model;

import database.mock.MockDatabaseWrapper;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jeremy on 9/27/16.
 */
public class MockDatabaseWrapperTest {

    @Test
    public void getUser_WhenAUserDoesntExistNullIsReturned() {
        assertEquals(null, MockDatabaseWrapper.mockDatabase.getUser("foobarwijef"));
    }

    @Test
    public void getUser_WhenAUserExistsTheUserObjectIsReturned() {
        Profile profile = new Profile("Test@test.com", "1234", "Sr.");
        User user = new User("user", "pass".hashCode(), AuthorizationLevel.ADMINISTRATOR, profile);
        MockDatabaseWrapper.mockDatabase.addUser(user.USERNAME, user.PASS_HASH, user.AUTH, user.PROFILE);

        assertEquals(user, MockDatabaseWrapper.mockDatabase.getUser("user"));
    }

    @Test
    public void addUser_WhenAUserExistsThenFalseIsReturnedAndDatabaseDoesntChange() {
    }

    @Test
    public void addUser_WhenAUserDoesntExistThenTrueIsReturned() {}
}
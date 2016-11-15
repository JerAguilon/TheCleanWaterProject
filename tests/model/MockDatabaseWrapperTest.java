package model;

import database.mock.MockDatabaseWrapper;
import exceptions.UserException;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * Created by jeremy on 9/27/16.
 */
public class MockDatabaseWrapperTest {

    @Test
    public void getUser_WhenAUserDoesntExistNullIsReturned() {
        assertEquals(null, MockDatabaseWrapper.mockDatabase.getUser("foobarwijef"));
    }

    @Test
    public void getUser_WhenAUserExistsTheUserObjectIsReturned() throws UserException {
        Profile profile = new Profile("Test@test.com", "1234", "Sr.");
        User user = new User("user", "pass", AuthorizationLevel.ADMINISTRATOR, profile);
        MockDatabaseWrapper.mockDatabase.addUser(user);

        assertEquals(user, MockDatabaseWrapper.mockDatabase.getUser("user"));
    }

    /*@Test
    public void addUser_WhenAUserExistsThenFalseIsReturnedAndDatabaseDoesntChange() {
    }

    @Test
    public void addUser_WhenAUserDoesntExistThenTrueIsReturned() {}*/
}
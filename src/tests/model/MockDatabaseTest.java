package tests.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jeremy on 9/27/16.
 */
public class MockDatabaseTest {

    @Test
    public void getUser_WhenAUserDoesntExistNullIsReturned() {}

    @Test
    public void getUser_WhenAUserExistsTheUserObjectIsReturned() {}

    @Test
    public void addUser_WhenAUserExistsThenFalseIsReturnedAndDatabaseDoesntChange() {}

    @Test
    public void addUser_WhenAUserDoesntExistThenTrueIsReturned() {}
}
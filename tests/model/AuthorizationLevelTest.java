package model;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

/**
 * Created by AshimaGauba on 11/16/16.
 */
public class AuthorizationLevelTest {

    @Test
    public void testMatchUser() {
        assertEquals("Authorization Level must be USER", AuthorizationLevel.USER, AuthorizationLevel.match("User"));
    }

    @Test
    public void testMatchWorker() {
        assertEquals("Authorization Level must be WORKER", AuthorizationLevel.WORKER, AuthorizationLevel.match("Worker"));
    }

    @Test
    public void testMatchManager() {
        assertEquals("Authorization Level must be MANAGER", AuthorizationLevel.MANAGER,AuthorizationLevel.match("Manager"));
    }

    @Test
    public void testMatchAdministrator() {
        assertEquals("Authorization Level must be ADMINISTRATOR", AuthorizationLevel.ADMINISTRATOR, AuthorizationLevel.match("Administrator"));
    }

    @Test
    public void testMatchInvalid() {
        try {
            AuthorizationLevel.match("Hi, I'm invalid!");
        } catch (InvalidParameterException e) {
            assertTrue(e.getMessage().equals("value does not exist in this enum"));
        }
    }
}
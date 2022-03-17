package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the User class
 * 
 * @author Alex Hetrick
 */
@Tag("Model-tier")
public class UserTest {
    @Test
    public void testCtor() {
        // Setup
        int expected_id = 99;
        String expected_name = "Username";
        String expected_password = "Password";

        // Invoke
        User user = new User(expected_id, expected_name, expected_password);

        // Analyze
        assertEquals(expected_id, user.getId());
        assertEquals(expected_name, user.getUsername());
        assertEquals(expected_password, user.getPassword());
    }

    @Test
    public void testUsername() {
        // Setup
        int id = 99;
        String username = "TestUsername";
        String password = "TestPassword";
        User user = new User(id, username, password);

        String expected_name = "NewName";

        // Invoke
        user.setUsername(expected_name);

        // Analyze
        assertEquals(expected_name, user.getUsername());
    }

    public void testStock(){
        int id = 99;
        String username = "TestUsername";
        String password = "TestPassword";
        User user = new User(id, username, password);

        String expected_password = "NewPassword";

        // Invoke
        user.setPassword(expected_password);

        // Analyze
        assertEquals(expected_password, user.getPassword());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 99;
        String username = "TestUsername";
        String password = "TestPassword";
        String expected_string = String.format(User.STRING_FORMAT,id, username, password);
        User user = new User(id, username, password);

        // Invoke
        String actual_string = user.toString();

        // Analyze`
        assertEquals(expected_string,actual_string);
    }
}
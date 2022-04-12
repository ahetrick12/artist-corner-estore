package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Item class
 * 
 * @author Kara Kolodinsky of Team 8
 */
@Tag("Model-tier")
public class MessageTest {
    @Test
    public void testCtor() {
        // Setup
        int expected_id = 99;
        String expected_name = "admin";
        String expected_message = "there is no discount available.";
       

        // Invoke
        Message message = new Message(expected_id,expected_name,expected_message);

        // Analyze
        assertEquals(expected_id,message.getId());
        assertEquals(expected_name,message.getName());
        assertEquals(expected_message,message.getMessage());
    }

    @Test
    public void testSetName() {
        // Setup
        int id = 99;
        String name = "axm9912";
        String mess = "i refuse to pay full price";
        Message message = new Message(id,name,mess);

        String expected_name = "bbf7070";

        // Invoke
        message.setName(expected_name);

        // Analyze
        assertEquals(expected_name,message.getName());
    }
    @Test
    public void testSetMess(){
        int id = 99;
        String name = "admin";
        String mess = "then you no get tattoo";
        Message message = new Message(id,name,mess);

        String expected_mess = "or painting!!";

        // Invoke
        message.setMessage(expected_mess);

        // Analyze
        assertEquals(expected_mess,message.getMessage());
    }

   
}
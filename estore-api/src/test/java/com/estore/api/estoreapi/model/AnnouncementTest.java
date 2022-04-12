package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Announcement class
 * 
 * @author jonathan campbell of Team 8
 */
@Tag("Model-tier")
public class AnnouncementTest {
    @Test
    public void testCtor() {
        // Setup
        int expected_id = 99;
        String expected_title = "Announcement";
        String expected_message = "This is an announcement";
        boolean expected_edited = false;

        // Invoke
        Announcement announcement = new Announcement(expected_id,expected_title,expected_message,expected_edited);

        // Analyze
        assertEquals(expected_id,announcement.getId());
        assertEquals(expected_title,announcement.getTitle());
        assertEquals(expected_message,announcement.getMessage());
        assertEquals(expected_edited,announcement.getEdited());
    }

    @Test
    public void testSetTitle() {
        // Setup
        int id = 99;
        String title = "Announcement";
        String message = "This is an announcement";
        boolean edited = false;
        Announcement announcement = new Announcement(id,title,message,edited);

        String expected_title = "Announcement2";

        // Invoke
        announcement.setTitle(expected_title);

        // Analyze
        assertEquals(expected_title,announcement.getTitle());
    }
    @Test
    public void testSetMessage(){
        // Setup
        int id = 99;
        String title = "Announcement";
        String message = "This is an announcement";
        boolean edited = false;
        Announcement announcement = new Announcement(id,title,message,edited);

        String expected_message = "This is not an announcement";

        // Invoke
        announcement.setMessage(expected_message);

        // Analyze
        assertEquals(expected_message,announcement.getMessage());
    }

    @Test
    public void testSetEdited(){
        // Setup
        int id = 99;
        String title = "Announcement";
        String message = "This is an announcement";
        boolean edited = false;
        Announcement announcement = new Announcement(id,title,message,edited);

        boolean expected_edited = true;

        // Invoke
        announcement.setEdited(expected_edited);

        // Analyze
        assertEquals(expected_edited,announcement.getEdited());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 99;
        String title = "Announcement";
        String message = "This is an announcement";
        boolean edited = false;
        String expected_string = String.format(Announcement.STRING_FORMAT, title, message, edited);
        Announcement announcement = new Announcement(id,title,message,edited);

        // Invoke
        String actual_string = announcement.toString();

        // Analyze
        assertEquals(expected_string,actual_string);
    }
}
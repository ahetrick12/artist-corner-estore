package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estore.api.estoreapi.model.Announcement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the Announcement File DAO class
 * 
 * @author SWEN Faculty
 * @author team 8
 */
@Tag("Persistence-tier")
public class AnnouncementFileDAOTest {
    AnnouncementFileDAO announcementFileDAO;
    Announcement[] testAnnouncements;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupAnnouncementFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testAnnouncements = new Announcement[3];
        testAnnouncements[0] = new Announcement(0,"Announcement","This is an announcement",false);
        testAnnouncements[1] = new Announcement(1,"Announcement2","This is an announcement2",false);
        testAnnouncements[2] = new Announcement(2,"Announcement3","This is an announcement3",false);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the announcement array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Announcement[].class))
                .thenReturn(testAnnouncements);
        announcementFileDAO = new AnnouncementFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testGetAnnouncements() {
        // Invoke
        Announcement[] announcements = announcementFileDAO.getAnnouncements();

        // Analyze
        assertEquals(announcements.length,testAnnouncements.length);
        for (int i = 0; i < testAnnouncements.length;++i)
            assertEquals(announcements[i],testAnnouncements[i]);
    }

    @Test
    public void testGetAnnouncement() {
        //Invoke
        Announcement announcement = announcementFileDAO.getAnnouncement(0);

        // Analzye
        assertEquals(announcement,testAnnouncements[0]);
    }

    @Test
    public void testDeleteAnnouncement() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> announcementFileDAO.deleteAnnouncement(0),
                            "Unexpected exception thrown");
        
        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test announcements array - 1 (because of the delete)
        // Because announcements attribute of AnnouncementFileDAO is package private
        // we can access it directly
        assertEquals(announcementFileDAO.announcements.size(),testAnnouncements.length-1);
    }

    @Test
    public void testCreateAnnouncement() {
        // Setup
        Announcement announcement = new Announcement(3,"Announcement4","This is an announcement4",false);

        // Invoke
        Announcement result = assertDoesNotThrow(() -> announcementFileDAO.createAnnouncement(announcement),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Announcement actual = announcementFileDAO.getAnnouncement(announcement.getId());
        assertEquals(actual.getId(),announcement.getId());
        assertEquals(actual.getTitle(),announcement.getTitle());
        assertEquals(actual.getMessage(),announcement.getMessage());
        assertEquals(actual.getEdited(),announcement.getEdited());
    }

    @Test
    public void testUpdateAnnouncement() {
        // Setup
        Announcement announcement = new Announcement(0,"Announcement","This is maybe an announcement",false);

        // Invoke
        Announcement result = assertDoesNotThrow(() -> announcementFileDAO.updateAnnouncement(announcement),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Announcement actual = announcementFileDAO.getAnnouncement(announcement.getId());
        assertEquals(actual, announcement);
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(Announcement[].class));

        Announcement announcement = new Announcement(0,"Announcement","This is an announcement",false);

        assertThrows(IOException.class,
                        () -> announcementFileDAO.createAnnouncement(announcement),
                        "IOException not thrown");
    }


    @Test
    public void testGetAnnouncementNotFound() {
         // Invoke
         Announcement announcement = announcementFileDAO.getAnnouncement(10);

         // Analyze
         assertEquals(announcement,null);
    }

    @Test
    public void testDeleteAnnouncementNotFound() {
         // Invoke
         boolean result = assertDoesNotThrow(() -> announcementFileDAO.deleteAnnouncement(10),
         "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(announcementFileDAO.announcements.size(),testAnnouncements.length);
    }

    @Test
    public void testUpdateAnnouncementNotFound() {
        // Setup
        Announcement announcement = new Announcement(10,"Announcement","This is maybe an announcement",false);

        // Invoke
        Announcement result = assertDoesNotThrow(() -> announcementFileDAO.updateAnnouncement(announcement),
                                                "Unexpected exception thrown");

        // Analyze
        assertNull(result);
    }

    @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the AnnouncementFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),Announcement[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new AnnouncementFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}

package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.AnnouncementDAO;
import com.estore.api.estoreapi.model.Announcement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Announcement Controller class
 * 
 * @author jonathan campbell of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
@Tag("Controller-tier")
public class AnnouncementControllerTest {
    private AnnouncementController announcementController;
    private AnnouncementDAO mockAnnouncementDAO;

    /**
     * Before each test, create a new AnnouncementController object and inject
     * a mock Announcement DAO
     */
    @BeforeEach
    public void setupAnnouncementController() {
        mockAnnouncementDAO = mock(AnnouncementDAO.class);
        announcementController = new AnnouncementController(mockAnnouncementDAO);
    }

    @Test
    public void testGetAnnouncement() throws IOException {  // getAnnouncement may throw IOException
        //Setup
        Announcement announcement = new Announcement(0, "Announcement", "This is an announcement", false);
        // When the same id is passed in, our mock Announcement DAO will return the Announcement object
        when(mockAnnouncementDAO.getAnnouncement(announcement.getId())).thenReturn(announcement);
        //Invoke
        ResponseEntity<Announcement> response = announcementController.getAnnouncement(announcement.getId());
        //Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(announcement, response.getBody());
    }

    @Test
    public void testGetAnnouncementNotFound() throws Exception { // getAnnouncement may throw IOException
        // Setup
        int announcementId = 99;
        // When the same id is passed in, our mock Announcement DAO will return null, simulating
        // no announcement found
        when(mockAnnouncementDAO.getAnnouncement(announcementId)).thenReturn(null);
        // Invoke
        ResponseEntity<Announcement> response = announcementController.getAnnouncement(announcementId);
        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetAnnouncementHandleException() throws Exception { // getAnnouncement may throw IOException
        // Setup
        int announcementId = 99;
        // When getAnnouncement is called on the Mock Announcement DAO, throw an IOException
        doThrow(new IOException()).when(mockAnnouncementDAO).getAnnouncement(announcementId);
        // Invoke
        ResponseEntity<Announcement> response = announcementController.getAnnouncement(announcementId);
        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /************************************************************************
     * The following tests will fail until all AnnouncementController methods
     * are implemented.
     ************************************************************************/

    @Test
    public void testCreateAnnouncement() throws IOException {  // createAnnouncement may throw IOException
        // Setup
        Announcement announcement = new Announcement(0, "Announcement", "This is an announcement", false);
        // when createAnnouncement is called, return true simulating successful
        // creation and save
        when(mockAnnouncementDAO.createAnnouncement(announcement)).thenReturn(announcement);

        // Invoke
        ResponseEntity<Announcement> response = announcementController.createAnnouncement(announcement);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(announcement,response.getBody());
    }

    @Test
    public void testCreateAnnouncementFailed() throws IOException {  // createAnnouncement may throw IOException
        // Setup
        Announcement announcement = new Announcement(0, "Announcement", "This is an announcement", false);
        // when createAnnouncement is called, return false simulating failed
        // creation and save
        when(mockAnnouncementDAO.createAnnouncement(announcement)).thenReturn(null);

        // Invoke
        ResponseEntity<Announcement> response = announcementController.createAnnouncement(announcement);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void testCreateAnnouncementHandleException() throws IOException {  // createAnnouncement may throw IOException
        // Setup
        Announcement announcement = new Announcement(0, "Announcement", "This is an announcement", false);

        // When createAnnouncement is called on the Mock Announcement DAO, throw an IOException
        doThrow(new IOException()).when(mockAnnouncementDAO).createAnnouncement(announcement);

        // Invoke
        ResponseEntity<Announcement> response = announcementController.createAnnouncement(announcement);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testUpdateAnnouncement() throws IOException { 
        // Setup
        Announcement announcement = new Announcement(0, "Announcement", "This is an announcement", false);
        // when updateAnnouncement is called, return true simulating successful
        // update and save
        when(mockAnnouncementDAO.updateAnnouncement(announcement)).thenReturn(announcement);
        ResponseEntity<Announcement> response = announcementController.updateAnnouncement(announcement);
        announcement.setTitle("Announcement 2");

        // Invoke
        response = announcementController.updateAnnouncement(announcement);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(announcement,response.getBody());
    }

    @Test
    public void testUpdateAnnouncementFailed() throws IOException {
        // Setup
        Announcement announcement = new Announcement(0, "Announcement", "This is an announcement", false);
        // when updateAnnouncement is called, return true simulating successful
        // update and save
        when(mockAnnouncementDAO.updateAnnouncement(announcement)).thenReturn(null);

        // Invoke
        ResponseEntity<Announcement> response = announcementController.updateAnnouncement(announcement);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateAnnouncementHandleException() throws IOException { 
        // Setup
        Announcement announcement = new Announcement(0, "Announcement", "This is an announcement", false);
        // When updateAnnouncement is called on the Mock Announcement DAO, throw an IOException
        doThrow(new IOException()).when(mockAnnouncementDAO).updateAnnouncement(announcement);

        // Invoke
        ResponseEntity<Announcement> response = announcementController.updateAnnouncement(announcement);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetAnnouncements() throws IOException { 
        // Setup
        Announcement[] announcements = new Announcement[4];
        announcements[0] = new Announcement(0, "Announcement", "This is an announcement", false);
        announcements[1] = new Announcement(1, "Announcement 2", "This is an announcement 2", false);
        announcements[2] = new Announcement(3, "Announcement 3", "This is an announcement 3", false);
        announcements[3] = new Announcement(0, "Announcement 4", "This is an announcement 4", false);
        // When getAnnouncements is called return the announcements created above
        when(mockAnnouncementDAO.getAnnouncements()).thenReturn(announcements);
        // Invoke
        ResponseEntity<Announcement[]> response = announcementController.getAnnouncements();
        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(announcements,response.getBody());
    }

    @Test
    public void testGetAnnouncementsHandleException() throws IOException { 
        // Setup
        // When getAnnouncements is called on the Mock Announcement DAO, throw an IOException
        doThrow(new IOException()).when(mockAnnouncementDAO).getAnnouncements();
        // Invoke
        ResponseEntity<Announcement[]> response = announcementController.getAnnouncements();
        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    
    @Test
    public void testDeleteAnnouncement() throws IOException {
        // Setup
        int announcementId = 99;
        // when deleteAnnouncement is called return true, simulating successful deletion
        when(mockAnnouncementDAO.deleteAnnouncement(announcementId)).thenReturn(true);

        // Invoke
        ResponseEntity<Announcement> response = announcementController.deleteAnnouncement(announcementId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteAnnouncementNotFound() throws IOException { 
        // Setup
        int announcementId = 99;
        // when deleteAnnouncement is called return false, simulating failed deletion
        when(mockAnnouncementDAO.deleteAnnouncement(announcementId)).thenReturn(false);

        // Invoke
        ResponseEntity<Announcement> response = announcementController.deleteAnnouncement(announcementId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteAnnouncementHandleException() throws IOException { 
        // Setup
        int announcementId = 99;
        // When deleteAnnouncement is called on the Mock Announcement DAO, throw an IOException
        doThrow(new IOException()).when(mockAnnouncementDAO).deleteAnnouncement(announcementId);

        // Invoke
        ResponseEntity<Announcement> response = announcementController.deleteAnnouncement(announcementId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}


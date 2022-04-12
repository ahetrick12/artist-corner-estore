package com.estore.api.estoreapi.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.AnnouncementDAO;
import com.estore.api.estoreapi.model.Announcement;

/**
 * handles the rest api requests for the Announcement resource.
 * 
 * {@literal @}RestController spring annotation identifies this class as a rest api
 * method handler to the spring framework.
 * 
 * @author jonathan campbell of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */

@RestController
@RequestMapping("announcements")
public class AnnouncementController {
    private static final Logger LOG = Logger.getLogger(AnnouncementController.class.getName());
    private AnnouncementDAO AnnouncementDao;

    /**
     * creates a rest api controller to reponds to requests.
     * 
     * @param AnnouncementDao the {@link AnnouncementDAO announcement data access object} to perform CRUD operations
     */
    public AnnouncementController(AnnouncementDAO AnnouncementDao) {
        this.AnnouncementDao = AnnouncementDao;
    }

    @GetMapping("/{announcement}.name")
    public ResponseEntity<Announcement> getAnnouncement(@PathVariable int announcementId) {
        LOG.info("GET /announcement/" + announcementId);
        try {
            Announcement announcement = AnnouncementDao.getAnnouncement(announcementId);
            if (announcement != null)
                return new ResponseEntity<Announcement>(announcement, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Announcement announcements}
     * 
     * @return ResponseEntity with array of {@link Announcement announcement} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Announcement[]> getAnnouncements() {
        LOG.info("GET /announcement");
        try{
            Announcement[] announcements = AnnouncementDao.getAnnouncements();
            if (announcements != null)
                return new ResponseEntity<Announcement[]>(announcements, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * updates the {@linkplain Announcement announcement} with the provided {@linkplain Announcement announcement} object, if it exists.
     * 
     * @param announcement the {@link Announcement announcement} to update
     * 
     * @return ResponseEntity with updated {@link Announcement announcement} object and HTTP status of OK if updated
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Announcement> updateAnnouncement(@RequestBody Announcement announcement) {
        LOG.info("PUT /announcement " + announcement.toString());
        try {
            Announcement updatedAnnouncement = AnnouncementDao.updateAnnouncement(announcement);
            if (updatedAnnouncement != null)
                return new ResponseEntity<Announcement>(updatedAnnouncement, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a {@linkplain Announcement announcement} with the given announcement id
     * 
     * @param announcement The id of the {@link Announcement announcement} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{announcementId}")
    public ResponseEntity<Announcement> deleteAnnouncement(@PathVariable int announcementId) {
        LOG.info("DELETE /announcement/" + announcementId);
        try {
            if(AnnouncementDao.deleteAnnouncement(announcementId)) {
                return new ResponseEntity<Announcement>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     /**
     * creates a {@linkplain Announcement announcement} with the provided announcement object.
     * 
     * @param announcement - The {@link Announcement announcement} to create
     * 
     * @return ResponseEntity with created {@link Announcement announcement} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Announcement announcement} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        LOG.info("POST /announcement/ " + announcement);
        try {
            Announcement created_announcement = AnnouncementDao.createAnnouncement(announcement);
            if (created_announcement != null){
                return new ResponseEntity<Announcement>(created_announcement,HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<Announcement>(HttpStatus.CONFLICT);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}

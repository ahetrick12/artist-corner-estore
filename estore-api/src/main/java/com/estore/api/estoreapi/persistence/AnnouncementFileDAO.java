package com.estore.api.estoreapi.persistence;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Announcement;

/**
 * Implements the functionality for JSON file-based peristance for Announcements
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author jonathan campbell of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
@Component
public class AnnouncementFileDAO implements AnnouncementDAO {
    private static final Logger LOG = Logger.getLogger(AnnouncementFileDAO.class.getName());
    Map<Integer,Announcement> announcements;   // Provides a local cache of the announcement objects
    private ObjectMapper objectMapper;  // Provides conversion between Announcement
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new announcement
    private String filename;    // Filename to read from and write to

    /**
     * Creates an Announcement File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public AnnouncementFileDAO(@Value("${announcements.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the announcements from the file
    }

    /**
     * Generates the next id for a new {@linkplain Announcement announcement}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Announcement announcements} from the tree map
     * 
     * @return  The array of {@link Announcement announcements}, may be empty
     */
    private Announcement[] getAnnouncementsArray() {
        return getAnnouncementsArray(null);
    }

    /**
     * generates an array of {@linkplain Announcement announcements} from the tree map for any
     * {@linkplain Announcement announcements} that contains the text specified by containsText.
     * if containsText is null, the array contains all of the {@linkplain Announcement announcements}
     * in the tree map
     * 
     * @return the array of {@link Announcement announcements}, may be empty.
     */
    private Announcement[] getAnnouncementsArray(String containsText) { // if containsText == null, no filter
        ArrayList<Announcement> announcementArrayList = new ArrayList<>();

        for (Announcement announcement : announcements.values()) {
            if (containsText == null || announcement.getTitle().toLowerCase().contains(containsText.toLowerCase())) {
                announcementArrayList.add(announcement);
            }
        }

        Announcement[] announcementArray = new Announcement[announcementArrayList.size()];
        announcementArrayList.toArray(announcementArray);
        return announcementArray;
    }

    /**
     * saves the {@linkplain Announcement announcements} from the map into the file as an array of JSON objects.
     * 
     * @return true if the {@link Announcement announcements} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Announcement[] announcementArray = getAnnouncementsArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),announcementArray);
        return true;
    }

    /**
     * loads {@linkplain Announcement announcement} from the JSON file into the map;
     * also sets next id to one more than the greatest id found in the file.
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        announcements = new TreeMap<>();
        nextId = 0;

        // deserializes the JSON objects from the file into an array of announcements;
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file.
        Announcement[] announcementArray = objectMapper.readValue(new File(filename),Announcement[].class);

        // add each announcement to the tree map and keep track of the greatest id
        for (Announcement announcement : announcementArray) {
            announcements.put(announcement.getId(),announcement);
            if (announcement.getId() > nextId)
                nextId = announcement.getId();
        }
        // make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Announcement[] getAnnouncements() {
        synchronized(announcements) {
            return getAnnouncementsArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Announcement getAnnouncement(int id) {
        synchronized(announcements) {
            if (announcements.containsKey(id))
                return announcements.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Announcement createAnnouncement(Announcement announcement) throws IOException {
        synchronized(announcements) {
            // we create a new announcement object because the id field is immutable
            // and we need to assign the next unique id
            Announcement newAnnouncement = new Announcement(nextId(), announcement.getTitle(), announcement.getMessage(), announcement.getEdited());
            announcements.put(newAnnouncement.getId(),newAnnouncement);
            save(); // may throw an IOException
            return newAnnouncement;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Announcement updateAnnouncement(Announcement announcement) throws IOException {
        synchronized(announcements) {
            if (announcements.containsKey(announcement.getId()) == false)
                return null;  // announcement does not exist

            announcements.put(announcement.getId(),announcement);
            save(); // may throw an IOException
            return announcement;
        }
    }

   /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteAnnouncement(int id) throws IOException {
        synchronized(announcements) {
            if (announcements.containsKey(id)) {
                announcements.remove(id);
                return save();
            }
            else
                return false;
        }
    }
}
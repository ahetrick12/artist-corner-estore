package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Announcement;

/**
 * defines the interface for Announcement object persistence.
 * 
 * @author jonathan campbell of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
public interface AnnouncementDAO {
    /**
     * Retrieves all {@linkplain Announcement announcements}
     * 
     * @return An array of {@link Announcement announcement} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Announcement[] getAnnouncements() throws IOException;

    /**
     * retrieves an {@linkplain Announcement announcement} with the given id.
     * 
     * @param id The id of the {@link Announcement announcement} to get
     * 
     * @return an {@link Announcement announcement} object with the matching id
     * null if no {@link Announcement announcement} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Announcement getAnnouncement(int id) throws IOException;

    /**
     * creates and saves an {@linkplain Announcement announcement}.
     * 
     * @param announcement {@linkplain Announcement announcement} object to be created and saved
     * The id of the announcement object is ignored and a new unique id is assigned.
     *
     * @return new {@link Announcement announcement} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Announcement createAnnouncement(Announcement announcement) throws IOException;

    /**
     * updates and saves an {@linkplain Announcement announcement}.
     * 
     * @param {@link Announcment announcement} object to be updated and saved.
     * 
     * @return an updated {@link Announcement announcement} if successful, null if
     * {@link Announcement announcement} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Announcement updateAnnouncement(Announcement announcement) throws IOException;

    /**
     * deletes an {@linkplain Announcement announcement} with the given id.
     * 
     * @param id the id of the {@link Announcement announcement}
     * 
     * @return true if the {@link Announcement announcement} was deleted
     * false if announcement with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteAnnouncement(int id) throws IOException;
}
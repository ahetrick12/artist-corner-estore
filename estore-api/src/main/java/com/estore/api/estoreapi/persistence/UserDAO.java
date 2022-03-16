package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.User;

/**
 * Defines the interface for User object persistence.
 * 
 * @author Alex Hetrick
 */
public interface UserDAO {
    /**
     * Retrieves all {@linkplain User users}
     * 
     * @return An array of {@link User user} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    User[] getUsers() throws IOException;

    /**
     * Retrieves an {@linkplain User user} with the given id.
     * 
     * @param id The id of the {@link User user} to get
     * 
     * @return an {@link User user} object with the matching id
     * null if no {@link User user} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    User getUser(int id) throws IOException;

    /**
     * Finds the {@linkplain user user} with a given username.
     * 
     * @param username The username to match
     * 
     * @return A user {@link User user} with the given username; may be empty.
     * 
     * @throws IOException if an issue with underlying storage
     */
    User findUser(String username) throws IOException;

    /**
     * Creates and saves a {@linkplain User user}.
     * 
     * @param user {@linkplain User user} object to be created and saved
     *
     * @return new {@link User user} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    User createUser(User user) throws IOException;

    /**
     * Updates and saves a {@linkplain User user}.
     * 
     * @param {@link User user} object to be updated and saved.
     * 
     * @return an updated {@link User user} if successful, null if not
     * {@link User user} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    User updateUser(User user) throws IOException;

    /**
     * Deletes a {@linkplain User user} with the given id.
     * 
     * @param id the id of the {@link User user}
     * 
     * @return true if the {@link User user} was deleted
     * False if user with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteUser(int id) throws IOException;

    /**
     * Checks if an {@linkplain User user}'s username matches any of the {@linkplain User User} usernames in the current record.
     * 
     * @param username the username of the {@link User user}
     * 
     * @return true if the {@link User user}'s username already exists
     * false if it is not found.
     * 
     * @throws IOException if underlying storage cannot be accessed
     */

    boolean usernameExists(String username) throws IOException;
}

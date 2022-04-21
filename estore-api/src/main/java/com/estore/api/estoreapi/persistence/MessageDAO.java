package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Message;


/**
 * defines the interface for Message object persistence.
 * 
 * @author Alex Martinez of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
public interface MessageDAO {
    /**
     * Retrieves all {@linkplain Message messages}
     * 
     * @return An array of {@link Message message} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Message[] getMessages() throws IOException;

    /**
     * finds all {@linkplain Message message} whose name contains the given text.
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Message message} whose names contains the given text; may be empty.
     * 
     * @throws IOException if an issue with underlying storage
     */
    Message[] findMessages(String containsText) throws IOException;

    /**
     * retrieves an {@linkplain Message message} with the given id.
     * 
     * @param id The id of the {@link Message message} to get
     * 
     * @return an {@link Message message} object with the matching id
     * null if no {@link Message message} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Message getMessage(int id) throws IOException;

    /**
     * creates and saves an {@linkplain Message message}.
     * 
     * @param item {@linkplain Message message} object to be created and saved
     * The id of the item object is ignored and a new unique id is assigned.
     * Stock is set to whatever the item's current stock is.
     *
     * @return new {@link Message message} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Message createMessage(Message message) throws IOException;


    /**
     * deletes an {@linkplain Message message} with the given id.
     * 
     * @param id the id of the {@link Message message}
     * 
     * @return true if the {@link Message message} was deleted
     * false if item with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteMessage(int id) throws IOException;

}



package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.Item;

/**
 * defines the interface for Item object persistence.
 * 
 * @author kara kolodinsky of team 8
 */
public interface ItemDAO {
    /**
     * Retrieves all {@linkplain Item items}
     * 
     * @return An array of {@link Item item} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item[] getItems() throws IOException;

    /**
     * finds all {@linkplain Item item} whose name contains the given text.
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Item item} whose names contains the given text; may be empty.
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item[] findItems(String containsText) throws IOException;

    /**
     * retrieves an {@linkplain Item item} with the given id.
     * 
     * @param id The id of the {@link Item item} to get
     * 
     * @return an {@link Item item} object with the matching id
     * null if no {@link Item item} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item getItem(int id) throws IOException;

    /**
     * creates and saves an {@linkplain Item item}.
     * 
     * @param item {@linkplain Item item} object to be created and saved
     * The id of the item object is ignored and a new unique id is assigned.
     * Stock is set to whatever the item's current stock is.
     *
     * @return new {@link Item item} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item createItem(Item item) throws IOException;

    /**
     * updates and saves an {@linkplain Item item}.
     * 
     * @param {@link Item item} object to be updated and saved.
     * 
     * @return an updated {@link Item item} if successful, null if
     * {@link Item item} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Item updateItem(Item item) throws IOException;

    /**
     * deletes an {@linkplain Item item} with the given id.
     * 
     * @param id the id of the {@link Item item}
     * 
     * @return true if the {@link Item item} was deleted
     * false if item with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteItem(int id) throws IOException;
}

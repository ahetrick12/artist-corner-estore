package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.Item;

/**
 * defines the interface for CartItem object persistence.
 * 
 * @author kara kolodinsky of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
public interface CartDAO {
    /**
     * Retrieves all {@linkplain CartItem items}
     * 
     * @return An array of {@link Item item} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Item[] getCart() throws IOException;

    /**
     * finds all {@linkplain Item item} whose name contains the given text.
     * 
     * @param containsText The text to match against
     * 
     * @return An array of {@link Item item} whose names contains the given text; may be empty.
     * 
     * @throws IOException if an issue with underlying storage
     */
    public void addCartItem(Item item) throws IOException;

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
    public boolean deleteCartItem(Item item) throws IOException;
}
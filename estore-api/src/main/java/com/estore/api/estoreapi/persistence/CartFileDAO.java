package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Item;
import com.estore.api.estoreapi.model.CartItem;

/**
 * Implements the functionality for JSON file-based peristance for CartItem
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author kara kolodinsky of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
@Component
public class CartFileDAO implements CartDAO {
    private static final Logger LOG = Logger.getLogger(CartFileDAO.class.getName());
    Map<String,CartItem> cart;   // Provides a local cache of the item objects
    private ObjectMapper objectMapper;  // Provides conversion between CartItem
                                        // objects and JSON text format written
                                        // to the file
    private String filename;    // Filename to read from and write to

    /**
     * Creates an Cart File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public CartFileDAO(@Value("${cart.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the Cartitems from the file
    }

    /**
     * Generates an array of {@linkplain CartItem items} from the tree map
     * 
     * @return  The array of {@link CartItem items}, may be empty
     */
    private CartItem[] getCartItems() {
        return getCartItems(null);
    }

    /**
     * generates an array of {@linkplain CartItem items} from the tree map for any
     * {@linkplain CartItem items} that contains the text specified by containsText.
     * if containsText is null, the array contains all of the {@linkplain Item items}
     * in the tree map
     * 
     * @return the array of {@link CartItem items}, may be empty.
     */
    private CartItem[] getCartItems(String containsText) { // if containsText == null, no filter
        ArrayList<CartItem> Cart = new ArrayList<>();

        for (CartItem item : cart.values()) {
            if (containsText == null || item.getItem().getName().contains(containsText)) {
                Cart.add(item);
            }
        }

        CartItem[] itemArray = new CartItem[Cart.size()];
        Cart.toArray(itemArray);
        return itemArray;
    }

    /**
     * saves the {@linkplain CartItem items} from the map into the file as an array of JSON objects.
     * 
     * @return true if the {@link CartItem items} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        CartItem[] itemArray = getCartItems();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),itemArray);
        return true;
    }

    /**
     * loads {@linkplain CartItem item} from the JSON file into the map;
     * also sets next id to one more than the greatest id found in the file.
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        cart = new TreeMap<>();

        // deserializes the JSON objects from the file into an array of items;
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file.
        CartItem[] Cart = objectMapper.readValue(new File(filename),CartItem[].class);

        // add each item to the tree map and keep track of the greatest id
        for (CartItem item : Cart) {
            cart.put(item.getItem().getName(),item);
        }
        // make the next id one greater than the maximum from the file

        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public CartItem[] getCart() {
        synchronized(cart) {
            return getCartItems();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public CartItem addCartItem(Item item) throws IOException {
        synchronized(cart) {
                if (cart.get(item.getName()) != null){
                    cart.get(item.getName()).incrementQuantity();
                    return null;
                }
                else{
                    cart.put(item.getName(),new CartItem(item, 1));
                    save(); // may throw an IOException
                    return new CartItem(item, 1);
            }   
        }
    }
   /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteCartItem(String item) throws IOException {
        synchronized(cart) {
            if (cart.containsKey(item)) {
                cart.remove(item);
                return save();
            }
            else
                return false;
        }
    }
      /**
    ** {@inheritDoc}
     */
    @Override
    public CartItem updateItem(CartItem item) throws IOException {
        synchronized(cart) {
            if (cart.containsKey(item.getItem().getName()) == false)
                return null;  // item does not exist

            cart.put(item.getItem().getName(),item);
            save(); // may throw an IOException
            return item;
        }
    }

     /**
    ** {@inheritDoc}
     */
    @Override
    public CartItem getCartItem(Item item) {
        synchronized(cart) {
            if (cart.containsKey(item.getName()))
                return cart.get(item.getName());
            else
                return null;
        }
    }
}

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

import com.estore.api.estoreapi.model.Item;

/**
 * Implements the functionality for JSON file-based peristance for Items
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author kara kolodinsky of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
@Component
public class ItemFileDAO implements ItemDAO {
    private static final Logger LOG = Logger.getLogger(ItemFileDAO.class.getName());
    Map<Integer,Item> items;   // Provides a local cache of the item objects
    private ObjectMapper objectMapper;  // Provides conversion between Item
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new item
    private String filename;    // Filename to read from and write to

    /**
     * Creates an Item File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public ItemFileDAO(@Value("${estore.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the items from the file
    }

    /**
     * Generates the next id for a new {@linkplain Item item}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Item items} from the tree map
     * 
     * @return  The array of {@link Item items}, may be empty
     */
    private Item[] getItemsArray() {
        return getItemsArray(null);
    }

    /**
     * generates an array of {@linkplain Item items} from the tree map for any
     * {@linkplain Item items} that contains the text specified by containsText.
     * if containsText is null, the array contains all of the {@linkplain Item items}
     * in the tree map
     * 
     * @return the array of {@link Item items}, may be empty.
     */
    private Item[] getItemsArray(String containsText) { // if containsText == null, no filter
        ArrayList<Item> itemArrayList = new ArrayList<>();

        for (Item item : items.values()) {
            if (containsText == null || item.getName().toLowerCase().contains(containsText.toLowerCase())) {
                itemArrayList.add(item);
            }
        }

        Item[] itemArray = new Item[itemArrayList.size()];
        itemArrayList.toArray(itemArray);
        return itemArray;
    }

    /**
     * saves the {@linkplain Item items} from the map into the file as an array of JSON objects.
     * 
     * @return true if the {@link Item items} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Item[] itemArray = getItemsArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),itemArray);
        return true;
    }

    /**
     * loads {@linkplain Item item} from the JSON file into the map;
     * also sets next id to one more than the greatest id found in the file.
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        items = new TreeMap<>();
        nextId = 0;

        // deserializes the JSON objects from the file into an array of items;
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file.
        Item[] itemArray = objectMapper.readValue(new File(filename),Item[].class);

        // add each item to the tree map and keep track of the greatest id
        for (Item item : itemArray) {
            items.put(item.getId(),item);
            if (item.getId() > nextId)
                nextId = item.getId();
        }
        // make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Item[] getItems() {
        synchronized(items) {
            return getItemsArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Item[] findItems(String containsText) {
        synchronized(items) {
            return getItemsArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Item getItem(int id) {
        synchronized(items) {
            if (items.containsKey(id))
                return items.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Item createItem(Item item) throws IOException {
        synchronized(items) {
            // we create a new item object because the id field is immutable
            // and we need to assign the next unique id
            if (nameExists(item.getName())== false){
                Item newItem = new Item(nextId(),item.getName(), item.getStock(), item.getPrice());
                items.put(newItem.getId(),newItem);
                save(); // may throw an IOException
                return newItem;
            }
            else {
                return null;
            }
            
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Item updateItem(Item item) throws IOException {
        synchronized(items) {
            if (items.containsKey(item.getId()) == false)
                return null;  // item does not exist

            items.put(item.getId(),item);
            save(); // may throw an IOException
            return item;
        }
    }

   /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteItem(int id) throws IOException {
        synchronized(items) {
            if (items.containsKey(id)) {
                items.remove(id);
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
    public boolean nameExists(String name) throws IOException {
    synchronized(items) {
        for (Item stock_item: items.values()){
            if (stock_item.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
}

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.ItemDAO;
import com.estore.api.estoreapi.model.Item;

/**
 * handles the rest api requests for the item resource.
 * 
 * {@literal @}RestController spring annotation identifies this class as a rest api
 * method handler to the spring framework.
 * 
 * @author team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */

@RestController
@RequestMapping("items")
public class ItemController {
    private static final Logger LOG = Logger.getLogger(ItemController.class.getName());
    private ItemDAO itemDao;

    /**
     * creates a rest api controller to reponds to requests.
     * 
     * @param ItemDao the {@link ItemDAO item data access object} to perform CRUD operations
     */
    public ItemController(ItemDAO itemDao) {
        this.itemDao = itemDao;
    }

    /**
     * responds to the GET request for a {@linkplain Item item} for the given id.
     * 
     * @param id The id used to locate the {@link Item item}
     * 
     * @return ResponseEntity with {@link Item item} object and HTTP status of OK if found
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author Jonathan Campbell
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable int id) {
        LOG.info("GET /items/" + id);
        try {
            Item item = itemDao.getItem(id);
            if (item != null)
                return new ResponseEntity<Item>(item, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Item items}
     * 
     * @return ResponseEntity with array of {@link Item item} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author Alex Martinez
     */
    @GetMapping("")
    public ResponseEntity<Item[]> getItems() {
        LOG.info("GET /items");
        try{
            Item[] items = itemDao.getItems();
            if (items != null)
                return new ResponseEntity<Item[]>(items, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * responds to the GET request for all {@linkplain Item items} whose name contains
     * the text in name.
     * 
     * @param name the name parameter which contains the text used to find the {@link Item items}
     * 
     * @return ResponseEntity with array of {@link Item item} objects (may be empty) and
     * HTTP status of OK
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author Daniel Kench
     */
    @GetMapping("/")
    public ResponseEntity<Item[]> searchItems(@RequestParam String name) {
        LOG.info("GET /items/?name= " + name);
        try {
            Item[] items = itemDao.findItems(name);
            if (items != null)
                return new ResponseEntity<Item[]>(items, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * creates a {@linkplain Item item} with the provided item object.
     * 
     * @param item - The {@link Item item} to create
     * 
     * @return ResponseEntity with created {@link Item item} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Item item} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author Kara Kolodinsky
     */
    @PostMapping("")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        LOG.info("POST /items/ " + item);
        try {
            Item created_item = itemDao.createItem(item);
            if (created_item != null){
                return new ResponseEntity<Item>(created_item,HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<Item>(HttpStatus.CONFLICT);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //kk was here :o)
    }

    /**
     * updates the {@linkplain Item item} with the provided {@linkplain Item item} object, if it exists.
     * 
     * @param item the {@link Item item} to update
     * 
     * @return ResponseEntity with updated {@link Item item} object and HTTP status of OK if updated
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author Alex Hetrick
     */
    @PutMapping("")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        LOG.info("PUT /items " + item);
        try {
            Item updatedItem = itemDao.updateItem(item);
            if (updatedItem != null)
                return new ResponseEntity<Item>(updatedItem, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a {@linkplain Item item} with the given id
     * 
     * @param id The id of the {@link Item item} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author Jonathan Campbell
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable int id) {
        LOG.info("DELETE /items/" + id);
        try {
            if(itemDao.deleteItem(id)) {
                return new ResponseEntity<Item>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

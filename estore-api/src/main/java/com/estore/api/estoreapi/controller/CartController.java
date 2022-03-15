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
import org.springframework.web.client.HttpClientErrorException.Conflict;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.CartDAO;
import com.estore.api.estoreapi.model.CartItem;
import com.estore.api.estoreapi.model.Item;

/**
 * handles the rest api requests for the item resource.
 * 
 * {@literal @}RestController spring annotation identifies this class as a rest api
 * method handler to the spring framework.
 * 
 * @author kara kolodinsky of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */

@RestController
@RequestMapping("cart")
public class CartController {
    private static final Logger LOG = Logger.getLogger(CartController.class.getName());
    private CartDAO CartDao;

    /**
     * creates a rest api controller to reponds to requests.
     * 
     * @param ItemDao the {@link ItemDAO item data access object} to perform CRUD operations
     */
    public CartController(CartDAO CartDao) {
        this.CartDao = CartDao;
    }

    /**
     * Responds to the GET request for all {@linkplain Item items}
     * 
     * @return ResponseEntity with array of {@link Item item} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<CartItem[]> getItems() {
        LOG.info("GET /cart");
        try{
            CartItem[] items = CartDao.getCart();
            if (items != null)
                return new ResponseEntity<CartItem[]>(items, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * updates the {@linkplain Item item} with the provided {@linkplain Item item} object, if it exists.
     * 
     * @param item the {@link Item item} to update
     * 
     * @return ResponseEntity with updated {@link Item item} object and HTTP status of OK if updated
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<CartItem> updateItem(@RequestBody CartItem item) {
        LOG.info("PUT /cart " + item.toString());
        try {
            CartItem updatedItem = CartDao.updateItem(item);
            if (updatedItem != null)
                return new ResponseEntity<CartItem>(updatedItem, HttpStatus.OK);
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
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CartItem> deleteItem(@PathVariable Item item) {
        LOG.info("DELETE /cart/" + item.toString());
        try {
            if(CartDao.deleteCartItem(item)) {
                return new ResponseEntity<CartItem>(HttpStatus.OK);
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
     * creates a {@linkplain Item item} with the provided item object.
     * 
     * @param item - The {@link Item item} to create
     * 
     * @return ResponseEntity with created {@link Item item} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Item item} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<CartItem> addItem(@RequestBody Item item) {
        LOG.info("POST /cart/ " + item);
        try {
            CartItem created_item = CartDao.addCartItem(item);
            if (created_item != null){
                return new ResponseEntity<CartItem>(created_item,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<CartItem>(HttpStatus.CONFLICT);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.ItemDAO;
import com.estore.api.estoreapi.model.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the User Controller class
 * 
 * @author SWEN Faculty
 */
@Tag("Controller-tier")
public class ItemControllerTest {
    private ItemController itemController;
    private ItemDAO mockItemDAO;

    /**
     * Before each test, create a new UserController object and inject
     * a mock User DAO
     */
    @BeforeEach
    public void setupUserController() {
        mockItemDAO = mock(ItemDAO.class);
        itemController = new ItemController(mockItemDAO);
    }

    @Test
    public void testGetItem() throws IOException {  // getItem may throw IOException
        //Setup
        Item item = new Item(44,"Puzzle",10, (float)99.99);
        // When the same id is passed in, our mock Item DAO will return the Item object
        when(mockItemDAO.getItem(item.getId())).thenReturn(item);
        //Invoke
        ResponseEntity<Item> response = itemController.getItem(item.getId());
        //Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    public void testGetItemNotFound() throws Exception { // getItem may throw IOException
        // Setup
        int itemId = 99;
        // When the same id is passed in, our mock Item DAO will return null, simulating
        // no item found
        when(mockItemDAO.getItem(itemId)).thenReturn(null);
        // Invoke
        ResponseEntity<Item> response = itemController.getItem(itemId);
        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetItemHandleException() throws Exception { // getItem may throw IOException
        // Setup
        int itemId = 99;
        // When getItem is called on the Mock Item DAO, throw an IOException
        doThrow(new IOException()).when(mockItemDAO).getItem(itemId);
        // Invoke
        ResponseEntity<Item> response = itemController.getItem(itemId);
        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /*****************************************************************
     * The following tests will fail until all ItemController methods
     * are implemented.
     ****************************************************************/

    @Test
    public void testCreateItem() throws IOException {  // createItem may throw IOException
        // Setup
        Item item = new Item(99,"Keychain",25, (float) 3.99);
        // when createItem is called, return true simulating successful
        // creation and save
        when(mockItemDAO.createItem(item)).thenReturn(item);

        // Invoke
        ResponseEntity<Item> response = itemController.createItem(item);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(item,response.getBody());
    }

    @Test
    public void testCreateItemFailed() throws IOException {  // createItem may throw IOException
        // Setup
        Item item = new Item(99,"Pin", 50, (float)1.99);
        // when createItem is called, return false simulating failed
        // creation and save
        when(mockItemDAO.createItem(item)).thenReturn(null);

        // Invoke
        ResponseEntity<Item> response = itemController.createItem(item);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void testCreateItemHandleException() throws IOException {  // createItem may throw IOException
        // Setup
        Item item = new Item(99,"Canvas", 10, (float) 10.99);

        // When createItem is called on the Mock Item DAO, throw an IOException
        doThrow(new IOException()).when(mockItemDAO).createItem(item);

        // Invoke
        ResponseEntity<Item> response = itemController.createItem(item);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testUpdateItem() throws IOException { 
        // Setup
        Item item = new Item(99, "Sticker", 20, (float)1.99);
        // when updateUser is called, return true simulating successful
        // update and save
        when(mockItemDAO.updateItem(item)).thenReturn(item);
        ResponseEntity<Item> response = itemController.updateItem(item);
        item.setName("Bolt");

        // Invoke
        response = itemController.updateItem(item);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(item,response.getBody());
    }

    @Test
    public void testUpdateItemFailed() throws IOException {
        // Setup
        Item item = new Item(99, "Sticker", 20, (float)1.99);
        // when updateUser is called, return true simulating successful
        // update and save
        when(mockItemDAO.updateItem(item)).thenReturn(null);

        // Invoke
        ResponseEntity<Item> response = itemController.updateItem(item);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateItemHandleException() throws IOException { 
        // Setup
        Item item = new Item(99, "Sticker", 20, (float)1.99);
        // When updateUser is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(mockItemDAO).updateItem(item);

        // Invoke
        ResponseEntity<Item> response = itemController.updateItem(item);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetItems() throws IOException { 
        // Setup
        Item[] items = new Item[4];
        items[0] = new Item(45, "Wood Print", 15, (float)299.99);
        items[1] = new Item(46, "Metal Print", 20, (float)199.99);
        items[2] = new Item(47, "Sticker", 100, (float)9.99);
        items[3] = new Item(48, "Bikini", 50, (float)49.99);
        // When getItems is called return the items created above
        when(mockItemDAO.getItems()).thenReturn(items);
        // Invoke
        ResponseEntity<Item[]> response = itemController.getItems();
        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(items,response.getBody());
    }

    @Test
    public void testGetItemsHandleException() throws IOException { 
        // Setup
        // When getItems is called on the Mock Item DAO, throw an IOException
        doThrow(new IOException()).when(mockItemDAO).getItems();
        // Invoke
        ResponseEntity<Item[]> response = itemController.getItems();
        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchItems() throws IOException {
        // Setup
        String searchString = "Print";
        Item[] items = new Item[2];
        items[0] = new Item(99, "Paper Print", 10, (float)99.99);
        items[1] = new Item(100, "Canvas Print", 10, (float)199.99);
        // When findItems is called with the search string, return the two
        /// items above
        when(mockItemDAO.findItems(searchString)).thenReturn(items);

        // Invoke
        ResponseEntity<Item[]> response = itemController.searchItems(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(items,response.getBody());
    }

    @Test
    public void testSearchItemsHandleException() throws IOException {
        // Setup
        String searchString = "an";
        // When createUser is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(mockItemDAO).findItems(searchString);

        // Invoke
        ResponseEntity<Item[]> response = itemController.searchItems(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteItem() throws IOException {

    }

    @Test
    public void testDeleteItemNotFound() throws IOException { 
    
    }

    @Test
    public void testDeleteItemHandleException() throws IOException { 
    }
}

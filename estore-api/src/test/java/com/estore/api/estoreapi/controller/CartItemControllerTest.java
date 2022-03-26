package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.CartDAO;
import com.estore.api.estoreapi.model.CartItem;
import com.estore.api.estoreapi.model.Item;
import com.estore.api.estoreapi.controller.CartController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the CartItem Controller class
 * 
 * @author SWEN Faculty
 */
@Tag("Controller-tier")
public class CartItemControllerTest {
    private CartController cartController;
    private CartDAO mockcartDAO;

    /**
     * Before each test, create a new CartController object and inject
     * a mock Cart DAO
     */
    @BeforeEach
    public void setupCartController() {
        mockcartDAO = mock(CartDAO.class);
        cartController = new CartController(mockcartDAO);
    }

    @Test
    public void testGetCartItem() throws IOException {  // getHero may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        CartItem cartitem = new CartItem(item, 1);
        // When the same id is passed in, our mock Hero DAO will return the Hero object
        when(mockcartDAO.getCartItem(item)).thenReturn(cartitem);

        // Invoke
        ResponseEntity<CartItem> response = cartController.getItem(item);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(cartitem,response.getBody());
    }

    @Test
    public void testGetCartItemNotFound() throws Exception { // createHero may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        // When the same id is passed in, our mock Hero DAO will return null, simulating
        // no hero found
        when(cartController.getItem(item)).thenReturn(null);

        // Invoke
        ResponseEntity<CartItem> response = cartController.getItem(item);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    /*****************************************************************
     * The following tests will fail until all HeroController methods
     * are implemented.
     ****************************************************************/

    @Test
    public void testCreateCartItemConflict() throws IOException {  // createHero may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        CartItem item2 = mockcartDAO.addCartItem(item);
        // when createHero is called, return true simulating successful
        // creation and save
        when(mockcartDAO.addCartItem(item)).thenReturn(item2);

        // Invoke
        ResponseEntity<CartItem> response = cartController.addItem(item);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
        assertEquals(item2,response.getBody());
    }

    @Test
    public void testCreateCartItem() throws IOException {  // createHero may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        CartItem item2 = new CartItem(item, 1);
        // when createHero is called, return true simulating successful
        // creation and save
        when(mockcartDAO.addCartItem(item)).thenReturn(item2);

        // Invoke
        ResponseEntity<CartItem> response = cartController.addItem(item);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(item2,response.getBody());
    }

    @Test
    public void testCreateCartItemNotFound() throws IOException {  // createHero may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);

        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockcartDAO).addCartItem(item);

        // Invoke
        ResponseEntity<CartItem> response = cartController.getItem(item);
        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateCartItem() throws IOException { // updateHero may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        CartItem cartitem = new CartItem(item, 1);
        // when updateHero is called, return true simulating successful
        // update and save
        when(mockcartDAO.updateItem(cartitem)).thenReturn(cartitem);
        ResponseEntity<CartItem> response = cartController.updateItem(cartitem);
        cartitem.setQuantity(2);

        // Invoke
        response = cartController.updateItem(cartitem);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(cartitem,response.getBody());
    }

    @Test
    public void testUpdateCartItemHandleException() throws IOException { // updateHero may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        CartItem cartitem = new CartItem(item, 1);
        // When updateHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockcartDAO).updateItem(cartitem);

        // Invoke
        ResponseEntity<CartItem> response = cartController.updateItem(cartitem);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetCart() throws IOException { // getHeroes may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        Item item2 = new Item(100,"Flag", 15, (float)8.99);
        CartItem[] Cart = new CartItem[2];
        Cart[0] = new CartItem(item, 1);
        Cart[1] = new CartItem(item2, 2);
        // When getHeroes is called return the heroes created above
        when(mockcartDAO.getCart()).thenReturn(Cart);

        // Invoke
        ResponseEntity<CartItem[]> response = cartController.getItems();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Cart,response.getBody());
    }

    @Test
    public void testGetCartHandleException() throws IOException { // getHeroes may throw IOException
        // Setup
        // When getHeroes is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockcartDAO).getCart();

        // Invoke
        ResponseEntity<CartItem[]> response = cartController.getItems();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteCartItem() throws IOException { // deleteHero may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        // when deleteHero is called return true, simulating successful deletion
        when(mockcartDAO.deleteCartItem(item.getName())).thenReturn(true);

        // Invoke
        ResponseEntity<CartItem> response = cartController.deleteItem(item.getName());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteCartItemNotFound() throws IOException { // deleteHero may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        // when deleteHero is called return false, simulating failed deletion
        when(mockcartDAO.deleteCartItem(item.getName())).thenReturn(false);

        // Invoke
        ResponseEntity<CartItem> response = cartController.deleteItem(item.getName());

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteCartItemHandleException() throws IOException { // deleteHero may throw IOException
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        // When deleteHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockcartDAO).deleteCartItem(item.getName());

        // Invoke
        ResponseEntity<CartItem> response = cartController.deleteItem(item.getName());

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}

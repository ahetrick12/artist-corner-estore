package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estore.api.estoreapi.model.Item;
import com.estore.api.estoreapi.model.CartItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the CartItem File DAO class
 * 
 * @author SWEN Faculty
 * @author team 8
 */
@Tag("Persistence-tier")
public class CartItemFileDAOTest {
    CartFileDAO cartFileDAO;
    CartItem[] testItems;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupCartFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testItems = new CartItem[3];
        Item a = new Item(99,"Hamburger", 30,(float) 3.99);
        Item b = new Item(100,"Cheeseburger", 20, (float) 4.99);
        Item c = new Item(101,"Art Print", 50, (float) 15.99);
        testItems[0] = new CartItem(a, 1);
        testItems[1] = new CartItem(b, 1);
        testItems[2] = new CartItem(c, 1);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the cartitem array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),CartItem[].class))
                .thenReturn(testItems);
        cartFileDAO = new CartFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testCart() {
        // Invoke
        CartItem[] cart = cartFileDAO.getCart();

        // Analyze
        assertEquals(cart.length,testItems.length);
        for (int i = 0; i < testItems.length;++i)
            assertEquals(cart[i],testItems[i]);
    }

    @Test
    public void testGetCartItem() {
        // Invoke
        Item a = new Item(99,"Hamburger", 30,(float) 3.99);
        CartItem item = cartFileDAO.getCartItem(a);

        // Analzye
        assertEquals(item,testItems[0]);
    }

    @Test
    public void testDeleteCartItem() {
        // Invoke
        Item a = new Item(99,"Hamburger", 30,(float) 3.99);
        boolean result = assertDoesNotThrow(() -> cartFileDAO.deleteCartItem(a.toString()),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test heroes array - 1 (because of the delete)
        // Because heroes attribute of HeroFileDAO is package private
        // we can access it directly
        assertEquals(cartFileDAO.cart.size(),testItems.length-1);
    }

    @Test
    public void testAddCartItem() {
        // Setup
        Item item = new Item(102,"Mask", 20, (float)5.99);
        int quantity = 1;
        CartItem cartitem = new CartItem(item, quantity);

        // Invoke
        CartItem result = assertDoesNotThrow(() -> cartFileDAO.addCartItem(item),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        CartItem actual = cartFileDAO.getCartItem(cartitem.getItem());
        assertEquals(actual.getItem(),cartitem.getItem());
        assertEquals(actual.getQuantity(),cartitem.getQuantity());
    }

    @Test
    public void testUpdateCartItem() throws IOException {
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99);
        CartItem cartitem = new CartItem(item, 1);
        // Invoke
        CartItem result = assertDoesNotThrow(() -> cartFileDAO.updateItem(cartitem),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        CartItem actual = cartFileDAO.updateItem(cartitem);
        assertEquals(actual, item);
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(CartItem[].class));

        Item item = new Item(4,"Flag", 30, (float) 4.00);

        assertThrows(IOException.class,
                        () -> cartFileDAO.addCartItem(item),
                        "IOException not thrown");
    }

    @Test
    public void testGetCartItemNotFound() {
        // Invoke
        Item test = new Item(4,"Flag", 30, (float) 4.00);
        CartItem item = cartFileDAO.getCartItem(test);

        // Analyze
        assertEquals(item,null);
    }

    @Test
    public void testDeleteCartItemNotFound() {
        // Invoke
        Item test = new Item(4,"Flag", 30, (float) 4.00);
        boolean result = assertDoesNotThrow(() -> cartFileDAO.deleteCartItem(test.getName()),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(cartFileDAO.cart.size(),testItems.length);
    }

    @Test
    public void testUpdateCartItemNotFound() {
        // Setup
        Item item = new Item(98, "Shirt", 20, (float)8.99);
        CartItem cartitem = new CartItem(item, 1);

        // Invoke
        CartItem result = assertDoesNotThrow(() -> cartFileDAO.updateItem(cartitem),
                                                "Unexpected exception thrown");

        // Analyze
        assertNull(result);
    }

    @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the ItemFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),Item[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new ItemFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}

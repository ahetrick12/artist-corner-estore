package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


/**
 * The unit test suite for the CartItem class
 * 
 * @author Kara Kolodinsky of Team 8
 */
@Tag("Model-tier")
public class CartItemTest {
    @Test
    public void testCtor() {
        // Setup
        int expected_id = 99;
        String expected_name = "Hamburger";
        int expected_stock = 30;
        float expected_price = (float) 3.99;
        String expected_imagelink = "dog.jpg";
        Item expected_item = new Item(expected_id,expected_name,expected_stock,expected_price, expected_imagelink);
        int expected_quantity =  2;
        CartItem cartItem = new CartItem(expected_item, expected_quantity);
        assertEquals(expected_item,cartItem.getItem());
        assertEquals(expected_quantity,cartItem.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        // Setup
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String link = "dog.jpg";
        Item item = new Item(id,name,stock,price,link);
        int quantity = 2;
        CartItem cartItem = new CartItem(item, quantity); 
        int expected_quantity = 5;

        // Invoke
        cartItem.setQuantity(expected_quantity);

        // Analyze
        assertEquals(expected_quantity,cartItem.getQuantity());
    }

    public void testSetItem(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String imagelink = "dog.jpg";
        Item item = new Item(id,name,stock,price,imagelink);
        int quantity = 2;
        CartItem cartItem = new CartItem(item, quantity); 

        Item expected_item = new Item(99, "Bandana", 30, 10, "dog.jpg");

        // Invoke
        cartItem.setItem(expected_item);

        // Analyze
        assertEquals(expected_item,cartItem.getItem());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String imagelink = "dog.jpg";
        int quantity = 2;
        Item item = new Item(id,name,stock,price,imagelink);
        CartItem cartItem = new CartItem(item, quantity); 
        String expected_string = String.format(CartItem.STRING_FORMAT,item, quantity);
        

        // Invoke
        String actual_string = cartItem.toString();

        // Analyze`
        assertEquals(expected_string,actual_string);
    }

    @Test
    public void testIncrementQuantity() {
        // Setup
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String imagelink ="dog.jpg";
        Item item = new Item(id,name,stock,price,imagelink);
        int quantity = 2;
        CartItem cartItem = new CartItem(item, quantity); 
        int expected_quantity = 3;
        // Invoke
        cartItem.incrementQuantity();

        // Analyze
        assertEquals(expected_quantity,cartItem.getQuantity());
    }

    @Test
    public void testCompareItem() {
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String imagelink = "dog.jpg";
        Item item = new Item(id,name,stock,price,imagelink);
        Item item2 = new Item(55,"Bandaid",5,(float)3.00, "baby.jpg");
        int quantity = 2;
        CartItem cartItem = new CartItem(item, quantity); 

        boolean resp1 = cartItem.compareItem(item);
        boolean resp2 = cartItem.compareItem(item2);

        assertEquals(resp1,true);
        assertEquals(resp2,false);

    }
    
}
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
        int expected_S = 15;
        int expected_M = 25;
        int expected_L = 40;
        int expected_XL = 35;
        int expected_x920 = 0;
        int expected_x1930 = 0;
        Item expected_item = new Item(expected_id,expected_name,expected_stock,expected_price,expected_S,expected_M,expected_L,
                            expected_XL,expected_x920,expected_x1930, "");
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
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, "");
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
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, "");
        int quantity = 2;
        CartItem cartItem = new CartItem(item, quantity); 

        Item expected_item = new Item(99, "Bandana", 30, 10,10,15,40,30,0,0, "");

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
        int quantity = 2;
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, "");
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
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, "");
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
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, "");
        Item item2 = new Item(55,"Bandaid",5,(float)3.00, 20,25,50,10,0,0, "");
        int quantity = 2;
        CartItem cartItem = new CartItem(item, quantity); 

        boolean resp1 = cartItem.compareItem(item);
        boolean resp2 = cartItem.compareItem(item2);

        assertEquals(resp1,true);
        assertEquals(resp2,false);

    }
    
}
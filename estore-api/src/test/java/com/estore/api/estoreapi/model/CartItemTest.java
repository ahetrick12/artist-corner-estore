package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


/**
 * The unit test suite for the CartItem class
 * 
 * @author Team 8
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
        int expected_S = 15;
        int expected_M = 25;
        int expected_L = 40;
        int expected_XL = 35;
        int expected_x920 = 0;
        int expected_x1930 = 0;
        Item expected_item = new Item(expected_id,expected_name,expected_stock,expected_price,expected_S,expected_M,expected_L,
                            expected_XL,expected_x920,expected_x1930, expected_imagelink);
        CartItem cartItem = new CartItem(expected_item, expected_S,expected_M,expected_L,expected_XL,expected_x920, expected_x1930); 
        assertEquals(expected_item, cartItem.getItem());
        assertEquals(expected_S, cartItem.getSmall());
        assertEquals(expected_M, cartItem.getMedium());
        assertEquals(expected_L, cartItem.getLarge());
        assertEquals(expected_XL, cartItem.getXLarge());
        assertEquals(expected_x920, cartItem.getx920());
        assertEquals(expected_x1930, cartItem.getX1930());

    }

    @Test
    public void testGetItem()
    {
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
        CartItem cartItem = new CartItem(expected_item, 0, 0, 0, 0, 0, 0); 
        // Test
        assertEquals(expected_item, cartItem.getItem());
    }

    @Test
    public void testGetSmall()
    {
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
        CartItem cartItem = new CartItem(expected_item, 0, 0, 0, 0, 0, 0); 
        // Test
        assertEquals(0, cartItem.getSmall());
    }

    @Test
    public void testGetMedium()
    {
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
        CartItem cartItem = new CartItem(expected_item, 0, 0, 0, 0, 0, 0); 
        // Test
        assertEquals(0, cartItem.getMedium());
    }

    @Test
    public void testGetLarge()
    {
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
        CartItem cartItem = new CartItem(expected_item, 0, 0, 0, 0, 0, 0); 
        // Test
        assertEquals(0, cartItem.getLarge());
    }

    @Test
    public void testGetXLarge()
    {
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
        CartItem cartItem = new CartItem(expected_item, 0, 0, 0, 0, 0, 0); 
        // Test
        assertEquals(0, cartItem.getXLarge());
    }

    @Test
    public void testGetx920()
    {
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
        CartItem cartItem = new CartItem(expected_item, 0, 0, 0, 0, 0, 0); 
        // Test
        assertEquals(0, cartItem.getx920());
    }

    @Test
    public void testGetX1930()
    {
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
        CartItem cartItem = new CartItem(expected_item, 0, 0, 0, 0, 0, 0); 
        // Test
        assertEquals(0, cartItem.getX1930());
    }

    @Test
    public void testSetSmall()
    {
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
        Item item = new Item(expected_id,expected_name,expected_stock,expected_price,expected_S,expected_M,expected_L,
                            expected_XL,expected_x920,expected_x1930, "");
        CartItem cartItem = new CartItem(item, 0, 0, 0, 0, 0, 0); 

        // Invoke
        cartItem.setSmall(1);

        // Test
        assertEquals(1, cartItem.getSmall());
    }

    @Test
    public void testSetMedium()
    {
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
        Item item = new Item(expected_id,expected_name,expected_stock,expected_price,expected_S,expected_M,expected_L,
                            expected_XL,expected_x920,expected_x1930, "");
        CartItem cartItem = new CartItem(item, 0, 0, 0, 0, 0, 0); 

        // Invoke
        cartItem.setMedium(1);

        // Test
        assertEquals(1, cartItem.getMedium());
    }

    @Test
    public void testSetLarge()
    {
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
        Item item = new Item(expected_id,expected_name,expected_stock,expected_price,expected_S,expected_M,expected_L,
                            expected_XL,expected_x920,expected_x1930, "");
        CartItem cartItem = new CartItem(item, 0, 0, 0, 0, 0, 0); 

        // Invoke
        cartItem.setLarge(1);

        // Test
        assertEquals(1, cartItem.getLarge());
    }

    @Test
    public void testSetXLarge()
    {
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
        Item item = new Item(expected_id,expected_name,expected_stock,expected_price,expected_S,expected_M,expected_L,
                            expected_XL,expected_x920,expected_x1930, "");
        CartItem cartItem = new CartItem(item, 0, 0, 0, 0, 0, 0); 

        // Invoke
        cartItem.setXLarge(1);

        // Test
        assertEquals(1, cartItem.getXLarge());
    }

    @Test
    public void testSetx920()
    {
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
        Item item = new Item(expected_id,expected_name,expected_stock,expected_price,expected_S,expected_M,expected_L,
                            expected_XL,expected_x920,expected_x1930, "");
        CartItem cartItem = new CartItem(item, 0, 0, 0, 0, 0, 0); 

        // Invoke
        cartItem.setx920(1);

        // Test
        assertEquals(1, cartItem.getx920());
    }

    @Test
    public void testSetX1930()
    {
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
        Item item = new Item(expected_id,expected_name,expected_stock,expected_price,expected_S,expected_M,expected_L,
                            expected_XL,expected_x920,expected_x1930, "");
        CartItem cartItem = new CartItem(item, 0, 0, 0, 0, 0, 0); 

        // Invoke
        cartItem.setX1930(1);

        // Test
        assertEquals(1, cartItem.getX1930());
    }

    @Test
    public void testSetItem(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String imagelink = "dog.jpg";
        int quantity = 2;
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, "");
        CartItem cartItem = new CartItem(item, 0, 0, 0, 0, 0, 0); 

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
        String imagelink = "aa";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, imagelink);
        CartItem cartItem = new CartItem(item,S,M,L,XL,x920,x1930); 
        String expected_string = String.format(CartItem.STRING_FORMAT,item,S,M,L,XL,x920,x1930);

        

        // Invoke
        String actual_string = cartItem.toString();

        // Analyze`
        assertEquals(expected_string,actual_string);
    }

    @Test
    public void testCompareItem() {
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String imagelink = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, imagelink);
        Item item2 = new Item(55,"Bandaid",5,(float)3.00, 20,25,50,10,0,0, "");
        CartItem cartItem = new CartItem(item, 0, 0, 0, 0, 0, 0); 

        boolean resp1 = cartItem.compareItem(item);
        boolean resp2 = cartItem.compareItem(item2);

        assertEquals(resp1,true);
        assertEquals(resp2,false);

    }
    
}
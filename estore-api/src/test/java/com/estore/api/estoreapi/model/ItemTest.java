package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Item class
 * 
 * @author Kara Kolodinsky of Team 8
 */
@Tag("Model-tier")
public class ItemTest {
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
        String expected_image = "dog.jpg";

        // Invoke
        Item item = new Item(expected_id,expected_name,expected_stock,expected_price,expected_S,expected_M,expected_L,
                                expected_XL,expected_x920,expected_x1930, expected_image);

        // Analyze
        assertEquals(expected_id,item.getId());
        assertEquals(expected_name,item.getName());
        assertEquals(expected_stock,item.getStock());
        assertEquals(expected_price,item.getPrice());
    }

    @Test
    public void testSetName() {
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

        String expected_name = "Socks";

        // Invoke
        item.setName(expected_name);

        // Analyze
        assertEquals(expected_name,item.getName());
    }
    @Test
    public void testSetStock(){
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

        int expected_stock = 50;

        // Invoke
        item.setStock(expected_stock);

        // Analyze
        assertEquals(expected_stock,item.getStock());
    }

    @Test
    public void testSetPrice(){
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

        float expected_price = (float) 99.99;

        // Invoke
        item.setPrice(expected_price);

        // Analyze
        assertEquals(expected_price,item.getPrice());
    }

    @Test
    public void testToString() {
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
        String expected_string = String.format(Item.STRING_FORMAT,id,name,stock,price,S,M,L,XL,x920,x1930, "");
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, "");

        // Invoke
        String actual_string = item.toString();

        // Analyze`
        assertEquals(expected_string,actual_string);
    }

    @Test
    public void testCompareTo(){
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
        Item item2 = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, "");
        Item item3 = new Item(2,"shoes",2,(float) 3.99, 20,30,40,10,0,0, "");

        int res1 = item.compareTo(item2);
        int res2 = item.compareTo(item3);

        assertEquals(res1,0);
        assertEquals(res2,-49);

    }
}
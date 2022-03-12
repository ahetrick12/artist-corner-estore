package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Hero class
 * 
 * @author SWEN Faculty
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

        // Invoke
        Item item = new Item(expected_id,expected_name,expected_stock,expected_price);

        // Analyze
        assertEquals(expected_id,item.getId());
        assertEquals(expected_name,item.getName());
        assertEquals(expected_name,item.getStock());
        assertEquals(expected_name,item.getPrice());
    }

    @Test
    public void testName() {
        // Setup
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        Item item = new Item(id,name,stock,price);

        String expected_name = "Socks";

        // Invoke
        item.setName(expected_name);

        // Analyze
        assertEquals(expected_name,item.getName());
    }

    public void testStock(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        Item item = new Item(id,name,stock,price);

        int expected_stock = 50;

        // Invoke
        item.setStock(expected_stock);

        // Analyze
        assertEquals(expected_stock,item.getStock());
    }

    public void testPrice(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        Item item = new Item(id,name,stock,price);

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
        String expected_string = String.format(Item.STRING_FORMAT,id,name);
        Item item = new Item(id,name,stock,price);

        // Invoke
        String actual_string = item.toString();

        // Analyze
        assertEquals(expected_string,actual_string);
    }
}
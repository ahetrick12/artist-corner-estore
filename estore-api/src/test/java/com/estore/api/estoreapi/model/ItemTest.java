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
        // Invoke
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
        assertEquals(expected_image,item.getImageLink());
    }

    @Test
    public void testSetName() {
        // Setup
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);

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
        String link = "dog.jpg";
        int S = 10; 
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);

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
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);

        float expected_price = (float) 99.99;

        // Invoke
        item.setPrice(expected_price);

        // Analyze
        assertEquals(expected_price,item.getPrice());
    }

    @Test
    public void testSetImage(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930,link);

        String expected_image = "wowo.jpg";

        // Invoke
        item.setImageLink(expected_image);

        // Analyze
        assertEquals(expected_image,item.getImageLink());
    }

    @Test
    public void testSetSmall(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);

        int expected_small = 500;

        // Invoke
        item.setS(expected_small);

        // Analyze
        assertEquals(expected_small,item.getS());
    }

    @Test
    public void testSetMed(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);

        int expected_med = 500;

        // Invoke
        item.setM(expected_med);

        // Analyze
        assertEquals(expected_med,item.getM());
    }

    @Test
    public void testSetLarge(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);

        int expected_large = 500;

        // Invoke
        item.setL(expected_large);

        // Analyze
        assertEquals(expected_large,item.getL());
    }

    @Test
    public void testSetXL(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);

        int expected_XL = 500;

        // Invoke
        item.setXL(expected_XL);

        // Analyze
        assertEquals(expected_XL,item.getXL());
    }

    @Test
    public void testSetx920(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);

        int expected_x920 = 500;

        // Invoke
        item.setx920(expected_x920);

        // Analyze
        assertEquals(expected_x920,item.getx920());
    }

    @Test
    public void testSetX1930(){
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);

        int expected_x1930 = 500;

        // Invoke
        item.setx1930(expected_x1930);

        // Analyze
        assertEquals(expected_x1930,item.getx1930());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 99;
        String name = "Bandana";
        int stock = 30;
        float price = 10;
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        String expected_string = String.format(Item.STRING_FORMAT,id,name,stock,price,S,M,L,XL,x920,x1930, link);
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930,link);

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
        String link = "dog.jpg";
        int S = 10;
        int M = 15;
        int L = 40;
        int XL = 30;
        int x920 = 0;
        int x1930 = 0;
        Item item = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);
        Item item2 = new Item(id,name,stock,price,S,M,L,XL,x920,x1930, link);
        Item item3 = new Item(2,"shoes",2,(float) 3.99, 20,30,40,10,0,0, link);
        int res1 = item.compareTo(item2);
        int res2 = item.compareTo(item3);

        assertEquals(res1,0);
        assertEquals(res2,-49);

    }
}
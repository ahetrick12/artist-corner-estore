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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the Item File DAO class
 * 
 * @author SWEN Faculty
 * @author team 8
 */
@Tag("Persistence-tier")
public class ItemFileDAOTest {
    ItemFileDAO itemFileDAO;
    Item[] testItems;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupItemFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testItems = new Item[3];
        testItems[0] = new Item(99,"Hamburger", 30,(float) 3.99, "");
        testItems[1] = new Item(100,"Cheeseburger", 20, (float) 4.99, "");
        testItems[2] = new Item(101,"Art Print", 50, (float) 15.99, "");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the item array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Item[].class))
                .thenReturn(testItems);
        itemFileDAO = new ItemFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testGetItems() {
        // Invoke
        Item[] items = itemFileDAO.getItems();

        // Analyze
        assertEquals(items.length,testItems.length);
        for (int i = 0; i < testItems.length;++i)
            assertEquals(items[i],testItems[i]);
    }

    @Test
    public void testFindItems() {
         // Invoke
         Item[] items = itemFileDAO.findItems("burger");

         // Analyze
         assertEquals(items.length,2);
         assertEquals(items[0],testItems[0]);
         assertEquals(items[1],testItems[1]);
    }

    @Test
    public void testGetItem() {

        Item item = itemFileDAO.getItem(99);

        // Analzye
        assertEquals(item,testItems[0]);
    }

    @Test
    public void testDeleteItem() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> itemFileDAO.deleteItem(99),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test heroes array - 1 (because of the delete)
        // Because heroes attribute of HeroFileDAO is package private
        // we can access it directly
        assertEquals(itemFileDAO.items.size(),testItems.length-1);
    }

    @Test
    public void testCreateItem() {
        // Setup
        Item item = new Item(102,"Mask", 20, (float)5.99, "");

        // Invoke
        Item result = assertDoesNotThrow(() -> itemFileDAO.createItem(item),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Item actual = itemFileDAO.getItem(item.getId());
        assertEquals(actual.getId(),item.getId());
        assertEquals(actual.getName(),item.getName());
        assertEquals(actual.getStock(),item.getStock());
        assertEquals(actual.getPrice(),item.getPrice());
    }

    @Test
    public void testUpdateItem() {
        // Setup
        Item item = new Item(99,"Sticker", 20, (float)1.99, "");

        // Invoke
        Item result = assertDoesNotThrow(() -> itemFileDAO.updateItem(item),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Item actual = itemFileDAO.getItem(item.getId());
        assertEquals(actual, item);
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(Item[].class));

        Item item = new Item(99,"Sticker", 20, (float)1.99, "");

        assertThrows(IOException.class,
                        () -> itemFileDAO.createItem(item),
                        "IOException not thrown");
    }


    @Test
    public void testGetItemNotFound() {

         // Invoke
         Item item = itemFileDAO.getItem(98);

         // Analyze
         assertEquals(item,null);
    }

    @Test
    public void testDeleteItemNotFound() {
         // Invoke
         boolean result = assertDoesNotThrow(() -> itemFileDAO.deleteItem(98),
         "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(itemFileDAO.items.size(),testItems.length);
    }

    @Test
    public void testUpdateItemNotFound() {
        // Setup
        Item item = new Item(98, "Shirt", 20, (float)8.99, "");

        // Invoke
        Item result = assertDoesNotThrow(() -> itemFileDAO.updateItem(item),
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

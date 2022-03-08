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
 * Test the Hero Controller class
 * 
 * @author SWEN Faculty
 */
@Tag("Controller-tier")
public class ItemControllerTest {
    private ItemController itemController;
    private ItemDAO mockItemDAO;

    /**
     * Before each test, create a new HeroController object and inject
     * a mock Hero DAO
     */
    @BeforeEach
    public void setupHeroController() {
        mockItemDAO = mock(ItemDAO.class);
        itemController = new ItemController(mockItemDAO);
    }

    @Test
    public void testGetItem() throws IOException {  // getHero may throw IOException
    }

    @Test
    public void testGetHeroNotFound() throws Exception { // createHero may throw IOException
    }

    @Test
    public void testGetHeroHandleException() throws Exception { // createHero may throw IOException
    }

    /*****************************************************************
     * The following tests will fail until all HeroController methods
     * are implemented.
     ****************************************************************/

    @Test
    public void testCreateItem() throws IOException {  
    }

    @Test
    public void testCreateItemFailed() throws IOException { 
    }

    @Test
    public void testCreateItemHandleException() throws IOException { 

    }

    @Test
    public void testUpdateItem() throws IOException { 
    }

    @Test
    public void testUpdateItemFailed() throws IOException {

    }

    @Test
    public void testUpdateItemHandleException() throws IOException { 
    }

    @Test
    public void testGetItems() throws IOException { 
    
    }

    @Test
    public void testGetItemsHandleException() throws IOException { 
    }

    @Test
    public void testSearchItems() throws IOException { 
       
    }

    @Test
    public void testSearchItemsHandleException() throws IOException { 
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

package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.MessageDAO;
import com.estore.api.estoreapi.model.Message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the User Controller class
 * 
 * @author SWEN Faculty
 */
@Tag("Controller-tier")
public class MessageControllerTest {
    private MessageController messageController;
    private MessageDAO mockMessageDAO;

    /**
     * Before each test, create a new UserController object and inject
     * a mock User DAO
     */
    @BeforeEach
    public void setupUserController() {
        mockMessageDAO = mock(MessageDAO.class);
        messageController = new MessageController(mockMessageDAO);
    }

    @Test
    public void testGetMessage() throws IOException {  // getItem may throw IOException
        //Setup
        Message message = new Message(44,"amdin","Hello user.");
        // When the same id is passed in, our mock Item DAO will return the Item object
        when(mockMessageDAO.getMessage(message.getId())).thenReturn(message);
        //Invoke
        ResponseEntity<Message> response = messageController.getMessage(message.getId());
        //Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    public void testGetMessageNotFound() throws Exception { // getItem may throw IOException
        // Setup
        int messageId = 99;
        // When the same id is passed in, our mock Item DAO will return null, simulating
        // no item found
        when(mockMessageDAO.getMessage(messageId)).thenReturn(null);
        // Invoke
        ResponseEntity<Message> response = messageController.getMessage(messageId);
        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetItemHandleException() throws Exception { // getItem may throw IOException
        // Setup
        int messageId = 99;
        // When getItem is called on the Mock Item DAO, throw an IOException
        doThrow(new IOException()).when(mockMessageDAO).getMessage(messageId);
        // Invoke
        ResponseEntity<Message> response = messageController.getMessage(messageId);
        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    /*****************************************************************
     * The following tests will fail until all ItemController methods
     * are implemented.
     ****************************************************************/

    @Test
    public void testCreateMessage() throws IOException {  // createItem may throw IOException
        // Setup
        Message message = new Message(98,"axm9912","My name is ...");
        // when createItem is called, return true simulating successful
        // creation and save
        when(mockMessageDAO.createMessage(message)).thenReturn(message);

        // Invoke
        ResponseEntity<Message> response = messageController.createMessage(message);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(message,response.getBody());
    }

    @Test
    public void testCreateMessageFailed() throws IOException {  // createItem may throw IOException
        // Setup
        Message message = new Message(97,"admin", "the price is....");
        // when createItem is called, return false simulating failed
        // creation and save
        when(mockMessageDAO.createMessage(message)).thenReturn(null);

        // Invoke
        ResponseEntity<Message> response = messageController.createMessage(message);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void testCreateMessageHandleException() throws IOException {  // createItem may throw IOException
        // Setup
        Message message = new Message(96,"axm9912", "thats too high....");

        // When createItem is called on the Mock Item DAO, throw an IOException
        doThrow(new IOException()).when(mockMessageDAO).createMessage(message);

        // Invoke
        ResponseEntity<Message> response = messageController.createMessage(message);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetMessages() throws IOException { 
        // Setup
        Message[] messages = new Message[3];
        messages[0] = new Message(45, "admin", "you pay or you go ....");
        messages[1] = new Message(46, "axm9912", "and then....");
        messages[2] = new Message(47, "admin", "no and then...");
        // When getItems is called return the items created above
        when(mockMessageDAO.getMessages()).thenReturn(messages);
        // Invoke
        ResponseEntity<Message[]> response = messageController.getMessages();
        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(messages,response.getBody());
    }

    @Test
    public void testGetMessagesHandleException() throws IOException { 
        // Setup
        // When getItems is called on the Mock Item DAO, throw an IOException
        doThrow(new IOException()).when(mockMessageDAO).getMessages();
        // Invoke
        ResponseEntity<Message[]> response = messageController.getMessages();
        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchItems() throws IOException {
        // Setup
        String searchString = "price";
        Message[]  messages = new Message[2];
        messages[0] = new Message(99, "admin", "the price is set and cant be changed");
        messages[1] = new Message(100, "axm9912", "i want a discount");
        // When findItems is called with the search string, return the two
        /// items above
        when(mockMessageDAO.findMessages(searchString)).thenReturn(messages);

        // Invoke
        ResponseEntity<Message[]> response = messageController.searchMessages(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(messages,response.getBody());
    }

    @Test
    public void testSearchMessagesHandleException() throws IOException {
        // Setup
        String searchString = "an";
        // When createUser is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(mockMessageDAO).findMessages(searchString);

        // Invoke
        ResponseEntity<Message[]> response = messageController.searchMessages(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchMessagesNotFound() throws IOException {
        // Setup
        String searchString = "an";
        // When createUser is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(mockMessageDAO).findMessages(searchString);

        // Invoke
        ResponseEntity<Message[]> response = messageController.searchMessages(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteMessage() throws IOException {
        // Setup
        int itemId = 99;
        // when deleteItem is called return true, simulating successful deletion
        when(mockMessageDAO.deleteMessage(itemId)).thenReturn(true);

        // Invoke
        ResponseEntity<Message> response = messageController.deleteMessage(itemId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteMessageNotFound() throws IOException { 
        // Setup
        int itemId = 99;
        // when deleteItem is called return false, simulating failed deletion
        when(mockMessageDAO.deleteMessage(itemId)).thenReturn(false);

        // Invoke
        ResponseEntity<Message> response = messageController.deleteMessage(itemId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteMessageHandleException() throws IOException { 
        // Setup
        int itemId = 99;
        // When deleteItem is called on the Mock Item DAO, throw an IOException
        doThrow(new IOException()).when(mockMessageDAO).deleteMessage(itemId);

        // Invoke
        ResponseEntity<Message> response = messageController.deleteMessage(itemId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}

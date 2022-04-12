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
import com.estore.api.estoreapi.model.Message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.misusing.NullInsteadOfMockException;
import org.mockito.internal.matchers.Null;

/**
 * Test the Item File DAO class
 * 
 * @author SWEN Faculty
 * @author team 8
 */
@Tag("Persistence-tier")
public class MessageFileDAOTest {
    MessageFileDAO messageFileDAO;
    Message[] testMessages;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupMessageFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testMessages = new Message[3];
        testMessages[0] = new Message(99,"admin", "you pay me now");
        testMessages[1] = new Message(100,"axm9912", "no thanks");
        testMessages[2] = new Message(101,"tbh7070", "lets run...");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the item array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter2.txt"),Message[].class))
                .thenReturn(testMessages);
        messageFileDAO = new MessageFileDAO("doesnt_matter2.txt",mockObjectMapper);
    }

    @Test
    public void testGetMessages() {
        // Invoke
        Message[] messages = messageFileDAO.getMessages();

        // Analyze
        assertEquals(messages.length,testMessages.length);
        for (int i = 0; i < testMessages.length;++i)
            assertEquals(messages[i],testMessages[i]);
    }



    @Test
    public void testGetMessage() {

        Message message = messageFileDAO.getMessage(99);

        // Analzye
        assertEquals(message,testMessages[0]);
    }

    @Test
    public void testDeleteMessage() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> messageFileDAO.deleteMessage(99),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test heroes array - 1 (because of the delete)
        // Because heroes attribute of HeroFileDAO is package private
        // we can access it directly
        assertEquals(messageFileDAO.messages.size(),testMessages.length-1);
    }

    @Test
    public void testCreateMessage() {
        // Setup
        Message mess = new Message(102,"pft3033", "how much is a painting");

        // Invoke
        Message result = assertDoesNotThrow(() -> messageFileDAO.createMessage(mess),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Message actual = messageFileDAO.getMessage(mess.getId());
        assertEquals(actual.getId(),mess.getId());
        assertEquals(actual.getName(),mess.getName());
        assertEquals(actual.getMessage(),mess.getMessage());
    }

    // @Test
    // public void testSaveException() throws IOException{
    //     doThrow(new IOException())
    //         .when(mockObjectMapper)
    //             .writeValue(any(File.class),any(Message[].class));

    //     Message message = new Message(90,"admin", "you pay me now");
    //      NullInsteadOfMockException var;

    //     assertThrows(NullInsteadOfMockException,
    //                     () -> messageFileDAO.createMessage(message),
    //                     "IOException not thrown");
    // }


    @Test
    public void testGetMessageNotFound() {

         // Invoke
         Message message = messageFileDAO.getMessage(989);

         // Analyze
         assertEquals(message,null);
    }

    @Test
    public void testDeleteMessageNotFound() {
         // Invoke
         boolean result = assertDoesNotThrow(() -> messageFileDAO.deleteMessage(989),
         "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(messageFileDAO.messages.size(),testMessages.length);
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
                .readValue(new File("doesnt_matter2.txt"),Message[].class);

        // Invoke & Analyze
        assertThrows(NullPointerException.class,
                        () -> new ItemFileDAO("doesnt_matter2.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}

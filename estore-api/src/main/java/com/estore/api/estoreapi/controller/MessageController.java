package com.estore.api.estoreapi.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.MessageDAO;
import com.estore.api.estoreapi.model.Message;

/**
 * handles the rest api requests for the message resource.
 * 
 * {@literal @}RestController spring annotation identifies this class as a rest api
 * method handler to the spring framework.
 * 
 * @author alex martinez of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */

@RestController
@RequestMapping("messages")
public class MessageController {
    private static final Logger LOG = Logger.getLogger(MessageController.class.getName());
    private MessageDAO messageDAO;

    /**
     * creates a rest api controller to reponds to requests.
     * 
     * @param messageDao the {@link messageDAO message data access object} to perform CRUD operations
     */
    public MessageController(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    /**
     * responds to the GET request for a {@linkplain Message message} for the given id.
     * 
     * @param id The id used to locate the {@link Message message}
     * 
     * @return ResponseEntity with {@link Message message} object and HTTP status of OK if found
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author alex martinez
     */
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable int id) {
        LOG.info("GET /messages/" + id);
        try {
            Message message = messageDAO.getMessage(id);
            if (message != null)
                return new ResponseEntity<Message>(message, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Message messages}
     * 
     * @return ResponseEntity with array of {@link Message message} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author Alex Martinez
     */
    @GetMapping("")
    public ResponseEntity<Message[]> getMessages() {
        LOG.info("GET /messages");
        try{
            Message[] messages = messageDAO.getMessages();
            if (messages != null)
                return new ResponseEntity<Message[]>(messages, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * responds to the GET request for all {@linkplain Message messages} whose name contains
     * the text in name.
     * 
     * @param name the name parameter which contains the text used to find the {@link Message messages}
     * 
     * @return ResponseEntity with array of {@link Message message} objects (may be empty) and
     * HTTP status of OK
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author alex martinez
     */
    @GetMapping("/")
    public ResponseEntity<Message[]> searchMessages(@RequestParam String group) {
        LOG.info("GET /messages/?group= " + group);
        try {
            Message[] messages = messageDAO.findMessages(group);
            if (messages != null){
                
                return new ResponseEntity<Message[]>(messages, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * creates a {@linkplain Item item} with the provided item object.
     * 
     * @param item - The {@link Item item} to create
     * 
     * @return ResponseEntity with created {@link Item item} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Item item} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author Kara Kolodinsky
     */
    @PostMapping("")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        LOG.info("POST /messages/ ");
        try {
            Message created_message = messageDAO.createMessage(message);
            if (created_message != null){
                return new ResponseEntity<Message>(created_message,HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<Message>(HttpStatus.CONFLICT);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //kk was here :o)
    }


    /**
     * Deletes a {@linkplain Item item} with the given id
     * 
     * @param id The id of the {@link Item item} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * @author Jonathan Campbell
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable int id) {
        LOG.info("DELETE /messages/" + id);
        try {
            if(messageDAO.deleteMessage(id)) {
                return new ResponseEntity<Message>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

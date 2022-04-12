package com.estore.api.estoreapi.persistence;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Message;

/**
 * Implements the functionality for JSON file-based peristance for Items
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author kara kolodinsky of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
@Component
public class MessageFileDAO implements MessageDAO {
    private static final Logger LOG = Logger.getLogger(MessageFileDAO.class.getName());
    Map<Integer,Message> messages;   // Provides a local cache of the item objects
    private ObjectMapper objectMapper;  // Provides conversion between Item
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new item
    private String filename;    // Filename to read from and write to

    /**
     * Creates an Item File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public MessageFileDAO(@Value("${messages.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename= filename;
        this.objectMapper = objectMapper;
        load();  // load the items from the file
    }

    /**
     * Generates the next id for a new {@linkplain Item item}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Item items} from the tree map
     * 
     * @return  The array of {@link Item items}, may be empty
     */
    private Message[] getMessagesArray() {
        return getMessagesArray(null);
    }

    /**
     * generates an array of {@linkplain Item items} from the tree map for any
     * {@linkplain Item items} that contains the text specified by containsText.
     * if containsText is null, the array contains all of the {@linkplain Item items}
     * in the tree map
     * 
     * @return the array of {@link Item items}, may be empty.
     */
    private Message[] getMessagesArray(String containsText) { // if containsText == null, no filter
        ArrayList<Message> messageArrayList = new ArrayList<>();

        for (Message message : messages.values()) {
            if (containsText == null || message.getName().toLowerCase().contains(containsText.toLowerCase())) {
                messageArrayList.add(message);
            }
        }

        Message[] messageArray = new Message[messageArrayList.size()];
        messageArrayList.toArray(messageArray);
        return messageArray;
    }

    /**
     * saves the {@linkplain Item items} from the map into the file as an array of JSON objects.
     * 
     * @return true if the {@link Item items} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Message[] messageArray = getMessagesArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),messageArray);
        return true;
    }

    /**
     * loads {@linkplain Item item} from the JSON file into the map;
     * also sets next id to one more than the greatest id found in the file.
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        messages = new TreeMap<>();
        nextId = 0;

        // deserializes the JSON objects from the file into an array of items;
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file.
        Message[] messageArray = objectMapper.readValue(new File(filename),Message[].class);

        // add each item to the tree map and keep track of the greatest id
        for (Message message : messageArray) {
            messages.put(message.getId(),message);
            if (message.getId() > nextId)
                nextId = message.getId();
        }
        // make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Message[] getMessages() {
        synchronized(messages) {
            return getMessagesArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Message[] findMessages(String containsText) {
        synchronized(messages) {
            return getMessagesArray(containsText);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Message getMessage(int id) {
        synchronized(messages) {
            if (messages.containsKey(id))
                return messages.get(id);
            else
                return null;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Message createMessage(Message message) throws IOException {
        synchronized(messages) {
            // we create a new item object because the id field is immutable
            // and we need to assign the next unique id
            if (nameExists(message.getName())== false){
                Message newMessage = new Message(nextId(),message.getName(), message.getMessage());
                messages.put(newMessage.getId(),newMessage);
                save(); // may throw an IOException
                return newMessage;
            }
            else {
                return null;
            }
            
        }
    }


   /**
    ** {@inheritDoc}
     */
    @Override
    public boolean deleteMessage(int id) throws IOException {
        synchronized(messages) {
            if (messages.containsKey(id)) {
                messages.remove(id);
                return save();
            }
            else
                return false;
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public boolean nameExists(String name) throws IOException {
    synchronized(messages) {
        for (Message message_name: messages.values()){
            if (message_name.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
}
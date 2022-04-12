package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.User;

/**
 * Implements the functionality for JSON file-based peristance for Users
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Alex Hetrick
 */
@Component
public class UserFileDAO implements UserDAO {
    Map<Integer, User> users;           // Provides a local cache of the item objects
    private ObjectMapper objectMapper;  // Provides conversion between User
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;          // The next Id to assign to a new User
    private String filename;            // Filename to read from and write to

    /**
     * Creates an User File Data Access Object
     * 
     * @param filename      Filename to read from and write to
     * @param objectMapper  Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public UserFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the Users from the file
    }

    /**
     * Generates the next id for a new {@linkplain User user}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain User users} from the tree map
     * 
     * @return  The array of {@link User users}, may be empty
     */
    private User[] getUsersArray() {
        ArrayList<User> userArrayList = new ArrayList<>();

        for (User user : users.values()) {
            userArrayList.add(user);
        }

        User[] userArray = new User[userArrayList.size()];
        userArrayList.toArray(userArray);
        return userArray;
    }

    /**
     * Saves the {@linkplain User users} from the map into the file as an array of JSON objects.
     * 
     * @return true if the {@link User users} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        User[] userArray = getUsersArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),userArray);
        return true;
    }

    /**
     * Loads {@linkplain User user} from the JSON file into the map;
     * also sets next id to one more than the greatest id found in the file.
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        users = new TreeMap<>();
        nextId = 0;

        // deserializes the JSON objects from the file into an array of Users;
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file.
        User[] userArray = objectMapper.readValue(new File(filename), User[].class);

        // add each User to the tree map and keep track of the greatest id
        for (User user : userArray) {
            users.put(user.getId(), user);
            if (user.getId() > nextId)
                nextId = user.getId();
        }
        // make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User[] getUsers() {
        synchronized(users) {
            return getUsersArray();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(int id) {
        synchronized(users) {
            if (users.containsKey(id))
                return users.get(id);
            else
                return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findUser(String username) {
        synchronized(users) {
            User[] userArray = getUsersArray();
            for (User user : userArray)
            {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }

            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(User user) throws IOException {
        synchronized(users) {
            // We create a new user object because the id field is immutable
            // and we need to assign the next unique id
            if (usernameExists(user.getUsername()) == false && user.getUsername() != "" && user.getPassword() != ""){
                User newUser = new User(nextId(), user.getUsername(), user.getPassword(), user.getCart(),"../../assets/images/avatar.jpg");
                users.put(newUser.getId(), newUser);
                save(); // may throw an IOException
                return newUser;
            }
            else {
                return null;
            }
            
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User updateUser(User user) throws IOException {
        synchronized(users) {
            if (users.containsKey(user.getId()) == false)
                return null;  // item does not exist

                users.put(user.getId(), user);
            save(); // may throw an IOException
            return user;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteUser(int id) throws IOException {
        synchronized(users) {
            if (users.containsKey(id)) {
                users.remove(id);
                return save();
            }
            else
                return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean usernameExists(String username) throws IOException {
        synchronized(users) {
            for (User stock_item: users.values()){
                if (stock_item.getUsername().equals(username)){
                    return true;
                }
            }
            return false;
        }
    }
    
}

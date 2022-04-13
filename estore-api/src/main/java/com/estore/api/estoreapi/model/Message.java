package com.estore.api.estoreapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * represents an Item entity.
 *
 * @author alex martinez of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
public class Message implements Comparable<Message>{

    // Package private for tests
    static final String STRING_FORMAT = "Message [id=%d, name=%s, message=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("message") private String mess;

    /**
     * create an item with the given id, name, and stock.
     * @param id the id of the item
     * @param name the name of the item
     * @param message the stock of the item.
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Message(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("message") String message) {
        this.id = id;
        this.name = name;
        this.mess = message;
    }

    /**
     * retrieves the id of the message
     * @return The id of the message
     */
    public int getId() {return id;}

    /**
     * sets the name of the message; necessary for JSON object to Java object deserialization.
     * @param name The name of the message
     */
    public void setName(String name) {this.name = name;}

    /**
     * retrieves the name of the message
     * @return the name of the message
     */
    public String getName() {return name;}

    /**
     * retrieves the message of the message
     * @return the message of the message
     */
    public String getMessage() {return mess;}

    /**
     * sets the message of the message.
     * @param message The message of the message.
     */
    public void setMessage(String message) {this.mess = message;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,mess);
    }
    @Override
    public int compareTo(Message obj){
        return this.mess.compareTo(obj.mess);
  }
    
}
package com.estore.api.estoreapi.model;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * represents an Announcement entity.
 *
 * @author jonathan campbell of team 8
 */
public class Announcement {
    @JsonProperty("id") private int id;
    @JsonProperty("title") private String title;
    @JsonProperty("message") private String message;
    static final String STRING_FORMAT = "Announcement [title=%s, message=%s]";

    /**
     * create an announcement with the given title and message
     * @param title the title of the annoncement
     * @param message the message of the announcement
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Announcement(@JsonProperty("id") int id, @JsonProperty("title") String title, @JsonProperty("message") String message){
        this.id = id;
        this.title = title;
        this.message = message;
    }

    /**
     * retrieves the id of the announcement
     * @return the id of the announcment
     */
    public int getId() {return id;}

    /**
     * retrieves the title of the announcement
     * @return the title of the announcment
     */
    public String getTitle() {return title;}

    /**
     * retrieves the message of the announcement
     * @return the message of the announcement
     */
    public String getMessage() {return message;}

    /**
     * sets the title of the announcment
     * @param title The title of the announcment
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * sets the message of the announcment
     * @param messsage The message of the announcment
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, title, message);
    }
}

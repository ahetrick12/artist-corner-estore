package com.estore.api.estoreapi.model;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an User entity.
 *
 * @author Alex Hetrick
 */
public class User {
    // Package private for tests
    static final String STRING_FORMAT = "User [id=%s username=%s, password=%s, cart=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("username") private String username;
    @JsonProperty("password") private String password;
    @JsonProperty("cart") private CartItem[] cart;

     /**
     * Create an user with a given username and password.
     * @param username the username of the user
     * @param password the password of the user
     * @param cart the user's shopping cart
     * @param image the user's image
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public User(@JsonProperty("id") int id, @JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("cart") CartItem[] cart) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cart = cart;
    }

    /**
    * retrieves the id of the item
    * @return The id of the item
    */
    public int getId() {return id;}

    /**
     * Sets the username of the user
     * @param username The username of the user
     */
    public void setUsername(String username) {this.username = username;}

    /**
     * Retrieves the username of the user
     * @return the username of the user
     */
    public String getUsername() {return username;}

    /**
     * Sets the password of the user
     * @param password The password of the user
     */
    public void setPassword(String password) {this.password = password;}

    /**
     * Retrieves the password of the user
     * @return the password of the user
     */
    public String getPassword() {return password;}

    /**
     * Sets the cart of the user
     * @param cart The cart of the user
     */
    public void setCart(CartItem[] cart) {this.cart = cart;}

    /**
     * Retrieves the cart of the user
     * @return the cart of the user
     */
    public CartItem[] getCart() {return cart;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, username, password, cart);
    }
}

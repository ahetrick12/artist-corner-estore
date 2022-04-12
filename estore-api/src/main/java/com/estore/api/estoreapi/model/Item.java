package com.estore.api.estoreapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * represents an Item entity.
 *
 * @author kara kolodinsky of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
public class Item implements Comparable<Item>{
    private static final Logger LOG = Logger.getLogger(Item.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Item [id=%d, name=%s, stock=%s, price=%s, image=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("stock") private int stock;
    @JsonProperty("price") private float price;
    @JsonProperty("image") private String image;

    /**
     * create an item with the given id, name, and stock.
     * @param id the id of the item
     * @param name the name of the item
     * @param stock the stock of the item.
     * @param price the price of the item.
     * @param image link to the item's image.
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Item(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("stock") int stock,  @JsonProperty("price") float price, @JsonProperty("image") String image) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.image = image;
    }

    /**
     * retrieves the id of the item
     * @return The id of the item
     */
    public int getId() {return id;}

    /**
     * sets the name of the item; necessary for JSON object to Java object deserialization.
     * @param name The name of the item
     */
    public void setName(String name) {this.name = name;}

    /**
     * retrieves the name of the item
     * @return the name of the item
     */
    public String getName() {return name;}

    /**
     * retrieves the price of the item
     * @return the price of the item
     */
    public float getPrice() {return price;}

     /**
     * retrieves the stock of the item
     * @return the stock of the item
     */
    public int getStock() {return stock;}

    /**
     * sets the stock of the item.
     * @param stock The stock of the item.
     */
    public void setStock(int stock) {this.stock = stock;}

     /**
     * retrieves the image of the item
     * @return the image of the item
     */
    public String getImage() {return image;}

    /**
     * sets the image of the item.
     * @param image The image of the item.
     */
    public void setImage(String image) {this.image = image;}
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,stock,price,image);
    }
    @Override
    public int compareTo(Item obj){
        return this.name.compareTo(obj.name);
  }
    /**
     * sets the price of the item.
     * @param price The price of the item.
     */

    public void setPrice(float price) {
        this.price = price;
    }
}
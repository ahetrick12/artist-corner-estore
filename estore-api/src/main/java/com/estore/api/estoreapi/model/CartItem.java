package com.estore.api.estoreapi.model;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * represents a CartItem entity.
 *
 * @author Team 8
 */
public class CartItem {
    @JsonProperty("small") private int s;
    @JsonProperty("medium") private int m;
    @JsonProperty("large") private int l;
    @JsonProperty("xlarge") private int xl;
    @JsonProperty("x920") private int x920;
    @JsonProperty("x1930") private int x1930;
    @JsonProperty("item") private Item item;
    static final String STRING_FORMAT = "CartItem [item=%s, small=%s, medium=%s, large=%s, xlarge=%s, x920=%s, x1930=%s]";

        /**
     * create an item with the given id, name, and stock.
     * @param item the item to be added to the cart
     * @param quantity the quantity of the item
     * @param s the quantity of small items
     * @param m the quantity of medium items
     * @param l the quantity of large items
     * @param xl the quantity of extra large items
     * @param x920 the quantity of x920 items
     * @param x1930 the quantity of x1930 items
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public CartItem(@JsonProperty("item") Item item, @JsonProperty("small") int s, @JsonProperty("medium") int m, @JsonProperty("large") int l, @JsonProperty("xlarge") int xl, @JsonProperty("x920") int x920, @JsonProperty("x1930") int x1930) {
        this.item = item;
        this.s = s;
        this.m = m;
        this.l = l;
        this.xl = xl;
        this.x920 = x920;
        this.x1930 = x1930;
    }

    /**
     * retrieves the item of the cartitem
     * @return the item of the cartitem
     */
    public Item getItem() {return item;}

    /**
     * retrieves the quantity of small items
     * @return the quantity of small items
     */
    public int getSmall() {return s;}

    /**
     * retrieves the quantity of medium items
     * @return the quantity of medium items
     */
    public int getMedium() {return m;}

    /**
     * retrieves the quantity of large items
     * @return the quantity of large items
     */
    public int getLarge() {return l;}

    /**
     * retrieves the quantity of extra large items
     * @return the quantity of extra large items
     */
    public int getXLarge() {return xl;}

    /**
     * retrieves the quantity of x920 items
     * @return the quantity of x920 items
     */
    public int getx920() {return x920;}

    /**
     * retrieves the quantity of x1930 items
     * @return the quantity of x1930 items
     */
    public int getX1930() {return x1930;}

    /**
     * sets the quantity of small items.
     * @param s the quantity of small items.
     */
    public void setSmall(int s) {this.s = s;}

    /**
     * sets the quantity of medium items.
     * @param m the quantity of medium items.
     */
    public void setMedium(int m) {this.m = m;}

    /**
     * sets the quantity of large items.
     * @param l the quantity of large items.
     */
    public void setLarge(int l) {this.l = l;}

    /**
     * sets the quantity of extra large items.
     * @param xl the quantity of extra large items.
     */
    public void setXLarge(int xl) {this.xl = xl;}

    /**
     * sets the quantity of x920 items.
     * @param x920 the quantity of x920 items.
     */
    public void setx920(int x920) {this.x920 = x920;}

    /**
     * sets the quantity of x1930 items.
     * @param x1930 the quantity of x1930 items.
     */
    public void setX1930(int x1930) {this.x1930 = x1930;}

    public boolean compareItem(Item item2){
        if (this.item.equals(item2)){
            return true;
        } else return false;
    }

      /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, item.toString(), s, m, l, xl, x920, x1930);
    }
    /**
     * sets the Item of the cartitem.
     * @param item The item of the cartitem.
     */
    public void setItem(Item item) {
        this.item = item;
    }
}

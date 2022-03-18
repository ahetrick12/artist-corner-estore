package com.estore.api.estoreapi.model;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * represents a CartItem entity.
 *
 * @author kara kolodinsky of team 8
 */
public class CartItem {
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("item") private Item item;
    static final String STRING_FORMAT = "CartItem [item=%s, quantity=%s]";

        /**
     * create an item with the given id, name, and stock.
     * @param item the item to be added to the cart
     * @param quantity the quantity of the item
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public CartItem(@JsonProperty("item") Item item, @JsonProperty("quantity") int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * retrieves the item of the cartitem
     * @return the item of the cartitem
     */
    public Item getItem() {return item;}

    /**
     * retrieves the quantity of the cartitem
     * @return the quantity of the cartitem
     */
    public int getQuantity() {return quantity;}

        /**
     * sets the quantity of the item.
     * @param quantity The quantity of the item.
     */
    public void setQuantity(int quantity) { this.quantity = quantity;}
    /**
     * increments the cartitem's quantity by 1.
     */
    public void incrementQuantity(){this.quantity += 1;}

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
        return String.format(STRING_FORMAT,item.toString(), quantity);
    }
    /**
     * sets the Item of the cartitem.
     * @param item The item of the cartitem.
     */
    public void setItem(Item item) {
        this.item = item;
    }
}

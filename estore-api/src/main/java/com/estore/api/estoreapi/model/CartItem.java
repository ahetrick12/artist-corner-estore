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


    public CartItem(@JsonProperty("item") Item item, @JsonProperty("quantity") int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {return item;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) { this.quantity = quantity;}

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
}

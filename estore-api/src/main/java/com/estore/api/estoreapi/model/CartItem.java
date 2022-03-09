package com.estore.api.estoreapi.model;
import com.estore.api.estoreapi.*;

import com.estore.api.estoreapi.model.Item;

/**
 * represents a CartItem entity.
 *
 * @author kara kolodinsky of team 8
 */
public class CartItem {
    private int quantity;
    private Item item;

    public CartItem(Item item, int quantity){
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
}

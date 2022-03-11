package com.estore.api.estoreapi.model;
import com.estore.api.estoreapi.*;
import java.util.ArrayList;
import com.estore.api.estoreapi.model.CartItem;
import java.util.List;
/**
 * represents a Cart entity.
 *
 * @author kara kolodinsky of team 8
 */

public class Cart {
    private List<CartItem> cart;
    public Cart() {
        List<CartItem> cart = new ArrayList<CartItem>();
    }
    
    public  List<CartItem> getCart(){ return cart;}

    public void addToCart(Item item){
        if (getCartItems().contains(item)){
            int index = getIndex(item);
            cart.get(index).incrementQuantity();
        }
        else{
            cart.add(new CartItem(item, 1));
        }
        
    }

    public List<Item> getCartItems(){
        List<Item> cart_items = new ArrayList<Item>();
        for (int i= 0; i<cart.size() ;i++){
            cart_items.add(cart.get(i).getItem());
        }
        return cart_items;
    }

    public int getCartSize(){
        int size = 0;
        for (int i= 0; i<cart.size() ;i++){
            size += cart.get(i).getQuantity();
        }
        return size;
    }

    public int getIndex(Item item){
        List<Item> items = getCartItems();
        return items.indexOf(item);
}


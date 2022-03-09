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
        //this is kinda super time intensive depending on cart size. O(N)
        // feel free to use dictionaries/whatever to make it less so, but
        // right now i'm just making sure it works. kk
        for (int i = 0; i<cart.size() ;i++){
            if (cart.get(i).getItem() == item){
                cart.get(i).incrementQuantity();
            }
        }
        
    }
}


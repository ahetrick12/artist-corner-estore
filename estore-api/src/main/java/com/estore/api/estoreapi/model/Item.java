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
    static final String STRING_FORMAT = "Item [id=%d, name=%s, stock=%s, price=%s, S=%s,m=%s,L=%s,XL=%s,x920=%s,x1930=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("stock") private int stock;
    @JsonProperty("price") private float price;
    @JsonProperty("S") private int S;
    @JsonProperty("M") private int M;
    @JsonProperty("L") private int L;
    @JsonProperty("XL") private int XL;
    @JsonProperty("x920") private int x920;
    @JsonProperty("x1930") private int x1930;

    /**
     * create an item with the given id, name, and stock.
     * @param id the id of the item
     * @param name the name of the item
     * @param stock the stock of the item.
     * @param price the price of the item.
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Item(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("stock") int stock,  @JsonProperty("price") float price,
                 @JsonProperty("S") int S,@JsonProperty("M") int M,@JsonProperty("L") int L,@JsonProperty("XL") int XL,@JsonProperty("x920") int x920,@JsonProperty("x1930") int x1930) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.S = S;
        this.M = M;
        this.L = L;
        this.XL = XL;
        this.x920 = x920;
        this.x1930 = x1930;
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

    public int getS(){
        return S;
    }

    public void setS(int small){
        this.S = small;
    }
    public int getM(){
        return M;
    }

    public void setM(int med){
        this.M = med;
    }
    public int getL(){
        return L;
    }

    public void setL(int larg){
        this.L = larg;
    }
    public int getXL(){
        return XL;
    }

    public void setXL(int extra){
        this.XL = extra;
    }
    public int getx920(){
        return x920;
    }

    public void setx920(int nin){
        this.x920 = nin;
    }
    public int getx1930(){
        return x1930;
    }

    public void setx1930(int thir){
        this.x1930 = thir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,stock,price);
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
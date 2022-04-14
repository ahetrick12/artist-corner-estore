package com.estore.api.estoreapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * represents an Item entity.
 *
 * @author kara kolodinsky of team 8
 * @author code heavily based on heroes-api by SWEN Faculty
 */
public class Item implements Comparable<Item>{

    // Package private for tests
    static final String STRING_FORMAT = "Item [id=%d, name=%s, stock=%s, price=%s, s=%s, m=%s, l=%s, xl=%s, x920=%s, x1930=%s, imageLink=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("stock") private int stock;
    @JsonProperty("price") private float price;
    @JsonProperty("imageLink") private String imageLink;
    @JsonProperty("s") private int S;
    @JsonProperty("m") private int M;
    @JsonProperty("l") private int L;
    @JsonProperty("xl") private int XL;
    @JsonProperty("x920") private int x920;
    @JsonProperty("x1930") private int x1930;

    /**
     * create an item with the given id, name, stock, and price.
     * @param id the id of the item
     * @param name the name of the item
     * @param stock the stock of the item.
     * @param price the price of the item.
     * @param imageLink link to the item's image.
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */

    public Item(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("stock") int stock,  @JsonProperty("price") float price,
                 @JsonProperty("s") int S,@JsonProperty("m") int M,@JsonProperty("l") int L,@JsonProperty("xl") int XL,@JsonProperty("x920") int x920,@JsonProperty("x1930") int x1930,  @JsonProperty("imageLink") String imageLink) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.imageLink = imageLink;
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
     * retrieves the name of the item
     * @return the name of the item
     */
    public String getName() {return name;}
    
    /**
     * sets the name of the item; necessary for JSON object to Java object deserialization.
     * @param name The name of the item
     */
    public void setName(String name) {this.name = name;}

    /**
     * retrieves the price of the item
     * @return the price of the item
     */
    public float getPrice() {return price;}

    /**
     * sets the price of the item.
     * @param price The price of the item.
     */
    public void setPrice(float price) {
        this.price = price;
    }

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
     * retrieves the imageLink of the item
     * @return The imageLink of the item
     */
    public String getImageLink() {return imageLink;}

    /**
     * sets the stock of the item.
     * @param stock The stock of the item.
     */
    public void setImageLink(String imageLink) {this.imageLink = imageLink;}
     /**
     * retrieves the small stock of the item
     * @return the small stock of the item
     */
    public int getS(){
        return S;
    }
    /**
     * sets the small stock of the item.
     * @param small The small stock of the item.
     */
    public void setS(int small){
        this.S = small;
    }
    
    /**
     * retrieves medium stock of the item
     * @return the medium stock of the item
     */
    public int getM(){
        return M;
    }
    /**
     * sets the medium stock of the item.
     * @param med The medium stock of the item.
     */
    public void setM(int med){
        this.M = med;
    }

     /**
     * retrieves the stock of the item
     * @return the stock of the item
     */
    public int getL(){
        return L;
    }
    /**
     * sets the large stock of the item.
     * @param large The large stock of the item.
     */
    public void setL(int larg){
        this.L = larg;
    }

     /**
     * retrieves the XL stock of the item
     * @return the XL stock of the item
     */
    public int getXL(){
        return XL;
    }

    /**
     * sets the XL stock of the item.
     * @param extra The XL stock of the item.
     */
    public void setXL(int extra){
        this.XL = extra;
    }

     /**
     * retrieves the x920 stock of the item
     * @return the x920 stock of the item
     */
    public int getx920(){
        return x920;
    }

    /**
     * sets the x920 stock of the item.
     * @param nin The x920 stock of the item.
     */
    public void setx920(int nin){
        this.x920 = nin;
    }
     /**
     * retrieves the x1930 stock of the item
     * @return the x1930 stock of the item
     */
    public int getx1930(){
        return x1930;
    }
    /**
     * sets the x1930 stock of the item.
     * @param thir The x1930 stock of the item.
     */
    public void setx1930(int thir){
        this.x1930 = thir;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,stock,price, S, M, L, XL, x920, x1930, imageLink);
    }

     /*
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Item obj){
        return this.name.compareTo(obj.name);
    }
}
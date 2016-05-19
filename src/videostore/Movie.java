package videostore;

import java.io.Serializable;

public class Movie implements Serializable {

    protected String name;
    protected int movieId;
    protected float price;
    protected int quantity = 0;

    public Movie(int movieId, String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.movieId = movieId;
    }

    public void decreaseQuantity(int count) {
        quantity -= count;
    }

    public void increaseQuantity(int count) {
        quantity += count;
    }

    public void updatePrice(float price) {
        this.price = price;
    }

}
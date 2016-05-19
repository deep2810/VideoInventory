package videostore;

import java.io.*;
import java.util.HashMap;

public class Inventory extends AbstractInventory implements Serializable {

    private HashMap<Integer, Movie> movieIdMap = new HashMap<>();
    private HashMap<String, Movie> movieNameMap = new HashMap<>();
    private static int movieCounter = 0;
    private static final long serialVersionUID = -4800495704707931382L;

    @Override
    public void addNewMovie(String movieName, float price, int quantity) {
        if (!movieNameMap.keySet().contains(movieName)) {
            movieCounter++;
            Movie movie = new Movie(movieCounter, movieName, price, quantity);
            movieIdMap.put(movieCounter, movie);
            movieNameMap.put(movieName, movie);
            
        }
    }

    @Override
    public void sell(String movieName, int quantity) {
        Movie movie = movieNameMap.get(movieName);
        movie.decreaseQuantity(2);
    }

    @Override
    public void increaseQuantity(String movieName, int quantity) {
        Movie movie = movieNameMap.get(movieName);
        movie.increaseQuantity(quantity);
    }

    @Override
    public void updatePrice(String movieName, float price) {
        Movie movie = movieNameMap.get(movieName);
        movie.updatePrice(price);
    }

    @Override
    public float getPrice(String movieName) {
        return movieNameMap.get(movieName).price;
    }

    @Override
    public float getPrice(int movieId) {
        return movieIdMap.get(movieId).price;
    }

    @Override
    public int getQuantity(String movieName) {
        return movieNameMap.get(movieName).quantity;
    }

    @Override
    public int getQuantity(int movieId) {
        return movieIdMap.get(movieId).quantity;
    }
}
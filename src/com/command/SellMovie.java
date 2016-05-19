package com.command;

import videostore.*;

public class SellMovie extends Command {

    private String movieName;
    private int quantity;

    public SellMovie(String movieName, int quantity) {
        this.movieName = movieName;
        this.quantity = quantity;
        write(".");
    }

    @Override
    public void execute(AbstractInventory inventory) {
        inventory.sell(movieName, quantity);
    }

}
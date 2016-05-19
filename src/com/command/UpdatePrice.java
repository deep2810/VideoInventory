package com.command;

import videostore.AbstractInventory;
import videostore.Inventory;

public class UpdatePrice extends Command {

    private String movieName;
    private float price;

    public UpdatePrice(String movieName, float price) {
        this.movieName = movieName;
        this.price = price;
        write(".");
    }

    @Override
    public void execute(AbstractInventory inventory) {
        inventory.updatePrice(movieName, price);
    }
}
package com.command;

import videostore.AbstractInventory;
import videostore.Inventory;

public class UpdateQuantity extends Command {

    private String movieName;
    private int quantity;

    public UpdateQuantity(String movieName, int quantity) {
        this.movieName = movieName;
        this.quantity = quantity;
        write(".");
    }

    @Override
    public void execute(AbstractInventory inventory) {
        inventory.increaseQuantity(movieName, quantity);
    }

}
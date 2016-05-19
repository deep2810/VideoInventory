package com.command;

import videostore.*;

public class AddNewMovie extends Command{
    private String name;
    private float price;
    private int quantity;
    
    public AddNewMovie(String name,float price,int quantity)
    {
        this.name= name;
        this.price = price;
        this.quantity = quantity;
        write(".");
    }
    @Override
    public void execute(AbstractInventory inventory) {
        inventory.addNewMovie(name, price, quantity);
    }
    
}
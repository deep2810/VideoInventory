package com.test;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import videostore.InventoryDecorator;

public class CommandTest {

    private InventoryDecorator decorator;

    public CommandTest() {
    }

    @Before
    public void setUp() {
        decorator = new InventoryDecorator();
        decorator.addNewMovie("ironman1", (float) 2.23, 2);
        decorator.addNewMovie("ironman2", 22, 2);
        decorator.addNewMovie("ironman3", 22, 2);
    }

    @After
    public void tearDown() {
        File commandFile = new File("F:/eclipse/storage/VideoInventory/src/Command.txt");
        File mementoFile = new File("F:/eclipse/storage/VideoInventory/src/Memento.txt");
        if (mementoFile.exists()) {
            mementoFile.delete();
        }
        if (commandFile.exists()) {
            commandFile.delete();
        }
    }

    @Test
    public void sellCommand() {
        decorator.sell("ironman1", 2);
        assertEquals(0, decorator.getQuantity("ironman1"));
    }

    @Test
    public void changePriceCommand() {
        decorator.updatePrice("ironman1", (float) 0.02);
        assertEquals(0.02, decorator.getPrice("ironman1"), 0.0001);
    }

    @Test
    public void updateQuantityCommand() {
        decorator.increaseQuantity("ironman1", 50);
        assertEquals(52, decorator.getQuantity("ironman1"));
    }
}
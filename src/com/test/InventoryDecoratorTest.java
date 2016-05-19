package com.test;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import videostore.InventoryDecorator;

public class InventoryDecoratorTest {

    private InventoryDecorator decorator;

    public InventoryDecoratorTest() {

    }

    @Before
    public void setUp() {
        decorator = new InventoryDecorator();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void inventoryTest() {
        decorator.addNewMovie("ironman1", (float) 2.23, 2);
        decorator.addNewMovie("ironman2", 22, 2);
        decorator.addNewMovie("ironman3", 22, 2);
    }
    
//Focrefully cerating the memenot and command file to make it look like system crash...     
    private void createBackUpFiles() {
        String filePath = ".";
        File commandFile = new File("F:/eclipse/storage/VideoInventory/src/command.txt");
        File mementoFile = new File("F:/eclipse/storage/VideoInventory/src/Memento.txt");
        if (mementoFile.exists()) {
            mementoFile.delete();
        }
        if (commandFile.exists()) {
            commandFile.delete();
        }
        decorator = new InventoryDecorator();
        decorator.addNewMovie("ironman1", (float) 2.23, 2);
        decorator.addNewMovie("ironman2", 22, 2);
        decorator.addNewMovie("ironman3", 22, 2);
        decorator.updatePrice("ironman3", 10);
    }

    /*@Test
    public void inventoryRestoreTest() {

        createBackUpFiles();
        decorator = new InventoryDecorator();
        assertEquals(10, decorator.getPrice("ironman3"), 0.001);
        decorator.updatePrice("ironman1", 100);
        //assume system is crashed and new invenrotyDecorator is created....
        decorator = new InventoryDecorator();
        //System is restored from previous state.... 
        //check whether left over commands are executed or not.....
        assertEquals(100, decorator.getPrice("ironman1"), 0.0001);
    }*/
}
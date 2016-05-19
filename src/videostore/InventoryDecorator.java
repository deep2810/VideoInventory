package videostore;

import com.command.*;
import com.command.AddNewMovie;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventoryDecorator extends AbstractInventoryDecorator {

    private static int commandCounter = 0;
    private CareTaker careTaker = new CareTaker();

    public InventoryDecorator() {
        inventory = new Inventory();
        restoreInventory();
    }

    private void restoreInventory() {

        File commandFile = new File("F:/eclipse/storage/VideoInventory/src/command.txt");
        if (commandFile.exists()) {
            restoreMemento(".");
            executeCommands();
        }
    }

    private void restoreMemento(String inputFile) {
        File mementoFile = new File("F:/eclipse/storage/VideoInventory/src/Memento.txt");
        if (mementoFile.exists()) 
        {
            Memento memento = careTaker.read();
            inventory = memento.getInventory();
        } 
        else {
            inventory = new Inventory();
        }
    }

    private void executeCommands() {
        FileInputStream fileRead = null;
        ObjectInputStream input = null;
        String filePath = ".";
        Command command;
        try {
            fileRead = new FileInputStream("F:/eclipse/storage/VideoInventory/src/Command.txt");
            input = new ObjectInputStream(fileRead);
            while ((command = (Command) input.readObject()) != null) {
                command.execute(inventory);
            }
        } 
        catch (EOFException ex) {
        } 
        catch (IOException | ClassNotFoundException ex ) {
            Logger.getLogger(InventoryDecorator.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally 
        {
            try {
                input.close();
                fileRead.close();
            } catch (IOException ex) {
                Logger.getLogger(Memento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        File commandFile = new File("F:/eclipse/storage/VideoInventory/src/command.txt");
        commandFile.deleteOnExit();
        careTaker.set(saveInventoryToMemento());
        careTaker.write();
    }

    public Memento saveInventoryToMemento() {
        return new Memento(inventory);
    }

    private void checkCommandCounter() {
        commandCounter++;
        if (commandCounter == 3) {
            careTaker.set(saveInventoryToMemento());
            careTaker.write();
            commandCounter = 0;
        }
    }

    @Override
    public void addNewMovie(String movieName, float price, int quantity) {
        Command command = new AddNewMovie(movieName, price, quantity);
        command.execute(inventory);
        checkCommandCounter();
    }

    @Override
    public void sell(String movieName, int quantity) {
        Command command = new SellMovie(movieName, quantity);
        command.execute(inventory);
        checkCommandCounter();
    }

    @Override
    public void increaseQuantity(String movieName, int quantity) {
        Command command = new UpdateQuantity(movieName, quantity);
        command.execute(inventory);
        checkCommandCounter();
    }

    @Override
    public void updatePrice(String movieName, float price) {
        Command command = new UpdatePrice(movieName, price);
        command.execute(inventory);
        checkCommandCounter();
    }
}
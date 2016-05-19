package videostore;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CareTaker {

    private Memento currentMemento = new Memento();

    public void set(Memento state) {
        currentMemento = state.clone();
    }

    public Memento get() {
        return currentMemento;
    }

    public boolean write() {
        String filePath= ".";
        File sourceFile = new File("F:/eclipse/storage/VideoInventory/src/Memento.txt");
        File destinationFile = new File("F:/eclipse/storage/VideoInventory/src/Memento.txt.old");
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try 
        {
            copyFile(sourceFile, destinationFile);
            fileOut = new FileOutputStream("F:/eclipse/storage/VideoInventory/src/Memento.txt");
            out = new ObjectOutputStream(fileOut);
            out.writeObject(currentMemento);
            out.close();
            fileOut.close();
        } 
        catch (IOException ex) {
            copyFile(destinationFile, sourceFile);
            Logger.getLogger(Memento.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            destinationFile.delete();
        }
        File commandFile = new File("F:/eclipse/storage/VideoInventory/src/command.txt");
        commandFile.delete();
        return true;
    }

    private void copyFile(File sourceFile, File destFile) {
        if (sourceFile.exists() && destFile.exists()) {
            try {
                FileChannel source = new FileInputStream(sourceFile).getChannel();
                FileChannel destination = new FileOutputStream(destFile).getChannel();
                destination.transferFrom(source, 0, source.size());
                source.close();
                destination.close();
            } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(CareTaker.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex) {
                Logger.getLogger(CareTaker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Memento read() 
    {
        String filePath= ".";
        FileInputStream fileRead = null;
        ObjectInputStream input = null;
        try 
        {
            fileRead = new FileInputStream("F:/eclipse/storage/VideoInventory/src/Memento.txt");
            input = new ObjectInputStream(fileRead);
            currentMemento = (Memento) input.readObject();
        } 
        catch (Exception ex) {
            Logger.getLogger(Memento.class.getName()).log(Level.SEVERE, null, ex);
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
        return currentMemento;
    }

}
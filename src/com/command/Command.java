package com.command;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import videostore.AbstractInventory;
import videostore.*;

public abstract class Command implements Serializable {

    public abstract void execute(AbstractInventory inventory);

    public boolean write(String filePath) {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        File commandFile = null;
        File temporaryFile = null;
        try 
        {
            commandFile = new File("F:/eclipse/storage/VideoInventory/src/Command.txt");
            temporaryFile = new File("F:/eclipse/storage/VideoInventory/src/Command.txt.old");
            copyFile(commandFile, temporaryFile);
            if (commandFile.exists())
            {
                fileOut = new FileOutputStream("F:/eclipse/storage/VideoInventory/src/Command.txt", true);
                out = new NoHeaderObjectOutputStream(fileOut);
            } 
            else 
            {
                fileOut = new FileOutputStream("F:/eclipse/storage/VideoInventory/src/Command.txt");
                out = new ObjectOutputStream(fileOut);
            }
            out.writeObject(this);
            out.close();
            fileOut.close();
        } 
        catch (IOException ex) {
            copyFile(temporaryFile, commandFile);
            return false;
        } 
        finally {
            temporaryFile.delete();
        }
        return true;
    }

    private void copyFile(File sourceFile, File destFile) {
        if (sourceFile.exists() && destFile.exists())
        {
            try 
            {
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
}
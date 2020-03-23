package com.middlemind.Odroid;

import java.io.File;
import java.util.ArrayList;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;

/**
 * A worker thread that handles loading the Tyre game DAT file. Created on
 * August 1, 2015, 10:57 PM by Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class RunResourceLoad implements Runnable {

    /**
     * A boolean result of the loading process.
     */
    public boolean readResult;

    /**
     * A boolean result of the last read operation.
     */
    public boolean readComplete;

    /**
     * Helper variables for the read process.
     */
    public int readPos;

    /**
     * Helper variables for the read process.
     */
    public int readLen;
    //public int len;    
    public int loadMultiplier = 1000;
    //public int extraLoadLen = ((42 + 15 + 284) * extraLoadMultiplier); //direct file load steps + console image steps + regular image steps

    /**
     * An event handler for receiving update messages from the DAT loader.
     */
    public LoadResourceUpdateHandler update;

    public int tlen;
    public int i;
    public long slowDown;
    public boolean exitLoad;
    
    /**
     * A constructor that sets the thin load option, don't load binary image or
     * sound data yet, and sets the source byte array to parse.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public RunResourceLoad() {
        readPos = 0;
        readLen = 0;
        readResult = false;
        readComplete = false;
        exitLoad = false;
        slowDown = 0;
    }

    /**
     * A method for passing on LoadDatUpdate events.
     *
     * @param Update The handler subscribing to events.
     */
    public void SetUpdateHandler(LoadResourceUpdateHandler Update) {
        update = Update;
    }    
    
    /**
     * The run method for this worker thread.
     */
    @Override
    public void run() {
        readLen = 1;
        readPos = 1;
        readResult = false;
        readComplete = false;
        
        File asld = null;
        File adld = null;
        File[] srcFiles = null;
        ArrayList<File> clnFiles = null;
        ArrayList<File> asFiles = null;
        ArrayList<File> adFiles = null;
        int j = 0;
        
        try {
            asFiles = new ArrayList();
            adFiles = new ArrayList();            
            
            //load core auto loading audio files
            asld = new File(GameSettings.AUTO_SOUND_LOAD_DIR);
            if(asld.exists()) {
                srcFiles = asld.listFiles();
                clnFiles = new ArrayList();

                for(j = 0; j < srcFiles.length; j++) {
                    if(srcFiles[j].getName().charAt(0) != '.' && srcFiles[j].getName().toLowerCase().indexOf(".wav") != -1) {
                        clnFiles.add(srcFiles[j]);
                    }
                }

                asFiles.addAll(clnFiles);
                if(asFiles != null && asFiles.size() > 0) {
                    readLen = (asFiles.size() - 1) * loadMultiplier;
                }
            }
            
            //load core auto loading image files            
            adld = new File(GameSettings.AUTO_IMAGE_LOAD_DIR);
            if(adld.exists()) {
                srcFiles = adld.listFiles();
                clnFiles = new ArrayList();

                for(j = 0; j < srcFiles.length; j++) {
                    if(srcFiles[j].getName().charAt(0) != '.' && (srcFiles[j].getName().toLowerCase().indexOf(".png") != -1 || srcFiles[j].getName().toLowerCase().indexOf(".jpg") != -1 || srcFiles[j].getName().toLowerCase().indexOf(".bmp") != -1)) {
                        clnFiles.add(srcFiles[j]);
                    }
                }

                adFiles.addAll(clnFiles);
                if(adFiles != null && adFiles.size() > 0) {
                    readLen = (adFiles.size() - 1) * loadMultiplier;
                }
            }
             
            //load core auto loading audio files            
            asld = new File(GameSettings.PROGRAM_SOUND_LOAD_DIR);
            if(asld.exists()) {
                srcFiles = asld.listFiles();
                clnFiles = new ArrayList();

                for(j = 0; j < srcFiles.length; j++) {
                    if(srcFiles[j].getName().charAt(0) != '.' && srcFiles[j].getName().toLowerCase().indexOf(".wav") != -1) {
                        clnFiles.add(srcFiles[j]);
                    }
                }

                asFiles.addAll(clnFiles);                
                if(asFiles != null && asFiles.size() > 0) {
                    readLen = (asFiles.size() - 1) * loadMultiplier;
                }
            }
            
            //load core auto loading image files            
            adld = new File(GameSettings.PROGRAM_IMAGE_LOAD_DIR);
            if(adld.exists()) {
                srcFiles = adld.listFiles();
                clnFiles = new ArrayList();

                for(j = 0; j < srcFiles.length; j++) {
                    if(srcFiles[j].getName().charAt(0) != '.' && (srcFiles[j].getName().toLowerCase().indexOf(".png") != -1 || srcFiles[j].getName().toLowerCase().indexOf(".jpg") != -1 || srcFiles[j].getName().toLowerCase().indexOf(".bmp") != -1)) {
                        clnFiles.add(srcFiles[j]);
                    }
                }

                adFiles.addAll(clnFiles);
                if(adFiles != null && adFiles.size() > 0) {
                    readLen = (adFiles.size() - 1) * loadMultiplier;
                }
            }
            
            readPos = 0;
        } catch(Exception e) {
            MmgHelper.wrErr(e);
        }
        
        try {
            //auto load audio files
            if(asFiles != null && asFiles.size() > 0) {
                tlen = asFiles.size();
                
                for(i = 0; i < tlen; i++) {
                    Helper.wr("Found auto_load file: " + asFiles.get(i).getName() + " Path: " + asFiles.get(i).getPath());
                    Helper.GetBasicCachedSound(asFiles.get(i).getPath(), asFiles.get(i).getName());
                    readPos = i * loadMultiplier;
                    
                    if (update != null) {                        
                        update.HandleUpdate(new LoadResourceUpdateMessage(readPos, readLen));
                    }
                    
                    try {
                        Thread.sleep(slowDown);
                    } catch (Exception e) {
                        
                    }
                    
                    if(exitLoad) {
                        break;
                    }
                }                
            }
            readResult = true;
            
        }catch(Exception e) {
            e.printStackTrace();
        }        
        
        try {            
            if(adFiles != null && adFiles.size() > 0) {
                readLen = (adFiles.size() - 1) * loadMultiplier;
                readPos = 0;
                tlen = adFiles.size();
                
                for(i = 0; i < tlen; i++) {
                    Helper.wr("Found auto_load file: " + adFiles.get(i).getName() + " Path: " + adFiles.get(i).getPath());
                    Helper.GetBasicCachedBmp(adFiles.get(i).getPath(), adFiles.get(i).getName());
                    readPos = i * loadMultiplier;
                    
                    if (update != null) {                        
                        update.HandleUpdate(new LoadResourceUpdateMessage(readPos, readLen));
                    }
                    
                    try {
                        Thread.sleep(slowDown);
                    } catch (Exception e) {
                        
                    }
                    
                    if(exitLoad) {
                        break;
                    }
                }                
            }
            readResult = true;
            
        }catch(Exception e) {
            e.printStackTrace();
        }
                
        readComplete = true;

        if (update != null) {
            update.HandleUpdate(new LoadResourceUpdateMessage(readPos, readLen));
        }
    }

    public long GetSlowDown() {
        return slowDown;
    }

    public void SetSlowDown(long l) {
        slowDown = l;
    }
        
    /**
     * Stops the current DAT load.
     */
    public void StopLoad() {
        i = tlen;
        exitLoad = true;
    }

    /**
     * Gets the current read length.
     *
     * @return Integer representing the read length.
     */
    public int GetLen() {
        return readLen;
    }

    /**
     * Gets the current read position.
     *
     * @return The current read position.
     */
    public int GetPos() {
        return readPos;
    }

    /**
     * Gets the result of the last read operation.
     *
     * @return The result of the last read operation.
     */
    public boolean GetReadResult() {
        return readResult;
    }

    /**
     * Gets and indication if the DAT load completed.
     *
     * @return
     */
    public boolean GetReadComplete() {
        return readComplete;
    }    
}
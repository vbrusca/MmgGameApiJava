package com.middlemind.Odroid;

import java.io.File;
import java.util.ArrayList;

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
     *
     * @param Thin A boolean indicating if image and sound data should be parsed
     * or just copied.
     * @param Data A binary array representing the Tyre DAT file.
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
        
        try {
            File ald = new File(GameSettings.AUTO_IMAGE_LOAD_DIR);
            File[] srcFiles = ald.listFiles();
            ArrayList<File> clnFiles = new ArrayList();
            
            for(int j = 0; j < srcFiles.length; j++) {
                if(srcFiles[j].getName().charAt(0) != '.') {
                    clnFiles.add(srcFiles[j]);
                }
            }

            File[] files = new File[clnFiles.size()];
            files = clnFiles.toArray(files);
            
            if(files != null && files.length > 0) {
                readLen = (files.length - 1) * loadMultiplier;
                readPos = 0;
                tlen = files.length;
                
                for(i = 0; i < tlen; i++) {
                    Helper.wr("Found auto_load file: " + files[i].getName() + " Path: " + files[i].getPath());
                    Helper.GetBasicCachedBmp(files[i].getPath(), files[i].getName());
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

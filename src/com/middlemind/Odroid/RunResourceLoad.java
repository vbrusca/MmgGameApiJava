package com.middlemind.Odroid;

import java.io.File;
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
    protected boolean readResult;

    /**
     * A boolean result of the last read operation.
     */
    protected boolean readComplete;

    /**
     * Helper variables for the read process.
     */
    protected int readPos;

    /**
     * Helper variables for the read process.
     */
    protected int readLen;
    //protected int len;    
    protected int loadMultiplier = 1000;
    //protected int extraLoadLen = ((42 + 15 + 284) * extraLoadMultiplier); //direct file load steps + console image steps + regular image steps

    /**
     * An event handler for receiving update messages from the DAT loader.
     */
    protected LoadResourceUpdateHandler update;

    protected int tlen;
    protected int i;
    protected long slowDown;
    protected boolean exitLoad;
    
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
            File[] files = ald.listFiles();
            
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

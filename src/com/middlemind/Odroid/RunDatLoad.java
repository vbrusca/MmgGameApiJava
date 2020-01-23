package com.middlemind.Odroid;

import java.io.File;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;

/**
 * A worker thread that handles loading the Tyre game DAT file. Created on
 * August 1, 2015, 10:57 PM by Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public final class RunDatLoad implements Runnable, LoadDatUpdateHandler {

    /**
     * A boolean result of the loading process.
     */
    private boolean readResult;

    /**
     * A boolean result of the last read operation.
     */
    private boolean readComplete;

    /**
     * Helper variables for the read process.
     */
    private int readPos;

    /**
     * Helper variables for the read process.
     */
    private int readLen;
    private int len;    
    private int extraLoadMultiplier = 1000;
    private int extraLoadLen = ((42 + 15 + 284) * extraLoadMultiplier); //direct file load steps + console image steps + regular image steps

    /**
     * An event handler for receiving update messages from the DAT loader.
     */
    private LoadDatUpdateHandler update;

    /**
     * A constructor that sets the thin load option, don't load binary image or
     * sound data yet, and sets the source byte array to parse.
     *
     * @param Thin A boolean indicating if image and sound data should be parsed
     * or just copied.
     * @param Data A binary array representing the Tyre DAT file.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public RunDatLoad() {
        readPos = 0;
        readLen = 0;
        readResult = false;
    }

    /**
     * A method for passing on LoadDatUpdate events.
     *
     * @param Update The handler subscribing to events.
     */
    public final void SetUpdateHandler(LoadDatUpdateHandler Update) {
        update = Update;
    }    
    
    /**
     * The run method for this worker thread.
     */
    @Override
    public final void run() {

        try {
            File ald = new File(GameSettings.AUTO_IMAGE_LOAD_DIR);
            File[] files = ald.listFiles();
            if(files != null && files.length > 0) {
                int tlen = files.length;
                for(int i = 0; i < tlen; i++) {
                    Helper.wr("Found auto_load file: " + files[i].getName() + " Path: " + files[i].getPath());
                    Helper.GetBasicCachedBmp(files[i].getPath(), files[i].getName());
                }
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        if (update != null) {
            //update.HandleUpdate(new LoadDatUpdateMessage(tmp, readLen));
        }
        
        readComplete = true;

        if (update != null) {
            //update.HandleUpdate(new LoadDatUpdateMessage(tmp, readLen));
        }
    }

    /**
     * Handles a LoadDatUpdateMessage from the DAT loading worker thread.
     *
     * @param obj The message to process.
     */
    @Override
    public final void HandleUpdate(LoadDatUpdateMessage obj) {
        if (obj != null) {
            obj.SetLen(obj.GetLen() + extraLoadLen);

            readPos = obj.GetPos();
            readLen = obj.GetLen();

            if (update != null) {
                update.HandleUpdate(obj);

                /*
                if (GetReadComplete() == true) {
                    LoadDat.chapter = null;
                    LoadDat.pos = 0;
                    LoadDat.foundEOF = false;
                    LoadDat.stop = false;
                    lDat = null;
                }
                */
            }
        }
    }
    
    /**
     * Stops the current DAT load.
     */
    public final void StopDatLoad() {
        //LoadDat.stop = true;
    }

    /**
     * Gets the current read length.
     *
     * @return Integer representing the read length.
     */
    public final int GetLen() {
        return readLen;
    }

    /**
     * Gets the current read position.
     *
     * @return The current read position.
     */
    public final int GetPos() {
        return readPos;
    }

    /**
     * Gets the result of the last read operation.
     *
     * @return The result of the last read operation.
     */
    public final boolean GetReadResult() {
        return readResult;
    }

    /**
     * Gets and indication if the DAT load completed.
     *
     * @return
     */
    public final boolean GetReadComplete() {
        return readComplete;
    }    
}

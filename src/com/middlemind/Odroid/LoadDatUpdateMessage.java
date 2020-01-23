package com.middlemind.Odroid;

/**
 * A class that wraps the LoadDat update information.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public class LoadDatUpdateMessage {
    
    /**
     * The current position of the DAT load process.
     */
    private int pos;
    
    /**
     * The length of the DAT binary array.
     */
    private int len;
   
    /**
     * Class constructor sets the current position and the current length of the data.
     * 
     * @param Pos       The current position of the DAT load process.
     * @param Len       The length of the DAT binary array.
     */
    public LoadDatUpdateMessage(int Pos, int Len) {
        pos = Pos;
        len = Len;
    }

    /**
     * Getter for the read position.
     * 
     * @return      The read position.
     */
    public int GetPos() {
        return pos;
    }

    /**
     * Setter for the read position.
     * 
     * @param pos   Sets the read position.
     */
    public void SetPos(int pos) {
        this.pos = pos;
    }

    /**
     * Getter for the total data length.
     * 
     * @return      The total data length.
     */
    public int GetLen() {
        return len;
    }

    /**
     * Setter for the total data length.
     * 
     * @param len   The total data length.
     */
    public void SetLen(int len) {
        this.len = len;
    }  
}

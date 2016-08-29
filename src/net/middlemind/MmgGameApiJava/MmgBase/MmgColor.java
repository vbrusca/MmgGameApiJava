package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.*;

/**
 * Class that wraps the lower level color object.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public class MmgColor {
    
    /**
     * The color of this object.
     */
    private Color c;
    //private Paint p;

    /**
     * Constructor of this object.
     */
    public MmgColor() {
        c = Color.WHITE;
    }

    /**
     * Constructor that sets its properties from an input MmgColor object.
     * 
     * @param m     Input MmgColor object. 
     */
    public MmgColor(MmgColor m) {
        c = m.GetColor();
    }

    /**
     * Constructor that sets the color to the given argument.
     * 
     * @param C     The color to set the object. 
     */
    public MmgColor(Color C) {
        c = C;
    }
    
    /**
     * Clones the current object.
     * 
     * @return      Clone of this object.
     */
    public MmgColor Clone() {
        return new MmgColor(c);
    }

    /**
     * Static helper method returns white.
     * 
     * @return      The color white. 
     */
    public static MmgColor GetWhite() {
        return new MmgColor(Color.WHITE);
    }

    /**
     * Static helper method returns black.
     * 
     * @return      The color black. 
     */
    public static MmgColor GetBlack() {
        return new MmgColor(Color.BLACK);
    }

    /**
     * Returns the color of this MmgColor object.
     * 
     * @return      The color of this object. 
     */
    public Color GetColor() {
        return c;
    }

    /**
     * Sets the color of this MmgColor object.
     * 
     * @param C     The color of this object. 
     */
    public void SetColor(Color C) {
        c = C;
    }

    /*
    public Paint GetPaint() {
        p = new Paint();
        p.setColor(GetColor());
        return new Color();
    }
    */
    
    public boolean Equals(MmgColor c) {
        if(c != null && c.GetColor().equals(GetColor())) {
            return true;
        }else {
            return false;
        }
    }
}

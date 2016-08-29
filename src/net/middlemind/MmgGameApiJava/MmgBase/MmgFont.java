package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Class that wraps the lower level font class.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 * 
 */
public final class MmgFont extends MmgObj {

    /**
     * Font of this class.
     */
    private Font font;
    
    /**
     * The text this font class is going to display.
     */
    private String text;
    
    /**
     * A render context for calculating font dimensions.
     */
    private final FontRenderContext frc;

    /**
     * Constructor for this class.
     */
    public MmgFont() {
        super();
        frc = new FontRenderContext(null, true, true);
        text = "";
        font = null;
        SetWidth(0);
        SetHeight(0);
    }

    /**
     * Constructor that sets the lower level font class.
     * 
     * @param tf    Font to use for text drawing. 
     */
    public MmgFont(Font tf) {
        frc = new FontRenderContext(null, true, true);
        text = "";
        font = tf;
        SetWidth(0);
        SetHeight(0);        
    }

    /**
     * Constructor that sets the lower level attributes based
     * on the given argument.
     * 
     * @param obj       The MmgObj to use. 
     */
    public MmgFont(MmgObj obj) {
        super(obj);
        frc = new FontRenderContext(null, true, true);
        text = "";
        font = null;
        SetWidth(0);
        SetHeight(0);        
    }

    /**
     * Constructor that sets attributes based on the 
     * given argument.
     * 
     * @param fnt 
     */
    public MmgFont(MmgFont fnt) {
        frc = new FontRenderContext(null, true, true);        
        SetFont(fnt.GetFont());        
        SetText(fnt.GetText());

        if(fnt.GetPosition() == null) {
            SetPosition(fnt.GetPosition());
        }else {
            SetPosition(fnt.GetPosition().Clone());
        }
        SetIsVisible(fnt.GetIsVisible());

        if(fnt.GetMmgColor() == null) {
            SetMmgColor(fnt.GetMmgColor());
        }else {
            SetMmgColor(fnt.GetMmgColor().Clone());
        }
    }

    /**
     * Constructor that sets the font, text, position, and color.
     * 
     * @param sf        Low level font class.
     * @param txt       Text to draw.
     * @param pos       Position to draw the text.
     * @param cl        Color to use to draw the text.
     */
    public MmgFont(Font sf, String txt, MmgVector2 pos, MmgColor cl) {
        frc = new FontRenderContext(null, true, true);
        SetFont(sf);
        SetText(txt);
        SetPosition(pos);
        SetIsVisible(true);
        SetMmgColor(cl);
    }

    /**
     * Constructor that sets the font, text, position in X, Y, and color.
     * 
     * @param sf        Low level font class.
     * @param txt       Text to draw.
     * @param x         Position, on the X axis.
     * @param y         Position, on the Y axis.
     * @param cl        Color to use to draw the text.
     */
    public MmgFont(Font sf, String txt, int x, int y, MmgColor cl) {
        frc = new FontRenderContext(null, true, true);
        SetFont(sf);
        SetText(txt);
        SetPosition(new MmgVector2(x, y));
        SetIsVisible(true);
        SetMmgColor(cl);
    }

    /**
     * Clones the current object.
     * 
     * @return  A clone of this object. 
     */
    @Override
    public MmgFont Clone() {
        MmgFont ret = new MmgFont(this);
        return ret;
    }

    /**
     * Gets the text for this object.
     * 
     * @return      The text for this object. 
     */
    public String GetText() {
        return text;
    }

    /**
     * Sets the text for this object.
     * Recalculates the drawing bounds of this object.
     * 
     * @param s     The text for this object.
     */
    public void SetText(String s) {
        if(s != null) {
            text = s;
            if("".equals(text) == false) {
                Rectangle2D r = font.getStringBounds(text, frc);
                Rectangle r2 = r.getBounds();
                SetWidth((int) r2.width);
                SetHeight((int) r2.height);
            }else{
                SetWidth(0);
                SetHeight(0);
            }
        }else{
            text = null;
            SetWidth(0);
            SetHeight(0);
        }
    }

    /**
     * Sets the size of the font.
     * 
     * @param sz        The size of the font. 
     */
    public void SetFontSize(int sz) {
        //if(font != null) {
            font = font.deriveFont((float)sz);
        //}
    }

    /**
     * Gets the size of the font.
     * 
     * @return      The size of the font.
     */
    public int GetFontSize() {
        //if(font != null) {
            return (int) font.getSize();
        //}else {
        //    return 0;
        //}
    }

    /**
     * Sets the sprite font, or font to use to draw text.
     * 
     * @param tf 
     */
    public void SetFont(Font tf) {
        //SetSpriteFont(tf);
        font = tf;
    }
    
    /**
     * Sets the sprite font, or font to use to draw text.
     * 
     * @param tf 
     */
    //public void SetSpriteFont(Font tf) {
    //    font = tf;
    //}
    
    /**
     * Gets the font object used to draw text.
     * 
     * @return      The font used to draw text.
     */
    public Font GetFont() {
        return font;
    }

    /**
     * Gets the font objects used to draw text.
     * 
     * @return      The font used to draw text.
     */
    /*
    public Font GetSpriteFont() {
        return font;
    }
    */
    
    /**
     * The base drawing method used to draw this object.
     * 
     * @param p     The MmgPen used to draw this object.
     * @see MmgPen
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (GetIsVisible() == true) {
            p.DrawText(this);
        } else {
            //do nothing
        }
    }
}

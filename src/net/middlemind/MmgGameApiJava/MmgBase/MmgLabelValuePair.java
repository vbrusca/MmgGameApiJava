package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.*;

/**
 * A class that wraps the MmgFont class and makes a label value pair.
 * Created by Middlemind Games 08/29/2016
 * 
 * @author Victor G. Brusca
 */
public class MmgLabelValuePair extends MmgObj {

    /**
     * The MmgFont to use to draw the label.
     */
    private MmgFont lbl;
    
    /**
     * The MmgFont to use to draw the value.
     */
    private MmgFont val;
    
    /**
     * A padding value to use in positioning on the X axis.
     */
    private int paddingX;
    
    /**
     * A boolean flag that prevents the object from being reset with a call to the Reset method.
     */
    private boolean skipReset;
    
    /**
     * A static class field that holds the default X axis padding.
     */
    public static int DEFAULT_PADDING_X = 8;
    
    /**
     * Constructor for this class.
     */
    public MmgLabelValuePair() {
        super();
        skipReset = true;
        SetLabel(new MmgFont(MmgFontData.GetFontBold()));
        SetValue(new MmgFont(MmgFontData.GetFontNorm()));
        SetPaddingX(DEFAULT_PADDING_X);
        SetWidth(0);
        SetHeight(0);
        skipReset = false;
        Reset();
    }

    /**
     * Constructor that sets the lower level font classes with Font class instances.
     * 
     * @param fontLbl       The Font to use for the label.
     * @param fontVal       The Font to use for the value.
     */
    public MmgLabelValuePair(Font fontLbl, Font fontVal) {
        super();
        skipReset = true;
        SetLabel(new MmgFont(fontLbl));
        SetValue(new MmgFont(fontVal));
        SetPaddingX(DEFAULT_PADDING_X);
        SetWidth(0);
        SetHeight(0);    
        skipReset = false;
        Reset();
    }

    /**
     * Constructor that sets the lower level font classes with MmgFont class instances.
     * 
     * @param fontLbl       The MmgFont to use for the label.
     * @param fontVal       The MmgFont to use for the value.
     */
    public MmgLabelValuePair(MmgFont fontLbl, MmgFont fontVal) {
        super();
        skipReset = true;
        SetLabel(fontLbl);
        SetValue(fontVal);
        SetPaddingX(DEFAULT_PADDING_X);
        SetWidth(0);
        SetHeight(0);    
        skipReset = false;
        Reset();
    }        
    
    /**
     * Constructor that sets the lower level attributes based
     * on the given argument.
     * 
     * @param obj       The MmgObj to use. 
     */
    public MmgLabelValuePair(MmgObj obj) {
        super(obj);
        skipReset = true;
        SetLabel(new MmgFont());
        SetValue(new MmgFont());
        SetPaddingX(DEFAULT_PADDING_X);
        SetWidth(0);
        SetHeight(0);    
        skipReset = false;
        Reset();
    }

    /**
     * Constructor that sets attributes based on the 
     * given argument.
     * 
     * @param lvp       The MmgLabelValuePair to use to initialize this class instance.
     */
    public MmgLabelValuePair(MmgLabelValuePair lvp) {
        super();
        skipReset = true;
        if(lvp.GetLabel() == null) {
            SetLabel(lvp.GetLabel());
        }else {
            SetLabel(lvp.GetLabel().CloneTyped());
        }
        
        if(lvp.GetValue() == null) {
            SetValue(lvp.GetValue());
        }else {
            SetValue(lvp.GetValue().CloneTyped());
        }
        
        if(lvp.GetPosition() == null) {
            SetPosition(lvp.GetPosition());
        }else {
            SetPosition(lvp.GetPosition().Clone());
        }
        SetIsVisible(lvp.GetIsVisible());

        if(lvp.GetMmgColor() == null) {
            SetMmgColor(lvp.GetMmgColor());
        }else {
            SetMmgColor(lvp.GetMmgColor().Clone());
        }
        
        SetPaddingX(lvp.GetPaddingX());
        SetWidth(lvp.GetWidth());
        SetHeight(lvp.GetHeight());
        skipReset = false;
        Reset();
    }

    /**
     * Constructor that sets the font, text, position, and color.
     * 
     * @param fontLbl       The font to use for the label.
     * @param txtLbl        The text to set the label to.
     * @param pos           The position to draw the label value pair.
     * @param txtVal        The text to set the value to.
     * @param fontVal       The font to use for the value.
     * @param cl            The color to use to draw the text.
     */
    public MmgLabelValuePair(Font fontLbl, String txtLbl, Font fontVal, String txtVal, MmgVector2 pos, MmgColor cl) {
        super();
        skipReset = true;
        SetLabel(new MmgFont(fontLbl));
        SetValue(new MmgFont(fontVal));
        SetLabelText(txtLbl);
        SetValueText(txtVal);
        SetPosition(pos);
        SetIsVisible(true);
        SetMmgColor(cl);
        SetPaddingX(DEFAULT_PADDING_X);
        skipReset = false;
        Reset();   
    }

    /**
     * Constructor that sets the font, text, position in X, Y, and color.
     * 
     * @param fontLbl       The font to use for the label.
     * @param txtLbl        The text to set the label to.
     * @param fontVal       The font to use for the value.
     * @param txtVal        The text to set the value to.
     * @param x             Position, on the X axis.
     * @param y             Position, on the Y axis.
     * @param cl            The color to use to draw the text.
     */
    public MmgLabelValuePair(Font fontLbl, String txtLbl, Font fontVal, String txtVal, int x, int y, MmgColor cl) {
        super();
        skipReset = true;
        SetLabel(new MmgFont(fontLbl));
        SetValue(new MmgFont(fontVal));
        SetLabelText(txtLbl);
        SetValueText(txtVal);
        SetPosition(new MmgVector2(x, y));
        SetIsVisible(true);
        SetMmgColor(cl);
        SetPaddingX(DEFAULT_PADDING_X);
        skipReset = false;
        Reset();
    }

    /**
     * Creates a basic clone of this class.
     * 
     * @return      A clone of this class. 
     */
    @Override
    public MmgObj Clone() {
        MmgLabelValuePair ret = new MmgLabelValuePair(this);
        return (MmgObj) ret;
    }

    /**
     * Creates a typed clone of this class.
     * 
     * @return      A typed clone of this class.
     */
    @Override
    public MmgLabelValuePair CloneTyped() {
        return new MmgLabelValuePair(this);
    }
    
    /**
     * Gets the X axis padding.
     * 
     * @return      The X axis padding of this object.
     */
    public int GetPaddingX() {
        return paddingX;
    }
    
    /**
     * Sets the X axis padding.
     * 
     * @param p     The X axis padding to use for this object. 
     */
    public void SetPaddingX(int p ) {
        paddingX = p;
        Reset();
    }
    
    /**
     * Gets the value text of this object.
     * 
     * @return      The value text to use for this object.
     */
    public String GetValueText() {
        return val.GetText();
    }

    /**
     * Sets the value text of this object.
     * 
     * @param s     The value text to use for this object.
     */
    public void SetValueText(String s) {
        val.SetText(s);
        Reset();
    }
    
    /**
     * Gets the label text of this object.
     * 
     * @return      The label text to use for this object.
     */
    public String GetLabelText() {
        return lbl.GetText();
    }
    
    /**
     * Sets the label text of this object.
     *  
     * @param s     The label text to use for this object.
     */
    public void SetLabelText(String s) {
        lbl.SetText(s);
        Reset();
    }
    
    /**
     * Resets the width, height and position of this object if skipReset is false.
     */
    private void Reset() {
        if(skipReset == false) {
            SetWidth(lbl.GetWidth() + paddingX + val.GetWidth());
            SetHeight(lbl.GetHeight());
            val.SetPosition(lbl.GetPosition().Clone());
            val.SetX(val.GetX() + lbl.GetWidth() + paddingX);
        }
    }
    
    /**
     * Sets the size of the font.
     * 
     * @param sz        The size of the font. 
     */
    public void SetFontSize(int sz) {
        val.GetFont().deriveFont(sz);
        lbl.GetFont().deriveFont(sz);
        Reset();
    }

    /**
     * Gets the size of the font.
     * 
     * @return      The size of the font.
     */
    public int GetFontSize() {
        if(lbl != null) {
            return (int) lbl.GetFont().getSize();
            
        }else if(val != null) {
            return (int) val.GetFont().getSize();
            
        }else {
            return 0;
            
        }
    }

    /**
     * Gets the MmgFont of the label of this object.
     * 
     * @return      The MmgFont of the label of this object.
     */
    public MmgFont GetLabel() {
        return lbl;
    }
    
    /**
     * Sets the MmgFont of the label of this object.
     * 
     * @param ft    The MmgFont of the label of this object.
     */
    public void SetLabel(MmgFont ft) {
        lbl = ft;
        Reset();
    }
    
    /**
     * Gets the MmgFont of the value of this object.
     * 
     * @return      The MmgFont of the value of this object.
     */
    public MmgFont GetValue() {
        return val;
    }
    
    /**
     * Sets the MmgFont of the value of this object.
     * 
     * @param ft    The MmgFont of the value of this object.
     */
    public void SetValue(MmgFont ft) {
        val = ft;
        Reset();
    }
    
    /**
     * Gets the Font object used used to draw the label text.
     * 
     * @return      The font used to draw the label text.
     */
    public Font GetLabelFont() {
        return lbl.GetFont();
    }

    /**
     * Sets the Font object used to draw the label text.
     * 
     * @param ft    The font used to draw the label text.
     */
    public void SetLabelFont(Font ft) {
        lbl.SetFont(ft);
        Reset();
    }
    
    /**
     * Gets the Font object used to draw the value text.
     * 
     * @return      The font used to draw the value text.
     */
    public Font GetValueFont() {
        return val.GetFont();
    }
    
    /**
     * Sets the Font object used to draw the value text.
     * 
     * @param ft    the font used to draw the value text.
     */
    public void SetValueFont(Font ft) {
        val.SetFont(ft);
        Reset();
    }
    
    /**
     * Sets the position of this object and resets the label, value pair positioning.
     * 
     * @param v     The position of this object.
     */
    @Override
    public void SetPosition(MmgVector2 v) {
        super.SetPosition(v);
        lbl.SetPosition(v);
        Reset();
    }
    
    /**
     * Sets the position of this object and resets the label, value pair positioning.
     * 
     * @param x     The X coordinate to set in the position.
     * @param y     The Y coordinate to set in the position.
     */
    @Override
    public void SetPosition(int x, int y) {
        SetPosition(new MmgVector2(x, y));
    }    
    
    /**
     * Sets the MmgColor to use for drawing the label, value pair text.
     * 
     * @param c     The color to use for drawing this object's text.
     */
    @Override
    public void SetMmgColor(MmgColor c) {
        super.SetMmgColor(c);
        lbl.SetMmgColor(c);
        val.SetMmgColor(c);
    }
    
    /**
     * Sets the X coordinate of this object and resets the position of the label, value pair.
     * 
     * @param x     The X position of this object.
     */
    @Override
    public void SetX(int x) {
        super.SetX(x);
        lbl.SetX(x);
        Reset();
    }
    
    /**
     * Sets the Y coordinate of this object and resets the position of the label, value pair.
     * 
     * @param y     The Y position of this object.
     */
    @Override
    public void SetY(int y) {
        super.SetY(y);
        lbl.SetY(y);
        Reset();
    }
    
    /**
     * The base drawing method used to draw this object.
     * 
     * @param p     The MmgPen used to draw this object.
     * @see         MmgPen
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (isVisible == true) {
            p.DrawText(lbl);
            p.DrawText(val);
        }
    }
    
    /**
     * A method used to determine equality for MmgLabelValuePair objects.
     * Equality is based on having the same label, value, and padding.
     * 
     * @param r     The MmgLabelValuePair to compare to.
     * @return      A boolean value indicating if the two objects are equal.
     */
    public boolean Equals(MmgLabelValuePair r) {
        if(GetLabel().Equals(r.GetLabel()) == true
            && GetValue().Equals(r.GetValue()) == true
            && GetPaddingX() == r.GetPaddingX()
        ) {
            return true;
        }else {
            return false;
        }
    }
}

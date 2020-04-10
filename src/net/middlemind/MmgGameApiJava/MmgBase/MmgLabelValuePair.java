package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.*;
//import java.awt.font.FontRenderContext;
//import java.awt.geom.Rectangle2D;

/**
 * Class that wraps the lower level font class.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 * 
 */
public class MmgLabelValuePair extends MmgObj {

    private MmgFont lbl;
    private MmgFont val;
    private int paddingX;
    private boolean skipReset;
    
    /**
     * Constructor for this class.
     */
    public MmgLabelValuePair() {
        super();
        skipReset = true;
        SetLabel(new MmgFont(MmgFontData.GetFontBold()));
        SetValue(new MmgFont(MmgFontData.GetFontNorm()));
        SetPaddingX(8);
        SetWidth(0);
        SetHeight(0);
        skipReset = false;
        Reset();
    }

    /**
     * Constructor that sets the lower level font class.
     * 
     * @param fontLbl
     * @param fontVal
     */
    public MmgLabelValuePair(Font fontLbl, Font fontVal) {
        super();
        skipReset = true;
        SetLabel(new MmgFont(fontLbl));
        SetValue(new MmgFont(fontVal));
        SetPaddingX(8);
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
        SetPaddingX(8);
        SetWidth(0);
        SetHeight(0);    
        skipReset = false;
        Reset();
    }

    /**
     * Constructor that sets attributes based on the 
     * given argument.
     * 
     * @param lvp
     */
    public MmgLabelValuePair(MmgLabelValuePair lvp) {
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
        
        SetPaddingX(8);
        SetWidth(lvp.GetWidth());
        SetHeight(lvp.GetHeight());
        skipReset = false;
        Reset();
    }

    /**
     * Constructor that sets the font, text, position, and color.
     * 
     * @param fontLbl
     * @param txtLbl
     * @param pos       Position to draw the text.
     * @param txtVal
     * @param fontVal
     * @param cl        Color to use to draw the text.
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
        SetPaddingX(8);
        skipReset = false;
        Reset();   
    }

    /**
     * Constructor that sets the font, text, position in X, Y, and color.
     * 
     * @param fontLbl
     * @param txtLbl
     * @param fontVal
     * @param txtVal
     * @param x         Position, on the X axis.
     * @param y         Position, on the Y axis.
     * @param cl        Color to use to draw the text.
     */
    public MmgLabelValuePair(Font fontLbl, String txtLbl, Font fontVal, String txtVal, int x, int y, MmgColor cl) {
        skipReset = true;
        SetLabel(new MmgFont(fontLbl));
        SetValue(new MmgFont(fontVal));
        SetLabelText(txtLbl);
        SetValueText(txtVal);
        SetPosition(new MmgVector2(x, y));
        SetIsVisible(true);
        SetMmgColor(cl);
        SetPaddingX(8);
        skipReset = false;
        Reset();
    }

    /**
     * Clones the current object.
     * 
     * @return  A clone of this object. 
     */
    @Override
    public MmgObj Clone() {
        MmgLabelValuePair ret = new MmgLabelValuePair(this);
        return (MmgObj) ret;
    }

    /**
     * 
     * 
     * @return 
     */
    @Override
    public MmgLabelValuePair CloneTyped() {
        return new MmgLabelValuePair(this);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public int GetPaddingX() {
        return paddingX;
    }
    
    /**
     * 
     * 
     * @param p 
     */
    public void SetPaddingX(int p ) {
        paddingX = p;
        Reset();
    }
    
    /**
     * 
     * 
     * @return 
     */
    public String GetValueText() {
        return val.GetText();
    }

    /**
     * 
     * 
     * @param s 
     */
    public void SetValueText(String s) {
        val.SetText(s);
        Reset();
    }
    
    /**
     * 
     * 
     * @return 
     */
    public String GetLabelText() {
        return lbl.GetText();
    }
    
    /**
     * 
     * 
     * @param s 
     */
    public void SetLabelText(String s) {
        lbl.SetText(s);
        Reset();
    }
    
    /**
     * 
     * 
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
     * 
     * 
     * @return 
     */
    public MmgFont GetLabel() {
        return lbl;
    }
    
    /**
     * 
     * 
     * @param ft 
     */
    public void SetLabel(MmgFont ft) {
        lbl = ft;
        Reset();
    }
    
    /**
     * 
     * 
     * @return 
     */
    public MmgFont GetValue() {
        return val;
    }
    
    /**
     * 
     * 
     * @param ft 
     */
    public void SetValue(MmgFont ft) {
        val = ft;
        Reset();
    }
    
    /**
     * Gets the font object used to draw text.
     * 
     * @return      The font used to draw text.
     */
    public Font GetLabelFont() {
        return lbl.GetFont();
    }

    /**
     * 
     * 
     * @param ft 
     */
    public void SetLabelFont(Font ft) {
        lbl.SetFont(ft);
        Reset();
    }
    
    /**
     * 
     * 
     * @return 
     */
    public Font GetValueFont() {
        return val.GetFont();
    }
    
    /**
     * 
     * 
     * @param ft 
     */
    public void SetValueFont(Font ft) {
        val.SetFont(ft);
        Reset();
    }
    
    /**
     * 
     * 
     * @param v 
     */
    @Override
    public void SetPosition(MmgVector2 v) {
        super.SetPosition(v);
        lbl.SetPosition(v);
        Reset();
    }
    
    /**
     * 
     * 
     * @param c 
     */
    @Override
    public void SetMmgColor(MmgColor c) {
        super.SetMmgColor(c);
        lbl.SetMmgColor(c);
        val.SetMmgColor(c);
    }
    
    /**
     * 
     * 
     * @param x 
     */
    @Override
    public void SetX(int x) {
        super.SetX(x);
        lbl.SetX(x);
        Reset();
    }
    
    /**
     * 
     * 
     * @param y 
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
     * @see MmgPen
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (GetIsVisible() == true) {
            p.DrawText(lbl);
            p.DrawText(val);
        } else {
            //do nothing
        }
    }
    
    /**
     * 
     * 
     * @param r
     * @return 
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

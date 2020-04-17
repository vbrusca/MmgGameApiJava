package net.middlemind.MmgGameApiJava.MmgBase;

import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.GamePanel;
import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventHandler;

/**
 * 
 * 
 * @author Victor G. Brusca, Middlemind Games
 */
public class MmgTextField extends MmgObj {
    
    /**
     * 
     */
    private MmgBmp bgroundSrc;
    
    /**
     * 
     */
    private Mmg9Slice bground;
    
    /**
     * 
     */
    private MmgFont font;
    
    /**
     * 
     */
    private int maxLength;
    
    /**
     * 
     */
    private boolean maxLengthOn;
    
    /**
     * 
     */
    private String textFieldString = "";
    
    private int displayChars;
    
    /**
     * 
     */
    private int fontWidth;
    
    /**
     * 
     */
    private int fontHeight;
    
    /**
     * 
     */
    private int padding;
    
    /**
     * 
     */
    private long cursorBlinkStart;
    
    /**
     * 
     */
    private boolean cursorBlinkOn;
    
    /**
     * 
     */
    private long tmpL;
    
    /**
     * 
     */
    private boolean isDirty;
    
    /**
     * 
     */
    private String tmpStr;
    
    /**
     * 
     */
    private MmgEvent errorMaxLength = new MmgEvent(null, "error_max_length", MmgTextField.TEXT_FIELD_MAX_LENGTH_ERROR_EVENT_ID, MmgTextField.TEXT_FIELD_MAX_LENGTH_ERROR_TYPE, null, null);
    
    /**
     * 
     */
    public static int TEXT_FIELD_9_SLICE_OFFSET = 16;
    
    /**
     * 
     */
    public static String TEXT_FIELD_CURSOR = "_";
    
    /**
     * 
     */
    public static long TEXT_FIELD_CURSOR_BLINK_RATE_MS = 350l;
        
    /**
     * 
     */
    public static int TEXT_FIELD_MAX_LENGTH_ERROR_EVENT_ID = 1;
    
    /**
     * 
     */
    public static int TEXT_FIELD_MAX_LENGTH_ERROR_TYPE = 0;    
    
    /**
     * 
     */
    public static int DEFAULT_MAX_LENGTH = 20;
    
    /**
     * 
     * 
     * @param BgroundSrc
     * @param Font
     * @param Width
     * @param Height
     * @param Padding 
     */
    public MmgTextField(MmgBmp BgroundSrc, MmgFont Font, int Width, int Height, int Padding, int DisplayChars) {
        bgroundSrc = BgroundSrc;
        font = Font;
        padding = Padding;
        displayChars = DisplayChars;
        SetWidth(Width);
        SetHeight(Height);
        SetMaxLength(DEFAULT_MAX_LENGTH);
        font.SetText("");
        Prep();
    }

    /**
     * 
     */
    public void Prep() {
        cursorBlinkStart = System.currentTimeMillis();
        fontHeight = font.GetHeight();
        fontWidth = GetWidth() - (padding * 2);
        textFieldString = "";
        bground = new Mmg9Slice(TEXT_FIELD_9_SLICE_OFFSET, bgroundSrc, GetWidth(), GetHeight());        
    }

    public MmgEvent GetErrorMaxLength() {
        return errorMaxLength;
    }

    public void SetErrorMaxLength(MmgEvent e) {
        errorMaxLength = e;
    }
    
    /**
     * 
     * 
     * @return 
     */
    public MmgBmp GetBgroundSrc() {
        return bgroundSrc;
    }

    /**
     * 
     * 
     * @param bg 
     */
    public void SetBgroundSrc(MmgBmp bg) {
        bgroundSrc = bg;
    }

    /**
     * 
     * 
     * @return 
     */
    public Mmg9Slice GetBground() {
        return bground;
    }

    /**
     * 
     * 
     * @param bg 
     */
    public void SetBground(Mmg9Slice bg) {
        bground = bg;
    }

    /**
     * 
     * 
     * @return 
     */
    public MmgFont GetFont() {
        return font;
    }

    /**
     * 
     * 
     * @param f 
     */
    public void SetFont(MmgFont f) {
        font = f;
    }

    /**
     * 
     * 
     * @return 
     */
    public int GetMaxLength() {
        return maxLength;
    }

    /**
     * 
     * 
     * @param i 
     */
    public void SetMaxLength(int i) {
        maxLength = i;
    }

    /**
     * 
     * 
     * @return 
     */
    public boolean IsMaxLengthOn() {
        return maxLengthOn;
    }

    /**
     * 
     * 
     * @param b 
     */
    public void SetMaxLengthOn(boolean b) {
        maxLengthOn = b;
    }

    /**
     * 
     * 
     * @return 
     */
    public String GetTextFieldString() {
        return textFieldString;
    }

    /**
     * 
     * 
     * @param str 
     */
    public void SetTextFieldString(String str) {
        textFieldString = str;
    }

    public int GetDisplayChars() {
        return displayChars;
    }

    public void SetDisplayChars(int i) {
        displayChars = i;
    }

    /**
     * 
     * 
     * @return 
     */
    public int GetFontWidth() {
        return fontWidth;
    }

    /**
     * 
     * 
     * @param i 
     */
    public void SetFontWidth(int i) {
        fontWidth = i;
    }

    /**
     * 
     * 
     * @return 
     */
    public int GetFontHeight() {
        return fontHeight;
    }

    /**
     * 
     * 
     * @param i 
     */
    public void SetFontHeight(int i) {
        fontHeight = i;
    }

    /**
     * 
     * 
     * @return 
     */
    public int GetPadding() {
        return padding;
    }

    /**
     * 
     * 
     * @param i 
     */
    public void SetPadding(int i) {
        padding = i;
    }

    /**
     * 
     * 
     * @return 
     */
    public boolean GetIsDirty() {
        return isDirty;
    }

    /**
     * 
     * 
     * @param b 
     */
    public void SetIsDirty(boolean b) {
        isDirty = b;
    }
    
    /**
     * 
     * 
     * @param pos 
     */
    @Override
    public void SetPosition(MmgVector2 pos) {
        super.SetPosition(pos);
        bground.SetPosition(new MmgVector2(pos.GetX(), pos.GetY()));
        font.SetPosition(new MmgVector2(pos.GetX() + padding, pos.GetY() + GetHeight() - padding));
    }
    
    /**
     * 
     * 
     * @param c
     * @param code
     * @return 
     */
    public boolean ProcessKeyClick(char c, int code) {
        if(maxLengthOn) {
            if(textFieldString.length() + 1 >= maxLength) {
                if(errorMaxLength != null) {
                    errorMaxLength.Fire();
                }
                return true;
            }
        }
        
        textFieldString += c;
        isDirty = true;        
        return true;
    }    
    
    /**
     * 
     * 
     */
    public void DeleteChar() {
        if(textFieldString.length() > 0) {
            textFieldString = textFieldString.substring(0, textFieldString.length() - 1);
        }
        isDirty = true;
    }
    
    /**
     * 
     * 
     * @param pos 
     */
    @Override
    public void SetPosition(int x, int y) {
        SetPosition(new MmgVector2(x, y));
    }    
    
    /**
     * 
     * 
     * @param x 
     */
    @Override
    public void SetX(int x) {
        super.SetX(x);
        bground.SetX(x);
        font.SetX(x + padding);
    }
    
    /**
     * 
     * 
     * @param y 
     */
    @Override
    public void SetY(int y) {
        super.SetY(y);
        bground.SetY(y);
        font.SetY(y + GetHeight() - padding);
    }    
    
    /**
     * The MmgUpdate method that handles updating any object fields during the update calls.
     * 
     * @param updateTick            The index of the update call.
     * @param currentTimeMs         The current time in milliseconds that the update was called.
     * @param msSinceLastFrame      The difference in milliseconds between this update call and the previous update call.
     * @return                      A boolean indicating if the update call was handled.
     */
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        if(isVisible == true) {            
            tmpL = System.currentTimeMillis();
            if(tmpL - cursorBlinkStart >= MmgTextField.TEXT_FIELD_CURSOR_BLINK_RATE_MS) {
                isDirty = true;
                cursorBlinkOn = !cursorBlinkOn;
                cursorBlinkStart = tmpL;
            }
            
            if(isDirty) {
                if(textFieldString.length() > displayChars) {
                    font.SetText(textFieldString.substring(textFieldString.length() - displayChars));
                } else {
                    font.SetText(textFieldString); 
                }

                if(cursorBlinkOn) {
                    font.SetText(font.GetText() + TEXT_FIELD_CURSOR);
                }                
                
                isDirty = false;
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * The base drawing method for this object.
     *
     * @param p     The MmgPen used to draw this object.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (isVisible == true) {
            bground.MmgDraw(p);
            font.MmgDraw(p);
        }
    }    
}

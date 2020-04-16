package net.middlemind.MmgGameApiJava.MmgBase;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 */
public class MmgTextField extends MmgObj {
    
    private MmgBmp bgroundSrc;
    
    private Mmg9Slice bground;
    
    private MmgFont font;
    
    private int maxLength;
    
    private boolean maxLengthOn;
    
    private int minLength;
    
    private boolean minLengthOn;
        
    private String textFieldString = "";
    
    private int charStart;
    
    private int charEnd;
    
    private int fontWidth;
    
    private int fontHeight;
    
    private int padding;
    
    private long cursorBlinkStart;
    
    private boolean cursorBlinkOn;
    
    private long tmpL;
    
    private boolean isDirty;
    
    private String tmpStr;
    
    public static int TEXT_FIELD_9_SLICE_OFFSET = 16;
    
    public static String TEXT_FIELD_CURSOR = "_";
    
    public static long TEXT_FIELD_CURSOR_BLINK_RATE_MS = 350l;
    
    public static int TEXT_FIELD_MIN_LENGTH_ERROR_EVENT_ID = 0;
    
    public static int TEXT_FIELD_MAX_LENGTH_ERROR_EVENT_ID = 1;

    public static int TEXT_FIELD_NUMBER_ONLY_ERROR_EVENT_ID = 2;    
    
    public static int DEFAULT_MAX_LENGTH = 20;
    
    public MmgTextField(MmgBmp BgroundSrc, MmgFont Font, int Width, int Height, int Padding) {
        bgroundSrc = BgroundSrc;
        font = Font;
        padding = Padding;
        SetWidth(Width);
        SetHeight(Height);
        SetMaxLength(DEFAULT_MAX_LENGTH);
        font.SetText("");
        Prep();
    }

    public void Prep() {
        cursorBlinkStart = System.currentTimeMillis();
        fontHeight = font.GetHeight();
        fontWidth = GetWidth() - (padding * 2);
        textFieldString = "";
        bground = new Mmg9Slice(TEXT_FIELD_9_SLICE_OFFSET, bgroundSrc, GetWidth(), GetHeight());        
    }
    
    public MmgBmp GetBgroundSrc() {
        return bgroundSrc;
    }

    public void SetBgroundSrc(MmgBmp bg) {
        bgroundSrc = bg;
    }

    public Mmg9Slice GetBground() {
        return bground;
    }

    public void SetBground(Mmg9Slice bg) {
        bground = bg;
    }

    public MmgFont GetFont() {
        return font;
    }

    public void SetFont(MmgFont f) {
        font = f;
    }

    public int GetMaxLength() {
        return maxLength;
    }

    public void SetMaxLength(int i) {
        maxLength = i;
    }

    public boolean IsMaxLengthOn() {
        return maxLengthOn;
    }

    public void SetMaxLengthOn(boolean b) {
        maxLengthOn = b;
    }

    public int GetMinLength() {
        return minLength;
    }

    public void SetMinLength(int i) {
        minLength = i;
    }

    public boolean IsMinLengthOn() {
        return minLengthOn;
    }

    public void SetMinLengthOn(boolean b) {
        minLengthOn = b;
    }

    public String GetTextFieldString() {
        return textFieldString;
    }

    public void SetTextFieldString(String str) {
        textFieldString = str;
    }

    public int GetCharStart() {
        return charStart;
    }

    public void SetCharStart(int i) {
        charStart = i;
    }

    public int GetCharEnd() {
        return charEnd;
    }

    public void SetCharEnd(int i) {
        charEnd = i;
    }

    public int GetFontWidth() {
        return fontWidth;
    }

    public void SetFontWidth(int i) {
        fontWidth = i;
    }

    public int GetFontHeight() {
        return fontHeight;
    }

    public void SetFontHeight(int i) {
        fontHeight = i;
    }

    public int GetPadding() {
        return padding;
    }

    public void SetPadding(int i) {
        padding = i;
    }

    public boolean IsIsDirty() {
        return isDirty;
    }

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
    
    public boolean ProcessKeyClick(char c) {        
        textFieldString += c;
        isDirty = true;        
        return true;
    }    
    
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
                //tmpStr = font.GetText();
                if(cursorBlinkOn) {
                    font.SetText(textFieldString + TEXT_FIELD_CURSOR);
                } else {
                    font.SetText(textFieldString);
                }
                charStart = 0;
                
                tmpStr = font.GetText();
                while(font.GetX() + font.GetWidth() > GetX() + GetWidth() - padding) {
                    font.SetText(tmpStr);                    
                    charStart++;
                    tmpStr = tmpStr.substring(charStart);
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

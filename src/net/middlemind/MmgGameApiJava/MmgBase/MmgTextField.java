package net.middlemind.MmgGameApiJava.MmgBase;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 */
public class MmgTextField extends MmgObj {
    
    private MmgBmp bgroundSrc = null;
    
    private Mmg9Slice bground = null;
    
    private MmgFont font = null;
    
    private int maxLength = 20;
    
    private boolean maxLengthOn = true;
    
    private int minLength = 0;
    
    private boolean minLengthOn = false;
    
    private boolean numberOnlyOn = false;
    
    private String textFieldString = "";
    
    private int charStart = 0;
    
    private int charEnd = 0;
    
    private int fontWidth = 0;
    
    private int fontHeight = 0;
    
    private int padding = 0;
    
    public static char TEXT_FIELD_CURSOR = '_';
    
    public static long TEXT_FIELD_CURSOR_BLINK_RATE_MS = 200l;
    
    public static int TEXT_FIELD_MIN_LENGTH_ERROR_EVENT_ID = 0;
    
    public static int TEXT_FIELD_MAX_LENGTH_ERROR_EVENT_ID = 1;

    public static int TEXT_FIELD_NUMBER_ONLY_ERROR_EVENT_ID = 2;    
    
    public MmgTextField(MmgBmp BgroundSrc, MmgFont Font, int Width, int Height, int Padding) {
        bgroundSrc = BgroundSrc;
        font = Font;
        fontHeight = font.GetHeight();
        fontWidth = Width - (Padding * 2);
        SetWidth(Width);
        SetHeight(Height);
    }
    
    
}

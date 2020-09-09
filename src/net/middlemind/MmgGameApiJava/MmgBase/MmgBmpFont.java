package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.PixelGrabber;

//TODO: Add method documentation

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 */
public class MmgBmpFont extends MmgObj {
    /**
     * 
     */
    private MmgBmp src;
    
    /**
     * 
     */
    private MmgBmp dst;
    
    /**
     * 
     */
    private MmgBmp[] chars;
    
    /**
     * 
     */
    private String text;
    
    /**
     * 
     */
    private int[] widths;
    
    /**
     * 
     */
    public static int EXPECTED_CHAR_LENGTH = 94;
    
    /**
     * 
     */
    public static int DEFAULT_NULL_WIDTH = MmgHelper.ScaleValue(20);
    
    /**
     * 
     */
    public static int DEFAULT_NULL_HEIGHT = MmgHelper.ScaleValue(20);    
    
    /**
     * 
     * 
     * @param Src
     * @param Text 
     */
    public MmgBmpFont(MmgBmp Src, String Text) {
        super();
        SetSrc(Src);        
        Prep();
        SetText(Text);
    }
    
    /**
     * 
     * 
     * @param Src 
     */
    public MmgBmpFont(MmgBmp Src) {
        super();        
        SetSrc(Src);
        Prep();
    }
            
    /**
     * 
     * 
     * @param obj 
     */
    public MmgBmpFont(MmgBmpFont obj) {
        super();        
        if(obj.GetSrc() == null) {
            SetSrc(obj.GetSrc());
        } else {
            SetSrc(obj.GetSrc().CloneTyped());
        }
                 
        if(obj.GetText() != null && obj.GetText().equals("") == false) {
            SetText(obj.GetText());
        }
        
        if(obj.GetDst() == null) {
            SetDst(obj.GetDst());
        } else {
            SetDst(obj.GetDst().CloneTyped());
        }
        
        int len = 0;
        if(obj.GetChars() == null) {
            SetChars(obj.GetChars());
        } else {
            len = obj.GetCharCount();
            SetChars(new MmgBmp[len]);
            for(int i = 0; i < len; i++) {
                SetChar(obj.GetChar(i).CloneTyped(), i);
            }
        }
        
        if(obj.GetWidths() == null) {
            SetWidths(obj.GetWidths());
        } else {
            len = obj.GetWidths().length;
            SetWidths(new int[len]);
            for(int i = 0; i < len; i++) {
                widths[i] = obj.GetWidths()[i];
            }
        }
    }
    
    /**
     * 
     * 
     * @return 
     */
    public MmgObj Clone() {
        return (MmgObj) new MmgBmpFont(this);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public MmgBmpFont CloneTyped() {
        return new MmgBmpFont(this);
    }
    
    /**
     * 
     */
    public void Prep() {        
        Image img = src.GetImage();
        int width = src.GetWidth();
        int height = src.GetHeight();
        MmgDrawableBmpSet bmpSet;
        Graphics2D g = (Graphics2D) img.getGraphics();
        PixelGrabber grabber = new PixelGrabber(img, 0, 0, width, height, false);
        
        int[] data = (int[]) grabber.getPixels();
        int i = 1;
        int start = i;
        int found = 0;
        int pos;
        int w;
        chars = new MmgBmp[94];
        widths = new int[94];
        
        for(i = 1; i < width; i++) {
            Color ct = new Color(data[i]);
            if(ct.getRed() == 255 && ct.getGreen() == 0 && ct.getBlue() == 255) {
                pos = start;
                w = (i - start);
                bmpSet = MmgHelper.CreateDrawableBmpSet(w, height, true);
                bmpSet.p.DrawBmp(src, new MmgRect(start, 0, height, start + w), new MmgRect(0, 0, height, w));
                chars[found] = bmpSet.img;
                widths[found] = w;
                MmgHelper.wr("Index: " + found + ", Found char at " + pos + " with width " + w);
                start = i + 1;
                found++;
            }
        }        
    }
    
    /**
     * 
     * 
     * @param c
     * @return 
     */
    public int GetIndexOf(char c) {
        // !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~
        switch(c) {
            case ' ':
                return 0;
            case '!':
                return 1;
            case '\"':
                return 2;
            case '#':
                return 3;
            case '$':
                return 4;
            case '%':
                return 5;
            case '&':
                return 6;
            case '\'':
                return 7;
            case '(':
                return 8;
            case ')':
                return 9;
            case '*':
                return 10;
            case '+':
                return 11;
            case ',':
                return 12;
            case '-':
                return 13;
            case '.':
                return 14;
            case '/':
                return 15;
            case '0':
                return 16;
            case '1':
                return 17;
            case '2':
                return 18;
            case '3':
                return 19;
            case '4':
                return 20;
            case '5':
                return 21;
            case '6':
                return 22;
            case '7':
                return 23;
            case '8':
                return 24;
            case '9':
                return 25;
            case ':':
                return 26;                
            case ';':
                return 27;                
            case '<':
                return 28;                
            case '=':
                return 29;                
            case '>':
                return 30;                
            case '?':
                return 31;                
            case '@':
                return 32;                
            case 'A':
                return 33;                
            case 'B':
                return 34;                
            case 'C':
                return 35;                
            case 'D':
                return 36;                
            case 'E':
                return 37;
            case 'F':
                return 38;
            case 'G':
                return 39;
            case 'H':
                return 40;
            case 'I':
                return 41;
            case 'J':
                return 42;
            case 'K':
                return 43;
            case 'L':
                return 44;
            case 'M':
                return 45;
            case 'N':
                return 46;
            case 'O':
                return 47;
            case 'P':
                return 48;
            case 'Q':
                return 49;
            case 'R':
                return 50;
            case 'S':
                return 51;
            case 'T':
                return 52;
            case 'U':
                return 53;
            case 'V':
                return 54;
            case 'W':
                return 55;
            case 'X':
                return 56;
            case 'Y':
                return 57;
            case 'Z':
                return 58;
            case '[':
                return 59;
            case '\\':
                return 60;
            case ']':
                return 61;
            case '^':
                return 62;
            case '_':
                return 63;
            case '`':
                return 64;
            case 'a':
                return 65;
            case 'b':
                return 66;
            case 'c':
                return 67;
            case 'd':
                return 68;
            case 'e':
                return 69;
            case 'f':
                return 70;
            case 'g':
                return 71;
            case 'h':
                return 72;
            case 'i':
                return 73;
            case 'j':
                return 74;
            case 'k':
                return 75;
            case 'l':
                return 76;
            case 'm':
                return 77;
            case 'n':
                return 78;
            case 'o':
                return 79;
            case 'p':
                return 80;
            case 'q':
                return 81;
            case 'r':
                return 82;
            case 's':
                return 83;
            case 't':
                return 84;
            case 'u':
                return 85;
            case 'v':
                return 86;
            case 'w':
                return 87;
            case 'x':
                return 88;
            case 'y':
                return 89;
            case 'z':
                return 90;
            case '{':
                return 91;
            case '|':
                return 92;
            case '}':
                return 93;
            case '~':
                return 94;
            default:
                return -1;
        }
    }
    
    /**
     * 
     * 
     * @param s
     * @return 
     */
    public int GetIndexOf(String s) {
        char c = s.charAt(0);
        return GetIndexOf(c);
    }
    
    /**
     * 
     * 
     * @param i
     * @return 
     */
    public String GetStrAt(int i) {
        return GetCharAt(i) + "";
    }
    
    /**
     * 
     * 
     * @param i
     * @return 
     */
    public Character GetCharAt(int i) {
        switch(i) {
            case 0:
                return ' ';
            case 1:
                return '!';
            case 2:
                return '\"';
            case 3:
                return '#';
            case 4:
                return '$';
            case 5:
                return '%';
            case 6:
                return '&';
            case 7:
                return '\'';
            case 8:
                return '(';
            case 9:
                return ')';
            case 10:
                return '*';
            case 11:
                return '+';
            case 12:
                return ',';
            case 13:
                return '-';
            case 14:
                return '.';
            case 15:
                return '/';
            case 16:
                return '0';
            case 17:
                return '1';
            case 18:
                return '2';
            case 19:
                return '3';
            case 20:
                return '4';
            case 21:
                return '5';
            case 22:
                return '6';
            case 23:
                return '7';
            case 24:
                return '8';
            case 25:
                return '9';
            case 26:
                return ':';                
            case 27:
                return ';';                
            case 28:
                return '<';                
            case 29:
                return '=';                
            case 30:
                return '>';                
            case 31:
                return '?';                
            case 32:
                return '@';                
            case 33:
                return 'A';                
            case 34:
                return 'B';                
            case 35:
                return 'C';                
            case 36:
                return 'D';                
            case 37:
                return 'E';
            case 38:
                return 'F';
            case 39:
                return 'G';
            case 40:
                return 'H';
            case 41:
                return 'I';
            case 42:
                return 'J';
            case 43:
                return 'K';
            case 44:
                return 'L';
            case 45:
                return 'M';
            case 46:
                return 'N';
            case 47:
                return 'O';
            case 48:
                return 'P';
            case 49:
                return 'Q';
            case 50:
                return 'R';
            case 51:
                return 'S';
            case 52:
                return 'T';
            case 53:
                return 'U';
            case 54:
                return 'V';
            case 55:
                return 'W';
            case 56:
                return 'X';
            case 57:
                return 'Y';
            case 58:
                return 'Z';
            case 59:
                return '[';
            case 60:
                return '\\';
            case 61:
                return ']';
            case 62:
                return '^';
            case 63:
                return '_';
            case 64:
                return '`';
            case 65:
                return 'a';
            case 66:
                return 'b';
            case 67:
                return 'c';
            case 68:
                return 'd';
            case 69:
                return 'e';
            case 70:
                return 'f';
            case 71:
                return 'g';
            case 72:
                return 'h';
            case 73:
                return 'i';
            case 74:
                return 'j';
            case 75:
                return 'k';
            case 76:
                return 'l';
            case 77:
                return 'm';
            case 78:
                return 'n';
            case 79:
                return 'o';
            case 80:
                return 'p';
            case 81:
                return 'q';
            case 82:
                return 'r';
            case 83:
                return 's';
            case 84:
                return 't';
            case 85:
                return 'u';
            case 86:
                return 'v';
            case 87:
                return 'w';
            case 88:
                return 'x';
            case 89:
                return 'y';
            case 90:
                return 'z';
            case 91:
                return '{';
            case 92:
                return '|';
            case 93:
                return '}';
            case 94:
                return '~';
            default:
                return null;
        }
    }
    
    /**
     * 
     * 
     * @return 
     */
    public String GetText() {
        return text;
    }
    
    /**
     * 
     * 
     * @param s 
     */
    public void SetText(String s) {
        if(s != null) {
            int len = s.length();
            char c;
            int idx = 0;
            int totalWidth = 0;
            int i = 0;
            
            for(i = 0; i < len; i++) {
                c = s.charAt(i);
                idx = GetIndexOf(c);
                totalWidth += w;
            }

            int posX = 0;            
            MmgDrawableBmpSet bmpSet = MmgHelper.CreateDrawableBmpSet(totalWidth, src.GetHeight(), true);
            MmgBmp img = null;
            for(i = 0; i < len; i++) {
                c = s.charAt(i);
                idx = GetIndexOf(c);
                img = chars[idx];
                bmpSet.p.DrawBmp(img, new MmgRect(0, 0, img.GetHeight(), img.GetWidth()), new MmgRect(posX, 0, img.GetHeight(), posX + img.GetWidth()));
                posX += widths[idx];                
            }            
            
            dst = bmpSet.img;
        } else {
            dst = MmgHelper.CreateFilledBmp(DEFAULT_NULL_WIDTH, DEFAULT_NULL_HEIGHT, MmgColor.GetDarkRed());
        }
    }

    /**
     * 
     * 
     * @return 
     */
    @Override
    public MmgColor GetMmgColor() {
        return dst.GetMmgColor();
    }
    
    /**
     * 
     * 
     * @param c 
     */
    @Override
    public void SetMmgColor(MmgColor c) {
        super.SetMmgColor(c);
        dst.SetMmgColor(c);
    }
    
    @Override
    public int GetX() {
        return dst.GetX();
    }
    
    /**
     * 
     * 
     * @param x 
     */
    @Override
    public void SetX(int x) {
        super.SetX(x);
        dst.SetX(x);
    }
       
    /**
     * 
     * 
     * @return 
     */
    @Override
    public int GetY() {
        return dst.GetY();
    }
    
    /**
     * 
     * 
     * @param y 
     */
    @Override
    public void SetY(int y) {
        super.SetY(y);
        dst.SetY(y);
    }    
    
    /**
     * 
     * 
     * @return 
     */
    @Override 
    public MmgVector2 GetPosition() {
        return dst.GetPosition();
    }    
    
    /**
     * 
     * 
     * @param x
     * @param y 
     */
    @Override
    public void SetPosition(int x, int y) {
        super.SetPosition(x, y);
        dst.SetPosition(x, y);
    }
        
    /**
     * 
     * 
     * @param v 
     */
    @Override
    public void SetPosition(MmgVector2 v) {
        super.SetPosition(v);
        dst.SetPosition(v);
    }
          
    /**
     * 
     * 
     * @return 
     */
    @Override
    public int GetWidth() {
        return dst.GetWidth();
    }
    
    /**
     * 
     * 
     * @param w 
     */
    @Override 
    public void SetWidth(int w) {
        super.SetWidth(w);
        dst.SetWidth(w);
    }
    
    /**
     * 
     * 
     * @return 
     */
    @Override 
    public int GetHeight() {
        return dst.GetHeight();
    }
    
    @Override 
    public void SetHeight(int h) {
        super.SetHeight(h);
        dst.SetHeight(h);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public int[] GetWidths() {
        return widths;
    }

    /**
     * 
     * 
     * @param ws 
     */
    public void SetWidths(int[] ws) {
        widths = ws;
    }
       
    /**
     * 
     * 
     * @return 
     */
    public MmgBmp GetSrc() {
        return src;
    }

    /**
     * 
     * 
     * @param b 
     */
    public void SetSrc(MmgBmp b) {
        src = b;
    }

    /**
     * 
     * 
     * @return 
     */
    public MmgBmp GetDst() {
        return dst;
    }

    /**
     * 
     * 
     * @param b 
     */
    public void SetDst(MmgBmp b) {
        dst = b;
    }    
    
    /**
     * 
     * 
     * @return 
     */
    public MmgBmp[] GetChars() {
        return chars;
    }

    /**
     * 
     * 
     * @param c 
     */
    public void SetChars(MmgBmp[] c) {
        chars = c;
    }
    
    /**
     * 
     * 
     * @return 
     */
    public int GetCharCount() {
        return chars.length;
    }
    
    /**
     * 
     * 
     * @param i
     * @return 
     */
    public MmgBmp GetChar(int i) {
        return chars[i];
    }
    
    /**
     * 
     * 
     * @param b
     * @param i 
     */
    public void SetChar(MmgBmp b, int i) {
        chars[i] = b;
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
            p.DrawBmp(dst, GetPosition());
        }
    }    

    /**
     * A method used to check the equality of this MmgBmpFont when compared to another MmgBmpFont.
     * Compares object fields to determine equality.
     * 
     * @param obj     The MmgBmpFont object to compare to.
     * @return      A boolean indicating if the two objects are equal or not.
     */ 
    public boolean Equals(MmgBmpFont obj) {
        if(obj == null) {
            return false;
        } else if(obj.equals(this)) {
            return true;
        }
        
        boolean ret = true;
        if(
            super.Equals((MmgObj)obj)
            && ((obj.GetSrc() == null && GetSrc() == null) || (obj.GetSrc() != null && GetSrc() != null && obj.GetSrc().Equals(GetSrc())))
            && ((obj.GetDst() == null && GetDst() == null) || (obj.GetDst() != null && GetDst() != null && obj.GetDst().Equals(GetDst())))
            && ((obj.GetText() == null && GetText() == null) || (obj.GetText() != null && GetText() != null && obj.GetText().equals(GetText())))
        ) {
            int len = obj.GetCharCount();
            int i = 0;
            for(i = 0; i < len; i++) {
                if(!obj.GetChar(i).Equals(GetChar(i))) {
                    ret = false;
                    break;
                }
            }
            
            len = obj.GetWidths().length;
            for(i = 0; i < len; i++) {
                if(!(obj.GetWidths()[i] == GetWidths()[i])) {
                    ret = false;
                    break;
                }
            }            
        } else {
            ret = false;
        }
        return ret;
    }
}

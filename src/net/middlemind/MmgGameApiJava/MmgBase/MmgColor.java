package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.*;

/**
 * Class that wraps the lower level color object. Created by Middlemind Games
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
     * @param m Input MmgColor object.
     */
    public MmgColor(MmgColor m) {
        c = m.GetColor();
    }

    /**
     * Constructor that sets the color to the given argument.
     *
     * @param C The color to set the object.
     */
    public MmgColor(Color C) {
        c = C;
    }

    /**
     * Clones the current object.
     *
     * @return Clone of this object.
     */
    public MmgColor Clone() {
        return new MmgColor(c);
    }

    /**
     * Static helper method returns white.
     *
     * @return The color white.
     */
    public static MmgColor GetWhite() {
        return new MmgColor(Color.WHITE);
    }

    /**
     * Static helper method returns black.
     *
     * @return The color black.
     */
    public static MmgColor GetBlack() {
        return new MmgColor(Color.BLACK);
    }

    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetRed() {
        return new MmgColor(Color.RED);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetBlue() {
        return new MmgColor(Color.BLUE);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetGreen() {
        return new MmgColor(Color.GREEN);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetCyan() {
        return new MmgColor(Color.CYAN);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetGray() {
        return new MmgColor(Color.GRAY);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetDarkGray() {
        return new MmgColor(Color.DARK_GRAY);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetLightGray() {
        return new MmgColor(Color.LIGHT_GRAY);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetMagenta() {
        return new MmgColor(Color.MAGENTA);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetOrange() {
        return new MmgColor(Color.ORANGE);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetPink() {
        return new MmgColor(Color.PINK);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetYellow() {
        return new MmgColor(Color.YELLOW);
    }
     
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetLimeGreen() {
        return new MmgColor(Color.decode("#DAF7A6"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetYellowOrange() {
        return new MmgColor(Color.decode("#FFC300"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetRedOrange() {
        return new MmgColor(Color.decode("#FF5733"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetPurpleRed() {
        return new MmgColor(Color.decode("#C70039"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetDarkRed() {
        return new MmgColor(Color.decode("#900C3F"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetDarkBlue() {
        return new MmgColor(Color.decode("#0000A0"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetLightBlue() {
        return new MmgColor(Color.decode("#ADD8E6"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetOlive() {
        return new MmgColor(Color.decode("#808000"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetBrown() {
        return new MmgColor(Color.decode("#A52A2A"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetMaroon() {
        return new MmgColor(Color.decode("#800000"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetGunMetalGrey() {
        return new MmgColor(Color.decode("#2C3539"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetNight() {
        return new MmgColor(Color.decode("#0C090A"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetMidnight() {
        return new MmgColor(Color.decode("#2B1B17"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetCharcoal() {
        return new MmgColor(Color.decode("#34282C"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetDarkSlateGrey() {
        return new MmgColor(Color.decode("#25383C"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetOil() {
        return new MmgColor(Color.decode("#3B3131"));
    }

    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetCalmBlue() {
        return new MmgColor(Color.decode("#3B8EFF"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetBlackCat() {
        return new MmgColor(Color.decode("#413839"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetIridium() {
        return new MmgColor(Color.decode("#3D3C3A"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetGreyWolf() {
        return new MmgColor(Color.decode("#504A4B"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetGreyDolphin() {
        return new MmgColor(Color.decode("#5C5858"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetCarbonGrey() {
        return new MmgColor(Color.decode("#625D5D"));
    }

    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetBattleshipGrey() {
        return new MmgColor(Color.decode("#848482"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetGreyCloud() {
        return new MmgColor(Color.decode("#B6B6B4"));
    }

    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetGreyGoose() {
        return new MmgColor(Color.decode("#D1D0CE"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetPlatinum() {
        return new MmgColor(Color.decode("#E5E4E2"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetMetallicSilver() {
        return new MmgColor(Color.decode("#BCC6CC"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetBlueGrey() {
        return new MmgColor(Color.decode("#98AFC7"));
    }
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetSlateBlue() {
        return new MmgColor(Color.decode("#737CA1"));
    }
    
    //steel blue: #4863A0
    //navy blue: #000080
    //blue whale: #342D7E
    //sapphire blue: #2554C7
    //blue orcid: #1F45FC
    //blue lotus: #6960EC
    //crystal blue: #5CB3FF
    //power blue: #C6DEFF
    //blue green: #7BCCB5
    //teal: #008080
    //sea green: #4E8975
    //camouflage green: #78866B
    //dark forest green: #254117
    //clover green: #3EA055
    //zombie green: #54C571
    //mint green: #98FF98
    //harvest gold: #EDE275
    //corn yellow: #FFF380
    //blonde: #FBF6D9
    //tan brown: #ECE5B6
    //peach: #FFE5B4
    //mustard: #FFDB58
    //bright gold: #FDD017
    //cantaloupe: #FFA62F
    //deep peach: #FFCBA4
    //sand: #C2B280
    //brass: #B5A642
    //bronze: #CD7F32
    //copper: #B87333
    //wood: #966F33
    //mocha: #493D26
    //coffee: #6F4E37
    //sepia: #7F462C
    //pumpkin: #F87217
    //mango: #FF8040
    //dark orange: #F88017
    //tangerine: #E78A61
    //light coral: #E77471
    //scarlet: #FF2400
    //lava red: #E42217
    //grape fruit: #DC381F
    //cherry red: #C24641
    //cranberry: #9F000F
    //burgundy: #8C001A
    //chestnut: #954535
    //maroon: #810541
    //plum: #7D0552
    //puce: #7F5A58
    //rose: #E8ADAA
    //rose gold: #ECC5C0
    //light pink: #FAAFBA
    //flamingo pink: #F9A7B0
    //hot pink: #F660AB
    //magenta: #FF00FF
    //purple iris: #571B7E
    //purple haze: #4E387E
    //grape: #5E5A80
    //dark violet: #842DCE
    //lavender blue: #E3E4FA
    
    /**
     * 
     * 
     * @param htmlColor
     * @return 
     */
    public static MmgColor GetDecodedColor(String htmlColor) {
        return new MmgColor(Color.decode(htmlColor));
    }    
    
    /**
     * 
     * 
     * @return 
     */
    public static MmgColor GetTransparent() {
        return new MmgColor(new Color(0f, 0f, 0f, 1f));
    }    
    
    /**
     * Returns the color of this MmgColor object.
     *
     * @return The color of this object.
     */
    public Color GetColor() {
        return c;
    }

    /**
     * Sets the color of this MmgColor object.
     *
     * @param C The color of this object.
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
        if (c != null && c.GetColor().equals(GetColor()) == true) {
            return true;
        } else {
            return false;
        }
    }
}

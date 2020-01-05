package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * A class that represents the screen data of the game. Also provides helper
 * methods for scaling. Created on June 1, 2005, 10:57 PM by Middlemind Games
 * Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class MmgScreenData {

    /**
     * Default screen width.
     */
    public final int DEFAULT_WIDTH = 1024;

    /**
     * Default screen height.
     */
    public final int DEFAULT_HEIGHT = 768;

    /**
     * Game screen width.
     */
    private static int gameWidth;

    /**
     * Game screen height.
     */
    private static int gameHeight;

    /**
     * Game screen offset, X axis.
     */
    private static int gameLeft;

    /**
     * Game screen offset, Y axis.
     */
    private static int gameTop;

    /**
     * Screen width.
     */
    private static int screenWidth;

    /**
     * Screen height.
     */
    private static int screenHeight;

    /**
     * A class helper variable for scaling the game to match the screen size.
     */
    private static double scaleX;

    /**
     * A class helper variable for scaling the game to match the screen size.
     */
    private static double scaleY;

    /**
     * A class helper variable for setting X axis scaling on.
     */
    private static boolean scaleXOn;

    /**
     * A class helper variable for setting Y axis scaling on.
     */
    private static boolean scaleYOn;

    /**
     * A class helper variable for the scaling vector.
     */
    private static MmgVector2 scaleVec;

    /**
     * A class helper variable for the position vector.
     */
    private static MmgVector2 posVec;

    /**
     * Constructor for this class that sets all default values.
     */
    public MmgScreenData() {
        MmgScreenData.gameWidth = DEFAULT_WIDTH;
        MmgScreenData.gameHeight = DEFAULT_HEIGHT;
        MmgScreenData.gameLeft = 0;
        MmgScreenData.gameTop = 0;
        MmgScreenData.screenWidth = DEFAULT_WIDTH;
        MmgScreenData.screenHeight = DEFAULT_HEIGHT;
        MmgScreenData.scaleX = 1;
        MmgScreenData.scaleY = 1;
        MmgScreenData.scaleVec = new MmgVector2(MmgScreenData.scaleX, MmgScreenData.scaleY);
        MmgScreenData.posVec = new MmgVector2(MmgScreenData.gameLeft, MmgScreenData.gameTop);
    }

    /**
     * Constructor for this class that sets the screen width and height to the
     * same given values as the game width and height.
     *
     * @param w The game and screen width.
     * @param h The game and screen height.
     */
    public MmgScreenData(int w, int h) {
        MmgScreenData.gameWidth = w;
        MmgScreenData.gameHeight = h;
        MmgScreenData.gameLeft = 0;
        MmgScreenData.gameTop = 0;
        MmgScreenData.screenWidth = w;
        MmgScreenData.screenHeight = h;
        MmgScreenData.scaleX = 1.0f;
        MmgScreenData.scaleY = 1.0f;
        MmgScreenData.scaleVec = new MmgVector2(MmgScreenData.scaleX, MmgScreenData.scaleY);
        MmgScreenData.posVec = new MmgVector2(MmgScreenData.gameLeft, MmgScreenData.gameTop);
    }

    /**
     * Constructor for this class that sets the screen dimensions and the game
     * dimensions.
     *
     * @param ScreenWidth The screen width.
     * @param ScreenHeight The screen height.
     * @param GameWidth The game width.
     * @param GameHeight The game height.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgScreenData(int ScreenWidth, int ScreenHeight, int GameWidth, int GameHeight) {
        MmgScreenData.screenWidth = ScreenWidth;
        MmgScreenData.screenHeight = ScreenHeight;
        MmgScreenData.gameWidth = GameWidth;
        MmgScreenData.gameHeight = GameHeight;
        MmgScreenData.CalculateScaleAndOffset();
        MmgScreenData.scaleVec = new MmgVector2(MmgScreenData.scaleX, MmgScreenData.scaleY);
        MmgScreenData.posVec = new MmgVector2(MmgScreenData.gameLeft, MmgScreenData.gameTop);
    }

    /**
     * A string representation of the screen data.
     *
     * @return A string representing the screen data state.
     */
    public static String ToString() {
        String ret = "";
        ret += "Screen Width: " + MmgScreenData.GetScreenWidth() + System.lineSeparator();
        ret += "Screen Height: " + MmgScreenData.GetScreenHeight() + System.lineSeparator();
        ret += "Game Width: " + MmgScreenData.GetGameWidth() + System.lineSeparator();
        ret += "Game Height: " + MmgScreenData.GetGameHeight() + System.lineSeparator();
        ret += "Game Offset X: " + MmgScreenData.GetGameLeft() + System.lineSeparator();
        ret += "Game Offset Y: " + MmgScreenData.GetGameTop() + System.lineSeparator();
        ret += "Scale X: " + MmgScreenData.GetScaleX() + System.lineSeparator();
        ret += "Scale Y: " + MmgScreenData.GetScaleY() + System.lineSeparator();
        return ret;
    }

    /**
     * Gets the game width.
     *
     * @return The game width.
     */
    public static int GetGameWidth() {
        return MmgScreenData.gameWidth;
    }

    /**
     * Sets the game width.
     *
     * @param w The game width.
     */
    public static void SetGameWidth(int w) {
        MmgScreenData.gameWidth = w;
    }

    /**
     * Gets the game top position, Y axis.
     *
     * @return The game top position, Y axis.
     */
    public static int GetGameTop() {
        return MmgScreenData.gameTop;
    }

    /**
     * Gets the game bottom position, Y axis.
     *
     * @return The game bottom position, Y axis.
     */    
    public static int GetGameBottom() {
        return (MmgScreenData.gameTop + MmgScreenData.gameHeight);
    }
    
    /**
     * Sets the game top position, Y axis.
     *
     * @param t The game top position, Y axis.
     */
    public static void SetGameTop(int t) {
        MmgScreenData.gameTop = t;
    }

    /**
     * Gets the game left position, X axis.
     *
     * @return The game left position, X axis.
     */
    public static int GetGameLeft() {
        return MmgScreenData.gameLeft;
    }

    /**
     * Gets the game right position, X axis.
     *
     * @return The game right position, X axis.
     */
    public static int GetGameRight() {
        return (MmgScreenData.gameLeft + MmgScreenData.gameWidth);
    }    
    
    /**
     * Sets the game left position, Y axis.
     *
     * @param l The game left position, Y axis.
     */
    public static void SetGameLeft(int l) {
        MmgScreenData.gameLeft = l;
    }

    /**
     * Gets the game screen height.
     *
     * @return The game screen height.
     */
    public static int GetGameHeight() {
        return MmgScreenData.gameHeight;
    }

    /**
     * Sets the game screen height.
     *
     * @param h The game screen height.
     */
    public static void SetGameHeight(int h) {
        MmgScreenData.gameHeight = h;
    }

    /**
     * Sets the screen width.
     *
     * @param w The screen width.
     */
    public static void SetScreenWidth(int w) {
        MmgScreenData.screenWidth = w;
    }

    /**
     * Gets the screen width.
     *
     * @return The screen width.
     */
    public static int GetScreenWidth() {
        return MmgScreenData.screenWidth;
    }

    /**
     * Sets the screen height.
     *
     * @param h The screen height.
     */
    public static void SetScreenHeight(int h) {
        MmgScreenData.screenHeight = h;
    }

    /**
     * Gets the screen height.
     *
     * @return The screen height.
     */
    public static int GetScreenHeight() {
        return MmgScreenData.screenHeight;
    }

    /**
     * Gets scale X.
     *
     * @return The X scale value.
     */
    public static double GetScaleX() {
        return MmgScreenData.scaleX;
    }

    /**
     * Sets scale X.
     *
     * @param x The X scale value.
     */
    public static void SetScaleX(double x) {
        MmgScreenData.scaleX = x;
    }

    /**
     * Gets scale Y.
     *
     * @return The scale Y value.
     */
    public static double GetScaleY() {
        return MmgScreenData.scaleY;
    }

    /**
     * Sets scale Y.
     *
     * @param y The scale Y value.
     */
    public static void SetScaleY(double y) {
        MmgScreenData.scaleY = y;
    }

    /**
     * Gets if scale X is on.
     *
     * @return If scale X is on.
     */
    public static boolean GetScaleXOn() {
        return MmgScreenData.scaleXOn;
    }

    /**
     * Sets if scale X is on.
     *
     * @param b If scale X is on.
     */
    public static void SetScaleXOn(boolean b) {
        MmgScreenData.scaleXOn = b;
    }

    /**
     * Gets if scale Y is on.
     *
     * @return If scale Y is on.
     */
    public static boolean GetScaleYOn() {
        return MmgScreenData.scaleYOn;
    }

    /**
     * Sets if scale Y is on.
     *
     * @param b If scale Y is on.
     */
    public static void SetScaleYOn(boolean b) {
        MmgScreenData.scaleYOn = b;
    }

    /**
     * Gets the scale vector.
     *
     * @return The scale vector.
     */
    public static MmgVector2 GetScale() {
        return MmgScreenData.scaleVec;
    }

    /**
     * Gets the position vector.
     *
     * @return The position vector.
     */
    public static MmgVector2 GetPosition() {
        return MmgScreenData.posVec;
    }

    /**
     * Calculates the game screen's top offset.
     */
    private static void CalculateTop() {
        MmgScreenData.gameTop = (MmgScreenData.screenHeight - MmgScreenData.gameHeight) / 2;
    }

    /**
     * Calculates the game screen's left offset.
     */
    private static void CalculateLeft() {
        MmgScreenData.gameLeft = (MmgScreenData.screenWidth - MmgScreenData.gameWidth) / 2;
    }

    /**
     * Calculates the scale value on the X axis for the game. Based on the
     * screen dimensions and the default game width and height.
     */
    @SuppressWarnings("UnusedAssignment")
    private static void CalculateScaleX() {
        double test = 32.0f;
        double resF;
        double resI;
        double prctDiffX;
        int panic = 5000;
        int count;
        int resIi = 0;
        double dir = -1;
        double diff = 0;

        prctDiffX = ((double) MmgScreenData.screenWidth / (double) MmgScreenData.gameWidth);
        dir = -1;
        resF = test * prctDiffX;
        resI = (float) ((int) resF);
        resIi = (int) resI;
        count = 0;
        diff = Math.abs((resF - resI));

        while ((diff > 0.01 || resIi % 2 != 0) && count < panic) {
            prctDiffX += (dir * 0.000250);
            resF = test * prctDiffX;
            resI = (double) ((int) resF);
            resIi = (int) resI;
            count++;
            diff = Math.abs((resF - resI));
        }

        MmgScreenData.scaleXOn = true;
        MmgScreenData.scaleYOn = true;
        MmgScreenData.scaleX = prctDiffX;
        MmgScreenData.scaleY = prctDiffX;
        MmgScreenData.gameWidth *= MmgScreenData.scaleX;
        MmgScreenData.gameHeight *= MmgScreenData.scaleY;
        CalculateTop();
        CalculateLeft();
        MmgApiUtils.wr("Found X Scale: " + prctDiffX + ", ResF: " + resF + ", ResI: " + resI + ", Diff: " + diff + ", Count: " + count);
    }

    /**
     * Calculates the scale value on the Y axis for the game. Based on the
     * screen dimensions and the default game width and height.
     */
    @SuppressWarnings("UnusedAssignment")
    private static void CalculateScaleY() {
        double test = 32.0f;
        double resF;
        double resI;
        double prctDiffY;
        int panic = 5000;
        int count;
        int resIi = 0;
        double dir = -1;
        double diff = 0;

        prctDiffY = ((double) MmgScreenData.screenHeight / (double) MmgScreenData.gameHeight);
        dir = -1;
        resF = test * prctDiffY;
        resI = (double) ((int) resF);
        resIi = (int) resI;
        count = 0;
        diff = Math.abs((resF - resI));

        while ((diff > 0.01 || resIi % 2 != 0) && count < panic) {
            prctDiffY += (dir * 0.000250);
            resF = test * prctDiffY;
            resI = (double) ((int) resF);
            resIi = (int) resI;
            count++;
            diff = Math.abs((resF - resI));
        }

        MmgScreenData.scaleXOn = true;
        MmgScreenData.scaleYOn = true;
        MmgScreenData.scaleX = prctDiffY;
        MmgScreenData.scaleY = prctDiffY;
        MmgScreenData.gameWidth *= MmgScreenData.scaleX;
        MmgScreenData.gameHeight *= MmgScreenData.scaleY;
        CalculateTop();
        CalculateLeft();
        MmgApiUtils.wr("Found Y Scale: " + prctDiffY + ", ResF: " + resF + ", ResI: " + resI + ", Diff: " + diff + ", Count: " + count);
    }

    /**
     * Calculates the scale and offset needed to adjust the game screen inside
     * the screen dimensions.
     */
    public static void CalculateScaleAndOffset() {
        if (MmgScreenData.screenHeight == MmgScreenData.gameHeight && MmgScreenData.screenWidth == MmgScreenData.gameWidth) {
            MmgScreenData.scaleX = 1.0f;
            MmgScreenData.scaleY = 1.0f;
            MmgScreenData.gameTop = 0;
            MmgScreenData.gameLeft = 0;
        } else {
            boolean sX = true;
            boolean sY = true;

            /*
             if(MmgScreenData.screenHeight > MmgScreenData.gameHeight) {
                MmgScreenData.scaleY = 1.0f;
                MmgScreenData.CalculateTop();
                MmgScreenData.CalculateLeft();
             }else{
             */
                //calculate the scale Y
                //sY = true;
            //}
            
            /*
             if(MmgScreenData.screenWidth > MmgScreenData.gameWidth) {
                MmgScreenData.scaleX = 1.0f;
                MmgScreenData.CalculateTop();
                MmgScreenData.CalculateLeft();
             }else{
             */
                //calculate the scale X
                //sX = true;
            //}
            
            MmgApiUtils.wr("ScaleX: " + sX + " ScaleY: " + sY);
            if (sX == true && sY == false) {
                //scale X
                CalculateScaleX();

            } else if (sY == true && sX == false) {
                //scale Y
                CalculateScaleY();

            } else if (sX == true && sY == true) {
                //scale both
                CalculateScaleX();
                if (MmgScreenData.gameHeight > MmgScreenData.screenHeight) {
                    CalculateScaleY();
                }
            }
        }
    }
}

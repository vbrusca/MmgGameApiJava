package com.middlemind.Odroid_Tutorial2_Pong;

import com.middlemind.Odroid.GamePanel.GameStates;
import com.middlemind.Odroid.GameSettings;
import com.middlemind.Odroid.GenericEventMessage;
import com.middlemind.Odroid.Helper;
import com.middlemind.Odroid.Screen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmpScaler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFont;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFontData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;

/**
 * A game screen object, ScreenGame, that extends the MmgGameScreen base
 * class. This class is for testing new UI widgets, etc.
 *
 * @author Victor G. Brusca
 * 03/15/2020
 */
public class ScreenGame extends Screen {

    /**
     * The game state this screen has.
     */
    protected final GameStates gameState;

    /**
     * The GamePanel that owns this game screen. Usually a JPanel instance that
     * holds a reference to this game screen object.
     */
    protected final GamePanel owner;
        
    private MmgBmp bground;
    private MmgBmp padelLeft;
    private MmgBmp padelRight;
    private MmgFont scoreLeft;
    private MmgFont scoreRight;
    private MmgFont exit;
    
    private float padleMinAccel = 0.0f;
    private float padleMaxAccel = 0.6f;
    private float padleCurrentAccel = 0.0f;
    private float padleAccelChange = 0.1f;
    private int padleMinSpeed = 10;
    private int padleMaxSpeed = 20;
    private int padleCurrentSpeed = padleMinSpeed;
    private int padleMovePerFrame = (int)(padleCurrentSpeed * (MmgPongClone.FPS/60.0f)); 
    private boolean padle1MoveUp = false;
    private boolean padle1MoveDown = false;    
    
    /**
     * Constructor, sets the game state associated with this screen, and sets
     * the owner GamePanel instance.
     *
     * @param State The game state of this game screen.
     * @param Owner The owner of this game screen.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ScreenGame(GameStates State, GamePanel Owner) {
        super(State, Owner);
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("Odroid.ScreenGame: Constructor");
    }

    /**
     * Loads all the resources needed to display this game screen.
     */
    @Override
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());

        String imgId = "";
        
        imgId = "game_board.png";
        bground = MmgHelper.GetBasicCachedBmp(imgId);
        bground = MmgBmpScaler.ScaleMmgBmpToGameScreen(bground, false);
        MmgHelper.CenterHorAndVert(bground);
        AddObj(bground);
        
        padelLeft = MmgHelper.CreateFilledBmp(MmgHelper.ScaleValue(20), h/5, MmgColor.GetWhite());
        padelLeft.SetPosition(MmgHelper.ScaleValue(20), GetY() + (h - padelLeft.GetHeight())/2);
        AddObj(padelLeft);
        
        padelRight = MmgHelper.CreateFilledBmp(MmgHelper.ScaleValue(20), h/5, MmgColor.GetWhite());
        padelRight.SetPosition((w - padelRight.GetWidth() - MmgHelper.ScaleValue(20)), GetY() + (h - padelLeft.GetHeight())/2);        
        AddObj(padelRight);
        
        scoreLeft = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreLeft.SetText("00");
        scoreLeft.SetMmgColor(MmgColor.GetWhite());
        scoreLeft.SetPosition(MmgHelper.ScaleValue(10), GetY() + scoreLeft.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(scoreLeft);
        
        scoreRight = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreRight.SetText("00");
        scoreRight.SetMmgColor(MmgColor.GetWhite());
        scoreRight.SetPosition(w - scoreRight.GetWidth() - MmgHelper.ScaleValue(10), GetY() + scoreRight.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(scoreRight);        
        
        exit = MmgFontData.CreateDefaultBoldMmgFontLg();
        exit.SetText("Press B to Exit");
        exit.SetMmgColor(MmgColor.GetWhite());
        exit.SetPosition((w - exit.GetWidth())/2, GetY() + exit.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(exit);        
        
        ready = true;
        pause = false;
    }

    @Override
    public boolean ProcessScreenPress(MmgVector2 v) {
        return ProcessScreenPress(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessScreenPress(int x, int y) {
        return true;
    }

    @Override
    public boolean ProcessScreenRelease(MmgVector2 v) {
        return ProcessScreenPress(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessScreenRelease(int x, int y) {
        return true;
    }
    
    @Override
    public boolean ProcessAClick() {
        return true;
    }
    
    @Override
    public boolean ProcessBClick() {
        return true;
    }
    
    @Override
    public void ProcessDebugClick() {

    }

    @Override
    public boolean ProcessDpadPress(int dir) {
        if(dir == GameSettings.DOWN) {
            padleCurrentAccel += padleAccelChange;
            if(padleCurrentAccel > padleMaxAccel) {
                padleCurrentAccel = padleMaxAccel;
            }
            padleCurrentSpeed = (int)(padleMovePerFrame * padleCurrentAccel);
            padle1MoveDown = true;
            dirty = true;
            return true;
            
        } else if(dir == GameSettings.UP) {
            padleCurrentAccel += padleAccelChange;
            if(padleCurrentAccel > padleMaxAccel) {
                padleCurrentAccel = padleMaxAccel;
            }
            padleCurrentSpeed = (int)(padleMovePerFrame * padleCurrentAccel);            
            padle1MoveUp = true;
            dirty = true;            
            return true;
            
        }
        
        return false;
    }

    @Override
    public boolean ProcessDpadRelease(int dir) {
        if(dir == GameSettings.DOWN) {
            padleCurrentAccel = padleMinAccel;
            padleCurrentSpeed = 0;
            padle1MoveDown = false;
            dirty = true;            
            return true;
            
        } else if(dir == GameSettings.UP) {
            padleCurrentAccel = padleMinAccel;
            padleCurrentSpeed = 0;
            padle1MoveUp = false;
            dirty = true;            
            return true;
            
        }
        
        return false;
    }
    
    @Override
    public boolean ProcessDpadClick(int dir) {
        return true;
    }
    
    @Override
    public boolean ProcessScreenClick(MmgVector2 v) {
        return ProcessScreenClick(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessScreenClick(int x, int y) {        
        return false;
    }    

    @Override
    public void DrawScreen() {
        //run each game frame
        pause = true;
        dirty = false;

        
        pause = false;
    }
    
    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);
        ClearObjs();
        ready = false;
    }

    /**
     * Returns the game state of this game screen.
     *
     * @return The game state of this game screen.
     */
    public GameStates GetGameState() {
        return gameState;
    }

    /**
     * The main drawing routine.
     *
     * @param p An MmgPen object to use for drawing this game screen.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (pause == false && GetIsVisible() == true) {            
            //super.MmgDraw(p);                        
            super.GetObjects().MmgDraw(p);
        } else {
            //do nothing
        }
    }

    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        //handle generic event
    }
}

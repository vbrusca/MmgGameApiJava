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
    private MmgBmp paddleLeft;
    private MmgBmp paddleRight;
    
    private MmgBmp number1;
    private MmgBmp number2;
    private MmgBmp number3;    
    
    private MmgFont scoreLeft;
    private MmgFont scoreRight;
    private MmgFont exit;
    
    private float paddleMinAccel = 16.0f;
    private float paddleMaxAccel = 40.0f;
    private float paddle1CurrentAccel = paddleMinAccel;
    private float paddle2CurrentAccel = paddleMinAccel;    
    private float paddleAccelChange = 8.0f;
    
    private int paddleMinSpeed = 30;
    private int paddleMaxSpeed = 60;
    private int paddle1CurrentSpeed = paddleMinSpeed;
    private int paddle2CurrentSpeed = paddleMinSpeed;    
    
    private int paddle1MovePerFrame = (int)(paddle1CurrentSpeed * (MmgPongClone.FPS/60.0f));
    private int paddle2MovePerFrame = (int)(paddle2CurrentSpeed * (MmgPongClone.FPS/60.0f));
    private boolean paddle1MoveUp = false;
    private boolean paddle1MoveDown = false;    
    
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
        
        paddleLeft = MmgHelper.CreateFilledBmp(MmgHelper.ScaleValue(20), h/5, MmgColor.GetWhite());
        paddleLeft.SetPosition(MmgHelper.ScaleValue(20), GetY() + (h - paddleLeft.GetHeight())/2);
        AddObj(paddleLeft);
                
        paddleRight = MmgHelper.CreateFilledBmp(MmgHelper.ScaleValue(20), h/5, MmgColor.GetWhite());
        paddleRight.SetPosition((w - paddleRight.GetWidth() - MmgHelper.ScaleValue(20)), GetY() + (h - paddleLeft.GetHeight())/2);        
        AddObj(paddleRight);
        
        scoreLeft = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreLeft.SetText("00");
        scoreLeft.SetMmgColor(MmgColor.GetRed());
        scoreLeft.SetPosition(MmgHelper.ScaleValue(10) + paddleLeft.GetX() + paddleLeft.GetWidth(), GetY() + scoreLeft.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(scoreLeft);
        
        scoreRight = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreRight.SetText("00");
        scoreRight.SetMmgColor(MmgColor.GetRed());
        scoreRight.SetPosition(w - scoreRight.GetWidth() - MmgHelper.ScaleValue(10) - paddleLeft.GetX() - paddleRight.GetWidth(), GetY() + scoreRight.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(scoreRight);        
        
        exit = MmgFontData.CreateDefaultBoldMmgFontLg();
        exit.SetText("Press B to Exit");
        exit.SetMmgColor(MmgColor.GetRed());
        exit.SetPosition((w - exit.GetWidth())/2, GetY() + exit.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(exit);        
        
        imgId = "number_1_lrg.png";
        number1 = MmgHelper.GetBasicCachedBmp(imgId);
        MmgHelper.CenterHorAndVert(number1);
        number1.SetIsVisible(false);
        AddObj(number1);
        
        imgId = "number_2_lrg.png";
        number2 = MmgHelper.GetBasicCachedBmp(imgId);
        MmgHelper.CenterHorAndVert(number2);
        number2.SetIsVisible(false);
        AddObj(number2);

        imgId = "number_3_lrg.png";
        number3 = MmgHelper.GetBasicCachedBmp(imgId);
        MmgHelper.CenterHorAndVert(number3);
        number3.SetIsVisible(false);
        AddObj(number3);       
        
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
        //Helper.wr("ProcessDpadPress: " + dir);
        if(dir == GameSettings.DOWN) {
            paddle1CurrentAccel += paddleAccelChange;
            if(paddle1CurrentAccel > paddleMaxAccel) {
                paddle1CurrentAccel = paddleMaxAccel;
            }
            
            paddle1CurrentSpeed = paddle1MovePerFrame + (int)(paddle1CurrentAccel);
            if(paddle1CurrentSpeed > paddleMaxSpeed) {
                paddle1CurrentSpeed = paddleMaxSpeed;
            }
            
            paddle1MoveDown = true;
            dirty = true;
            return true;
            
        } else if(dir == GameSettings.UP) {
            paddle1CurrentAccel += paddleAccelChange;
            if(paddle1CurrentAccel > paddleMaxAccel) {
                paddle1CurrentAccel = paddleMaxAccel;
            }
            
            paddle1CurrentSpeed = paddle1MovePerFrame + (int)(paddle1CurrentAccel);              
            if(paddle1CurrentSpeed > paddleMaxSpeed) {
                paddle1CurrentSpeed = paddleMaxSpeed;
            }            
            
            paddle1MoveUp = true;
            dirty = true;            
            return true;
            
        }
        
        return false;
    }

    @Override
    public boolean ProcessDpadRelease(int dir) {
        Helper.wr("ProcessDpadRelease: " + dir);        
        if(dir == GameSettings.DOWN) {
            paddle1CurrentAccel = paddleMinAccel;
            paddle1CurrentSpeed = 0;
            paddle1MoveDown = false;
            dirty = true;            
            return true;
            
        } else if(dir == GameSettings.UP) {
            paddle1CurrentAccel = paddleMinAccel;
            paddle1CurrentSpeed = 0;
            paddle1MoveUp = false;
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
        Helper.wr("DrawScreen: " + paddle1CurrentSpeed);
        pause = true;
        //dirty = false;

        if(paddle1MoveUp) {
            if(paddleLeft.GetY() - paddle1CurrentSpeed < GetY()) {
                paddleLeft.SetY(GetY());                
            } else {
                paddleLeft.SetY(paddleLeft.GetY() - paddle1CurrentSpeed);
            }
        } else if(paddle1MoveDown) {
            if(paddleLeft.GetY() + paddleLeft.GetHeight() + paddle1CurrentSpeed > GetY() + GetHeight()) {
                paddleLeft.SetY(GetY() + GetHeight() - paddleLeft.GetHeight());
            } else {
               paddleLeft.SetY(paddleLeft.GetY() + paddle1CurrentSpeed);                            
            }         
        }
        
        
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

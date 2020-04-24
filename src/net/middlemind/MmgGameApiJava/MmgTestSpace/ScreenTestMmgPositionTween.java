package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmpScaler;
import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEvent;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEventHandler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFont;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFontData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGameScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPositionTween;
import net.middlemind.MmgGameApiJava.MmgBase.MmgSound;
import net.middlemind.MmgGameApiJava.MmgBase.MmgSprite;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;
import net.middlemind.MmgGameApiJava.MmgCore.GameSettings;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventHandler;

/**
 * A game screen object, ScreenTest, that extends the MmgGameScreen base class.
 * This class is for testing new UI widgets, etc.
 * Created by Middlemind Games 02/25/2020
 * 
 * @author Victor G. Brusca
 */
public class ScreenTestMmgPositionTween extends MmgGameScreen implements GenericEventHandler, MmgEventHandler {

    /**
     * The game state this screen has.
     */
    protected final GameStates gameState;

    /**
     * Event handler for firing generic events. Events would fire when the
     * screen has non UI actions to broadcast.
     */
    protected GenericEventHandler handler;

    /**
     * The GamePanel that owns this game screen. Usually a JPanel instance that
     * holds a reference to this game screen object.
     */
    protected final GamePanel owner;
        
    /**
     * 
     */
    private MmgBmp frame1;
    
    /**
     * 
     */
    private MmgBmp frame2;
    
    /**
     * 
     */
    private MmgBmp frame3;
    
    /**
     * 
     */
    private MmgBmp[] frames;
    
    /**
     * 
     */
    private MmgSprite sprite;    
    
    /**
     * 
     */
    private MmgPositionTween posTween;
                
    /**
     * 
     */
    private MmgFont posTweenLabel;
    
    /**
     * 
     */
    private MmgFont eventLabel;    
        
    /**
     * 
     */
    private MmgFont title;
    
    /**
     * 
     */
    private boolean isDirty = false;
    
    /**
     * 
     */
    private boolean lret = false;
    
    /**
     * Constructor, sets the game state associated with this screen, and sets
     * the owner GamePanel instance.
     *
     * @param State         The game state of this game screen.
     * @param Owner         The owner of this game screen.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ScreenTestMmgPositionTween(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMmgPositionTween.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMmgPositionTween.SetGenericEventHandler");
        handler = Handler;
    }

    /**
     * 
     * 
     * @return 
     */
    public GenericEventHandler GetGenericEventHandler() {
        return handler;
    }
       
    /**
     * Loads all the resources needed to display this game screen.
     */
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        Helper.wr("ScreenTestMmgPositionTween.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());
        
        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Position Tween (18)  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + 30);
        AddObj(title);
              
        frame1 = MmgHelper.GetBasicCachedBmp("soldier_frame_1.png");
        frame1 = MmgBmpScaler.ScaleMmgBmp(frame1, 2.0f, true);
        MmgHelper.CenterHorAndVert(frame1);
        
        frame2 = MmgHelper.GetBasicCachedBmp("soldier_frame_2.png");    
        frame2 = MmgBmpScaler.ScaleMmgBmp(frame2, 2.0f, true);
        MmgHelper.CenterHorAndVert(frame2);
        
        frame3 = MmgHelper.GetBasicCachedBmp("soldier_frame_3.png");
        frame3 = MmgBmpScaler.ScaleMmgBmp(frame3, 2.0f, true);
        MmgHelper.CenterHorAndVert(frame3);
        
        frames = new MmgBmp[4];
        frames[0] = frame1;
        frames[1] = frame2;
        frames[2] = frame3;
        frames[3] = frame2;        
        
        MmgVector2 tmpPos = frame1.GetPosition().Clone();
        tmpPos.SetY(tmpPos.GetY() + 15);
        sprite = new MmgSprite(frames, tmpPos);
        sprite.SetFrameTime(200l);
        AddObj(sprite);
        
        posTweenLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        posTweenLabel.SetText("MmgSprite Example with 4 Frames Attached to an MmgPositionTween");
        MmgHelper.CenterHorAndVert(posTweenLabel);
        posTweenLabel.SetY(GetY() + 70);
        AddObj(posTweenLabel);
        
        MmgVector2 start = new MmgVector2(100, GetY() + (GetHeight() - frame1.GetHeight()) / 2);
        MmgVector2 stop = new MmgVector2(GetWidth() - 100, GetY() + (GetHeight() - frame1.GetHeight()) / 2);
        
        posTween = new MmgPositionTween(sprite, 10000, start, stop);
        posTween.SetOnReachStart(this);
        posTween.SetOnReachFinish(this);
        posTween.SetMsStartMove(2000);
        posTween.SetMsStartMove(System.currentTimeMillis());
        posTween.SetMoving(true);
        
        eventLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        eventLabel.SetText("Event:");
        MmgHelper.CenterHor(eventLabel);
        eventLabel.SetY(GetY() + GetHeight() - 30);
        AddObj(eventLabel); 
        
        ready = true;
        pause = false;
    }

    /**
     * 
     * 
     * @param v
     * @return 
     */
    @Override
    public boolean ProcessMousePress(MmgVector2 v) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessScreenPress");
        return ProcessMousePress(v.GetX(), v.GetY());
    }

    /**
     * 
     * 
     * @param x
     * @param y
     * @return 
     */
    @Override
    public boolean ProcessMousePress(int x, int y) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessScreenPress");
        return true;
    }

    /**
     * 
     * 
     * @param v
     * @return 
     */
    @Override
    public boolean ProcessMouseRelease(MmgVector2 v) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessScreenRelease");
        return ProcessMousePress(v.GetX(), v.GetY());
    }

    /**
     * 
     * 
     * @param x
     * @param y
     * @return 
     */
    @Override
    public boolean ProcessMouseRelease(int x, int y) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessScreenRelease");
        return true;
    }
    
    /**
     * 
     * 
     * @param src
     * @return 
     */
    @Override
    public boolean ProcessAClick(int src) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessAClick");
        return true;
    }
    
    /**
     * 
     * 
     * @param src
     * @return 
     */
    @Override
    public boolean ProcessBClick(int src) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessBClick");        
        return true;
    }
    
    /**
     * 
     */
    @Override
    public void ProcessDebugClick() {
        Helper.wr("ScreenTestMmgPositionTween.ProcessDebugClick");
    }

    /**
     * 
     * 
     * @param dir
     * @return 
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessDpadPress: " + dir);
        return true;
    }

    /**
     * 
     * 
     * @param dir
     * @return 
     */
    @Override
    public boolean ProcessDpadRelease(int dir) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessDpadRelease: " + dir);
        if(dir == GameSettings.RIGHT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_01);
        
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_17);
            
        }
        return true;
    }
    
    /**
     * 
     * 
     * @param dir
     * @return 
     */
    @Override
    public boolean ProcessDpadClick(int dir) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessDpadClick: " + dir);        
        return true;
    }
    
    /**
     * 
     * 
     * @param v
     * @return 
     */
    @Override
    public boolean ProcessMouseClick(MmgVector2 v) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessScreenClick");        
        return ProcessMouseClick(v.GetX(), v.GetY());
    }

    /**
     * 
     * 
     * @param x
     * @param y
     * @return 
     */
    @Override
    public boolean ProcessMouseClick(int x, int y) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessScreenClick");
        return true;
    }    
    
    /**
     * 
     * 
     * @param c
     * @param code
     * @return 
     */
    @Override
    public boolean ProcessKeyClick(char c, int code) {
        Helper.wr("ScreenTestMmgPositionTween.ProcessKeyClick");
        return true;
    }
    
    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);

        title = null;
        frame1 = null;
        frame2 = null;
        frame3 = null;
        frames = null;
        sprite = null;
        posTween = null;
        posTweenLabel = null;
        eventLabel = null;
        
        ClearObjs();
        ready = false;
    }

    /**
     * Returns the game state of this game screen.
     *
     * @return      The game state of this game screen.
     */
    public GameStates GetGameState() {
        return gameState;
    }
    
    /**
     * 
     * 
     * @param updateTick
     * @param currentTimeMs
     * @param msSinceLastFrame
     * @return 
     */
    @Override
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        lret = false;

        if (pause == false && isVisible == true) {
            //always run this update
            posTween.MmgUpdate(updateTick, currentTimeMs, msSinceLastFrame);
            sprite.MmgUpdate(updateTick, currentTimeMs, msSinceLastFrame);            
        }

        return lret;
    }    
    
    /**
     * The main drawing routine.
     *
     * @param p     An MmgPen object to use for drawing this game screen.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (pause == false && isVisible == true) {
            super.MmgDraw(p);
        }
    }
    
    /**
     * 
     * 
     * @param obj 
     */
    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        Helper.wr("ScreenTestMmgPositionTween.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * 
     * 
     * @param e 
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        Helper.wr("ScreenTestMmgPositionTween.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());
        eventLabel.SetText("Event: " + e.GetMessage() + " Id: " + e.GetEventId() + " Type: " + e.GetEventType());
        MmgHelper.CenterHor(eventLabel);
        if(e.GetEventId() == MmgPositionTween.MMG_POSITION_TWEEN_REACH_FINISH) {
            posTween.SetDirStartToFinish(false);
            posTween.SetMsStartMove(2000);
            posTween.SetMsStartMove(System.currentTimeMillis());
            posTween.SetMoving(true);
            
        } else {
            posTween.SetDirStartToFinish(true);
            posTween.SetMsStartMove(2000);
            posTween.SetMsStartMove(System.currentTimeMillis());        
            posTween.SetMoving(true);            
        
        }
    }
}

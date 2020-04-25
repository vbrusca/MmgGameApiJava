package net.middlemind.MmgGameApiJava.MmgTestSpace;

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
public class ScreenTestMmgScreenData extends MmgGameScreen implements GenericEventHandler, MmgEventHandler {

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
    private MmgFont defaultHeightLabel;
    
    /**
     * 
     */
    private MmgFont defaultWidthLabel;
       
    /**
     * 
     */
    private MmgFont gameHeightLabel;
    
    /**
     * 
     */
    private MmgFont gameWidthLabel;
    
    /**
     * 
     */
    private MmgFont gameLeftLabel;
    
    /**
     * 
     */
    private MmgFont gameTopLabel;    

    /**
     * 
     */
    private MmgFont screenHeightLabel;
    
    /**
     * 
     */
    private MmgFont screenWidthLabel;

    /**
     * 
     */
    private MmgFont scaleXLabel;
    
    /**
     * 
     */
    private MmgFont scaleYLabel;
    
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
    public ScreenTestMmgScreenData(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMmgScreenData.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMmgScreenData.SetGenericEventHandler");
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
        Helper.wr("ScreenTestMmgScreenData.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());
        
        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Screen Data (1)  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + 30);
        AddObj(title);
                
        int yDiff = 40;
        int yStrt = GetY() + 140;
        int xLeft = 200;
        int i = 0;
        
        defaultHeightLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        defaultHeightLabel.SetText("DefaultHeight: " + MmgScreenData.DEFAULT_HEIGHT);
        defaultHeightLabel.SetX(xLeft);
        defaultHeightLabel.SetY(yStrt + (yDiff * i));
        AddObj(defaultHeightLabel);
        i++;
        
        defaultWidthLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        defaultWidthLabel.SetText("DefaultWidth: " + MmgScreenData.DEFAULT_WIDTH);
        defaultWidthLabel.SetX(xLeft);
        defaultWidthLabel.SetY(yStrt + (yDiff * i));
        AddObj(defaultWidthLabel);
        i++;
        
        gameHeightLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        gameHeightLabel.SetText("GameHeight: " + MmgScreenData.GetGameHeight());
        gameHeightLabel.SetX(xLeft);
        gameHeightLabel.SetY(yStrt + (yDiff * i));
        AddObj(gameHeightLabel);
        i++;
                
        gameWidthLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        gameWidthLabel.SetText("GameWidth: " + MmgScreenData.GetGameWidth());
        gameWidthLabel.SetX(xLeft);
        gameWidthLabel.SetY(yStrt + (yDiff * i));
        AddObj(gameWidthLabel);
        i++;
        
        gameLeftLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        gameLeftLabel.SetText("GameLeft: " + MmgScreenData.GetGameLeft());
        gameLeftLabel.SetX(xLeft);
        gameLeftLabel.SetY(yStrt + (yDiff * i));
        AddObj(gameLeftLabel);
        i++;        
                
        xLeft = GetWidth()/2 + 70;
        i = 0;
        
        gameTopLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        gameTopLabel.SetText("GameTop: " + MmgScreenData.GetGameTop());
        gameTopLabel.SetX(xLeft);
        gameTopLabel.SetY(yStrt + (yDiff * i));
        AddObj(gameTopLabel);
        i++;
        
        screenHeightLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        screenHeightLabel.SetText("ScreenHeight: " + MmgScreenData.GetScreenHeight());
        screenHeightLabel.SetX(xLeft);
        screenHeightLabel.SetY(yStrt + (yDiff * i));
        AddObj(screenHeightLabel);
        i++;
        
        screenWidthLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        screenWidthLabel.SetText("ScreenWidth: " + MmgScreenData.GetScreenWidth());
        screenWidthLabel.SetX(xLeft);
        screenWidthLabel.SetY(yStrt + (yDiff * i));
        AddObj(screenWidthLabel);
        i++;
        
        scaleXLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        scaleXLabel.SetText("ScaleX: " + MmgScreenData.GetScaleX());
        scaleXLabel.SetX(xLeft);
        scaleXLabel.SetY(yStrt + (yDiff * i));
        AddObj(scaleXLabel);
        i++;
        
        scaleYLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        scaleYLabel.SetText("ScaleY: " + MmgScreenData.GetScaleY());
        scaleYLabel.SetX(xLeft);
        scaleYLabel.SetY(yStrt + (yDiff * i));
        AddObj(scaleYLabel);
        i++;
        
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
        Helper.wr("ScreenTestMmgScreenData.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgScreenData.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgScreenData.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgScreenData.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgScreenData.ProcessAClick");
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
        Helper.wr("ScreenTestMmgScreenData.ProcessBClick");        
        return true;
    }
    
    /**
     * 
     */
    @Override
    public void ProcessDebugClick() {
        Helper.wr("ScreenTestMmgScreenData.ProcessDebugClick");
    }

    /**
     * 
     * 
     * @param dir
     * @return 
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        Helper.wr("ScreenTestMmgScreenData.ProcessDpadPress: " + dir);
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
        Helper.wr("ScreenTestMmgScreenData.ProcessDpadRelease: " + dir);
        if(dir == GameSettings.RIGHT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_02);
        
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_19);
            
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
        Helper.wr("ScreenTestMmgScreenData.ProcessDpadClick: " + dir);        
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
        Helper.wr("ScreenTestMmgScreenData.ProcessScreenClick");        
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
        Helper.wr("ScreenTestMmgScreenData.ProcessScreenClick");
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
        Helper.wr("ScreenTestMmgScreenData.ProcessKeyClick");
        return true;
    }
    
    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);
        title = null;
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
        Helper.wr("ScreenTestMmgScreenData.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * 
     * 
     * @param e 
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        Helper.wr("ScreenTestMmgScreenData.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());
    }
}

package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
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
import net.middlemind.MmgGameApiJava.MmgBase.MmgLabelValuePair;
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
public class ScreenTestMmgLabelValuePair extends MmgGameScreen implements GenericEventHandler, MmgEventHandler {

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
    private MmgFont labelFont;
    
    /**
     * 
     */
    private MmgFont valueFont;
        
    /**
     * 
     */
    private MmgLabelValuePair lvPair1;
    
    /**
     * 
     */
    private MmgLabelValuePair lvPair2;
    
    /**
     * 
     */
    private MmgLabelValuePair lvPair3;        
    
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
    public ScreenTestMmgLabelValuePair(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMmgLabelValuePair.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMmgLabelValuePair.SetGenericEventHandler");
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
        Helper.wr("ScreenTestMmgLabelValuePair.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());
        
        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Label Value Pair (13)  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + 30);
        AddObj(title);
               
        labelFont = MmgFontData.CreateDefaultBoldMmgFontLg();
        labelFont.SetMmgColor(MmgColor.GetGrayWolf());
        labelFont.SetText("Label1:");
        
        valueFont = MmgFontData.CreateDefaultBoldMmgFontSm();
        valueFont.SetMmgColor(MmgColor.GetBlueGray());
        valueFont.SetText("Value1");
        
        lvPair1 = new MmgLabelValuePair(labelFont, valueFont);
        MmgHelper.CenterHorAndVert(lvPair1);
        lvPair1.SetY(lvPair1.GetY() - 30);
        AddObj(lvPair1);
               
        labelFont = labelFont.CloneTyped();
        labelFont.SetMmgColor(MmgColor.GetGrayWolf());        
        labelFont.SetText("Label2:");
        
        valueFont = valueFont.CloneTyped();
        valueFont.SetMmgColor(MmgColor.GetSlateBlue());
        valueFont.SetText("Value2");        
        
        lvPair2 = new MmgLabelValuePair(labelFont, valueFont);
        MmgHelper.CenterHorAndVert(lvPair2);
        lvPair2.SetY(lvPair2.GetY() + 0);
        AddObj(lvPair2);        
        
        labelFont = labelFont.CloneTyped();
        labelFont.SetMmgColor(MmgColor.GetGrayWolf());        
        labelFont.SetText("Label3:");
        
        valueFont = valueFont.CloneTyped();
        valueFont.SetMmgColor(MmgColor.GetCalmBlue());        
        valueFont.SetText("Value3");        
        
        lvPair3 = new MmgLabelValuePair(labelFont, valueFont);
        MmgHelper.CenterHorAndVert(lvPair3);
        lvPair3.SetY(lvPair3.GetY() + 30);
        AddObj(lvPair3);        
        
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessAClick");
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessBClick");        
        return true;
    }
    
    /**
     * 
     */
    @Override
    public void ProcessDebugClick() {
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessDebugClick");
    }

    /**
     * 
     * 
     * @param dir
     * @return 
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessDpadPress: " + dir);
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessDpadRelease: " + dir);
        if(dir == GameSettings.RIGHT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_01);
        
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_12);
            
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessDpadClick: " + dir);        
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessScreenClick");        
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessScreenClick");
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
        Helper.wr("ScreenTestMmgLabelValuePair.ProcessKeyClick");        
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
        Helper.wr("ScreenTestMmgLabelValuePair.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * 
     * 
     * @param e 
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        Helper.wr("ScreenTestMmgLabelValuePair.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());
    }
}

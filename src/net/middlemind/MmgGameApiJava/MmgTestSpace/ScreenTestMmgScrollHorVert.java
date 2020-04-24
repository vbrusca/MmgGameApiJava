package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import java.awt.Color;
import net.middlemind.MmgGameApiJava.MmgBase.Mmg9Slice;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgDrawableBmpSet;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEvent;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEventHandler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFont;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFontData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGameScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgObj;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScrollHor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScrollHorVert;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScrollVert;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventHandler;

/**
 * A game screen object, ScreenTest, that extends the MmgGameScreen base class.
 * This class is for testing new UI widgets, etc.
 * Created by Middlemind Games 02/25/2020
 * 
 * @author Victor G. Brusca
 */
public class ScreenTestMmgScrollHorVert extends MmgGameScreen implements GenericEventHandler, MmgEventHandler {

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
    protected MmgScrollHorVert scrollBoth;
        
    /**
     * 
     */
    private MmgBmp bground;
    
    /**
     * 
     */
    private Mmg9Slice menuBground;
    
    /**
     * 
     */
    private MmgFont title;
    
    /**
     * 
     */
    private MmgFont instr;
    
    /**
     * 
     */
    private MmgFont event;
    
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
    public ScreenTestMmgScrollHorVert(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMmgScrollHorVert.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMmgScrollHorVert.SetGenericEventHandler");
        handler = Handler;
    }

    public GenericEventHandler GetGenericEventHandler() {
        return handler;
    }
       
    /**
     * Loads all the resources needed to display this game screen.
     */
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        Helper.wr("ScreenTestMmgScrollHorVert.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());

        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Scroll Hor and Vert (16)  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + 30);
        AddObj(title);        
        
        instr = MmgFontData.CreateDefaultBoldMmgFontLg();
        instr.SetText("Press 'A' to navigate left, press 'B' to navigate right.");
        MmgHelper.CenterHorAndTop(instr);
        instr.SetY(instr.GetY() + 70);
        AddObj(instr);
        
        MmgPen p;
        p = new MmgPen();
        p.SetCacheOn(false);

        int totalWidth = MmgHelper.ScaleValue(235);
        int totalHeight = MmgHelper.ScaleValue(235);
        bground = Helper.GetBasicCachedBmp("popup_window_base.png");
        menuBground = new Mmg9Slice(16, bground, totalWidth, totalHeight);
        menuBground.SetPosition(MmgVector2.GetOriginVec());
        menuBground.SetWidth(totalWidth);
        menuBground.SetHeight(totalHeight);
        MmgHelper.CenterHorAndVert(menuBground);
        AddObj(menuBground);
        
        MmgBmp vPort = null;
        MmgBmp sPane = null;
        MmgDrawableBmpSet dBmpSetScrollPane = null;
        MmgDrawableBmpSet dBmpSetViewPort = null;
        MmgColor sBarColor;
        MmgColor sBarSldrColor;
        int sBarWidth = 0;
        int sBarSldrHeight = 0;     
        int interval = 0;                
        int hund2 = MmgHelper.ScaleValue(200);
        int hund4 = MmgHelper.ScaleValue(400);
        int sWidth = MmgHelper.ScaleValue(200);
        int sHeight = MmgHelper.ScaleValue(200);
                
        dBmpSetScrollPane = MmgHelper.CreateDrawableBmpSet(hund4, hund4, false, MmgColor.GetBlack());
        dBmpSetScrollPane.graphics.setColor(Color.RED);
        dBmpSetScrollPane.graphics.fillRect(0, 0, hund4 / 4, hund4 / 4);
        dBmpSetScrollPane.graphics.setColor(Color.BLUE);
        dBmpSetScrollPane.graphics.fillRect(hund4 / 4, hund4 / 4, hund4 / 4, hund4 / 4);
        dBmpSetScrollPane.graphics.setColor(Color.GREEN);
        dBmpSetScrollPane.graphics.fillRect(hund4 / 2, hund4 / 2, hund4 / 4, hund4 / 4);
        
        dBmpSetViewPort = MmgHelper.CreateDrawableBmpSet(hund2, hund2, false, MmgColor.GetBlack());
        dBmpSetViewPort.graphics.setColor(Color.LIGHT_GRAY);
        dBmpSetViewPort.graphics.fillRect(0, 0, hund2, hund2);
                        
        vPort = dBmpSetViewPort.img;
        sPane = dBmpSetScrollPane.img;
        
        sBarColor = MmgColor.GetLightGray();
        sBarSldrColor = MmgColor.GetGray();       
        sBarWidth = MmgHelper.ScaleValue(15);
        sBarSldrHeight = MmgHelper.ScaleValue(30);
        interval = 10;         
       
        scrollBoth = new MmgScrollHorVert(vPort, sPane, sBarColor, sBarSldrColor, sBarWidth, sBarWidth, sBarSldrHeight, sBarSldrHeight, interval, interval);
        scrollBoth.SetIsVisible(true);
        scrollBoth.SetWidth(sWidth + scrollBoth.GetScrollBarVertWidth());
        scrollBoth.SetHeight(sHeight + scrollBoth.GetScrollBarHorHeight());
        scrollBoth.SetEventHandler(this);
        MmgScrollHorVert.SHOW_CONTROL_BOUNDING_BOX = true;
        MmgHelper.CenterHorAndVert(scrollBoth);
        AddObj(scrollBoth);
        
        event = MmgFontData.CreateDefaultMmgFontSm();
        event.SetText("Event: ");
        MmgHelper.CenterHorAndTop(event);
        event.SetY(scrollBoth.GetY() + scrollBoth.GetHeight() + 30);
        AddObj(event);        
        
        ready = true;
        pause = false;
    }

    @Override
    public boolean ProcessMousePress(MmgVector2 v) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessScreenPress");
        return ProcessMousePress(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessMousePress(int x, int y) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessScreenPress");
        return true;
    }

    @Override
    public boolean ProcessMouseRelease(MmgVector2 v) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessScreenRelease");
        return ProcessMousePress(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessMouseRelease(int x, int y) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessScreenRelease");
        return true;
    }
    
    @Override
    public boolean ProcessAClick(int src) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessAClick");
        //Go Left
        owner.SwitchGameState(GameStates.GAME_SCREEN_15);
        return true;
    }
    
    @Override
    public boolean ProcessBClick(int src) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessBClick");
        //Go Right
        owner.SwitchGameState(GameStates.GAME_SCREEN_01);        
        return true;
    }
    
    @Override
    public void ProcessDebugClick() {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessDebugClick");
    }

    @Override
    public boolean ProcessDpadPress(int dir) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessDpadPress: " + dir);
        return true;
    }

    @Override
    public boolean ProcessDpadRelease(int dir) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessDpadRelease: " + dir);
        scrollBoth.ProcessDpadRelease(dir);
        isDirty = true;
        return true;
    }
    
    @Override
    public boolean ProcessDpadClick(int dir) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessDpadClick: " + dir);        
        return true;
    }
    
    @Override
    public boolean ProcessMouseClick(MmgVector2 v) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessScreenClick");        
        return ProcessMouseClick(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessMouseClick(int x, int y) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessScreenClick");
        scrollBoth.ProcessScreenClick(x, y);
        isDirty = true;
        return true;
    }    
    
    @Override
    public boolean ProcessKeyClick(char c, int code) {
        Helper.wr("ScreenTestMmgScrollHorVert.ProcessKeyClick");
        return true;
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
            if (isDirty == true) {
                super.GetObjects().SetIsDirty(true);            

                if (super.MmgUpdate(updateTick, currentTimeMs, msSinceLastFrame) == true) {
                    lret = true;
                }
            }
        }

        return lret;
    }
    
    /**
     * The main drawing routine.
     *
     * @param p An MmgPen object to use for drawing this game screen.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (pause == false && isVisible == true) {
            super.MmgDraw(p);
        }
    }

    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        Helper.wr("ScreenTestMmgScrollHorVert.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    @Override
    public void MmgHandleEvent(MmgEvent e) {
        if(e.GetEventId() == MmgScrollVert.SCROLL_VERT_CLICK_EVENT_ID || e.GetEventId() == MmgScrollHor.SCROLL_HOR_CLICK_EVENT_ID || e.GetEventId() == MmgScrollHorVert.SCROLL_BOTH_CLICK_EVENT_ID) {
            MmgVector2 v2 = (MmgVector2)e.GetExtra();
            event.SetText("Event: Id: " + e.GetEventId() + " Type: " + e.GetEventType() + " Pos: " + v2.ToString() + " Msg: " + e.GetMessage() + " " + System.currentTimeMillis());
        
        } else {
            event.SetText("Event: Id: " + e.GetEventId() + " Type: " + e.GetEventType() + " Msg: " + e.GetMessage() + " " + System.currentTimeMillis());
            
        }
        
        MmgHelper.CenterHor(event);
    }
}

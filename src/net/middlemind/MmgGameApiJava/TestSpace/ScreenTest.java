package com.middlemind.Odroid;

import com.middlemind.Odroid.GamePanel.GameStates;
import com.middlemind.Odroid.GenericEventHandler;
import com.middlemind.Odroid.GenericEventMessage;
import com.middlemind.Odroid.Helper;
import java.awt.Color;
import java.awt.Graphics;
import net.middlemind.MmgGameApiJava.MmgBase.Mmg9Slice;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEvent;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGameScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgObj;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScrollHor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScrollVert;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;

/**
 * A game screen object, ScreenTest, that extends the MmgGameScreen base
 * class. This class is for testing new UI widgets, etc.
 *
 * @author Victor G. Brusca
 * 02/25/2020
 */
public class ScreenTest extends MmgGameScreen implements GenericEventHandler {

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
    
    protected MmgScrollVert scrollVert;
    protected MmgScrollHor scrollHor;    
    
    private MmgBmp bground;
    private Mmg9Slice menuBground;    
    
    /**
     * Constructor, sets the game state associated with this screen, and sets
     * the owner GamePanel instance.
     *
     * @param State The game state of this game screen.
     * @param Owner The owner of this game screen.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ScreenTest(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        //SetMmgUpdateHandler(this);
        Helper.wr("ScreenTest: Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("Odroid.ScreenTest.SetGenericEventHandler");
        handler = Handler;
    }

    public GenericEventHandler GetGenericEventHandler() {
        return handler;
    }
        
    /**
     * Public method that fires the local generic event, the listener will
     * receive a display complete event.
     *
     * @param obj The information payload to send along with this message.
     */
    /*
    @Override
    public void MmgHandleUpdate(Object obj) {
        Helper.wr("Odroid.ScreenTest.MmgHandleUpdate");
        if (handler != null) {
            handler.HandleGenericEvent(new GenericEventMessage(ScreenSplash.EVENT_DISPLAY_COMPLETE, null, GetGameState()));
        }
    }
    */
    
    /**
     * Loads all the resources needed to display this game screen.
     */
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        Helper.wr("Odroid.ScreenTest.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());

        //MmgBmp tB = null;
        MmgPen p;
        p = new MmgPen();
        p.SetCacheOn(false);

        int totalWidth = 210;
        int totalHeight = 210;
        
        bground = Helper.GetBasicCachedBmp("popup_window_base.png");
        menuBground = new Mmg9Slice(16, bground, totalWidth, totalHeight);
        menuBground.SetPosition(MmgVector2.GetOriginVec());
        menuBground.SetWidth(totalWidth);
        menuBground.SetHeight(totalHeight);
        MmgHelper.CenterHorAndVert(menuBground);
        AddObj(menuBground);
        
        int sWidth = 0;
        int sHeight = 0;
        MmgObj vPort = null;
        MmgObj sPane = null;
        MmgColor sBarColor;
        MmgColor sBarSldrColor;
        int sBarWidth = 0;
        int sBarSldrHeight = 0;     
        int interval = 0;                
        
        sWidth = 200;
        sHeight = 200;
        vPort = new MmgObj(0, 0, 200, 200, true, MmgColor.GetRed());
        sPane = new MmgObj(0, 0, 200, 400, true, MmgColor.GetBlue());        
        sBarColor = MmgColor.GetLightGray();
        sBarSldrColor = MmgColor.GetGray();
        sBarWidth = MmgHelper.ScaleValue(15);
        sBarSldrHeight = MmgHelper.ScaleValue(30);
        interval = 10;
        
        scrollVert = new MmgScrollVert(vPort, sPane, sBarColor, sBarSldrColor, sBarWidth, sBarSldrHeight, interval, gameState);
        //scrollVert = new MmgScrollVert(vPort, sPane, sBarColor, sBarSldrColor, interval, state);        
        scrollVert.SetIsVisible(true);
        scrollVert.SetWidth(sWidth + scrollVert.GetScrollBarWidth());
        scrollVert.SetHeight(sHeight);
        scrollVert.SetHandler(this);        
        MmgScrollVert.SHOW_CONTROL_BOUNDING_BOX = true;
        MmgHelper.CenterHorAndVert(scrollVert);
        //scrollVert.SetPosition(new MmgVector2(50, 50));
        //AddObj(scrollVert);
        
        vPort = new MmgObj(0, 0, 200, 200, true, MmgColor.GetRed());
        sPane = new MmgObj(0, 0, 400, 200, true, MmgColor.GetBlue());        
        sBarColor = MmgColor.GetLightGray();
        sBarSldrColor = MmgColor.GetGray();
        
        scrollHor = new MmgScrollHor(vPort, sPane, sBarColor, sBarSldrColor, sBarWidth, sBarSldrHeight, interval, gameState);
        //scrollHor = new MmgScrollHor(vPort, sPane, sBarColor, sBarSldrColor, interval, state);        
        scrollHor.SetIsVisible(true);
        scrollHor.SetWidth(sWidth);
        scrollHor.SetHeight(sHeight + scrollHor.GetScrollBarHeight());
        scrollHor.SetHandler(this);
        MmgScrollHor.SHOW_CONTROL_BOUNDING_BOX = true;
        MmgHelper.CenterHorAndVert(scrollHor);
        AddObj(scrollHor);
        
        ready = true;
        pause = false;
        Helper.wr("ScreenTest: LoadResources");
    }

    @Override
    public boolean ProcessScreenPress(MmgVector2 v) {
        Helper.wr("ScreenTest.ProcessScreenPress");
        return ProcessScreenPress(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessScreenPress(int x, int y) {
        Helper.wr("ScreenTest.ProcessScreenPress");
        return true;
    }

    @Override
    public boolean ProcessScreenRelease(MmgVector2 v) {
        Helper.wr("ScreenTest.ProcessScreenRelease");
        return ProcessScreenPress(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessScreenRelease(int x, int y) {
        Helper.wr("ScreenTest.ProcessScreenRelease");
        return true;
    }
    
    @Override
    public boolean ProcessAClick() {
        Helper.wr("ScreenTest.ProcessAClick");
        return true;
    }
    
    @Override
    public boolean ProcessBClick() {
        Helper.wr("ScreenTest.ProcessBClick");        
        return true;
    }
    
    @Override
    public void ProcessDebugClick() {
        Helper.wr("ScreenTest.ProcessDebugClick");
    }

    @Override
    public boolean ProcessDpadPress(int dir) {
        Helper.wr("ScreenTest.ProcessDpadPress: " + dir);
        return true;
    }

    @Override
    public boolean ProcessDpadRelease(int dir) {
        Helper.wr("ScreenTest.ProcessDpadRelease: " + dir);
        if(scrollVert.ProcessDpadRelease(dir) || scrollHor.ProcessDpadRelease(dir)) {
            return true;
        }        
        return false;
    }
    
    @Override
    public boolean ProcessDpadClick(int dir) {
        Helper.wr("ScreenTest.ProcessDpadClick: " + dir);        
        return true;
    }
    
    @Override
    public boolean ProcessScreenClick(MmgVector2 v) {
        Helper.wr("ScreenTest.ProcessScreenClick");        
        return ProcessScreenClick(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessScreenClick(int x, int y) {
        Helper.wr("ScreenTest.ProcessScreenClick");
        if(scrollVert.ProcessScreenClick(x, y) || scrollHor.ProcessScreenClick(x, y)) {
            return true;
        }
        
        return false;
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
            super.MmgDraw(p);
            
            Graphics g = p.GetGraphics();
            Color ct = g.getColor();
            g.setColor(Color.WHITE);
            g.fillRect(MmgScreenData.GetGameLeft(), MmgScreenData.GetGameTop(), MmgScreenData.GetGameWidth(), MmgScreenData.GetGameHeight());
            g.setColor(ct);
            
            super.GetObjects().MmgDraw(p);
        } else {
            //do nothing
        }
    }

    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
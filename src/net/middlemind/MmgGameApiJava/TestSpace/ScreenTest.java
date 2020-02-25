package com.middlemind.Odroid;

import com.middlemind.Odroid.GamePanel.GameStates;
import com.middlemind.Odroid.GenericEventHandler;
import com.middlemind.Odroid.GenericEventMessage;
import com.middlemind.Odroid.Helper;
import java.awt.Color;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGameScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgMenuItem;
import net.middlemind.MmgGameApiJava.MmgBase.MmgObj;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScrollVert;
import net.middlemind.MmgGameApiJava.MmgBase.MmgUpdateHandler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;

/**
 * A game screen object, ScreenTest, that extends the MmgGameScreen base
 * class. This class is for testing new UI widgets, etc.
 *
 * @author Victor G. Brusca
 * 02/25/2020
 */
public class ScreenTest extends MmgGameScreen implements MmgUpdateHandler {

    /**
     * The game state this screen has.
     */
    protected final GameStates state;

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
        state = State;
        owner = Owner;
        SetMmgUpdateHandler(this);
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
    @Override
    public void MmgHandleUpdate(Object obj) {
        Helper.wr("Odroid.ScreenTest.MmgHandleUpdate");
        if (handler != null) {
            handler.HandleGenericEvent(new GenericEventMessage(ScreenSplash.EVENT_DISPLAY_COMPLETE, null, GetGameState()));
        }
    }

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

        //tB = Helper.GetBasicBmp("../cfg/drawable/logo_large.jpg");
        //if (tB != null) {
        //    SetCenteredBackground(tB);
        //}

        int sWidth = 100;
        int sHeight = 100;
        MmgObj vPort = new MmgObj(0, 0, 100, 100, true, MmgColor.GetRed());
        MmgObj sPane = new MmgObj(0, 0, 100, 300, true, MmgColor.GetBlue());        
        MmgColor sBarColor = MmgColor.GetLightGray();
        MmgColor sBarSldrColor = MmgColor.GetGray();
        int sBarWidth = MmgHelper.ScaleValue(15);
        int sBarSldrHeight = MmgHelper.ScaleValue(30);
        int interval = 10;        
        scrollVert = new MmgScrollVert(vPort, sPane, sBarColor, sBarSldrColor, sBarWidth, sBarSldrHeight, interval);
        scrollVert.SetIsVisible(true);
        scrollVert.SetWidth(sWidth + scrollVert.GetScrollBarWidth());
        scrollVert.SetHeight(sHeight);
        MmgScrollVert.SHOW_CONTROL_BOUNDING_BOX = true;
        MmgHelper.CenterHorAndVert(scrollVert);
        AddObj(scrollVert);
        
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
        return true;
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
        if(scrollVert.ProcessScreenClick(x, y)) {
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
        return state;
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
            super.GetObjects().MmgDraw(p);
        } else {
            //do nothing
        }
    }
}

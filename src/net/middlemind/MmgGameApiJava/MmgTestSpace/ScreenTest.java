package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventHandler;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import java.awt.Color;
import java.awt.Graphics;
import net.middlemind.MmgGameApiJava.MmgBase.Mmg9Slice;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgDrawableBmpSet;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEvent;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGameScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgObj;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScrollHor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScrollHorVert;
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
    protected MmgScrollHorVert scrollBoth;
    
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

        int totalWidth = MmgHelper.ScaleValue(210);
        int totalHeight = MmgHelper.ScaleValue(235);
        bground = Helper.GetBasicCachedBmp("popup_window_base.png");
        menuBground = new Mmg9Slice(16, bground, totalWidth, totalHeight);
        menuBground.SetPosition(MmgVector2.GetOriginVec());
        menuBground.SetWidth(totalWidth);
        menuBground.SetHeight(totalHeight);
        MmgHelper.CenterHorAndVert(menuBground);
        //AddObj(menuBground);
        
        int sWidth = 0;
        int sHeight = 0;
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
                
        sWidth = MmgHelper.ScaleValue(200);
        sHeight = MmgHelper.ScaleValue(200);
        
        dBmpSetScrollPane = MmgHelper.CreateDrawableBmpSet(hund2, hund4, false, MmgColor.GetBlack());
        dBmpSetScrollPane.graphics.setColor(Color.RED);
        dBmpSetScrollPane.graphics.fillRect(0, 0, hund2, hund4 / 4);
        dBmpSetScrollPane.graphics.setColor(Color.BLUE);
        dBmpSetScrollPane.graphics.fillRect(0, hund4 / 4, hund2, hund4 / 4);
        dBmpSetScrollPane.graphics.setColor(Color.GREEN);
        dBmpSetScrollPane.graphics.fillRect(0, hund4 / 2, hund2, hund4 / 4);
        
        dBmpSetViewPort = MmgHelper.CreateDrawableBmpSet(hund2, hund2, false, MmgColor.GetBlack());
        dBmpSetViewPort.graphics.setColor(Color.LIGHT_GRAY);
        dBmpSetViewPort.graphics.fillRect(0, 0, hund2, hund2);
        
        //vPort = new MmgBmp(new MmgObj(0, 0, hund2, hund2, true, MmgColor.GetRed()));        
        //sPane = new MmgBmp(new MmgObj(0, 0, hund2, hund4, true, MmgColor.GetBlue()));        
        vPort = dBmpSetViewPort.img;
        sPane = dBmpSetScrollPane.img;
        
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
        //AddObj(scrollVert);
        
        dBmpSetScrollPane = MmgHelper.CreateDrawableBmpSet(hund4, hund2, false, MmgColor.GetBlack());
        dBmpSetScrollPane.graphics.setColor(Color.RED);
        dBmpSetScrollPane.graphics.fillRect(0, 0, hund4 / 4, hund2);
        dBmpSetScrollPane.graphics.setColor(Color.BLUE);
        dBmpSetScrollPane.graphics.fillRect(hund4 / 4, 0, hund4 / 4, hund2);
        dBmpSetScrollPane.graphics.setColor(Color.GREEN);
        dBmpSetScrollPane.graphics.fillRect(hund4 / 2, 0, hund4 / 4, hund2);
        
        dBmpSetViewPort = MmgHelper.CreateDrawableBmpSet(hund2, hund2, false, MmgColor.GetBlack());
        dBmpSetViewPort.graphics.setColor(Color.LIGHT_GRAY);
        dBmpSetViewPort.graphics.fillRect(0, 0, hund2, hund2);
                        
        //vPort = new MmgBmp(new MmgObj(0, 0, hund2, hund2, true, MmgColor.GetRed()));
        //sPane = new MmgBmp(new MmgObj(0, 0, hund4, hund2, true, MmgColor.GetBlue()));        
        vPort = dBmpSetViewPort.img;
        sPane = dBmpSetScrollPane.img;
        
        sBarColor = MmgColor.GetLightGray();
        sBarSldrColor = MmgColor.GetGray();
        sBarWidth = MmgHelper.ScaleValue(15);
        sBarSldrHeight = MmgHelper.ScaleValue(30);
        interval = 10;        
        
        scrollHor = new MmgScrollHor(vPort, sPane, sBarColor, sBarSldrColor, sBarWidth, sBarSldrHeight, interval, gameState);
        //scrollHor = new MmgScrollHor(vPort, sPane, sBarColor, sBarSldrColor, interval, state);        
        scrollHor.SetIsVisible(true);
        scrollHor.SetWidth(sWidth);
        scrollHor.SetHeight(sHeight + scrollHor.GetScrollBarHeight());
        scrollHor.SetHandler(this);
        MmgScrollHor.SHOW_CONTROL_BOUNDING_BOX = true;
        MmgHelper.CenterHorAndVert(scrollHor);
        //AddObj(scrollHor);

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
                        
        vPort = new MmgBmp(new MmgObj(0, 0, hund2, hund2, true, MmgColor.GetRed()));
        sPane = new MmgBmp(new MmgObj(0, 0, hund4, hund4, true, MmgColor.GetBlue()));
        vPort = dBmpSetViewPort.img;
        sPane = dBmpSetScrollPane.img;
        
        sBarColor = MmgColor.GetLightGray();
        sBarSldrColor = MmgColor.GetGray();       
        sBarWidth = MmgHelper.ScaleValue(15);
        sBarSldrHeight = MmgHelper.ScaleValue(30);
        interval = 10;         
       
        scrollBoth = new MmgScrollHorVert(vPort, sPane, sBarColor, sBarSldrColor, sBarWidth, sBarWidth, sBarSldrHeight, sBarSldrHeight, interval, interval, gameState);
        //scrollBoth = new MmgScrollHor(vPort, sPane, sBarColor, sBarSldrColor, interval, state);        
        scrollBoth.SetIsVisible(true);
        scrollBoth.SetWidth(sWidth + scrollBoth.GetScrollBarWidth());
        scrollBoth.SetHeight(sHeight + scrollBoth.GetScrollBarHeight());
        scrollBoth.SetHandler(this);
        MmgScrollHorVert.SHOW_CONTROL_BOUNDING_BOX = true;
        MmgHelper.CenterHorAndVert(scrollBoth);
        AddObj(scrollBoth);
        
        ready = true;
        pause = false;
        Helper.wr("ScreenTest: LoadResources");
    }

    @Override
    public boolean ProcessMousePress(MmgVector2 v) {
        Helper.wr("ScreenTest.ProcessScreenPress");
        return ProcessMousePress(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessMousePress(int x, int y) {
        Helper.wr("ScreenTest.ProcessScreenPress");
        return true;
    }

    @Override
    public boolean ProcessMouseRelease(MmgVector2 v) {
        Helper.wr("ScreenTest.ProcessScreenRelease");
        return ProcessMousePress(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessMouseRelease(int x, int y) {
        Helper.wr("ScreenTest.ProcessScreenRelease");
        return true;
    }
    
    @Override
    public boolean ProcessAClick(int src) {
        Helper.wr("ScreenTest.ProcessAClick");
        return true;
    }
    
    @Override
    public boolean ProcessBClick(int src) {
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
        scrollVert.ProcessDpadRelease(dir);
        scrollHor.ProcessDpadRelease(dir);
        scrollBoth.ProcessDpadRelease(dir);

        return false;
    }
    
    @Override
    public boolean ProcessDpadClick(int dir) {
        Helper.wr("ScreenTest.ProcessDpadClick: " + dir);        
        return true;
    }
    
    @Override
    public boolean ProcessMouseClick(MmgVector2 v) {
        Helper.wr("ScreenTest.ProcessScreenClick");        
        return ProcessMouseClick(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessMouseClick(int x, int y) {
        Helper.wr("ScreenTest.ProcessScreenClick");
        scrollVert.ProcessScreenClick(x, y);
        scrollHor.ProcessScreenClick(x, y);
        scrollBoth.ProcessScreenClick(x, y);
        
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
            
            /*
            Graphics g = p.GetGraphics();
            Color ct = g.getColor();
            g.setColor(Color.WHITE);
            //g.fillRect(MmgScreenData.GetGameLeft(), MmgScreenData.GetGameTop(), MmgScreenData.GetGameWidth(), MmgScreenData.GetGameHeight());
            g.fillRect(GetX(), GetY(), w, h);
            g.setColor(ct);
            */
            
            super.GetObjects().MmgDraw(p);
        } else {
            //do nothing
        }
    }

    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        Helper.wr("ScreenTest.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
        if(obj.id == MmgScrollVert.SCROLL_VERT_CLICK_EVENT_ID || obj.id == MmgScrollHor.SCROLL_HOR_CLICK_EVENT_ID || obj.id == MmgScrollHorVert.SCROLL_BOTH_CLICK_EVENT_ID) {
            MmgVector2 v2 = (MmgVector2)obj.payload;
            Helper.wr("ScreenTest.HandleGenericEvent: " + v2.ToString());
        }
    }
}

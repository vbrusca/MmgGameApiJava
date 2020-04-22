package net.middlemind.MmgGameApiJava.MmgTestSpace;

import java.awt.Color;
import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmpScaler;
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
import net.middlemind.MmgGameApiJava.MmgBase.MmgRect;
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
public class ScreenTestMmgBmp extends MmgGameScreen implements GenericEventHandler, MmgEventHandler {

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
    private MmgBmp bmpCache;
    
    /**
     * 
     */
    private MmgFont bmpCacheLabel;
    
    /**
     * 
     */
    private MmgBmp bmpFile;
    
    /**
     * 
     */
    private MmgFont bmpFileLabel;    
    
    /**
     * 
     */
    private MmgBmp bmpCustomFill;
    
    /**
     * 
     */
    private MmgFont bmpCustomFillLabel;    
    
    /**
     * 
     */
    private MmgBmp bmpPartialCopy;
    
    /**
     * 
     */
    private MmgFont bmpPartialCopyLabel;    
        
    /**
     * 
     */
    private MmgDrawableBmpSet bmpSet;
    
    /**
     * 
     */
    private MmgRect srcRect;
    
    /**
     * 
     */
    private MmgRect dstRect;
    
    /**
     * 
     */
    private MmgFont title;
    
    /**
     * 
     */
    private MmgBmp bmpScaled;
    
    /**
     * 
     */
    private MmgFont bmpScaledLabel;
    
    /**
     * 
     */
    private MmgBmp bmpRotate;
    
    /**
     * 
     */
    private MmgFont bmpRotateLabel;    
    
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
    public ScreenTestMmgBmp(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMmgBmp.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMmgBmp.SetGenericEventHandler");
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
        Helper.wr("ScreenTestMmgBmp.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());

        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Bmp  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + 30);
        AddObj(title);         
        
        bmpCache = MmgHelper.GetBasicCachedBmp("soldier_frame_1.png");
        bmpCache.SetY(GetY() + 90);
        bmpCache.SetX(220);
        AddObj(bmpCache);

        bmpCacheLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        bmpCacheLabel.SetText("MmgBmp From Auto Load Cache");
        bmpCacheLabel.SetPosition(50, GetY() + 70);
        AddObj(bmpCacheLabel);
        
        bmpFile = MmgHelper.GetBasicCachedBmp("../cfg/drawable/loading_bar.png", "loading_bar.png");
        bmpFile.SetY(GetY() + 90);
        bmpFile.SetX(560);
        AddObj(bmpFile);
        
        bmpFileLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        bmpFileLabel.SetText("MmgBmp From Path");
        bmpFileLabel.SetPosition(545, GetY() + 70);
        AddObj(bmpFileLabel);
        
        bmpCustomFill = MmgHelper.CreateFilledBmp(50, 50, MmgColor.GetCalmBlue());
        bmpCustomFill.SetY(GetY() + 210);
        bmpCustomFill.SetX(205);
        AddObj(bmpCustomFill);        
        
        bmpCustomFillLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        bmpCustomFillLabel.SetText("MmgBmp Created Custom with Fill");
        bmpCustomFillLabel.SetPosition(45, GetY() + 190);
        AddObj(bmpCustomFillLabel);
                
        bmpSet = MmgHelper.CreateDrawableBmpSet(bmpCache.GetWidth()/2, bmpCache.GetHeight()/2, true);
        srcRect = new MmgRect(0, 0, bmpCache.GetHeight()/2, bmpCache.GetWidth()/2);
        dstRect = new MmgRect(0, 0, bmpCache.GetHeight()/2, bmpCache.GetWidth()/2);        
        bmpSet.p.DrawBmp(bmpCache, srcRect, dstRect);
        
        bmpSet.img.SetY(GetY() + 210);
        bmpSet.img.SetX(650);
        AddObj(bmpSet.img);        
        
        bmpPartialCopyLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        bmpPartialCopyLabel.SetText("MmgBmp Custom with Copy");
        bmpPartialCopyLabel.SetPosition(505, GetY() + 190);
        AddObj(bmpPartialCopyLabel);        
        
        bmpScaled = MmgBmpScaler.ScaleMmgBmp(bmpCache, 1.50, true);
        bmpScaled.SetPosition(213, GetY() + 330);        
        AddObj(bmpScaled);
        
        bmpScaledLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        bmpScaledLabel.SetText("MmgBmp Custom Scaled");
        bmpScaledLabel.SetPosition(90, GetY() + 310);
        AddObj(bmpScaledLabel);
        
        bmpRotate = MmgBmpScaler.RotateMmgBmp(bmpCache, 90, true);
        bmpRotate.SetPosition(645, GetY() + 330);        
        AddObj(bmpRotate);        
        
        bmpRotateLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        bmpRotateLabel.SetText("MmgBmp Custom Rotated");
        bmpRotateLabel.SetPosition(515, GetY() + 310);
        AddObj(bmpRotateLabel);        
        
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
        Helper.wr("ScreenTestMmgBmp.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgBmp.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgBmp.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgBmp.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgBmp.ProcessAClick");
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
        Helper.wr("ScreenTestMmgBmp.ProcessBClick");        
        return true;
    }
    
    /**
     * 
     */
    @Override
    public void ProcessDebugClick() {
        Helper.wr("ScreenTestMmgBmp.ProcessDebugClick");
    }

    /**
     * 
     * 
     * @param dir
     * @return 
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        Helper.wr("ScreenTestMmgBmp.ProcessDpadPress: " + dir);
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
        Helper.wr("ScreenTestMmgBmp.ProcessDpadRelease: " + dir);
        if(dir == GameSettings.RIGHT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_06);            
            
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_04);
            
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
        Helper.wr("ScreenTestMmgBmp.ProcessDpadClick: " + dir);        
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
        Helper.wr("ScreenTestMmgBmp.ProcessScreenClick");        
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
        Helper.wr("ScreenTestMmgBmp.ProcessScreenClick");
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
        Helper.wr("ScreenTestMmgBmp.ProcessKeyClick");
        return true;
    }
    
    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);
        
        bmpCache = null;
        bmpCacheLabel = null;
        bmpCustomFill = null;
        bmpCustomFillLabel = null;
        bmpFile = null;
        bmpFileLabel = null;
        bmpPartialCopy = null;
        bmpPartialCopyLabel = null;
        bmpRotate = null;
        bmpRotateLabel = null;
        bmpScaled = null;
        bmpScaledLabel = null;
        bmpSet = null;
        
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
        Helper.wr("ScreenTestMmgBmp.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * 
     * 
     * @param e 
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        Helper.wr("ScreenTestMmg9Slice.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());        
    }
}

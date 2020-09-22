package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
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
 * A game screen class that extends the MmgGameScreen base class.
 * This class is for testing API classes.
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
     * An MmgFont class instance used to display the MmgScreenData's default height.
     */
    private MmgFont defaultHeightLabel;
    
    /**
     * An MmgFont class instance used to display the MmgScreenData's default width.
     */
    private MmgFont defaultWidthLabel;
       
    /**
     * An MmgFont class instance used to display the MmgScreenData's game height.
     */
    private MmgFont gameHeightLabel;
    
    /**
     * An MmgFont class instance used to display the MmgScreenData's game width.
     */
    private MmgFont gameWidthLabel;
    
    /**
     * An MmgFont class instance used to display the MmgScreenData's left coordinate.
     */
    private MmgFont gameLeftLabel;
    
    /**
     * An MmgFont class instance used to display the MmgScreenData's top coordinate.
     */
    private MmgFont gameTopLabel;    

    /**
     * An MmgFont class instance used to display the MmgScreenData's screen height.
     */
    private MmgFont screenHeightLabel;
    
    /**
     * An MmgFont class instance used to display the MmgScreenData's screen width.
     */
    private MmgFont screenWidthLabel;

    /**
     * An MmgFont class instance used to display the MmgScreenData's X scale value.
     */
    private MmgFont scaleXLabel;
    
    /**
     * An MmgFont class instance used to display the MmgScreenData's Y scale value.
     */
    private MmgFont scaleYLabel;
    
    /**
     * An MmgFont class instance used as the title for the test game screen.
     */
    private MmgFont title;
    
    /**
     * A boolean flag indicating if there is work to do in the next MmgUpdate call.
     */
    private boolean isDirty = false;
    
    /**
     * A private boolean flag used in the MmgUpdate method during the update process.
     */
    private boolean lret = false;
    
    /**
     * Constructor, sets the game state associated with this screen, and sets the owner GamePanel instance.
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
        MmgHelper.wr("ScreenTestMmgScreenData.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        MmgHelper.wr("ScreenTestMmgScreenData.SetGenericEventHandler");
        handler = Handler;
    }

    /**
     * Gets the GenericEventHandler this game screen uses to handle GenericEvents.
     * 
     * @return      The GenericEventHandler this screen uses to handle GenericEvents.
     */
    public GenericEventHandler GetGenericEventHandler() {
        return handler;
    }
       
    /**
     * Loads all the resources needed to display this game screen.
     */
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        MmgHelper.wr("ScreenTestMmgScreenData.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());
        
        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Screen Data (1 / " + GamePanel.TOTAL_TESTS + ")  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + MmgHelper.ScaleValue(30));
        AddObj(title);
                
        int yDiff = MmgHelper.ScaleValue(40);
        int yStrt = GetY() + MmgHelper.ScaleValue(140);
        int xLeft = MmgHelper.ScaleValue(200);
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
                
        xLeft = GetWidth()/2 + MmgHelper.ScaleValue(70);
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
     * Expects a relative X, Y vector that takes into account the game's offset and the current panel's
     * offset.
     * 
     * @param v     The coordinates of the mouse event.
     * @return      A boolean indicating if the event was handled or not.
     */ 
    @Override
    public boolean ProcessMousePress(MmgVector2 v) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessScreenPress");
        return ProcessMousePress(v.GetX(), v.GetY());
    }

    /**
     * Expects a relative X, Y values that takes into account the game's offset and the current panel's
     * offset.
     * 
     * @param x     The X coordinate of the mouse.
     * @param y     The Y coordinate of the mouse.
     * @return      A boolean indicating if the event was handled or not.
     */
    @Override
    public boolean ProcessMousePress(int x, int y) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessScreenPress");
        return true;
    }

    /**
     * Expects a relative X, Y vector that takes into account the game's offset and the current panel's
     * offset.
     * 
     * @param v     The coordinates of the mouse event.
     * @return      A boolean indicating if the event was handled or not.
     */
    @Override
    public boolean ProcessMouseRelease(MmgVector2 v) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessScreenRelease");
        return ProcessMousePress(v.GetX(), v.GetY());
    }

    /**
     * Expects a relative X, Y values that takes into account the game's offset and the current panel's offset.
     * 
     * @param x     The X coordinate of the event.
     * @param y     The Y coordinate of the event.
     * @return      A boolean indicating if the event was handled or not.      
     */
    @Override
    public boolean ProcessMouseRelease(int x, int y) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessScreenRelease");
        return true;
    }
    
    /**
     * A method to handle A click events.
     * 
     * @param src       The source gamepad, keyboard of the A event.
     * @return          A boolean indicating if this event was handled or not.
     */
    @Override
    public boolean ProcessAClick(int src) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessAClick");
        return true;
    }
    
    /**
     * A method to handle B click events.
     * 
     * @param src       The source gamepad, keyboard of the B event.
     * @return          A boolean indicating if this event was handled or not.
     */
    @Override
    public boolean ProcessBClick(int src) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessBClick");        
        return true;
    }
    
    /**
     * A method to handle special debug events that can be customized for each game.
     */
    @Override
    public void ProcessDebugClick() {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessDebugClick");
    }

    /**
     * A method to handle dpad press events.
     * 
     * @param dir       The direction id for the dpad event.
     * @return          A boolean indicating if this event was handled or not.
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessDpadPress: " + dir);
        return true;
    }

    /**
     * A method to handle dpad release events.
     * 
     * @param dir       The direction id for the dpad event.
     * @return          A boolean indicating if this event was handled or not.
     */
    @Override
    public boolean ProcessDpadRelease(int dir) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessDpadRelease: " + dir);
        if(dir == GameSettings.RIGHT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_02);
        
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_26);
            
        }
        return true;
    }
    
    /**
     * A method to handle dpad click events.
     * 
     * @param dir       The direction id for the dpad event.
     * @return          A boolean indicating if this event was handled or not.
     */
    @Override
    public boolean ProcessDpadClick(int dir) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessDpadClick: " + dir);        
        return true;
    }
    
    /**
     * Process a screen click. 
     * Expects coordinate that don't take into account the offset of the game and panel.
     *
     * @param v     The coordinates of the click.
     * @return      Boolean indicating if a menu item was the target of the click, menu item event is fired automatically by this class.
     */
    @Override
    public boolean ProcessMouseClick(MmgVector2 v) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessScreenClick");        
        return ProcessMouseClick(v.GetX(), v.GetY());
    }

    /**
     * Process a screen click. 
     * Expects coordinate that don't take into account the offset of the game and panel.
     *
     * @param x     The X axis coordinate of the screen click.
     * @param y     The Y axis coordinate of the screen click.
     * @return      Boolean indicating if a menu item was the target of the click, menu item event is fired automatically by this class.
     */
    @Override
    public boolean ProcessMouseClick(int x, int y) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessScreenClick");
        return true;
    }    
    
    /**
     * A method to handle keyboard click events.
     * 
     * @param c         The key used in the event.
     * @param code      The code of the key used in the event.
     * @return          A boolean indicating if this event was handled or not.
     */
    @Override
    public boolean ProcessKeyClick(char c, int code) {
        MmgHelper.wr("ScreenTestMmgScreenData.ProcessKeyClick");
        return true;
    }
    
    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);
        
        title = null;
        defaultHeightLabel = null;
        defaultWidthLabel = null;
        gameHeightLabel = null;
        gameLeftLabel = null;
        gameTopLabel = null;
        gameWidthLabel = null;
        
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
     * The callback method to handle GenericEventMessage objects.
     * 
     * @param obj       A GenericEventMessage object instance to process.
     */
    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        MmgHelper.wr("ScreenTestMmgScreenData.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * The callback method to handle MmgEvent objects.
     * 
     * @param e         An MmgEvent object instance to process.
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        MmgHelper.wr("ScreenTestMmgScreenData.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());
    }
}

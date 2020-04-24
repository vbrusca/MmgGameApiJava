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
public class ScreenTestMmgColor extends MmgGameScreen implements GenericEventHandler, MmgEventHandler {

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
    private MmgFont color1Label;
    
    /**
     * 
     */
    private MmgFont color2Label;
       
    /**
     * 
     */
    private MmgFont color3Label;
    
    /**
     * 
     */
    private MmgFont color4Label;
    
    /**
     * 
     */
    private MmgFont color5Label;
    
    /**
     * 
     */
    private MmgFont color6Label;    

    /**
     * 
     */
    private MmgFont color7Label;
    
    /**
     * 
     */
    private MmgFont color8Label;

    /**
     * 
     */
    private MmgFont color9Label;
    
    /**
     * 
     */
    private MmgFont color10Label;
    
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
    public ScreenTestMmgColor(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMmgColor.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMmgColor.SetGenericEventHandler");
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
        Helper.wr("ScreenTestMmgColor.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());
        
        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Color (11)  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + 30);
        AddObj(title);
                
        int yDiff = 40;
        int yStrt = GetY() + 140;
        int xLeft = 200;
        int i = 0;
        
        color1Label = MmgFontData.CreateDefaultBoldMmgFontLg();
        color1Label.SetMmgColor(MmgColor.GetBlueGray());
        color1Label.SetText("Color: BlueGray");
        color1Label.SetX(xLeft);
        color1Label.SetY(yStrt + (yDiff * i));
        AddObj(color1Label);
        i++;
        
        color2Label = MmgFontData.CreateDefaultBoldMmgFontLg();
        color2Label.SetMmgColor(MmgColor.GetCarbonGray());
        color2Label.SetText("Color: CarbonGray");
        color2Label.SetX(xLeft);
        color2Label.SetY(yStrt + (yDiff * i));
        AddObj(color2Label);
        i++;
        
        color3Label = MmgFontData.CreateDefaultBoldMmgFontLg();
        color3Label.SetMmgColor(MmgColor.GetDarkRed());
        color3Label.SetText("Color: DarkRed");
        color3Label.SetX(xLeft);
        color3Label.SetY(yStrt + (yDiff * i));
        AddObj(color3Label);
        i++;
                
        color4Label = MmgFontData.CreateDefaultBoldMmgFontLg();
        color4Label.SetMmgColor(MmgColor.GetIridium());
        color4Label.SetText("Color: Iridium");
        color4Label.SetX(xLeft);
        color4Label.SetY(yStrt + (yDiff * i));
        AddObj(color4Label);
        i++;
        
        color5Label = MmgFontData.CreateDefaultBoldMmgFontLg();
        color5Label.SetMmgColor(MmgColor.GetLimeGreen());
        color5Label.SetText("Color: LimeGreen");
        color5Label.SetX(xLeft);
        color5Label.SetY(yStrt + (yDiff * i));
        AddObj(color5Label);
        i++;        
                
        xLeft = GetWidth()/2 + 70;
        i = 0;
        
        color6Label = MmgFontData.CreateDefaultBoldMmgFontLg();
        color6Label.SetMmgColor(MmgColor.GetMaroon());
        color6Label.SetText("Color: Maroon");
        color6Label.SetX(xLeft);
        color6Label.SetY(yStrt + (yDiff * i));
        AddObj(color6Label);
        i++;
        
        color7Label = MmgFontData.CreateDefaultBoldMmgFontLg();
        color7Label.SetMmgColor(MmgColor.GetOil());
        color7Label.SetText("Color: Oil");
        color7Label.SetX(xLeft);
        color7Label.SetY(yStrt + (yDiff * i));
        AddObj(color7Label);
        i++;
        
        color8Label = MmgFontData.CreateDefaultBoldMmgFontLg();
        color8Label.SetMmgColor(MmgColor.GetOlive());
        color8Label.SetText("Color: Olive");
        color8Label.SetX(xLeft);
        color8Label.SetY(yStrt + (yDiff * i));
        AddObj(color8Label);
        i++;
        
        color9Label = MmgFontData.CreateDefaultBoldMmgFontLg();
        color9Label.SetMmgColor(MmgColor.GetOrange());
        color9Label.SetText("Color: Orange");
        color9Label.SetX(xLeft);
        color9Label.SetY(yStrt + (yDiff * i));
        AddObj(color9Label);
        i++;
        
        color10Label = MmgFontData.CreateDefaultBoldMmgFontLg();
        color10Label.SetMmgColor(MmgColor.GetPlatinum());
        color10Label.SetText("Color: Platinum");
        color10Label.SetX(xLeft);
        color10Label.SetY(yStrt + (yDiff * i));
        AddObj(color10Label);
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
        Helper.wr("ScreenTestMmgColor.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgColor.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgColor.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgColor.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgColor.ProcessAClick");
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
        Helper.wr("ScreenTestMmgColor.ProcessBClick");        
        return true;
    }
    
    /**
     * 
     */
    @Override
    public void ProcessDebugClick() {
        Helper.wr("ScreenTestMmgColor.ProcessDebugClick");
    }

    /**
     * 
     * 
     * @param dir
     * @return 
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        Helper.wr("ScreenTestMmgColor.ProcessDpadPress: " + dir);
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
        Helper.wr("ScreenTestMmgColor.ProcessDpadRelease: " + dir);
        if(dir == GameSettings.RIGHT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_12);
        
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_10);
            
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
        Helper.wr("ScreenTestMmgColor.ProcessDpadClick: " + dir);        
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
        Helper.wr("ScreenTestMmgColor.ProcessScreenClick");        
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
        Helper.wr("ScreenTestMmgColor.ProcessScreenClick");
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
        Helper.wr("ScreenTestMmgColor.ProcessKeyClick");
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
        Helper.wr("ScreenTestMmgColor.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * 
     * 
     * @param e 
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        Helper.wr("ScreenTestMmgColor.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());
    }
}

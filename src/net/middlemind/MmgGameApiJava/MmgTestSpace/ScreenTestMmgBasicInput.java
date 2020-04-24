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
public class ScreenTestMmgBasicInput extends MmgGameScreen implements GenericEventHandler, MmgEventHandler {

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
    private MmgFont processABtnPress;    
    
    /**
     * 
     */
    private MmgFont processABtnRelease;
    
    /**
     * 
     */
    private MmgFont processABtnClick;
        
    /**
     * 
     */
    private MmgFont processBBtnPress;    
    
    /**
     * 
     */
    private MmgFont processBBtnRelease;    
    
    /**
     * 
     */
    private MmgFont processBBtnClick;
    
    /**
     * 
     */
    private MmgFont processDebugClick;    
    
    /**
     * 
     */
    private MmgFont processUpBtnPress;
    
    /**
     * 
     */
    private MmgFont processUpBtnRelease;    
    
    /**
     * 
     */
    private MmgFont processUpBtnClick;    
    
    /**
     * 
     */
    private MmgFont processDownBtnPress;
    
    /**
     * 
     */
    private MmgFont processDownBtnRelease;    
    
    /**
     * 
     */
    private MmgFont processDownBtnClick;    
    
    /**
     * 
     */
    private MmgFont processLeftBtnPress;
    
    /**
     * 
     */
    private MmgFont processLeftBtnRelease;    
    
    /**
     * 
     */
    private MmgFont processLeftBtnClick;    
    
    /**
     * 
     */
    private MmgFont processRightBtnPress;
    
    /**
     * 
     */
    private MmgFont processRightBtnRelease;    
    
    /**
     * 
     */
    private MmgFont processRightBtnClick;    
    
    /**
     * 
     */
    private MmgFont processKeyPress;
    
    /**
     * 
     */
    private MmgFont processKeyRelease;    
    
    /**
     * 
     */
    private MmgFont processKeyClick;
    
    /**
     * 
     */
    private MmgFont processMousePress;
    
    /**
     * 
     */
    private MmgFont processMouseRelease;    
    
    
    /**
     * 
     */
    private MmgFont processMouseClick;
    
    /**
     * 
     */
    private MmgFont processMouseMove;    
        
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
    public ScreenTestMmgBasicInput(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMmgBasicInput.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMmgBasicInput.SetGenericEventHandler");
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
        Helper.wr("ScreenTestMmgBasicInput.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());
        
        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Basic Input (9)  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + 30);
        AddObj(title);

        int yDiff = 25;
        int yStrt = GetY() + 60;
        int xLeft = 20;
        int i = 0;
                
        processABtnPress = MmgFontData.CreateDefaultBoldMmgFontSm();
        processABtnPress.SetText("ProcessABtnPress (A): ");
        processABtnPress.SetX(xLeft);
        processABtnPress.SetY(yStrt + (yDiff * i));
        AddObj(processABtnPress);
        i++;
        
        processABtnRelease = MmgFontData.CreateDefaultBoldMmgFontSm();
        processABtnRelease.SetText("ProcessABtnRelease (A): ");
        processABtnRelease.SetX(xLeft);
        processABtnRelease.SetY(yStrt + (yDiff * i));
        AddObj(processABtnRelease);
        i++;
        
        processABtnClick = MmgFontData.CreateDefaultBoldMmgFontSm();
        processABtnClick.SetText("ProcessABtnClick (A): ");
        processABtnClick.SetX(xLeft);
        processABtnClick.SetY(yStrt + (yDiff * i));
        AddObj(processABtnClick);
        i++;
                
        processBBtnPress = MmgFontData.CreateDefaultBoldMmgFontSm();
        processBBtnPress.SetText("ProcessBBtnPress (A): ");
        processBBtnPress.SetX(xLeft);
        processBBtnPress.SetY(yStrt + (yDiff * i));
        AddObj(processBBtnPress);
        i++;
        
        processBBtnRelease = MmgFontData.CreateDefaultBoldMmgFontSm();
        processBBtnRelease.SetText("ProcessBBtnRelease (A): ");
        processBBtnRelease.SetX(xLeft);
        processBBtnRelease.SetY(yStrt + (yDiff * i));
        AddObj(processBBtnRelease);
        i++;
        
        processBBtnClick = MmgFontData.CreateDefaultBoldMmgFontSm();
        processBBtnClick.SetText("ProcessBBtnClick (B): ");
        processBBtnClick.SetX(xLeft);
        processBBtnClick.SetY(yStrt + (yDiff * i));
        AddObj(processBBtnClick);
        i++;
        
        processDebugClick = MmgFontData.CreateDefaultBoldMmgFontSm();
        processDebugClick.SetText("ProcessDebugClick (D): ");
        processDebugClick.SetX(xLeft);
        processDebugClick.SetY(yStrt + (yDiff * i));
        AddObj(processDebugClick);
        i++;        
        
        processUpBtnPress = MmgFontData.CreateDefaultBoldMmgFontSm();
        processUpBtnPress.SetText("ProcessUpBtnPress: ");
        processUpBtnPress.SetX(xLeft);
        processUpBtnPress.SetY(yStrt + (yDiff * i));
        AddObj(processUpBtnPress);
        i++;
        
        processUpBtnRelease = MmgFontData.CreateDefaultBoldMmgFontSm();
        processUpBtnRelease.SetText("ProcessUpBtnRelease: ");
        processUpBtnRelease.SetX(xLeft);
        processUpBtnRelease.SetY(yStrt + (yDiff * i));
        AddObj(processUpBtnRelease);
        i++;
        
        processUpBtnClick = MmgFontData.CreateDefaultBoldMmgFontSm();
        processUpBtnClick.SetText("ProcessUpBtnClick: ");
        processUpBtnClick.SetX(xLeft);
        processUpBtnClick.SetY(yStrt + (yDiff * i));
        AddObj(processUpBtnClick);
        i++;
        
        processDownBtnPress = MmgFontData.CreateDefaultBoldMmgFontSm();
        processDownBtnPress.SetText("ProcessDownBtnPress: ");
        processDownBtnPress.SetX(xLeft);
        processDownBtnPress.SetY(yStrt + (yDiff * i));
        AddObj(processDownBtnPress);
        i++;
        
        processDownBtnRelease = MmgFontData.CreateDefaultBoldMmgFontSm();
        processDownBtnRelease.SetText("ProcessDownBtnRelease: ");
        processDownBtnRelease.SetX(xLeft);
        processDownBtnRelease.SetY(yStrt + (yDiff * i));
        AddObj(processDownBtnRelease);
        i++;
        
        processDownBtnClick = MmgFontData.CreateDefaultBoldMmgFontSm();
        processDownBtnClick.SetText("ProcessDownBtnClick: ");
        processDownBtnClick.SetX(xLeft);
        processDownBtnClick.SetY(yStrt + (yDiff * i));
        AddObj(processDownBtnClick);
        i++;
        
        xLeft = GetWidth()/2 + 50;
        i = 0;        
        processLeftBtnPress = MmgFontData.CreateDefaultBoldMmgFontSm();
        processLeftBtnPress.SetText("ProcessLeftBtnPress: ");
        processLeftBtnPress.SetX(xLeft);
        processLeftBtnPress.SetY(yStrt + (yDiff * i));
        AddObj(processLeftBtnPress);
        i++;
        
        processLeftBtnRelease = MmgFontData.CreateDefaultBoldMmgFontSm();
        processLeftBtnRelease.SetText("ProcessLeftBtnRelease: ");
        processLeftBtnRelease.SetX(xLeft);
        processLeftBtnRelease.SetY(yStrt + (yDiff * i));
        AddObj(processLeftBtnRelease);
        i++;
        
        processLeftBtnClick = MmgFontData.CreateDefaultBoldMmgFontSm();
        processLeftBtnClick.SetText("ProcessLeftBtnClick: ");
        processLeftBtnClick.SetX(xLeft);
        processLeftBtnClick.SetY(yStrt + (yDiff * i));
        AddObj(processLeftBtnClick);
        i++;
        
        processRightBtnPress = MmgFontData.CreateDefaultBoldMmgFontSm();
        processRightBtnPress.SetText("ProcessRightBtnPress: ");
        processRightBtnPress.SetX(xLeft);
        processRightBtnPress.SetY(yStrt + (yDiff * i));
        AddObj(processRightBtnPress);
        i++;
        
        processRightBtnRelease = MmgFontData.CreateDefaultBoldMmgFontSm();
        processRightBtnRelease.SetText("ProcessRightBtnRelease: ");
        processRightBtnRelease.SetX(xLeft);
        processRightBtnRelease.SetY(yStrt + (yDiff * i));
        AddObj(processRightBtnRelease);
        i++;
        
        processRightBtnClick = MmgFontData.CreateDefaultBoldMmgFontSm();
        processRightBtnClick.SetText("ProcessRightBtnClick: ");
        processRightBtnClick.SetX(xLeft);
        processRightBtnClick.SetY(yStrt + (yDiff * i));
        AddObj(processRightBtnClick);
        i++;
                
        processKeyPress = MmgFontData.CreateDefaultBoldMmgFontSm();
        processKeyPress.SetText("ProcessKeyPress: ");
        processKeyPress.SetX(xLeft);
        processKeyPress.SetY(yStrt + (yDiff * i));
        AddObj(processKeyPress);
        i++;
        
        processKeyRelease = MmgFontData.CreateDefaultBoldMmgFontSm();
        processKeyRelease.SetText("ProcessKeyRelease: ");
        processKeyRelease.SetX(xLeft);
        processKeyRelease.SetY(yStrt + (yDiff * i));
        AddObj(processKeyRelease);
        i++;
        
        processKeyClick = MmgFontData.CreateDefaultBoldMmgFontSm();
        processKeyClick.SetText("ProcessKeyClick: ");
        processKeyClick.SetX(xLeft);
        processKeyClick.SetY(yStrt + (yDiff * i));
        AddObj(processKeyClick);
        i++;
        
        processMousePress = MmgFontData.CreateDefaultBoldMmgFontSm();
        processMousePress.SetText("ProcessMousePress: ");
        processMousePress.SetX(xLeft);
        processMousePress.SetY(yStrt + (yDiff * i));
        AddObj(processMousePress);
        i++;
        
        processMouseRelease = MmgFontData.CreateDefaultBoldMmgFontSm();
        processMouseRelease.SetText("ProcessMouseRelease: ");
        processMouseRelease.SetX(xLeft);
        processMouseRelease.SetY(yStrt + (yDiff * i));
        AddObj(processMouseRelease);
        i++;
        
        processMouseClick = MmgFontData.CreateDefaultBoldMmgFontSm();
        processMouseClick.SetText("ProcessMouseClick: ");
        processMouseClick.SetX(xLeft);
        processMouseClick.SetY(yStrt + (yDiff * i));
        AddObj(processMouseClick);
        i++; 
        
        processMouseMove = MmgFontData.CreateDefaultBoldMmgFontSm();
        processMouseMove.SetText("ProcessMouseMove: ");
        processMouseMove.SetX(xLeft);
        processMouseMove.SetY(yStrt + (yDiff * i));
        AddObj(processMouseMove);
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
        Helper.wr("ScreenTestMmgBasicInput.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgBasicInput.ProcessScreenPress");
        processMousePress.SetText("ProcessMousePress: X: " + x + " Y: " + y);
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
        Helper.wr("ScreenTestMmgBasicInput.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgBasicInput.ProcessScreenRelease");
        processMouseRelease.SetText("ProcessMouseRelease: X: " + x + " Y: " + y);        
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
        Helper.wr("ScreenTestMmgBasicInput.ProcessMouseClick");        
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
        Helper.wr("ScreenTestMmgBasicInput.ProcessMouseClick");
        processMouseClick.SetText("ProcessMouseClick: X: " + x + " Y: " + y);
        return true;
    }    
    
    /**
     * 
     * 
     * @param x
     * @param y
     * @return 
     */
    @Override
    public boolean ProcessMouseMove(int x, int y) {
        Helper.wr("ScreenTestMmgBasicInput.ProcessMouseMove");
        processMouseMove.SetText("ProcessMouseMove: X: " + x + " Y: " + y);
        return true;
    }         
    
    /**
     * 
     * 
     * @param src
     * @return 
     */
    @Override
    public boolean ProcessAPress(int src) {
        Helper.wr("ScreenTestMmgBasicInput.ProcessAPress");
        processABtnPress.SetText("ProcessABtnPress (A): " + System.currentTimeMillis());
        return true;
    }
    
    /**
     * 
     * 
     * @param src
     * @return 
     */
    @Override
    public boolean ProcessARelease(int src) {
        Helper.wr("ScreenTestMmgBasicInput.ProcessARelease");
        processABtnRelease.SetText("ProcessABtnRelease (A): " + System.currentTimeMillis());
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
        Helper.wr("ScreenTestMmgBasicInput.ProcessAClick");
        processABtnClick.SetText("ProcessABtnClick (A): " + System.currentTimeMillis());
        return true;
    }
    
    /**
     * 
     * 
     * @param src
     * @return 
     */
    @Override
    public boolean ProcessBPress(int src) {
        Helper.wr("ScreenTestMmgBasicInput.ProcessBPress");
        processBBtnPress.SetText("ProcessBBtnPress (A): " + System.currentTimeMillis());
        return true;
    }
    
    /**
     * 
     * 
     * @param src
     * @return 
     */
    @Override
    public boolean ProcessBRelease(int src) {
        Helper.wr("ScreenTestMmgBasicInput.ProcessBRelease");
        processBBtnRelease.SetText("ProcessBBtnRelease (A): " + System.currentTimeMillis());
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
        Helper.wr("ScreenTestMmgBasicInput.ProcessBClick");
        processBBtnClick.SetText("ProcessBBtnClick (B): " + System.currentTimeMillis());       
        return true;
    }
    
    /**
     * 
     */
    @Override
    public void ProcessDebugClick() {
        Helper.wr("ScreenTestMmgBasicInput.ProcessDebugClick");
        processDebugClick.SetText("ProcessDebugClick (D): " + System.currentTimeMillis()); 
    }

    /**
     * 
     * 
     * @param dir
     * @return 
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        Helper.wr("ScreenTestMmgBasicInput.ProcessDpadPress: " + dir);
        if(dir == GameSettings.UP_KEYBOARD) {
            processUpBtnPress.SetText("ProcessUpBtnPress: " + System.currentTimeMillis());
        
        } else if(dir == GameSettings.DOWN_KEYBOARD) {
            processDownBtnPress.SetText("ProcessDownBtnPress: " + System.currentTimeMillis());            
            
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            processLeftBtnPress.SetText("ProcessLeftBtnPress: " + System.currentTimeMillis());

        } else if(dir == GameSettings.RIGHT_KEYBOARD) {
            processRightBtnPress.SetText("ProcessRightBtnPress: " + System.currentTimeMillis());            
            
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
    public boolean ProcessDpadRelease(int dir) {
        Helper.wr("ScreenTestMmgBasicInput.ProcessDpadRelease: " + dir);
        if(dir == GameSettings.UP_KEYBOARD) {
            processUpBtnRelease.SetText("ProcessUpBtnRelease: " + System.currentTimeMillis());
        
        } else if(dir == GameSettings.DOWN_KEYBOARD) {
            processDownBtnRelease.SetText("ProcessDownBtnRelease: " + System.currentTimeMillis());            
            
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            processLeftBtnRelease.SetText("ProcessLeftBtnRelease: " + System.currentTimeMillis());

        } else if(dir == GameSettings.RIGHT_KEYBOARD) {
            processRightBtnRelease.SetText("ProcessRightBtnRelease: " + System.currentTimeMillis());            
            
        }        
        
        if(dir == GameSettings.RIGHT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_10);
        
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_08);
            
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
        Helper.wr("ScreenTestMmgBasicInput.ProcessDpadClick: " + dir);
        if(dir == GameSettings.UP_KEYBOARD) {
            processUpBtnClick.SetText("ProcessUpBtnClick: " + System.currentTimeMillis());
        
        } else if(dir == GameSettings.DOWN_KEYBOARD) {
            processDownBtnClick.SetText("ProcessDownBtnClick: " + System.currentTimeMillis());            
            
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            processLeftBtnClick.SetText("ProcessLeftBtnClick: " + System.currentTimeMillis());

        } else if(dir == GameSettings.RIGHT_KEYBOARD) {
            processRightBtnClick.SetText("ProcessRightBtnClick: " + System.currentTimeMillis());            
            
        }        
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
    public boolean ProcessKeyPress(char c, int code) {
        Helper.wr("ScreenTestMmgBasicInput.ProcessKeyPress: " + code);
        processKeyPress.SetText(("ProcessKeyPress: " + System.currentTimeMillis()));
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
    public boolean ProcessKeyRelease(char c, int code) {
        Helper.wr("ScreenTestMmgBasicInput.ProcessKeyRelease: " + code);
        processKeyRelease.SetText(("ProcessKeyRelease: " + System.currentTimeMillis()));
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
        Helper.wr("ScreenTestMmgBasicInput.ProcessKeyClick: " + code);
        processKeyClick.SetText(("ProcessKeyClick: " + System.currentTimeMillis()));
        return true;
    }
            
    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);
        title = null;
        
        processABtnClick = null;
        processBBtnClick = null;
        processDownBtnClick = null;
        processDownBtnPress = null;
        processDownBtnRelease = null;
        processKeyClick = null;
        processKeyPress = null;
        processKeyRelease = null;
        processLeftBtnClick = null;
        processLeftBtnPress = null;
        processLeftBtnRelease = null;
        processRightBtnClick = null;
        processRightBtnPress = null;
        processRightBtnRelease = null;
        processUpBtnClick = null;
        processUpBtnPress = null;
        processUpBtnRelease = null;
        
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
        Helper.wr("ScreenTestMmgBasicInput.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * 
     * 
     * @param e 
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        Helper.wr("ScreenTestMmgBasicInput.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());
    }
}

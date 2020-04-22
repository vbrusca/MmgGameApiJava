package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEvent;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEventHandler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFont;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFontData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGameScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgTextField;
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
public class ScreenTestMmgTextField extends MmgGameScreen implements GenericEventHandler, MmgEventHandler {

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
    private MmgBmp bground;
        
    /**
     * 
     */
    private MmgTextField txtField;
    
    /**
     * 
     */
    private MmgFont txtFieldLabel;
    
    /**
     * 
     */
    private MmgFont maxLenLabel;
    
    /**
     * 
     */
    private MmgFont txtFieldText;
    
    /**
     * 
     */
    private MmgFont txtFieldMaxLenError;
    
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
    public ScreenTestMmgTextField(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMsgTextField.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMsgTextField.SetGenericEventHandler");
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
        Helper.wr("ScreenTestMsgTextField.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());

        int width = MmgHelper.ScaleValue(200);
        int height = MmgHelper.ScaleValue(50);
        
        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Text Field  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + 30);
        AddObj(title);        
        
        bground = Helper.GetBasicCachedBmp("popup_window_base.png");
        
        txtField = new MmgTextField(bground, MmgFontData.CreateDefaultMmgFontLg(), width, height, 12, 15);
        MmgHelper.CenterHorAndVert(txtField);
        txtField.SetMaxLengthOn(true);
        txtField.SetEventHandler(this);
        txtField.SetY(txtField.GetY() - 30);
        AddObj(txtField);
        
        txtFieldLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtFieldLabel.SetText("MmgTextField Example");
        MmgHelper.CenterHorAndVert(txtFieldLabel);
        txtFieldLabel.SetY(txtFieldLabel.GetY() - 55);
        AddObj(txtFieldLabel);
        
        txtFieldText = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtFieldText.SetText("Text Field Text: ");
        MmgHelper.CenterHorAndVert(txtFieldText);
        txtFieldText.SetY(txtFieldText.GetY() + 40);
        AddObj(txtFieldText);
        
        maxLenLabel = MmgFontData.CreateDefaultBoldMmgFontLg();
        maxLenLabel.SetText("Max Len Error On: " + txtField.IsMaxLengthOn() + " Max Len: " + MmgTextField.DEFAULT_MAX_LENGTH);
        MmgHelper.CenterHorAndVert(maxLenLabel);
        maxLenLabel.SetY(maxLenLabel.GetY() + 70);
        AddObj(maxLenLabel);
        
        txtFieldMaxLenError = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtFieldMaxLenError.SetText("Max Len Error Current Time MS: ");
        MmgHelper.CenterHorAndVert(txtFieldMaxLenError);
        txtFieldMaxLenError.SetY(txtFieldMaxLenError.GetY() + 100);
        AddObj(txtFieldMaxLenError);
        
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
        Helper.wr("ScreenTestMsgTextField.ProcessScreenPress");
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
        Helper.wr("ScreenTestMsgTextField.ProcessScreenPress");
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
        Helper.wr("ScreenTestMsgTextField.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMsgTextField.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMsgTextField.ProcessAClick");
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
        Helper.wr("ScreenTestMsgTextField.ProcessBClick");        
        return true;
    }
    
    /**
     * 
     */
    @Override
    public void ProcessDebugClick() {
        Helper.wr("ScreenTestMsgTextField.ProcessDebugClick");
    }

    /**
     * 
     * 
     * @param dir
     * @return 
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        Helper.wr("ScreenTestMsgTextField.ProcessDpadPress: " + dir);
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
        Helper.wr("ScreenTestMsgTextField.ProcessDpadRelease: " + dir);
        if(dir == GameSettings.RIGHT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_05);
        
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_03);
            
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
        Helper.wr("ScreenTestMsgTextField.ProcessDpadClick: " + dir);        
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
        Helper.wr("ScreenTestMsgTextField.ProcessScreenClick");        
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
        Helper.wr("ScreenTestMsgTextField.ProcessScreenClick");
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
        if(Character.isLetterOrDigit(c)) {
            txtField.ProcessKeyClick(c, code);            
        } else if(code == 8) {
            txtField.DeleteChar();
        }
        txtFieldText.SetText("Text Field Text: " + txtField.GetTextFieldString());
        MmgHelper.CenterHor(txtFieldText);
        return true;
    }
    
    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);
        bground = null;
        txtField = null;
        title = null;
        txtFieldLabel = null;
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
     * @param updateTick
     * @param currentTimeMs
     * @param msSinceLastFrame
     * @return 
     */
    @Override
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        lret = false;

        if (pause == false && isVisible == true) {
            //always run this update
            txtField.MmgUpdate(updateTick, currentTimeMs, msSinceLastFrame);
            
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
     * 
     * 
     * @param obj 
     */
    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        Helper.wr("ScreenTestMsgTextField.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * 
     * 
     * @param e 
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        Helper.wr("ScreenTestMsgTextField.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());   
        if(e.GetMessage() != null && e.GetMessage().equals("error_max_length") == true) {
            txtFieldMaxLenError.SetText("Max Len Error Current Time MS: " + System.currentTimeMillis());
            MmgHelper.CenterHor(txtFieldMaxLenError);
        }
    }
}

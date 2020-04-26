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
import net.middlemind.MmgGameApiJava.MmgCore.GameSettings;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventHandler;

/**
 * A game screen class that extends the MmgGameScreen base class.
 * This class is for testing API classes.
 * Created by Middlemind Games 02/25/2020
 * 
 * @author Victor G. Brusca
 */
public class ScreenTestMmgFont extends MmgGameScreen implements GenericEventHandler, MmgEventHandler {

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
     * An MmgFont class instance that is used as the title for this test game screen.
     */
    private MmgFont title;
    
    /**
     * An MmgFont class instance that is used as an example of bold large text.
     */
    private MmgFont fontBoldLg;    
    
    /**
     * An MmgFont class instance that is used as an example of bold medium text.
     */
    private MmgFont fontBoldMd;   
        
    /**
     * An MmgFont class instance that is used as an example of bold small text.
     */
    private MmgFont fontBoldSm;
    
    /**
     * An MmgFont class instance that is used as an example of normal large text.
     */
    private MmgFont fontNormLg;    
    
    /**
     * An MmgFont class instance that is used as an example of normal medium text.
     */
    private MmgFont fontNormMd;   
        
    /**
     * An MmgFont class instance that is used as an example of normal small text.
     */
    private MmgFont fontNormSm;    
    
    /**
     * An MmgFont class instance that is used as an example of italic large text.
     */
    private MmgFont fontItalicLg;    
    
    /**
     * An MmgFont class instance that is used as an example of italic medium text.
     */
    private MmgFont fontItalicMd;   
        
    /**
     * An MmgFont class instance that is used as an example of italic small text.
     */
    private MmgFont fontItalicSm;    
    
    /**
     * An MmgFont class instance that is used as an example of custom large text.
     */
    private MmgFont fontCustomLg;    
    
    /**
     * An MmgFont class instance that is used as an example of custom medium text.
     */
    private MmgFont fontCustomMd;    
    
    /**
     * An MmgFont class instance that is used as an example of custom small text.
     */
    private MmgFont fontCustomSm;
    
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
    public ScreenTestMmgFont(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMmgFont.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMmgFont.SetGenericEventHandler");
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
        Helper.wr("ScreenTestMmgFont.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());
        
        int y = 60;
        
        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Font (3)  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + 30);
        AddObj(title);        
        
        fontBoldLg = MmgFontData.CreateDefaultBoldMmgFontLg();
        fontBoldLg.SetText("Font Bold Large");
        fontBoldLg.SetY(GetY() + 15 + (y * 1));
        fontBoldLg.SetX(60);
        AddObj(fontBoldLg);
                
        fontBoldMd = MmgFontData.CreateDefaultBoldMmgFontLg();
        fontBoldMd.SetText("Font Bold Medium");
        fontBoldMd.SetY(GetY() + 15 + (y * 2));
        fontBoldMd.SetX(60);        
        AddObj(fontBoldMd);
        
        fontBoldSm = MmgFontData.CreateDefaultBoldMmgFontLg();
        fontBoldSm.SetText("Font Bold Small");
        fontBoldSm.SetY(GetY() + 15 + (y * 3));
        fontBoldSm.SetX(60);        
        AddObj(fontBoldSm);                 
                
        fontNormLg = MmgFontData.CreateDefaultMmgFontLg();
        fontNormLg.SetText("Font Norm Large");
        fontNormLg.SetY(GetY() + 15 + (y * 4));
        fontNormLg.SetX(60);
        AddObj(fontNormLg);
                
        fontNormMd = MmgFontData.CreateDefaultMmgFont(MmgFontData.GetFontSize());
        fontNormMd.SetText("Font Norm Medium");
        fontNormMd.SetY(GetY() + 15 + (y * 5));
        fontNormMd.SetX(60);        
        AddObj(fontNormMd);
        
        fontNormSm = MmgFontData.CreateDefaultMmgFontSm();
        fontNormSm.SetText("Font Norm Small");
        fontNormSm.SetY(GetY() + 15 + (y * 6));
        fontNormSm.SetX(60);        
        AddObj(fontNormSm);         
        
        fontItalicLg = MmgFontData.CreateDefaultItalicMmgFontLg();
        fontItalicLg.SetText("Font Italic Large");
        fontItalicLg.SetY(GetY() + 15 + (y * 1));
        fontItalicLg.SetX(GetWidth()/2 + 60);
        AddObj(fontItalicLg);
        
        fontItalicMd = MmgFontData.CreateDefaultItalicMmgFont(MmgFontData.GetFontSize());
        fontItalicMd.SetText("Font Italic Medium");
        MmgHelper.CenterHorAndVert(fontItalicMd);
        fontItalicMd.SetY(GetY() + 15 + (y * 2));
        fontItalicMd.SetX(GetWidth()/2 + 60);        
        AddObj(fontItalicMd);        
        
        fontItalicSm = MmgFontData.CreateDefaultItalicMmgFontSm();
        fontItalicSm.SetText("Font Italic Small");
        MmgHelper.CenterHorAndVert(fontItalicSm);
        fontItalicSm.SetY(GetY() + 15 + (y * 3));
        fontItalicSm.SetX(GetWidth()/2 + 60);        
        AddObj(fontItalicSm);        
        
        fontCustomLg = MmgFontData.CreateDefaultMmgFont(MmgFontData.GetFontSize() + 12);
        fontCustomLg.SetText("Font Custom Large");
        MmgHelper.CenterHorAndVert(fontCustomLg);
        fontCustomLg.SetY(GetY() + 15 + (y * 4));
        fontCustomLg.SetX(GetWidth()/2 + 60);
        AddObj(fontCustomLg);        
        
        fontCustomMd = MmgFontData.CreateDefaultMmgFont(MmgFontData.GetFontSize() + 8);
        fontCustomMd.SetText("Font Custom Medium");
        MmgHelper.CenterHorAndVert(fontCustomMd);
        fontCustomMd.SetY(GetY() + 15 + (y * 5));
        fontCustomMd.SetX(GetWidth()/2 + 60);
        AddObj(fontCustomMd);
        
        fontCustomSm = MmgFontData.CreateDefaultMmgFont(MmgFontData.GetFontSize() + 4);
        fontCustomSm.SetText("Font Custom Small");
        MmgHelper.CenterHorAndVert(fontCustomSm);
        fontCustomSm.SetY(GetY() + 15 + (y * 6));
        fontCustomSm.SetX(GetWidth()/2 + 60);        
        AddObj(fontCustomSm);
        
        ready = true;
        pause = false;
    }
    
    /**
     * A method to handle dpad release events.
     * 
     * @param dir       The direction id for the dpad event.
     * @return          A boolean indicating if this event was handled or not.
     */  
    @Override
    public boolean ProcessDpadRelease(int dir) {
        Helper.wr("ScreenTestMmgFont.ProcessDpadRelease: " + dir);
        if(dir == GameSettings.RIGHT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_04);
            
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_02);            
        
        }
        return true;
    }    
    
    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);
        fontBoldLg = null;
        fontBoldMd = null;
        fontBoldSm = null;
        fontCustomLg = null;
        fontCustomMd = null;
        fontCustomSm = null;
        fontItalicLg = null;
        fontItalicMd = null;
        fontItalicSm = null;
        fontNormLg = null;
        fontNormMd = null;
        fontNormSm = null;
        title = null;
        super.ClearObjs();
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
     * Base draw method, handles drawing this class.
     *
     * @param p     The MmgPen used to draw this object.
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
        Helper.wr("ScreenTestMmgFont.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * The callback method to handle MmgEvent objects.
     * 
     * @param e         An MmgEvent object instance to process.
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        Helper.wr("ScreenTestMmgFont.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());        
    }
}

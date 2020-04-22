package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import net.middlemind.MmgGameApiJava.MmgBase.Mmg9Slice;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
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
     * 
     */
    private MmgFont title;
    
    /**
     * 
     */
    private MmgFont fontBoldLg;    
    
    /**
     * 
     */
    private MmgFont fontBoldMd;   
        
    /**
     * 
     */
    private MmgFont fontBoldSm;
    
    /**
     * 
     */
    private MmgFont fontNormLg;    
    
    /**
     * 
     */
    private MmgFont fontNormMd;   
        
    /**
     * 
     */
    private MmgFont fontNormSm;    
    
    /**
     * 
     */
    private MmgFont fontItalicLg;    
    
    /**
     * 
     */
    private MmgFont fontItalicMd;   
        
    /**
     * 
     */
    private MmgFont fontItalicSm;    
    
    /**
     * 
     */
    private MmgFont fontCustomLg;    
    
    /**
     * 
     */
    private MmgFont fontCustomMd;    
    
    /**
     * 
     */
    private MmgFont fontCustomSm;    
    
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
    public ScreenTestMmgFont(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMmgFont.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMmgFont.SetGenericEventHandler");
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
        Helper.wr("ScreenTestMmgFont.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());
        
        int y = 60;
        
        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Font  >");
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
     * 
     * 
     * @param dir
     * @return 
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
        Helper.wr("ScreenTestMmgFont.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * 
     * 
     * @param e 
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        Helper.wr("ScreenTestMmgFont.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());        
    }
}

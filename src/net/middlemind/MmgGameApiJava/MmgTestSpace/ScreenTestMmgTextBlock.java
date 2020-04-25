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
import net.middlemind.MmgGameApiJava.MmgBase.MmgSound;
import net.middlemind.MmgGameApiJava.MmgBase.MmgTextBlock;
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
public class ScreenTestMmgTextBlock extends MmgGameScreen implements GenericEventHandler, MmgEventHandler {

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
    private MmgTextBlock txtBlock;
        
    /**
     * 
     */
    private MmgFont txtWords;
    
    /**
     * 
     */
    private String txt;
    
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
    public ScreenTestMmgTextBlock(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("ScreenTestMmgTextBlock.Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler       A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenTestMmgTextBlock.SetGenericEventHandler");
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
        Helper.wr("ScreenTestMmgTextBlock.LoadResources");
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());
        
        title = MmgFontData.CreateDefaultBoldMmgFontLg();
        title.SetText("<  Screen Test Mmg Text Block (19)  >");
        MmgHelper.CenterHorAndTop(title);
        title.SetY(title.GetY() + 30);
        AddObj(title);
               
        txt = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce nec sapien eget velit hendrerit ultrices ut ac tortor. Sed a elit libero. Fusce venenatis dapibus auctor. Nullam lacinia consectetur erat id rhoncus. Nullam consequat scelerisque tincidunt. Phasellus et dolor justo. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Maecenas hendrerit ante eros, et dapibus augue eleifend ut. Integer et dapibus metus. Donec ac mi blandit elit tristique mollis sit amet vel lorem. Cras vehicula eros vel arcu dapibus tempor. In at lectus porta, mattis nisi vitae, tristique urna. Nunc eget vestibulum odio, nec convallis enim. Cras viverra turpis ut tempor feugiat. Mauris ut mauris et felis vehicula facilisis vitae quis nisl. Nam vulputate semper enim, ut iaculis nulla elementum at. Integer mattis pulvinar nunc vestibulum placerat. Nulla semper id nulla non condimentum. In efficitur dignissim libero laoreet aliquam. Sed eu metus urna. Sed semper quam quis ultrices pharetra. Aenean ante neque, pulvinar eget facilisis sit amet, tempor quis dolor. Vivamus eleifend purus vitae urna imperdiet commodo. Etiam non commodo neque. Sed iaculis luctus mollis. Maecenas id purus mollis, hendrerit diam in, sagittis erat. Nullam eu malesuada sem. Etiam dolor orci, maximus id rutrum at, cursus nec lectus. Vivamus ac eleifend nulla. Donec efficitur, quam at pretium aliquet, velit justo iaculis tortor, eget rutrum libero sem non velit. Mauris est tellus, pharetra nec tempus vitae, eleifend non neque. Nulla convallis neque nibh, eu luctus metus congue in. Pellentesque non sem a leo consequat luctus. Nullam volutpat urna sed magna imperdiet aliquam. Fusce fringilla, felis a sodales posuere, urna dui pretium enim, in rutrum neque massa id eros. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Proin rhoncus interdum nisl, in vulputate nulla gravida vel. Integer id interdum nisi. Nullam a interdum dolor, convallis molestie odio. Mauris lorem metus, pulvinar ac justo non, accumsan lobortis lorem. Donec commodo purus eu nunc varius porttitor.";
        
        MmgTextBlock.SHOW_CONTROL_BGROUND_STORY_BOUNDING_BOX = true;        
        txtBlock = new MmgTextBlock();
        txtBlock.SetLineHeight(MmgFontData.GetTargetPixelHeightScaled() + MmgHelper.ScaleValue(5));
        txtBlock.SetHeight(MmgHelper.ScaleValue(300));
        txtBlock.SetWidth(MmgHelper.ScaleValue(400));
        txtBlock.SetPaddingX(MmgHelper.ScaleValue(txtBlock.GetPaddingX()));
        txtBlock.SetPaddingY(MmgHelper.ScaleValue(txtBlock.GetPaddingY()));
        txtBlock.PrepLinesInBox(txtBlock.GetLinesInBox());
        txtBlock.PrepTextSplit(txt, MmgFontData.GetFontNorm(), MmgFontData.GetFontSize(), MmgHelper.ScaleValue(375));
        txtBlock.SetColor(MmgColor.GetWhite());
        
        MmgHelper.CenterHorAndVert(txtBlock);
        txtBlock.PrepPage(0);
        
        //Must be done after the text split to set the position of each line of text.
        txtBlock.SetPosition(txtBlock.GetPosition());
        AddObj(txtBlock);
                
        Helper.wr("Words: " + txtBlock.GetWordCount());
        Helper.wr("Lines: " + txtBlock.GetLineCount());
        Helper.wr("Pages: " + txtBlock.GetPageCount());        
        
        txtWords = MmgFontData.CreateDefaultBoldMmgFontSm();
        txtWords.SetText("Words: " + txtBlock.GetWordCount() + "  Lines: " + txtBlock.GetLineCount() + "  Pages: " + txtBlock.GetPageCount());
        MmgHelper.CenterHor(txtWords);
        txtWords.SetY(GetY() + GetHeight() - 30);
        AddObj(txtWords);
        
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessScreenPress");
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessScreenRelease");
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessAClick");
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessBClick");        
        return true;
    }
    
    /**
     * 
     */
    @Override
    public void ProcessDebugClick() {
        Helper.wr("ScreenTestMmgTextBlock.ProcessDebugClick");
    }

    /**
     * 
     * 
     * @param dir
     * @return 
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        Helper.wr("ScreenTestMmgTextBlock.ProcessDpadPress: " + dir);
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessDpadRelease: " + dir);
        if(dir == GameSettings.RIGHT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_01);
        
        } else if(dir == GameSettings.LEFT_KEYBOARD) {
            owner.SwitchGameState(GameStates.GAME_SCREEN_18);
            
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessDpadClick: " + dir);        
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessScreenClick");        
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessScreenClick");
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
        Helper.wr("ScreenTestMmgTextBlock.ProcessKeyClick");
        return true;
    }
    
    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);

        title = null;
        txtBlock = null;
        txt = null;
        txtWords = null;
        
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
        Helper.wr("ScreenTestMmgTextBlock.HandleGenericEvent: Id: " + obj.id + " GameState: " + obj.gameState);
    }

    /**
     * 
     * 
     * @param e 
     */
    @Override
    public void MmgHandleEvent(MmgEvent e) {
        Helper.wr("ScreenTestMmgTextBlock.HandleMmgEvent: Msg: " + e.GetMessage() + " Id: " + e.GetEventId());
    }
}

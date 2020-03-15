package com.middlemind.Odroid;

import com.middlemind.Odroid.GamePanel.GameStates;
import java.util.Hashtable;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmpScaler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGameScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgMenuContainer;
import net.middlemind.MmgGameApiJava.MmgBase.MmgMenuItem;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;

/**
 * A game screen object, ScreenMainMenu, that extends the MmgGameScreen base
 * class. This game screen is for displaying a main menu screen. Created on
 * August 1, 2015, 10:57 PM by Middlemind Games. Created by Middlemind Games.
 *
 * @author Victor G. Brusca
 */
public class Screen extends MmgGameScreen implements GenericEventHandler {

    /**
     * The game state this screen has.
     */
    private GameStates state;
    
    /**
     * The GamePanel that owns this game screen. Usually a JPanel instance that
     * holds a reference to this game screen object.
     */
    private GamePanel owner;
    private boolean dirty;
    private boolean lret;
    
    private Hashtable<String, Double> classConfig;
    
    /**
     * Constructor, sets the game state associated with this screen, and sets
     * the owner GamePanel instance.
     *
     * @param State The game state of this game screen.
     * @param Owner The owner of this game screen.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public Screen(GameStates State, GamePanel Owner) {
        super();
        dirty = false;
        pause = false;
        ready = false;
        state = State;
        owner = Owner;
    }
    
    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        
    }
    
    /**
     * Loads all the resources needed to display this game screen.
     */
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());

        Helper.wr("ScreenMainMenu: LoadResources: Position: " + GetPosition().ToString());
        Helper.wr("ScreenMainMenu: LoadResources: Width: " + GetWidth());
        Helper.wr("ScreenMainMenu: LoadResources: Height: " + GetHeight());

        classConfig = MmgHelper.LoadClassConfigFile(GameSettings.CLASS_CONFIG_DIR + "screen_main_menu.txt");
        
        MmgBmp tB = null;
        MmgPen p;
        String key = "";
        double scale = 1.0;
        String imgId = "";
        MmgBmp lval = null;
        
        p = new MmgPen();
        p.SetCacheOn(false);
        
        tB =  MmgHelper.CreateFilledBmp(w, h, MmgColor.GetBlack());
        if (tB != null) {
            SetCenteredBackground(tB);
        }

        dirty = true;
        ready = true;
        pause = false;
    }

    @Override
    public boolean ProcessAClick() {
        return true;
    }
    
    @Override
    public boolean ProcessDpadRelease(int dir) {
        return true;
    }

    /**
     * Forces this screen to prepare itself for display. This is the method that
     * handles displaying different game screen text. Calling draw screen
     * prepares the screen for display.
     */
    public void DrawScreen() {
        pause = true;
        dirty = false;


        pause = false;
    }

    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        dirty = false;
        pause = true;
        SetIsVisible(false);
        SetBackground(null);
        SetMenu(null);
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

    public boolean IsDirty() {
        return dirty;
    }

    public void SetDirty(boolean b) {
        dirty = b;
    }

    public void MakeDirty() {
        dirty = true;
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
        } else {
            //do nothing
        }
    }

    @Override
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        lret = false;

        if (pause == false && GetIsVisible() == true) {
            if (super.MmgUpdate(updateTick, currentTimeMs, msSinceLastFrame) == true) {
                lret = true;
            }

            if (dirty == true) {
                lret = true;
                DrawScreen();
            }

        } else {
            //do nothing
        }

        return lret;
    }
}
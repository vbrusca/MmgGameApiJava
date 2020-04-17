package net.middlemind.MmgGameApiJava.MmgCore;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import java.util.Hashtable;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgCfgFileEntry;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGameScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;

/**
 * A game screen object, Screen, that extends the MmgGameScreen base
 * class. This game screen is for displaying a main menu screen. Created on
 * August 1, 2015, 10:57 PM by Middlemind Games. Created by Middlemind Games.
 *
 * @author Victor G. Brusca
 * 03/15/2020
 */
public class Screen extends MmgGameScreen implements GenericEventHandler {

    /**
     * The game state this screen has.
     */
    public GameStates state;
    
    /**
     * The GamePanel that owns this game screen. Usually a JPanel instance that
     * holds a reference to this game screen object.
     */
    public GamePanel owner;
    
    /**
     * 
     */
    public boolean isDirty;
    
    /**
     * 
     */
    private boolean lret;
    
    /**
     * 
     */
    public Hashtable<String, MmgCfgFileEntry> classConfig;
    
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
        isDirty = false;
        pause = false;
        ready = false;
        state = State;
        owner = Owner;
    }
    
    /**
     * 
     * @param obj 
     */
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
        
        MmgBmp tB = null;
        MmgPen p;
        
        p = new MmgPen();
        p.SetCacheOn(false);
        
        tB =  MmgHelper.CreateFilledBmp(w, h, MmgColor.GetBlack());
        if (tB != null) {
            SetCenteredBackground(tB);
        }

        isDirty = true;
        ready = true;
        pause = false;
    }

    /**
     * 
     * 
     * @param src
     * @return 
     */
    @Override
    public boolean ProcessAClick(int src) {
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
        return true;
    }

    /**
     * Forces this screen to prepare itself for display. This is the method that
     * handles displaying different game screen text. Calling draw screen
     * prepares the screen for display.
     */
    public void DrawScreen() {
        pause = true;
        isDirty = false;


        pause = false;
    }

    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        isDirty = false;
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

    /**
     * 
     * 
     * @return 
     */
    public boolean GetIsDirty() {
        return isDirty;
    }

    /**
     * 
     * 
     * @param b 
     */
    public void SetIsDirty(boolean b) {
        isDirty = b;
    }

    /**
     * The main drawing routine.
     *
     * @param p An MmgPen object to use for drawing this game screen.
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
            if (super.MmgUpdate(updateTick, currentTimeMs, msSinceLastFrame) == true) {
                lret = true;
            }

            if (isDirty == true) {
                lret = true;
                DrawScreen();
            }
        }

        return lret;
    }
}
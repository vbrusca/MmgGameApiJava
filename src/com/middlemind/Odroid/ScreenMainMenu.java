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
import net.middlemind.MmgGameApiJava.MmgBase.MmgSound;

/**
 * A game screen object, ScreenMainMenu, that extends the MmgGameScreen base
 * class. This game screen is for displaying a main menu screen. Created on
 * August 1, 2015, 10:57 PM by Middlemind Games. Created by Middlemind Games.
 *
 * @author Victor G. Brusca
 */
public class ScreenMainMenu extends MmgGameScreen {

    /**
     * The game state this screen has.
     */
    private GameStates state;

    /**
     * Helper class for screen UI.
     */
    private MmgBmp menuStartGame;

    /**
     * Helper class for screen UI.
     */
    private MmgBmp menuExitGame;    
    
    /**
     * Helper class for screen UI.
     */
    private MmgBmp menuTitle;

    /**
     * Helper class for screen UI.
     */
    private MmgBmp menuSubTitle;    
    
    /**
     * Helper class for screen UI.
     */
    private MmgBmp menuFooterUrl;

    private MmgBmp menuCursor;

    /**
     * Helper class for storing game screen menu options.
     */
    private MmgMenuContainer menu;

    private MmgSound menuSound;
    
    /**
     * Helper class for handling game screen events.
     */
    private HandleMainMenuEvent handleMenuEvent = null;

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
    public ScreenMainMenu(GameStates State, GamePanel Owner) {
        super();
        dirty = false;
        pause = false;
        ready = false;
        state = State;
        owner = Owner;
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
        String sndId = "";        
        MmgBmp lval = null;
        MmgSound sval = null;
        
        p = new MmgPen();
        p.SetCacheOn(false);
        handleMenuEvent = new HandleMainMenuEvent(this, owner);
        
        sndId = "jump1.wav";
        sval = MmgHelper.GetBasicCachedSound(sndId);
        sval.Play();
        menuSound = sval;
        
        tB =  MmgHelper.CreateFilledBmp(w, h, MmgColor.GetBlack());
        if (tB != null) {
            SetCenteredBackground(tB);
        }

        Helper.wr("Background Image Position: " + GetBackground().GetPosition().ToString());

        imgId = "game_title.png";
        lval = MmgHelper.GetBasicCachedBmp(imgId);
        menuTitle = lval;
        if (menuTitle != null) {
            key = "menuTitleScale";
            if(classConfig.containsKey(key)) {
                scale = classConfig.get(key).doubleValue();
                if(scale != 1.0) {
                    menuTitle = MmgBmpScaler.ScaleMmgBmp(menuTitle, scale, false);
                }
            }
            
            MmgHelper.CenterHor(menuTitle);
            menuTitle.GetPosition().SetY(GetPosition().GetY() + MmgHelper.ScaleValue(50));
                
            key = "menuTitlePosY";
            if(classConfig.containsKey(key)) {
                menuTitle.GetPosition().SetY(GetPosition().GetY() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }
            
            key = "menuTitlePosX";
            if(classConfig.containsKey(key)) {
                menuTitle.GetPosition().SetX(GetPosition().GetX() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }
            
            key = "menuTitleOffsetY";
            if(classConfig.containsKey(key)) {
                menuTitle.GetPosition().SetY(menuTitle.GetY() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }
            
            key = "menuTitleOffsetX";
            if(classConfig.containsKey(key)) {
                menuTitle.GetPosition().SetX(menuTitle.GetX() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }            
            
            AddObj(menuTitle);
        }

        imgId = "game_sub_title.png";
        lval = MmgHelper.GetBasicCachedBmp(imgId);
        menuSubTitle = lval;
        if (menuSubTitle != null) {
            key = "menuSubTitleScale";
            if(classConfig.containsKey(key)) {
                scale = classConfig.get(key).doubleValue();
                if(scale != 1.0) {
                    menuSubTitle = MmgBmpScaler.ScaleMmgBmp(menuTitle, scale, false);
                }
            }
            
            MmgHelper.CenterHor(menuSubTitle);
            menuSubTitle.GetPosition().SetY(menuTitle.GetY() + menuTitle.GetHeight() + MmgHelper.ScaleValue(50));            
            
            key = "menuSubTitlePosY";
            if(classConfig.containsKey(key)) {
                menuSubTitle.GetPosition().SetY(GetPosition().GetY() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }
            
            key = "menuSubTitlePosX";
            if(classConfig.containsKey(key)) {
                menuSubTitle.GetPosition().SetX(GetPosition().GetX() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }            
            
            key = "menuSubTitleOffsetY";
            if(classConfig.containsKey(key)) {
                menuSubTitle.GetPosition().SetY(menuSubTitle.GetY() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }
            
            key = "menuSubTitleOffsetX";
            if(classConfig.containsKey(key)) {
                menuSubTitle.GetPosition().SetX(menuSubTitle.GetX() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }            
            
            AddObj(menuSubTitle);
        }        
        
        imgId = "start_game.png";
        lval = MmgHelper.GetBasicCachedBmp(imgId);
        menuStartGame = lval;
        if (menuStartGame != null) {
            key = "menuStartGameScale";
            if(classConfig.containsKey(key)) {
                scale = classConfig.get(key).doubleValue();
                if(scale != 1.0) {
                    menuStartGame = MmgBmpScaler.ScaleMmgBmp(menuStartGame, scale, false);
                }
            }
            
            MmgHelper.CenterHor(menuStartGame);
            menuStartGame.GetPosition().SetY(menuSubTitle.GetY() + menuSubTitle.GetHeight() + MmgHelper.ScaleValue(10));            
            
            key = "menuStartGamePosY";
            if(classConfig.containsKey(key)) {
                menuStartGame.GetPosition().SetY(GetPosition().GetY() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }
            
            key = "menuStartGamePosX";
            if(classConfig.containsKey(key)) {
                menuStartGame.GetPosition().SetX(GetPosition().GetX() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }  
            
            key = "menuStartGameOffsetY";
            if(classConfig.containsKey(key)) {
                menuStartGame.GetPosition().SetY(menuStartGame.GetY() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }
            
            key = "menuStartGameOffsetX";
            if(classConfig.containsKey(key)) {
                menuStartGame.GetPosition().SetX(menuStartGame.GetX() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }         
        }
        
        imgId = "exit_game.png";
        lval = MmgHelper.GetBasicCachedBmp(imgId);
        menuExitGame = lval;
        if (menuExitGame != null) {
            key = "menuExitGameScale";
            if(classConfig.containsKey(key)) {
                scale = classConfig.get(key).doubleValue();
                if(scale != 1.0) {
                    menuStartGame = MmgBmpScaler.ScaleMmgBmp(menuExitGame, scale, false);
                }
            }
            
            MmgHelper.CenterHor(menuExitGame);
            menuExitGame.GetPosition().SetY(menuStartGame.GetY() + menuStartGame.GetHeight() + MmgHelper.ScaleValue(10));
                
            key = "menuExitGamePosY";
            if(classConfig.containsKey(key)) {        
                menuExitGame.GetPosition().SetY(GetPosition().GetY() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));                
            }
            
            key = "menuExitGamePosX";
            if(classConfig.containsKey(key)) {
                menuExitGame.GetPosition().SetX(GetPosition().GetX() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }
            
            key = "menuExitGameOffsetY";
            if(classConfig.containsKey(key)) {        
                menuExitGame.GetPosition().SetY(menuExitGame.GetY() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));                
            }
            
            key = "menuExitGameOffsetX";
            if(classConfig.containsKey(key)) {
                menuExitGame.GetPosition().SetX(menuExitGame.GetX() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }            
        }        
        
        imgId = "footer_url.png";
        lval = MmgHelper.GetBasicCachedBmp(imgId);
        menuFooterUrl = lval;
        if (menuFooterUrl != null) {
            key = "menuFooterUrlScale";
            if(classConfig.containsKey(key)) {
                scale = classConfig.get(key).doubleValue();
                if(scale != 1.0) {
                    menuFooterUrl = MmgBmpScaler.ScaleMmgBmp(menuFooterUrl, scale, false);
                }
            }
            
            MmgHelper.CenterHor(menuFooterUrl);
            menuFooterUrl.GetPosition().SetY(menuExitGame.GetY() + menuExitGame.GetHeight() + MmgHelper.ScaleValue(10));
                
            key = "menuFooterUrlPosY";
            if(classConfig.containsKey(key)) {        
                menuFooterUrl.GetPosition().SetY(GetPosition().GetY() + menuExitGame.GetHeight() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));                
            }
            
            key = "menuFooterUrlPosX";
            if(classConfig.containsKey(key)) {
                menuFooterUrl.GetPosition().SetX(GetPosition().GetX() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }            
            
            key = "menuFooterUrlOffsetY";
            if(classConfig.containsKey(key)) {        
                menuFooterUrl.GetPosition().SetY(menuFooterUrl.GetY() + menuExitGame.GetHeight() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));                
            }
            
            key = "menuFooterUrlOffsetX";
            if(classConfig.containsKey(key)) {
                menuFooterUrl.GetPosition().SetX(menuFooterUrl.GetX() + MmgHelper.ScaleValue(classConfig.get(key).intValue()));
            }            
            
            AddObj(menuFooterUrl);
        }          
        
        imgId = "cursor_hand_sm_right.png";
        lval = MmgHelper.GetBasicCachedBmp(imgId);
        menuCursor = lval;
        SetLeftCursor(menuCursor);

        dirty = true;
        ready = true;
        pause = false;
    }

    @Override
    public boolean ProcessAClick() {
        int idx = GetMenuIdx();
        MmgMenuItem mmi;
        mmi = (MmgMenuItem) menu.GetContainer().get(idx);
        
        if (mmi != null) {
            ProcessMenuItemSel(mmi);
            return true;
        }
        
        return true;
    }
    
    @Override
    public boolean ProcessDpadRelease(int dir) {
        if (dir == GameSettings.LEFT || dir == GameSettings.RIGHT) {
            return false;
        }

        if (dir == GameSettings.UP) {            
            MoveMenuSelUp();
        } else if (dir == GameSettings.DOWN) {
            MoveMenuSelDown();
        }

        return true;
    }

    /**
     * Forces this screen to prepare itself for display. This is the method that
     * handles displaying different game screen text. Calling draw screen
     * prepares the screen for display.
     */
    public void DrawScreen() {
        //int mainY;
        pause = true;
        menu = new MmgMenuContainer();
        menu.SetMmgColor(null);
        dirty = false;

        MmgMenuItem mItm = null;
        
        if (menuStartGame != null) {
            mItm = Helper.GetBasicMenuItem(handleMenuEvent, "Main Menu Start Game", HandleMainMenuEvent.MAIN_MENU_EVENT_START_GAME, HandleMainMenuEvent.MAIN_MENU_EVENT_TYPE, menuStartGame);
            mItm.SetSound(menuSound);
            menu.Add(mItm);
        }

        if (menuExitGame != null) {
            mItm = Helper.GetBasicMenuItem(handleMenuEvent, "Main Menu Exit Game", HandleMainMenuEvent.MAIN_MENU_EVENT_EXIT_GAME, HandleMainMenuEvent.MAIN_MENU_EVENT_TYPE, menuExitGame);
            mItm.SetSound(menuSound);
            menu.Add(mItm);
        }
        
        SetMenuStart(0);
        SetMenuStop(menu.GetCount() - 1);
        
        SetMenu(menu);
        SetMenuOn(true);
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

        menuStartGame = null;
        menuExitGame = null;
        menuTitle = null;
        menuFooterUrl = null;
        menuCursor = null;
        menuSound = null;
        
        handleMenuEvent = null;
        menu = null;
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
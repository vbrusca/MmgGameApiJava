package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.DatExternalStrings;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import net.middlemind.MmgGameApiJava.MmgCore.MainFrame;
import net.middlemind.MmgGameApiJava.MmgCore.ScreenLoading;
import net.middlemind.MmgGameApiJava.MmgCore.ScreenSplash;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 02/19/2020
 */
public class GamePanel extends net.middlemind.MmgGameApiJava.MmgCore.GamePanel {
        
    protected ScreenTest screenTest;
    
    public GamePanel(MainFrame Mf, int WinWidth, int WinHeight, int X, int Y, int GameWidth, int GameHeight) {
        super(Mf, WinWidth, WinHeight, X, Y, GameWidth, GameHeight);
        Helper.wr("TestSpace.GamePanel.Constructor");
        screenSplash.SetGenericEventHandler(this);
        screenLoading.SetGenericEventHandler(this);
        screenLoading.SetSlowDown(500);
        
        screenTest = new ScreenTest(GameStates.GAME_SCREEN_01, this);
        screenTest.Pause();
        screenTest.SetIsVisible(false);
    }
        
    @Override
    public void SwitchGameState(GameStates g) {
        Helper.wr("TestSpace.Switching Game State To: " + g);

        if (gameState != prevGameState) {
            prevGameState = gameState;
        }

        if (g != gameState) {
            gameState = g;
        } else {
            return;
        }

        //unload
        if (prevGameState == GameStates.BLANK) {
            Helper.wr("Hiding BLANK screen.");

        } else if (prevGameState == GameStates.SPLASH) {
            Helper.wr("Hiding SPLASH screen.");
            screenSplash.Pause();
            screenSplash.SetIsVisible(false);
            screenSplash.UnloadResources();
            
        } else if (prevGameState == GameStates.LOADING) {
            Helper.wr("Hiding LOADING screen.");
            screenLoading.Pause();
            screenLoading.SetIsVisible(false);
            screenLoading.UnloadResources();
            Helper.wr("Hiding LOADING screen DONE.");

        } else if (prevGameState == GameStates.GAME_SCREEN_01) {
            Helper.wr("Hiding GAME_SCREEN_01 screen.");
            screenTest.Pause();
            screenTest.SetIsVisible(false);
            screenTest.UnloadResources();
                        
        } else if (prevGameState == GameStates.MAIN_MENU) {
            Helper.wr("Hiding MAIN_MENU screen.");
            //mainMenuScreen.Pause();
            //mainMenuScreen.SetIsVisible(false);
            //mainMenuScreen.UnloadResources();

        } else if (prevGameState == GameStates.ABOUT) {
            Helper.wr("Hiding ABOUT screen.");
            //aboutScreen.Pause();
            //aboutScreen.SetIsVisible(false);
            //aboutScreen.UnloadResources();

        } else if (prevGameState == GameStates.HELP_MENU) {
            Helper.wr("Hiding HELP screen.");
            //helpScreen.Pause();
            //helpScreen.SetIsVisible(false);
            //helpScreen.UnloadResources();

        } else if (prevGameState == GameStates.MAIN_GAME) {
            Helper.wr("Hiding MAIN GAME screen.");
            //mainGameScreen.Pause();
            //mainGameScreen.SetIsVisible(false);
            //mainGameScreen.UnloadResources();

        } else if (prevGameState == GameStates.SETTINGS) {
            Helper.wr("Hiding SETTINGS screen.");
            //settingsScreen.Pause();
            //settingsScreen.SetIsVisible(false);
            //settingsScreen.UnloadResources();

        }

        //load
        Helper.wr("Switching Game State To: " + gameState);
        if (gameState == GameStates.BLANK) {
            Helper.wr("Showing BLANK screen.");

        } else if (gameState == GameStates.SPLASH) {
            Helper.wr("Showing SPLASH screen.");
            screenSplash.LoadResources();
            screenSplash.UnPause();
            screenSplash.SetIsVisible(true);
            screenSplash.StartDisplay();
            currentScreen = screenSplash;
                        
        } else if (gameState == GameStates.LOADING) {
            Helper.wr("Showing LOADING screen.");
            screenLoading.LoadResources();
            screenLoading.UnPause();
            screenLoading.SetIsVisible(true);
            screenLoading.StartDatLoad();
            currentScreen = screenLoading;

        } else if (gameState == GameStates.GAME_SCREEN_01) {
            Helper.wr("Showing GAME_SCREEN_01 screen.");
            screenTest.LoadResources();
            screenTest.UnPause();
            screenTest.SetIsVisible(true);
            currentScreen = screenTest;
                        
        } else if (gameState == GameStates.MAIN_MENU) {
            Helper.wr("Showing MAIN_MENU screen.");
            //mainMenuScreen.LoadResources();
            //mainMenuScreen.UnPause();
            //mainMenuScreen.SetIsVisible(true);
            //currentScreen = mainMenuScreen;

        } else if (gameState == GameStates.ABOUT) {
            Helper.wr("Showing ABOUT screen.");
            //aboutScreen.LoadResources();
            //aboutScreen.UnPause();
            //aboutScreen.SetIsVisible(true);
            //currentScreen = aboutScreen;

        } else if (gameState == GameStates.HELP_MENU) {
            Helper.wr("Showing HELP screen.");
            //helpScreen.LoadResources();
            //helpScreen.UnPause();
            //helpScreen.SetIsVisible(true);
            //currentScreen = helpScreen;

        } else if (gameState == GameStates.MAIN_GAME) {
            Helper.wr("Showing MAIN GAME screen.");
            //mainGameScreen.LoadResources();
            //mainGameScreen.UnPause();
            //mainGameScreen.SetIsVisible(true);
            //currentScreen = mainGameScreen;

        } else if (gameState == GameStates.SETTINGS) {
            Helper.wr("Showing SETTINGS screen.");
            //settingsScreen.LoadResources();
            //settingsScreen.UnPause();
            //settingsScreen.SetIsVisible(true);
            //currentScreen = settingsScreen;

        }
    }    
    
    @Override
    public void HandleGenericEvent(MmgGenericEventMessage obj) {
        if (obj != null) {
            Helper.wr("TestSpace.HandleGenericEvent " + obj.GetGameState());
            if (obj.GetGameState() == GameStates.LOADING) {
                if (obj.GetId() == ScreenLoading.EVENT_LOAD_COMPLETE) {
                    //Final loading steps
                    DatExternalStrings.LOAD_EXT_STRINGS();                    
                    SwitchGameState(GameStates.GAME_SCREEN_01);
                }
                
            } else if (obj.GetGameState() == GameStates.SPLASH) {
                if (obj.GetId() == ScreenSplash.EVENT_DISPLAY_COMPLETE) {
                    SwitchGameState(GameStates.LOADING);
                }
                
            }
        }
    }    
}

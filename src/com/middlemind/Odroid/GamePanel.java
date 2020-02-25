package com.middlemind.Odroid;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmpScaler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFontData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGameScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;

/**
 * The JPanel used to render the game to. This is the connection point between
 * native UI rendering and the game rendering. Created on August 1, 2015, 10:57
 * PM by Middlemind Games
 *
 * @author Victor G. Brusca
 */
@SuppressWarnings("UseOfObsoleteCollectionType")
public class GamePanel implements GenericEventHandler, GamePadSimple {

    /**
     * An enumeration that lists all of the current game states.
     */
    public enum GameStates {
        LOADING,
        BLANK,
        SPLASH,
        MAIN_MENU,
        ABOUT,
        HELP_MENU,
        HELP_PROLOGUE,
        HELP_ITEM_DESC,
        HELP_ENEMY_DESC,
        HELP_ITEM_DESC_ITEM_TEXT,
        HELP_GAME_PLAY_OVERWORLD,
        HELP_GAME_PLAY_BATTLE_MODE,
        HELP_CHAR_DESC,
        HELP_QUEST_DESC,
        HELP_ROOM_DESC,
        HELP_ROOM_DESC_ROOM_DETAILS,
        MAIN_GAME,
        SETTINGS,
        GAME_SCREEN_01,
        GAME_SCREEN_02,
        GAME_SCREEN_03,
        GAME_SCREEN_04,
        GAME_SCREEN_05,
        GAME_SCREEN_06,
        GAME_SCREEN_07,
        GAME_SCREEN_08,
        GAME_SCREEN_09,
        GAME_SCREEN_10,
        GAME_SCREEN_11,
        GAME_SCREEN_12,
        GAME_SCREEN_13,
        GAME_SCREEN_14,
        GAME_SCREEN_15,
        GAME_SCREEN_16,
        GAME_SCREEN_17,
        GAME_SCREEN_18,
        GAME_SCREEN_19,
        GAME_SCREEN_20,
        GAME_SCREEN_21,
        GAME_SCREEN_22,
        GAME_SCREEN_23,
        GAME_SCREEN_24,
        GAME_SCREEN_25,
        GAME_SCREEN_26,
        GAME_SCREEN_27,
        GAME_SCREEN_28,
        GAME_SCREEN_29,
        GAME_SCREEN_30        
    }

    /**
     * MainFrame that this panel is hosted in.
     */
    protected final MainFrame mf;

    /**
     * Window width.
     */
    protected final int winWidth;

    /**
     * Window height.
     */
    protected final int winHeight;

    /**
     * The X coordinate of this panel.
     */
    protected final int myX;

    /**
     * The Y coordinate of this panel.
     */
    protected final int myY;

    /**
     * Window width.
     */
    protected final int sWinWidth;

    /**
     * Window height.
     */
    protected final int sWinHeight;

    /**
     * The X coordinate of this panel.
     */
    protected final int sMyX;

    /**
     * The Y coordinate of this panel.
     */
    protected final int sMyY;

    /**
     * Default target game width.
     */
    public static final int GAME_WIDTH = 854;

    /**
     * Default target game height.
     */
    public static final int GAME_HEIGHT = 416;

    /**
     * Pause the game.
     */
    public static boolean PAUSE = false;

    /**
     * Exit the game.
     */
    public static boolean EXIT = false;

    /**
     * Paint helper class, used in the paint drawing routine.
     */
    protected Graphics2D g2d;

    /**
     * Holds a reference to all game screens.
     */
    protected Hashtable<GameStates, MmgGameScreen> gameScreens;

    /**
     * The current game screen being displayed.
     */
    protected MmgGameScreen currentScreen;

    /**
     * Paint helper class, used to draw Mmg API objects.
     */
    protected final MmgPen p;

    /**
     * Game state helper class, the previous game state.
     */
    protected GameStates prevGameState;

    /**
     * Game state helper class, the current game state.
     */
    protected GameStates gameState;

    public static Hashtable<String, Object> VARS = new Hashtable();

    public static String FPS = "Drawing FPS: 0000 Actual FPS: 00";
    public static String VAR1 = "** EMPTY **";
    public static String VAR2 = "** EMPTY **";

    protected Canvas canvas;
    protected BufferStrategy strategy;
    protected BufferedImage background;
    protected Graphics2D backgroundGraphics;
    protected Graphics2D graphics;
    protected final double scale = 1.0;
    protected int updateTick = 0;
    protected long now;
    protected long prev;
    protected final Font debugFont;
    protected Font tmpF;
    public static GameType GAME_TYPE = GameType.NEW_GAME;

    public enum GameType {
        NEW_GAME,
        CONTINUED_GAME
    }

    public int lastX;
    public int lastY;
    public long lastKeyPressEvent = -1;
    protected Graphics2D bg;
    protected Graphics2D g;
    
    public ScreenSplash screenSplash;
    public ScreenLoading screenLoading;

    /**
     * Constructor, sets the MainFrame, window dimensions, and position of this
     * JPanel.
     *
     * @param Mf The MainFrame class this panel belongs to.
     * @param WinWidth The target window width.
     * @param WinHeight The target window height.
     * @param X The X coordinate of this JPanel.
     * @param Y The Y coordinate of this JPanel.
     */
    @SuppressWarnings({"LeakingThisInConstructor", "OverridableMethodCallInConstructor"})
    public GamePanel(MainFrame Mf, int WinWidth, int WinHeight, int X, int Y) {
        mf = Mf;
        winWidth = WinWidth;
        winHeight = WinHeight;
        sWinWidth = (int) (winWidth * scale);
        sWinHeight = (int) (winHeight * scale);

        myX = X;
        myY = Y;
        sMyX = myX + (winWidth - sWinWidth);
        sMyY = myY + (winHeight - sWinHeight);

        now = System.currentTimeMillis();
        prev = System.currentTimeMillis();

        canvas = new Canvas(MmgBmpScaler.GRAPHICS_CONFIG);
        canvas.setSize(winWidth, winHeight);

        Helper.wr("");        
        Helper.wr("GamePanel Window Width: " + winWidth);
        Helper.wr("GamePanel Window Height: " + winHeight);
        Helper.wr("GamePanel Offset X: " + myX);
        Helper.wr("GamePanel Offset Y: " + myY);

        MmgScreenData screenData = new MmgScreenData(winWidth, winHeight, GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);
        Helper.wr("");
        Helper.wr("--- MmgScreenData ---");
        Helper.wr(MmgScreenData.ToString());

        MmgFontData fontData = new MmgFontData();
        Helper.wr("");
        Helper.wr("--- MmgFontData ---");
        Helper.wr(MmgFontData.ToString());
        debugFont = MmgFontData.CreateDefaultFontSm();

        p = new MmgPen();
        MmgPen.ADV_RENDER_HINTS = true;
        
        screenSplash = new ScreenSplash(GameStates.SPLASH, this);
        screenLoading = new ScreenLoading(GameStates.LOADING, this);
        
        gameScreens = new Hashtable();
        gameState = GameStates.BLANK;
        SwitchGameState(GameStates.SPLASH);

        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }
        });

        canvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ' || e.getKeyChar() == '\n') {
                    ProcessAClick();
                    
                } else if (e.getKeyChar() == '+') {
                    //increase speed

                } else if (e.getKeyChar() == '-') {
                    //decrease speed

                } else if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
                    if (gameState == GameStates.MAIN_GAME) {
                        //pause game
                    }
                    
                } else if (e.getKeyChar() == 'f' || e.getKeyChar() == 'F') {  
                    //clear game flags
                    
                } else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
                    ProcessAClick();
                    
                } else if (e.getKeyChar() == 'b' || e.getKeyChar() == 'B') {
                    ProcessBClick();
                    
                } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
                    ProcessDebugClick();
                    
                }                    
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //Ignore Enter and Space bar presses, handle them as A and B button clicks.
                if (e.getKeyCode() != 32 && e.getKeyCode() != 10) {
                    lastKeyPressEvent = System.currentTimeMillis();
                    if (e.getKeyCode() == 40) {
                        ProcessDpadPress(GameSettings.DOWN);
                        
                    } else if (e.getKeyCode() == 38) {
                        ProcessDpadPress(GameSettings.UP);
                        
                    } else if (e.getKeyCode() == 37) {
                        ProcessDpadPress(GameSettings.LEFT);
                        
                    } else if (e.getKeyCode() == 39) {
                        ProcessDpadPress(GameSettings.RIGHT);
                        
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Ignore Enter and Space bar releases, handle them as A and B button clicks.                
                if (e.getKeyCode() != 32 && e.getKeyCode() != 10) {
                    if (e.getKeyCode() == 40) {
                        ProcessDpadRelease(GameSettings.DOWN);
                        
                    } else if (e.getKeyCode() == 38) {
                        ProcessDpadRelease(GameSettings.UP);
                        
                    } else if (e.getKeyCode() == 37) {
                        ProcessDpadRelease(GameSettings.LEFT);
                        
                    } else if (e.getKeyCode() == 39) {
                        ProcessDpadRelease(GameSettings.RIGHT);
                        
                    }
                }
            }
        });

        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ProcessClick(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ProcessPress(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ProcessRelease(e.getX(), e.getY());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }

    @Override
    public void ProcessDpadClick(int dir) {
        currentScreen.ProcessDpadClick(dir);
    }

    @Override
    public void ProcessAPress() {

    }

    @Override
    public void ProcessARelease() {

    }

    @Override
    public void ProcessBPress() {

    }

    @Override
    public void ProcessBRelease() {

    }
    
    @Override    
    public void ProcessAClick() {
        currentScreen.ProcessAClick();
    }

    @Override    
    public void ProcessBClick() {
        currentScreen.ProcessBClick();
    }

    public void ProcessDebugClick() {
        currentScreen.ProcessDebugClick();
    }

    @Override    
    public void ProcessDpadPress(int dir) {
        currentScreen.ProcessDpadPress(dir);
    }

    @Override    
    public void ProcessDpadRelease(int dir) {
        currentScreen.ProcessDpadRelease(dir);
    }
    
    public void ProcessPress(int x, int y) {
        //int nx = x;
        //int ny = y;
        int anx = x - MmgScreenData.GetGameLeft() - myX;
        int any = y - MmgScreenData.GetGameTop() - myY;

        Helper.wr("GamePanel: ProcessPress Original: " + x + ", " + y);
        Helper.wr("GamePanel: ProcessPress Adjusted: " + anx + ", " + any);
        currentScreen.ProcessScreenPress(anx, any);
    }

    public void ProcessRelease(int x, int y) {
        //int nx = x;
        //int ny = y;
        int anx = x - MmgScreenData.GetGameLeft() - myX;
        int any = y - MmgScreenData.GetGameTop() - myY;

        Helper.wr("GamePanel: ProcessRelease Original: " + x + ", " + y);
        Helper.wr("GamePanel: ProcessRelease Adjusted: " + anx + ", " + any);        
        currentScreen.ProcessScreenRelease(anx, any);
    }

    public void ProcessClick(int x, int y) {
        int nx = x;
        int ny = y;
        int anx = x - MmgScreenData.GetGameLeft() - myX;
        int any = y - MmgScreenData.GetGameTop() - myY;

        //Helper.wr("Mouse Clicked Orig: X: " + x + " Y: " + y);
        //Helper.wr("Mouse Clicked Adjusted: X: " + nx + " Y: " + ny);
        //Helper.wr("GamePanel Window Width: " + winWidth);
        //Helper.wr("GamePanel Window Height: " + winHeight);
        //Helper.wr("GamePanel Offset X: " + myX);
        //Helper.wr("GamePanel Offset Y: " + myY);
        
        Helper.wr("GamePanel: ProcessClick Original: " + x + ", " + y);
        Helper.wr("GamePanel: ProcessClick Adjusted: " + anx + ", " + any);        
        currentScreen.ProcessScreenClick(nx, ny);
    }

    public void PrepBuffers() {
        // Background & Buffer
        background = create(winWidth, winHeight, false);
        canvas.createBufferStrategy(2);

        do {
            strategy = canvas.getBufferStrategy();
        } while (strategy == null);

        backgroundGraphics = (Graphics2D) background.getGraphics();
    }

    // create a hardware accelerated image
    public BufferedImage create(final int width, final int height, final boolean alpha) {
        return MmgBmpScaler.GRAPHICS_CONFIG.createCompatibleImage(width, height, alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
    }

    /**
     * Gets the set of game screens this class has references too.
     *
     * @return A Hashtable of game screens, MmgGameScreen.
     */
    public Hashtable<GameStates, MmgGameScreen> GetGameScreens() {
        return gameScreens;
    }

    /**
     * Sets the set of game screens this class has references too.
     *
     * @param GameScreens A Hashtable of game screens, MmgGameScreen.
     */
    public void SetGameScreens(Hashtable<GameStates, MmgGameScreen> GameScreens) {
        gameScreens = GameScreens;
    }

    public Canvas GetCanvas() {
        return canvas;
    }
    
    /**
     * Gets the current game screen.
     *
     * @return A game screen object, MmgGameScreen.
     */
    public MmgGameScreen GetCurrentScreen() {
        return currentScreen;
    }

    /**
     * Sets the current game screen.
     *
     * @param CurrentScreen A game screen object.
     */
    public void SetCurrentScreen(MmgGameScreen CurrentScreen) {
        currentScreen = CurrentScreen;
    }

    /**
     * Switches the current game state, cleans up the current state, then loads
     * up the next state. Currently does not use the gameScreens hash table.
     * Uses direct references instead, for now.
     *
     * @param g The game state to switch to.
     */
    public void SwitchGameState(GameStates g) {
        Helper.wr("Switching Game State To: " + g);

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

        } else if (prevGameState == GameStates.LOADING) {
            Helper.wr("Hiding LOADING screen.");
            //loadingScreen.Pause();
            //loadingScreen.SetIsVisible(false);
            //loadingScreen.UnloadResources();
            Helper.wr("Hiding LOADING screen DONE.");

        } else if (prevGameState == GameStates.SPLASH) {
            Helper.wr("Hiding SPLASH screen.");
            //splashScreen.Pause();
            //splashScreen.SetIsVisible(false);
            //splashScreen.UnloadResources();

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

        } else if (gameState == GameStates.LOADING) {
            Helper.wr("Showing LOADING screen.");
            //loadingScreen.LoadResources();
            //loadingScreen.UnPause();
            //loadingScreen.SetIsVisible(true);
            //loadingScreen.StartDatLoad();
            //currentScreen = loadingScreen;

        } else if (gameState == GameStates.SPLASH) {
            Helper.wr("Showing SPLASH screen.");
            screenSplash.LoadResources();
            screenSplash.UnPause();
            screenSplash.SetIsVisible(true);
            screenSplash.StartDisplay();
            currentScreen = screenSplash;

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

    /**
     * A generic event, GenericEventHandler, callback method. Used to handle
     * generic events from certain game screens, MmgGameScreen.
     *
     * @param obj
     */
    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        if (obj != null) {
            Helper.wr("HandleGenericEvent " + obj.GetGameState());
            if (obj.GetGameState() == GameStates.LOADING) {
                /*
                if (obj.GetId() == ScreenLoading.EVENT_LOAD_COMPLETE) {
                    //Final loading steps
                    DatExternalStrings.LOAD_EXT_STRINGS();                    
                    SwitchGameState(GameStates.MAIN_MENU);
                }
                */
                
            } else if (obj.GetGameState() == GameStates.SPLASH) {
                /*
                if (obj.GetId() == ScreenSplash.EVENT_DISPLAY_COMPLETE) {
                    SwitchGameState(GameStates.LOADING);
                }
                */
                
            }
        }
    }

    protected Graphics2D GetBuffer() {
        if (graphics == null) {
            try {
                graphics = (Graphics2D) strategy.getDrawGraphics();
            } catch (IllegalStateException e) {
                return null;
            }
        }
        return graphics;
    }

    public int GetWinWidth() {
        return winWidth;
    }

    public int GetWinHeight() {
        return winHeight;
    }

    public int GetX() {
        return myX;
    }

    public int GetY() {
        return myY;
    }

    protected boolean UpdateScreen() {
        graphics.dispose();
        graphics = null;
        try {
            strategy.show();
            Toolkit.getDefaultToolkit().sync();
            return (!strategy.contentsLost());

        } catch (Exception e) {
            return true;
        }
    }

    public void UpdateGame() {
        updateTick++;

        prev = now;
        now = System.currentTimeMillis();

        // update game logic here
        if (currentScreen != null) {
            currentScreen.MmgUpdate(updateTick, now, (now - prev));
        }
    }

    public void RenderGame() {
        if (PAUSE == true || EXIT == true) {
            //do nothing
        } else {
            UpdateGame();
        }

        // Update Graphics
        do {
            bg = GetBuffer();
            g = backgroundGraphics;
            g2d = (Graphics2D) g;

            if (currentScreen == null || currentScreen.IsPaused() == true || currentScreen.IsReady() == false) {
                //do nothing
            } else {
                //clear background
                g.setColor(Color.DARK_GRAY);
                g.fillRect(0, 0, winWidth, winHeight);

                //draw border
                g.setColor(Color.WHITE);
                g.drawRect(MmgScreenData.GetGameLeft() - 1, MmgScreenData.GetGameTop() - 1, MmgScreenData.GetGameWidth() + 1, MmgScreenData.GetGameHeight() + 1);

                g.setColor(Color.BLACK);
                g.fillRect(MmgScreenData.GetGameLeft(), MmgScreenData.GetGameTop(), MmgScreenData.GetGameWidth(), MmgScreenData.GetGameHeight());

                p.SetGraphics(g2d);
                p.SetAdvRenderHints();
                currentScreen.MmgDraw(p);

                if (Helper.LOGGING == true) {
                    tmpF = g.getFont();
                    g.setFont(debugFont);
                    g.drawString(GamePanel.FPS, 15, 15);
                    g.drawString("Var1: " + GamePanel.VAR1, 15, 35);
                    g.drawString("Var2: " + GamePanel.VAR2, 15, 55);
                    g.setFont(tmpF);
                }
            }

            //draws a scaled version of the final state of the background buffer to the screen buffer if scaling is
            //enabled.
            if (scale != 1.0) {
                bg.drawImage(background, sMyX, sMyY, sWinWidth, sWinHeight, 0, 0, winWidth, winHeight, null);
            } else {
                bg.drawImage(background, myX, myY, null);
            }
            bg.dispose();
            
        } while (!UpdateScreen());
    }
}

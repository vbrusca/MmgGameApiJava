package net.middlemind.MmgGameApiJava.MmgCore;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;

/**
 * The main frame of the game, extends the JFrame class. 
 * Handles housing the GamePanel, JPanel, that draws each game state by making the corresponding game screen the active screen. 
 * Created by Middlemind Games 08/01/2015
 *
 * @author Victor G. Brusca
 */
public class MainFrame extends JFrame {

    /**
     * The GamePanel, extends JFrame, class that handles drawing the different game states.
     */
    public GamePanel pnlGame;

    /**
     * The window width.
     */
    public final int winWidth;

    /**
     * The window height.
     */
    public final int winHeight;

    /**
     * The X offset of this frame.
     */
    public final int myX;

    /**
     * The Y offset of this frame.
     */
    public final int myY;

    /**
    * The game panel width.
    */
    public final int panelWidth;
    
    /**
    * The game panel height.
    */
    public final int panelHeight;
    
    /**
    * The game panel width.
    */
    public final int gameWidth;
    
    /**
    * The game panel height.
    */
    public final int gameHeight;    

    /**
     * Constructor that sets the window width and height, and defaults the X, Y offsets to 0. 
     * It also sets the JFrame and game width and height to that of the window width and height.
     *
     * @param WinWidth      The desired window width.
     * @param WinHeight     The desired window height.
     */
    public MainFrame(int WinWidth, int WinHeight) {
        winWidth = WinWidth;
        winHeight = WinHeight;
        panelWidth = winWidth;
        panelHeight = winHeight;
        gameWidth = winWidth;
        gameHeight = winHeight;
        myX = 0;
        myY = 0;
    }

    /**
     * Constructor that sets the window width and height, the JFrame width and height, and the game width and height.
     * 
     * @param WinWidth      The desired window width.
     * @param WinHeight     The desired window height.
     * @param PanWidth      The desired JFrame width.
     * @param PanHeight     The desired JFrame height.
     * @param GameWidth     The desired game width.
     * @param GameHeight    The desired game height.
     */
    public MainFrame(int WinWidth, int WinHeight, int PanWidth, int PanHeight, int GameWidth, int GameHeight) {
        MmgHelper.wr("MainFrame: WinWidth: " + WinWidth);
        MmgHelper.wr("MainFrame: WinHeight: " + WinHeight);
        MmgHelper.wr("MainFrame: PanelWidth: " + PanWidth);
        MmgHelper.wr("MainFrame: PanelHeight: " + PanHeight);
        MmgHelper.wr("MainFrame: GameWidth: " + GameWidth);
        MmgHelper.wr("MainFrame: GameHeight: " + GameHeight);        
        winWidth = WinWidth;
        winHeight = WinHeight;
        panelWidth = PanWidth;
        panelHeight = PanHeight;
        gameWidth = GameWidth;
        gameHeight = GameHeight;
        myX = (winWidth - panelWidth) / 2;
        myY = (winHeight - panelHeight) / 2;
    }

    /**
     * Sets the display text of the frame rate label.
     *
     * @param fr        A long representing the current drawing frame rate, or the frame rate if no time lock is applied.
     * @param rfr       A long representing the locked frame rate.
     */
    public void SetFrameRate(long fr, long rfr) {
        if (pnlGame != null) {
            GamePanel.FPS = "Drawing FPS: " + fr + " Actual FPS: " + rfr;
        }
    }

    /**
     * Sets the GamePanel for this class to use as the game being displayed.
     * 
     * @param gp        The GamePanel to display.
     */
    public void SetGamePanel(GamePanel gp) {
        pnlGame = gp;
    }
    
    /**
     * Gets the current GamePanel this classing is using to display a game.
     * 
     * @return      The current GamePanel being displayed. 
     */
    public GamePanel GetGamePanel() {
        return pnlGame;
    }

    /**
     * Gets the current window width of the application.
     * 
     * @return      The current window width.
     */
    public int GetWindowWidth() {
        return winWidth;
    }

    /**
     * Gets the current window height of the application.
     * 
     * @return The current window height. 
     */
    public int GetWindowHeight() {
        return winHeight;
    }

    /**
     * Gets the X offset of the GamePanel in this JFrame.
     * 
     * @return      The X offset of the GamePanel's rendering location in the JFrame.
     */
    public int GetOffsetX() {
        return myX;
    }

    /**
     * Gets the Y offset of the GamePanel in this JFrame.
     * 
     * @return      The Y offset of the GamePanel's rendering location in the JFrame.
     */
    public int GetOffsetY() {
        return myY;
    }

    /**
     * Gets the width of the GamePanel in this JFrame.
     * 
     * @return      The width of the GamePanel in this JFrame.
     */
    public int GetGamePanelWidth() {
        return panelWidth;
    }
    
    /**
     * Gets the height of the GamePanel in this JFrame.
     * 
     * @return      The height of the GamePanel in this JFrame.
     */    
    public int GetGamePanelHeight() {
        return panelHeight;
    }
    
    /**
     * Gets the width of the game that's rendered by the GamePanel in this JFrame.
     * 
     * @return      The width of the game that's rendered by the GamePanel in this JFrame.
     */
    public int GetGameWidth() {
        return gameWidth;
    }
    
    /**
     * Gets the height of the game that's rendered by the GamePanel in this JFrame.
     * 
     * @return      The height of the game that's rendered by the GamePanel in this JFrame.
     */
    public int GetGameHeight() {
        return gameHeight;
    }
    
    /**
     * Initializes the components used by this JFrame.
     */
    public void InitComponents() {
        MmgHelper.wr("MainFrame: Found Screen Dimen: " + winWidth + "x" + winHeight);
        MmgHelper.wr("MainFrame: Found Position: " + myX + "x" + myY);
        add(pnlGame.GetCanvas());
                
        pnlGame.GetCanvas().setFocusable(true);
        pnlGame.GetCanvas().requestFocus();
        pnlGame.GetCanvas().requestFocusInWindow();        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(GameSettings.RUN_IN_EXCLUSIVE_MODE == true) { 
            // Get the graphics environment and default screen device
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            JFrame frame = (JFrame)this;

            frame.setUndecorated(true);
            // Check if full-screen exclusive mode is supported
            if (gd.isFullScreenSupported()) {
                try {
                    System.out.println("Full-screen: Entering full screen exclusive mode.");
                    // Enter full-screen exclusive mode
                    DisplayMode ogd = gd.getDisplayMode();
                    gd.setFullScreenWindow(frame);

                    if (gd.isDisplayChangeSupported()) {
                        ogd = gd.getDisplayMode();
                        int cnt = 1;
                        for(DisplayMode lgd : gd.getDisplayModes()) {
                            System.out.println("Display mode " + cnt + " supports width: " + lgd.getWidth() + ", height: " + lgd.getHeight());  
                            if(lgd.getWidth() == GameSettings.RUN_IN_EXCLUSIVE_MODE_DESIRED_WIDTH && lgd.getHeight() == GameSettings.RUN_IN_EXCLUSIVE_MODE_DESIRED_HEIGHT) {
                                gd.setDisplayMode(lgd);
                            }
                            cnt += 1;                            
                        }                        
                    } else {
                        System.out.println("Display mode changes not supported. Running in default full screen resolution.");
                    }                    
                    
                } catch (Exception e) {
                    System.err.println("Full-screen: Failed to enter full-screen exclusive mode: " + e.getMessage());
                    e.printStackTrace();
                    
                    // Fallback to windowed mode if exclusive mode fails
                    gd.setFullScreenWindow(null); // Exit full screen if it was partially set up
                    frame.setUndecorated(false);
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);                    
                    frame.setVisible(true);
                }
            } else {
                System.out.println("Full-screen: exclusive mode not supported. Maximizing window instead.");
                // Fallback for systems that don't support exclusive mode
                frame.setUndecorated(false);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            }      
        }
        
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            @SuppressWarnings("CallToPrintStackTrace")
            public void windowClosing(WindowEvent e) {
                try {
                    MmgHelper.wr("MainFrame: WindowClosing");
                    GamePanel.PAUSE = true;
                    GamePanel.EXIT = true;
                    RunFrameRate.PAUSE = true;
                    RunFrameRate.RUNNING = false;
                } catch (Exception ex) {
                    MmgHelper.wrErr(ex);
                }
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    /**
     * Forces the GamePanel to repaint itself.
     */
    public void Redraw() {
        if (pnlGame != null) {
            pnlGame.RenderGame();
        }
    }
}
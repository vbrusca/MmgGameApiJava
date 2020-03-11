package com.middlemind.Odroid;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

/**
 * The main frame of the game, extends the JFrame class. Handles housing
 * the GamePanel, JPanel, that draws each game state by making the corresponding
 * game screen the active screen. Created on August 1, 2015, 10:57 PM by
 * Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class MainFrame extends JFrame {

    /**
     * A label used to display the current frame rate.
     */
    //protected JLabel lblFrameRate;
    /**
     * The GamePanel, extends JPanel, class that handles drawing the different
     * game states.
     */
    protected GamePanel pnlGame;

    /**
     * The window width.
     */
    protected final int winWidth;

    /**
     * The window height.
     */
    protected final int winHeight;

    /**
     * The X offset of this frame.
     */
    protected final int myX;

    /**
     * The Y offset of this frame.
     */
    protected final int myY;

    /**
    * The game panel width.
    */
    protected final int panelWidth;
    
    /**
    * The game panel height.
    */
    protected final int panelHeight;
    
    /**
    * The game panel width.
    */
    protected final int gameWidth;
    
    /**
    * The game panel height.
    */
    protected final int gameHeight;    

    /**
     * Constructor that sets the window width and height, and defaults the X, Y
     * offsets to 0.
     *
     * @param WinWidth The window width.
     * @param WinHeight The window height.
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

    public MainFrame(int WinWidth, int WinHeight, int PanWidth, int PanHeight, int GameWidth, int GameHeight) {
        Helper.wr("MainFrame: WinWidth: " + WinWidth);
        Helper.wr("MainFrame: WinHeight: " + WinHeight);
        Helper.wr("MainFrame: PanelWidth: " + PanWidth);
        Helper.wr("MainFrame: PanelHeight: " + PanHeight);
        Helper.wr("MainFrame: GameWidth: " + GameWidth);
        Helper.wr("MainFrame: GameHeight: " + GameHeight);        
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
     * @param fr A long representing the current drawing frame rate, or the
     * frame rate if no time lock is applied.
     * @param rfr A long representing the locked frame rate.
     */
    public void SetFrameRate(long fr, long rfr) {
        if (pnlGame != null) {
            GamePanel.FPS = "Drawing FPS: " + fr + " Actual FPS: " + rfr;
        }
    }

    public void SetGamePanel(GamePanel gp) {
        pnlGame = gp;
    }
    
    public GamePanel GetGamePanel() {
        return pnlGame;
    }

    public int GetWindowWidth() {
        return winWidth;
    }

    public int GetWindowHeight() {
        return winHeight;
    }

    public int GetOffsetX() {
        return myX;
    }

    public int GetOffsetY() {
        return myY;
    }

    public int GetGamePanelWidth() {
        return panelWidth;
    }

    public int GetGamePanelHeight() {
        return panelHeight;
    }
    
    public int GetGameWidth() {
        return gameWidth;
    }
    
    public int GetGameHeight() {
        return gameHeight;
    }
    
    /**
     * Initializes the components used by this JFrame.
     */
    public void InitComponents() {
        Helper.wr("MainFrame: Found Screen Dimen: " + winWidth + "x" + winHeight);
        Helper.wr("MainFrame: Found Position: " + myX + "x" + myY);

        //pnlGame = new GamePanel(this, panelWidth, panelHeight, (winWidth - panelWidth) / 2, (winHeight - panelHeight) / 2);
        add(pnlGame.GetCanvas());
                
        pnlGame.GetCanvas().setFocusable(true);
        pnlGame.GetCanvas().requestFocus();
        pnlGame.GetCanvas().requestFocusInWindow();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            @SuppressWarnings("CallToPrintStackTrace")
            public void windowClosing(WindowEvent e) {
                try {
                    Helper.wr("WindowClosing");

                    GamePanel.PAUSE = true;
                    GamePanel.EXIT = true;
                    RunFrameRate.PAUSE = true;
                    RunFrameRate.RUNNING = false;
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
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

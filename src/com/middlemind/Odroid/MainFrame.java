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
public final class MainFrame extends JFrame {

    /**
     * A label used to display the current frame rate.
     */
    //private JLabel lblFrameRate;
    /**
     * The GamePanel, extends JPanel, class that handles drawing the different
     * game states.
     */
    private GamePanel pnlGame;

    /**
     * The window width.
     */
    private final int winWidth;

    /**
     * The window height.
     */
    private final int winHeight;

    /**
     * The X offset of this frame.
     */
    private final int myX;

    /**
     * The Y offset of this frame.
     */
    private final int myY;

    /**
    * The game panel width.
    */
    private final int panelWidth;
    
    /**
    * The game panel height.
    */
    private final int panelHeight;

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
        myX = 0;
        myY = 0;
        InitComponents();
    }

    public MainFrame(int WinWidth, int WinHeight, int PanWidth, int PanHeight) {
        winWidth = WinWidth;
        winHeight = WinHeight;
        panelWidth = PanWidth;
        panelHeight = PanHeight;
        myX = (winWidth - panelWidth) / 2;
        myY = (winHeight - panelHeight) / 2;
        InitComponents();
    }

    /**
     * Sets the display text of the frame rate label.
     *
     * @param fr A long representing the current drawing frame rate, or the
     * frame rate if no time lock is applied.
     * @param rfr A long representing the locked frame rate.
     */
    public final void SetFrameRate(long fr, long rfr) {
        if (pnlGame != null) {
            GamePanel.FPS = "Drawing FPS: " + fr + " Actual FPS: " + rfr;
        }
    }

    public final GamePanel GetGamePanel() {
        return pnlGame;
    }

    public final int GetWindowWidth() {
        return winWidth;
    }

    public final int GetWindowHeight() {
        return winHeight;
    }

    public final int GetOffsetX() {
        return myX;
    }

    public final int GetOffsetY() {
        return myY;
    }

    public final int GetGamePanelWidth() {
        return panelWidth;
    }

    public final int GetGamePanelHeight() {
        return panelHeight;
    }
    
    /**
     * Initializes the components used by this JFrame.
     */
    public final void InitComponents() {
        Helper.wr("MainFrame: Found Screen Dimen: " + winWidth + "x" + winHeight);
        Helper.wr("MainFrame: Found Position: " + myX + "x" + myY);

        pnlGame = new GamePanel(this, panelWidth, panelHeight, (winWidth - panelWidth) / 2, (winHeight - panelHeight) / 2);
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
    public final void Redraw() {
        if (pnlGame != null) {
            pnlGame.RenderGame();
        }
    }
}

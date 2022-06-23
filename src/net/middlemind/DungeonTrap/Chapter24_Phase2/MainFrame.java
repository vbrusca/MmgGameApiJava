package net.middlemind.DungeonTrap.Chapter24_Phase2;

/**
 * An instance of the MainFrame class that extends the MmgCore MainFrame class.
 * 
 * @author Victor G. Brusca, Middlemind Games
 * 02/19/2020
 */
public class MainFrame extends net.middlemind.MmgGameApiJava.MmgCore.MainFrame {
    
    /**
     * The default constructor of the MainFrame class.
     * 
     * @param WinWidth      The total window width.
     * @param WinHeight     The total window height.
     * @param PanWidth      The total panel width.
     * @param PanHeight     The total panel height.
     * @param GameWidth     The target game width.
     * @param GameHeight    The target game height.
     */
    public MainFrame(int WinWidth, int WinHeight, int PanWidth, int PanHeight, int GameWidth, int GameHeight) {
        super(WinWidth, WinHeight, PanWidth, PanHeight, GameWidth, GameHeight);
    }
    
    /**
     * A simplified constructor for the MainFrame class.
     * 
     * @param WinWidth      The total window width.
     * @param WinHeight     The total window height.
     */
    public MainFrame(int WinWidth, int WinHeight) {
        super(WinWidth, WinHeight);
    }
}
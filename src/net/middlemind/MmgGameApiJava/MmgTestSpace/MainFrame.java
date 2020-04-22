package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.Helper;

/**
 * Created by Middlemind Games 02/19/2020
 * 
 * @author Victor G. Brusca
 */
public class MainFrame extends net.middlemind.MmgGameApiJava.MmgCore.MainFrame {
    
    /**
     * 
     * 
     * @param WinWidth
     * @param WinHeight
     * @param PanWidth
     * @param PanHeight
     * @param GameWidth
     * @param GameHeight 
     */
    public MainFrame(int WinWidth, int WinHeight, int PanWidth, int PanHeight, int GameWidth, int GameHeight) {
        super(WinWidth, WinHeight, PanWidth, PanHeight, GameWidth, GameHeight);
        Helper.wr("MainFrame.Constructor");
    }
    
    /**
     * 
     * 
     * @param WinWidth
     * @param WinHeight 
     */
    public MainFrame(int WinWidth, int WinHeight) {
        super(WinWidth, WinHeight);
        Helper.wr("MainFrame.Constructor");        
    }    
    
}

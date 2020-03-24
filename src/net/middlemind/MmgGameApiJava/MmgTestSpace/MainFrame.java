package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.Helper;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 02/19/2020
 */
public class MainFrame extends net.middlemind.MmgGameApiJava.MmgCore.MainFrame {
    
    public MainFrame(int WinWidth, int WinHeight, int PanWidth, int PanHeight, int GameWidth, int GameHeight) {
        super(WinWidth, WinHeight, PanWidth, PanHeight, GameWidth, GameHeight);
        Helper.wr("TestSpace.MainFrame.Constructor");
    }
    
    public MainFrame(int WinWidth, int WinHeight) {
        super(WinWidth, WinHeight);
        Helper.wr("TestSpace.MainFrame.Constructor");        
    }    
    
}

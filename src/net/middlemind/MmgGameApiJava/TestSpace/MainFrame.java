package net.middlemind.MmgGameApiJava.TestSpace;

import com.middlemind.Odroid.Helper;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 02/19/2020
 */
public class MainFrame extends com.middlemind.Odroid.MainFrame {
    
    public MainFrame(int WinWidth, int WinHeight, int PanWidth, int PanHeight, int GameWidth, int GameHeight) {
        super(WinWidth, WinHeight, PanWidth, PanHeight, GameWidth, GameHeight);
        Helper.wr("TestSpace.MainFrame.Constructor");
    }
    
    public MainFrame(int WinWidth, int WinHeight) {
        super(WinWidth, WinHeight);
        Helper.wr("TestSpace.MainFrame.Constructor");        
    }    
    
}

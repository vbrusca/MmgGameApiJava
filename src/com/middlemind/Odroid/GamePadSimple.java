package com.middlemind.Odroid;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/05/2020
 */
public interface GamePadSimple {        
    public void ProcessDpadPress(int dir);
    public void ProcessDpadRelease(int dir);
    public void ProcessDpadClick(int dir);
    public void ProcessAPress();
    public void ProcessARelease();    
    public void ProcessAClick();
    public void ProcessBPress();
    public void ProcessBRelease();        
    public void ProcessBClick();
}

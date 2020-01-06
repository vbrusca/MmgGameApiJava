package com.middlemind.Odroid;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/05/2020
 */
public interface GamePadSimple {        
    public void ProcessDpadPress(int dir);
    public void ProcessAClick();
    public void ProcessBClick();
}

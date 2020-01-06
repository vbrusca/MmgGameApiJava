package com.middlemind.Odroid;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/05/2020
 */
public interface GamePadSimple {
    
    public enum Direction {
        DOWN,
        UP,
        LEFT,
        RIGHT
    }
    
    public boolean ProcessDpadPress(Direction dir);
    public boolean ProcessAClick();
    public boolean ProcessBClick();
}

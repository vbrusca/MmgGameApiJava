package com.middlemind.Odroid_Tutorial1_Gpio;

import net.middlemind.MmgGameApiJava.MmgCore.GamePadSimple;

/**
 * A class the implements the MmgCore GamePadSimple interface.
 * The interface is designed to handle input in the form of a simple gamepad with a directional
 * pad, A and B buttons.
 * 
 * @author Victor G. Brusca, Middlemind Games
 * 01/22/2020
 */
public class GamePadExample implements GamePadSimple {

    /**
     * A method to handle dpad press events.
     * 
     * @param dir       The dpad UP, DOWN, LEFT, RIGHT code of the press event.
     */
    @Override
    public void ProcessDpadPress(int dir) {

    }

    /**
     * A method to handle dpad release events.
     * 
     * @param dir       The dpad UP, DOWN, LEFT, RIGHT code of the release event.
     */    
    @Override
    public void ProcessDpadRelease(int dir) {

    }

    /**
     * A method to handle dpad click events.
     * 
     * @param dir       The dpad UP, DOWN, LEFT, RIGHT code of the click event.
     */    
    @Override
    public void ProcessDpadClick(int dir) {

    }

    /**
     * A method to handle A button press events.
     */
    @Override
    public void ProcessAPress() {
        System.out.println("ProcessAPress");
    }

    /**
     * A method to handle A button release events.
     */
    @Override
    public void ProcessARelease() {

    }

    /**
     * A method to handle A button click events.
     */
    @Override
    public void ProcessAClick() {

    }

    /**
     * A method to handle B button press events.
     */
    @Override
    public void ProcessBPress() {

    }

    /**
     * A method to handle B button release events.
     */
    @Override
    public void ProcessBRelease() {

    }

    /**
     * A method to handle B button click events.
     */
    @Override
    public void ProcessBClick() {

    }
 }
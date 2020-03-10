package com.middlemind.Odroid_Tutorial1_Gpio;

import com.middlemind.Odroid.GamePadSimple;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/22/2020
 */
public class GamePadExample implements GamePadSimple {

    @Override
    public void ProcessDpadPress(int dir) {

    }

    @Override
    public void ProcessDpadRelease(int dir) {

    }

    @Override
    public void ProcessDpadClick(int dir) {

    }

    @Override
    public void ProcessAPress() {
        System.out.println("ProcessAPress");
    }

    @Override
    public void ProcessARelease() {

    }

    @Override
    public void ProcessAClick() {

    }

    @Override
    public void ProcessBPress() {

    }

    @Override
    public void ProcessBRelease() {

    }

    @Override
    public void ProcessBClick() {

    }

 }

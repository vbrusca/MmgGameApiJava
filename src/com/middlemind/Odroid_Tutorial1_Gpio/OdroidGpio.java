package com.middlemind.Odroid_Tutorial1_Gpio;

import net.middlemind.MmgGameApiJava.MmgCore.GpioHub;
import net.middlemind.MmgGameApiJava.MmgCore.GpioHubRunner;
import net.middlemind.MmgGameApiJava.MmgCore.GpioPin;
import net.middlemind.MmgGameApiJava.MmgCore.GpioPin.GpioButton;

/**
 * A static main class that is used for a demonstration of GPIO driven input on ODROID-N2 devices.
 * 
 * @author Victor G. Brusca, Middlemind Games
 * 01/22/2020
 */
public class OdroidGpio {
    
    /**
     * A static main execution entry point.
     * 
     * @param args      A string array of the command line arguments passed to this class.
     */
    public static void main(String[] args) {
        try {
            GpioPin[] buttons = new GpioPin[6];
            buttons[0] = new GpioPin(488, false, true, GpioButton.BtnUp, true, true, true);
            buttons[1] = new GpioPin(488, false, true, GpioButton.BtnDown, true, true, true);
            buttons[2] = new GpioPin(488, false, true, GpioButton.BtnLeft, true, true, true);
            buttons[3] = new GpioPin(488, false, true, GpioButton.BtnRight, true, true, true);
            buttons[4] = new GpioPin(488, false, true, GpioButton.BtnA, true, true, true);
            buttons[5] = new GpioPin(488, false, true, GpioButton.BtnB, true, true, true);

            GpioHub hub = new GpioHub(buttons);
            hub.PrepPins();

            GamePadExample gamePad = new GamePadExample();
            GpioHubRunner hubRunner = new GpioHubRunner(hub, 500, gamePad);
            long start = System.currentTimeMillis();
            long stop = System.currentTimeMillis();
            long duration = 15000;
            while(stop - start < duration) {
                hubRunner.PollGpio();
                try {
                    Thread.sleep(100);
                }catch (Exception e) { 

                }
                stop = System.currentTimeMillis();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }     
}

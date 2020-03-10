package com.middlemind.Odroid_Tutorial1_Gpio;

import com.middlemind.Odroid.GpioHub;
import com.middlemind.Odroid.GpioHubRunner;
import com.middlemind.Odroid.GpioPin;
import com.middlemind.Odroid.GpioPin.GpioButton;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/22/2020
 */
public class OdroidGpio {
    
    /**
     * @param args the command line arguments
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

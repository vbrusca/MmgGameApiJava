package com.middlemind.Odroid;

import com.middlemind.Odroid.GpioPin.GpioButton;
import java.io.IOException;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/05/202
 */
public class GpioHub {
    public static int UP = 0;
    public static int DOWN = 1;
    public static int LEFT = 2;
    public static int RIGHT = 3;
    public static int A = 4;
    public static int B = 5;        
    public static int char0toInt = 48;
    public static int char1toInt = 49;
    
    public GpioPin[] buttons = null;
    private Runtime runTime = null;
    private int tmp;
    private boolean prepped = false;
    
    public GpioHub() {
        buttons = new GpioPin[6];
        buttons[0] = new GpioPin(1, true, true, GpioButton.BtnUp);
        buttons[1] = new GpioPin(2, true, true, GpioButton.BtnDown);
        buttons[2] = new GpioPin(3, true, true, GpioButton.BtnLeft);
        buttons[3] = new GpioPin(4, true, true, GpioButton.BtnRight);
        buttons[4] = new GpioPin(5, true, true, GpioButton.BtnA);
        buttons[5] = new GpioPin(6, true, true, GpioButton.BtnB);
        runTime = Runtime.getRuntime();        
    }
    
    public boolean GetUp() {
        if(buttons != null && buttons[UP] != null) {
            return buttons[UP].stateCurrent;
        }
        return false;
    }
    
    public boolean GetDown() {
        if(buttons != null && buttons[DOWN] != null) {
            return buttons[DOWN].stateCurrent;
        }
        return false;
    }    
    
    public boolean GetLeft() {
        if(buttons != null && buttons[LEFT] != null) {
            return buttons[LEFT].stateCurrent;
        }
        return false;
    }        
    
    public boolean GetRight() {
        if(buttons != null && buttons[RIGHT] != null) {
            return buttons[RIGHT].stateCurrent;
        }
        return false;
    }            
    
    public boolean GetA() {
        if(buttons != null && buttons[A] != null) {
            return buttons[A].stateCurrent;
        }
        return false;
    }                
    
    public boolean GetB() {
        if(buttons != null && buttons[B] != null) {
            return buttons[B].stateCurrent;
        }
        return false;
    }                    
    
    public void PrepPins() throws IOException {
        if(runTime != null && buttons != null) {
            for(GpioPin btn: buttons) {
                runTime.exec("echo " + btn.pinNum + ">/sys/class/gpio/unexport");
                runTime.exec("echo " + btn.pinNum + ">/sys/class/gpio/export");
                if(btn.pinIn == false) {
                    if(btn.pinHigh == true) {
                        runTime.exec("echo 1 >/sys/class/gpio" + btn.pinNum + "/value");
                    } else {
                        runTime.exec("echo 0 >/sys/class/gpio" + btn.pinNum + "/value");                        
                    }
                }
            }
            prepped = true;
        }
    }
        
    public boolean IsPrepped() {
        return prepped;
    }
    
    public void GetState() throws IOException {
        for(GpioPin btn: buttons) {
            tmp = runTime.exec("cat /sys/class/gpio/gpio" + btn.pinNum + "/value").getErrorStream().read();
            if(tmp == char0toInt) {
                btn.stateTmp = false;
            } else {
                btn.stateTmp = true;                
            }
            btn.stateCurrent = btn.stateTmp;
        }
    }
}

package com.middlemind.Odroid;

import com.middlemind.Odroid.GpioPin.GpioButton;
import java.io.IOException;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/05/202
 */
public final class GpioHub {
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
        buttons[0] = new GpioPin(1, true, false, GpioButton.BtnUp, true, true, false);
        buttons[1] = new GpioPin(2, true, false, GpioButton.BtnDown, true, true, false);
        buttons[2] = new GpioPin(3, true, false, GpioButton.BtnLeft, true, true, false);
        buttons[3] = new GpioPin(4, true, false, GpioButton.BtnRight, true, true, false);
        buttons[4] = new GpioPin(5, true, false, GpioButton.BtnA, false, false, true);
        buttons[5] = new GpioPin(6, true, false, GpioButton.BtnB, false, false, true);
        runTime = Runtime.getRuntime();        
    }
        
    public final boolean GetDownPressed() {
        if(buttons[DOWN].checkPressed == true) {
            return buttons[DOWN].pressed;
        } else {
            return false;
        }
    }
    
    public final boolean GetDownReleased() {
        if(buttons[DOWN].checkReleased == true) {
            return buttons[DOWN].released;
        } else {
            return false;
        }
    }    
    
    public final boolean GetDownClicked() {
        if(buttons[DOWN].checkClicked == true) {
            return buttons[DOWN].clicked;
        } else {
            return false;
        }
    }
    
    public final boolean GetUpPressed() {
        if(buttons[UP].checkPressed == true) {
            return buttons[UP].pressed;
        } else {
            return false;
        }
    }
    
    public final boolean GetUpReleased() {
        if(buttons[UP].checkReleased == true) {
            return buttons[UP].released;
        } else {
            return false;
        }
    }    
    
    public final boolean GetUpClicked() {
        if(buttons[UP].checkClicked == true) {
            return buttons[UP].clicked;
        } else {
            return false;
        }
    }    
    
    public final boolean GetLeftPressed() {
        if(buttons[LEFT].checkPressed == true) {
            return buttons[LEFT].pressed;
        } else {
            return false;
        }
    }
    
    public final boolean GetLeftReleased() {
        if(buttons[LEFT].checkReleased == true) {
            return buttons[LEFT].released;
        } else {
            return false;
        }
    }    
    
    public final boolean GetLeftClicked() {
        if(buttons[LEFT].checkClicked == true) {
            return buttons[LEFT].clicked;
        } else {
            return false;
        }
    }    
    
    public final boolean GetRightPressed() {
        if(buttons[RIGHT].checkPressed == true) {
            return buttons[RIGHT].pressed;
        } else {
            return false;
        }
    }
    
    public final boolean GetRightReleased() {
        if(buttons[RIGHT].checkReleased == true) {
            return buttons[RIGHT].released;
        } else {
            return false;
        }
    }    
    
    public final boolean GetRightClicked() {
        if(buttons[RIGHT].checkClicked == true) {
            return buttons[RIGHT].clicked;
        } else {
            return false;
        }
    }    
    
    public final boolean GetAPressed() {
        if(buttons[A].checkPressed == true) {
            return buttons[A].pressed;
        } else {
            return false;
        }
    }
    
    public final boolean GetAReleased() {
        if(buttons[A].checkReleased == true) {
            return buttons[A].released;
        } else {
            return false;
        }
    }    
    
    public final boolean GetAClicked() {
        if(buttons[A].checkClicked == true) {
            return buttons[A].clicked;
        } else {
            return false;
        }
    }    
    
    public final boolean GetBPressed() {
        if(buttons[B].checkPressed == true) {
            return buttons[B].pressed;
        } else {
            return false;
        }
    }
    
    public final boolean GetBReleased() {
        if(buttons[B].checkReleased == true) {
            return buttons[B].released;
        } else {
            return false;
        }
    }    
    
    public final boolean GetBClicked() {
        if(buttons[B].checkClicked == true) {
            return buttons[B].clicked;
        } else {
            return false;
        }
    }    
    
    public final void SetGpioPin(int pinIdx, boolean high) throws IOException {
        if(buttons != null && (pinIdx >= 0 || pinIdx < buttons.length)) {
            buttons[pinIdx].pinHigh = high;
            if(buttons[pinIdx].pinHigh == true) {
                runTime.exec("echo 1 >/sys/class/gpio" + buttons[pinIdx].pinNum + "/value");
            } else {
                runTime.exec("echo 0 >/sys/class/gpio" + buttons[pinIdx].pinNum + "/value");                        
            }
        }
    }
    
    public final void PrepPins() throws IOException {
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
        
    public final boolean IsPrepped() {
        return prepped;
    }
    
    public final void CleanUp() {
        for(GpioPin btn: buttons) {
            if(btn.pressed == true) {
                btn.pressed = false;
            }
            
            if(btn.clicked == true) {
                btn.clicked = false;
            }
        }   
    }
    
    public final void GetState() throws IOException {
        for(GpioPin btn: buttons) {
            tmp = runTime.exec("cat /sys/class/gpio/gpio" + btn.pinNum + "/value").getErrorStream().read();
            if(tmp == char0toInt) {
                btn.stateTmp = false;
            } else {
                btn.stateTmp = true;                
            }
            
            if(btn.stateTmp != btn.stateCurrent) {
                btn.statePrev = btn.stateCurrent;
                btn.stateCurrent = btn.stateTmp;
            }
                        
            if(btn.stateCurrent == false && btn.statePrev == true) {
                btn.released = true;
                btn.clicked = true;
            } else {
                btn.released = false;
                btn.clicked = false;                
            }
            
            if(btn.stateCurrent == true && btn.statePrev == false) {
                btn.pressed = true;
            } else {
                btn.pressed = false;
            }
        }
    }
}

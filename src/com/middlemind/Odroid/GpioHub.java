package com.middlemind.Odroid;

import com.middlemind.Odroid.GpioPin.GpioButton;
import java.io.File;
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
    
    protected GpioPin[] buttons = null;
    protected Runtime runTime = null;
    protected int tmp;
    protected boolean prepped = false;
    protected boolean gpioEnabled = false;
    
    public GpioHub() {
        try {
            File f = new File("/sys/class/gpio");
            if(!f.isDirectory() || !f.exists()) {
                System.out.println("GPIO directory, /sys/class/gpio/, does not exist. Disabling class functionality.");
                gpioEnabled = false;
            } else {
                System.out.println("GPIO directory, /sys/class/gpio/, exists! Enabling class functionality.");
                gpioEnabled = true;
            }
            
        }catch(Exception e) {
            Helper.wrErr(e);
        }
               
        buttons = new GpioPin[6];
        buttons[0] = new GpioPin(GameSettings.GpioPinBtnUp, true, false, GpioButton.BtnUp, GameSettings.BtnUpCheckPress, GameSettings.BtnUpCheckRelease, GameSettings.BtnUpCheckClick);
        buttons[1] = new GpioPin(GameSettings.GpioPinBtnDown, true, false, GpioButton.BtnDown, GameSettings.BtnDownCheckPress, GameSettings.BtnDownCheckRelease, GameSettings.BtnDownCheckClick);
        buttons[2] = new GpioPin(GameSettings.GpioPinBtnLeft, true, false, GpioButton.BtnLeft, GameSettings.BtnLeftCheckPress, GameSettings.BtnLeftCheckRelease, GameSettings.BtnLeftCheckClick);
        buttons[3] = new GpioPin(GameSettings.GpioPinBtnRight, true, false, GpioButton.BtnRight, GameSettings.BtnRightCheckPress, GameSettings.BtnRightCheckRelease, GameSettings.BtnRightCheckClick);
        buttons[4] = new GpioPin(GameSettings.GpioPinBtnA, true, false, GpioButton.BtnA, GameSettings.BtnACheckPress, GameSettings.BtnACheckRelease, GameSettings.BtnACheckClick);
        buttons[5] = new GpioPin(GameSettings.GpioPinBtnB, true, false, GpioButton.BtnB, GameSettings.BtnBCheckPress, GameSettings.BtnBCheckRelease, GameSettings.BtnBCheckClick);
        runTime = Runtime.getRuntime();        
    }

    public GpioHub(GpioPin[] Buttons) {
       try {
            File f = new File("/sys/class/gpio");
            if(!f.isDirectory() || !f.exists()) {
                System.out.println("GPIO directory, /sys/class/gpio/, does not exist. Disabling class functionality.");
                gpioEnabled = false;
            } else {
                System.out.println("GPIO directory, /sys/class/gpio/, exists! Enabling class functionality.");
                gpioEnabled = true;
            }
            
        }catch(Exception e) {
            Helper.wrErr(e);
        }        
        
        buttons = Buttons;
        runTime = Runtime.getRuntime(); 
    }

    public boolean IsGpioEnabled() {
        return gpioEnabled;
    }

    public void SetGpioEnabled(boolean b) {
        this.gpioEnabled = b;
    }
    
    public GpioPin[] GetButtons() {
        return buttons;
    }
    
    public boolean ButtonEnabled(int i) {
        if(buttons != null && i >= 0 && i < buttons.length) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean GetDownPressed() {
        if(ButtonEnabled(DOWN) && buttons[DOWN].checkPressed == true) {
            return buttons[DOWN].pressed;
        } else {
            return false;
        }
    }
    
    public boolean GetDownReleased() {
        if(ButtonEnabled(DOWN) && buttons[DOWN].checkReleased == true) {
            return buttons[DOWN].released;
        } else {
            return false;
        }
    }    
    
    public boolean GetDownClicked() {
        if(ButtonEnabled(DOWN) && buttons[DOWN].checkClicked == true) {
            return buttons[DOWN].clicked;
        } else {
            return false;
        }
    }
    
    public boolean GetUpPressed() {
        if(ButtonEnabled(UP) && buttons[UP].checkPressed == true) {
            return buttons[UP].pressed;
        } else {
            return false;
        }
    }
    
    public boolean GetUpReleased() {
        if(ButtonEnabled(UP) && buttons[UP].checkReleased == true) {
            return buttons[UP].released;
        } else {
            return false;
        }
    }    
    
    public boolean GetUpClicked() {
        if(ButtonEnabled(UP) && buttons[UP].checkClicked == true) {
            return buttons[UP].clicked;
        } else {
            return false;
        }
    }    
    
    public boolean GetLeftPressed() {
        if(ButtonEnabled(LEFT) && buttons[LEFT].checkPressed == true) {
            return buttons[LEFT].pressed;
        } else {
            return false;
        }
    }
    
    public boolean GetLeftReleased() {
        if(ButtonEnabled(LEFT) && buttons[LEFT].checkReleased == true) {
            return buttons[LEFT].released;
        } else {
            return false;
        }
    }    
    
    public boolean GetLeftClicked() {
        if(buttons[LEFT].checkClicked == true) {
            return buttons[LEFT].clicked;
        } else {
            return false;
        }
    }    
    
    public boolean GetRightPressed() {
        if(ButtonEnabled(RIGHT) && buttons[RIGHT].checkPressed == true) {
            return buttons[RIGHT].pressed;
        } else {
            return false;
        }
    }
    
    public boolean GetRightReleased() {
        if(ButtonEnabled(RIGHT) && buttons[RIGHT].checkReleased == true) {
            return buttons[RIGHT].released;
        } else {
            return false;
        }
    }    
    
    public boolean GetRightClicked() {
        if(ButtonEnabled(RIGHT) && buttons[RIGHT].checkClicked == true) {
            return buttons[RIGHT].clicked;
        } else {
            return false;
        }
    }    
    
    public boolean GetAPressed() {
        if(ButtonEnabled(A) && buttons[A].checkPressed == true) {
            return buttons[A].pressed;
        } else {
            return false;
        }
    }
    
    public boolean GetAReleased() {
        if(ButtonEnabled(A) && buttons[A].checkReleased == true) {
            return buttons[A].released;
        } else {
            return false;
        }
    }    
    
    public boolean GetAClicked() {
        if(ButtonEnabled(A) && buttons[A].checkClicked == true) {
            return buttons[A].clicked;
        } else {
            return false;
        }
    }    
    
    public boolean GetBPressed() {
        if(ButtonEnabled(B) && buttons[B].checkPressed == true) {
            return buttons[B].pressed;
        } else {
            return false;
        }
    }
    
    public boolean GetBReleased() {
        if(ButtonEnabled(B) && buttons[B].checkReleased == true) {
            return buttons[B].released;
        } else {
            return false;
        }
    }    
    
    public boolean GetBClicked() {
        if(ButtonEnabled(B) && buttons[B].checkClicked == true) {
            return buttons[B].clicked;
        } else {
            return false;
        }
    }    
    
    public void SetGpioPin(int pinIdx, boolean high) throws IOException {
        if(buttons != null && (pinIdx >= 0 || pinIdx < buttons.length)) {
            buttons[pinIdx].pinHigh = high;
            if(buttons[pinIdx].pinHigh == true) {
                runTime.exec("echo 1 > /sys/class/gpio/gpio" + buttons[pinIdx].pinNum + "/value");
            } else {
                runTime.exec("echo 0 > /sys/class/gpio/gpio" + buttons[pinIdx].pinNum + "/value");                        
            }
        }
    }
    
    public void PrepPins() throws IOException {
        if(runTime != null && buttons != null) {
            for(GpioPin btn: buttons) {
                runTime.exec("echo " + btn.pinNum + " > /sys/class/gpio/unexport");
                runTime.exec("echo " + btn.pinNum + " > /sys/class/gpio/export");
                if(btn.pinIn == false) {
                    runTime.exec("echo out > /sys/class/gpio/gpio" + btn.pinNum + "/direction");
                    if(btn.pinHigh == true) {
                        runTime.exec("echo 1 > /sys/class/gpio/gpio" + btn.pinNum + "/value");
                    } else {
                        runTime.exec("echo 0 > /sys/class/gpio/gpio" + btn.pinNum + "/value");                        
                    }
                } else {
                    if(btn.pinHigh == true) {
                        runTime.exec("echo 1 > /sys/class/gpio/gpio" + btn.pinNum + "/value");
                    } else {
                        runTime.exec("echo 0 > /sys/class/gpio/gpio" + btn.pinNum + "/value");                        
                    }                    
                    runTime.exec("echo in > /sys/class/gpio/gpio" + btn.pinNum + "/direction");                    
                }
            }
            prepped = true;
        }
    }
        
    public boolean IsPrepped() {
        return prepped;
    }
    
    public void CleanUp() {
        for(GpioPin btn: buttons) {
            if(btn.pressed == true) {
                btn.pressed = false;
            }
            
            if(btn.clicked == true) {
                btn.clicked = false;
            }
        }   
    }
    
    public void GetState() throws IOException {
        for(GpioPin btn: buttons) {
            tmp = runTime.exec("cat /sys/class/gpio/gpio" + btn.pinNum + "/value").getInputStream().read();
            //System.out.println("PinStatus: " + tmp);
            
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

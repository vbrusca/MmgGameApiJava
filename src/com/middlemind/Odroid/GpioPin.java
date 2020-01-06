package com.middlemind.Odroid;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/05/2020
 */
public class GpioPin {
    public enum GpioButton {
        BtnUp,
        BtnDown,
        BtnLeft,
        BtnRight,
        BtnA,
        BtnB
    };
    
    public int pinNum;
    public boolean pinHigh;
    public boolean pinIn;
    public GpioButton button;
    public boolean stateTmp;
    public boolean stateCurrent;
    public boolean statePrev;
    public boolean pressed;
    public boolean released;
    public boolean clicked;
    public boolean checkPressed;
    public boolean checkReleased;
    public boolean checkClicked;    
    
    public GpioPin(int pinNumber, boolean high, boolean in, GpioButton buttonType, boolean chkPress, boolean chkRelease, boolean chkClick) {
        pinNum = pinNumber;
        pinHigh = high;
        pinIn = in;
        button = buttonType;
        stateTmp = false;
        statePrev = false;
        stateCurrent = false;
        pressed = false;
        released = false;
        clicked = false;
        checkPressed = chkPress;
        checkReleased = chkRelease;
        checkClicked = chkClick;
    }
    
    public GpioPin(int pinNumber, GpioButton buttonType, boolean chkPress, boolean chkRelease, boolean chkClick) {
        pinNum = pinNumber;
        pinHigh = false;
        pinIn = false;
        button = buttonType;
        stateTmp = false;
        statePrev = false;
        stateCurrent = false;
        pressed = false;
        released = false;
        clicked = false;
        checkPressed = chkPress;
        checkReleased = chkRelease;
        checkClicked = chkClick;
    }    
}

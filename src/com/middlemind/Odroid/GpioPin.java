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
    
    public GpioPin(int num, boolean high, boolean in, GpioButton button) {
        pinNum = num;
        pinHigh = high;
        pinIn = in;
        button = button;
    }
}

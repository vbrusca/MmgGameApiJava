package com.middlemind.Odroid;

import java.io.IOException;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/05/2020
 */
public final class GpioHubRunner implements Runnable {
    private GpioHub gpioHub;
    private long pollingIntervalMs = 10;
    private long start;
    private long stop;
    private long diff;
    private boolean running = false;
    private GamePadSimple gamePad;
    
    
    public GpioHubRunner(GpioHub hub, long intervalMs, GamePadSimple gamePadSimple) {
        gpioHub = hub;
        pollingIntervalMs = intervalMs;
        gamePad = gamePadSimple;
        
        if(gpioHub == null) {
            System.err.println("GpioHub is null! Turning off GpioHubRunner.");
            running = false;            
        }
        
        if(gpioHub != null && gpioHub.IsPrepped() == false) {
            System.err.println("GpioHub has not had its pins prepped! Turning off GpioHubRunner.");
            running = false;
        }
        
        if(gamePad == null) {
            System.err.println("GamePad is null! Turning off GpioHubRunner.");
        }
    }

    public long GetPollingIntervalMs() {
        return pollingIntervalMs;
    }

    public void SetPollingIntervalMs(long intervalMs) {
        pollingIntervalMs = intervalMs;
    }

    public boolean IsRunning() {
        return running;
    }

    public void SetRunning(boolean b) {
        running = b;
    }
    
    @Override
    public void run() {       
        while(running == true) {
            start = System.currentTimeMillis();
            if(gpioHub != null && gpioHub.IsPrepped()) {
                try {
                    gpioHub.GetState();
                } catch (IOException e) {
                    e.printStackTrace();
                    running = false;
                }
                
                //down, up, left, right
                if(gpioHub.GetDown()) {
                    gamePad.ProcessDpadPress(GameSettings.DOWN);
                } else if(gpioHub.GetUp()) {
                    gamePad.ProcessDpadPress(GameSettings.UP);                    
                } else if(gpioHub.GetLeft()) {
                    gamePad.ProcessDpadPress(GameSettings.LEFT);                    
                } else if(gpioHub.GetRight()) {
                    gamePad.ProcessDpadPress(GameSettings.RIGHT);                    
                }
                
                //a, b
                if(gpioHub.GetA()) {
                    gamePad.ProcessAClick();
                } else if(gpioHub.GetB()) {
                    gamePad.ProcessBClick();
                }
            }
            stop = System.currentTimeMillis();
            
            diff = stop - start;                    //Total time in ms to get gpio data
            diff = pollingIntervalMs - diff;        //Difference in actual time and polling time
            
            if(pollingIntervalMs - diff > 0) {
                try {
                    Thread.sleep(diff);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }    
}

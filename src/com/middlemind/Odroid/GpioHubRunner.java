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

    public final long GetPollingIntervalMs() {
        return pollingIntervalMs;
    }

    public final void SetPollingIntervalMs(long intervalMs) {
        pollingIntervalMs = intervalMs;
    }

    public final boolean IsRunning() {
        return running;
    }

    public final void SetRunning(boolean b) {
        running = b;
    }

    public final void PollGpio() {
        if(gpioHub != null && gpioHub.IsPrepped()) {
            try {
                gpioHub.GetState();
            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }

            //down, up, left, right
            //check dpad pressed state
            if(gpioHub.GetDownPressed()) {
                gamePad.ProcessDpadPress(GameSettings.DOWN);
            }
            
            if(gpioHub.GetUpPressed()) {
                gamePad.ProcessDpadPress(GameSettings.UP);                    
            }
            
            if(gpioHub.GetLeftPressed()) {
                gamePad.ProcessDpadPress(GameSettings.LEFT);                    
            }
            
            if(gpioHub.GetRightPressed()) {
                gamePad.ProcessDpadPress(GameSettings.RIGHT);                    
            }

            //check dpad released state
            if(gpioHub.GetDownReleased()) {
                gamePad.ProcessDpadPress(GameSettings.DOWN);
            }
            
            if(gpioHub.GetUpReleased()) {
                gamePad.ProcessDpadRelease(GameSettings.UP);                    
            }
            
            if(gpioHub.GetLeftReleased()) {
                gamePad.ProcessDpadRelease(GameSettings.LEFT);                    
            }
            
            if(gpioHub.GetRightReleased()) {
                gamePad.ProcessDpadRelease(GameSettings.RIGHT);                    
            }            
            
            //check dpad clicked state
            if(gpioHub.GetDownClicked()) {
                gamePad.ProcessDpadPress(GameSettings.DOWN);
            }
            
            if(gpioHub.GetUpClicked()) {
                gamePad.ProcessDpadRelease(GameSettings.UP);                    
            }
            
            if(gpioHub.GetLeftClicked()) {
                gamePad.ProcessDpadRelease(GameSettings.LEFT);                    
            }
            
            if(gpioHub.GetRightClicked()) {
                gamePad.ProcessDpadRelease(GameSettings.RIGHT);                    
            }            
            
            //a, b
            //check a,b pressed state
            if(gpioHub.GetAPressed()) {
                gamePad.ProcessAPress();
            }
            
            if(gpioHub.GetBPressed()) {
                gamePad.ProcessBPress();
            }

            //check a,b released state            
            if(gpioHub.GetAReleased()) {
                gamePad.ProcessARelease();
            }
            
            if(gpioHub.GetBReleased()) {
                gamePad.ProcessBRelease();
            }            
            
            //check a,b clicked state            
            if(gpioHub.GetAClicked()) {
                gamePad.ProcessAClick();
            }
            
            if(gpioHub.GetBClicked()) {
                gamePad.ProcessBClick();
            }
            
            gpioHub.CleanUp();
        }        
    }
    
    @Override
    public final void run() {       
        while(running == true) {
            start = System.currentTimeMillis();
            PollGpio();
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

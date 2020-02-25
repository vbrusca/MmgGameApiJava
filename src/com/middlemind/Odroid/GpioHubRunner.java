package com.middlemind.Odroid;

import java.io.IOException;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/05/2020
 */
public class GpioHubRunner implements Runnable {
    protected GpioHub gpioHub;
    protected long pollingIntervalMs = 10;
    protected long start;
    protected long stop;
    protected long diff;
    protected boolean running = false;
    protected GamePadSimple gamePad;
    
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

    @SuppressWarnings("CallToPrintStackTrace")
    public void PollGpio() {
        if(gpioHub != null && gpioHub.IsPrepped() && gpioHub.IsGpioEnabled()) {
            try {
                gpioHub.GetState();
            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }

            //down, up, left, right
            //check dpad pressed state
            if(gpioHub.GetDownPressed()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadPress(GameSettings.DOWN);
                }
            }
            
            if(gpioHub.GetUpPressed()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadPress(GameSettings.UP);                    
                }
            }
            
            if(gpioHub.GetLeftPressed()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadPress(GameSettings.LEFT);                    
                }
            }
            
            if(gpioHub.GetRightPressed()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadPress(GameSettings.RIGHT);                    
                }
            }

            //check dpad released state
            if(gpioHub.GetDownReleased()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadPress(GameSettings.DOWN);
                }
            }
            
            if(gpioHub.GetUpReleased()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadRelease(GameSettings.UP);                    
                }
            }
            
            if(gpioHub.GetLeftReleased()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadRelease(GameSettings.LEFT);                    
                }
            }
            
            if(gpioHub.GetRightReleased()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadRelease(GameSettings.RIGHT);                    
                }
            }            
            
            //check dpad clicked state
            if(gpioHub.GetDownClicked()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadPress(GameSettings.DOWN);
                }
            }
            
            if(gpioHub.GetUpClicked()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadRelease(GameSettings.UP);                    
                }
            }
            
            if(gpioHub.GetLeftClicked()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadRelease(GameSettings.LEFT);                    
                }
            }
            
            if(gpioHub.GetRightClicked()) {
                if(gamePad != null) {
                    gamePad.ProcessDpadRelease(GameSettings.RIGHT);                    
                }
            }            
            
            //a, b
            //check a,b pressed state
            if(gpioHub.GetAPressed()) {
                if(gamePad != null) {
                    gamePad.ProcessAPress();
                }
            }
            
            if(gpioHub.GetBPressed()) {
                if(gamePad != null) {
                    gamePad.ProcessBPress();
                }
            }

            //check a,b released state            
            if(gpioHub.GetAReleased()) {
                if(gamePad != null) {
                    gamePad.ProcessARelease();
                }
            }
            
            if(gpioHub.GetBReleased()) {
                if(gamePad != null) {
                    gamePad.ProcessBRelease();
                }
            }            
            
            //check a,b clicked state            
            if(gpioHub.GetAClicked()) {
                if(gamePad != null) {
                    gamePad.ProcessAClick();
                }
            }
            
            if(gpioHub.GetBClicked()) {
                if(gamePad != null) {
                    gamePad.ProcessBClick();
                }
            }
            
            gpioHub.CleanUp();
        }        
    }
    
    @Override
    @SuppressWarnings({"CallToPrintStackTrace", "SleepWhileInLoop"})
    public void run() {       
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

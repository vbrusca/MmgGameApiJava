package net.middlemind.MmgGameApiJava.MmgCore;

import java.io.IOException;

/**
 * The GpioHubRunner class is a threaded class that is used to run a GpioHub and determine the state of each
 * of the GPIO pins at interval.
 * 
 * @author Victor G. Brusca, Middlemind Games
 * 01/05/2020
 */
public class GpioHubRunner implements Runnable {
    /**
     * A reference to the GpioHub to the GpioHubRunner will monitor.
     */
    public GpioHub gpioHub;
    
    /**
     * The interval in ms that the GpioHubRunner will update the state of the GPIO pins.
     */
    public long pollingIntervalMs = 10;
    
    /**
     * A class field used to mark the start time of a polling interval.
     */
    public long start;
    
    /**
     * A class field used to mark the stop time of a polling interval.
     */
    public long stop;
    
    /**
     * A class field used to mark a time difference for measuring polling intervals.
     */
    public long diff;
    
    /**
     * A class field used to control if the GpioHubRunner is running.
     */
    public boolean running = false;
    
    /**
     * An instance of a class that implements that GamePadSimple interface, used to call dpad, A, B, event
     * methods.
     */
    public GamePadSimple gamePad;
    
    /**
     * The main GpioHubRunner constructor that sets the class up for polling the GpioHub for GPIO pin states and determines
     * the GamePadSimple interface for calling button event methods.
     * 
     * @param hub               A method argument that sets the GpioHub of GPIO pins to monitor.
     * @param intervalMs        A time interval in ms to update the state of the GpioHub's GPIO pins.
     * @param gamePadSimple     A class that implements the GamePadSimple interface used for calling button event callback methods.
     */
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

    /**
     * A method that returns the polling interval in ms that the GpioHubRunner uses to monitor GPIO pins.
     * 
     * @return      Returns a long value that represents the number of ms of the polling interval.
     */
    public long GetPollingIntervalMs() {
        return pollingIntervalMs;
    }

    /**
     * A method that sets the current polling interval in ms that the GpioHubRUnner uses to monitor GPIO pins.
     * 
     * @param intervalMs        A method argument that sets the polling interval that the GpioHubRunner uses to monitor GPIO pins.
     */
    public void SetPollingIntervalMs(long intervalMs) {
        pollingIntervalMs = intervalMs;
    }

    /**
     * A method that returns the current execution control variable value.
     * 
     * @return      Returns a boolean indicating if the thread is currently running or not.
     */
    public boolean IsRunning() {
        return running;
    }

    /**
     * A method that sets the current execution control variable value.
     * 
     * @param b     A method argument that sets the value of the thread execution control variable.
     */
    public void SetRunning(boolean b) {
        running = b;
    }

    /**
     * The PollGpio method is used to update the GPIO pin state and call event handler methods from the GamePadSimple
     * interface. The last step in the process is to call the CleanUp method of the GpioHub class.
     */
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
    
    /**
     * The run method of the Runnable interface. Called when a new thread is created and started.
     */
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

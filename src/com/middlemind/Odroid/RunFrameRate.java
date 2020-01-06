package com.middlemind.Odroid;

/**
 * The frame rate worker thread, runs the main loop while tracking frame rate
 * information. Created on August 1, 2015, 10:57 PM by Middlemind Games Created
 * by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public final class RunFrameRate implements Runnable {

    /**
     * The MainFrame that houses the game, connection between JFrame, JPanel and
     * the game.
     */
    private final MainFrame mf;

    /**
     * Target frames per second.
     */
    private final long tFps;

    /**
     * Target frame time.
     */
    private final long tFrameTime;

    /**
     * Actual frames per second.
     */
    private long aFps;
    private long rFps;

    /**
     * Last frame stop time.
     */
    private long frameStart;

    /**
     * Last frame start time.
     */
    private long frameStop;

    /**
     * Frame time.
     */
    private long frameTime;

    /**
     * Frame time difference from actual time to target time. Used to sleep the
     * few milliseconds between the target time and the actual time if the
     * actual time is less than the target time.
     */
    private long frameTimeDiff;

    /**
     * Pauses the current render loop.
     */
    public static boolean PAUSE = false;

    /**
     * Exits the current render loop.
     */
    public static boolean RUNNING = true;

    /**
     * Constructor, sets the MainFrame, JFrame, and the target frames per
     * second.
     *
     * @param Mf The MainFrame, JFrame for this game.
     * @param Fps The target frames per second to use for the main loop.
     */
    public RunFrameRate(MainFrame Mf, long Fps) {
        mf = Mf;
        tFps = Fps;
        tFrameTime = (1000 / tFps);
        Helper.wr("Target Frame Rate: " + tFps);
        Helper.wr("Target Frame Time: " + tFrameTime);
    }

    /**
     * Pauses the main loop.
     */
    public final static void Pause() {
        PAUSE = true;
    }

    /**
     * Gets the pause status of the main loop.
     *
     * @return The pause status of the main loop.
     */
    public final static boolean IsPaused() {
        return PAUSE;
    }

    /**
     * UnPauses the main loop.
     */
    public final static void UnPause() {
        PAUSE = false;
    }

    /**
     * Stops the main loop from running.
     */
    public final static void StopRunning() {
        RUNNING = false;
    }

    /**
     * Gets the running status of the main loop.
     *
     * @return True if running, false otherwise.
     */
    public final static boolean IsRunning() {
        return RUNNING;
    }

    /**
     * Starts running the main loop but only if run is called again. Once the
     * main loop exits the run method returns.
     */
    public final static void StartRunning() {
        RUNNING = true;
    }

    /**
     * Gets the actual frame rate of the game.
     *
     * @return The game's frame rate.
     */
    public final long GetActualFrameRate() {
        return aFps;
    }

    /**
     * Gets the target frame rate of the game.
     *
     * @return The game's target frame rate.
     */
    public final long GetTargetFrameRate() {
        return tFps;
    }

    /**
     * The main drawing loop of the game.
     */
    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public final void run() {
        while (RunFrameRate.RUNNING == true) {
            frameStart = System.currentTimeMillis();

            if (RunFrameRate.PAUSE == false) {
                mf.Redraw();
            }

            frameStop = System.currentTimeMillis();
            frameTime = (frameStop - frameStart) + 1;
            aFps = (1000 / frameTime);

            frameTimeDiff = tFrameTime - frameTime;
            if (frameTimeDiff > 0) {
                try {
                    Thread.sleep((int) frameTimeDiff);
                } catch (Exception e) {
                    Helper.wrErr(e);
                }
            }

            frameStop = System.currentTimeMillis();
            frameTime = (frameStop - frameStart) + 1;
            rFps = (1000 / frameTime);
            mf.SetFrameRate(aFps, rFps);
        }
    }
}

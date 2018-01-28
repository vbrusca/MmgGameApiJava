package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * A class that represents a splash screen. Created on June 1, 2005, 10:57 PM by
 * Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class MmgSplashScreen extends MmgGameScreen implements MmgUpdateHandler {

    /**
     * Inner class used to time how long to display the splash screen. Created
     * on June 1, 2005, 10:57 PM by Middlemind Games Created by Middlemind Games
     *
     * @author Victor G. Brusca
     */
    public class MmgSplashScreenTimer implements Runnable {

        /**
         * The display time to show the given splash screen.
         */
        private final long displayTime;

        /**
         * The update handler to handle update event messages.
         */
        private MmgUpdateHandler update;

        /**
         * Generic constructor sets the display time in ms.
         *
         * @param DisplayTime Splash screen display time in ms.
         */
        public MmgSplashScreenTimer(long DisplayTime) {
            displayTime = DisplayTime;
        }

        /**
         * Sets the update handler for this runnable. Calls back to the update
         * handler once the display time has passed.
         *
         * @param Update A class that supports the MmgUpdateHandler interface.
         */
        public void SetUpdateHandler(MmgUpdateHandler Update) {
            update = Update;
        }

        /**
         * Start the display wait thread.
         */
        @Override
        public void run() {
            try {
                Thread.sleep(displayTime);

                if (update != null) {
                    update.MmgHandleUpdate(null);
                }
            } catch (Exception e) {

            }
        }

    }

    /**
     * The display time to show this splash screen.
     */
    private int displayTime;

    /**
     * The update handler to handle update events.
     */
    private MmgUpdateHandler update;

    /**
     * The default display time.
     */
    public final int DEFAULT_DISPLAY_TIME_MS = 3000;

    /**
     * Constructor that sets the splash screen display time.
     *
     * @param DisplayTime The display time for this splash screen, in
     * milliseconds.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgSplashScreen(int DisplayTime) {
        super();
        displayTime = DisplayTime;
    }

    /**
     * Constructor that sets the splash screen attributes based on the values of
     * the given argument.
     *
     * @param mls The display time in milliseconds.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgSplashScreen(MmgSplashScreen mls) {
        super(mls);
        SetBackground(mls.GetBackground());
        SetFooter(mls.GetFooter());
        SetHeader(mls.GetHeader());
        SetHeight(mls.GetHeight());
        SetIsVisible(mls.GetIsVisible());
        SetLeftCursor(mls.GetLeftCursor());
        SetRightCursor(mls.GetRightCursor());
        SetWidth(mls.GetWidth());
        displayTime = DEFAULT_DISPLAY_TIME_MS;
    }

    /**
     * Constructor that sets the display time to the default display time.
     */
    public MmgSplashScreen() {
        super();
        displayTime = DEFAULT_DISPLAY_TIME_MS;
    }

    /**
     * Starts the splash screen display.
     */
    public void StartDisplay() {
        MmgSplashScreenTimer s = new MmgSplashScreenTimer(displayTime);
        s.SetUpdateHandler(this);
        Runnable r = s;
        Thread t = new Thread(r);
        t.start();
    }

    /**
     * Sets the update event handler.
     *
     * @param Update The update event handler.
     */
    public void SetUpdateHandler(MmgUpdateHandler Update) {
        update = Update;
    }

    /**
     * Handles update events.
     *
     * @param obj The update event to handle.
     */
    @Override
    public void MmgHandleUpdate(Object obj) {
        if (update != null) {
            update.MmgHandleUpdate(obj);
        }
    }

    /**
     * Clone this object.
     *
     * @return A clone of this object.
     */
    @Override
    public MmgObj Clone() {
        MmgSplashScreen ret = new MmgSplashScreen(this);
        return (MmgObj) ret;
    }

    /**
     * Sets the background centered.
     *
     * @param b The object to set as a centered background.
     */
    @Override
    public void SetCenteredBackground(MmgObj b) {
        MmgHelper.CenterHorAndVert(b);
        super.SetBackground(b);
    }

    /**
     * Gets the current display time.
     *
     * @return The current display time.
     */
    public int GetDisplayTime() {
        return displayTime;
    }

    /**
     * Sets the current display time.
     *
     * @param DisplayTime The current display time.
     */
    public void SetDisplayTime(int DisplayTime) {
        displayTime = DisplayTime;
    }

    /**
     * Draws this object to the screen.
     *
     * @param p The MmgPen to draw this object with.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (GetIsVisible() == true) {
            super.MmgDraw(p);

        } else {
            //do nothing
        }
    }
}

package com.middlemind.Odroid;

import com.middlemind.Odroid.GamePanel.GameStates;
import com.middlemind.Odroid.GenericEventHandler;
import com.middlemind.Odroid.GenericEventMessage;
import com.middlemind.Odroid.Helper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgSplashScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgUpdateHandler;

/**
 * A game screen object, ScreenSplash, that extends the MmgGameScreen base
 * class. This game screen is for displaying a splash screen before the game
 * loading screen. Created on August 1, 2015, 10:57 PM by Middlemind Games.
 * Created by Middlemind Games.
 *
 * @author Victor G. Brusca
 */
public class ScreenSplash extends MmgSplashScreen implements MmgUpdateHandler {

    /**
     * Event display time complete id.
     */
    public static int EVENT_DISPLAY_COMPLETE = 0;

    /**
     * The game state this screen has.
     */
    protected final GameStates state;

    /**
     * Event handler for firing generic events. Events would fire when the
     * screen has non UI actions to broadcast.
     */
    protected GenericEventHandler handler;

    /**
     * The GamePanel that owns this game screen. Usually a JPanel instance that
     * holds a reference to this game screen object.
     */
    protected final GamePanel owner;

    /**
     * Constructor, sets the game state associated with this screen, and sets
     * the owner GamePanel instance.
     *
     * @param State The game state of this game screen.
     * @param Owner The owner of this game screen.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ScreenSplash(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        state = State;
        owner = Owner;
        SetUpdateHandler(this);
        Helper.wr("ScreenSplash: Constructor");
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("Odroid.ScreenSplash.SetGenericEventHandler");
        handler = Handler;
    }

    public GenericEventHandler GetGenericEventHandler() {
        return handler;
    }
        
    /**
     * Public method that fires the local generic event, the listener will
     * receive a display complete event.
     *
     * @param obj The information payload to send along with this message.
     */
    @Override
    public void MmgHandleUpdate(Object obj) {
        Helper.wr("Odroid.ScreenSplash.MmgHandleUpdate");
        if (handler != null) {
            handler.HandleGenericEvent(new GenericEventMessage(ScreenSplash.EVENT_DISPLAY_COMPLETE, null, GetGameState()));
        }
    }

    /**
     * Loads all the resources needed to display this game screen.
     */
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());

        MmgBmp tB = null;
        MmgPen p;
        p = new MmgPen();
        p.SetCacheOn(false);

        tB = Helper.GetBasicBmp("../cfg/drawable/logo_large.jpg");
        if (tB != null) {
            SetCenteredBackground(tB);
        }

        ready = true;
        pause = false;
        Helper.wr("ScreenTest: LoadResources");
    }

    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);
        ClearObjs();
        ready = false;
    }

    /**
     * Returns the game state of this game screen.
     *
     * @return The game state of this game screen.
     */
    public GameStates GetGameState() {
        return state;
    }

    /**
     * The main drawing routine.
     *
     * @param p An MmgPen object to use for drawing this game screen.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (pause == false && GetIsVisible() == true) {
            super.MmgDraw(p);
        } else {
            //do nothing
        }
    }
}

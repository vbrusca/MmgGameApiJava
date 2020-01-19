package net.middlemind.MmgGameApiJava.PlayGround;

import com.middlemind.Odroid.GamePanel;
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
public final class ScreenSimple extends MmgSplashScreen implements MmgUpdateHandler {

    /**
     * Event display time complete id.
     */
    public static int EVENT_DISPLAY_COMPLETE = 0;

    /**
     * The game state this screen has.
     */
    private final GameStates state;

    /**
     * Event handler for firing generic events. Events would fire when the
     * screen has non UI actions to broadcast.
     */
    private GenericEventHandler handler;

    /**
     * The GamePanel that owns this game screen. Usually a JPanel instance that
     * holds a reference to this game screen object.
     */
    private final GamePanel owner;

    /**
     * Constructor, sets the game state associated with this screen, and sets
     * the owner GamePanel instance.
     *
     * @param State The game state of this game screen.
     * @param Owner The owner of this game screen.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ScreenSimple(GameStates State, GamePanel Owner) {
        super();
        pause = false;
        ready = false;
        state = State;
        owner = Owner;
        SetUpdateHandler(this);
    }

    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler A class that implements the GenericEventHandler interface.
     */
    public final void SetGenericEventHandler(GenericEventHandler Handler) {
        handler = Handler;
    }

    /**
     * Public method that fires the local generic event, the listener will
     * receive a display complete event.
     *
     * @param obj The information payload to send along with this message.
     */
    @Override
    public final void MmgHandleUpdate(Object obj) {
        if (handler != null) {
            handler.HandleGenericEvent(new GenericEventMessage(ScreenSimple.EVENT_DISPLAY_COMPLETE, null, GetGameState()));
        }
    }

    /**
     * Loads all the resources needed to display this game screen.
     */
    @SuppressWarnings("UnusedAssignment")
    public final void LoadResources() {
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
    }

    /**
     * Unloads resources needed to display this game screen.
     */
    public final void UnloadResources() {
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
    public final GameStates GetGameState() {
        return state;
    }

    /**
     * The main drawing routine.
     *
     * @param p An MmgPen object to use for drawing this game screen.
     */
    @Override
    public final void MmgDraw(MmgPen p) {
        if (pause == false && GetIsVisible() == true) {
            super.MmgDraw(p);
        } else {
            //do nothing
        }
    }
}

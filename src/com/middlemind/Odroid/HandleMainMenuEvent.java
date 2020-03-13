package com.middlemind.Odroid;

import net.middlemind.MmgGameApiJava.MmgBase.MmgApiUtils;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEvent;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEventHandler;

/**
 * Handles events that originate from the main menu screen. Implements the
 * MmgEventHandler. Created on August 1, 2015, 10:57 PM by Middlemind Games
 * Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public final class HandleMainMenuEvent implements MmgEventHandler {

    /**
     * The about screen object, ScreenAbout, this event handler belongs to.
     */
    private final ScreenMainMenu cApp;

    /**
     * The game panel object, GamePanel, that owns the about screen object,
     * ScreenAbout.
     */
    private final GamePanel owner;

    /**
     * Supported event type.
     */
    public static final int MAIN_MENU_EVENT_TYPE = 0;

    /**
     * Supported event id.
     */
    public static final int MAIN_MENU_EVENT_START_GAME = 0;

    /**
     * Supported event id.
     */
    public static final int MAIN_MENU_EVENT_SETTINGS = 1;

    /**
     * Supported event id.
     */
    public static final int MAIN_MENU_EVENT_ABOUT = 2;

    /**
     * Supported event id.
     */
    public static final int MAIN_MENU_EVENT_HELP = 3;

    /**
     * Supported event id.
     */
    public static final int MAIN_MENU_EVENT_EXIT_GAME = 4;    
    
    /**
     * Constructor that sets the about screen object, ScreenAbout, owner and the
     * GamePanel that owns the about screen.
     *
     * @param CApp The about screen object, ScreenABout, that this event handler
     * belongs too.
     * @param Owner The game panel, GamePanel, that the about screen belongs
     * too.
     */
    public HandleMainMenuEvent(ScreenMainMenu CApp, GamePanel Owner) {
        cApp = CApp;
        owner = Owner;
    }

    /**
     * Event handler call back, when a UI action event occurs on the owner this
     * event handler can react to that event and either call methods in the
     * screen object that this class belongs to or it can call methods in the
     * JPanel that owns the screen, i.e. switch the current game state.
     *
     * @param e An MmgEvent object.
     */
    @Override
    public final void MmgHandleEvent(MmgEvent e) {
        MmgApiUtils.wr("MmgHandleMainMenuEvent Found Event Id: " + e.GetEventId());
        if (owner != null) {
            if (e.GetEventId() == HandleMainMenuEvent.MAIN_MENU_EVENT_START_GAME) {
                owner.SwitchGameState(GamePanel.GameStates.MAIN_GAME);

            } else if (e.GetEventId() == HandleMainMenuEvent.MAIN_MENU_EVENT_EXIT_GAME) {
                System.exit(0);
                
            }
        }
    }
}

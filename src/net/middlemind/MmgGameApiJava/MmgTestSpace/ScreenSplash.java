package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventHandler;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 02/19/2020
 */
public class ScreenSplash extends net.middlemind.MmgGameApiJava.MmgCore.ScreenSplash {
    
    public ScreenSplash(GamePanel.GameStates State, GamePanel Owner) {
        super(State, Owner);
    }
  
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("TestSpace.ScreenSplash.SetGenericEventHandler");
        handler = Handler;
    }    
    
    @Override
    public void MmgHandleUpdate(Object obj) {
        Helper.wr("TestSpace.ScreenSplash.MmgHandleUpdate");
        if (handler != null) {
            handler.HandleGenericEvent(new GenericEventMessage(net.middlemind.MmgGameApiJava.MmgCore.ScreenSplash.EVENT_DISPLAY_COMPLETE, null, GetGameState()));
        }
    }    
}

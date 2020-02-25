package net.middlemind.MmgGameApiJava.TestSpace;

import com.middlemind.Odroid.GamePanel;
import com.middlemind.Odroid.GenericEventHandler;
import com.middlemind.Odroid.GenericEventMessage;
import com.middlemind.Odroid.Helper;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 02/19/2020
 */
public class ScreenSplash extends com.middlemind.Odroid.ScreenSplash {
    
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
            handler.HandleGenericEvent(new GenericEventMessage(com.middlemind.Odroid.ScreenSplash.EVENT_DISPLAY_COMPLETE, null, GetGameState()));
        }
    }    
}

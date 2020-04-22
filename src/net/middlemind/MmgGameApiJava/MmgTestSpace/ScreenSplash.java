package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventHandler;

/**
 * Created by Middlemind Games 02/19/2020
 * 
 * @author Victor G. Brusca, Middlemind Games
 */
public class ScreenSplash extends net.middlemind.MmgGameApiJava.MmgCore.ScreenSplash {
    
    /**
     * 
     * 
     * @param State
     * @param Owner 
     */
    public ScreenSplash(GamePanel.GameStates State, GamePanel Owner) {
        super(State, Owner);
    }
  
    /**
     * 
     * 
     * @param Handler 
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        Helper.wr("ScreenSplash.SetGenericEventHandler");
        handler = Handler;
    }    
    
    /**
     * 
     * 
     * @param obj 
     */
    @Override
    public void MmgHandleUpdate(Object obj) {
        Helper.wr("ScreenSplash.MmgHandleUpdate");
        if (handler != null) {
            handler.HandleGenericEvent(new GenericEventMessage(net.middlemind.MmgGameApiJava.MmgCore.ScreenSplash.EVENT_DISPLAY_COMPLETE, null, GetGameState()));
        }
    }    
}

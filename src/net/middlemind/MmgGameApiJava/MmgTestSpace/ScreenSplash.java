package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgGenericEventHandler;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 02/19/2020
 */
public class ScreenSplash extends net.middlemind.MmgGameApiJava.MmgCore.ScreenSplash {
    
    public ScreenSplash(GamePanel.GameStates State, GamePanel Owner) {
        super(State, Owner);
    }
  
    public void SetGenericEventHandler(MmgGenericEventHandler Handler) {
        Helper.wr("TestSpace.ScreenSplash.SetGenericEventHandler");
        handler = Handler;
    }    
    
    @Override
    public void MmgHandleUpdate(Object obj) {
        Helper.wr("TestSpace.ScreenSplash.MmgHandleUpdate");
        if (handler != null) {
            handler.HandleGenericEvent(new MmgGenericEventMessage(net.middlemind.MmgGameApiJava.MmgCore.ScreenSplash.EVENT_DISPLAY_COMPLETE, null, GetGameState()));
        }
    }    
}

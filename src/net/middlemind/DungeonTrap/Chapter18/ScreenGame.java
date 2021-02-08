package net.middlemind.DungeonTrap.Chapter18;

import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgObj;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;

/**
 * A game screen object, ScreenGame, that extends the MmgGameScreen base
 * class. This class is for testing new UI widgets, etc.
 *
 * @author Victor G. Brusca
 * 03/15/2020
 */
public class ScreenGame {
 
    public static int GAME_TOP = MmgScreenData.GetGameTop();
    public static int BOARD_TOP = GAME_TOP + MmgHelper.ScaleValue(106);    
    public static int GAME_BOTTOM = MmgScreenData.GetGameBottom();
    public static int BOARD_BOTTOM = GAME_BOTTOM - MmgHelper.ScaleValue(56);    
    public static int GAME_LEFT = MmgScreenData.GetGameLeft();
    public static int BOARD_LEFT = GAME_LEFT + MmgHelper.ScaleValue(20);
    public static int GAME_RIGHT = MmgScreenData.GetGameRight();
    public static int BOARD_RIGHT = GAME_RIGHT - MmgHelper.ScaleValue(132);    
    public static int GAME_WIDTH = GAME_RIGHT - GAME_LEFT;   
    public static int BOARD_WIDTH = BOARD_RIGHT - BOARD_LEFT;
    public static int GAME_HEIGHT = GAME_BOTTOM - GAME_TOP;
    public static int BOARD_HEIGHT = BOARD_BOTTOM - BOARD_TOP;     
    
    /**
     * Converts the given speed to a uniform speed per frame so that the game movement will
     * be the same even if the game runs at different frame rates.
     * 
     * @param speed     The target speed to convert to a speed per frame.
     * 
     * @return          A converted speed that represents the speed per frame of the given input speed. 
     */
    public static int GetSpeedPerFrame(int speed) {
        return (int)(speed/(DungeonTrap.FPS - 4));        
    }
    
    /**
     * Removes the specified object from the game screen.
     * 
     * @param obj       The object to remove from this game screen.
     */
    public void RemoveObj(MmgObj obj) {
        //TODO
    }
}

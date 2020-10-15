package net.middlemind.MmgGameApiJava.MmgCore;

import java.util.Arrays;
import net.java.games.input.test.ControllerReadTest;
import net.middlemind.MmgGameApiJava.MmgCore.MmgApiGame;
import net.middlemind.MmgGameApiJava.MmgTestSpace.MmgTestScreens;

/**
 * A centralized main class that can run the different executable classes in the library.
 * 
 * @author Victor G. Brusca, Middlemind Games
 */
public class MmgCentralMain {
    
    /**
     * Static main entry point.
     * 
     * @param args  String array of arguments, append the executable name before its arguments.
     */
    public static void main(String[] args) {
        if(args == null || args.length == 0) {
            System.out.println("No arguments found.");
            return;
        }
            
        if(args[0] != null && args[0].toLowerCase().equals("controllerreadtest")) {
            ControllerReadTest.main(Arrays.copyOfRange(args, 1, args.length));
            
        } else if(args[0] != null && args[0].toLowerCase().equals("mmgtestspace")) {
            MmgTestScreens.main(Arrays.copyOfRange(args, 1, args.length));
            
        } else if(args[0] != null && args[0].toLowerCase().equals("mmgapigame")) {
            MmgApiGame.main(Arrays.copyOfRange(args, 1, args.length));
                        
        }
    }
}

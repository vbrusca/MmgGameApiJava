package net.middlemind.MmgGameApiJava.MmgCore;

import java.util.Arrays;
import net.java.games.input.test.ControllerReadTest;
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
                        
        } else if(args[0] != null && args[0].toLowerCase().equals("chapter13")) {
            net.middlemind.PongClone.Chapter13.PongClone.main(Arrays.copyOfRange(args, 1, args.length));            

        } else if(args[0] != null && args[0].toLowerCase().equals("chapter14")) {
            net.middlemind.PongClone.Chapter14.PongClone.main(Arrays.copyOfRange(args, 1, args.length));            
            
        } else if(args[0] != null && (args[0].toLowerCase().equals("chapter15") || args[0].toLowerCase().equals("chapter15_completegame"))) {
            net.middlemind.PongClone.Chapter15_CompleteGame.PongClone.main(Arrays.copyOfRange(args, 1, args.length));
            
        } else if(args[0] != null && args[0].toLowerCase().equals("chapter16")) {
            net.middlemind.DungeonTrap.ChapterE1.DungeonTrap.main(Arrays.copyOfRange(args, 1, args.length));            

        } else if(args[0] != null && args[0].toLowerCase().equals("chapter17")) {
            net.middlemind.DungeonTrap.ChapterE2.DungeonTrap.main(Arrays.copyOfRange(args, 1, args.length));            
                       
        } else if(args[0] != null && args[0].toLowerCase().equals("chapter18")) {
            net.middlemind.DungeonTrap.ChapterE3.DungeonTrap.main(Arrays.copyOfRange(args, 1, args.length));                        
        
        } else if(args[0] != null && args[0].toLowerCase().equals("chapter19")) {
            net.middlemind.DungeonTrap.ChapterE4.DungeonTrap.main(Arrays.copyOfRange(args, 1, args.length));                    

        } else if(args[0] != null && args[0].toLowerCase().equals("chapter19_demoscreen")) {
            net.middlemind.DungeonTrap.ChapterE4_DemoScreen.DungeonTrap.main(Arrays.copyOfRange(args, 1, args.length));            

        } else if(args[0] != null && args[0].toLowerCase().equals("chapter20_phase1")) {
            net.middlemind.DungeonTrap.ChapterE5_Phase1.DungeonTrap.main(Arrays.copyOfRange(args, 1, args.length));                        

        } else if(args[0] != null && args[0].toLowerCase().equals("chapter20_phase2")) {
            net.middlemind.DungeonTrap.ChapterE5_Phase2.DungeonTrap.main(Arrays.copyOfRange(args, 1, args.length));                        

        } else if(args[0] != null && (args[0].toLowerCase().equals("chapter20_phase3") || args[0].toLowerCase().equals("chapter20_phase3_completegame"))) {
            net.middlemind.DungeonTrap.ChapterE5_Phase3_CompleteGame.DungeonTrap.main(Arrays.copyOfRange(args, 1, args.length));                        
            
        } else {
            MmgTestScreens.main(Arrays.copyOfRange(args, 1, args.length));
        
        }
    }
}

package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.DatConstantsEntry;
import net.middlemind.MmgGameApiJava.MmgCore.GameSettings;
import net.middlemind.MmgGameApiJava.MmgCore.GameSettingsImporter;
import java.lang.reflect.Field;
import javax.swing.JFrame;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import net.middlemind.MmgGameApiJava.MmgCore.OdroidGame;
import net.middlemind.MmgGameApiJava.MmgCore.RunFrameRate;

/**
 * Java swing game that runs the Tyre DAT file. MAIN ENTRY POINT Created on
 * August 1, 2015, 10:57 PM by Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public final class MmgTestSpace {

    /**
     * The main JPanel that houses the different game screens.
     */
    public static MainFrame mf;

    /**
     * The frame rate worker thread.
     */
    public static RunFrameRate fr;

    /**
     * The thread that is associated with the frame rate worker thread.
     */
    public static Thread t;

    /**
     * The target window width.
     */
    public static int WIN_WIDTH = 858;

    /**
     * The target window height.
     */
    public static int WIN_HEIGHT = 600;

    /**
     * The game panel width.
     */
    public static int PANEL_WIDTH = 854;

    /**
     * The game panel height.
     */
    public static int PANEL_HEIGHT = 596; //416;

    /**
     * The game width.
     */
    public static int GAME_WIDTH = 854;

    /**
     * The game height.
     */
    public static int GAME_HEIGHT = 416;    
    
    /**
     * The frame rate for the game, frames per second.
     */
    public static long FPS = 16l;

    public static String DAT_MAP_FILE = "../cfg/data_map/chapter2.xml";
    public static String DAT_FILE = "../cfg/data/chapter2.dat";

    /**
     * Base engine config files.
     */
    public static String ENGINE_CONFIG_FILE = "../cfg/engine_config.xml";

    /**
     * The GamePanel used to render the game in a MainFrame instance.
     */     
    public static GamePanel pnlGame;
    
    /**
     * A copy of the command line arguments passed to the Java application.
     */       
    public static String[] ARGS = null;    
        
    /**
     * Method that searches an array for a string match.
     *
     * @param v     The string to find a match for.
     * @param s     The array of string to search through.
     * 
     * @return      The command line argument that matched the test string, v.
     */
    public static String ArrayHasEntryLike(String v, String[] s) {
        if(v == null || s == null) {
            return null;
        }
        
        String tmp = v.toLowerCase();
        int len = s.length;
        for (int i = 0; i < len; i++) {
            if(s[i] != null) {
                if (s[i].toLowerCase().contains(tmp) == true || s[i].toLowerCase().equals(tmp) == true) {
                    return s[i];
                }
            }
        }
        return null;
    }

    /**
     * Sets the value of the field specified by the field reflection object.
     *
     * @param ent Entry object that wraps the XML entry.
     * @param f Class member that needs to be updated.
     * 
     * @throws Exception
     */
    public static void SetField(DatConstantsEntry ent, Field f) throws Exception {
        if (ent.type != null && ent.type.equals("int") == true) {
            f.setInt(null, Integer.parseInt(ent.val));

        } else if (ent.type != null && ent.type.equals("long") == true) {
            f.setLong(null, Long.parseLong(ent.val));

        } else if (ent.type != null && ent.type.equals("float") == true) {
            f.setFloat(null, Float.parseFloat(ent.val));

        } else if (ent.type != null && ent.type.equals("double") == true) {
            f.setDouble(null, Double.parseDouble(ent.val));

        } else if (ent.type != null && ent.type.equals("short") == true) {
            f.setShort(null, Short.parseShort(ent.val));

        } else if (ent.type != null && ent.type.equals("string") == true) {
            f.set(null, ent.val);

        } else if (ent.type != null && ent.type.equals("bool") == true) {
            f.setBoolean(null, Boolean.parseBoolean(ent.val));

        } else {
            f.setInt(null, Integer.parseInt(ent.val));
        }
    }

    /**
     * Static access method.
     *
     * @param args The command line arguments
     */
    public static final void main(String[] args) {
        //Store program arguments for future reference
        ARGS = args;        
        if (args != null && args.length > 0) {
            Helper.wr("Found command line arguments!");
            String res = null;

            res = ArrayHasEntryLike("WIN_WIDTH=", args);
            if (res != null) {
                WIN_WIDTH = Integer.parseInt(res.split("=")[1]);
            }

            res = ArrayHasEntryLike("WIN_HEIGHT=", args);
            if (res != null) {
                WIN_HEIGHT = Integer.parseInt(res.split("=")[1]);
            }

            res = ArrayHasEntryLike("PANEL_WIDTH=", args);
            if (res != null) {
                PANEL_WIDTH = Integer.parseInt(res.split("=")[1]);
            }

            res = ArrayHasEntryLike("PANEL_HEIGHT=", args);
            if (res != null) {
                PANEL_HEIGHT = Integer.parseInt(res.split("=")[1]);
            }

            res = ArrayHasEntryLike("FPS=", args);
            if (res != null) {
                FPS = Integer.parseInt(res.split("=")[1]);
            }

            res = ArrayHasEntryLike("OPENGL=false", args);
            if(res == null) {
                System.setProperty("sun.java2d.opengl", "true");
            }
            
            res = ArrayHasEntryLike("ENGINE_CONFIG_FILE=", args);
            if (res != null) {
                ENGINE_CONFIG_FILE = res.split("=")[1];
            }
            
            res = ArrayHasEntryLike("ODROID=true", args);
            if(res != null) {
                WIN_WIDTH = 480;
                WIN_HEIGHT = 320;
                PANEL_WIDTH = 478;
                PANEL_HEIGHT = 318;
                GAME_WIDTH = 478;
                GAME_HEIGHT = 318;
            }            
        }

        //LOAD ENGINE CONFIG FILE
        try {
            if (MmgTestSpace.ENGINE_CONFIG_FILE != null && MmgTestSpace.ENGINE_CONFIG_FILE.equals("") == false) {
                GameSettingsImporter dci = new GameSettingsImporter();
                boolean r = dci.ImportGameSettings(MmgTestSpace.ENGINE_CONFIG_FILE);
                System.out.println("Engine config load result: " + r);

                int len = dci.GetValues().keySet().size();
                String[] keys = dci.GetValues().keySet().toArray(new String[len]);
                String key;
                DatConstantsEntry ent = null;
                Field f;

                for (int i = 0; i < len; i++) {
                    try {
                        key = keys[i];
                        ent = dci.GetValues().get(key);

                        if (ent.from != null && ent.from.equals("GameSettings") == true) {
                            f = GameSettings.class.getField(ent.key);
                            if (f != null) {
                                System.out.println("Importing " + ent.from + " field: " + ent.key + " with value: " + ent.val + " with type: " + ent.type + " from: " + ent.from);
                                SetField(ent, f);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Ignoring dat constants field: " + ent.key + " with value: " + ent.val + " with type: " + ent.type);
                        Helper.wrErr(e);
                    }
                }
            }
        } catch (Exception e) {
            Helper.wrErr(e);
        }

        //Set program specific resource loading directories
        GameSettings.PROGRAM_IMAGE_LOAD_DIR += GameSettings.NAME;
        GameSettings.PROGRAM_SOUND_LOAD_DIR += GameSettings.NAME;        
        
        Helper.wr("Window Width: " + WIN_WIDTH);
        Helper.wr("Window Height: " + WIN_HEIGHT);
        Helper.wr("Panel Width: " + PANEL_WIDTH);
        Helper.wr("Panel Height: " + PANEL_HEIGHT);
        Helper.wr("Game Width: " + GAME_WIDTH);
        Helper.wr("Game Height: " + GAME_HEIGHT);

        mf = new MainFrame(MmgTestSpace.WIN_WIDTH, MmgTestSpace.WIN_HEIGHT, MmgTestSpace.PANEL_WIDTH, MmgTestSpace.PANEL_HEIGHT, MmgTestSpace.GAME_WIDTH, MmgTestSpace.GAME_HEIGHT);
        pnlGame = new GamePanel(mf, MmgTestSpace.PANEL_WIDTH, MmgTestSpace.PANEL_HEIGHT, (MmgTestSpace.WIN_WIDTH - MmgTestSpace.PANEL_WIDTH) / 2, (MmgTestSpace.WIN_HEIGHT - MmgTestSpace.PANEL_HEIGHT) / 2, MmgTestSpace.GAME_WIDTH, MmgTestSpace.GAME_HEIGHT);
        mf.SetGamePanel(pnlGame);
        mf.InitComponents();
        fr = new RunFrameRate(mf, FPS);

        mf.setSize(MmgTestSpace.WIN_WIDTH, MmgTestSpace.WIN_HEIGHT);
        mf.setResizable(false);
        mf.setVisible(true);

        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.GetGamePanel().PrepBuffers();
        t = new Thread(fr);
        t.start();
    }
}

package com.middlemind.Odroid;

import static com.middlemind.Odroid_Tutorial2_Pong.MmgPongClone.LoadNativeLibraries;
import java.lang.reflect.Field;
import javax.swing.JFrame;

/**
 * Java swing game that runs the Tyre DAT file. MAIN ENTRY POINT Created on
 * August 1, 2015, 10:57 PM by Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class OdroidGame {

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

    /**
     * Base engine config files.
     */
    public static String ENGINE_CONFIG_FILE = "../cfg/engine_config.xml";

    public static GamePanel pnlGame;    
    
    /**
     * Method that searches an array for a string match.
     *
     * @param v The string to find a match for.
     * @param s The array of string to search through.
     * @return
     */
    public static String ArrayHasEntryLike(String v, String[] s) {
        if(v == null || s == null) {
            return null;
        }
        
        String tmp = v.toLowerCase();
        int len = s.length;
        for (int i = 0; i < len; i++) {
            if(s[i] != null) {
                if (s[i].toLowerCase().contains(v) == true) {
                    return s[i];
                }
            }
        }
        return null;
    }

    public static void LoadNativeLibraries() {
        String OS = System.getProperty("os.name").toLowerCase();
        Helper.wr("Found platform: " + OS);
		
        if (isWindows(OS)) {
            Helper.wr("This is Windows");
            
        } else if (isMac(OS)) {
            Helper.wr("This is Mac");
            System.loadLibrary("libjinput-osx.jnilib");
            
        } else if (isUnix(OS)) {
            Helper.wr("This is Unix or Linux");
            
        } else if (isSolaris(OS)) {
            Helper.wr("This is Solaris");
            
        } else {
            Helper.wr("Your OS is not support!!");
            
        }
    }
    
    public static boolean isWindows(String OS) {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac(String OS) {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix(String OS) {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    public static boolean isSolaris(String OS) {
        return (OS.indexOf("sunos") >= 0);
    }    
    
    /**
     * Sets the value of the field specified by the field reflection object.
     *
     * @param ent Entry object that wraps the XML entry.
     * @param f Class member that needs to be updated.
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
        LoadNativeLibraries();
        
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
            if(res == null) {
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
            if (OdroidGame.ENGINE_CONFIG_FILE != null && OdroidGame.ENGINE_CONFIG_FILE.equals("") == false) {
                GameSettingsImporter dci = new GameSettingsImporter();
                boolean r = dci.ImportGameSettings(OdroidGame.ENGINE_CONFIG_FILE);
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

        Helper.wr("Window Width: " + WIN_WIDTH);
        Helper.wr("Window Height: " + WIN_HEIGHT);
        Helper.wr("Panel Width: " + PANEL_WIDTH);
        Helper.wr("Panel Height: " + PANEL_HEIGHT);
        Helper.wr("Game Width: " + GAME_WIDTH);
        Helper.wr("Game Height: " + GAME_HEIGHT);

        mf = new MainFrame(OdroidGame.WIN_WIDTH, OdroidGame.WIN_HEIGHT, OdroidGame.PANEL_WIDTH, OdroidGame.PANEL_HEIGHT, OdroidGame.GAME_WIDTH, OdroidGame.GAME_HEIGHT);
        pnlGame = new GamePanel(mf, OdroidGame.PANEL_WIDTH, OdroidGame.PANEL_HEIGHT, (OdroidGame.WIN_WIDTH - OdroidGame.PANEL_WIDTH) / 2, (OdroidGame.WIN_HEIGHT - OdroidGame.PANEL_HEIGHT) / 2, OdroidGame.GAME_WIDTH, OdroidGame.GAME_HEIGHT);
        mf.SetGamePanel(pnlGame);
        mf.InitComponents();
        fr = new RunFrameRate(mf, FPS);

        mf.setSize(OdroidGame.WIN_WIDTH, OdroidGame.WIN_HEIGHT);
        mf.setResizable(false);
        mf.setVisible(true);
        mf.setName(GameSettings.NAME);

        if (GameSettings.DEVELOPMENT_MODE_ON == false) {
            mf.setTitle(GameSettings.TITLE);
        } else {
            mf.setTitle(GameSettings.TITLE + " - " + GameSettings.DEVELOPER_COMPANY + " (" + GameSettings.VERSION + ")");
        }

        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.GetGamePanel().PrepBuffers();
        t = new Thread(fr);
        t.start();
    }
}

package net.middlemind.MmgGameApiJava.MmgTestSpace;

import net.middlemind.MmgGameApiJava.MmgCore.DatConstantsEntry;
import net.middlemind.MmgGameApiJava.MmgCore.GameSettings;
import net.middlemind.MmgGameApiJava.MmgCore.GameSettingsImporter;
import java.lang.reflect.Field;
import javax.swing.JFrame;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
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
    public static int PANEL_HEIGHT = 596;

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
     * Run OS specific code on startup before the native libraries are loaded and the game engine
     * config XML file.
     */
    public static void RunOsSpecificCode() {
        try {
            String OS = System.getProperty("os.name").toLowerCase();
            Helper.wr("Found platform: " + OS);
            
            if (isWindows(OS)) {
                Helper.wr("This is Windows");
                
            } else if (isMac(OS)) {
                Helper.wr("This is Mac");
                GameSettings.LOAD_NATIVE_LIBRARIES = true;
                GameSettings.GAMEPAD_1_ON = true;
                GameSettings.GAMEPAD_1_THREADED_POLLING = false;
                GameSettings.GAMEPAD_2_ON = false;
                GameSettings.GPIO_GAMEPAD_ON = false;
                
            } else if (isUnix(OS)) {
                Helper.wr("This is Unix or Linux");
                GameSettings.LOAD_NATIVE_LIBRARIES = false;
                GameSettings.GAMEPAD_1_ON = false;
                GameSettings.GAMEPAD_2_ON = false;
                GameSettings.GPIO_GAMEPAD_ON = true;
                GameSettings.GPIO_GAMEPAD_THREADED_POLLING = true;
                
            } else if (isSolaris(OS)) {
                Helper.wr("This is Solaris");
                
            } else {
                Helper.wr("Your OS is not supported!!");

            }
            
        } catch(Exception e) {
            Helper.wrErr(e);
        }
    }    
    
    /**
     * A static method that loads native libraries that allow access to gamepads and controllers.
     */
    public static void LoadNativeLibraries() {
        try {
            String OS = System.getProperty("os.name").toLowerCase();
            Helper.wr("Found platform: " + OS);
            Helper.wr("LibPath: " + System.getProperty("java.library.path"));
            //System.load("/Users/victor/Documents/files/netbeans_workspace/MmgGameApiJava/lib/jinput-platform/native-libs/libjinput-osx.jnilib");
            
            if (isWindows(OS)) {
                Helper.wr("This is Windows");
                System.loadLibrary("jinput-dx8_64");
                
            } else if (isMac(OS)) {
                Helper.wr("This is Mac");
                System.loadLibrary("jinput-osx");

            } else if (isUnix(OS)) {
                Helper.wr("This is Unix or Linux");
                System.loadLibrary("jinput-linux64");
                
            } else if (isSolaris(OS)) {
                Helper.wr("This is Solaris");
                System.loadLibrary("jinput-linux");
                
            } else {
                Helper.wr("Your OS is not supported!!");

            }
            
        } catch(Exception e) {
            Helper.wrErr(e);
        }            
    }
    
    /**
     * A static class method for checking if this Java application is running on Windows.
     * 
     * @param OS        The current OS, System.getProperty("os.name").toLowerCase(), that this Java application is running on.
     * 
     * @return          A boolean value indicating if the Java application is running on Windows.
     */
    public static boolean isWindows(String OS) {
        return (OS.indexOf("win") >= 0);
    }

    /**
     * A static class method for checking if this Java application is running on a Mac.
     * 
     * @param OS        The current OS, System.getProperty("os.name").toLowerCase(), that this Java application is running on.
     * 
     * @return          A boolean value indicating if the Java application is running on a Mac.
     */
    public static boolean isMac(String OS) {
        return (OS.indexOf("mac") >= 0);
    }

    /**
     * A static class method for checking if this Java application is running on Linux.
     * 
     * @param OS        The current OS, System.getProperty("os.name").toLowerCase(), that this Java application is running on.
     * 
     * @return          A boolean value indicating if the Java application is running on Linux.
     */
    public static boolean isUnix(String OS) {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    /**
     * A static class method for checking if this Java application is running on Sun OS.
     * 
     * @param OS        The current OS, System.getProperty("os.name").toLowerCase(), that this Java application is running on.
     * 
     * @return          A boolean value indicating if the Java application is running on Sun OS.
     */
    public static boolean isSolaris(String OS) {
        return (OS.indexOf("sunos") >= 0);
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
        if(GameSettings.RUN_OS_SPECIFIC_CODE) {
            RunOsSpecificCode();
        }
        
        if(GameSettings.LOAD_NATIVE_LIBRARIES) {
            LoadNativeLibraries();
        }

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
                        } else if(ent.from != null && ent.from.equals("Helper") == true) {
                            f = Helper.class.getField(ent.key);
                            if (f != null) {
                                System.out.println("Importing " + ent.from + " field: " + ent.key + " with value: " + ent.val + " with type: " + ent.type + " from: " + ent.from);
                                SetField(ent, f);
                            }
                        } else if(ent.from != null && ent.from.equals("StaticMain") == true) {
                            f = MmgTestSpace.class.getField(ent.key);
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

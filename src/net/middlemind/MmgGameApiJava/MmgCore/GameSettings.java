package net.middlemind.MmgGameApiJava.MmgCore;

/**
 * Class that holds global game settings. Can be set using the engine config XML files. All path variables should end in
 * a path separator character. Application specific engine config XML files can be specified and used by the executable
 * class, i.e. ENGINE_CONFIG_FILE = "../cfg/engine_config_mmg_pong_clone.xml".
 *
 * @author Victor G. Brusca
 */
public class GameSettings {
    public static int DOWN = 0;
    public static int UP = 1;
    public static int LEFT = 2;
    public static int RIGHT = 3;
    
    public static boolean SND_CACHE_ON = true;
    public static String SND_PREFIX = "sounds_";    
    
    public static boolean BMP_CACHE_ON = true;
    public static String BMP_PREFIX = "images_";
    public static String BMP_PREFIX_CONSOLE = "console_images_";
    
    //Image dirs must end with a file path separator
    public static String IMAGE_LOAD_DIR = "../cfg/drawable/";
    public static String SOUND_LOAD_DIR = "../cfg/playable/";
    public static String PROGRAM_IMAGE_LOAD_DIR = "../cfg/drawable/";
    public static String PROGRAM_SOUND_LOAD_DIR = "../cfg/playable/";    
    public static String AUTO_IMAGE_LOAD_DIR = "../cfg/drawable/auto_load/";
    public static String AUTO_SOUND_LOAD_DIR = "../cfg/playable/auto_load/";    
    public static String CLASS_CONFIG_DIR = "../cfg/class_config/";
    
    public static String NAME = "Unknown";
    public static boolean DEVELOPMENT_MODE_ON = true;
    public static String VERSION = "0.0.0";
    public static String DEVELOPER_COMPANY = "Unknown";
    public static String TITLE = "Unknown";
    
    public static int GpioPinBtnUp = 1;
    public static boolean BtnUpCheckPress = true;
    public static boolean BtnUpCheckRelease = true;
    public static boolean BtnUpCheckClick = false;

    public static int GpioPinBtnDown = 2;
    public static boolean BtnDownCheckPress = true;
    public static boolean BtnDownCheckRelease = true;
    public static boolean BtnDownCheckClick = false;    
    
    public static int GpioPinBtnLeft = 3;
    public static boolean BtnLeftCheckPress = true;
    public static boolean BtnLeftCheckRelease = true;
    public static boolean BtnLeftCheckClick = false;        
    
    public static int GpioPinBtnRight = 4;
    public static boolean BtnRightCheckPress = true;
    public static boolean BtnRightCheckRelease = true;
    public static boolean BtnRightCheckClick = false;            
    
    public static int GpioPinBtnA = 5;
    public static boolean BtnACheckPress = false;
    public static boolean BtnACheckRelease = false;
    public static boolean BtnACheckClick = true;                
    
    public static int GpioPinBtnB = 6;
    public static boolean BtnBCheckPress = false;
    public static boolean BtnBCheckRelease = false;
    public static boolean BtnBCheckClick = true;                    
}

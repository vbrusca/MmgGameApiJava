package net.middlemind.MmgGameApiJava.MmgCore;

/**
 * Class that holds global game settings. Can be set using the engine config XML files. All path variables should end in
 * a path separator character. Application specific engine config XML files can be specified and used by the executable
 * class, i.e. ENGINE_CONFIG_FILE = "../cfg/engine_config_mmg_pong_clone.xml".
 *
 * @author Victor G. Brusca
 */
public class GameSettings {
    public static int SRC_KEYBOARD = 0;
    public static int DOWN_KEYBOARD = 0;
    public static int UP_KEYBOARD = 1;
    public static int LEFT_KEYBOARD = 2;
    public static int RIGHT_KEYBOARD = 3;
    
    public static int SRC_GPIO = 1;
    public static int DOWN_GPIO = 4;
    public static int UP_GPIO = 5;
    public static int LEFT_GPIO = 6;
    public static int RIGHT_GPIO = 7;
    
    public static int SRC_GAMEPAD_1 = 1;    
    public static int DOWN_GAMEPAD_1 = 8;
    public static int UP_GAMEPAD_1 = 9;
    public static int LEFT_GAMEPAD_1 = 10;
    public static int RIGHT_GAMEPAD_1 = 11;
    
    public static int SRC_GAMEPAD_2 = 2;
    public static int DOWN_GAMEPAD_2 = 12;
    public static int UP_GAMEPAD_2 = 13;
    public static int LEFT_GAMEPAD_2 = 14;
    public static int RIGHT_GAMEPAD_2 = 15;    
    
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
    
    //GPIO gamepad input settings
    //On/Off values are always 0 and 1 so no mapping is needed for these values.
    public static boolean GPIO_GAMEPAD_ON = false;
    
    public static int GPIO_PIN_BTN_UP = 1;
    public static boolean BTN_UP_CHECK_PRESS = true;
    public static boolean BTN_UP_CHECK_RELEASE = true;
    public static boolean BTN_UP_CHECK_CLICK = true;

    public static int GPIO_PIN_BTN_DOWN = 2;
    public static boolean BTN_DOWN_CHECK_PRESS = true;
    public static boolean BTN_DOWN_CHECK_RELEASE = true;
    public static boolean BTN_DOWN_CHECK_CLICK = true;    
    
    public static int GPIO_PIN_BTN_LEFT = 3;
    public static boolean BTN_LEFT_CHECK_PRESS = true;
    public static boolean BTN_LEFT_CHECK_RELEASE = true;
    public static boolean BTN_LEFT_CHECK_CLICK = true;        
    
    public static int GPIO_PIN_BTN_RIGHT = 4;
    public static boolean BTN_RIGHT_CHECK_PRESS = true;
    public static boolean BTN_RIGHT_CHECK_RELEASE = true;
    public static boolean BTN_RIGHT_CHECK_CLICK = true;            
    
    public static int GPIO_PIN_BTN_A = 5;
    public static boolean BTN_A_CHECK_PRESS = true;
    public static boolean BTN_A_CHECK_RELEASE = true;
    public static boolean BTN_A_CHECK_CLICK = true;
    
    public static int GPIO_PIN_BTN_B = 6;
    public static boolean BTN_B_CHECK_PRESS = true;
    public static boolean BTN_B_CHECK_RELEASE = true;
    public static boolean BTN_B_CHECK_CLICK = true;
    
    //GamePad 1 input settings
    public static boolean GAMEPAD_1_ON = true;
    public static int GAMEPAD_1_INDEX = 0;    
    
    public static int GAMEPAD_1_UP_INDEX = 15;
    public static float GAMEPAD_1_UP_VALUE_ON = 0.25f;
    public static float GAMEPAD_1_UP_VALUE_OFF = 0.00f;
    public static boolean GAMEPAD_1_UP_CHECK_PRESS = true;
    public static boolean GAMEPAD_1_UP_CHECK_RELEASE = true;
    public static boolean GAMEPAD_1_UP_CHECK_CLICK = true;

    public static int GAMEPAD_1_DOWN_INDEX = 15;
    public static float GAMEPAD_1_DOWN_VALUE_ON = 0.75f;
    public static float GAMEPAD_1_DOWN_VALUE_OFF = 0.00f;
    public static boolean GAMEPAD_1_DOWN_CHECK_PRESS = true;
    public static boolean GAMEPAD_1_DOWN_CHECK_RELEASE = true;
    public static boolean GAMEPAD_1_DOWN_CHECK_CLICK = true;
    
    public static int GAMEPAD_1_LEFT_INDEX = 15;
    public static float GAMEPAD_1_LEFT_VALUE_ON = 1.00f;
    public static float GAMEPAD_1_LEFT_VALUE_OFF = 0.00f;
    public static boolean GAMEPAD_1_LEFT_CHECK_PRESS = true;
    public static boolean GAMEPAD_1_LEFT_CHECK_RELEASE = true;
    public static boolean GAMEPAD_1_LEFT_CHECK_CLICK = true;
    
    public static int GAMEPAD_1_RIGHT_INDEX = 15;
    public static float GAMEPAD_1_RIGHT_VALUE_ON = 0.50f;
    public static float GAMEPAD_1_RIGHT_VALUE_OFF = 0.00f;
    public static boolean GAMEPAD_1_RIGHT_CHECK_PRESS = true;
    public static boolean GAMEPAD_1_RIGHT_CHECK_RELEASE = true;
    public static boolean GAMEPAD_1_RIGHT_CHECK_CLICK = true;
    
    public static int GAMEPAD_1_A_INDEX = 0;
    public static float GAMEPAD_1_A_VALUE_ON = 1.00f;
    public static float GAMEPAD_1_A_VALUE_OFF = 0.00f;    
    public static boolean GAMEPAD_1_A_CHECK_PRESS = true;
    public static boolean GAMEPAD_1_A_CHECK_RELEASE = true;
    public static boolean GAMEPAD_1_A_CHECK_CLICK = true;
    
    public static int GAMEPAD_1_B_INDEX = 1;
    public static float GAMEPAD_1_B_VALUE_ON = 1.00f;
    public static float GAMEPAD_1_B_VALUE_OFF = 0.00f;
    public static boolean GAMEPAD_1_B_CHECK_PRESS = true;
    public static boolean GAMEPAD_1_B_CHECK_RELEASE = true;
    public static boolean GAMEPAD_1_B_CHECK_CLICK = true;
    
    //GamePad 2 input settings
    public static boolean GAMEPAD_2_ON = true;
    public static int GAMEPAD_2_INDEX = 0;    
    
    public static int GAMEPAD_2_UP_INDEX = 15;
    public static float GAMEPAD_2_UP_VALUE_ON = 0.25f;
    public static float GAMEPAD_2_UP_VALUE_OFF = 0.00f;
    public static boolean GAMEPAD_2_UP_CHECK_PRESS = true;
    public static boolean GAMEPAD_2_UP_CHECK_RELEASE = true;
    public static boolean GAMEPAD_2_UP_CHECK_CLICK = true;

    public static int GAMEPAD_2_DOWN_INDEX = 15;
    public static float GAMEPAD_2_DOWN_VALUE_ON = 0.75f;
    public static float GAMEPAD_2_DOWN_VALUE_OFF = 0.00f;
    public static boolean GAMEPAD_2_DOWN_CHECK_PRESS = true;
    public static boolean GAMEPAD_2_DOWN_CHECK_RELEASE = true;
    public static boolean GAMEPAD_2_DOWN_CHECK_CLICK = true;
    
    public static int GAMEPAD_2_LEFT_INDEX = 15;
    public static float GAMEPAD_2_LEFT_VALUE_ON = 1.00f;
    public static float GAMEPAD_2_LEFT_VALUE_OFF = 0.00f;
    public static boolean GAMEPAD_2_LEFT_CHECK_PRESS = true;
    public static boolean GAMEPAD_2_LEFT_CHECK_RELEASE = true;
    public static boolean GAMEPAD_2_LEFT_CHECK_CLICK = true;
    
    public static int GAMEPAD_2_RIGHT_INDEX = 15;
    public static float GAMEPAD_2_RIGHT_VALUE_ON = 0.50f;
    public static float GAMEPAD_2_RIGHT_VALUE_OFF = 0.00f;
    public static boolean GAMEPAD_2_RIGHT_CHECK_PRESS = true;
    public static boolean GAMEPAD_2_RIGHT_CHECK_RELEASE = true;
    public static boolean GAMEPAD_2_RIGHT_CHECK_CLICK = true;
    
    public static int GAMEPAD_2_A_INDEX = 0;
    public static float GAMEPAD_2_A_VALUE_ON = 1.00f;
    public static float GAMEPAD_2_A_VALUE_OFF = 0.00f;    
    public static boolean GAMEPAD_2_A_CHECK_PRESS = true;
    public static boolean GAMEPAD_2_A_CHECK_RELEASE = true;
    public static boolean GAMEPAD_2_A_CHECK_CLICK = true;
    
    public static int GAMEPAD_2_B_INDEX = 1;
    public static float GAMEPAD_2_B_VALUE_ON = 1.00f;
    public static float GAMEPAD_2_B_VALUE_OFF = 0.00f;
    public static boolean GAMEPAD_2_B_CHECK_PRESS = true;
    public static boolean GAMEPAD_2_B_CHECK_RELEASE = true;
    public static boolean GAMEPAD_2_B_CHECK_CLICK = true;    
}

package com.middlemind.Odroid;

/**
 * Class that holds global game settings.
 *
 * @author Victor G. Brusca
 */
public final class GameSettings {
    public static int DOWN = 0;
    public static int UP = 1;
    public static int LEFT = 2;
    public static int RIGHT = 3;
    
    public static boolean BMP_CACHE_ON = true;
    public static String BMP_PREFIX = "images_";
    public static String BMP_PREFIX_CONSOLE = "console_images_";
    public static String AUTO_IMAGE_LOAD_DIR = "./cfg/drawable/auto_load/";
    public static String NAME = "Unknown";
    
    public static boolean DEVELOPMENT_MODE_ON = true;
    public static String VERSION = "0.0.0";
    public static String DEVELOPER_COMPANY = "Unknown";
    public static String TITLE = "Unknown";
}

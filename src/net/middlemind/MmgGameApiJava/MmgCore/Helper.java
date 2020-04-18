package net.middlemind.MmgGameApiJava.MmgCore;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEvent;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEventHandler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgMediaTracker;
import net.middlemind.MmgGameApiJava.MmgBase.MmgMenuItem;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgSound;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;

/**
 * Provides support for static helper methods. This class provides similar methods to the MmgHelper
 * class but allows the developer to tie into higher level libraries and code structure outside of the
 * base Mmg API. The GameSettings class controls the image and sound cache via the SND_CACHE_ON, BMP_CACHE_ON static variables.
 * Created by Middlemind Games 08/01/2015
 *
 * @author Victor G. Brusca
 */
public class Helper {

    /**
     * Controls if logging is turned on or off.
     */
    public static boolean LOGGING = true;
    
    /**
     * A random number generator that can be used for convenient access to random number generation.
     */
    public static Random rando = new Random(System.currentTimeMillis());

    /**
     * A static helper method that is used to generate a random integer value with an exclusive upper bound.
     * 
     * @param exclusiveUpperBound       A method argument that provides the exclusive upper bound for the random number generator.
     * 
     * @return                          A random integer value greater then or equal to zero and less than the exclusiveUpperBound. 
     */
    public static int GetRandomInt(int exclusiveUpperBound) {
        return rando.nextInt(exclusiveUpperBound);
    }

    /**
     * Centralized logging method for standard out logging.
     *
     * @param s         The string to be logged.
     */
    public static void wr(String s) {
        if (Helper.LOGGING = true) {
            System.out.println(s);
        }
    }

    /**
     * Centralized logging method for standard error logging.
     *
     * @param e         The exception to be logged.
     */
    public static void wrErr(Exception e) {
        if (Helper.LOGGING = true) {
            System.err.println(e.getMessage());
            StackTraceElement[] els = e.getStackTrace();
            int len = els.length;
            for (int i = 0; i < len; i++) {
                System.err.println(els[i].toString());
            }
        }
    }

    /**
     * Centralized logging method for standard err logging.
     *
     * @param s         The string to be logged.
     */
    public static void wrErr(String s) {
        if (Helper.LOGGING = true) {
            System.err.println(s);
        }
    }

    /**
     * A helper method for loading a sound file and storing it in the audio cache using the ID method argument.
     * 
     * @param path      A method argument that is the location of the sound file to load.
     * @param sndId     A method argument that is the ID used in the audio cache.
     * 
     * @return          An MmgSound object created from the source resource file.
     */
    @SuppressWarnings("UnusedAssignment")
    public static MmgSound GetBasicCachedSound(String path, String sndId) {
        MmgSound lval = null;
        if (GameSettings.SND_CACHE_ON == true) {
            if (MmgMediaTracker.HasSoundKey(sndId) == true) {
                lval = new MmgSound(MmgMediaTracker.GetSoundValue(sndId));
            } else {
                lval = Helper.GetBasicSound(path);
                MmgMediaTracker.CacheSound(sndId, lval.GetSound());
            }
        } else {
            lval = Helper.GetBasicSound(path);
        }
        return lval;
    }
    
    /**
     * A helper method to get a sound resource from the audio cache using the ID method argument.
     * 
     * @param sndId     A method argument that is the ID used in the audio cache.
     * 
     * @return          An MmgSound object returned from the audio cache.
     */
    @SuppressWarnings("UnusedAssignment")
    public static MmgSound GetBasicCachedSound(String sndId) {
        MmgSound lval = null;
        if (GameSettings.SND_CACHE_ON == true) {
            if (MmgMediaTracker.HasSoundKey(sndId) == true) {
                lval = new MmgSound(MmgMediaTracker.GetSoundValue(sndId));
            }
        }
        return lval;
    }    
            
    /**
     * A helper method for loading an image file and storing it in the image cache using the ID method argument.
     * 
     * @param path      A method argument that is the location of the image file to load.
     * @param imgId     A method argument that is the ID used in the image cache.
     * 
     * @return          An MmgBmp object created from the source resource file.
     */    
    @SuppressWarnings("UnusedAssignment")
    public static MmgBmp GetBasicCachedBmp(String path, String imgId) {
        MmgBmp lval = null;
        if (GameSettings.BMP_CACHE_ON == true) {
            if (MmgMediaTracker.HasBmpKey(imgId) == true) {
                lval = new MmgBmp(MmgMediaTracker.GetBmpValue(imgId));
                lval.SetMmgColor(null);
            } else {
                lval = Helper.GetBasicBmp(path);
                MmgMediaTracker.CacheImage(imgId, lval.GetImage());
            }
        } else {
            lval = Helper.GetBasicBmp(path);
        }
        return lval;
    }

    /**
     * A helper method to get an image resource from the image cache using the ID method argument.
     * 
     * @param imgId     A method argument that is the ID used in the image cache.
     * 
     * @return          An MmgBmp object created from the source resource file.
     */    
    @SuppressWarnings("UnusedAssignment")
    public static MmgBmp GetBasicCachedBmp(String imgId) {
        MmgBmp lval = null;
        if (GameSettings.BMP_CACHE_ON == true) {
            if (MmgMediaTracker.HasBmpKey(imgId) == true) {
                lval = new MmgBmp(MmgMediaTracker.GetBmpValue(imgId));
                lval.SetMmgColor(null);
            }
        }
        return lval;
    }    
    
    /**
     * A helper method that lists the contents of the image and audio caches if they are enabled.
     */
    public static void ListCacheEntries() {
        int len;
        Object[] keys;
        String key;
        int i;
        if (GameSettings.BMP_CACHE_ON == true) {
            len = MmgMediaTracker.GetBmpCacheSize();
            keys = MmgMediaTracker.cacheBmp.keySet().toArray();
            for (i = 0; i < len; i++) {
                key = keys[i] + "";
                wr("BMP: " + i + " key: " + key);
            }
        }
        
        if (GameSettings.SND_CACHE_ON == true) {
            len = MmgMediaTracker.GetSoundCacheSize();
            keys = MmgMediaTracker.cacheSound.keySet().toArray();
            for (i = 0; i < len; i++) {
                key = keys[i] + "";
                wr("SND: " + i + " key: " + key);
            }
        }        
    }

    /**
     * Gets a basic image from the file system.
     *
     * @param src       A path to a valid image resource.
     * 
     * @return          An MmgBmp object loaded from the file path.
     */
    public static MmgBmp GetBasicBmp(String src) {
        Image b = null;
        MmgBmp r = null;

        try {
            b = ImageIO.read(new File(src));
        } catch (Exception e) {
            Helper.wrErr(e);
        }

        if (b != null) {
            b = MmgPen.ScaleImage(b, MmgScreenData.GetScale());
            r = new MmgBmp(b);
            r.SetScaling(MmgVector2.GetUnitVec());
            r.SetPosition(MmgVector2.GetOriginVec());
            r.SetOrigin(MmgVector2.GetOriginVec());
            r.SetMmgColor(null);
        }

        return r;
    }
    
    /**
     * Gets a basic sound from the file system.
     *
     * @param src       A path to a valid sound resource.
     * 
     * @return          An MmgSound object loaded from the file path.
     */
    public static MmgSound GetBasicSound(String src) {
        Clip in = null;
        MmgSound snd = null;
        
        try
        {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(src));
            in = AudioSystem.getClip();
            in.open(audioIn);
            snd = new MmgSound(in);
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        return snd;
    }  

    /**
     * A helper method that creates an MmgBmp object from an array of bytes that constitutes an image file.
     * 
     * @param d     A method argument that is a binary array of image data.
     * 
     * @return      An MmgBmp object created from the binary array data.
     */
    public static MmgBmp GetBinaryBmp(byte[] d) {
        Image b = null;
        MmgBmp r = null;

        try {
            b = ImageIO.read(new ByteArrayInputStream(d));
        } catch (Exception e) {
            Helper.wrErr(e);
        }

        if (b != null) {
            b = MmgPen.ScaleImage(b, MmgScreenData.GetScale());
            r = new MmgBmp(b);
            r.SetScaling(MmgVector2.GetUnitVec());
            r.SetPosition(MmgVector2.GetOriginVec());
            r.SetOrigin(MmgVector2.GetOriginVec());
            r.SetMmgColor(null);
        }

        return r;
    }

    /**
     * A helper method that creates an MmgBmp object from a lower level Image object and scales it based on the screen scaling data.
     * 
     * @param b     A Java Image class instance.
     * 
     * @return      An MmgBmp object created from the lower level Java Image class instance.
     */
    public static MmgBmp GetImageBmp(Image b) {
        MmgBmp r = null;

        if (b != null) {
            b = MmgPen.ScaleImage(b, MmgScreenData.GetScale());
            r = new MmgBmp(b);
            r.SetScaling(MmgVector2.GetUnitVec());
            r.SetPosition(MmgVector2.GetOriginVec());
            r.SetOrigin(MmgVector2.GetOriginVec());
            r.SetMmgColor(null);
        }

        return r;
    }

    /**
     * A helper method that creates an MmgBmp object from a lower level Image object. 
     * Despite the name of this method the MmgBmp is not cached.
     * 
     * @param b     A Java Image class instance
     * 
     * @return      An MmgBmp object created from the lower level Java Image class instance.
     */
    public static MmgBmp GetImageCacheBmp(Image b) {
        MmgBmp r = null;

        if (b != null) {
            r = new MmgBmp(b);
            r.SetScaling(MmgVector2.GetUnitVec());
            r.SetPosition(MmgVector2.GetOriginVec());
            r.SetOrigin(MmgVector2.GetOriginVec());
            r.SetMmgColor(null);
        }

        return r;
    }

    /**
     * Gets a basic MmgMenuItem instance.
     *
     * @param handler       The event handler of the menu item.
     * @param name          The name of the menu item.
     * @param eventId       The id of the menu item event.
     * @param eventType     The type of the menu item event.
     * @param img           The image used to display the menu item.
     * 
     * @return
     */
    public static MmgMenuItem GetBasicMenuItem(MmgEventHandler handler, String name, int eventId, int eventType, MmgBmp img) {
        MmgMenuItem itm;
        itm = new MmgMenuItem();
        itm.SetNormal(img);
        itm.SetSelected(img);
        itm.SetInactive(img);
        itm.SetPosition(img.GetPosition());
        itm.SetState(MmgMenuItem.STATE_NORMAL);
        itm.SetEventPress(new MmgEvent(handler, name, eventId, eventType, handler, null));
        itm.SetMmgColor(null);
        return itm;
    }
}

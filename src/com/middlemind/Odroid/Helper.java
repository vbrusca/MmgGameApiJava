package com.middlemind.Odroid;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEvent;
import net.middlemind.MmgGameApiJava.MmgBase.MmgEventHandler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgMediaTracker;
import net.middlemind.MmgGameApiJava.MmgBase.MmgMenuItem;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;

/**
 * Provides support for static helper methods. Created on August 1, 2015, 10:57
 * PM by Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class Helper {

    /**
     * Controls if logging is turned on or off.
     */
    public static boolean LOGGING = true;
    private static Random rando = new Random(System.currentTimeMillis());

    public static final int GetRandomInt(int exclusiveUpperBound) {
        return rando.nextInt(exclusiveUpperBound);
    }

    /**
     * Centralized logging method for standard out logging.
     *
     * @param s The string to be logged.
     */
    public static final void wr(String s) {
        if (Helper.LOGGING = true) {
            System.out.println(s);
        }
    }

    /**
     * Centralized logging method for standard error logging.
     *
     * @param e The exception to be logged.
     */
    public static final void wrErr(Exception e) {
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
     * @param s The string to be logged.
     */
    public static final void wrErr(String s) {
        if (Helper.LOGGING = true) {
            System.err.println(s);
        }
    }

    @SuppressWarnings("UnusedAssignment")
    public static final MmgBmp GetBasicCachedBmp(String path, String imgId) {
        MmgBmp lval = null;
        if (GameSettings.BMP_CACHE_ON == true) {
            if (MmgMediaTracker.HasKey(imgId) == true) {
                lval = new MmgBmp(MmgMediaTracker.GetValue(imgId));
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

    public static final void ListCacheEntries() {
        if (GameSettings.BMP_CACHE_ON == true) {
            int len = MmgMediaTracker.GetCacheSize();
            Object[] keys = MmgMediaTracker.ms.keySet().toArray();
            for (int i = 0; i < len; i++) {
                String key = keys[i] + "";
                wr(i + " key: " + key);
            }
        }
    }

    /**
     * Gets a basic bitmap from the file system.
     *
     * @param src A path to a valid bitmap resource.
     * @return A bitmap loaded from the file path.
     */
    public static final MmgBmp GetBasicBmp(String src) {
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

    public static final MmgBmp GetBinaryBmp(byte[] d) {
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

    public static final MmgBmp GetImageBmp(Image b) {
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

    public static final MmgBmp GetImageCacheBmp(Image b) {
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
     * @param handler The event handler of the menu item.
     * @param name The name of the menu item.
     * @param eventId The id of the menu item event.
     * @param eventType The type of the menu item event.
     * @param img The image used to display the menu item.
     * @return
     */
    public static final MmgMenuItem GetBasicMenuItem(MmgEventHandler handler, String name, int eventId, int eventType, MmgBmp img) {
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

package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * Class that provides high level helper methods. Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class MmgHelper {
   
    /**
     * Controls if logging is turned on or off.
     */
    public static boolean LOGGING = true;
    public static boolean BMP_CACHE_ON = true;
    private static Random rando = new Random(System.currentTimeMillis());    

    public static Hashtable<String, Double> LoadClassConfigFile(String file) {
        Hashtable<String, Double> ret = new Hashtable();;
        
        try {
            File f = new File(file);
            if(f.exists()) {                
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                String[] data = null;
                
                while(line != null) {
                    line = line.trim();
                    if(line.charAt(0) != '#' && line.equals("") == false) {
                        data = line.split("=");
                        if(data.length == 2) {
                            ret.put(data[0], new Double(data[1]));
                        }
                    }
                    line = br.readLine();
                }
            }
        }catch(Exception e) {
            wrErr(e);
        }
        
        return ret;
    }    
    
    public static MmgBmp CreateFilledBmp(int width, int height, MmgColor color) {
        return CreateDrawableBmpSet(width, height, false, color).img;
    }
    
    public static MmgDrawableBmpSet CreateDrawableBmpSet(int width, int height, boolean alpha) {
        MmgDrawableBmpSet dBmpSet = new MmgDrawableBmpSet();
        dBmpSet.buffImg = MmgBmpScaler.GRAPHICS_CONFIG.createCompatibleImage(width, height, alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
        dBmpSet.graphics = (Graphics2D)dBmpSet.buffImg.getGraphics();
        dBmpSet.p = new MmgPen();
        dBmpSet.p.SetGraphics(dBmpSet.graphics);
        dBmpSet.p.SetAdvRenderHints();
        dBmpSet.img = new MmgBmp(dBmpSet.buffImg);
        return dBmpSet;
    }
    
    public static MmgDrawableBmpSet CreateDrawableBmpSet(int width, int height, boolean alpha, MmgColor color) {
        MmgDrawableBmpSet dBmpSet = MmgHelper.CreateDrawableBmpSet(width, height, alpha);
        Color c = dBmpSet.graphics.getColor();
        dBmpSet.graphics.setColor(color.GetColor());
        dBmpSet.graphics.fillRect(0, 0, width, height);
        dBmpSet.graphics.setColor(c);
        return dBmpSet;
    }    
    
    public static boolean RectCollision(int x, int y, MmgRect r) {
        if(x >= r.GetLeft() && x <= r.GetRight()) {
            if(y >= r.GetTop() && y <= r.GetBottom()) {
                return true;
            }
        }
        
        return false;
    }    
    
    /**
     * Tests for collision of two rectangles.
     *
     * @param r1x Rectangle #1, X coordinate.
     * @param r1y Rectangle #1, Y coordinate.
     * @param w Rectangle #1, width.
     * @param h Rectangle #1, height.
     * @param r Rectangle #2.
     * @return True if there is a collision of the two rectangles.
     */
    public static boolean RectCollision(int r1x, int r1y, int w, int h, MmgRect r) {
        int r2x = r.GetLeft();
        int r2y = r.GetTop();

        return RectCollision(r1x, r1y, w, h, r2x, r2y, (r.GetRight() - r.GetLeft()), (r.GetBottom() - r.GetTop()));
    }

    /**
     * Tests for collision of two rectangles.
     *
     * @param src Rectangle #1.
     * @param dest Rectangle #2.
     * @return True if there is a collision of the two rectangles.
     */
    public static boolean RectCollision(MmgRect src, MmgRect dest) {
        return RectCollision(src.GetLeft(), src.GetTop(), src.GetWidth(), src.GetHeight(), dest);
    }

    /**
     * Tests for collisions of two rectangles.
     *
     * @param r1x Rectangle #1, X coordinate.
     * @param r1y Rectangle #1, Y coordinate.
     * @param r1w Rectangle #1, width.
     * @param r1h Rectangle #1, height.
     * @param r2x Rectangle #2, X coordinate.
     * @param r2y Rectangle #2, Y coordinate.
     * @param r2w Rectangle #2, width.
     * @param r2h Rectangle #2, height.
     * @return True if there is a collision of the two rectangles.
     */
    public static boolean RectCollision(int r1x, int r1y, int r1w, int r1h, int r2x, int r2y, int r2w, int r2h) {

        if (r1x >= r2x && r1x <= (r2x + r2w) && r1y >= r2y && r1y <= (r2y + r2h)) {
            return true;

        } else if ((r1x + r1w) >= r2x && (r1x + r1w) <= (r2x + r2w) && r1y >= r2y && r1y <= (r2y + r2h)) {
            return true;

        } else if ((r1x + r1w) >= r2x && (r1x + r1w) <= (r2x + r2w) && (r1y + r1h) >= r2y && (r1y + r1h) <= (r2y + r2h)) {
            return true;

        } else if (r1x >= r2x && r1x <= (r2x + r2w) && (r1y + r1h) >= r2y && (r1y + r1h) <= (r2y + r2h)) {
            return true;

        } else if (r2x >= r1x && r2x <= (r1x + r1w) && r2y >= r1y && r2y <= (r1y + r1h)) {
            return true;

        } else if ((r2x + r2w) >= r1x && (r2x + r2w) <= (r1x + r1w) && r2y >= r1y && r2y <= (r1y + r1h)) {
            return true;

        } else if ((r2x + r2w) >= r1x && (r2x + r2w) <= (r1x + r1w) && (r2y + r2h) >= r1y && (r2y + r2h) <= (r1y + r1h)) {
            return true;

        } else if (r2x >= r1x && r2x <= (r1x + r1w) && (r2y + r2h) >= r1y && (r2y + r2h) <= (r1y + r1h)) {
            return true;

        }

        return false;
    }

    /**
     * The absolute value of the distance between two points.
     *
     * @param x1 Point #1, X coordinate.
     * @param x2 Point #2, X coordinate.
     * @param y1 Point #1, Y coordinate.
     * @param y2 Point #2, Y coordinate.
     * @return The absolute value of the calculated distance.
     */
    public static int AbsDistance(int x1, int x2, int y1, int y2) {
        return (int) Math.sqrt((double) (((y1 - x1) * (y1 - x1)) + ((y2 - x2) * (y2 - x2))));
    }

    /**
     * Centers an MmgObj horizontally.
     *
     * @param obj The object to center.
     * @return A centered object.
     * @see MmgScreenData
     */
    public static MmgObj CenterHor(MmgObj obj) {
        MmgVector2 vec = new MmgVector2((int) (MmgScreenData.GetGameLeft() + (MmgScreenData.GetGameWidth() - obj.GetWidth()) / 2), obj.GetPosition().GetY());
        obj.SetPosition(vec);
        return obj;
    }

    /**
     * Centers an MmgObj vertically.
     *
     * @param obj The object to center.
     * @return A centered object.
     * @see MmgScreenData
     */
    public static MmgObj CenterVert(MmgObj obj) {
        MmgVector2 vec = new MmgVector2(obj.GetPosition().GetX(), (int) (MmgScreenData.GetGameTop() + (MmgScreenData.GetGameHeight() - obj.GetHeight()) / 2));
        obj.SetPosition(vec);
        return obj;
    }

    /**
     * Centers an MmgObj horizontally and vertically.
     *
     * @param obj The object to center.
     * @return A centered object.
     * @see MmgScreenData
     */
    public static MmgObj CenterHorAndVert(MmgObj obj) {
        obj = CenterHor(obj);
        obj = CenterVert(obj);
        return obj;
    }

    /**
     * Centers an MmgObj horizontally and places it at the top of the screen
     * vertically.
     *
     * @param obj The object to position.
     * @return A repositioned object.
     * @see MmgScreenData
     */
    public static MmgObj CenterHorAndTop(MmgObj obj) {
        obj = CenterHor(obj);
        MmgVector2 pos = obj.GetPosition().Clone();
        pos.SetY(MmgScreenData.GetGameTop());
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Centers an MmgObj horizontally and places it at the bottom of the screen
     * vertically.
     *
     * @param obj The object to position.
     * @return A repositioned object.
     */
    public static MmgObj CenterHorAndBot(MmgObj obj) {
        obj = CenterHor(obj);
        MmgVector2 pos = obj.GetPosition().Clone();
        int h = obj.GetHeight();
        pos.SetY((MmgScreenData.GetGameTop() + MmgScreenData.GetGameHeight()) - h);
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Centers an MmgObj horizontally and vertically.
     *
     * @param obj The object to position.
     * @return A repositioned object.
     */
    public static MmgObj CenterHorAndMid(MmgObj obj) {
        return CenterHorAndVert(obj);
    }

    /**
     * Repositions an MmgObj horizontally left and vertically top.
     *
     * @param obj The object to position.
     * @return A repositioned object.
     */
    public static MmgObj LeftHorAndTop(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        pos.SetX(MmgScreenData.GetGameLeft());
        pos.SetY(MmgScreenData.GetGameTop());
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Repositions an MmgObj horizontally left, and vertically top.
     *
     * @param obj The object to position.
     * @return A repositioned object.
     */
    public static MmgObj LeftHorAndMid(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        pos.SetX(MmgScreenData.GetGameLeft());
        obj.SetPosition(pos);
        return CenterVert(obj);
    }

    /**
     * Repositions an MmgObj horizontally left and vertically bottom.
     *
     * @param obj The object to reposition.
     * @return A repositioned object.
     */
    public static MmgObj LeftHorAndBot(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        int h = obj.GetHeight();
        pos.SetY((MmgScreenData.GetGameTop() + MmgScreenData.GetGameHeight()) - h);
        pos.SetX(MmgScreenData.GetGameLeft());
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Repositions an MmgObj horizontally right, and vertically top.
     *
     * @param obj The object to reposition.
     * @return A repositioned object.
     */
    public static MmgObj RightHorAndTop(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        int w = obj.GetWidth();
        pos.SetX((MmgScreenData.GetGameLeft() + MmgScreenData.GetGameWidth()) - w);
        pos.SetY(MmgScreenData.GetGameTop());
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Repositions an MmgObj horizontally right, and vertically middle.
     *
     * @param obj The object to reposition.
     * @return A repositioned object.
     */
    public static MmgObj RightHorAndMid(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        int w = obj.GetWidth();
        pos.SetX((MmgScreenData.GetGameLeft() + MmgScreenData.GetGameWidth()) - w);
        obj.SetPosition(pos);
        return CenterVert(obj);
    }

    /**
     * Repositions an MmgObj horizontally right, and vertically bottom.
     *
     * @param obj The object to reposition.
     * @return A repositioned object.
     */
    public static MmgObj RightHorAndBot(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        int h = obj.GetHeight();
        int w = obj.GetWidth();
        pos.SetY((MmgScreenData.GetGameTop() + MmgScreenData.GetGameHeight()) - h);
        pos.SetX((MmgScreenData.GetGameLeft() + MmgScreenData.GetGameWidth()) - w);
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Scaling method, applies the scale X to the given argument.
     *
     * @param val The value to scale.
     * @return A scaled value.
     */
    public static int ScaleValue(int val) {
        return (int) ((double) val * MmgScreenData.GetScaleX());
    }

    /**
     * Scaling method, applies the scale x to the given argument.
     *
     * @param val The value to scale.
     * @return A scaled value.
     */
    public static float ScaleValue(float val) {
        return (float) ((double) val * MmgScreenData.GetScaleX());
    }

    /**
     * Scaling method, applies the scale x to the given argument.
     *
     * @param val The value to scale.
     * @return A scaled value.
     */
    public static double ScaleValue(double val) {
        return (double) ((double) val * MmgScreenData.GetScaleX());
    }
    
    public static final int GetRandomInt(int exclusiveUpperBound) {
        return rando.nextInt(exclusiveUpperBound);
    }

    /**
     * Centralized logging method for standard out logging.
     *
     * @param s The string to be logged.
     */
    public static final void wr(String s) {
        if (LOGGING = true) {
            System.out.println(s);
        }
    }

    /**
     * Centralized logging method for standard error logging.
     *
     * @param e The exception to be logged.
     */
    public static final void wrErr(Exception e) {
        if (LOGGING = true) {
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
        if (LOGGING = true) {
            System.err.println(s);
        }
    }

    @SuppressWarnings("UnusedAssignment")
    public static final MmgBmp GetBasicCachedBmp(String path, String imgId) {
        MmgBmp lval = null;
        if (BMP_CACHE_ON == true) {
            if (MmgMediaTracker.HasKey(imgId) == true) {
                lval = new MmgBmp(MmgMediaTracker.GetValue(imgId));
                lval.SetMmgColor(null);
            } else {
                lval = MmgHelper.GetBasicBmp(path);
                MmgMediaTracker.CacheImage(imgId, lval.GetImage());
            }
        } else {
            lval = MmgHelper.GetBasicBmp(path);
        }
        return lval;
    }

    @SuppressWarnings("UnusedAssignment")
    public static final MmgBmp GetBasicCachedBmp(String imgId) {
        MmgBmp lval = null;
        if (BMP_CACHE_ON == true) {
            if (MmgMediaTracker.HasKey(imgId) == true) {
                lval = new MmgBmp(MmgMediaTracker.GetValue(imgId));
                lval.SetMmgColor(null);
            }
        }
        return lval;
    }    
    
    public static final void ListCacheEntries() {
        if (BMP_CACHE_ON == true) {
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
            MmgHelper.wrErr(e);
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
            MmgHelper.wrErr(e);
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

package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import net.middlemind.MmgGameApiJava.MmgBase.MmgCfgFileEntry.CfgEntryType;

/**
 * Class that provides high level helper methods. 
 * Created by Middlemind Games 08/29/2016
 *
 * @author Victor G. Brusca
 */
public class MmgHelper {
   
    /**
     * Controls if logging is turned on or off.
     */
    public static boolean LOGGING = true;
    
    /**
     * Controls if the MmgBmp cache is used when loading image resources.
     */
    public static boolean BMP_CACHE_ON = true;
    
    /**
     * Controls if the MmgSound cache is used when loading sound resources.
     */
    public static boolean SND_CACHE_ON = true;    
    
    /**
     * A random number generator.
     */
    private static Random rando = new Random(System.currentTimeMillis());    

    /**
     * A static class method that writes MmgCfgFileEntry objects to a target file.
     * 
     * @param file      The file to write the class config data to.
     * @param data      The array of MmgCfgFileEntry objects to write.
     * @return          A boolean indicating if the write was handled successfully.
     */
    public static boolean WriteClassConfigFile(String file, MmgCfgFileEntry[] data) {
        boolean ret = false;
        
        try {
            if(data != null && data.length > 0) {                
                MmgCfgFileEntry cfe = null;
                FileWriter fw = new FileWriter(file, false);
                BufferedWriter bw = new BufferedWriter(fw);
                Arrays.sort(data);
                int len = data.length;
                
                for(int i = 0; i < len; i++) {
                    cfe = data[i];
                    bw.write(cfe.ToString());
                    bw.newLine();
                }

                try {
                    bw.close();
                }catch(Exception ex) {
                    
                }                
                
                ret = true;
                
            } else {
                ret = false;
                
            }
            
        }catch(Exception e) {
            ret = false;
            wrErr(e);
            
        }
        
        return ret;
    }    
    
    /**
     * A static class method that writes MmgCfgFileEntry objects to a target file.
     * 
     * @param file      The file to write the class config data to. 
     * @param data      A Hashtable that contains the MmgCfgFileEntry objects to write.
     * @return          A boolean indicating if the write was handled successfully.
     */
    public static boolean WriteClassConfigFile(String file, Hashtable<String, MmgCfgFileEntry> data) {
        boolean ret = false;
        
        try {
            if(data != null && data.size() > 0) {
                MmgCfgFileEntry cfe = null;
                FileWriter fw = new FileWriter(file, false);
                BufferedWriter bw = new BufferedWriter(fw);
                Set<String> keys = data.keySet();                
                String[] nKeys = new String[keys.size()];
                nKeys = keys.toArray(nKeys);
                Arrays.sort(nKeys);
                int len = nKeys.length;
                
                //for(String key : keys) {
                for(int i = 0; i < len; i++) {
                    //cfe = data.get(key);
                    cfe = data.get(nKeys[i]);
                    bw.write(cfe.ToString());
                    bw.newLine();
                }

                try {
                    bw.close();
                }catch(Exception ex) {
                    
                }                
                
                ret = true;
                
            } else {
                ret = false;
                
            }
            
        }catch(Exception e) {
            ret = false;
            wrErr(e);
            
        }
        
        return ret;
    }    
    
    /**
     * A static class method that reads a Hashtable of MmgCfgFileEntry objects from a valid class config file.
     * 
     * @param file      The target class config file to read.
     * @return          A Hashtable with key, MmgCfgFileEntry pairs, loading from the class config file.
     */
    public static Hashtable<String, MmgCfgFileEntry> ReadClassConfigFile(String file) {
        Hashtable<String, MmgCfgFileEntry> ret = new Hashtable();
        
        try {
            MmgCfgFileEntry cfe = null;
            File f = null;
            String nFile = file.replace(".txt", MmgScreenData.GetScreenWidth() + "x" + MmgScreenData.GetScreenHeight() + ".txt");
            f = new File(nFile);            
            if(!f.exists()) {
                f = new File(file);
            }
            
            if(f.exists()) {                
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                String[] data = null;
                
                while(line != null) {
                    cfe = new MmgCfgFileEntry();
                    line = line.trim();
                    if(line.equals("") == false && line.charAt(0) != '#') {
                        if(line.indexOf("=") != -1) {
                            data = line.split("=");
                            if(data.length == 2) {
                                cfe.cfgType = CfgEntryType.TYPE_DOUBLE;
                                cfe.number = new Double(data[1]);
                                cfe.name = data[0];
                                ret.put(data[0], cfe);
                            }
                        } else if(line.indexOf("->") != -1) {
                            data = line.split("->");
                            if(data.length == 2) {
                                cfe.cfgType = CfgEntryType.TYPE_STRING;                                
                                cfe.string = data[1];
                                cfe.name = data[0];                                
                                ret.put(data[0], cfe);
                            }                            
                        }
                    }
                    line = br.readLine();
                }
                
                try {
                    br.close();
                }catch(Exception ex) {
                    
                }
            }
        }catch(Exception e) {
            wrErr(e);
        }
        
        return ret;
    }    
    
    /**
     * A static method for creating an MmgBmp object filled with the provided color.
     * 
     * @param width     The width of the MmgBmp object created.
     * @param height    The height of the MmgBmp object created.
     * @param color     The MmgColor that is used to fill the MmgBmp object created.
     * @return          An MmgBmp object with the width and height provided filled with the specified color.
     */
    public static MmgBmp CreateFilledBmp(int width, int height, MmgColor color) {
        return CreateDrawableBmpSet(width, height, false, color).img;
    }
    
    /**
     * A static method for creating an MmgDrawableBmpSet of the specified width and height.
     * An MmgDrawableBmpSet contains an MmgPen wrapping a Graphics object and configured to write to the BufferedImage and
     * an MmgBmp object that wraps the BufferedImage.
     * 
     * @param width     The width of the MmgBmp object created.
     * @param height    The height of the MmgBmp object created.
     * @param alpha     A boolean flag indicating if transparency should be taken into consideration when creating new images.
     * @return          An MmgDrawableBmpSet that contains initialized objects needed to draw on an MmgBmp object.
     */
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
    
    /**
     * A static method for creating an MmgDrawableBmpSet of the specified width and height and filled with the specified color.
     * An MmgDrawableBmpSet contains an MmgPen wrapping a Graphics object and configured to write to the BufferedImage and
     * an MmgBmp object that wraps the BufferedImage.
     * 
     * @param width     The width of the MmgBmp object created.
     * @param height    The height of the MmgBmp object created.
     * @param alpha     A boolean flag indicating if transparency should be taken into consideration when creating new images.
     * @param color     The color used to fill the created MmgBmp object.
     * @return          An MmgDrawableBmpSet that contains initialized objects needed to draw on an MmgBmp object.
     */
    public static MmgDrawableBmpSet CreateDrawableBmpSet(int width, int height, boolean alpha, MmgColor color) {
        MmgDrawableBmpSet dBmpSet = MmgHelper.CreateDrawableBmpSet(width, height, alpha);
        Color c = dBmpSet.graphics.getColor();
        dBmpSet.graphics.setColor(color.GetColor());
        dBmpSet.graphics.fillRect(0, 0, width, height);
        dBmpSet.graphics.setColor(c);
        return dBmpSet;
    }    
    
    /**
     * A static class method used to determine if there is a collision between the X, Y coordinates and the MmgRect.
     * 
     * @param x     The X coordinate used to test for a collision.
     * @param y     The Y coordinate used to test for a collision.
     * @param r     The MmgRect used to test for a collision.
     * @return      A boolean indicating if there has been a collision detected.
     */
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
     * @param r1x       Rectangle #1, X coordinate.
     * @param r1y       Rectangle #1, Y coordinate.
     * @param w         Rectangle #1, width.
     * @param h         Rectangle #1, height.
     * @param r         Rectangle #2.
     * @return          True if there is a collision of the two rectangles.
     */
    public static boolean RectCollision(int r1x, int r1y, int w, int h, MmgRect r) {
        int r2x = r.GetLeft();
        int r2y = r.GetTop();
        return RectCollision(r1x, r1y, w, h, r2x, r2y, (r.GetRight() - r.GetLeft()), (r.GetBottom() - r.GetTop()));
    }

    /**
     * Tests for collision of two rectangles.
     *
     * @param src       Rectangle #1.
     * @param dest      Rectangle #2.
     * @return          True if there is a collision of the two rectangles.
     */
    public static boolean RectCollision(MmgRect src, MmgRect dest) {
        return RectCollision(src.GetLeft(), src.GetTop(), src.GetWidth(), src.GetHeight(), dest);
    }
    
    /**
     * A static method that tests for the collision of two MmgObj instances.
     * 
     * @param src       The source MmgObj to test for a collision.
     * @param dest      The destination MmgObj to test for a collision.
     * @return          A boolean indicating if a collision was found or not.
     */
    public static boolean RectCollision(MmgObj src, MmgObj dest) {
        return RectCollision(src.GetX(), src.GetY(), src.GetWidth(), src.GetHeight(), dest.GetX(), dest.GetY(), dest.GetWidth(), dest.GetHeight());
    }    

    /**
     * A static method that tests for a collision using an MmgVector2 and a width and height to define a collision rectangle.
     * 
     * @param src       The source point used in the collision test.
     * @param sW        The width used in the source rectangle definition.
     * @param sH        The height used in the source rectangle definition.
     * @param dest      The destination point used in the collision test.
     * @param dW        The width used in the destination rectangle definition.
     * @param dH        The height used in the destination rectangle definition.
     * @return          A boolean indicating if a collision was found or not.
     */
    public static boolean RectCollision(MmgVector2 src, int sW, int sH, MmgVector2 dest, int dW, int dH) {
        return RectCollision(src.GetX(), src.GetY(), sW, sH, dest.GetX(), dest.GetY(), dW, dH);
    }    
    
    /**
     * Tests for collisions of two rectangles.
     *
     * @param r1x       Rectangle #1, X coordinate.
     * @param r1y       Rectangle #1, Y coordinate.
     * @param r1w       Rectangle #1, width.
     * @param r1h       Rectangle #1, height.
     * @param r2x       Rectangle #2, X coordinate.
     * @param r2y       Rectangle #2, Y coordinate.
     * @param r2w       Rectangle #2, width.
     * @param r2h       Rectangle #2, height.
     * @return          True if there is a collision of the two rectangles.
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
     * @param x1        Point #1, X coordinate.
     * @param x2        Point #2, X coordinate.
     * @param y1        Point #1, Y coordinate.
     * @param y2        Point #2, Y coordinate.
     * @return          The absolute value of the calculated distance.
     */
    public static int AbsDistance(int x1, int x2, int y1, int y2) {
        return (int) Math.sqrt((double) (((y1 - x1) * (y1 - x1)) + ((y2 - x2) * (y2 - x2))));
    }

    /**
     * Centers an MmgObj horizontally.
     *
     * @param obj       The object to center.
     * @return          A centered object.
     * @see             MmgScreenData
     */
    public static MmgObj CenterHor(MmgObj obj) {
        MmgVector2 vec = new MmgVector2((int) (MmgScreenData.GetGameLeft() + (MmgScreenData.GetGameWidth() - obj.GetWidth()) / 2), obj.GetPosition().GetY());
        obj.SetPosition(vec);
        return obj;
    }

    /**
     * Centers an MmgObj vertically.
     *
     * @param obj       The object to center.
     * @return          A centered object.
     * @see             MmgScreenData
     */
    public static MmgObj CenterVert(MmgObj obj) {
        MmgVector2 vec = new MmgVector2(obj.GetPosition().GetX(), (int) (MmgScreenData.GetGameTop() + (MmgScreenData.GetGameHeight() - obj.GetHeight()) / 2));
        obj.SetPosition(vec);
        return obj;
    }

    /**
     * Centers an MmgObj horizontally and vertically.
     *
     * @param obj       The object to center.
     * @return          A centered object.
     * @see             MmgScreenData
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
     * @param obj       The object to position.
     * @return          A repositioned object.
     * @see             MmgScreenData
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
     * @param obj       The object to position.
     * @return          A repositioned object.
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
     * @param obj       The object to position.
     * @return          A repositioned object.
     */
    public static MmgObj CenterHorAndMid(MmgObj obj) {
        return CenterHorAndVert(obj);
    }

    /**
     * Repositions an MmgObj horizontally left and vertically top.
     *
     * @param obj       The object to position.
     * @return          A repositioned object.
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
     * @param obj       The object to position.
     * @return          A repositioned object.
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
     * @param obj       The object to reposition.
     * @return          A repositioned object.
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
     * @param obj       The object to reposition.
     * @return          A repositioned object.
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
     * @param obj       The object to reposition.
     * @return          A repositioned object.
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
     * @param obj       The object to reposition.
     * @return          A repositioned object.
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
     * @param val       The value to scale.
     * @return          A scaled value.
     */
    public static int ScaleValue(int val) {
        return (int) ((double) val * MmgScreenData.GetScaleX());
    }

    /**
     * Scaling method, applies the scale x to the given argument.
     *
     * @param val       The value to scale.
     * @return          A scaled value.
     */
    public static float ScaleValue(float val) {
        return (float) ((double) val * MmgScreenData.GetScaleX());
    }

    /**
     * Scaling method, applies the scale x to the given argument.
     *
     * @param val       The value to scale.
     * @return          A scaled value.
     */
    public static double ScaleValue(double val) {
        return (double) ((double) val * MmgScreenData.GetScaleX());
    }
    
    /**
     * A static method used to generate a random integer.
     * 
     * @param exclusiveUpperBound       An exclusive upper bound on the random number generated.
     * @return                          An integer between 0 not including the exclusiveUpperBound
     */
    public static int GetRandomInt(int exclusiveUpperBound) {
        return rando.nextInt(exclusiveUpperBound);
    }

    /**
     * Centralized logging method for standard out logging.
     *
     * @param s     The string to be logged.
     */
    public static void wr(String s) {
        if (LOGGING = true) {
            System.out.println(s);
        }
    }

    /**
     * Centralized logging method for standard error logging.
     *
     * @param e     The exception to be logged.
     */
    public static void wrErr(Exception e) {
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
     * @param s     The string to be logged.
     */
    public static void wrErr(String s) {
        if (LOGGING = true) {
            System.err.println(s);
        }
    }

    /**
     * A static method used to create an MmgSound object from a sound resource file.
     * 
     * @param path      The path of the sound resource loaded.
     * @param sndId     The id to use when storing the sound resource in the sound resource cache.
     * @return          An MmgSound object created from the specified resource file or loaded from the sound resource cache.
     */
    @SuppressWarnings("UnusedAssignment")
    public static MmgSound GetBasicCachedSound(String path, String sndId) {
        MmgSound lval = null;
        if (SND_CACHE_ON == true) {
            if (MmgMediaTracker.HasSoundKey(sndId) == true) {
                lval = new MmgSound(MmgMediaTracker.GetSoundValue(sndId));
            } else {
                lval = MmgHelper.GetBasicSound(path);
                MmgMediaTracker.CacheSound(sndId, lval.GetSound());
            }
        } else {
            lval = MmgHelper.GetBasicSound(path);
        }
        return lval;
    }
    
    /**
     * A static method used to create an MmgSound object from a sound resource file.
     * 
     * @param sndId     The id to use when pulling the sound resource from the sound cache.
     * @return          An MmgSound object loaded from the sound resource cache.
     */
    @SuppressWarnings("UnusedAssignment")
    public static MmgSound GetBasicCachedSound(String sndId) {
        MmgSound lval = null;
        if (SND_CACHE_ON == true) {
            if (MmgMediaTracker.HasSoundKey(sndId) == true) {
                lval = new MmgSound(MmgMediaTracker.GetSoundValue(sndId));
            }
        }
        return lval;
    }     
    
    /**
     * A static method used to create an MmgBmp object loaded from an image resource file.
     * 
     * @param path      The path to the image resource file to load.
     * @param imgId     The id used to store the image resource file into the image resource cache.
     * @return          An MmgBmp object loaded from the image file or the image resource cache.
     */ 
    @SuppressWarnings("UnusedAssignment")
    public static MmgBmp GetBasicCachedBmp(String path, String imgId) {
        MmgBmp lval = null;
        if (BMP_CACHE_ON == true) {
            if (MmgMediaTracker.HasBmpKey(imgId) == true) {
                lval = new MmgBmp(MmgMediaTracker.GetBmpValue(imgId));
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

    /**
     * A static method used to create an MmgBmp object from an image resource file.
     * 
     * @param imgId     The id used to store the image resource file into the image resource cache.
     * @return          An MmgBmp object loaded from the image file or the image resource cache.
     */
    @SuppressWarnings("UnusedAssignment")
    public static MmgBmp GetBasicCachedBmp(String imgId) {
        MmgBmp lval = null;
        if (BMP_CACHE_ON == true) {
            if (MmgMediaTracker.HasBmpKey(imgId) == true) {
                lval = new MmgBmp(MmgMediaTracker.GetBmpValue(imgId));
                lval.SetMmgColor(null);
            }
        }
        return lval;
    }    
    
    /**
     * A static method to list the cached resources, images and sounds.
     */
    public static void ListCacheEntries() {
        int len;
        Object[] keys;
        String key;
        int i;
        
        if (BMP_CACHE_ON == true) {
            len = MmgMediaTracker.GetBmpCacheSize();
            keys = MmgMediaTracker.cacheBmp.keySet().toArray();
            for (i = 0; i < len; i++) {
                key = keys[i] + "";
                wr("BMP: " + i + " key: " + key);
            }
        }
        
        if (SND_CACHE_ON == true) {
            len = MmgMediaTracker.GetSoundCacheSize();
            keys = MmgMediaTracker.cacheSound.keySet().toArray();
            for (i = 0; i < len; i++) {
                key = keys[i] + "";
                wr("SND: " + i + " key: " + key);
            }
        }        
    }

    /**
     * Gets a basic sound from the file system.
     *
     * @param src       A path to a valid sound resource.
     * @return          A sound loaded from the file path.
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
     * Gets a basic bitmap from the file system.
     *
     * @param src       A path to a valid bitmap resource.
     * @return          A bitmap loaded from the file path.
     */
    public static MmgBmp GetBasicBmp(String src) {
        Image b = null;
        MmgBmp r = null;

        try {
            b = ImageIO.read(new File(src));
        } catch (Exception e) {
            MmgHelper.wrErr(e);
        }

        if (b != null) {
            b = MmgPen.ScaleImageStatic(b, MmgScreenData.GetScale());
            r = new MmgBmp(b);
            r.SetScaling(MmgVector2.GetUnitVec());
            r.SetPosition(MmgVector2.GetOriginVec());
            r.SetOrigin(MmgVector2.GetOriginVec());
            r.SetMmgColor(null);
        }

        return r;
    }

    /**
     * A static method for converting binary image data into an MmgBmp object.
     * 
     * @param d         The array of bytes representing the image data.
     * @return          An MmgBmp object that is created from the binary image data.
     */
    public static MmgBmp GetBinaryBmp(byte[] d) {
        Image b = null;
        MmgBmp r = null;

        try {
            b = ImageIO.read(new ByteArrayInputStream(d));
        } catch (Exception e) {
            MmgHelper.wrErr(e);
        }

        if (b != null) {
            b = MmgPen.ScaleImageStatic(b, MmgScreenData.GetScale());
            r = new MmgBmp(b);
            r.SetScaling(MmgVector2.GetUnitVec());
            r.SetPosition(MmgVector2.GetOriginVec());
            r.SetOrigin(MmgVector2.GetOriginVec());
            r.SetMmgColor(null);
        }

        return r;
    }

    /**
     * A static method for converting the lower level Java image into an MmgBmp object.
     * 
     * @param b         The Image instance used to create an MmgBmp object.
     * @return          An MmgBmp object that is created from the binary image data.
     */
    public static MmgBmp GetImageBmp(Image b) {
        MmgBmp r = null;

        if (b != null) {
            b = MmgPen.ScaleImageStatic(b, MmgScreenData.GetScale());
            r = new MmgBmp(b);
            r.SetScaling(MmgVector2.GetUnitVec());
            r.SetPosition(MmgVector2.GetOriginVec());
            r.SetOrigin(MmgVector2.GetOriginVec());
            r.SetMmgColor(null);
        }

        return r;
    }

    /**
     * A static method for converting the lower level Java image into an MmgBmp object.
     * 
     * @param b         The Image instance used to create an MmgBmp object.
     * @return          An MmgBmp object that is created from the binary image data.
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
     * @return              A new configured MmgMenuItem.
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

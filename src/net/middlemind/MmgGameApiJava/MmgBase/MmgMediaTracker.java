package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Image;
//import java.awt.MediaTracker;
import java.util.Hashtable;

/**
 * A local class that provides static access to a media tracker and image cache.
 * Created on 07-29-2015 by Middlemind Games
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
@SuppressWarnings("UseOfObsoleteCollectionType")
public class MmgMediaTracker {
    
    /**
     * MediaTracker used in tracking loading of AWT based images.
     */
    //public static MediaTracker mt = new MediaTracker(null);
    
    /**
     * Hashtable used to track loading of image resources, in a central place.
     */
    public static Hashtable<String, Image> ms = new Hashtable(100);
    public static boolean REMOVE_EXISTING = true;
    
    /**
     * Tracks the loading of images via the media tracker class.
     * 
     * @param imageId       The unique image id.
     * @param img           The image object to track.
     */
    /*
    public static void MediaTrackImage(int imageId, Image img) {        
        MmgMediaTracker.mt.removeImage(img, imageId);
        MmgMediaTracker.mt.addImage(img, imageId);
        try {
            MmgMediaTracker.mt.waitForID(imageId);
        }catch (Exception e) {
            MmgApiUtils.wrErr("Exception while loading image.");
            MmgApiUtils.wrErr(e);
        }
    }
    */
    
    /**
     * Stores cached images by an unique id string and an image class.
     * 
     * @param key       The unique image id.
     * @param val       The image object to cache.
     */
    public static void CacheImage(String key, Image val) {
        MmgApiUtils.wr("+++++++++ Cache image with key: " + key);
        if(MmgMediaTracker.HasKey(key) == false) {
            MmgMediaTracker.ms.put(key, val);
        }else {
            if(MmgMediaTracker.REMOVE_EXISTING == true) {
                MmgMediaTracker.RemoveByKey(key);
            }
            MmgMediaTracker.ms.put(key, val);
        }
    }
    
    public static int GetCacheSize() {
        MmgApiUtils.wr("+++++++++ Cache Image Size: " + ms.size());
        return ms.size();
    }
    
    /**
     * Gets the value of the image cache entry for the given key.
     * 
     * @param key       The key under which the image was stored.
     * @return          An image object.
     */
    public static Image GetValue(String key) {
        if(MmgMediaTracker.HasKey(key) == true) {
            return MmgMediaTracker.ms.get(key);
        }else {
            return null;
        }
    }
    
    /**
     * Returns true if the image cache hash table contains the given key.
     * 
     * @param key       The key to check existence for.
     * @return          True if the key exists in the image cache.
     */
    public static boolean HasKey(String key) {
        if(MmgMediaTracker.ms.containsKey(key) == true) {
            return true;
        }else {
            return false;
        }
    }
    
    /**
     * Returns true if the image cache has the given value.
     * 
     * @param img       The image to check existence for.
     * @return          True if the image argument exists as a value in the image cache.
     */
    public static boolean HasValue(Image img) {
        if(MmgMediaTracker.ms.containsValue(img) == true) {
            return true;
        }else {
            return false;
        }
    }
    
    /**
     * Removes an entry by key.
     * 
     * @param key       The key to use to remove a value for.
     * @return          Returns true if an entry was removed.
     */
    public static boolean RemoveByKey(String key) {
        if(MmgMediaTracker.HasKey(key) == true) {
            MmgMediaTracker.ms.remove(key);
            return true;
        }else {
            return false;
        }
    }
    
    /**
     * Removes an entry by key and value.
     * 
     * @param key       The key to use to remove an entry for.
     * @param img       The value to use to remove an entry for.
     * @return          Returns true if a key value pair was found and removed.
     */
    public static boolean RemoveByKeyValue(String key, Image img) {
        if(MmgMediaTracker.HasKey(key) == true) {
            MmgMediaTracker.ms.remove(key, img);
            return true;
        }else {
            return false;
        }
    }
}

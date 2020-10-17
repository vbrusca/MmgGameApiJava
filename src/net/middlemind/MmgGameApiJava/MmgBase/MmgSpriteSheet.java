package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * A class that splits a source image into frames, sprite animation frames, based on the provided dimension of each frame.
 * Created by Middlemind Games 08/14/2020
 * 
 * @author Victor G. Brusca
 */
public class MmgSpriteSheet {
    /**
     * The source image to split into frames.
     */
    private MmgBmp src;
    
    /**
     * The frames extracted from the source image.
     */
    private MmgBmp[] frames;
    
    /**
     * The width of a frame from the source image.
     */
    private int width;
    
    /**
     * The height of a frame from the source image.
     */
    private int height;
    
    /**
     * A constructor that takes a sprite frame source image and splits it into frames based on the image dimensions
     * and the provided frame dimensions.
     * 
     * @param Src       The source image to split into frames.
     * @param Width     The width of a frame from the source image.
     * @param Height    The height of a frame from the source image.
     */
    public MmgSpriteSheet(MmgBmp Src, int Width, int Height) {
        src = Src;
        width = Width;
        height = Height;
        Prep();
    }

    /**
     * A specialized constructor that takes an MmgSpriteSheet instance as an argument.
     * 
     * @param obj   The MmgSpriteSheet object to use as the basis for a new object.
     */
    public MmgSpriteSheet(MmgSpriteSheet obj) {
        if(obj.GetSrc() == null) {
            SetSrc(obj.GetSrc());
        } else {
            SetSrc(obj.GetSrc().CloneTyped());            
        }
        
        if(obj.GetFrames() == null) {
            SetFrames(obj.GetFrames());
        } else {
            int len = obj.GetFrameCount();
            frames = new MmgBmp[len];
            for(int i = 0; i < len; i++) {
                if(obj.GetFrames()[i] != null) {
                    frames[i] = obj.GetFrames()[i].CloneTyped();
                } else {
                    frames[i] = null;
                }
            }
        }
        
        SetWidth(obj.GetWidth());
        SetHeight(obj.GetHeight());
    }
    
    /**
     * Returns a cloned instance of this object.
     * 
     * @return      A cloned instance of this object.
     */
    public MmgSpriteSheet CloneTyped() {
        return new MmgSpriteSheet(this);
    }
    
    /**
     * This method prepares the individual frames by copying them out into individual images.
     */
    public void Prep() {
        int srcW = src.GetWidth();
        int frameCount = srcW / width;
        int posX = 0;
        MmgDrawableBmpSet bmpSet = null;
        frames = new MmgBmp[frameCount];
        
        for(int i = 0; i < frameCount; i++) {
            bmpSet = MmgHelper.CreateDrawableBmpSet(width, height, true);
            bmpSet.p.DrawBmp(src, new MmgRect(posX, 0, height, posX + width), new MmgRect(0, 0, height, width));
            frames[i] = bmpSet.img;
            posX += width;
        }
    }
    
    /**
     * Returns the number of frames in the sprite sheet.
     * 
     * @return      The number of frames in the sprite sheet.
     */
    public int GetFrameCount() {
        return frames.length;
    }
    
    /**
     * Returns the source image used as the basis for copying out sprite animation frames.
     * 
     * @return      The source image the sprite animation frames are based off of.
     */
    public MmgBmp GetSrc() {
        return src;
    }

    /**
     * Sets the source image used as the basis for copying out sprite animation frames.
     * 
     * @param b     The source image the sprite animation frames are based off of.   
     */
    public void SetSrc(MmgBmp b) {
        src = b;
    }

    /**
     * Returns the array of sprite animation frames.
     * 
     * @return      The array of sprite animation frames.
     */
    public MmgBmp[] GetFrames() {
        return frames;
    }

    /**
     * Sets the array of sprite animation frames.
     * 
     * @param f     The array of sprite animation frames.
     */
    public void SetFrames(MmgBmp[] f) {
        frames = f;
    }

    /**
     * Gets the width of a sprite animation frame.
     * 
     * @return  The width of a sprite animation frame.
     */
    public int GetWidth() {
        return width;
    }

    /**
     * Sets the width of a sprite animation frame.
     * 
     * @param w     The width of a sprite animation frame.
     */
    public void SetWidth(int w) {
        width = w;
    }

    /**
     * Gets the height of a sprite animation frame.
     * 
     * @return      The height of a sprite animation frame.
     */
    public int GetHeight() {
        return height;
    }

    /**
     * Sets the height of a sprite animation frame.
     * 
     * @param h     The height of a sprite animation frame.
     */
    public void SetHeight(int h) {
        height = h;
    }
    
    /**
     * Gets the MmgBmp frame at the given index.
     * 
     * @param i     The index of the frame at the given index.
     * @return      The MmgBmp of the frame at the given index.
     */
    public MmgBmp GetFrame(int i) {
        return frames[i];
    }
    
    /**
     * Sets the MmgBmp frame at the given index.
     * 
     * @param b     The MmgBmp of the frame at the given index.
     * @param i     The index of the frame at the given index.
     */
    public void SetFrame(MmgBmp b, int i) {
        frames[i] = b;
    }    
    
    /**
     * A method that checks the equality of this MmgSpriteSheet object and the given argument.
     * 
     * @param obj       The MmgSpriteSheet object to compare this object to.
     * @return          Returns true if the two objects are equal and false otherwise.
     */
    public boolean ApiEquals(MmgSpriteSheet obj) {
        if(obj == null) {
            return false;
        } else if(obj.equals(this)) {
            return true;
        }
        
        boolean ret = true;
        if(
            ((obj.GetSrc() == null && GetSrc() == null) || (obj.GetSrc() != null && GetSrc() != null && obj.GetSrc().ApiEquals(GetSrc())))
            && GetWidth() == obj.GetWidth() 
            && GetHeight() == obj.GetHeight()
        ) {
            int len1 = obj.GetFrameCount();
            int len2 = GetFrameCount();
            if(len1 == len2) {
                MmgObj m1;
                MmgObj m2;
                for(int i = 0; i < len1; i++) {
                    m1 = obj.GetFrame(i);
                    m2 = GetFrame(i);
                    if(
                        !((m1 == null && m2 == null) || (m1 != null && m2 != null && m1.ApiEquals(m2)))
                    ) {
                        ret = false;
                        break;
                    }
                }
            } else {
                ret = false;
            }
        } else {
            ret = false;
        }
        
        return ret;
    }
}
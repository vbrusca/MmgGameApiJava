package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * A class that represents a sprite image.
 * Created on June 1, 2005, 10:57 PM by Middlemind Games
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public class MmgSprite extends MmgObj {

    /**
     * The origin of the sprite image.
     */
    private MmgVector2 origin;
    
    /**
     * The scaling of the sprite image.
     */
    private MmgVector2 scaling;
    
    /**
     * The source drawing rectangle.
     */
    private MmgRect srcRect;
    
    /**
     * The destination drawing rectangle.
     */
    private MmgRect dstRect;
    
    /**
     * The bitmaps to use to draw the sprite's frames.
     */
    private MmgBmp[] b;
    
    /**
     * The rotation to apply to the sprite.
     */
    private float rotation;
    
    /**
     * The frame start.
     */
    private int frameStart;
    
    /**
     * The frame stop.
     */
    private int frameStop;
    
    /**
     * The frame index.
     */
    private int frameIdx;
    
    /**
     * The milliseconds per frame.
     */
    private long msPerFrame;

    /**
     * The frame start time.
     */
    private long frameStartTime;
    
    /**
     * The frame stop time.
     */
    private long frameStopTime;
    
    /**
     * True if there was a frame change.
     */
    private boolean frameChange;

    /**
     * Constructor that sets the MmgBmp array, the source rectangle, the destination rectangle,
     * the origin, the scaling vector, and the rotation.
     * 
     * @param t             The MmgBmp frames.
     * @param Src           The source drawing rectangle.
     * @param Dst           The destination drawing rectangle.
     * @param Origin        The drawing origin of the sprite.
     * @param Scaling       The scaling of the sprite.
     * @param Rotation      The rotation of the sprite
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgSprite(MmgBmp[] t, MmgRect Src, MmgRect Dst, MmgVector2 Origin, MmgVector2 Scaling, float Rotation) {
        SetRotation(Rotation);
        SetOrigin(Origin);
        SetScaling(Scaling);
        SetSrcRect(Src);
        SetDstRect(Dst);
        SetBmpArray(t);

        SetPosition(null);
        SetWidth(t[0].GetWidth());
        SetHeight(t[0].GetHeight());
        SetIsVisible(true);
        frameChange = true;
    }

    /**
     * Constructor that sets the MmgBmp frames, the position, the origin, the scaling vector, and the rotation.
     * 
     * @param t             The MmgBmp frames of the object.
     * @param Position      The position vector of the object.
     * @param Origin        The origin of the object.
     * @param Scaling       The scaling of the object.
     * @param Rotation      The rotation of the object.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgSprite(MmgBmp[] t, MmgVector2 Position, MmgVector2 Origin, MmgVector2 Scaling, float Rotation) {
        SetRotation(Rotation);
        SetOrigin(Origin);
        SetScaling(Scaling);
        MmgRect r = new MmgRect(Position, t[0].GetWidth(), t[0].GetHeight());
        SetSrcRect(r);
        SetDstRect(null);
        SetBmpArray(t);

        SetPosition(Position);
        SetWidth(t[0].GetWidth());
        SetHeight(t[0].GetHeight());
        SetIsVisible(true);
        frameChange = true;
    }

    /**
     * Constructor that sets the value of the class attributes based on the attributes
     * of the given argument.
     * 
     * @param spr       The MmgSprite object to base our class off of.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgSprite(MmgSprite spr) {
        SetRotation(spr.GetRotation());

        if (spr.GetOrigin() == null) {
            SetOrigin(spr.GetOrigin());
        } else {
            SetOrigin(spr.GetOrigin().Clone());
        }

        if (spr.GetScaling() == null) {
            SetScaling(spr.GetScaling());
        } else {
            SetScaling(spr.GetScaling().Clone());
        }

        if (spr.GetSrcRect() == null) {
            SetSrcRect(spr.GetSrcRect());
        } else {
            SetSrcRect(spr.GetSrcRect().Clone());
        }

        if (spr.GetDstRect() == null) {
            SetDstRect(spr.GetDstRect());
        } else {
            SetDstRect(spr.GetDstRect().Clone());
        }

        SetBmpArray(spr.GetBmpArray());

        if (spr.GetPosition() == null) {
            SetPosition(spr.GetPosition());
        } else {
            SetPosition(spr.GetPosition().Clone());
        }

        SetWidth(spr.GetWidth());
        SetHeight(spr.GetHeight());
        SetIsVisible(spr.GetIsVisible());
        frameChange = true;
    }

    /**
     * Clones this class.
     * 
     * @return      A clone of this class.
     */
    @Override
    public MmgObj Clone() {
        MmgSprite ret = new MmgSprite(this);
        return (MmgObj) ret;
    }

    /**
     * Gets the MmgBmp frames of this class.
     * 
     * @return      The MmgBmp frames of this class.
     */
    public MmgBmp[] GetBmpArray() {
        return b;
    }

    /**
     * Sets the MmgBmp frames of this class.
     * 
     * @param d      The MmgBmp frames of this class.
     */
    public void SetBmpArray(MmgBmp[] d) {
        b = d;
    }

    /**
     * Gets the drawing source rectangle.
     * 
     * @return      The drawing source rectangle.
     */
    public MmgRect GetSrcRect() {
        return srcRect;
    }

    /**
     * Sets the drawing source rectangle.
     * 
     * @param r     The drawing source rectangle.
     */
    public void SetSrcRect(MmgRect r) {
        srcRect = r;
    }

    /**
     * Gets the drawing destination rectangle.
     * 
     * @return      The drawing destination rectangle.
     */
    public MmgRect GetDstRect() {
        return dstRect;
    }

    /**
     * Sets the drawing destination rectangle.
     * 
     * @param r     The drawing destination rectangle.
     */
    public void SetDstRect(MmgRect r) {
        dstRect = r;
    }
    
    /**
     * Gets the rotation of the sprite.
     * 
     * @return      The rotation of the sprite.
     */
    public float GetRotation() {
        return rotation;
    }

    /**
     * Sets the rotation of the sprite.
     * 
     * @param r     The rotation of the sprite.
     */
    public void SetRotation(float r) {
        rotation = r;
    }

    /**
     * Gets the drawing origin vector.
     * 
     * @return      The drawing origin vector.
     */
    public MmgVector2 GetOrigin() {
        return origin;
    }

    /**
     * Sets the drawing origin vector.
     * 
     * @param v     The drawing origin vector.
     */
    public void SetOrigin(MmgVector2 v) {
        origin = v;
    }

    /**
     * Gets the drawing scaling vector.
     * 
     * @return      The drawing scaling vector.
     */
    public MmgVector2 GetScaling() {
        return scaling;
    }

    /**
     * Sets the drawing scaling vector.
     * 
     * @param v     The drawing scaling vector.
     */
    public void SetScaling(MmgVector2 v) {
        scaling = v;
    }

    /**
     * Gets the frame index.
     * 
     * @return      The frame index.
     */
    public int GetFrameIdx() {
        return frameIdx;
    }

    /**
     * Sets the frame index.
     * 
     * @param f     The frame index.
     */
    public void SetFrameIdx(int f) {
        frameIdx = f;
    }

    /**
     * Gets the frame start index.
     * 
     * @return      The frame start index.
     */
    public int GetFrameStart() {
        return frameStart;
    }

    /**
     * Sets the frame start index.
     * 
     * @param f     The frame start index.
     */
    public void SetFrameStart(int f) {
        frameStart = f;
    }

    /**
     * Gets the frame stop index.
     * 
     * @return      The frame stop index.
     */
    public int GetFrameStop() {
        return frameStop;
    }

    /**
     * Sets the frame stop index.
     * 
     * @param f     The frame stop index.
     */
    public void SetFrameStop(int f) {
        frameStop = f;
    }

    /**
     * Gets the milliseconds per frame.
     * 
     * @return      The milliseconds per frame.
     */
    public long GetMsPerFrame() {
        return msPerFrame;
    }

    /**
     * Sets the milliseconds per frame.
     * 
     * @param f     The milliseconds per frame.
     */
    public void SetMsPerFrame(long f) {
        msPerFrame = f;
    }

    /**
     * Draw this object.
     * 
     * @param p     The MmgPen used to draw this object.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (GetIsVisible() == true) {
            if (b[frameIdx] != null) {
                if (frameChange == true) {
                    frameStartTime = System.currentTimeMillis();
                    frameChange = false;
                }
                p.DrawBmp(b[frameIdx], GetPosition(), GetSrcRect(), GetDstRect(), GetScaling(), GetOrigin(), GetRotation());
                UpdateFrame();
            }
        } else {
            //do nothing
        }
    }

    /**
     * Update the current sprite animation frame index.
     */
    public void UpdateFrame() {
        frameStopTime = System.currentTimeMillis();
        if (frameStop - frameStart > 1) {
            if ((long) (frameStopTime - frameStartTime) > msPerFrame) {
                frameIdx++;
                if (frameIdx > frameStop) {
                    frameIdx = frameStart;
                }
                frameChange = true;
            }
        }
    }
}

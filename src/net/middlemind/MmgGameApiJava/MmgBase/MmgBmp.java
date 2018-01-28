package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.*;

/**
 * Class that wraps the lower level bitmap object. Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class MmgBmp extends MmgObj {

    /**
     * Drawing mode to determine the best way to handle drawing a bitmap.
     */
    public enum MmgBmpDrawMode {

        DRAW_BMP_FULL,
        DRAW_BMP_BASIC_CACHE,
        DRAW_BMP_BASIC
    };

    /**
     * The initial value to use for bitmap IDs in unique id mode.
     */
    private static int ID_SRC = 0;

    /**
     * The origin of this object.
     */
    private MmgVector2 origin;

    /**
     * The scaling to apply to this object, if defined.
     */
    private MmgVector2 scaling;

    /**
     * The source drawing rectangle if defined.
     */
    private MmgRect srcRect;

    /**
     * The destination drawing rectangle if defined.
     */
    private MmgRect dstRect;

    /**
     * The image representing this object, if defined.
     */
    private Image b;

    /**
     * The rotation to apply to this object, if defined.
     */
    private float rotation;

    /**
     * The string representation of this objects id.
     */
    private String idStr;

    /**
     * The integer representation of this objects id.
     */
    private int id;

    /**
     * The strategy to use when drawing bitmaps.
     */
    public MmgBmpDrawMode DRAW_MODE = MmgBmpDrawMode.DRAW_BMP_BASIC;

    /**
     * Generic constructor.
     */
    public MmgBmp() {
        super();
        origin = new MmgVector2(0, 0);
        scaling = new MmgVector2(1, 1);
        srcRect = new MmgRect(0, 0, 0, 0);
        dstRect = new MmgRect(0, 0, 0, 0);
        b = null;
        rotation = 0f;
        SetBmpId();
    }

    /**
     * Construct from a previous instance of MmgObj.
     *
     * @param obj The object to create this class from.
     */
    public MmgBmp(MmgObj obj) {
        super(obj);
        origin = new MmgVector2(0, 0);
        scaling = new MmgVector2(1, 1);
        srcRect = new MmgRect(0, 0, 0, 0);
        dstRect = new MmgRect(0, 0, 0, 0);
        b = null;
        rotation = 0f;
        SetBmpId();
    }

    /**
     * Construct from a previous instance of MmgBmp.
     *
     * @param bmp The object to create this class from.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgBmp(MmgBmp bmp) {
        SetRotation(bmp.GetRotation());

        if (bmp.GetOrigin() == null) {
            SetOrigin(bmp.GetOrigin());
        } else {
            SetOrigin(bmp.GetOrigin().Clone());
        }

        if (bmp.GetSrcRect() == null) {
            SetSrcRect(bmp.GetSrcRect());
        } else {
            SetSrcRect(bmp.GetSrcRect().Clone());
        }

        if (bmp.GetDstRect() == null) {
            SetDstRect(bmp.GetDstRect());
        } else {
            SetDstRect(bmp.GetDstRect().Clone());
        }

        SetTexture2D(bmp.GetTexture2D());

        if (bmp.GetPosition() == null) {
            SetPosition(bmp.GetPosition());
        } else {
            SetPosition(bmp.GetPosition().Clone());
        }

        if (bmp.GetScaling() == null) {
            SetScaling(bmp.GetScaling());
        } else {
            SetScaling(bmp.GetScaling().Clone());
        }

        SetWidth(bmp.GetUnscaledWidth());
        SetHeight(bmp.GetUnscaledHeight());
        SetIsVisible(bmp.GetIsVisible());

        if (bmp.GetMmgColor() == null) {
            SetMmgColor(bmp.GetMmgColor());
        } else {
            SetMmgColor(bmp.GetMmgColor().Clone());
        }
        SetBmpId();
    }

    /**
     * Construct from a lower level Image objects.
     *
     * @param t The object to create this instance from.
     * @see Image
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgBmp(Image t) {
        SetRotation(0f);
        SetOrigin(MmgVector2.GetOriginVec());
        SetScaling(MmgVector2.GetUnitVec());
        MmgRect r = new MmgRect(MmgVector2.GetOriginVec(), t.getWidth(null), t.getHeight(null));
        SetSrcRect(r);
        SetDstRect(null);
        SetTexture2D(t);

        SetPosition(MmgVector2.GetOriginVec());
        SetWidth(b.getWidth(null));
        SetHeight(b.getHeight(null));
        SetIsVisible(true);
        SetMmgColor(null);
        SetBmpId();
    }

    /**
     * Construct this instance from a lower level image object and some
     * rendering hints.
     *
     * @param t The lower level Image object to create this instance from.
     * @param Src The source drawing rectangle.
     * @param Dst The destination drawing rectangle.
     * @param Origin The origin this image should be rotated from.
     * @param Scaling The scaling values to use when drawing this image.
     * @param Rotation The rotation values to use when drawing this image.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgBmp(Image t, MmgRect Src, MmgRect Dst, MmgVector2 Origin, MmgVector2 Scaling, float Rotation) {
        SetRotation(Rotation);
        SetOrigin(Origin);
        SetScaling(Scaling);
        SetSrcRect(Src);
        SetDstRect(Dst);
        SetTexture2D(t);

        SetPosition(MmgVector2.GetOriginVec());
        SetWidth(b.getWidth(null));
        SetHeight(b.getHeight(null));
        SetIsVisible(true);
        SetMmgColor(null);
        SetBmpId();
    }

    /**
     * Construct this instance from a lower level image object and some
     * rendering hints.
     *
     * @param t The lower level Image object to create this instance from.
     * @param Position The position this object should be drawn at.
     * @param Origin The origin this image should be rotated from.
     * @param Scaling The scaling values to use when drawing this image.
     * @param Rotation The rotation values to use when drawing this image.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgBmp(Image t, MmgVector2 Position, MmgVector2 Origin, MmgVector2 Scaling, float Rotation) {
        SetRotation(Rotation);
        SetOrigin(Origin);
        SetScaling(Scaling);
        MmgRect r = new MmgRect(Position, t.getWidth(null), t.getHeight(null));
        SetSrcRect(r);
        SetDstRect(null);
        SetTexture2D(t);

        SetPosition(Position);
        SetWidth(b.getWidth(null));
        SetHeight(b.getHeight(null));
        SetIsVisible(true);
        SetMmgColor(null);
        SetBmpId();
    }

    /**
     * Returns an id string, used in image caching, based on rotation.
     *
     * @param rotation The rotation value to use in id string creation.
     * @return A new id string.
     * @see MmgPen
     */
    public String GetIdStr(float rotation) {
        return (idStr + "_" + rotation);
    }

    /**
     * Returns an id string, used in image caching, based on scaling.
     *
     * @param scaling The scaling value to use in id string creation.
     * @return A new id string.
     * @see MmgPen
     */
    public String GetIdStr(MmgVector2 scaling) {
        return (idStr + "_" + scaling.GetXFloat() + "x" + scaling.GetYFloat());
    }

    /**
     *
     *
     * @param rotation
     * @param scaling
     * @return
     */
    public String GetIdStr(float rotation, MmgVector2 scaling) {
        return (idStr + "_" + rotation + "_" + scaling.GetXFloat() + "x" + scaling.GetYFloat());
    }

    /**
     * Id helper method, takes rotation into account when making an id.
     *
     * @param rotation The rotation of the bitmap.
     * @return The unique id of the bitmap.
     */
    public int GetId(float rotation) {
        return Integer.parseInt((id + "0" + (int) (rotation)));
    }

    /**
     * Id helper method, takes scaling into account when making an id.
     *
     * @param scaling The scaling to apply to the object.
     * @return The unique id of the bitmap.
     */
    public int GetId(MmgVector2 scaling) {
        return Integer.parseInt((idStr + "0" + (int) (scaling.GetXFloat() * 10) + "0" + (int) (scaling.GetYFloat() * 10)));
    }

    /**
     * If helper method, takes rotation, and scaling into account when making an
     * id.
     *
     * @param rotation The rotation of the bitmap.
     * @param scaling The scaling of the bitmap.
     * @return The unique id of the bitmap.
     */
    public int GetId(float rotation, MmgVector2 scaling) {
        return Integer.parseInt((idStr + "0" + (int) (rotation) + "0" + (int) (scaling.GetXFloat() * 10) + "0" + (int) (scaling.GetYFloat() * 10)));
    }

    /**
     * Get the unique id of the bitmap in string form.
     *
     * @return The string form of the unique id.
     */
    public String GetBmpIdStr() {
        return idStr;
    }

    /**
     * Sets the string form of the id.
     *
     * @param IdStr A unique id string.
     */
    public void SetBmpIdStr(String IdStr) {
        idStr = IdStr;
    }

    /**
     * Gets the string form of the id.
     *
     * @return A unique id integer.
     */
    public int GetBmpId() {
        return id;
    }

    /**
     * Sets the unique id integer and string representations using a common
     * method.
     */
    private void SetBmpId() {
        id = MmgBmp.ID_SRC;
        idStr = (id + "");
        MmgBmp.ID_SRC++;
    }

    /**
     * Clones the current object.
     *
     * @return Returns a new MmgObj based on the original MmgObj.
     */
    @Override
    public MmgObj Clone() {
        MmgBmp ret = new MmgBmp(this);
        return (MmgObj) ret;
    }

    /**
     * Returns the image of this bitmap.
     *
     * @return This bitmaps image.
     */
    public Image GetTexture2D() {
        return b;
    }

    /**
     * Sets the image of this bitmap.
     *
     * @param d The image to set for this bitmap.
     */
    public void SetTexture2D(Image d) {
        b = d;
    }

    /**
     * Gets the image of this bitmap. Same as GetTexture2D.
     *
     * @return The image of this bitmap.
     */
    public Image GetImage() {
        return GetTexture2D();
    }

    /**
     * Sets the image of this bitmap. Same as SetTexture2D.
     *
     * @param d The image to set for this bitmap.
     */
    public void SetImage(Image d) {
        SetTexture2D(d);
    }

    /**
     * Gets the source drawing rectangle of this bitmap. Only used by drawing
     * methods in the MmgPen class that supports, source, or source, destination
     * drawing methods.
     *
     * @return The source drawing rectangle.
     * @see MmgPen
     */
    public MmgRect GetSrcRect() {
        return srcRect;
    }

    /**
     * Sets the source drawing rectangle. Only used by drawing methods in the
     * MmgPen class that supports, source, or source, destination drawing
     * methods.
     *
     * @param r The source drawing rectangle.
     * @see MmgPen
     */
    public void SetSrcRect(MmgRect r) {
        srcRect = r;
    }

    /**
     * Gets the destination drawing rectangle.
     *
     * @return The destination drawing rectangle.
     */
    public MmgRect GetDstRect() {
        return dstRect;
    }

    /**
     * Sets the destination drawing rectangle.
     *
     * @param r The destination drawing rectangle.
     */
    public void SetDstRect(MmgRect r) {
        dstRect = r;
    }

    /**
     * Gets the rotation of the bitmap.
     *
     * @return The rotation of the bitmap.
     */
    public float GetRotation() {
        return rotation;
    }

    /**
     * Sets the rotation of the bitmap.
     *
     * @param r The rotation of the bitmap.
     */
    public final void SetRotation(float r) {
        rotation = r;
    }

    /**
     * Gets the origin used in drawing the bitmap.
     *
     * @return The drawing origin of the bitmap.
     */
    public MmgVector2 GetOrigin() {
        return origin;
    }

    /**
     * Sets the origin used in drawing the bitmap.
     *
     * @param v The drawing origin of the bitmap.
     */
    public void SetOrigin(MmgVector2 v) {
        origin = v;
    }

    /**
     * Gets the scaling value used to scale the bitmap.
     *
     * @return The drawing scaling value.
     */
    public MmgVector2 GetScaling() {
        return scaling;
    }

    /**
     * Sets the scaling value used to scale the bitmap.
     *
     * @param v The drawing scaling value.
     */
    public void SetScaling(MmgVector2 v) {
        scaling = v;
    }

    /**
     * Gets the scaled height of this bitmap.
     *
     * @return The scaled height of this bitmap.
     */
    public double GetScaledHeight() {
        if (GetScaling() == null) {
            return super.GetHeight();
        } else {
            return ((double) super.GetHeight() * GetScaling().GetXDouble());
        }
    }

    /**
     * Gets the un-scaled, original height of the bitmap.
     *
     * @return The un-scaled, original height of the bitmap.
     */
    public int GetUnscaledHeight() {
        return super.GetHeight();
    }

    /**
     * Gets the scaled height of the bitmap.
     *
     * @return The scaled height of the bitmap.
     */
    @Override
    public int GetHeight() {
        return (int) GetScaledHeight();
    }

    /**
     * Gets the scaled height of the bitmap in float form.
     *
     * @return The scaled height of the bitmap.
     */
    public float GetHeightFloat() {
        return (float) GetScaledHeight();
    }

    /**
     * Gets the un-scaled, original width of the bitmap.
     *
     * @return The un-scaled, original width of the bitmap.
     */
    public int GetUnscaledWidth() {
        return super.GetWidth();
    }

    /**
     * Gets the scaled width of the bitmap.
     *
     * @return The scaled width of the bitmap.
     */
    public double GetScaledWidth() {
        if (GetScaling() == null) {
            return super.GetWidth();
        } else {
            return ((double) super.GetWidth() * GetScaling().GetYDouble());
        }
    }

    /**
     * Gets the scaled width of the bitmap.
     *
     * @return The scaled width of the bitmap.
     */
    @Override
    public int GetWidth() {
        return (int) GetScaledWidth();
    }

    /**
     * Gets the scaled width of the bitmap in float form.
     *
     * @return
     */
    public float GetWidthFloat() {
        return (float) GetScaledWidth();
    }

    /**
     * The base drawing method for the bitmap object.
     *
     * @param p The MmgPen used to draw this bitmap.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (GetIsVisible() == true) {
            if (DRAW_MODE == MmgBmpDrawMode.DRAW_BMP_FULL) {
                p.DrawBmp(this);
            } else if (DRAW_MODE == MmgBmpDrawMode.DRAW_BMP_BASIC) {
                p.DrawBmpBasic(this);
            } else if (DRAW_MODE == MmgBmpDrawMode.DRAW_BMP_BASIC_CACHE) {
                p.DrawBmpFromCache(this);
            }
        } else {
            //do nothing
        }
    }

    public boolean Equals(MmgBmp b) {
        if (b != null) {
            //ORIGIN
            if (GetOrigin() == null && b.GetOrigin() != null) {
                return false;
            }

            if (GetOrigin() != null && b.GetOrigin() == null) {
                return false;
            }

            if (GetOrigin() != null && b.GetOrigin() != null && GetOrigin().Equals(b.GetOrigin()) == false) {
                return false;
            }

            //SCALING
            if (GetScaling() == null && b.GetScaling() != null) {
                return false;
            }

            if (GetScaling() != null && b.GetScaling() == null) {
                return false;
            }

            if (GetScaling() != null && b.GetScaling() != null && GetScaling().Equals(b.GetScaling()) == false) {
                return false;
            }

            //SRC RECT
            if (GetSrcRect() == null && b.GetSrcRect() != null) {
                return false;
            }

            if (GetSrcRect() != null && b.GetSrcRect() == null) {
                return false;
            }

            if (GetSrcRect() != null && b.GetSrcRect() != null && GetSrcRect().Equals(b.GetSrcRect()) == false) {
                return false;
            }

            //DST RECT
            if (GetDstRect() == null && b.GetDstRect() != null) {
                return false;
            }

            if (GetDstRect() != null && b.GetDstRect() == null) {
                return false;
            }

            if (GetDstRect() != null && b.GetDstRect() != null && GetDstRect().Equals(b.GetDstRect()) == false) {
                return false;
            }

            //IMAGE
            if (GetImage() == null && b.GetImage() != null) {
                return false;
            }

            if (GetImage() != null && b.GetImage() == null) {
                return false;
            }

            if (GetImage() != null && b.GetImage() != null && GetImage().equals(b.GetImage()) == false) {
                return false;
            }

            //OTHER VARS
            if (GetRotation() != b.GetRotation()) {
                return false;
            }

            if (GetBmpIdStr().equals(b.GetBmpIdStr()) == true) {
                return false;
            }

            if (GetBmpId() == b.GetBmpId()) {
                return false;
            }

            if (DRAW_MODE != b.DRAW_MODE) {
                return false;
            }

            MmgObj o1 = (MmgObj) this;
            MmgObj o2 = (MmgObj) b;
            if (o1 != null && o2 != null && o1.Equals(o2) == false) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }
}

package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * The base drawable class of the MmgApi.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public class MmgObj {

    /**
     * The screen position to draw this object.
     */
    protected MmgVector2 pos;
    
    /**
     * The width of this object.
     */
    protected int w;
    
    /**
     * The height of this object.
     */
    protected int h;
    
    /**
     * The visibility of this object.
     */
    protected boolean isVisible;
    
    /**
     * The color of this object.
     */
    protected MmgColor color;
    
    /**
     * The version of this MmgApi.
     */
    private final String version = "1.0.1"; //"1.0.0";

    /**
     * Flag indicating if this MmgObj has a parent container.
     */
    protected boolean hasParent;
    
    /**
     * Parent object if available.
     */
    protected MmgObj parent;
    
    /**
     * The name of this MmgObj.
     */
    protected String name;
    
    /**
     * The id of this MmgObj.
     */
    protected String mmgUid;
    
    
    /**
     * Constructor for this class.
     */
    public MmgObj() {
        pos = MmgVector2.GetOriginVec();
        w = 0;
        h = 0;
        isVisible = true;
        color = MmgColor.GetWhite();
        hasParent = false;
        parent = null;
        name = "";
        mmgUid = "";
    }

    /**
     * Constructor with identification.
     * 
     * @param n         Name of the MmgObj.
     * @param i         Id of the MmgObj.
     */
    public MmgObj(String n, String i) {
        pos = MmgVector2.GetOriginVec();
        w = 0;
        h = 0;
        isVisible = true;
        color = MmgColor.GetWhite();
        hasParent = false;
        parent = null;
        name = n;
        mmgUid = i;
    }
    
    /**
     * Constructor for this class that sets the position, dimensions, visibility, and color of this object.
     * 
     * @param X         The X coordinate of this object's position.
     * @param Y         The Y coordinate of this object's position.
     * @param W         The width of this object.
     * @param H         The height of this object.
     * @param isv       The visibility of this object.
     * @param c         The color of this object.
     */
    public MmgObj(int X, int Y, int W, int H, boolean isv, MmgColor c) {
        pos = new MmgVector2(X, Y);
        w = W;
        h = H;
        isVisible = isv;
        color = c;
        hasParent = false;
        parent = null;
        name = "";
        mmgUid = "";
    }

    /**
     * Constructor for this class that sets the position, dimensions, visibility, and color of this object.
     * 
     * @param X         The X coordinate of this object's position.
     * @param Y         The Y coordinate of this object's position.
     * @param W         The width of this object.
     * @param H         The height of this object.
     * @param isv       The visibility of this object.
     * @param c         The color of this object.
     * @param n         The name you want to give this object.
     * @param i         The id you want to give this object.
     */    
    public MmgObj(int X, int Y, int W, int H, boolean isv, MmgColor c, String n, String i) {
        pos = new MmgVector2(X, Y);
        w = W;
        h = H;
        isVisible = isv;
        color = c;
        hasParent = false;
        parent = null;
        name = n;
        mmgUid = i;
    }
    
    /**
     * Constructor for this class that sets the position, dimensions, visibility, and color of this object.
     * 
     * @param v2        The position of this object.
     * @param W         The width of this object.
     * @param H         The height of this object.
     * @param isv       The visibility of this object.
     * @param c         The color of this object.
     */
    public MmgObj(MmgVector2 v2, int W, int H, boolean isv, MmgColor c) {
        pos = v2;
        w = W;
        h = H;
        isVisible = isv;
        color = c;
        hasParent = false;
        parent = null;
        name = "";
        mmgUid = "";
    }

    /**
     * Constructor for this class that sets the position, dimensions, visibility, and color of this object.
     * 
     * @param v2        The position of this object.
     * @param W         The width of this object.
     * @param H         The height of this object.
     * @param isv       The visibility of this object.
     * @param c         The color of this object.
     * @param n         The name you want to give this object.
     * @param i         The id you want to give this object.
     */
    public MmgObj(MmgVector2 v2, int W, int H, boolean isv, MmgColor c, String n, String i) {
        pos = v2;
        w = W;
        h = H;
        isVisible = isv;
        color = c;
        hasParent = false;
        parent = null;
        name = n;
        mmgUid = i;
    }
    
    /**
     * Constructor for this object that sets all the attribute values to the values of the attributes
     * of the given argument.
     * 
     * @param obj       The object to use to set all local attributes. 
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgObj(MmgObj obj) {
        if(obj.GetPosition() != null) {
            SetPosition(obj.GetPosition().Clone());
        }else {
            SetPosition(obj.GetPosition());
        }
        
        SetWidth(obj.GetWidth());
        SetHeight(obj.GetHeight());
        SetIsVisible(obj.GetIsVisible());

        if(obj.GetMmgColor() != null) {
            SetMmgColor(obj.GetMmgColor().Clone());
        }else {
            SetMmgColor(obj.GetMmgColor());
        }
        
        SetHasParent(obj.GetHasParent());
        SetParent(obj.GetParent());
        SetName(obj.GetName());
        SetId(obj.GetId());
    }

    /**
     * Clones this object.
     * 
     * @return      A clone of this object.
     */
    public MmgObj Clone() {
        MmgObj ret = new MmgObj(this);
        return ret;
    }

    /**
     * Gets the API version number.
     * 
     * @return      The API version number. 
     */
    public String GetVersion() {
        return version;
    }

    /**
     * Base drawing class, does nothing.
     * 
     * @param p     The MmgPen that would be used to draw this object.
     */
    public void MmgDraw(MmgPen p) {
        //do nothing
        if(isVisible == true) {
            
        }else {
            //do nothing
        }
    }

    public void MmgUpdate(int updateTick) {
        if(isVisible == true) {
            
        }else {
            //do nothing
        }
    }
    
    /**
     * Gets the visibility of this class.
     * 
     * @return      True if this class is visible, false otherwise. 
     */
    public boolean GetIsVisible() {
        return isVisible;
    }

    /**
     * Sets the invisibility of this class.
     * 
     * @param bl    True if this class is visible, false otherwise.
     */
    public void SetIsVisible(boolean bl) {
        isVisible = bl;
    }

    /**
     * Gets the width of this object.
     * 
     * @return      The width of this object. 
     */
    public int GetWidth() {
        return w;
    }

    /**
     * Sets the width of this object.
     * 
     * @param W     The width of this object.
     */
    public void SetWidth(int W) {
        w = W;
    }

    /**
     * Gets the height of this object.
     * 
     * @return      The height of this object.
     */
    public int GetHeight() {
        return h;
    }

    /**
     * Sets the height of this object.
     * 
     * @param H     The height of this object.
     */
    public void SetHeight(int H) {
        h = H;
    }

    /**
     * Sets the position of this object.
     * 
     * @param v     The position of this object. 
     */
    public void SetPosition(MmgVector2 v) {
        pos = v;
    }

    /**
     * Gets the position of this object.
     * 
     * @return      The position of this object. 
     */
    public MmgVector2 GetPosition() {
        return pos;
    }

    /**
     * Gets the color of this object.
     * 
     * @return      The color of this object.
     */
    public MmgColor GetMmgColor() {
        return color;
    }

    /**
     * Sets the color of this object.
     * 
     * @param c     The color of this object.
     */
    public void SetMmgColor(MmgColor c) {
        color = c;
    }

    /**
     * Sets the X coordinate of this object.
     * 
     * @param inX   The X coordinate of this object. 
     */
    public void SetX(int inX) {
        this.GetPosition().SetX(inX);
    }

    /**
     * Gets the X coordinate of this object.
     * 
     * @return      The X coordinate of this object. 
     */
    public int GetX() {
        return this.GetPosition().GetX();
    }

    /**
     * Sets the Y coordinate of this object.
     * 
     * @param inY       The Y coordinate of this object.
     */
    public void SetY(int inY) {
        this.GetPosition().SetY(inY);
    }

    /**
     * Gets the Y coordinate of this object.
     * 
     * @return      The Y coordinate of this object. 
     */
    public int GetY() {
        return this.GetPosition().GetY();
    }
    
    public String GetName() {
        return name;
    }
    
    public void SetName(String n) {
        name = n;
    }
    
    public String GetId() {
        return mmgUid;
    }
    
    public void SetId(String i) {
        mmgUid = i;
    }
    
    public void SetHasParent(boolean b) {
        hasParent = b;
    }
    
    public void SetParent(MmgObj o) {
        parent = o;
    }
    
    public boolean GetHasParent() {
        return hasParent;
    }
    
    public MmgObj GetParent() {
        return parent;
    }
    
    public String ToString() {
        return "Name: " + GetName() + " Id: " + GetId() + " - " + GetPosition();
    }
    
    public boolean Equals(MmgObj o) {
        if(o != null 
                && o.GetHasParent() == GetHasParent() 
                && o.GetIsVisible() == GetIsVisible()
                && o.GetHeight() == GetHeight()
                && ((o.GetId() == null && GetId() == null) || (o.GetId() != null && GetId() != null && o.GetId().equals(GetId())))
                && ((o.GetName() == null && GetName() == null) || (o.GetName() != null && GetName() != null && o.GetName().equals(GetName())))
                && ((o.GetParent() == null && GetParent() == null) || (o.GetParent() != null && GetParent() != null && o.GetParent().Equals(GetParent())))
                && ((o.GetPosition() == null && GetPosition() == null) || (o.GetPosition() != null && GetPosition() != null && o.GetPosition().Equals(GetPosition())))
                ) {
            return true;
        }else {
            return false;
        }
    }
}

package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * The base drawable class of the MmgApi.
 * Created by Middlemind Games 08/29/2016
 * 
 * @author Victor G. Brusca
 */
public class MmgObj {

    /**
     * The screen position to draw this object.
     */
    public MmgVector2 pos;
    
    /**
     * The width of this object.
     */
    public int w;
    
    /**
     * The height of this object.
     */
    public int h;
    
    /**
     * The visibility of this object.
     */
    public boolean isVisible;
    
    /**
     * The color of this object.
     */
    public MmgColor color;
    
    /**
     * The version of this MmgApi.
     */
    private String version = "1.0.6";

    /**
     * Flag indicating if this MmgObj has a parent container.
     */
    public boolean hasParent;
    
    /**
     * Parent object if available.
     */
    public MmgObj parent;
    
    /**
     * The name of this MmgObj.
     */
    public String name;
    
    /**
     * The id of this MmgObj.
     */
    public String mmgUid;
    
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
     * Creates a basic clone of this class.
     * 
     * @return      A clone of this object.
     */
    public MmgObj Clone() {
        return new MmgObj(this);
    }

    /**
     * Creates a typed clone of this class.
     *
     * @return      A typed clone of this class.
     */
    public MmgObj CloneTyped() {
        return new MmgObj(this);
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
        if(isVisible == true) {
            
        }
    }

    /**
     * The MmgUpdate method that handles updating any object fields during the update calls.
     * 
     * @param updateTick            The index of the update call.
     * @param currentTimeMs         The current time in milliseconds that the update was called.
     * @param msSinceLastFrame      The difference in milliseconds between this update call and the previous update call.
     * @return                      A boolean indicating if the update call was handled.
     */
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        if(isVisible == true) {
            
        }
        return false;
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
     * Sets the position of this object using X, Y coordinates.
     * 
     * @param x     The X coordinate to set this object position to.
     * @param y     The Y coordinate to set this object position to.
     */
    public void SetPosition(int x, int y) {
        pos = new MmgVector2(x, y);
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
    
    /**
     * Gets the name of this MmgObj class instance.
     * 
     * @return      The name of this MmgObj.
     */
    public String GetName() {
        return name;
    }
    
    /**
     * Sets the name of this MmgObj class instance.
     * 
     * @param n     The name of this MmgObj.
     */
    public void SetName(String n) {
        name = n;
    }
    
    /**
     * Gets the id of this MmgObj.
     * 
     * @return      The id of this MmgObj.
     */
    public String GetId() {
        return mmgUid;
    }
    
    /**
     * Sets the id of this MmgObj.
     * 
     * @param i     The id of this MmgObj.
     */
    public void SetId(String i) {
        mmgUid = i;
    }
    
    /**
     * Sets that this MmgObj has a parent.
     * 
     * @param b     A boolean indicating that this object has a parent.
     */
    public void SetHasParent(boolean b) {
        hasParent = b;
    }
    
    /**
     * Sets the parent of this MmgObj.
     * 
     * @param o     The parent of this MmgObj.
     */
    public void SetParent(MmgObj o) {
        parent = o;
    }
    
    /**
     * Gets a boolean indicating if this MmgObj has a parent.
     * 
     * @return      A boolean indicating if this class instance has a parent.
     */
    public boolean GetHasParent() {
        return hasParent;
    }
    
    /**
     * Gets the parent of this MmgObj instance.
     * 
     * @return      The parent MmgObj of this class instance.
     */
    public MmgObj GetParent() {
        return parent;
    }
    
    /**
     * A string representation of this class.
     * 
     * @return      A string representation of this class.
     */
    public String ToString() {
        return "Name: " + GetName() + " Id: " + GetId() + " - " + GetPosition().ToString();
    }
    
    /**
     * A class method that checks equality of this class with another MmgObj class by comparing
     * some key class fields.
     * 
     * @param obj     A MmgObj to compare this class to.
     * @return      A boolean indicating if this class instance is equal to the comparison class instance.
     */
    public boolean Equals(MmgObj obj) {
        if(obj == null) {
            return false;
        }
        
        boolean ret = false;
        if(
            obj.GetHasParent() == GetHasParent() 
            && obj.GetHeight() == GetHeight()
            && obj.GetWidth() == GetWidth()
            && ((obj.GetMmgColor() == null && GetMmgColor() == null) || (obj.GetMmgColor() != null && GetMmgColor() != null && obj.GetMmgColor().Equals(GetMmgColor())))                
            && ((obj.GetId() == null && GetId() == null) || (obj.GetId() != null && GetId() != null && obj.GetId().equals(GetId())))
            && ((obj.GetName() == null && GetName() == null) || (obj.GetName() != null && GetName() != null && obj.GetName().equals(GetName())))
            && ((obj.GetParent() == null && GetParent() == null) || (obj.GetParent() != null && GetParent() != null && obj.GetParent().Equals(GetParent())))
            && ((obj.GetPosition() == null && GetPosition() == null) || (obj.GetPosition() != null && GetPosition() != null && obj.GetPosition().Equals(GetPosition())))
        ) {
            ret = true;
        }

        return ret;
    }
}

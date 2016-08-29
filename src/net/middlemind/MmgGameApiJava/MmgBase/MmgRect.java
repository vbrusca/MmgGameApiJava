package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.*; 

/**
 * A rectangle class that wraps the lower level rectangle class.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public class MmgRect {
    
    /**
     * The lower level rectangle class.
     */
    private Rectangle rect;

    /**
     * Constructor for this class.
     */
    public MmgRect() {
        rect = new Rectangle(0, 0, 0, 0);
    }

    /**
     * Constructor for this class that is created from an existing MmgRect class.
     * 
     * @param r     The MmgRect to use as a basis for this class. 
     */
    public MmgRect(MmgRect r) {
        rect = r.GetRect();
    }

    /**
     * Constructor for this class that sets the position and size of the rectangle.
     * 
     * @param left      The left X coordinate of the rectangle.
     * @param top       The top Y coordinate of the rectangle.
     * @param bottom    The bottom Y coordinate of the rectangle.
     * @param right     The right X coordinate of the rectangle.
     */
    public MmgRect(int left, int top, int bottom, int right) {
        rect = new Rectangle(left, top, (right - left), (bottom - top));
    }

    /**
     * Constructor for this class that sets the position and dimensions of this rectangle.
     * 
     * @param v     The position of the rectangle.
     * @param w     The width of the rectangle.
     * @param h     The height of the rectangle.
     */
    public MmgRect(MmgVector2 v, int w, int h) {
        rect = new Rectangle(v.GetX(), v.GetY(), w, h);
    }
	
    /**
     * Clones this class.
     * 
     * @return      A clone of this class.
     */
    public MmgRect Clone() {
        return new MmgRect(rect.x, rect.y, (rect.y + rect.height), (rect.x + rect.width));
    }

    public static MmgRect GetUnitRect() {
        return new MmgRect(0, 0, 1, 1);
    }
    
    /**
     * Gets the X coordinate left.
     * 
     * @return      The X coordinate left. 
     */
    public int GetLeft() {
    	return rect.x;
    }
    
    /**
     * Gets the Y coordinate top.
     * 
     * @return      The Y coordinate top.
     */
    public int GetTop() {
    	return rect.y;
    }
    
    /**
     * Gets the X coordinate right.
     * 
     * @return      The X coordinate right.
     */
    public int GetRight() {
    	return (rect.x + rect.width);
    }
    
    /**
     * Gets the Y coordinate bottom.
     * 
     * @return      The Y coordinate bottom.
     */
    public int GetBottom() {
    	return (rect.y + rect.height);
    }
    
    /**
     * Gets the width of the rectangle.
     * 
     * @return      The width of the rectangle.
     */
    public int GetWidth() {
        return rect.width;
    	//return (GetRight() - GetLeft());
    }
    
    public void SetWidth(int w) {
        rect.setSize(w, rect.height);
    }
    
    /**
     * Gets the height of the rectangle.
     * 
     * @return      The height of the rectangle.
     */
    public int GetHeight() {
    	//return (GetBottom() - GetTop());
        return rect.height;
    }
    
    public void SetHeight(int h) {
        rect.setSize(rect.width, h);
    }
    
    /**
     * Gets the underlying rectangle object.
     * 
     * @return      The underlying rectangle object.
     */
    public Rectangle GetRect() {
        return rect;
    }

    /**
     * Sets the underlying rectangle object.
     * 
     * @param r     The underlying rectangle object.
     */
    public void SetRect(Rectangle r) {
        rect = r;
    }

    /**
     * Gets the position of the rectangle.
     * 
     * @return      The position of the rectangle.
     */
    public MmgVector2 GetPosition() {
    	return new MmgVector2(GetLeft(), GetTop());
    }
    
    public void SetPosition(MmgVector2 v) {
        rect.setLocation(v.GetX(), v.GetY());
    }
    
    /**
     * A string representation of this object.
     * 
     * @return      A string representation of this object.
     */
    public String ToString() {
        return "L: " + GetLeft() + " R: " + GetRight() + " T: " + GetTop() + " B: " + GetBottom() + ", W: " + GetWidth() + " H: " + GetHeight();
    }
    
    public boolean Equals(MmgRect r) {
        if(GetLeft() == r.GetLeft() && GetRight() == r.GetRight() && GetTop() == r.GetTop() && GetBottom() == r.GetBottom()) {
            return true;
        }else{
            return false;
        }
    }
}

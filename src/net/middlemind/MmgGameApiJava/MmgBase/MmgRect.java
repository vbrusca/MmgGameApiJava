package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.*;

/**
 * A rectangle class that wraps the lower level rectangle class. 
 * Created by Middlemind Games 08/29/2016
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
     * Constructor for this class that is created from an existing MmgRect
     * class.
     *
     * @param r     The MmgRect to use as a basis for this class.
     */
    public MmgRect(MmgRect r) {
        rect = r.GetRect();
    }

    /**
     * Constructor for this class that sets the position and size of the
     * rectangle.
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
     * Constructor for this class that sets the position and dimensions of this
     * rectangle.
     *
     * @param v     The position of the rectangle.
     * @param w     The width of the rectangle.
     * @param h     The height of the rectangle.
     */
    public MmgRect(MmgVector2 v, int w, int h) {
        rect = new Rectangle(v.GetX(), v.GetY(), w, h);
    }

    /**
     * Shift this rectangle by the given amounts.
     *
     * @param shiftLeftRight    The number of pixels to shift the rectangle in the left, right direction.
     * @param shiftUpDown       The number of pixels to shift the rectangle i the up, down direction.
     */
    public void ShiftRect(int shiftLeftRight, int shiftUpDown) {
        rect = new Rectangle(rect.x + shiftLeftRight, rect.y + shiftUpDown, rect.width, rect.height);
    }

    /**
     * Return a shifted rectangle by the given amounts.
     *
     * @param shiftLeftRight    The number of pixels to shift the rectangle in the left, right direction.
     * @param shiftUpDown       The number of pixels to shift the rectangle i the up, down direction.
     * @return                  A new rectangle shifted in the X, Y directions by the specified amount.
     */
    public MmgRect ToShiftedRect(int shiftLeftRight, int shiftUpDown) {
        return new MmgRect(rect.x + shiftLeftRight, rect.y + shiftUpDown, rect.width, rect.height);
    }

    /**
     * Creates a typed clone of this class.
     *
     * @return      A typed clone of this class.
     */
    public MmgRect Clone() {
        return new MmgRect(rect.x, rect.y, (rect.y + rect.height), (rect.x + rect.width));
    }

    /**
     * Creates a unit rectangle with with and height of 1.
     * 
     * @return      A new rectangle with width and height of 1.
     */
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
     * Gets the difference in X coordinates between the this MmgRect and the provided MmgRect.
     * 
     * @param inRect            The MmgRect to compare with to calculate the X difference.
     * @param direction         The direction to compare the X difference in left or right.
     * @param opposite          A boolean indicating to calculate the X difference in the opposite direction.
     * @param left2right        A boolean indicating if the calculation should be left to right.
     * @return                  The X difference between the two MmgRect instances.
     */
    public int GetDiffX(MmgRect inRect, int direction, boolean opposite, boolean left2right) {
        if (MmgDir.DIR_LEFT == direction && opposite == false) {
            return (GetLeft() - inRect.GetLeft());
        } else if (MmgDir.DIR_LEFT == direction && opposite == true && left2right == true) {
            return (GetRight() - inRect.GetLeft());
        } else if (MmgDir.DIR_LEFT == direction && opposite == true && left2right == false) {
            return (inRect.GetLeft() - GetRight());
        } else if (MmgDir.DIR_RIGHT == direction && opposite == false) {
            return (GetRight() - inRect.GetRight());
        } else if (MmgDir.DIR_RIGHT == direction && opposite == true && left2right == true) {
            return (GetRight() - inRect.GetLeft());
        } else if (MmgDir.DIR_RIGHT == direction && opposite == true && left2right == false) {
            return (inRect.GetLeft() - GetRight());
        } else {
            return 0;
        }
    }

    /**
     * Gets the difference in X coordinates between the this MmgRect and the provided X coordinate.
     * 
     * @param x             The X coordinate to compare with to calculate the X difference.
     * @param direction     The direction to compare the X difference in left or right.
     * @param opposite      A boolean indicating to calculate the X difference in the opposite direction.
     * @param left2right    A boolean indicating if the calculation should be left to right.
     * @return              The X difference between the two coordinate values.
     */
    public int GetDiffX(int x, int direction, boolean opposite, boolean left2right) {
        if (MmgDir.DIR_LEFT == direction && opposite == false) {
            return (GetLeft() - x);
        } else if (MmgDir.DIR_LEFT == direction && opposite == true && left2right == true) {
            return (GetLeft() - x);
        } else if (MmgDir.DIR_LEFT == direction && opposite == true && left2right == false) {
            return (x - GetLeft());
        } else if (MmgDir.DIR_RIGHT == direction && opposite == false) {
            return (GetRight() - x);
        } else if (MmgDir.DIR_RIGHT == direction && opposite == true && left2right == true) {
            return (GetRight() - x);
        } else if (MmgDir.DIR_RIGHT == direction && opposite == true && left2right == false) {
            return (x - GetRight());
        } else {
            return 0;
        }
    }

    /**
     * Gets the difference in Y coordinates between the this MmgRect and the provided MmgRect.
     * 
     * @param inRect        The MmgRect to compare with to calculate the Y difference.
     * @param direction     The direction to compare the Y difference in up or down.
     * @param opposite      A boolean indicating to calculate the Y difference in the opposite direction.
     * @param left2right    A boolean indicating if the calculation should be top to bottom.
     * @return              The Y difference between the two MmgRect instances.
     */
    public int GetDiffY(MmgRect inRect, int direction, boolean opposite, boolean left2right) {
        if (MmgDir.DIR_TOP == direction && opposite == false) {
            return (GetTop() - inRect.GetTop());
        } else if (MmgDir.DIR_TOP == direction && opposite == true && left2right == true) {
            return (GetBottom() - inRect.GetTop());
        } else if (MmgDir.DIR_TOP == direction && opposite == true && left2right == false) {
            return (inRect.GetTop() - GetBottom());
        } else if (MmgDir.DIR_BOTTOM == direction && opposite == false) {
            return (GetBottom() - inRect.GetBottom());
        } else if (MmgDir.DIR_BOTTOM == direction && opposite == true && left2right == true) {
            return (GetBottom() - inRect.GetTop());
        } else if (MmgDir.DIR_BOTTOM == direction && opposite == true && left2right == false) {
            return (inRect.GetTop() - GetBottom());
        } else {
            return 0;
        }
    }

    /**
     * Gets the difference in Y coordinates between the this MmgRect and the provided Y coordinate.
     * 
     * @param y             The Y coordinate to compare with to calculate the Y difference.
     * @param direction     The direction to compare the X difference in up or down.
     * @param opposite      A boolean indicating to calculate the Y difference in the opposite direction.
     * @param left2right    A boolean indicating if the calculation should be top to bottom.
     * @return              The Y difference between the two coordinate values.
     */
    public int GetDiffY(int y, int direction, boolean opposite, boolean left2right) {
        if (MmgDir.DIR_TOP == direction && opposite == false) {
            return (GetLeft() - y);
        } else if (MmgDir.DIR_TOP == direction && opposite == true && left2right == true) {
            return (GetLeft() - y);
        } else if (MmgDir.DIR_TOP == direction && opposite == true && left2right == false) {
            return (y - GetLeft());
        } else if (MmgDir.DIR_BOTTOM == direction && opposite == false) {
            return (GetRight() - y);
        } else if (MmgDir.DIR_BOTTOM == direction && opposite == true && left2right == true) {
            return (GetRight() - y);
        } else if (MmgDir.DIR_BOTTOM == direction && opposite == true && left2right == false) {
            return (y - GetRight());
        } else {
            return 0;
        }
    }

    /**
     * Gets the position of the rectangle.
     *
     * @return      The position of the rectangle.
     */
    public MmgVector2 GetPosition() {
        return new MmgVector2(GetLeft(), GetTop());
    }

    /**
     * Sets the position of the rectangle.
     * 
     * @param v     The position of the rectangle.
     */
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

    /**
     * A method that test equality between two rectangles based on the coordinate of the left, right, top, bottom.
     * 
     * @param r     A MmgRect to compare with this class instance.
     * @return      A boolean indicating if the two class instances are equal.
     */
    public boolean Equals(MmgRect r) {
        if (GetLeft() == r.GetLeft() && GetRight() == r.GetRight() && GetTop() == r.GetTop() && GetBottom() == r.GetBottom()) {
            return true;
        } else {
            return false;
        }
    }
}

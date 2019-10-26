package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Color;

/**
 * A class that represents a menu item.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public class MmgMenuItem extends MmgObj {
	
    /**
     * Used to represent a normal menu item state.
     */
    public static final int STATE_NORMAL = 0;
    
    /**
     * Used to represent a selected menu item state.
     */
    public static final int STATE_SELECTED = 1;
    
    /**
     * Used to represent an inactive menu item state.
     */
    public static final int STATE_INACTIVE = 2;

    /**
     * The event to fir if the menu item has been pressed.
     */
    private MmgEvent eventPress;
    
    /**
     * The object to use if the menu item is normal.
     */
    private MmgObj normal;
    
    /**
     * The object to use if the menu item is selected.
     */
    private MmgObj selected;
    
    /**
     * The object to use if the menu item is inactive.
     */
    private MmgObj inactive;
    
    /**
     * The currently drawn object.
     */
    private MmgObj current;
    
    /**
     * A class helper variable for tracking the current state.
     */
    private int state;

    public static boolean SHOW_MENU_ITEM_BOUNDING_BOX = false;
    
    /**
     * Constructor for this class.
     */
    public MmgMenuItem() {
    	super();
        eventPress = null;
        normal = null;
        selected = null;
        inactive = null;
        current = null;
        state = STATE_NORMAL;
    }

    /**
     * Constructor for this class that sets the value of base attributes
     * from the given argument.
     * 
     * @param m     The MmgObj to use to set the attributes of this object. 
     */
    public MmgMenuItem(MmgObj m) {
    	super(m);
        eventPress = null;
        normal = null;
        selected = null;
        inactive = null;
        current = null;
        state = STATE_NORMAL;
    }

    /**
     * Constructor for this class that sets the value of certain attributes based
     * on the value of attributes in the given argument.
     * 
     * @param m     An MmgMenuItem object to get attribute values from. 
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgMenuItem(MmgMenuItem m) {
        SetEventPress(m.GetEventPress());
        SetNormal(m.GetNormal());
        SetSelected(m.GetSelected());
        SetInactive(m.GetInactive());
        SetState(m.GetState());

        if(m.GetPosition() != null) {
        	SetPosition(m.GetPosition().Clone());
        }else{
        	SetPosition(m.GetPosition());
        }
        SetWidth(m.GetWidth());
        SetHeight(m.GetHeight());
        SetIsVisible(m.GetIsVisible());
        
        if(m.GetMmgColor() != null) {
        	SetMmgColor(m.GetMmgColor().Clone());
        }else{
        	SetMmgColor(m.GetMmgColor());
        }
    }

    /**
     * A constructor for this class that sets the event, the appearance objects and the state
     * of the menu item.
     * 
     * @param me            The event to fire if the menu item is pressed. 
     * @param Normal        The object to show if the menu item has a normal state.
     * @param Selected      The object to show if the menu item has a selected state.
     * @param Inactive      The object to show if the menu item has an inactive state.
     * @param State         The current state of the menu item object.
     */
    public MmgMenuItem(MmgEvent me, MmgObj Normal, MmgObj Selected, MmgObj Inactive, int State) {
    	super();
        eventPress = me;
        normal = Normal;
        selected = Selected;
        inactive = Inactive;
        state = State;
    }

    /**
     * Clones this class.
     * 
     * @return      A clone of this class. 
     */
    @Override
    public MmgObj Clone() {
        MmgMenuItem ret = new MmgMenuItem(this);
        return (MmgObj) ret;
    }

    /**
     * Gets the press event for this menu item.
     * 
     * @return      The press event for this menu item. 
     */
    public MmgEvent GetEventPress() {
        return eventPress;
    }

    /**
     * Sets the press event for this menu item.
     * 
     * @param e     The press event for this menu item.
     */
    public void SetEventPress(MmgEvent e) {
        eventPress = e;
    }

    /**
     * Gets the object to draw in the normal state.
     * 
     * @return      The normal state object. 
     */
    public MmgObj GetNormal() {
        return normal;
    }

    /**
     * Sets the object to draw in the normal state.
     * 
     * @param m     The normal state object. 
     */
    public void SetNormal(MmgObj m) {
        normal = m;
    }

    /**
     * Gets the object to draw in the selected state.
     * 
     * @return      The selected state object.
     */
    public MmgObj GetSelected() {
        return selected;
    }

    /**
     * Sets the object to draw in the selected state.
     * 
     * @param m     The selected state object.
     */
    public void SetSelected(MmgObj m) {
        selected = m;
    }

    /**
     * Gets the activity flag for this menu item.
     * 
     * @return      True if the object is active, false otherwise. 
     */
    public MmgObj GetInactive() {
        return inactive;
    }

    /**
     * Sets the activity flag for this menu item.
     * 
     * @param m     True if the object is active, false otherwise.
     */
    public void SetInactive(MmgObj m) {
        inactive = m;
    }

    /**
     * Sets the current state of the menu item.
     * 
     * @param i     The current state of the menu item. 
     */
    public void SetState(int i) {
        if (i == STATE_NORMAL) {
            current = normal;
        }else if (i == STATE_SELECTED) {
            current = selected;
        }else if (i == STATE_INACTIVE) {
            current = inactive;
        }
        state = i;
    }

    /**
     * Gets the current state of the menu item.
     * 
     * @return      The current state of the menu item. 
     */
    public int GetState() {
        return state;
    }

    /**
     * Gets the height of the menu item object based on the menu item's
     * current state.
     * 
     * @return      The current height of the menu item based on its state.
     */
    @Override
    public int GetHeight() {
        return current.GetHeight();
    }

    /**
     * Sets the height of the menu item object based on the menu item's
     * current state.
     * 
     * @param h     The current height of the menu item based on its state.
     */
    @Override
    public void SetHeight(int h) {
        current.SetHeight(h);
    }

    /**
     * Gets the width of the menu item object based on the menu item's
     * current state.
     * 
     * @return      The current width of the menu item based on its state.
     */
    @Override
    public int GetWidth() {
        return current.GetWidth();
    }

    /**
     * Sets the width of the menu item object based on the menu item's 
     * current state.
     * 
     * @param w     The current width of the menu item based on its state.
     */
    @Override
    public void SetWidth(int w) {
        current.SetWidth(w);
    }

    /**
     * The base drawing method for this object.
     * 
     * @param p     The MmgPen object used to draw this object.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (GetIsVisible() == true) {
            if (MmgMenuItem.SHOW_MENU_ITEM_BOUNDING_BOX == true) {
                Color c = p.GetGraphics().getColor();
                p.GetGraphics().setColor(Color.red);
                p.DrawRect(this);
                p.GetGraphics().setColor(c);
            }            
            
            current.SetPosition(GetPosition());
            current.SetMmgColor(GetMmgColor());
            current.MmgDraw(p);
        }else {
            //do nothing
        }
    }
}

package net.middlemind.MmgGameApiJava.MmgBase;

import java.util.*;

/**
 * A class that contains a collection of MmgMenuItem objects.
 * Created by Middlemind Games 08/29/2016
 * 
 * @author Victor G. Brusca
 */
public class MmgMenuContainer extends MmgObj {

    /**
     * The ArrayList that holds the MmgMenuItem objects.
     */
    private ArrayList<MmgObj> container;
    
    /**
     * A class helper variable.
     */
    private MmgObj[] a;
    
    /**
     * A class helper variable.
     */
    private int i;
    
    /**
     * A class helper variable.
     */
    private MmgObj mo;

    /**
     * Constructor for this class.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgMenuContainer() {
        super();
        SetContainer(new ArrayList<MmgObj>(50));
    }

    /**
     * Constructor for this class that sets some default attributes to the same value as the attributes
     * of the given object.
     * 
     * @param obj       The object to use for default attribute values.
     */
    public MmgMenuContainer(MmgObj obj) {
        super(obj);
        SetContainer(new ArrayList<MmgObj>(50));
    }

    /**
     * Constructor for this class that sets the MmgMenuItem ArrayList.
     * 
     * @param objects       The ArrayList to use for the menu items. 
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgMenuContainer(ArrayList<MmgObj> objects) {
        super();
        SetContainer(objects);
    }

    /**
     * Constructor for this class that sets the attributes of this class to the values 
     * from the given argument.
     * 
     * @param obj      The MmgMenuContainer class to use for attribute values. 
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgMenuContainer(MmgMenuContainer obj) {
        super();
        if(obj.GetContainer() == null) {
            SetContainer(obj.GetContainer());
        } else {
            a = new MmgObj[obj.GetCount()];
            a = obj.GetContainer().toArray(a);
            SetContainer(new ArrayList<MmgObj>(a.length));
            for(i = 0; i < a.length; i++) {
                Add((MmgMenuItem)a[i].Clone());
            }
        }

        if(obj.GetPosition() == null) {
            SetPosition(obj.GetPosition());
        } else {
            SetPosition(obj.GetPosition().Clone());
        }
        
        SetWidth(obj.GetWidth());
        SetHeight(obj.GetHeight());
        SetIsVisible(obj.GetIsVisible());

        if (obj.GetMmgColor() == null) {
            SetMmgColor(obj.GetMmgColor());
        } else {
            SetMmgColor(obj.GetMmgColor().Clone());            
        }
    }

    /**
     * Creates a basic clone of this class.
     * 
     * @return      A clone of this class. 
     */
    @Override
    public MmgObj Clone() {
        MmgMenuContainer ret = new MmgMenuContainer(this);
        return (MmgObj) ret;
    }
    
    /**
     * Creates a typed clone of this class.
     * 
     * @return      A typed clone of this class. 
     */
    @Override
    public MmgMenuContainer CloneTyped() {
        return new MmgMenuContainer(this);
    }    

    /**
     * Adds a MmgMenuItem object to the menu item ArrayList.
     * 
     * @param obj       The menu item to add.
     */
    public void Add(MmgMenuItem obj) {
        container.add(obj);
    }

    /**
     * Removes an MmgMenuItem object from the menu item ArrayList.
     * 
     * @param obj       The menu item object to remove. 
     */
    public void Remove(MmgMenuItem obj) {
        container.remove(obj);
    }

    /**
     * Gets the number of menu items in this container.
     * 
     * @return      The number of menu items in this container. 
     */
    public int GetCount() {
        return container.size();
    }

    /**
     * Gets an array of MmgMenuItem objects stored by this container.
     * 
     * @return      The menu item objects stored by this container. 
     */
    public MmgObj[] GetArray() {
        a = new MmgObj[container.size()];
        return container.toArray(a);
    }

    /**
     * Clears all menu items from this menu item container.
     */
    public void Clear() {
        container.clear();
    }

    /**
     * Gets the ArrayList that contains all the MmgMenuItems.
     * 
     * @return      The menu items contained by this class. 
     */
    public ArrayList<MmgObj> GetContainer() {
        return container;
    }

    /**
     * Sets the ArrayList that stores all the MmgMenuItems.
     * 
     * @param aTmp  The menu items to hold.
     */
    public void SetContainer(ArrayList<MmgObj> aTmp) {
        container = aTmp;
    }

    /**
     * Draws this object.
     * 
     * @param p         An MmgPen object that draws this menu container.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (isVisible == true) {
            if (container != null) {
                a = container.toArray(a);
                for (i = 0; i < a.length; i++) {
                    mo = (MmgObj) a[i];
                    if (mo != null && mo.GetIsVisible() == true) {
                        mo.MmgDraw(p);
                    }
                }
            }
        }
    }
}

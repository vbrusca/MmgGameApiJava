package net.middlemind.MmgGameApiJava.MmgBase;

import java.util.*;

/**
 * Class that represents a container of MmgObj objects.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public class MmgContainer extends MmgObj {

    /**
     * The initial size of the collection object.
     */
    public static int INITIAL_SIZE = 10;
    
    /**
     * Private enumeration that lists the stamp, un-stamp actions that can be
     * performed on a child.
     */
    private enum ChildAction {
        STAMP,
        UNSTAMP
    }
    
    /**
     * The ArrayList that holds the MmgObj objects.
     */
    private ArrayList<MmgObj> container;
    
    /**
     * A class helper variable.
     */
    private Object[] a;
    
    /**
     * A class helper variable.
     */
    private MmgObj mo;
    
    /**
     * A class helper variable.
     */
    private int i;

    /**
     * Constructor for this class.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgContainer() {
        super();
        SetContainer(new ArrayList(INITIAL_SIZE));
    }

    /**
     * Constructor that sets the base objects properties equal to the given
     * arguments properties.
     * 
     * @param obj       The object to get MmgObj properties from. 
     */
    public MmgContainer(MmgObj obj) {
        super(obj);
    }

    /**
     * Constructor that initializes an ArrayList of objects contained 
     * by this container object.
     * 
     * @param objects       The objects to add to this container. 
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgContainer(ArrayList<MmgObj> objects) {
        super();
        SetContainer(objects);
    }

    /**
     * Constructor that initializes this class based on the attributes of a given argument.
     * 
     * @param cont      An MmgContainer class to use to set all the attributes of this class. 
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgContainer(MmgContainer cont) {
        ArrayList<MmgObj> tmp1 = cont.GetContainer();
        if(tmp1 != null) {
            int len = tmp1.size();
            ArrayList<MmgObj> tmp2 = new ArrayList(len);
            for(int j = 0; j < len; j++) {
                tmp2.add(j, tmp1.get(j).Clone());
            }
            SetContainer(tmp2);
        }else{
            SetContainer(tmp1);
        }
        
        if(cont.GetPosition() != null) {            
            SetPosition(cont.GetPosition().Clone());
        }else{
            SetPosition(cont.GetPosition());
        }
        
        SetWidth(cont.GetWidth());
        SetHeight(cont.GetHeight());
        SetIsVisible(cont.GetIsVisible());

        if(cont.GetMmgColor() != null) {
            SetMmgColor(cont.GetMmgColor().Clone());
        }else {
            SetMmgColor(cont.GetMmgColor());
        }
    }

    /**
     * Clones this object.
     * 
     * @return      A clone of this object. 
     */
    @Override
    public MmgObj Clone() {
        MmgContainer ret = new MmgContainer(this);
        return (MmgObj) ret;
    }

    /**
     * Adds a new MmgObj to the container.
     * 
     * @param obj   An MmgObj to add to the container. 
     */
    public void Add(MmgObj obj) {
        if(obj != null) {
            if(container.add(obj) == true) {
                StampChild(obj);
            }
        }
    }

    /**
     * Adds a new MmgObj to the container at the specified index.
     * 
     * @param idx       The index to add the object at.
     * @param obj       The object to add. 
     */
    public void AddAt(int idx, MmgObj obj) {
        if(obj != null) {
            container.add(idx, obj);
        }
    }
    
    /**
     * Removes an MmgObj from the container.
     * 
     * @param obj   An MmgObj to remove from the container. 
     */
    public void Remove(MmgObj obj) {
        if(obj != null) {
            if(container.remove(obj) == true) {
                UnstampChild(obj);
            }
        }
    }

    /**
     * Removes an MmgObj from the container at the specified index.
     * 
     * @param idx       The index to remove the object from.
     * @return          The MmgObj to remove.
     */
    public MmgObj RemoveAt(int idx) {
        MmgObj obj = container.remove(idx);
        if(obj != null) {
            UnstampChild(obj);
        }
        return obj;
    }
    
    /**
     * Gets the number of objects in the container.
     * 
     * @return      The number of objects in the container. 
     */
    public int GetCount() {
        return container.size();
    }

    /**
     * Gets an array representation of the objects in the container.
     * 
     * @return      An array of the objects in the container. 
     */
    public Object[] GetArray() {
        return container.toArray();
    }

    /**
     * Returns the MmgObj at the given index.
     * 
     * @param idx       The index to get an MmgObj from.
     * @return          The MmgObj at the specified index.
     */
    public MmgObj GetAt(int idx) {
        return container.get(idx);
    }
    
    /**
     * Clears all objects from the container.
     */
    public void Clear() {
        UpdateAllChildren(ChildAction.UNSTAMP);
        container.clear();
    }

    /**
     * Gets the ArrayList container that holds all child objects.
     * 
     * @return      The ArrayList container of this MmgContainer object. 
     */
    public ArrayList<MmgObj> GetContainer() {
        return container;
    }

    /**
     * Sets the ArrayList container that holds all the child objects.
     * 
     * @param aTmp      An ArrayList to set this container's contents from.
     */
    public void SetContainer(ArrayList<MmgObj> aTmp) {
        if(aTmp != null) {
            container = aTmp;
            UpdateAllChildren(ChildAction.STAMP);
        }else{
            container = new ArrayList(MmgContainer.INITIAL_SIZE);
        }
    }

    private void UpdateAllChildren(ChildAction act) {
        int len = GetCount();
        MmgObj obj;
        for(int j = 0; j < len; j++) {
            obj = container.get(j);
            if(obj != null) {
                if(act == ChildAction.STAMP) {
                    StampChild(obj);
                }else{
                    UnstampChild(obj);
                }
            }
        }
    }
    
    private void StampChild(MmgObj obj) {
        if(obj != null) {
            obj.SetHasParent(true);
            obj.SetParent(this);
        }
    }
    
    private void UnstampChild(MmgObj obj) {
        if(obj != null) {
            obj.SetHasParent(false);
            obj.SetParent(null);
        }
    }
    
    /**
     * Returns the child at the given index.
     * 
     * @param idx       The index of the child to get.
     * @return          The child at the given index.
     */
    public MmgObj GetChildAt(int idx) {
        return container.get(idx);
    }
    
    /**
     * Returns the relative position of the child at the given index.
     * 
     * @param idx       The index of the child to get.
     * @return          An MmgVector2 object with the relative position of the child.
     */
    public MmgVector2 GetChildPosRelative(int idx) {
        MmgObj obj = container.get(idx);
        MmgVector2 v1 = new MmgVector2();
        v1.SetX(obj.GetX() - this.GetX());
        v1.SetY(obj.GetY() - this.GetY());
        return v1;
    }
    
    /**
     * Returns the absolute position of the child at the given index.
     * 
     * @param idx       The index of the child to get.
     * @return          An MmgVector2 object with the absolute position of the child.
     */
    public MmgVector2 GetChildPosAbsolute(int idx) {
        return container.get(idx).GetPosition();
    }
    
    /**
     * The base drawing method used to render this object with an MmgPen.
     * 
     * @param p     The MmgPen that will draw this object. 
     * @see MmgPen
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if(GetIsVisible() == true) {
            a = container.toArray();
            for(i = 0; i < a.length; i++) {
                mo = (MmgObj) a[i];
                if(mo != null && mo.GetIsVisible() == true) {
                    mo.MmgDraw(p);
                }else {
                    //do nothing
                }
            }
        }else {
            //do nothing
        }
    }
}

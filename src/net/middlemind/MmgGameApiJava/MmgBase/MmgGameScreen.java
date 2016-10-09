package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * Class that represents a basic game screen.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public class MmgGameScreen extends MmgObj {

    /**
     * Pause this screen.
     */    
    protected boolean pause;
    
    /**
     * Is this screen ready.
     */    
    protected boolean ready;    
    
    /**
     * The MmgContainer that holds all the child objects.
     */
    private MmgContainer objects;
    
    /**
     * A place holder for the background object
     * of the game screen.
     */
    private MmgObj background;
    
    /**
     * A place holder for the foreground object
     * of the game screen.
     */
    private MmgObj foreground;
    
    /**
     * A place holder for the header object.
     */
    private MmgObj header;
    
    /**
     * A place holder for the footer object.
     */
    private MmgObj footer;
    
    /**
     * A place holder for the left menu cursor.
     */
    private MmgObj leftCursor;
    
    /**
     * A place holder for the right menu cursor.
     */
    private MmgObj rightCursor;
    
    /**
     * A place holder for the message object of the game screen.
     */
    private MmgObj message;
    
    /**
     * The MmgMenuContainer place holder for holding a menu.
     */
    private MmgMenuContainer menu;
    
    /**
     * Helper variables for the menu.
     */
    private int menuIdx;
    
    /**
     * Helper variables for the menu.
     */
    private int menuStart;
    
    /**
     * Helper variables for the menu.
     */
    private int menuStop;
    
    /**
     * Event handler for update events.
     */
    private MmgUpdateHandler updateHandler;
    
    /**
     * Flag to indicate if the menu is used on this game screen.
     */
    private boolean menuOn;

    /**
     * Constructor for this class.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgGameScreen() {
        super();
        pause = false;
        ready = false;        
        objects = new MmgContainer();
        menu = new MmgMenuContainer();
        background = null;
        foreground = null;
        header = null;
        footer = null;
        leftCursor = null;
        rightCursor = null;
        message = null;
        menuIdx = 0;
        menuStart = 0;
        menuStop = 0;
        SetMenuOn(false);
        SetWidth(MmgScreenData.GetGameWidth());
        SetHeight(MmgScreenData.GetGameHeight());
        SetPosition(MmgVector2.GetOriginVec());
    }

    /**
     * Constructor that sets attributes based on the given argument.
     * 
     * @param gm        The MmgGameScreen to use for attribute settings.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgGameScreen(MmgGameScreen gm) {
        SetObjects(gm.GetObjects());
        SetMenu(gm.GetMenu());
        SetBackground(gm.GetBackground());
        SetForeground(gm.GetForeground());
        SetHeader(gm.GetHeader());
        SetFooter(gm.GetFooter());
        SetLeftCursor(gm.GetLeftCursor());
        SetRightCursor(gm.GetRightCursor());
        SetMessage(gm.GetMessage());
        SetMenuOn(gm.GetMenuOn());
        SetMenuIdx(gm.GetMenuIdx());
        SetMenuStart(gm.GetMenuStart());
        SetMenuStop(gm.GetMenuStop());

        if (gm.GetPosition() != null) {
            SetPosition(gm.GetPosition().Clone());
        } else {
            SetPosition(gm.GetPosition());
        }
        SetWidth(gm.GetWidth());
        SetHeight(gm.GetHeight());
        SetMmgColor(gm.GetMmgColor());
    }

    /**
     * Clone of this object.
     * 
     * @return      A clone of this object. 
     */
    @Override
    public MmgObj Clone() {
        MmgGameScreen ret = new MmgGameScreen(this);
        return (MmgObj) ret;
    }

    /**
     * Gets true if this game screen has loaded its resources and is ready to display itself.
     * 
     * @return True if this object is ready, false otherwise. 
     */
    public final boolean IsReady() {
        return ready;
    }

    /**
     * Sets if this game screen is ready to display itself.
     * 
     * @param b A boolean indicating if this object is ready for display.
     */
    public final void SetReady(boolean b) {
        ready = b;
    }
    
    /**
     * Pauses this object. If paused no drawing occurs.
     */
    public final void Pause() {
        pause = true;
        SetIsVisible(false);
    }
    
    /**
     * Un-pause this object. If paused no drawing occurs.
     */
    public final void UnPause() {
        pause = false;
        SetIsVisible(true);
    }
    
    /**
     * Gets the pause state of this object.
     * 
     * @return True if this object was paused, false otherwise.
     */
    public final boolean IsPaused() {
        if(pause == true) {
            return true;
        }else {
            return false;
        }
    }    
    
    /**
     * Gets if the menu is on.
     * 
     * @return      The menu on flag. 
     */
    public boolean GetMenuOn() {
        return menuOn;
    }

    /**
     * Sets if the menu is on.
     * 
     * @param m     The menu on flag. 
     */
    public void SetMenuOn(boolean m) {
        menuOn = m;
    }

    /**
     * Adds an MmgObj to the game screen.
     * 
     * @param obj   The object to add. 
     */
    public void AddObj(MmgObj obj) {
        if (objects != null) {
            objects.Add(obj);
        }
    }

    /**
     * Removes an MmgObj from the game screen.
     * 
     * @param obj   The object to remove. 
     */
    public void RemoveObj(MmgObj obj) {
        if (objects != null) {
            objects.Remove(obj);
        }
    }

    /**
     * Clears the objects on this screen.
     */
    public void ClearObjs() {
        if (objects != null) {
            objects.Clear();
        }
    }

    /**
     * Event handler for processing simple menu events.
     * 
     * @param e         The MmgEvent object to handle.
     */
    @SuppressWarnings("UnusedAssignment")
    public void EventHandler(MmgEvent e) {
        if(e.GetEventId() == MmgEvent.EVENT_ID_UP) {
            MoveMenuSelUp();
        }else if(e.GetEventId() == MmgEvent.EVENT_ID_DOWN) {
            MoveMenuSelDown();
        }else if(e.GetEventId() == MmgEvent.EVENT_ID_ENTER) {
            if(menu != null) {
                Object[] objs = menu.GetArray();
                MmgMenuItem item = null;
                item = (MmgMenuItem) objs[menuIdx];
                ProcessMenuItemSel(item);
            }
        }else if(e.GetEventId() == MmgEvent.EVENT_ID_SPACE) {
            if(menu != null) {
                Object[] objs = menu.GetArray();
                MmgMenuItem item = null;
                item = (MmgMenuItem) objs[menuIdx];
                ProcessMenuItemSel(item);
            }
        }
    }

    /**
     * Gets the object container for this game screen.
     * 
     * @return      The MmgContainer used by this game screen.
     */
    public MmgContainer GetObjects() {
        return objects;
    }

    /**
     * Sets the object container for this game screen.
     * 
     * @param c         The MmgContainer used by this game screen.
     */
    public void SetObjects(MmgContainer c) {
        objects = c;
    }

    /**
     * Gets the background object for this game screen.
     * 
     * @return      The background object.
     */
    public MmgObj GetBackground() {
        return background;
    }

    /**
     * Sets the background object for this game screen.
     * 
     * @param b     The background object.
     */
    public void SetBackground(MmgObj b) {
        background = b;
    }

    /**
     * Sets the background object centered.
     * 
     * @param b     The background object.
     * @see MmgHelper
     */
    public void SetCenteredBackground(MmgObj b) {
        MmgHelper.CenterHorAndVert(b);
        SetBackground(b);
    }

    /**
     * Gets the foreground object.
     * 
     * @return      The foreground object. 
     */
    public MmgObj GetForeground() {
        return foreground;
    }

    /**
     * Sets the foreground object.
     * 
     * @param b     The foreground object.
     */
    public void SetForeground(MmgObj b) {
        foreground = b;
    }

    /**
     * Gets the header object.
     * 
     * @return      The header object.
     */
    public MmgObj GetHeader() {
        return header;
    }

    /**
     * Sets the header object.
     * 
     * @param m     The header object. 
     */
    public void SetHeader(MmgObj m) {
        header = m;
        MmgHelper.CenterHorAndTop(header);
    }

    /**
     * Gets the footer object.
     * 
     * @return      The footer object. 
     */
    public MmgObj GetFooter() {
        return footer;
    }

    /**
     * Sets the footer object.
     * 
     * @param m     The footer object.
     */
    public void SetFooter(MmgObj m) {
        footer = m;
        MmgHelper.CenterHorAndBot(footer);
    }

    /**
     * Gets the left menu cursor.
     * 
     * @return      The left menu cursor. 
     */
    public MmgObj GetLeftCursor() {
        return leftCursor;
    }

    /**
     * Sets the left menu cursor.
     * 
     * @param m         The left menu cursor.
     */
    public void SetLeftCursor(MmgObj m) {
        leftCursor = m;
    }

    /**
     * Gets the right menu cursor.
     * 
     * @return      The right menu cursor. 
     */
    public MmgObj GetRightCursor() {
        return rightCursor;
    }

    /**
     * Sets the right menu cursor.
     * 
     * @param m         The right menu cursor.
     */
    public void SetRightCursor(MmgObj m) {
        rightCursor = m;
    }

    /**
     * Gets the message object.
     * 
     * @return      The message object. 
     */
    public MmgObj GetMessage() {
        return message;
    }

    /**
     * Sets the message object.
     * 
     * @param m     The message object. 
     */
    public void SetMessage(MmgObj m) {
        message = m;
    }

    /**
     * Gets the MmgMenuContainer object.
     * 
     * @return  The MmgMenuContainer object. 
     */
    public MmgMenuContainer GetMenu() {
        return menu;
    }

    /**
     * Sets the MmgMenuContainer object.
     * 
     * @param m     The MmgMenuContainer object. 
     */
    public void SetMenu(MmgMenuContainer m) {
        menu = m;
    }

    /**
     * Gets the current menu item index.
     * 
     * @return      The menu item index. 
     */
    public int GetMenuIdx() {
        return menuIdx;
    }

    /**
     * Sets the current menu item index.
     * 
     * @param i     The menu item index.
     */
    public void SetMenuIdx(int i) {
        menuIdx = i;
    }

    /**
     * Gets the menu start index.
     * 
     * @return      The menu start index.
     */
    public int GetMenuStart() {
        return menuStart;
    }

    /**
     * Sets the menu start index.
     * 
     * @param i         The menu start index.
     */
    public void SetMenuStart(int i) {
        menuStart = i;
    }

    /**
     * Gets the meu stop index.
     * 
     * @return      The menu stop index. 
     */
    public int GetMenuStop() {
        return menuStop;
    }

    /**
     * Sets the menu stop index.
     * 
     * @param i     The menu stop index. 
     */
    public void SetMenuStop(int i) {
        menuStop = i;
    }

    /**
     * Centers all default place holder object of this game screen.
     */
    public void CenterObjects() {
        if (background != null) {
            background.SetPosition(new MmgVector2((MmgScreenData.GetGameWidth() - background.GetWidth()) / 2, (MmgScreenData.GetGameHeight() - background.GetHeight()) / 2));
        }

        if (header != null) {
            header.SetPosition(new MmgVector2((MmgScreenData.GetGameWidth() - header.GetWidth()) / 2, (MmgScreenData.GetGameHeight() - header.GetHeight()) / 2));
        }

        if (footer != null) {
            footer.SetPosition(new MmgVector2((MmgScreenData.GetGameWidth() - footer.GetWidth()) / 2, (MmgScreenData.GetGameHeight() - footer.GetHeight()) / 2));
        }

        if (message != null) {
            message.SetPosition(new MmgVector2((MmgScreenData.GetGameWidth() - message.GetWidth()) / 2, (MmgScreenData.GetGameHeight() - message.GetHeight()) / 2));
        }

        if (foreground != null) {
            foreground.SetPosition(new MmgVector2((MmgScreenData.GetGameWidth() - foreground.GetWidth()) / 2, (MmgScreenData.GetGameHeight() - foreground.GetHeight()) / 2));
        }
    }

    /**
     * Sets a handler for the update event.
     * 
     * @param u     An MmgUpdateHandler to handle events from this object. 
     */
    public void SetMmgUpdateHandler(MmgUpdateHandler u) {
        updateHandler = u;
    }

    /**
     * Gets a handler for the update event.
     * 
     * @return  The MmgUpdateHandler that handles update events from this class.
     */
    public MmgUpdateHandler GetMmgUpdateHandler() {
        return updateHandler;
    }

    /**
     * Fires an update event to th update handler.
     * 
     * @param data      The event data to process.
     */
    public void Update(Object data) {
        if(updateHandler != null) {
            updateHandler.MmgHandleUpdate(data);
        }
    }

    /**
     * Base draw method, handles drawing this class.
     * 
     * @param p     The MmgPen used to draw this object. 
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if(IsPaused() == true) {
            //do nothing
        }else {
            if(GetIsVisible() == true) {
                if(background != null) {
                    background.MmgDraw(p);
                }

                if(header != null) {
                    header.MmgDraw(p);
                }

                if(footer != null) {
                    footer.MmgDraw(p);
                }

                if(message != null) {
                    message.MmgDraw(p);
                }

                if(objects != null) {
                    objects.MmgDraw(p);
                }

                if(menuOn == true) {
                    DrawMenu(p);
                }

                if(foreground != null) {
                    foreground.MmgDraw(p);
                }
            }else {
                //do nothing
            }
        }
    }

    public void MmgUpdate(int updateTick) {
        if(IsPaused() == true) {
            //do nothing
        }else {
            if(GetIsVisible() == true) {

                if(message != null) {
                    message.MmgUpdate(updateTick);
                }

                if(objects != null) {
                    objects.MmgUpdate(updateTick);
                }

                if(foreground != null) {
                    foreground.MmgUpdate(updateTick);
                }
            }else {
                //do nothing
            }
        }
    }    
    
    /**
     * Process a screen click.
     * 
     * @param v     The coordinates of the click.
     * @return      Boolean indicating if a menu item was the target of the click, menu item event is fired automatically
     *              by this class.
     */
    public boolean ProcessScreenClick(MmgVector2 v) {
        return ProcessScreenClick(v.GetX(), v.GetY());
    }

    /**
     * Process a screen click.
     * 
     * @param x     The X axis coordinate of the screen click.
     * @param y     The Y axis coordinate of the screen click.
     * @return      Boolean indicating if a menu item was the target of the click, menu item event is fired automatically
     *              by this class.
     */
    @SuppressWarnings("UnusedAssignment")
    public boolean ProcessScreenClick(int x, int y) {
        if (menuOn == true && menu != null) {
            //MmgDebug.wr("ProcessScreenClick:AAA");
            Object[] objs = menu.GetArray();
            MmgMenuItem item = null;
            if (objs != null) {
                
                //MmgDebug.wr("ProcessScreenClick:BBB");
                for (int i = 0; i < objs.length; i++) {
                    //MmgDebug.wr("ProcessScreenClick:CCC:" + i);
                    item = (MmgMenuItem) objs[i];
                    //MmgDebug.wr("ProcessScreenClick:CCC2: [" + x + "] [" + item.GetX() + "," + (item.GetX() + item.GetWidth()) + "]");
                    if (x >= item.GetX() && x <= (item.GetX() + item.GetWidth())) {

                        //MmgDebug.wr("ProcessScreenClick:CCC3: [" + y + "] [" + item.GetY() + "," + (item.GetY() + item.GetHeight()) + "]");
                        if (y >= item.GetY() && y <= (item.GetY() + item.GetHeight())) {

                            //MmgDebug.wr("ProcessScreenClick:DDD:" + i);
                            menuIdx = i;
                            ProcessMenuItemSel(item);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Fire the click event registered in the target menu item object.
     * 
     * @param item      The menu item to fire a click event for.
     * @see MmgMenuItem
     */
    public void ProcessMenuItemSel(MmgMenuItem item) {
        if(item != null) {
            MmgEvent me = item.GetEventPress();
            if (me != null && me.GetTargetEventHandler() != null) {
                me.GetTargetEventHandler().MmgHandleEvent(me);
            }
        }
    }

    /**
     * Move the current menu selection up.
     */
    public void MoveMenuSelUp() {
        if(menuIdx - 1 >= menuStart) {
            menuIdx--;
        }
    }

    /**
     * Move the current menu selection down.
     */
    public void MoveMenuSelDown() {
        if(menuIdx + 1 <= menuStop) {
            menuIdx++;
        }
    }

    /**
     * Draws the current menu.
     * 
     * @param p     The MmgPen object used to draw the menu. 
     */
    private void DrawMenu(MmgPen p) {
        if(menu != null) {
            int padPosY = 5;
            int padPosX = 5;
            Object[] objs = menu.GetArray();

            for(int i = 0; i < objs.length; i++) {
                if (objs[i] != null) {
                    MmgMenuItem tmp = (MmgMenuItem) objs[i];
                    if (tmp != null && tmp.GetIsVisible() == true) {
                        if (i == menuIdx) {
                            if (tmp.GetState() != MmgMenuItem.STATE_INACTIVE) {
                                tmp.SetState(MmgMenuItem.STATE_SELECTED);
                            }
                        } else {
                            if (tmp.GetState() != MmgMenuItem.STATE_INACTIVE) {
                                tmp.SetState(MmgMenuItem.STATE_NORMAL);
                            }
                        }

                        tmp.MmgDraw(p);

                        if (leftCursor != null) {
                            leftCursor.SetPosition(new MmgVector2(new double[]{(double) (tmp.GetX() - leftCursor.GetWidth() - padPosX), tmp.GetY()}));
                            leftCursor.MmgDraw(p);
                        }

                        if (rightCursor != null) {
                            rightCursor.SetPosition(new MmgVector2(new double[]{(double) (tmp.GetX() + tmp.GetWidth() + padPosX), tmp.GetY()}));
                            rightCursor.MmgDraw(p);
                        }
                    }
                }
            }
        }else {
            //menu is null;
        }
    }
}

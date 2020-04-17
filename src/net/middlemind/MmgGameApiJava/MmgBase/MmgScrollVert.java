package net.middlemind.MmgGameApiJava.MmgBase;

import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import java.awt.Color;
import java.awt.Graphics2D;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventHandler;

/**
 * A class the provides support for a vertical scroll pane.
 * Created by Middlemind Games 01/16/2020
 * 
 * @author Victor G. Brusca
 */
public class MmgScrollVert extends MmgObj {
    
    /**
     * An MmgBmp object that is the view port for a scroll pane that sits behind the view port.
     */        
    private MmgBmp viewPort;
    
    /**
     * A rectangle with the dimensions of the view port.
     */        
    private MmgRect viewPortRect;  
    
    /**
     * The height of the scroll pane view port.
     */      
    private int viewPortHeight;
    
    /**
     * An MmgBmp object that is the scroll pane to show a portion of through the view port.
     */       
    private MmgBmp scrollPane;
    
    /**
     * A rectangle with the dimensions of the scroll pane.
     */    
    private MmgRect scrollPaneRect;
    
    /**
     * The height of the scroll pane.
     */     
    private int scrollPaneHeight;
    
    /**
     * An MmgBmp object that is the vertical slider.
     */    
    private MmgBmp sliderVert;
    
    /**
     * A rectangle with the dimensions of the vertical slider.
     */     
    private MmgRect sliderVertRect;
    
    /**
     * An MmgBmp that is the horizontal slider's top button.
     */    
    private MmgBmp sliderUpButton;
    
    /**
     * A rectangle with the dimensions of the horizontal slider's top button.
     */    
    private MmgRect sliderUpButtonRect;
    
    /**
     * An MmgBmp that is the horizontal slider's bottom button.
     */      
    private MmgBmp sliderDownButton;
    
    /**
     * A rectangle with the dimensions of the horizontal slider's bottom button.
     */      
    private MmgRect sliderDownButtonRect;
    
    /**
     * The height difference between the view port and the scroll pane.
     */      
    private int heightDiff;
    
    /**
     * The height difference percentage between the view port and the scroll pane.
     */      
    private double heightDiffPrct;
    
    /**
     * A boolean flag indicating if the vertical scroll bar is visible.
     */    
    private boolean scrollBarVertVisible;
    
    /**
     * An MmgColor for the horizontal scroll bar background color.
     */    
    private MmgColor scrollBarColor;
    
    /**
     * An MmgColor for the horizontal scroll bar slider's color.
     */    
    private MmgColor scrollBarSliderColor;    
    
    /**
     * The vertical scroll bar width.
     */      
    private int scrollBarVertWidth;
    
    /**
     * The height of the vertical scroll bar slider.
     */    
    private int scrollBarVertSliderHeight;
    
    /**
     * The height of the scroll bar slider button.
     */     
    private int scrollBarSliderButtonHeight; 
    
    /**
     * The scroll interval of the vertical slider.
     */      
    private int intervalY;
    
    /**
     * The scroll interval percentage of the vertical slider.
     */     
    private double intervalPrctY;
    
    /**
     * A boolean flag indicating if the scroll pane needs to be updated during the MmgUpdate call.
     */    
    private boolean isDirty;
    
    /**
     * The current offset of the vertical scroll bar's slider.
     */    
    private int offsetYScrollBarSlider;
    
    /**
     * The current offset of the vertical scroll pane.
     */    
    private int offsetYScrollPane;
            
    /**
     * A boolean flag indicating if a bounding box should be drawn around the elements of the scroll pane.
     */    
    public static boolean SHOW_CONTROL_BOUNDING_BOX = false;
    
    /**
     * 
     */
    private MmgEvent clickScreen = new MmgEvent(null, "vert_click_screen", MmgScrollVert.SCROLL_VERT_CLICK_EVENT_ID, MmgScrollVert.SCROLL_VERT_CLICK_EVENT_TYPE, null, null);    
    
    /**
     * 
     */
    private MmgEvent clickUp = new MmgEvent(null, "vert_click_up", MmgScrollVert.SCROLL_VERT_SCROLL_UP_EVENT_ID, MmgScrollVert.SCROLL_VERT_CLICK_EVENT_TYPE, null, null);        
    
    /**
     * 
     */
    private MmgEvent clickDown = new MmgEvent(null, "vert_click_down", MmgScrollVert.SCROLL_VERT_SCROLL_DOWN_EVENT_ID, MmgScrollVert.SCROLL_VERT_CLICK_EVENT_TYPE, null, null);
    
    public static int SCROLL_VERT_CLICK_EVENT_TYPE = 1; 
    
    /**
     * An event id for a scroll pane click event.
     */    
    public static int SCROLL_VERT_CLICK_EVENT_ID = 0;
    
    /**
     * An event id for a scroll pane up click event.
     */     
    public static int SCROLL_VERT_SCROLL_UP_EVENT_ID = 1;
    
    /**
     * An event id for a scroll pane down click event.
     */     
    public static int SCROLL_VERT_SCROLL_DOWN_EVENT_ID = 2;
    
    /**
     * An MmgPen object instance used to draw the visible portion of the scroll pane to the view port.
     */    
    private MmgPen p;
    
    /**
     * An MmgRect used in the MmgUpdate method.
     */
    private MmgRect updSrcRect;
    
    /**
     * An MmgRect used in the MmgUpdate method.
     */    
    private MmgRect updDestRect;
    
    /**
     * A Color object used in the bounding box drawing of the MmgDraw method.
     */
    private Color c;    
    
    /**
     * Basic constructor that sets the required objects and dimensions.
     * Prepares the drawing dimensions based on the provided objects.
     * 
     * @param ViewPort                  The MmgBmp that shows a portion of the MmgBmp scroll pane.
     * @param ScrollPane                The MmgBmp the is used to display a portion of itself to the view port.
     * @param ScrollBarColor            The MmgColor to use for the scroll bar.
     * @param ScrollBarSliderColor      The MmgColor to use for the scroll bar slider.
     * @param ScrollBarWidth            The width of the scroll bar.
     * @param ScrollBarSliderHeight     The height of the scroll bar slider.
     * @param IntervalY                 The interval to use when moving the scroll bar.
     * @param GameState                 The game state to use when firing events from the scroll view.
     */
    public MmgScrollVert(MmgBmp ViewPort, MmgBmp ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int ScrollBarWidth, int ScrollBarSliderHeight, int IntervalY) {
        super();                
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarVertWidth = ScrollBarWidth;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        scrollBarVertSliderHeight = ScrollBarSliderHeight;
        scrollBarSliderButtonHeight = MmgHelper.ScaleValue(15);        
        PrepDimensions();       

        if(IntervalY != 0) {
            SetIntervalY(IntervalY);
        }        
    }

    /**
     * Abridged constructor that sets the required objects, colors, and dimensions.
     * Prepares the drawing dimensions based on the provided objects.
     * 
     * @param ViewPort                  The MmgBmp that shows a portion of the MmgBmp scroll pane.
     * @param ScrollPane                The MmgBmp the is used to display a portion of itself to the view port.
     * @param ScrollBarColor            The MmgColor to use for the scroll bar.
     * @param ScrollBarSliderColor      The MmgColor to use for the scroll bar slider.
     * @param IntervalY                 The interval to use when moving the scroll bar.
     * @param GameState                 The game state to use when firing events from the scroll view.
     */
    public MmgScrollVert(MmgBmp ViewPort, MmgBmp ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int IntervalY) {
        super();        
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        scrollBarVertWidth = MmgHelper.ScaleValue(10);
        scrollBarVertSliderHeight = MmgHelper.ScaleValue(30);
        scrollBarSliderButtonHeight = MmgHelper.ScaleValue(15);        
        PrepDimensions();
        
        if(IntervalY != 0) {
            SetIntervalY(IntervalY);
        }
    }    
    
    /**
     * Constructor that is based on an instance of this class.
     * 
     * @param obj       An MmgScrollHor instance.
     */
    public MmgScrollVert(MmgScrollVert obj) {
        super();
        if(obj.GetViewPort() == null) {
            SetViewPort(obj.GetViewPort());
        } else {
            SetViewPort(obj.GetViewPort().CloneTyped());
        }
        
        if(obj.GetViewPortRect() == null) {
            SetViewPortRect(obj.GetViewPortRect());
        } else {
            SetViewPortRect(obj.GetViewPortRect().Clone());
        }
        
        if(obj.GetScrollPane() == null) {
            SetScrollPane(obj.GetScrollPane());
        } else {
            SetScrollPane(obj.GetScrollPane().CloneTyped());
        }
        
        if(obj.GetScrollPaneRect() == null) {
            SetScrollPaneRect(obj.GetScrollPaneRect());
        } else {
            SetScrollPaneRect(obj.GetScrollPaneRect().Clone());
        }
        
        if(obj.GetSliderVert() == null) {
            SetSliderVert(obj.GetSliderVert());
        } else {
            SetSliderVert(obj.GetSliderVert().CloneTyped());
        }        
        
        if(obj.GetSliderVertRect() == null) {
            SetSliderVertRect(obj.GetSliderVertRect());
        } else {
            SetSliderVertRect(obj.GetSliderVertRect().Clone());
        }
                
        if(obj.GetSliderUpButton() == null) {
            SetSliderUpButton(obj.GetSliderUpButton());
        } else {
            SetSliderUpButton(obj.GetSliderUpButton().CloneTyped());
        }        
        
        if(obj.GetSliderUpButtonRect() == null) {
            SetSliderUpButtonRect(obj.GetSliderUpButtonRect());
        } else {
            SetSliderUpButtonRect(obj.GetSliderUpButtonRect().Clone());
        }

        if(obj.GetSliderDownButton() == null) {
            SetSliderDownButton(obj.GetSliderDownButton());
        } else {
            SetSliderDownButton(obj.GetSliderDownButton().CloneTyped());
        }        
        
        if(obj.GetSliderDownButtonRect() == null) {
            SetSliderDownButtonRect(obj.GetSliderDownButtonRect());
        } else {
            SetSliderDownButtonRect(obj.GetSliderDownButtonRect().Clone());
        }
        
        SetHeightDiff(obj.GetHeightDiff());
        SetScrollBarVertVisible(obj.IsScrollBarVertVisible());
        SetScrollBarVertVisible(obj.IsScrollBarVertVisible());
        
        if(obj.GetScrollBarColor() == null) {
            SetScrollBarColor(obj.GetScrollBarColor());
        } else {
            SetScrollBarColor(obj.GetScrollBarColor().Clone());
        }
        
        if(obj.GetScrollBarSliderColor() == null) {
            SetScrollBarSliderColor(obj.GetScrollBarSliderColor());
        } else {
            SetScrollBarSliderColor(obj.GetScrollBarSliderColor().Clone());
        }
        
        SetScrollBarVertWidth(obj.GetScrollBarVertWidth());
        SetScrollBarVertSliderHeight(obj.GetScrollBarVertSliderHeight());
        SetIntervalY(obj.GetIntervalY());
        SetOffsetY(obj.GetOffsetY());
    }
    
    /**
     * Creates a basic clone of this class.
     * 
     * @return      A clone of this class.
     */
    @Override
    public MmgObj Clone() {
        MmgScrollVert ret = new MmgScrollVert(this);
        return (MmgObj) ret;
    }
    
    /**
     * Creates a typed clone of this class.
     * 
     * @return      A typed clone of this class.
     */
    @Override    
    public MmgScrollVert CloneTyped() {
        return new MmgScrollVert(this);
    } 
    
    /**
     * Prepares the dimension of the scroll view, scroll bar, slider, and buttons.
     */
    public void PrepDimensions() {
        int left = 0;
        int top = 0;
        int bottom = viewPort.GetHeight();
        int right = viewPort.GetWidth();
        viewPortRect = new MmgRect(left, top, bottom, right);
        viewPortHeight = viewPortRect.GetHeight();
        
        sliderDownButton = MmgHelper.GetBasicCachedBmp("scroll_bar_down_sm.png");
        sliderUpButton = MmgHelper.GetBasicCachedBmp("scroll_bar_up_sm.png");
        sliderVert = MmgHelper.GetBasicCachedBmp("scroll_bar_slider_sm.png");
        if(sliderUpButton != null && sliderDownButton != null && sliderVert != null) {
            scrollBarSliderButtonHeight = sliderUpButton.GetHeight();
            scrollBarVertWidth = sliderUpButton.GetWidth();
            scrollBarVertSliderHeight = sliderVert.GetHeight();
        }
        
        left = 0;
        top = 0;
        bottom = scrollPane.GetHeight();
        right = scrollPane.GetWidth();
        scrollPaneRect = new MmgRect(left, top, bottom, right);
        scrollPaneHeight = scrollPaneRect.GetHeight();
                
        left = viewPort.GetWidth();
        top = scrollBarSliderButtonHeight;
        bottom = scrollBarSliderButtonHeight + scrollBarVertSliderHeight;
        right = viewPort.GetWidth() + scrollBarVertWidth;
        sliderVertRect = new MmgRect(left, top, bottom, right);
        
        left = viewPort.GetWidth();
        top = 0;
        bottom = scrollBarSliderButtonHeight;
        right = viewPort.GetWidth() + scrollBarVertWidth;
        sliderUpButtonRect = new MmgRect(left, top, bottom, right);        
        
        left = viewPort.GetWidth();
        top = viewPort.GetHeight() - scrollBarSliderButtonHeight;
        bottom = viewPort.GetHeight();
        right = viewPort.GetWidth() + scrollBarVertWidth;
        sliderDownButtonRect = new MmgRect(left, top, bottom, right);   
           
        if(sliderUpButton != null && sliderDownButton != null && sliderVert != null) {
            sliderUpButton.SetPosition(sliderUpButtonRect.GetPosition());
            sliderDownButton.SetPosition(sliderDownButtonRect.GetPosition());            
            sliderVert.SetPosition(sliderVertRect.GetPosition());
        }
        
        PrepScrollPane();        
        isDirty = true;
    }
    
    /**
     * Finalizes the scroll view's preparation.
     * Called at the end of the PrepDimensions method.
     */    
    public void PrepScrollPane() {
        if(scrollPaneHeight - viewPortHeight > 0) {
            heightDiff = scrollPaneHeight - viewPortHeight;
            heightDiffPrct = heightDiff / viewPortHeight;
            scrollBarVertVisible = true;            
        } else {
            heightDiff = 0;
            heightDiffPrct = 0.0;
            scrollBarVertVisible = false;
        }
                
        MmgHelper.wr("scrollBarVertVisible: " + scrollBarVertVisible);

        p = new MmgPen((Graphics2D)viewPort.GetImage().getGraphics());
        p.SetAdvRenderHints();
    }    

    public void SetEventHandler(MmgEventHandler e) {
        clickScreen.SetTargetEventHandler(e);
        clickUp.SetTargetEventHandler(e);
        clickDown.SetTargetEventHandler(e);
    }
    
    public MmgEvent GetClickScreen() {
        return clickScreen;
    }

    public void SetClickScreen(MmgEvent e) {
        clickScreen = e;
    }

    public MmgEvent GetClickUp() {
        return clickUp;
    }

    public void SetClickUp(MmgEvent e) {
        clickUp = e;
    }

    public MmgEvent GetClickDown() {
        return clickDown;
    }

    public void SetClickDown(MmgEvent e) {
        clickDown = e;
    }
    
    /**
     * Sets the X coordinate of this scroll view.
     * 
     * @param inX       The X coordinate of this scroll view.
     */
    @Override
    public void SetX(int inX) {
        super.SetX(inX);
        viewPortRect.SetPosition(new MmgVector2(inX, viewPortRect.GetTop()));
        scrollPaneRect.SetPosition(new MmgVector2(inX, scrollPaneRect.GetTop()));
        sliderVertRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderVertRect.GetTop()));
        sliderUpButtonRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderUpButtonRect.GetTop()));
        sliderDownButtonRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderDownButtonRect.GetTop()));        
        
        if(sliderUpButton != null && sliderDownButton != null && sliderVert != null) {
            sliderUpButton.SetPosition(sliderUpButtonRect.GetPosition());
            sliderDownButton.SetPosition(sliderDownButtonRect.GetPosition());            
            sliderVert.SetPosition(sliderVertRect.GetPosition());
        }        
    }
    
    /**
     * Sets the Y coordinate of this scroll view.
     * 
     * @param inY       The Y coordinate of this scroll view.
     */    
    @Override
    public void SetY(int inY) {
        super.SetX(inY);
        viewPortRect.SetPosition(new MmgVector2(viewPortRect.GetLeft(), inY));
        scrollPaneRect.SetPosition(new MmgVector2(scrollPaneRect.GetLeft(), inY));
        sliderVertRect.SetPosition(new MmgVector2(sliderVertRect.GetLeft(), inY + scrollBarSliderButtonHeight + offsetYScrollBarSlider));
        sliderUpButtonRect.SetPosition(new MmgVector2(sliderUpButtonRect.GetLeft(), inY));
        sliderDownButtonRect.SetPosition(new MmgVector2(sliderDownButtonRect.GetLeft(), inY + viewPortRect.GetHeight() - scrollBarSliderButtonHeight));
        
        if(sliderUpButton != null && sliderDownButton != null && sliderVert != null) {
            sliderUpButton.SetPosition(sliderUpButtonRect.GetPosition());
            sliderDownButton.SetPosition(sliderDownButtonRect.GetPosition()); 
            sliderVert.SetPosition(sliderVertRect.GetPosition());
        }
    }
    
    /**
     * Sets the position of the scroll view.
     * 
     * @param inPos     The position to set the scroll view.
     */    
    @Override
    public void SetPosition(MmgVector2 inPos) {
        super.SetPosition(inPos);
        viewPortRect.SetPosition(inPos);
        scrollPaneRect.SetPosition(inPos);
        sliderVertRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY() + scrollBarSliderButtonHeight + offsetYScrollBarSlider));
        sliderUpButtonRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY()));
        sliderDownButtonRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY() + viewPortRect.GetHeight() - scrollBarSliderButtonHeight));
        
        if(sliderUpButton != null && sliderDownButton != null && sliderVert != null) {
            sliderUpButton.SetPosition(sliderUpButtonRect.GetPosition());
            sliderDownButton.SetPosition(sliderDownButtonRect.GetPosition());            
            sliderVert.SetPosition(sliderVertRect.GetPosition());
        }        
    }
        
    /**
     * Processes dpad input release events.
     * 
     * @param dir   The direction of the dpad input to process.
     * @return      A boolean indicating if the dpad input was handled.
     */
    public boolean ProcessDpadRelease(int dir) {
        if(scrollBarVertVisible && dir == MmgDir.DIR_BACK) {
            MmgHelper.wr("ProcessDpadRelease.sliderTopButtonRect click");
            if(offsetYScrollBarSlider - intervalY > viewPort.GetY() + scrollBarSliderButtonHeight) {
                offsetYScrollBarSlider -= intervalY;
                offsetYScrollPane -= (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = 0;
                offsetYScrollPane = 0; 
            }
            
            if(clickUp != null) {
                clickUp.Fire();
            }
            
            isDirty = true;
            return true;
            
        } else if(scrollBarVertVisible && dir == MmgDir.DIR_FRONT) {
            MmgHelper.wr("ProcessDpadRelease.sliderBottomButtonRect click");
            if(scrollBarSliderButtonHeight + offsetYScrollBarSlider + intervalY < viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarVertSliderHeight) {
                offsetYScrollBarSlider += intervalY;
                offsetYScrollPane += (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = (viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarVertSliderHeight);
                offsetYScrollPane = heightDiff;  
            }
            
            if(clickDown != null) {
                clickDown.Fire();
            }            
            
            isDirty = true;            
            return true;
            
        }
        
        return false;
    }
    
    /**
     * Handle screen click events.
     * 
     * @param x     The X coordinate of the screen click event.
     * @param y     The Y coordinate of the screen click event.
     * @return      A boolean indicating if the click event was handled.
     */
    public boolean ProcessScreenClick(int x, int y) {
        boolean ret = false;
        
        if(MmgHelper.RectCollision(x, y, viewPortRect)) {
            MmgHelper.wr("viewPort Vert click: X: " + x + " Y: " + y + " GetX: " + GetX() + " GetY: " + GetY());
            if(clickScreen != null) {
                clickScreen.Fire();
            }
            ret = true;
            
        //}else if(MmgHelper.RectCollision(x, y, 3, 3, scrollPaneRect)) {
            //MmgHelper.wr("scrollPane click");
            //return true;
                        
        }else if(scrollBarVertVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderUpButtonRect)) {
            MmgHelper.wr("ProcessScreenClick.sliderTopButtonRect click");
            if(offsetYScrollBarSlider - intervalY > viewPort.GetY() + scrollBarSliderButtonHeight) {
                offsetYScrollBarSlider -= intervalY;
                offsetYScrollPane -= (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = 0;
                offsetYScrollPane = 0; 
            }
            
            if(clickUp != null) {
                clickUp.Fire();
            }            
            
            isDirty = true;
            ret = true;
            
        }else if(scrollBarVertVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderDownButtonRect)) {            
            MmgHelper.wr("ProcessScreenClick.sliderBottomButtonRect click");
            if(scrollBarSliderButtonHeight + offsetYScrollBarSlider + intervalY < viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarVertSliderHeight) {
                offsetYScrollBarSlider += intervalY;
                offsetYScrollPane += (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = (viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarVertSliderHeight);
                offsetYScrollPane = heightDiff;  
            }
            
            if(clickDown != null) {
                clickDown.Fire();
            }
            
            isDirty = true;
            ret = true;
            
        //}else if(MmgHelper.RectCollision(x, y, sliderRect)) {
            //MmgHelper.wr("sliderRect click");            
            //ret = true;
            
        }
        
        return ret;
    }
    
    /**
     * Gets the current view port for scroll view.
     * 
     * @return      The view port for scroll view.
     */  
    public MmgBmp GetViewPort() {
        return viewPort;
    }

    /**
     * Sets the current view port for this scroll view.
     * Changing the view port requires a call to PrepDimensions.
     * 
     * @param ViewPort      The view port for this scroll view.
     */ 
    public void SetViewPort(MmgBmp ViewPort) {
        viewPort = ViewPort;
    }

    /**
     * Gets the scroll pane for this scroll view.
     * 
     * @return      The scroll pane for this scroll view.
     */
    public MmgBmp GetScrollPane() {
        return scrollPane;
    }

    /**
     * Sets the scroll pane for this scroll view.
     * Changing the scroll pane requires a call to PrepDimensions.
     * 
     * @param ScrollPane    The scroll pane for this scroll view.
     */    
    public void SetScrollPane(MmgBmp ScrollPane) {
        scrollPane = ScrollPane;
    }

    /**
     * Gets the MmgRect dimensions for the view port.
     * 
     * @return      The MmgRect dimensions for the view port.
     */    
    public MmgRect GetViewPortRect() {
        return viewPortRect;
    }

    /**
     * Sets the MmgRect dimensions for the view port.
     * Changing the view port dimensions requires a call to PrepDimensions.
     * 
     * @param r     The MmgRect dimensions for the view ports.
     */    
    public void SetViewPortRect(MmgRect r) {
        viewPortRect = r;
    }    
       
    /**
     * Gets the MmgRect dimensions for the scroll pane.
     * 
     * @return      The MmgRect dimensions for the scroll pane.
     */    
    public MmgRect GetScrollPaneRect() {
        return scrollPaneRect;
    }

    /**
     * Sets the MmgRect dimensions for the scroll pane.
     * Changing the scroll pane dimensiosn requires a call to PrepDimensions.
     * 
     * @param r     The MmgRect dimensions for this scroll pane.
     */    
    public void SetScrollPaneRect(MmgRect r) {
        scrollPaneRect = r;
    }
    
    /**
     * Gets the MmgBmp that is used for the vertical slider.
     * 
     * @return      The MmgBmp used for the vertical slider.
     */    
    public MmgBmp GetSliderVert() {
        return sliderVert;
    }
    
    /**
     * Sets the MmgBmp that is used for the horizontal slider.
     * Changing the slider requires a call to PrepDimensions.
     * 
     * @param b     The MmgBmp used for the horizontal slider.
     */
    public void SetSliderVert(MmgBmp b) {
        sliderVert = b;
    }
    
    /**
     * Gets the MmgRect dimensions for the vertical slider.
     * 
     * @return      The MmgRect dimensions for the vertical slider.
     */    
    public MmgRect GetSliderVertRect() {
        return sliderVertRect;
    }
    
    /**
     * Sets the MmgRect dimensions for the vertical slider.
     * Changing the slider dimensions requires a call to PrepDimensions.
     * 
     * @param r     The MmgRect dimensions for the vertical slider.
     */
    public void SetSliderVertRect(MmgRect r) {
        sliderVertRect = r;
    }    
           
    /**
     * Gets the MmgBmp used for the slider up button.
     * 
     * @return      The MmgBmp used for the slider up button.
     */
    public MmgBmp GetSliderUpButton() {
        return sliderUpButton;
    }    
    
    /**
     * Sets the MmgBmp used for the slider up button.
     * Changing the slider up button requires a call to PrepDimensions.
     * 
     * @param b     The MmgBmp used for the slider up button.
     */
    public void SetSliderUpButton(MmgBmp b) {
        sliderUpButton = b;
    }    
    
    /**
     * Gets the MmgRect dimensions for the up button.
     * 
     * @return      The MmgRect used for the up button.
     */
    public MmgRect GetSliderUpButtonRect() {
        return sliderUpButtonRect;
    }    
    
    /**
     * Sets the MmgRect dimensions for the up button.
     * Changing the up button requires a call to PrepDimensions.
     * 
     * @param r     The MmgRect used for the up button.
     */
    public void SetSliderUpButtonRect(MmgRect r) {
        sliderUpButtonRect = r;
    }
        
    /**
     * Gets the MmgBmp for the slider down button.
     * 
     * @return      The MmgBmp for the down button.
     */    
    public MmgBmp GetSliderDownButton() {
        return sliderDownButton;
    }    
    
    /**
     * Sets the MmgBmp for the slider down button.
     * Changing the down button requires a call to PrepDimensions.
     * 
     * @param b     The MmgBmp for the down button.
     */    
    public void SetSliderDownButton(MmgBmp b) {
        sliderDownButton = b;
    }        
    
    /**
     * Gets the MmgRect dimensions for the down button.
     * 
     * @return      The MmgRect used for the down button.
     */    
    public MmgRect GetSliderDownButtonRect() {
        return sliderDownButtonRect;
    }    
    
    /**
     * Sets the MmgRect dimensions for the down button.
     * Changing the down button requires a call to PrepDimensions.
     * 
     * @param r     The MmgRect used for the down button.
     */     
    public void SetSliderDownButtonRect(MmgRect r) {
        sliderDownButtonRect = r;
    }    
        
    /**
     * Gets the height difference between the view port and the scroll pane.
     * 
     * @return      The height difference between the view port and the scroll pane.
     */
    public int GetHeightDiff() {
        return heightDiff;
    }

    /**
     * Sets the height difference between the view port and the scroll pane.
     * 
     * @param HeightDiff    The height difference between the view port and the scroll pane.
     */
    public void SetHeightDiff(int HeightDiff) {
        heightDiff = HeightDiff;
    }

    /**
     * Gets the height difference percentage between the view port and the scroll pane.
     * 
     * @return      The height difference percentage between the view port and the scroll pane.
     */    
    public double GetHeightDiffPrct() {
        return heightDiffPrct;
    }

     /**
     * Sets the height difference percentage between the view port and the scroll pane.
     * 
     * @param d     The height difference percentage between the view port and the scroll pane.
     */
    public void SetHeightDiffPrect(double d) {
        heightDiffPrct = d;
    }
    
    /**
     * Gets a boolean indicating if the vertical scroll bar is visible.
     * 
     * @return  A boolean indicating if the vertical scroll bar is visible.
     */
    public boolean IsScrollBarVertVisible() {
        return scrollBarVertVisible;
    }

    /**
     * Sets a boolean value indicating if the vertical scroll bar is visible.
     * 
     * @param ScrollBarVertVisible      A boolean indicating if the vertical scroll bar is visible.
     */
    public void SetScrollBarVertVisible(boolean ScrollBarVertVisible) {
        scrollBarVertVisible = ScrollBarVertVisible;
    }

    /**
     * Gets the MmgColor of the scroll bar.
     * 
     * @return      The MmgColor of the scroll bar.
     */
    public MmgColor GetScrollBarColor() {
        return scrollBarColor;
    }

    /**
     * Sets the MmgColor of the scroll bar.
     * 
     * @param ScrollBarColor    The MmgColor of the scroll bar.
     */
    public void SetScrollBarColor(MmgColor ScrollBarColor) {
        scrollBarColor = ScrollBarColor;
    }

    /**
     * Gets the MmgColor of the scroll bar slider.
     * 
     * @return      The MmgColor of the scroll bar slider.
     */    
    public MmgColor GetScrollBarSliderColor() {
        return scrollBarSliderColor;
    }

    /**
     * Sets the MmgColor of the scroll bar slider.
     * 
     * @param ScrollBarSliderColor      The MmgColor of the scroll bar.
     */
    public void SetScrollBarSliderColor(MmgColor ScrollBarSliderColor) {
        scrollBarSliderColor = ScrollBarSliderColor;
    }

    /**
     * Gets the vertical scroll bar height.
     * 
     * @return      The vertical scroll bar height.
     */    
    public int GetScrollBarVertWidth() {
        return scrollBarVertWidth;
    }

    /**
     * Sets the vertical scroll bar height.
     * 
     * @param ScrollBarVertWidth    The vertical scroll bar height.
     */
    public void SetScrollBarVertWidth(int ScrollBarVertWidth) {
        scrollBarVertWidth = ScrollBarVertWidth;
    }

    /**
     * Gets the scroll bar slider button height.
     * 
     * @return      The scroll bar slider button height.
     */
    public int GetScrollBarSliderButtonHeight() {
        return scrollBarSliderButtonHeight;
    }

    /**
     * Sets the scroll bar slider button height.
     * 
     * @param ScrollBarSliderButtonWidth        The scroll bar slider button height.
     */    
    public void SetScrollBarSliderButtonHeight(int ScrollBarSliderButtonHeight) {
        scrollBarSliderButtonHeight = ScrollBarSliderButtonHeight;
    }
    
    /**
     * Gets the scroll bar vertical slider height.
     * 
     * @return      The scroll bar vertical slider height.
     */
    public int GetScrollBarVertSliderHeight() {
        return scrollBarVertSliderHeight;
    }

    /**
     * Sets the scroll bar vertical slider width.
     * 
     * @param ScrollBarHorSliderWidth       The scroll bar vertical slider width.
     */    
    public void SetScrollBarVertSliderHeight(int ScrollBarVertSliderHeight) {
        scrollBarVertSliderHeight = ScrollBarVertSliderHeight;
    }

    /**
     * Gets the interval for movement on the Y axis.
     * 
     * @return      The Y interval for movement.
     */    
    public int GetIntervalY() {
        return intervalY;
    }

    /**
     * Sets the interval for movement on the Y axis.
     * 
     * @param IntervalY     The Y interval for movement.
     */    
    public void SetIntervalY(int IntervalY) {
        if(IntervalY != 0) {
            intervalY = IntervalY;
            intervalPrctY = (double)intervalY / (viewPort.GetHeight() - (scrollBarSliderButtonHeight * 2) - scrollBarVertSliderHeight);
        }
    }

    /**
     * Gets a boolean indicating if the scroll view needs to be redrawn.
     * 
     * @return      A boolean indicating if the scroll view needs to be redrawn.
     */
    public boolean IsIsDirty() {
        return isDirty;
    }

    /**
     * Sets a boolean indicating if the scroll view needs to be redrawn.
     * 
     * @param IsDirty       A boolean indicating if the scroll view needs to be redrawn.
     */    
    public void SetIsDirty(boolean IsDirty) {
        isDirty = IsDirty;
    }

    /**
     * Gets the Y offset.
     * 
     * @return      The Y offset.
     */
    public int GetOffsetY() {
        return offsetYScrollBarSlider;
    }

    /**
     * Sets the Y offset.
     * 
     * @param OffsetX       The Y offset.
     */    
    public void SetOffsetY(int OffsetY) {
        offsetYScrollBarSlider = OffsetY;
    }
        
    /**
     * The MmgUpdate method used to call the update method of the child objects.
     * 
     * @param updateTicks           The update tick number. 
     * @param currentTimeMs         The current time in the game in milliseconds.
     * @param msSinceLastFrame      The number of milliseconds between the last frame and this frame.
     * @return                      A boolean indicating if any work was done.
     */
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        if(isVisible == true && isDirty == true) {
            sliderVertRect.SetPosition(new MmgVector2(sliderVertRect.GetLeft(), GetY() + scrollBarSliderButtonHeight + offsetYScrollBarSlider));
            scrollPaneRect.SetPosition(new MmgVector2(scrollPaneRect.GetLeft(), GetY() - offsetYScrollPane));

            if(sliderVert != null) {
                sliderVert.SetPosition(sliderVertRect.GetPosition());                    
            }

            updSrcRect = new MmgRect(0, offsetYScrollBarSlider, offsetYScrollBarSlider + viewPortRect.GetHeight(), viewPortRect.GetWidth());
            updDestRect = new MmgRect(0, 0, viewPortRect.GetHeight(), viewPortRect.GetWidth());
            p.DrawBmp(scrollPane, updSrcRect, updDestRect);
            
            isDirty = false;
            return true;
        }
        
        return false;
    }    
    
    /**
     * Draws this object using the given pen, MmgPen.
     *
     * @param p     The MmgPen to use to draw this object.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (isVisible == true) {
            if (MmgScrollVert.SHOW_CONTROL_BOUNDING_BOX == true) {
                c = p.GetGraphicsColor();
                
                //draw obj rect
                p.SetGraphicsColor(Color.RED);
                p.DrawRect(this);
                p.DrawRect(GetX() + GetWidth() - scrollBarVertWidth, GetY(), scrollBarVertWidth, h);
                
                //draw view port rect
                p.SetGraphicsColor(Color.BLUE);
                p.DrawRect(viewPortRect);
                
                //draw scroll pane rect
                p.SetGraphicsColor(Color.GREEN);                
                p.DrawRect(scrollPaneRect);
                
                if(scrollBarVertVisible) {
                    //slider
                    p.SetGraphicsColor(Color.ORANGE);
                    p.DrawRect(sliderVertRect);

                    //slider button bottom
                    p.SetGraphicsColor(Color.CYAN);
                    p.DrawRect(sliderDownButtonRect);

                    //slider button top
                    p.SetGraphicsColor(Color.PINK);
                    p.DrawRect(sliderUpButtonRect);
                }
                
                p.SetGraphicsColor(c);
            }

            /*
            Graphics g = p.GetGraphics();
            Color ct = g.getColor();
            g.setColor(Color.WHITE);
            g.fillRect(GetX(), GetY(), w - scrollBarWidth, h);
                       
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(GetX(), GetY(), 100, 100);            
            
            g.setColor(Color.cyan);
            g.fillRect(GetX(), GetY(), 50, 50);
            
            g.setColor(ct);
            */
            
            if(scrollBarVertVisible) {            
                if(sliderUpButton != null) {
                    p.DrawBmp(sliderUpButton);
                }
                
                if(sliderDownButton != null) {
                    p.DrawBmp(sliderDownButton);
                }
                
                if(sliderVert != null) {
                    p.DrawBmp(sliderVert);
                }                       
            }
            
            p.DrawBmp(viewPort, GetPosition());
            
        }
    }    
}

package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A class the provides support for a horizontal and vertical scroll pane.
 * Created by Middlemind Games 02/26/2020
 * 
 * @author Victor G. Brusca
 */
public class MmgScrollHorVert extends MmgObj {
    
    /**
     * An MmgBmp object that is the view port for a scroll pane that sits behind the view port.
     */
    private MmgBmp viewPort;
    
    /**
     * A rectangle with the dimensions of the view port.
     */
    private MmgRect viewPortRect;  
    
    /**
     * The width of the scroll pane view port.
     */
    private int viewPortWidth;
    
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
     * The width of the scroll pane.
     */
    private int scrollPaneWidth;
    
    /**
     * The height of the scroll pane
     */
    private int scrollPaneHeight;
    
    /**
     * An MmgBmp object that is the horizontal slider.
     */
    private MmgBmp sliderHor;
    
    /**
     * A rectangle with the dimensions of the horizontal slider.
     */
    private MmgRect sliderHorRect;
    
    /**
     * An MmgBmp object that is the vertical slider.
     */
    private MmgBmp sliderVert;
    
    /**
     * A rectangle with the dimensions of the vertical slider.
     */
    private MmgRect sliderVertRect;    
    
    /**
     * An MmgBmp that is the horizontal slider's left button.
     */
    private MmgBmp sliderLeftButton;
    
    /**
     * A rectangle with the dimensions of the horizontal slider's left button.
     */
    private MmgRect sliderLeftButtonRect;
    
    /**
     * An MmgBmp that is the horizontal slider's right button.
     */    
    private MmgBmp sliderRightButton;
    
    /**
     * A rectangle with the dimensions of the horizontal slider's right button.
     */    
    private MmgRect sliderRightButtonRect;
    
    /**
     * An MmgBmp that is the vertical slider's top button.
     */    
    private MmgBmp sliderUpButton;
    
    /**
     * A rectangle with the dimensions of the horizontal slider's top button.
     */    
    private MmgRect sliderUpButtonRect;
    
    /**
     * An MmgBmp that is the vertical slider's bottom button.
     */    
    private MmgBmp sliderDownButton;

    /**
     * A rectangle with the dimensions of the horizontal slider's bottom button.
     */    
    private MmgRect sliderDownButtonRect;    
    
    /**
     * The width difference between the view port and the scroll pane.
     */
    private int widthDiff;
    
    /**
     * The width difference percentage between the view port and the scroll pane.
     */
    private double widthDiffPrct;
    
    /**
     * The height difference between the view port and the scroll pane.
     */    
    private int heightDiff;
    
    /**
     * The height difference percentage between the view port and the scroll pane.
     */    
    private double heightDiffPrct;    
    
    /**
     * A boolean flag indicating if the horizontal scroll bar is visible.
     */    
    private boolean scrollBarHorVisible;
    
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
     * The horizontal scroll bar height.
     */
    private int scrollBarHorHeight;
    
    /**
     * The vertical scroll bar width.
     */
    private int scrollBarVertWidth;
    
    /**
     * The width of the horizontal scroll bar slider.
     */    
    private int scrollBarHorSliderWidth;
    
    /**
     * The height of the vertical scroll bar slider.
     */
    private int scrollBarVertSliderHeight;
    
    /**
     * The width of the scroll bar slider button.
     */
    private int scrollBarSliderButtonWidth;
    
    /**
     * The height of the scroll bar slider button.
     */
    private int scrollBarSliderButtonHeight;    
    
    /**
     * The scroll interval of the horizontal slider.
     */
    private int intervalX;
    
    /**
     * The scroll interval of the vertical slider.
     */
    private int intervalY;
    
    /**
     * The scroll interval percentage of the horizontal slider.
     */
    private double intervalPrctX;
    
    /**
     * The scroll interval percentage of the vertical slider.
     */
    private double intervalPrctY;
    
    /**
     * A boolean flag indicating if the scroll pane needs to be updated during the MmgUpdate call.
     */    
    private boolean isDirty;
    
    /**
     * The current offset of the horizontal scroll bar's slider.
     */
    private int offsetXScrollBarSlider;
    
    /**
     * The current offset of the vertical scroll bar's slider.
     */
    private int offsetYScrollBarSlider;
    
    /**
     * The current offset of the horizontal scroll pane.
     */    
    private int offsetXScrollPane;
    
    /**
     * The current offset of the vertical scroll pane.
     */    
    private int offsetYScrollPane;    
    
    /**
     * The MmgEvent to fire when a screen click occurs.
     */
    private MmgEvent clickScreen = new MmgEvent(null, "both_click_screen", MmgScrollHorVert.SCROLL_BOTH_CLICK_EVENT_ID, MmgScrollHorVert.SCROLL_BOTH_CLICK_EVENT_TYPE, null, null);    
    
    /**
     * The MmgEvent to fire when a scroll up event occurs.
     */
    private MmgEvent clickUp = new MmgEvent(null, "both_click_up", MmgScrollHorVert.SCROLL_BOTH_SCROLL_UP_EVENT_ID, MmgScrollHorVert.SCROLL_BOTH_CLICK_EVENT_TYPE, null, null);        
    
    /**
     * The MmgEvent to fire when a scroll down event occurs.
     */
    private MmgEvent clickDown = new MmgEvent(null, "both_click_down", MmgScrollHorVert.SCROLL_BOTH_SCROLL_DOWN_EVENT_ID, MmgScrollHorVert.SCROLL_BOTH_CLICK_EVENT_TYPE, null, null);
        
    /**
     * The MmgEvent to fire when a scroll left event occurs.
     */
    private MmgEvent clickLeft = new MmgEvent(null, "both_click_left", MmgScrollHorVert.SCROLL_BOTH_SCROLL_LEFT_EVENT_ID, MmgScrollHorVert.SCROLL_BOTH_CLICK_EVENT_TYPE, null, null);        
    
    /**
     * The MmgEvent to fire when a scroll right event occurs.
     */
    private MmgEvent clickRight = new MmgEvent(null, "both_click_right", MmgScrollHorVert.SCROLL_BOTH_SCROLL_RIGHT_EVENT_ID, MmgScrollHorVert.SCROLL_BOTH_CLICK_EVENT_TYPE, null, null);
            
    /**
     * A boolean flag indicating if a bounding box should be drawn around the elements of the scroll pane.
     */    
    public static boolean SHOW_CONTROL_BOUNDING_BOX = false;
    
    /**
     * An event type id for this object's events.
     */
    public static int SCROLL_BOTH_CLICK_EVENT_TYPE = 2;
    
    /**
     * An event id for a scroll pane click event.
     */
    public static int SCROLL_BOTH_CLICK_EVENT_ID = 6;
    
    /**
     * An event id for a scroll pane up click event.
     */
    public static int SCROLL_BOTH_SCROLL_UP_EVENT_ID = 7;
    
    /**
     * An event id for a scroll pane down click event.
     */
    public static int SCROLL_BOTH_SCROLL_DOWN_EVENT_ID = 8;
    
    /**
     * An event id for a scroll pane left click event.
     */
    public static int SCROLL_BOTH_SCROLL_LEFT_EVENT_ID = 9;
    
    /**
     * An event id for a scroll pane right click event.
     */
    public static int SCROLL_BOTH_SCROLL_RIGHT_EVENT_ID = 10;        
    
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
     * Basic constructor that sets the required objects, colors, and dimensions.
     * Prepares the drawing dimensions based on the provided objects.
     * 
     * @param ViewPort                  The MmgBmp that shows a portion of the MmgBmp scroll pane.
     * @param ScrollPane                The MmgBmp the is used to display a portion of itself to the view port.
     * @param ScrollBarColor            The MmgColor to use for the scroll bar.
     * @param ScrollBarSliderColor      The MmgColor to use for the scroll bar slider.
     * @param ScrollBarHeight           The height of the scroll bar.
     * @param ScrollBarWidth            The width of the scroll bar.
     * @param ScrollBarSliderHeight     The height of the scroll bar slider.
     * @param ScrollBarSliderWidth      The width of the scroll bar slider.
     * @param IntervalX                 The interval to use when moving the scroll bar.
     * @param IntervalY                 The interval to use when moving the scroll bar.
     * @param GameState                 The game state to use when firing events from the scroll view.
     */    
    public MmgScrollHorVert(MmgBmp ViewPort, MmgBmp ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int ScrollBarHeight, int ScrollBarWidth, int ScrollBarSliderHeight, int ScrollBarSliderWidth, int IntervalX, int IntervalY) {
        super();
        
        scrollBarSliderButtonWidth = MmgHelper.ScaleValue(15);
        scrollBarSliderButtonHeight = MmgHelper.ScaleValue(15);         
        
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarHorHeight = ScrollBarHeight;
        scrollBarVertWidth = ScrollBarWidth;
        
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        scrollBarHorSliderWidth = ScrollBarSliderWidth;
        scrollBarVertSliderHeight = ScrollBarSliderHeight;
        
        PrepDimensions();

        if(IntervalX != 0) {
            SetIntervalX(IntervalX);
        }
        
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
     * @param IntervalX                 The interval to use when moving the scroll bar.
     * @param IntervalY                 The interval to use when moving the scroll bar.
     * @param GameState                 The game state to use when firing events from the scroll view.
     */    
    public MmgScrollHorVert(MmgBmp ViewPort, MmgBmp ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int IntervalX, int IntervalY) {
        super();
        
        scrollBarHorHeight = MmgHelper.ScaleValue(10);
        scrollBarVertWidth = MmgHelper.ScaleValue(10);
        scrollBarHorSliderWidth = MmgHelper.ScaleValue(30);
        scrollBarVertSliderHeight = MmgHelper.ScaleValue(30);
        scrollBarSliderButtonWidth = MmgHelper.ScaleValue(15);
        scrollBarSliderButtonHeight = MmgHelper.ScaleValue(15);        
        
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;        
        
        PrepDimensions();
        
        if(IntervalX != 0) {
            SetIntervalX(IntervalX);
        }
        
        if(IntervalY != 0) {        
            SetIntervalY(IntervalY);            
        }
    }    
    
    /**
     * Constructor that is based on an instance of this class.
     * 
     * @param obj       An MmgScrollHor instance.
     */
    public MmgScrollHorVert(MmgScrollHorVert obj) {
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
        
        if(obj.GetSliderHor() == null) {
            SetSliderHor(obj.GetSliderHor());
        } else {
            SetSliderHor(obj.GetSliderHor().CloneTyped());
        }        
        
        if(obj.GetSliderHorRect() == null) {
            SetSliderHorRect(obj.GetSliderHorRect());
        } else {
            SetSliderHorRect(obj.GetSliderHorRect().Clone());
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
        
        if(obj.GetSliderLeftButton() == null) {
            SetSliderLeftButton(obj.GetSliderLeftButton());
        } else {
            SetSliderLeftButton(obj.GetSliderLeftButton().CloneTyped());
        }        
        
        if(obj.GetSliderLeftButtonRect() == null) {
            SetSliderLeftButtonRect(obj.GetSliderLeftButtonRect());
        } else {
            SetSliderLeftButtonRect(obj.GetSliderLeftButtonRect().Clone());
        }

        if(obj.GetSliderRightButton() == null) {
            SetSliderRightButton(obj.GetSliderRightButton());
        } else {
            SetSliderRightButton(obj.GetSliderRightButton().CloneTyped());
        }        
        
        if(obj.GetSliderRightButtonRect() == null) {
            SetSliderRightButtonRect(obj.GetSliderRightButtonRect());
        } else {
            SetSliderRightButtonRect(obj.GetSliderRightButtonRect().Clone());
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
        
        SetWidthDiff(obj.GetWidthDiff());
        SetHeightDiff(obj.GetHeightDiff());
        SetScrollBarHorVisible(obj.IsScrollBarHorVisible());
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
        
        SetScrollBarHorHeight(obj.GetScrollBarHorHeight());
        SetScrollBarVertWidth(obj.GetScrollBarVertWidth());
        SetScrollBarHorSliderWidth(obj.GetScrollBarHorSliderWidth());
        SetScrollBarVertSliderHeight(obj.GetScrollBarVertSliderHeight());        
        SetIntervalX(obj.GetIntervalX());
        SetIntervalY(obj.GetIntervalY());
        SetOffsetX(obj.GetOffsetX());
        SetOffsetY(obj.GetOffsetY());
    }
    
    /**
     * Creates a basic clone of this class.
     * 
     * @return      A clone of this class.
     */
    @Override
    public MmgObj Clone() {
        MmgScrollHorVert ret = new MmgScrollHorVert(this);
        return (MmgObj) ret;
    }
    
    /**
     * Creates a typed clone of this class.
     * 
     * @return      A typed clone of this class.
     */
    @Override    
    public MmgScrollHorVert CloneTyped() {
        return new MmgScrollHorVert(this);
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
        viewPortWidth = viewPortRect.GetWidth();
        viewPortHeight = viewPortRect.GetHeight();
        
        sliderLeftButton = MmgHelper.GetBasicCachedBmp("scroll_bar_left_sm.png");
        sliderRightButton = MmgHelper.GetBasicCachedBmp("scroll_bar_right_sm.png");
        sliderDownButton = MmgHelper.GetBasicCachedBmp("scroll_bar_down_sm.png");
        sliderUpButton = MmgHelper.GetBasicCachedBmp("scroll_bar_up_sm.png");
        
        sliderHor = MmgHelper.GetBasicCachedBmp("scroll_bar_slider_sm.png");        
        if(sliderLeftButton != null && sliderRightButton != null && sliderHor != null) {
            scrollBarSliderButtonWidth = sliderLeftButton.GetWidth();
            scrollBarHorHeight = sliderLeftButton.GetHeight();
            scrollBarHorSliderWidth = sliderHor.GetWidth();            
        }
        
        sliderVert = MmgHelper.GetBasicCachedBmp("scroll_bar_slider_sm.png");        
        if(sliderUpButton != null && sliderDownButton != null && sliderVert != null) {
            scrollBarSliderButtonHeight = sliderUpButton.GetHeight();
            scrollBarVertWidth = sliderUpButton.GetWidth();
            scrollBarVertSliderHeight = sliderHor.GetHeight();            
        }        
        
        left = 0;
        top = 0;
        bottom = scrollPane.GetHeight();
        right = scrollPane.GetWidth();
        scrollPaneRect = new MmgRect(left, top, bottom, right);
        scrollPaneWidth = scrollPaneRect.GetWidth();
        scrollPaneHeight = scrollPaneRect.GetHeight();        
        
        //slider hor
        left = scrollBarSliderButtonWidth;
        top = viewPort.GetHeight();
        bottom = viewPort.GetHeight() + scrollBarHorHeight;
        right = scrollBarSliderButtonWidth + scrollBarHorSliderWidth;
        sliderHorRect = new MmgRect(left, top, bottom, right);
        
        //slider vert
        left = viewPort.GetWidth();
        top = scrollBarSliderButtonHeight;
        bottom = scrollBarSliderButtonHeight + scrollBarVertSliderHeight;
        right = viewPort.GetWidth() + scrollBarVertWidth;
        sliderVertRect = new MmgRect(left, top, bottom, right);
        
        //button left
        left = 0;
        top = viewPort.GetHeight();
        bottom = viewPort.GetHeight() + scrollBarHorHeight;
        right = scrollBarSliderButtonWidth;
        sliderLeftButtonRect = new MmgRect(left, top, bottom, right);        
          
        //button right
        left = viewPort.GetWidth() - scrollBarSliderButtonWidth;
        top = viewPort.GetHeight();
        bottom = viewPort.GetHeight() + scrollBarHorHeight;        
        right = viewPort.GetWidth();
        sliderRightButtonRect = new MmgRect(left, top, bottom, right);   
          
        //button top
        left = viewPort.GetWidth();
        top = 0;
        bottom = scrollBarSliderButtonHeight;
        right = viewPort.GetWidth() + scrollBarVertWidth;
        sliderUpButtonRect = new MmgRect(left, top, bottom, right);        
        
        //button bottom
        left = viewPort.GetWidth();
        top = viewPort.GetHeight() - scrollBarSliderButtonHeight;
        bottom = viewPort.GetHeight();
        right = viewPort.GetWidth() + scrollBarVertWidth;
        sliderDownButtonRect = new MmgRect(left, top, bottom, right);           
        
        if(sliderLeftButton != null && sliderRightButton != null && sliderHor != null) {
            sliderLeftButton.SetPosition(sliderLeftButtonRect.GetPosition());
            sliderRightButton.SetPosition(sliderRightButtonRect.GetPosition());            
            sliderHor.SetPosition(sliderHorRect.GetPosition());
        }
        
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
        if(scrollPaneWidth - viewPortWidth > 0) {
            widthDiff = scrollPaneWidth - viewPortWidth;
            widthDiffPrct = widthDiff / viewPortWidth;
            scrollBarHorVisible = true;            
        } else {
            widthDiff = 0;
            widthDiffPrct = 0.0;
            scrollBarHorVisible = false;
        }
        MmgHelper.wr("scrollBarHorVisible: " + scrollBarHorVisible);
        
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

    /**
     * Sets event handlers for all this object's event.
     * 
     * @param e     The MmgEventHandler to use to handle events.
     */    
    public void SetEventHandler(MmgEventHandler e) {
        clickScreen.SetTargetEventHandler(e);
        clickLeft.SetTargetEventHandler(e);
        clickRight.SetTargetEventHandler(e);
        clickUp.SetTargetEventHandler(e);
        clickDown.SetTargetEventHandler(e);        
    }    
    
    /**
     * Gets the click screen event.
     * 
     * @return      The click screen event.
     */    
    public MmgEvent GetClickScreen() {
        return clickScreen;
    }

    /**
     * Sets the click screen event.
     * 
     * @param e     The click screen event.
     */    
    public void SetClickScreen(MmgEvent e) {
        clickScreen = e;
    }

    /**
     * Gets the scroll up event.
     * 
     * @return      The scroll up event. 
     */
    public MmgEvent GetClickUp() {
        return clickUp;
    }

    /**
     * Sets the scroll up event.
     * 
     * @param e     The scroll up event.
     */
    public void SetClickUp(MmgEvent e) {
        clickUp = e;
    }

    /**
     * Gets the scroll down event.
     * 
     * @return      The scroll down event.
     */
    public MmgEvent GetClickDown() {
        return clickDown;
    }

    /**
     * Sets the scroll down event.
     * 
     * @param e     The scroll down event.
     */
    public void SetClickDown(MmgEvent e) {
        clickDown = e;
    }
    
    /**
     * Gets the scroll left event.
     * 
     * @return      The scroll left event.
     */
    public MmgEvent GetClickLeft() {
        return clickLeft;
    }

    /**
     * Sets the scroll left event.
     * 
     * @param e     The scroll left event.
     */
    public void SetClickLeft(MmgEvent e) {
        clickLeft = e;
    }

    /**
     * Gets the scroll right event.
     * 
     * @return      The scroll right event.
     */
    public MmgEvent GetClickRight() {
        return clickRight;
    }

    /**
     * Sets the scroll right event.
     * 
     * @param e     The scroll right event.
     */
    public void SetClickRight(MmgEvent e) {
        clickRight = e;
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
        
        sliderHorRect.SetPosition(new MmgVector2(inX + scrollBarSliderButtonWidth + offsetXScrollBarSlider, sliderHorRect.GetTop()));
        sliderLeftButtonRect.SetPosition(new MmgVector2(inX, sliderLeftButtonRect.GetTop()));
        sliderRightButtonRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth() - scrollBarSliderButtonWidth, sliderRightButtonRect.GetTop()));        

        if(sliderLeftButton != null && sliderRightButton != null && sliderHor != null) {
            sliderLeftButton.SetPosition(sliderLeftButtonRect.GetPosition());
            sliderRightButton.SetPosition(sliderRightButtonRect.GetPosition());            
            sliderHor.SetPosition(sliderHorRect.GetPosition());
        }
        
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
        
        sliderHorRect.SetPosition(new MmgVector2(sliderHorRect.GetLeft(), inY + viewPort.GetHeight()));
        sliderLeftButtonRect.SetPosition(new MmgVector2(sliderLeftButtonRect.GetLeft(), inY + viewPortRect.GetHeight()));
        sliderRightButtonRect.SetPosition(new MmgVector2(sliderRightButtonRect.GetLeft(), inY + viewPortRect.GetHeight()));

        if(sliderLeftButton != null && sliderRightButton != null && sliderHor != null) {
            sliderLeftButton.SetPosition(sliderLeftButtonRect.GetPosition());
            sliderRightButton.SetPosition(sliderRightButtonRect.GetPosition());            
            sliderHor.SetPosition(sliderHorRect.GetPosition());
        }
        
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
        
        sliderHorRect.SetPosition(new MmgVector2(inPos.GetX() + scrollBarSliderButtonWidth + offsetXScrollBarSlider, inPos.GetY() + viewPort.GetHeight()));
        sliderLeftButtonRect.SetPosition(new MmgVector2(inPos.GetX(), inPos.GetY() + viewPort.GetHeight()));
        sliderRightButtonRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth() - scrollBarSliderButtonWidth, inPos.GetY() + viewPortRect.GetHeight()));        

        if(sliderLeftButton != null && sliderRightButton != null && sliderHor != null) {
            sliderLeftButton.SetPosition(sliderLeftButtonRect.GetPosition());
            sliderRightButton.SetPosition(sliderRightButtonRect.GetPosition());            
            sliderHor.SetPosition(sliderHorRect.GetPosition());
        }
        
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
     * @param dir       The direction of the dpad input to process.
     * @return          A boolean indicating if the dpad input was handled.
     */    
    public boolean ProcessDpadRelease(int dir) {
        if(scrollBarHorVisible && dir == MmgDir.DIR_LEFT) {
            MmgHelper.wr("Both: ProcessDpadRelease.sliderLeftButtonRect click");
            if(offsetXScrollBarSlider - intervalX > viewPort.GetX() + scrollBarSliderButtonWidth) {
                offsetXScrollBarSlider -= intervalX;
                offsetXScrollPane -= (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = 0;
                offsetXScrollPane = 0; 
            }
            
            if(clickLeft != null) {
                clickLeft.Fire();
            }                                    
            
            isDirty = true;
            return true;
            
        } else if(scrollBarHorVisible && dir == MmgDir.DIR_RIGHT) {
            MmgHelper.wr("Both: ProcessDpadRelease.sliderRightButtonRect click");
            if(scrollBarSliderButtonWidth + offsetXScrollBarSlider + intervalX < viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarHorSliderWidth) {
                offsetXScrollBarSlider += intervalX;
                offsetXScrollPane += (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = (viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarSliderButtonWidth - scrollBarHorSliderWidth);
                offsetXScrollPane = widthDiff;  
            }
            
            if(clickRight != null) {
                clickRight.Fire();
            }
            
            isDirty = true;            
            return true;
            
        } else if(scrollBarVertVisible && dir == MmgDir.DIR_BACK) {
            MmgHelper.wr("Both: ProcessDpadRelease.sliderTopButtonRect click");
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
            MmgHelper.wr("Both: ProcessDpadRelease.sliderBottomButtonRect click");
            if(scrollBarSliderButtonHeight + offsetYScrollBarSlider + intervalY < viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarVertSliderHeight) {
                offsetYScrollBarSlider += intervalY;
                offsetYScrollPane += (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = (viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarSliderButtonHeight - scrollBarVertSliderHeight);
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
     * @param x         The X coordinate of the screen click event.
     * @param y         The Y coordinate of the screen click event.
     * @return          A boolean indicating if the click event was handled.
     */
    public boolean ProcessScreenClick(int x, int y) {
        boolean ret = false;
        
        if(MmgHelper.RectCollision(x, y, viewPortRect)) {
            MmgHelper.wr("Both: viewPort click: X: " + x + " Y: " + y + " GetX: " + GetX() + " GetY: " + GetY());
            if(clickScreen != null) {
                clickScreen.SetExtra(new MmgVector2(x, y));
                clickScreen.Fire();
            }
            ret = true;
                                    
        }else if(scrollBarHorVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderLeftButtonRect)) {
            MmgHelper.wr("Both: ProcessScreenClick.sliderLeftButtonRect click");
            if(offsetXScrollBarSlider - intervalX > viewPort.GetX() + scrollBarSliderButtonWidth) {
                MmgHelper.wr("AAA");
                offsetXScrollBarSlider -= intervalX;
                offsetXScrollPane -= (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = 0;
                offsetXScrollPane = 0; 
            }
            
            if(clickLeft != null) {
                clickLeft.Fire();
            }
            
            isDirty = true;
            ret = true;
            
        }else if(scrollBarHorVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderRightButtonRect)) {            
            MmgHelper.wr("Both: ProcessScreenClick.sliderRightButtonRect click");
            if(scrollBarSliderButtonWidth + offsetXScrollBarSlider + intervalX < viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarHorSliderWidth) {
                MmgHelper.wr("AAA");
                offsetXScrollBarSlider += intervalX;
                offsetXScrollPane += (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = (viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarSliderButtonWidth - scrollBarHorSliderWidth);
                offsetXScrollPane = widthDiff;  
            }
            
            if(clickRight != null) {
                clickRight.Fire();
            }                        
            
            isDirty = true;            
            ret = true;
            
        }else if(scrollBarVertVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderUpButtonRect)) {
            MmgHelper.wr("Both: ProcessScreenClick.sliderTopButtonRect click");
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
            MmgHelper.wr("Both: ProcessScreenClick.sliderBottomButtonRect click");
            if(scrollBarSliderButtonHeight + offsetYScrollBarSlider + intervalY < viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarVertSliderHeight) {
                offsetYScrollBarSlider += intervalY;
                offsetYScrollPane += (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = (viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarSliderButtonHeight - scrollBarVertSliderHeight);
                offsetYScrollPane = heightDiff;  
            }
            
            if(clickDown != null) {
                clickDown.Fire();
            }                                   
            
            isDirty = true;
            ret = true;
                        
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
     * Changing the scroll pane dimensions requires a call to PrepDimensions.
     * 
     * @param r     The MmgRect dimensions for this scroll pane.
     */
    public void SetScrollPaneRect(MmgRect r) {
        scrollPaneRect = r;
    }
    
    /**
     * Gets the MmgBmp that is used for the horizontal slider.
     * 
     * @return      The MmgBmp used for the horizontal slider.
     */    
    public MmgBmp GetSliderHor() {
        return sliderHor;
    }
    
    /**
     * Sets the MmgBmp that is used for the horizontal slider.
     * Changing the slider requires a call to PrepDimensions.
     * 
     * @param b     The MmgBmp used for the horizontal slider.
     */    
    public void SetSliderHor(MmgBmp b) {
        sliderHor = b;
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
     * Gets the MmgRect dimensions for the horizontal slider.
     * 
     * @return      The MmgRect dimensions for the horizontal slider.
     */    
    public MmgRect GetSliderHorRect() {
        return sliderHorRect;
    }
    
    /**
     * Sets the MmgRect dimensions for the horizontal slider.
     * Changing the slider dimensions requires a call to PrepDimensions.
     * 
     * @param r     The MmgRect dimensions for the horizontal slider.
     */    
    public void SetSliderHorRect(MmgRect r) {
        sliderHorRect = r;
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
    public MmgBmp GetSliderLeftButton() {
        return sliderLeftButton;
    }
    
    /**
     * Sets the MmgBmp used for the slider up button.
     * Changing the slider up button requires a call to PrepDimensions.
     * 
     * @param b     The MmgBmp used for the slider up button.
     */    
    public void SetSliderLeftButton(MmgBmp b) {
        sliderLeftButton = b;
    }    
    
    /**
     * Gets the MmgRect dimensions for the left button.
     * 
     * @return      The MmgRect used for the left button.
     */    
    public MmgRect GetSliderLeftButtonRect() {
        return sliderLeftButtonRect;
    }
        
    /**
     * Sets the MmgRect dimensions for the left button.
     * Changing the left button requires a call to PrepDimensions.
     * 
     * @param r     The MmgRect used for the left button.
     */    
    public void SetSliderLeftButtonRect(MmgRect r) {
        sliderLeftButtonRect = r;
    }    
    
    /**
     * Gets the MmgBmp for the slider right button.
     * 
     * @return      The MmgBmp for the right button.
     */    
    public MmgBmp GetSliderRightButton() {
        return sliderRightButton;
    }
    
    /**
     * Sets the MmgBmp for the slider right button.
     * Changing the right button requires a call to PrepDimensions.
     * 
     * @param b     The MmgBmp for the right button.
     */    
    public void SetSliderRightButton(MmgBmp b) {
        sliderRightButton = b;
    }
    
    /**
     * Gets the MmgRect dimensions for the right button.
     * 
     * @return      The MmgRect used for the right button.
     */    
    public MmgRect GetSliderRightButtonRect() {
        return sliderRightButtonRect;
    }
    
    /**
     * Sets the MmgRect dimensions for the right button.
     * Changing the right button requires a call to PrepDimensions.
     * 
     * @param r     The MmgRect used for the right button.
     */    
    public void SetSliderRightButtonRect(MmgRect r) {
        sliderRightButtonRect = r;
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
     * Gets the width difference between the view port and the scroll pane.
     * 
     * @return      The width difference between the view port and the scroll pane.
     */    
    public int GetWidthDiff() {
        return widthDiff;
    }

    /**
     * Sets the width difference between the view port and the scroll pane.
     * 
     * @param HeightDiff    The width difference between the view port and the scroll pane.
     */     
    public void SetWidthDiff(int WidthDiff) {
        widthDiff = WidthDiff;
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
     * Gets the width difference percentage between the view port and the scroll pane.
     * 
     * @return      The width difference percentage between the view port and the scroll pane.
     */        
    public double GetWidthDiffPrct() {
        return widthDiffPrct;
    }    
    
    /**
     * Sets the width difference percentage between the view port and the scroll pane.
     * 
     * @param d     The width difference percentage between the view port and the scroll pane.
     */    
    public void SetWidthDiffPrct(double d) {
        widthDiffPrct = d;
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
    public void SetHeightDiffPrct(double d) {
        heightDiffPrct = d;
    }     
    
    /**
     * Gets a boolean indicating if the horizontal scroll bar is visible.
     * 
     * @return  A boolean indicating if the horizontal scroll bar is visible.
     */      
    public boolean IsScrollBarHorVisible() {
        return scrollBarHorVisible;
    }

    /**
     * Sets a boolean value indicating if the horizontal scroll bar is visible.
     * 
     * @param ScrollBarHorVisible       A boolean indicating if the horizontal scroll bar is visible.
     */    
    public void SetScrollBarHorVisible(boolean ScrollBarHorVisible) {
        scrollBarHorVisible = ScrollBarHorVisible;
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
     * Gets the horizontal scroll bar height.
     * 
     * @return      The horizontal scroll bar height.
     */    
    public int GetScrollBarHorHeight() {
        return scrollBarHorHeight;
    }

    /**
     * Sets the vertical scroll bar height.
     * 
     * @param ScrollBarVertWidth    The vertical scroll bar height.
     */     
    public void SetScrollBarHorHeight(int ScrollBarHeight) {
        scrollBarHorHeight = ScrollBarHeight;
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
    public void SetScrollBarVertWidth(int ScrollBarWidth) {
        scrollBarVertWidth = ScrollBarWidth;
    }    
    
    /**
     * Gets the scroll bar slider button width.
     * 
     * @return      The scroll bar slider button width.
     */    
    public int GetScrollBarSliderButtonWidth() {
        return scrollBarSliderButtonWidth;
    }

    /**
     * Sets the scroll bar slider button width.
     * 
     * @param ScrollBarSliderButtonWidth        The scroll bar slider button width.
     */    
    public void SetScrollBarSliderButtonWidth(int ScrollBarSliderButtonWidth) {
        scrollBarSliderButtonWidth = ScrollBarSliderButtonWidth;
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
     * Gets the scroll bar horizontal slider width. 
     * 
     * @return      The scroll bar horizontal slider width.
     */    
    public int GetScrollBarHorSliderWidth() {
        return scrollBarHorSliderWidth;
    }

    /**
     * Sets the scroll bar horizontal slider width.
     * 
     * @param ScrollBarHorSliderWidth       The scroll bar horizontal slider width.
     */    
    public void SetScrollBarHorSliderWidth(int ScrollBarSliderWidth) {
        scrollBarHorSliderWidth = ScrollBarSliderWidth;
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
    public void SetScrollBarVertSliderHeight(int ScrollBarSliderHeight) {
        scrollBarVertSliderHeight = ScrollBarSliderHeight;
    }    
    
    /**
     * Gets the interval for movement on the X axis.
     * 
     * @return      The X interval for movement.
     */    
    public int GetIntervalX() {
        return intervalX;
    }

    /**
     * Sets the interval for movement on the X axis.
     * 
     * @param IntervalX     The X interval for movement.
     */    
    public void SetIntervalX(int IntervalX) {
        if(IntervalX != 0) {
            intervalX = IntervalX;
            intervalPrctX = (double)intervalX / (viewPort.GetWidth() - (scrollBarSliderButtonWidth * 2) - scrollBarHorSliderWidth);
        }
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
     * Sets the interval for movement on the X axis.
     * 
     * @param IntervalX     The X interval for movement.
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
     * Gets the X offset.
     * 
     * @return      The X offset.
     */      
    public int GetOffsetX() {
        return offsetXScrollBarSlider;
    }

    /**
     * Sets the X offset.
     * 
     * @param OffsetX       The X offset.
     */    
    public void SetOffsetX(int OffsetX) {
        offsetXScrollBarSlider = OffsetX;
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
            scrollPaneRect.SetPosition(new MmgVector2(GetX() - offsetXScrollPane, GetY() - offsetYScrollPane));

            sliderHorRect.SetPosition(new MmgVector2(GetX() + scrollBarSliderButtonWidth + offsetXScrollBarSlider, sliderHorRect.GetTop()));            
            if(sliderHor != null) {
                sliderHor.SetPosition(sliderHorRect.GetPosition());                    
            }

            sliderVertRect.SetPosition(new MmgVector2(sliderVertRect.GetLeft(), GetY() + scrollBarSliderButtonHeight + offsetYScrollBarSlider));
            if(sliderVert != null) {
                sliderVert.SetPosition(sliderVertRect.GetPosition());                    
            }

            updSrcRect = new MmgRect(offsetXScrollBarSlider, offsetYScrollBarSlider, offsetYScrollBarSlider + viewPortRect.GetHeight(), offsetXScrollBarSlider + viewPortRect.GetWidth());
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
     * @param p     The pen to use to draw this object, MmgPen.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (isVisible == true) {
            if (MmgScrollHorVert.SHOW_CONTROL_BOUNDING_BOX == true) {
                c = p.GetGraphicsColor();

                //draw obj rect
                p.SetGraphicsColor(Color.RED);
                p.DrawRect(this);
                p.DrawRect(GetX(), GetY() + GetHeight() - scrollBarHorHeight, w, scrollBarHorHeight);

                //draw view port rect
                p.SetGraphicsColor(Color.BLUE);
                p.DrawRect(viewPortRect);
                
                //draw scroll pane rect
                p.SetGraphicsColor(Color.GREEN);
                p.DrawRect(scrollPaneRect);
                
                if(scrollBarHorVisible) {
                    //slider
                    p.SetGraphicsColor(Color.ORANGE);
                    p.DrawRect(sliderHorRect);

                    //slider button bottom
                    p.SetGraphicsColor(Color.CYAN);
                    p.DrawRect(sliderRightButtonRect);

                    //slider button top
                    p.SetGraphicsColor(Color.PINK);
                    p.DrawRect(sliderLeftButtonRect);
                }
                
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
                
                p.GetGraphics().setColor(c);
            }
                        
            if(scrollBarHorVisible) {            
                if(sliderLeftButton != null) {
                    p.DrawBmp(sliderLeftButton);
                }
                
                if(sliderRightButton != null) {
                    p.DrawBmp(sliderRightButton);
                }
                
                if(sliderHor != null) {
                    p.DrawBmp(sliderHor);
                }                       
            }
            
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

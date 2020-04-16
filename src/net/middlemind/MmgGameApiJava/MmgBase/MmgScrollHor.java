package net.middlemind.MmgGameApiJava.MmgBase;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GameSettings;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventHandler;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A class the provides support for a horizontal scroll pane.
 * Created by Middlemind Games 01/16/2020
 * 
 * @author Victor G. Brusca
 */
public class MmgScrollHor extends MmgObj {
    
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
     * An MmgBmp object that is the horizontal slider.
     */    
    private MmgBmp sliderHor;
    
    /**
     * A rectangle with the dimensions of the horizontal slider.
     */    
    private MmgRect sliderHorRect;
    
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
     * The width difference between the view port and the scroll pane.
     */    
    private int widthDiff;
    
    /**
     * The width difference percentage between the view port and the scroll pane.
     */    
    private double widthDiffPrct;
    
    /**
     * A boolean flag indicating if the horizontal scroll bar is visible.
     */
    private boolean scrollBarHorVisible = false;
    
    /**
     * An MmgColor for the horizontal scroll bar background color.
     */
    private MmgColor scrollBarColor = MmgColor.GetWhite();
    
    /**
     * An MmgColor for the horizontal scroll bar slider's color.
     */
    private MmgColor scrollBarSliderColor = MmgColor.GetRed();    
    
    /**
     * The horizontal scroll bar height.
     */    
    private int scrollBarHorHeight = MmgHelper.ScaleValue(10);
    
    /**
     * The width of the horizontal scroll bar slider.
     */
    private int scrollBarHorSliderWidth = MmgHelper.ScaleValue(30);
    
    /**
     * The width of the scroll bar slider button.
     */    
    private int scrollBarSliderButtonWidth = MmgHelper.ScaleValue(15);
    
    /**
     * The scroll interval of the horizontal slider.
     */    
    private int intervalX = 5;
    
    /**
     * The scroll interval percentage of the horizontal slider.
     */    
    private double intervalPrctX = 5.0;
    
    /**
     * A boolean flag indicating if the scroll pane needs to be updated during the MmgUpdate call.
     */
    private boolean isDirty;
    
    /**
     * The current offset of the horizontal scroll bar's slider.
     */    
    private int offsetXScrollBarSlider;
    
    /**
     * The current offset of the horizontal scroll pane.
     */
    private int offsetXScrollPane;
    
    /**
     * An event handler that is used to process scroll pane click events.
     */    
    private GenericEventHandler handler;
    
    /**
     * A game state used in the event handler calls.
     */    
    private GameStates gameState;
    
    /**
     * A boolean flag indicating if a bounding box should be drawn around the elements of the scroll pane.
     */
    public static boolean SHOW_CONTROL_BOUNDING_BOX = false;   
    
    /**
     * An event id for a scroll pane click event.
     */    
    public static int SCROLL_HOR_CLICK_EVENT_ID = 3;
    
    /**
     * An event id for a scroll pane left click event.
     */    
    public static int SCROLL_HOR_SCROLL_LEFT_EVENT_ID = 4;
    
    /**
     * An event id for a scroll pane right click event.
     */    
    public static int SCROLL_HOR_SCROLL_RIGHT_EVENT_ID = 5;
    
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
     * @param ScrollBarSliderWidth      The width of the scroll bar slider.
     * @param IntervalX                 The interval to use when moving the scroll bar.
     * @param GameState                 The game state to use when firing events from the scroll view.
     */
    public MmgScrollHor(MmgBmp ViewPort, MmgBmp ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int ScrollBarHeight, int ScrollBarSliderWidth, int IntervalX, GameStates GameState) {
        super();        
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarHorHeight = ScrollBarHeight;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        scrollBarHorSliderWidth = ScrollBarSliderWidth;
        scrollBarSliderButtonWidth = MmgHelper.ScaleValue(15);
        gameState = GameState;       
        PrepDimensions();

        if(IntervalX != 0) {
            SetIntervalX(IntervalX);
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
     * @param GameState                 The game state to use when firing events from the scroll view.
     */
    public MmgScrollHor(MmgBmp ViewPort, MmgBmp ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int IntervalX, GameStates GameState) {
        super();
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarHorHeight = MmgHelper.ScaleValue(10);
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        scrollBarHorSliderWidth = MmgHelper.ScaleValue(30);
        scrollBarSliderButtonWidth = MmgHelper.ScaleValue(15);
        gameState = GameState;
        PrepDimensions();
        
        if(IntervalX != 0) {
            SetIntervalX(IntervalX);
        }
    }    
    
    /**
     * Constructor that is based on an instance of this class.
     * 
     * @param obj       An MmgScrollHor instance. 
     */
    public MmgScrollHor(MmgScrollHor obj) {
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
        
        SetWidthDiff(obj.GetWidthDiff());
        SetScrollBarHorVisible(obj.IsScrollBarHorVisible());
        SetScrollBarHorVisible(obj.IsScrollBarHorVisible());
        
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
        SetScrollBarHorSliderWidth(obj.GetScrollBarHorSliderWidth());
        SetIntervalX(obj.GetIntervalX());
        SetOffsetX(obj.GetOffsetX());
        SetGameState(obj.GetGameState());        
    }    
    
    /**
     * Creates a basic clone of this class.
     * 
     * @return      A clone of this class.
     */
    @Override
    public MmgObj Clone() {
        MmgScrollHor ret = new MmgScrollHor(this);
        return (MmgObj) ret;
    }
    
    /**
     * Creates a typed clone of this class.
     * 
     * @return      A typed clone of this class.
     */
    @Override    
    public MmgScrollHor CloneTyped() {
        return new MmgScrollHor(this);
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
        
        sliderLeftButton = MmgHelper.GetBasicCachedBmp("scroll_bar_left_sm.png");
        sliderRightButton = MmgHelper.GetBasicCachedBmp("scroll_bar_right_sm.png");
        sliderHor = MmgHelper.GetBasicCachedBmp("scroll_bar_slider_sm.png");        
        if(sliderLeftButton != null && sliderRightButton != null && sliderHor != null) {
            scrollBarSliderButtonWidth = sliderLeftButton.GetWidth();
            scrollBarHorHeight = sliderLeftButton.GetHeight();
            scrollBarHorSliderWidth = sliderHor.GetWidth();
        }
        
        left = 0;
        top = 0;
        bottom = scrollPane.GetHeight();
        right = scrollPane.GetWidth();
        scrollPaneRect = new MmgRect(left, top, bottom, right);
        scrollPaneWidth = scrollPaneRect.GetWidth();
        
        left = scrollBarSliderButtonWidth;
        top = viewPort.GetHeight();
        bottom = viewPort.GetHeight() + scrollBarHorHeight;
        right = scrollBarSliderButtonWidth + scrollBarHorSliderWidth;
        sliderHorRect = new MmgRect(left, top, bottom, right);
        
        left = 0;
        top = viewPort.GetHeight();
        bottom = viewPort.GetHeight() + scrollBarHorHeight;
        right = scrollBarSliderButtonWidth;
        sliderLeftButtonRect = new MmgRect(left, top, bottom, right);        
                
        left = viewPort.GetWidth() - scrollBarSliderButtonWidth;
        top = viewPort.GetHeight();
        bottom = viewPort.GetHeight() + scrollBarHorHeight;        
        right = viewPort.GetWidth();
        sliderRightButtonRect = new MmgRect(left, top, bottom, right);   
          
        if(sliderLeftButton != null && sliderRightButton != null && sliderHor != null) {
            sliderLeftButton.SetPosition(sliderLeftButtonRect.GetPosition());
            sliderRightButton.SetPosition(sliderRightButtonRect.GetPosition());            
            sliderHor.SetPosition(sliderHorRect.GetPosition());
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
       
        MmgDebug.wr("scrollBarHorVisible: " + scrollBarHorVisible);        
        
        p = new MmgPen((Graphics2D)viewPort.GetImage().getGraphics());
        p.SetAdvRenderHints();
    }    

    /**
     * Returns the event handler for this class instance.
     * 
     * @return      The event handler for this class instance.
     */    
    public GenericEventHandler GetHandler() {
        return handler;
    }

    /**
     * Sets the event handler for this class instance.
     * 
     * @param Handler   The event handler for this class instance.
     */    
    public void SetHandler(GenericEventHandler Handler) {
        handler = Handler;
    }

    /**
     * Gets the game state value used when firing events from this class instance.
     * 
     * @return      The game state value used when firing events.
     */
    public GameStates GetGameState() {
        return gameState;
    }

    /**
     * Sets the game state value used when firing events from this class instance.
     * 
     * @param GameState     The game state value when firing events.
     */
    public void SetGameState(GameStates GameState) {
        gameState = GameState;
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
    }
         
    /**
     * Processes dpad input release events.
     * 
     * @param dir   The direction of the dpad input to process.
     * @return      A boolean indicating if the dpad input was handled.
     */    
    public boolean ProcessDpadRelease(int dir) {
        if(scrollBarHorVisible && (dir == GameSettings.LEFT_KEYBOARD || dir == GameSettings.LEFT_GAMEPAD_1 || dir == GameSettings.LEFT_GPIO)) {
            MmgDebug.wr("ProcessDpadRelease.sliderLeftButtonRect click");
            if(offsetXScrollBarSlider - intervalX > viewPort.GetX() + scrollBarSliderButtonWidth) {
                offsetXScrollBarSlider -= intervalX;
                offsetXScrollPane -= (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = 0;
                offsetXScrollPane = 0; 
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHor.SCROLL_HOR_SCROLL_LEFT_EVENT_ID, new Integer(offsetXScrollPane), gameState));
            }            
            
            isDirty = true;
            return true;
            
        } else if(scrollBarHorVisible && (dir == GameSettings.RIGHT_KEYBOARD || dir == GameSettings.RIGHT_GAMEPAD_1 || dir == GameSettings.RIGHT_GPIO)) {
            MmgDebug.wr("ProcessDpadRelease.sliderRightButtonRect click");
            if(scrollBarSliderButtonWidth + offsetXScrollBarSlider + intervalX < viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarHorSliderWidth) {
                offsetXScrollBarSlider += intervalX;
                offsetXScrollPane += (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = (viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarHorSliderWidth);
                offsetXScrollPane = widthDiff;  
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHor.SCROLL_HOR_SCROLL_RIGHT_EVENT_ID, new Integer(offsetXScrollPane), gameState));
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
            MmgDebug.wr("viewPort click: X: " + x + " Y: " + y + " GetX: " + GetX() + " GetY: " + GetY());
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHor.SCROLL_HOR_CLICK_EVENT_ID, new MmgVector2(x + offsetXScrollPane - GetX(), y - GetY()), gameState));
            }
            ret = true;
            
        //}else if(MmgHelper.RectCollision(x, y, 3, 3, scrollPaneRect)) {
            //MmgDebug.wr("scrollPane click");
            //ret = true;
                        
        }else if(scrollBarHorVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderLeftButtonRect)) {
            MmgDebug.wr("ProcessScreenClick.sliderLeftButtonRect click");
            if(offsetXScrollBarSlider - intervalX > viewPort.GetX() + scrollBarSliderButtonWidth) {
                offsetXScrollBarSlider -= intervalX;
                offsetXScrollPane -= (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = 0;
                offsetXScrollPane = 0; 
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHor.SCROLL_HOR_SCROLL_RIGHT_EVENT_ID, new Integer(offsetXScrollPane), gameState));
            }                        
            
            isDirty = true;
            ret = true;
            
        }else if(scrollBarHorVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderRightButtonRect)) {            
            MmgDebug.wr("ProcessScreenClick.sliderRightButtonRect click");
            if(scrollBarSliderButtonWidth + offsetXScrollBarSlider + intervalX < viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarHorSliderWidth) {
                offsetXScrollBarSlider += intervalX;
                offsetXScrollPane += (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = (viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarHorSliderWidth);
                offsetXScrollPane = widthDiff;  
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHor.SCROLL_HOR_SCROLL_LEFT_EVENT_ID, new Integer(offsetXScrollPane), gameState));
            }            
            
            isDirty = true;            
            ret = true;
            
        //}else if(MmgHelper.RectCollision(x, y, sliderRect)) {
            //MmgDebug.wr("sliderRect click");            
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
    public void SetScrollBarHorHeight(int ScrollBarHorHeight) {
        scrollBarHorHeight = ScrollBarHorHeight;
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
    public void SetScrollBarHorSliderWidth(int ScrollBarHorSliderWidth) {
        scrollBarHorSliderWidth = ScrollBarHorSliderWidth;
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
     * The MmgUpdate method used to call the update method of the child objects.
     * 
     * @param updateTicks           The update tick number. 
     * @param currentTimeMs         The current time in the game in milliseconds.
     * @param msSinceLastFrame      The number of milliseconds between the last frame and this frame.
     * @return                      A boolean indicating if any work was done.
     */    
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        if(isVisible == true && isDirty == true) {
            sliderHorRect.SetPosition(new MmgVector2(GetX() + scrollBarSliderButtonWidth + offsetXScrollBarSlider, sliderHorRect.GetTop()));
            scrollPaneRect.SetPosition(new MmgVector2(GetX() - offsetXScrollPane, scrollPaneRect.GetTop()));

            if(sliderHor != null) {
                sliderHor.SetPosition(sliderHorRect.GetPosition());                    
            }

            updSrcRect = new MmgRect(offsetXScrollBarSlider, 0, viewPortRect.GetHeight(), offsetXScrollBarSlider + viewPortRect.GetWidth());
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
            if (MmgScrollHor.SHOW_CONTROL_BOUNDING_BOX == true) {
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
                
                p.SetGraphicsColor(c);
            }

            /*
            Graphics g = p.GetGraphics();
            Color ct = g.getColor();
            g.setColor(Color.WHITE);
            g.fillRect(GetX(), GetY(), w, h - scrollBarHeight);
                       
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(GetX(), GetY(), 100, 100);            
            
            g.setColor(Color.cyan);
            g.fillRect(GetX(), GetY(), 50, 50);
            
            g.setColor(ct);
            */
            
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
            
            p.DrawBmp(viewPort, GetPosition());
            
        }
    }    
}

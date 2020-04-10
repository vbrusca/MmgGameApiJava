package net.middlemind.MmgGameApiJava.MmgBase;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GameSettings;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventHandler;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
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
     * 
     */
    private MmgBmp viewPort = null;
    
    /**
     * 
     */
    private MmgRect viewPortRect = null;  
    
    /**
     * 
     */
    private int viewPortWidth = 0;
    
    /**
     * 
     */
    private int viewPortHeight = 0;
    
    private MmgBmp scrollPane = null;
    private MmgRect scrollPaneRect = null;
    private int scrollPaneWidth = 0;
    private int scrollPaneHeight = 0;
    
    private MmgBmp sliderHor = null;
    private MmgRect sliderHorRect = null;
    
    private MmgBmp sliderVert = null;
    private MmgRect sliderVertRect = null;    
    
    private MmgBmp sliderLeftButton = null;
    private MmgRect sliderLeftButtonRect = null;
    
    private MmgBmp sliderRightButton = null;
    private MmgRect sliderRightButtonRect = null;
    
    private MmgBmp sliderTopButton = null;
    private MmgRect sliderTopButtonRect = null;
    
    private MmgBmp sliderBottomButton = null;
    private MmgRect sliderBottomButtonRect = null;    
    
    private int widthDiff = 0;
    private double widthDiffPrct = 0.0;
    
    private int heightDiff = 0;
    private double heightDiffPrct = 0.0;    
    
    private boolean scrollBarHorVisible = false;
    private boolean scrollBarVertVisible = false;    
    private MmgColor scrollBarColor = MmgColor.GetWhite();
    private MmgColor scrollBarSliderColor = MmgColor.GetRed();    
    
    private int scrollBarHorHeight = MmgHelper.ScaleValue(10);
    private int scrollBarVertWidth = MmgHelper.ScaleValue(10);
    
    private int scrollBarHorSliderWidth = MmgHelper.ScaleValue(30);
    private int scrollBarVertSliderHeight = MmgHelper.ScaleValue(30);
    private int scrollBarSliderButtonWidth = MmgHelper.ScaleValue(15);    
    private int scrollBarSliderButtonHeight = MmgHelper.ScaleValue(15);    
    
    private int intervalX = 5;
    private int intervalY = 5;    
    private double intervalPrctX = 5.0;
    private double intervalPrctY = 5.0;
    
    private boolean isDirty = false;
    private int offsetXScrollBarSlider = 0;
    private int offsetYScrollBarSlider = 0;
    
    private int offsetXScrollPane = 0;
    private int offsetYScrollPane = 0;    
    
    private GenericEventHandler handler = null;
    private GameStates gameState = GameStates.BLANK;
    public static boolean SHOW_CONTROL_BOUNDING_BOX = false;    
    public static int SCROLL_BOTH_CLICK_EVENT_ID = 6;
    public static int SCROLL_BOTH_SCROLL_UP_EVENT_ID = 7;
    public static int SCROLL_BOTH_SCROLL_DOWN_EVENT_ID = 8;
    public static int SCROLL_BOTH_SCROLL_LEFT_EVENT_ID = 9;
    public static int SCROLL_BOTH_SCROLL_RIGHT_EVENT_ID = 10;        
    
    //Internal
    private MmgPen p = null;    
    private MmgRect updSrcRect = null;
    private MmgRect updDestRect = null;
    private Color c = null;
    
    public MmgScrollHorVert(MmgBmp ViewPort, MmgBmp ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int ScrollBarHeight, int ScrollBarWidth, int ScrollBarSliderHeight, int ScrollBarSliderWidth, int IntervalX, int IntervalY, GameStates GameState) {
        super();
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarHorHeight = ScrollBarHeight;
        scrollBarVertWidth = ScrollBarWidth;
        
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        scrollBarHorSliderWidth = ScrollBarSliderWidth;
        scrollBarVertSliderHeight = ScrollBarSliderHeight;
        
        gameState = GameState;       
        PrepDimensions();

        if(IntervalX != 0) {
            SetIntervalX(IntervalX);
        }
        
        if(IntervalY != 0) {        
            SetIntervalY(IntervalY);            
        }
    }

    public MmgScrollHorVert(MmgBmp ViewPort, MmgBmp ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int IntervalX, int IntervalY, GameStates GameState) {
        super();
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;        
        
        gameState = GameState;
        PrepDimensions();
        
        if(IntervalX != 0) {
            SetIntervalX(IntervalX);
        }
        
        if(IntervalY != 0) {        
            SetIntervalY(IntervalY);            
        }
    }    
    
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
    }
    
    @Override
    public MmgObj Clone() {
        MmgScrollHorVert ret = new MmgScrollHorVert(this);
        return (MmgObj) ret;
    }
    
    @Override    
    public MmgScrollHorVert CloneTyped() {
        return new MmgScrollHorVert(this);
    }    
    
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
        sliderBottomButton = MmgHelper.GetBasicCachedBmp("scroll_bar_down_sm.png");
        sliderTopButton = MmgHelper.GetBasicCachedBmp("scroll_bar_up_sm.png");
        
        sliderHor = MmgHelper.GetBasicCachedBmp("scroll_bar_slider_sm.png");        
        if(sliderLeftButton != null && sliderRightButton != null && sliderHor != null) {
            scrollBarSliderButtonWidth = sliderLeftButton.GetWidth();
            scrollBarHorHeight = sliderLeftButton.GetHeight();
            scrollBarHorSliderWidth = sliderHor.GetWidth();            
        }
        
        sliderVert = MmgHelper.GetBasicCachedBmp("scroll_bar_slider_sm.png");        
        if(sliderTopButton != null && sliderBottomButton != null && sliderVert != null) {
            scrollBarSliderButtonHeight = sliderTopButton.GetHeight();
            scrollBarVertWidth = sliderTopButton.GetWidth();
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
        sliderTopButtonRect = new MmgRect(left, top, bottom, right);        
        
        //button bottom
        left = viewPort.GetWidth();
        top = viewPort.GetHeight() - scrollBarSliderButtonHeight;
        bottom = viewPort.GetHeight();
        right = viewPort.GetWidth() + scrollBarVertWidth;
        sliderBottomButtonRect = new MmgRect(left, top, bottom, right);           
        
        if(sliderLeftButton != null && sliderRightButton != null && sliderHor != null) {
            sliderLeftButton.SetPosition(sliderLeftButtonRect.GetPosition());
            sliderRightButton.SetPosition(sliderRightButtonRect.GetPosition());            
            sliderHor.SetPosition(sliderHorRect.GetPosition());
        }
        
        if(sliderTopButton != null && sliderBottomButton != null && sliderVert != null) {
            sliderTopButton.SetPosition(sliderTopButtonRect.GetPosition());
            sliderBottomButton.SetPosition(sliderBottomButtonRect.GetPosition());            
            sliderVert.SetPosition(sliderVertRect.GetPosition());
        }        
        
        PrepScrollPane();        
        isDirty = true;
    }
    
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
        
        if(scrollPaneHeight - viewPortHeight > 0) {
            heightDiff = scrollPaneHeight - viewPortHeight;
            heightDiffPrct = heightDiff / viewPortHeight;
            scrollBarVertVisible = true;            
        } else {
            heightDiff = 0;
            heightDiffPrct = 0.0;
            scrollBarVertVisible = false;
        }
        MmgDebug.wr("scrollBarVertVisible: " + scrollBarVertVisible);
        
        p = new MmgPen((Graphics2D)viewPort.GetImage().getGraphics());
        p.SetAdvRenderHints();        
    }    

    public GenericEventHandler GetHandler() {
        return handler;
    }

    public void SetHandler(GenericEventHandler Handler) {
        handler = Handler;
    }

    public GameStates SetGameState() {
        return gameState;
    }

    public void SetGameState(GameStates GameState) {
        gameState = GameState;
    }
    
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
        sliderTopButtonRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderTopButtonRect.GetTop()));
        sliderBottomButtonRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderBottomButtonRect.GetTop()));        
        
        if(sliderTopButton != null && sliderBottomButton != null && sliderVert != null) {
            sliderTopButton.SetPosition(sliderTopButtonRect.GetPosition());
            sliderBottomButton.SetPosition(sliderBottomButtonRect.GetPosition());            
            sliderVert.SetPosition(sliderVertRect.GetPosition());
        }
    }
    
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
        sliderTopButtonRect.SetPosition(new MmgVector2(sliderTopButtonRect.GetLeft(), inY));
        sliderBottomButtonRect.SetPosition(new MmgVector2(sliderBottomButtonRect.GetLeft(), inY + viewPortRect.GetHeight() - scrollBarSliderButtonHeight));
        
        if(sliderTopButton != null && sliderBottomButton != null && sliderVert != null) {
            sliderTopButton.SetPosition(sliderTopButtonRect.GetPosition());
            sliderBottomButton.SetPosition(sliderBottomButtonRect.GetPosition()); 
            sliderVert.SetPosition(sliderVertRect.GetPosition());
        }        
    }
    
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
        sliderTopButtonRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY()));
        sliderBottomButtonRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY() + viewPortRect.GetHeight() - scrollBarSliderButtonHeight));
        
        if(sliderTopButton != null && sliderBottomButton != null && sliderVert != null) {
            sliderTopButton.SetPosition(sliderTopButtonRect.GetPosition());
            sliderBottomButton.SetPosition(sliderBottomButtonRect.GetPosition());            
            sliderVert.SetPosition(sliderVertRect.GetPosition());
        }         
    }
         
    public boolean ProcessDpadRelease(int dir) {
        if(scrollBarHorVisible && dir == GameSettings.LEFT_KEYBOARD) {
            MmgDebug.wr("Both: ProcessDpadRelease.sliderLeftButtonRect click");
            if(offsetXScrollBarSlider - intervalX > viewPort.GetX() + scrollBarSliderButtonWidth) {
                offsetXScrollBarSlider -= intervalX;
                offsetXScrollPane -= (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = 0;
                offsetXScrollPane = 0; 
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHorVert.SCROLL_BOTH_SCROLL_LEFT_EVENT_ID, new Integer(offsetXScrollPane), gameState));
            }                                    
            
            isDirty = true;
            return true;
            
        } else if(scrollBarHorVisible && dir == GameSettings.RIGHT_KEYBOARD) {
            MmgDebug.wr("Both: ProcessDpadRelease.sliderRightButtonRect click");
            if(scrollBarSliderButtonWidth + offsetXScrollBarSlider + intervalX < viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarHorSliderWidth) {
                offsetXScrollBarSlider += intervalX;
                offsetXScrollPane += (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = (viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarHorSliderWidth);
                offsetXScrollPane = widthDiff;  
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHorVert.SCROLL_BOTH_SCROLL_RIGHT_EVENT_ID, new Integer(offsetXScrollPane), gameState));
            }                        
            
            isDirty = true;            
            return true;
            
        } else if(scrollBarVertVisible && dir == GameSettings.UP_KEYBOARD) {
            MmgDebug.wr("Both: ProcessDpadRelease.sliderTopButtonRect click");
            if(offsetYScrollBarSlider - intervalY > viewPort.GetY() + scrollBarSliderButtonHeight) {
                offsetYScrollBarSlider -= intervalY;
                offsetYScrollPane -= (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = 0;
                offsetYScrollPane = 0; 
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHorVert.SCROLL_BOTH_SCROLL_UP_EVENT_ID, new Integer(offsetYScrollPane), gameState));
            }
            
            isDirty = true;
            return true;
            
        } else if(scrollBarVertVisible && dir == GameSettings.DOWN_KEYBOARD) {
            MmgDebug.wr("Both: ProcessDpadRelease.sliderBottomButtonRect click");
            if(scrollBarSliderButtonHeight + offsetYScrollBarSlider + intervalY < viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarVertSliderHeight) {
                offsetYScrollBarSlider += intervalY;
                offsetYScrollPane += (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = (viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarVertSliderHeight);
                offsetYScrollPane = heightDiff;  
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHorVert.SCROLL_BOTH_SCROLL_DOWN_EVENT_ID, new Integer(offsetYScrollPane), gameState));
            }            
            
            isDirty = true;            
            return true;
            
        }
        
        return false;
    }
    
    public boolean ProcessScreenClick(int x, int y) {
        boolean ret = false;
        
        if(MmgHelper.RectCollision(x, y, viewPortRect)) {
            MmgDebug.wr("Both: viewPort click: X: " + x + " Y: " + y + " GetX: " + GetX() + " GetY: " + GetY());
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHorVert.SCROLL_BOTH_CLICK_EVENT_ID, new MmgVector2(x + offsetXScrollPane - GetX(), y + offsetYScrollPane - GetY()), gameState));
            }
            ret = true;
            
        //}else if(MmgHelper.RectCollision(x, y, 3, 3, scrollPaneRect)) {
            //MmgDebug.wr("scrollPane click");
            //ret = true;
                        
        }else if(scrollBarHorVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderLeftButtonRect)) {
            MmgDebug.wr("Both: ProcessScreenClick.sliderLeftButtonRect click");
            if(offsetXScrollBarSlider - intervalX > viewPort.GetX() + scrollBarSliderButtonWidth) {
                MmgDebug.wr("AAA");
                offsetXScrollBarSlider -= intervalX;
                offsetXScrollPane -= (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = 0;
                offsetXScrollPane = 0; 
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHorVert.SCROLL_BOTH_SCROLL_LEFT_EVENT_ID, new Integer(offsetXScrollPane), gameState));
            }            
            
            isDirty = true;
            ret = true;
            
        }else if(scrollBarHorVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderRightButtonRect)) {            
            MmgDebug.wr("Both: ProcessScreenClick.sliderRightButtonRect click");
            if(scrollBarSliderButtonWidth + offsetXScrollBarSlider + intervalX < viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarHorSliderWidth) {
                MmgDebug.wr("AAA");
                offsetXScrollBarSlider += intervalX;
                offsetXScrollPane += (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = (viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarHorSliderWidth);
                offsetXScrollPane = widthDiff;  
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHorVert.SCROLL_BOTH_SCROLL_RIGHT_EVENT_ID, new Integer(offsetXScrollPane), gameState));
            }                        
            
            isDirty = true;            
            ret = true;
            
        }else if(scrollBarVertVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderTopButtonRect)) {
            MmgDebug.wr("Both: ProcessScreenClick.sliderTopButtonRect click");
            if(offsetYScrollBarSlider - intervalY > viewPort.GetY() + scrollBarSliderButtonHeight) {
                offsetYScrollBarSlider -= intervalY;
                offsetYScrollPane -= (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = 0;
                offsetYScrollPane = 0; 
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHorVert.SCROLL_BOTH_SCROLL_UP_EVENT_ID, new Integer(offsetYScrollPane), gameState));
            }                        
            
            isDirty = true;
            ret = true;
            
        }else if(scrollBarVertVisible && MmgHelper.RectCollision(x - 3, y - 3, 6, 6, sliderBottomButtonRect)) {            
            MmgDebug.wr("Both: ProcessScreenClick.sliderBottomButtonRect click");
            if(scrollBarSliderButtonHeight + offsetYScrollBarSlider + intervalY < viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarVertSliderHeight) {
                offsetYScrollBarSlider += intervalY;
                offsetYScrollPane += (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = (viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarVertSliderHeight);
                offsetYScrollPane = heightDiff;  
            }
            
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHorVert.SCROLL_BOTH_SCROLL_DOWN_EVENT_ID, new Integer(offsetYScrollPane), gameState));
            }                                    
            
            isDirty = true;
            ret = true;
            
        //}else if(MmgHelper.RectCollision(x, y, sliderRect)) {
            //MmgDebug.wr("sliderRect click");            
            //ret = true;
            
        }
        
        return ret;
    }
    
    public MmgBmp GetViewPort() {
        return viewPort;
    }

    public void SetViewPort(MmgBmp ViewPort) {
        viewPort = ViewPort;
        PrepDimensions();
    }

    public MmgBmp GetScrollPane() {
        return scrollPane;
    }

    public void SetScrollPane(MmgBmp ScrollPane) {
        scrollPane = ScrollPane;
        PrepDimensions();        
    }

    public MmgRect GetViewPortRect() {
        return viewPortRect;
    }

    public void SetViewPortRect(MmgRect r) {
        viewPortRect = r;
    }
    
    public MmgRect GetScrollPaneRect() {
        return scrollPaneRect;
    }

    public void SetScrollPaneRect(MmgRect r) {
        scrollPaneRect = r;
    }
    
    public MmgBmp GetSliderHor() {
        return sliderHor;
    }
    
    public void SetSliderHor(MmgBmp b) {
        sliderHor = b;
    }
    
    public MmgBmp GetSliderVert() {
        return sliderVert;
    }
    
    public void SetSliderVert(MmgBmp b) {
        sliderVert = b;
    }
    
    public MmgRect GetSliderHorRect() {
        return sliderHorRect;
    }
    
    public void SetSliderHorRect(MmgRect r) {
        sliderHorRect = r;
    }
    
    public MmgRect GetSliderVertRect() {
        return sliderVertRect;
    }
    
    public void SetSliderVertRect(MmgRect r) {
        sliderVertRect = r;
    }    
    
    public MmgRect GetSliderLeftButtonRect() {
        return sliderLeftButtonRect;
    }
    
    public void SetSliderLeftButtonRect(MmgRect r) {
        sliderLeftButtonRect = r;
    }    
    
    public MmgRect GetSliderRightButtonRect() {
        return sliderRightButtonRect;
    }
    
    public void SetSliderRightButtonRect(MmgRect r) {
        sliderRightButtonRect = r;
    }
    
    public MmgRect GetSliderTopButtonRect() {
        return sliderTopButtonRect;
    }
    
    public void GetSliderTopButtonRect(MmgRect r) {
        sliderTopButtonRect = r;
    }    
    
    public MmgRect GetSliderBottomButtonRect() {
        return sliderBottomButtonRect;
    }
    
    public void GetSliderBottomButtonRect(MmgRect r) {
        sliderBottomButtonRect = r;
    }    
    
    public int GetWidthDiff() {
        return widthDiff;
    }

    public void SetWidthDiff(int WidthDiff) {
        widthDiff = WidthDiff;
    }

    public double GetWidthDiffPrct() {
        return widthDiffPrct;
    }

    public void SetWidthDiffPrct(double d) {
        widthDiffPrct = d;
    }    
    
    public int GetHeightDiff() {
        return heightDiff;
    }
    
    public void SetHeightDiff(int HeightDiff) {
        heightDiff = HeightDiff;
    }

    public double GetHeightDiffPrct() {
        return heightDiffPrct;
    }    
    
    public void GetHeightDiffPrct(double d) {
        heightDiffPrct = d;
    }     
    
    public boolean IsScrollBarHorVisible() {
        return scrollBarHorVisible;
    }

    public void SetScrollBarHorVisible(boolean ScrollBarHorVisible) {
        scrollBarHorVisible = ScrollBarHorVisible;
    }

    public boolean IsScrollBarVertVisible() {
        return scrollBarVertVisible;
    }

    public void SetScrollBarVertVisible(boolean ScrollBarVertVisible) {
        scrollBarVertVisible = ScrollBarVertVisible;
    }    
    
    public MmgColor GetScrollBarColor() {
        return scrollBarColor;
    }

    public void SetScrollBarColor(MmgColor ScrollBarColor) {
        scrollBarColor = ScrollBarColor;
    }

    public MmgColor GetScrollBarSliderColor() {
        return scrollBarSliderColor;
    }

    public void SetScrollBarSliderColor(MmgColor ScrollBarSliderColor) {
        scrollBarSliderColor = ScrollBarSliderColor;
    }

    public int GetScrollBarHorHeight() {
        return scrollBarHorHeight;
    }

    public void SetScrollBarHorHeight(int ScrollBarHeight) {
        scrollBarHorHeight = ScrollBarHeight;
    }

    public int GetScrollBarVertWidth() {
        return scrollBarVertWidth;
    }

    public void SetScrollBarVertWidth(int ScrollBarWidth) {
        scrollBarVertWidth = ScrollBarWidth;
    }    
    
    public int GetScrollBarSliderButtonWidth() {
        return scrollBarSliderButtonWidth;
    }

    public void SetScrollBarSliderButtonWidth(int ScrollBarSliderButtonWidth) {
        scrollBarSliderButtonWidth = ScrollBarSliderButtonWidth;
    }
    
    public int GetScrollBarSliderButtonHeight() {
        return scrollBarSliderButtonHeight;
    }

    public void SetScrollBarSliderButtonHeight(int ScrollBarSliderButtonHeight) {
        scrollBarSliderButtonHeight = ScrollBarSliderButtonHeight;
        PrepDimensions();        
    }    
    
    public int GetScrollBarHorSliderWidth() {
        return scrollBarHorSliderWidth;
    }

    public void SetScrollBarHorSliderWidth(int ScrollBarSliderWidth) {
        scrollBarHorSliderWidth = ScrollBarSliderWidth;
    }

    public int GetScrollBarVertSliderHeight() {
        return scrollBarVertSliderHeight;
    }

    public void SetScrollBarVertSliderHeight(int ScrollBarSliderHeight) {
        scrollBarVertSliderHeight = ScrollBarSliderHeight;
    }    
    
    public int GetIntervalX() {
        return intervalX;
    }

    public int GetIntervalY() {
        return intervalY;
    }    
    
    public void SetIntervalX(int IntervalX) {
        if(IntervalX != 0) {
            intervalX = IntervalX;
            intervalPrctX = (double)intervalX / (viewPort.GetWidth() - (scrollBarSliderButtonWidth * 2) - scrollBarHorSliderWidth);
        }
    }

    public void SetIntervalY(int IntervalY) {
        if(IntervalY != 0) {
            intervalY = IntervalY;
            intervalPrctY = (double)intervalY / (viewPort.GetHeight() - (scrollBarSliderButtonHeight * 2) - scrollBarVertSliderHeight);
        }
    }    
    
    public boolean IsIsDirty() {
        return isDirty;
    }

    public void SetIsDirty(boolean IsDirty) {
        isDirty = IsDirty;
    }

    public int GetOffsetX() {
        return offsetXScrollBarSlider;
    }

    public void SetOffsetX(int OffsetX) {
        offsetXScrollBarSlider = OffsetX;
    }
       
    public int GetOffsetY() {
        return offsetYScrollBarSlider;
    }

    public void SetOffsetY(int OffsetY) {
        offsetYScrollBarSlider = OffsetY;
    }    
    
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        if(GetIsVisible() == true) {
            if(isDirty) {
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
            }
            
            isDirty = false;
            
        }else {
            //do nothing
        }
        
        return false;
    }    
    
    /**
     * Draws this object using the given pen, MmgPen.
     *
     * @param p The pen to use to draw this object, MmgPen.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (GetIsVisible() == true) {
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
                    p.DrawRect(sliderBottomButtonRect);

                    //slider button top
                    p.SetGraphicsColor(Color.PINK);
                    p.DrawRect(sliderTopButtonRect);
                }                
                
                p.GetGraphics().setColor(c);
            }
            
            /*
            Graphics g = p.GetGraphics();
            Color ct = g.getColor();
            g.setColor(Color.WHITE);
            g.fillRect(GetX(), GetY(), w, h - scrollBarHorHeight);
                       
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
            
           if(scrollBarVertVisible) {            
                if(sliderTopButton != null) {
                    p.DrawBmp(sliderTopButton);
                }
                
                if(sliderBottomButton != null) {
                    p.DrawBmp(sliderBottomButton);
                }
                
                if(sliderVert != null) {
                    p.DrawBmp(sliderVert);
                }                       
            }

          p.DrawBmp(viewPort, GetPosition());
            
        } else {
            //do nothing

        }
    }    
}

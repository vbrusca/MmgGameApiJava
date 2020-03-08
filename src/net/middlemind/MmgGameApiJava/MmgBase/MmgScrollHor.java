package net.middlemind.MmgGameApiJava.MmgBase;

import com.middlemind.Odroid.GamePanel.GameStates;
import com.middlemind.Odroid.GameSettings;
import com.middlemind.Odroid.GenericEventHandler;
import com.middlemind.Odroid.GenericEventMessage;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/16/2020
 */
public class MmgScrollHor extends MmgObj {
    private MmgBmp viewPort = null;
    private MmgRect viewPortRect = null;  
    private int viewPortWidth = 0;
    
    private MmgBmp scrollPane = null;
    private MmgRect scrollPaneRect = null;
    private int scrollPaneWidth = 0;
    
    private MmgBmp slider = null;
    private MmgRect sliderRect = null;
    
    private MmgBmp sliderLeftButton = null;
    private MmgRect sliderLeftButtonRect = null;
    
    private MmgBmp sliderRightButton = null;
    private MmgRect sliderRightButtonRect = null;
    
    private int widthDiff = 0;
    private double widthDiffPrct = 0.0;
    
    private boolean scrollBarHorVisible = false;
    private MmgColor scrollBarColor = MmgColor.GetWhite();
    private MmgColor scrollBarSliderColor = MmgColor.GetRed();    
    
    private int scrollBarHeight = MmgHelper.ScaleValue(10);
    private int scrollBarSliderWidth = MmgHelper.ScaleValue(30);
    private int scrollBarSliderButtonWidth = MmgHelper.ScaleValue(15);    
    private int intervalX = 5;
    private double intervalPrctX = 5.0;
    private boolean isDirty = false;
    private int offsetXScrollBarSlider = 0;
    private int offsetXScrollPane = 0;
    
    private GenericEventHandler handler = null;
    private GameStates gameState = GameStates.BLANK;
    public static boolean SHOW_CONTROL_BOUNDING_BOX = false;    
    public static int SCROLL_HOR_CLICK_EVENT_ID = 0;
    
    public MmgScrollHor(MmgBmp ViewPort, MmgBmp ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int ScrollBarHeight, int ScrollBarSliderWidth, int Interval, GameStates GameState) {
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarHeight = ScrollBarHeight;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        scrollBarSliderWidth = ScrollBarSliderWidth;        
        gameState = GameState;       
        PrepDimensions();

        if(Interval != 0) {
            SetIntervalX(Interval);
        }        
    }

    public MmgScrollHor(MmgBmp ViewPort, MmgBmp ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int Interval, GameStates GameState) {
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;        
        gameState = GameState;
        PrepDimensions();
        
        if(Interval != 0) {
            SetIntervalX(Interval);
        }
    }    
    
    public void PrepDimensions() {
        int left = 0;
        int top = 0;
        int bottom = viewPort.GetHeight();
        int right = viewPort.GetWidth();
        viewPortRect = new MmgRect(left, top, bottom, right);
        viewPortWidth = viewPortRect.GetWidth();
        
        sliderLeftButton = MmgHelper.GetBasicCachedBmp("scroll_bar_left_sm.png");
        sliderRightButton = MmgHelper.GetBasicCachedBmp("scroll_bar_right_sm.png");
        slider = MmgHelper.GetBasicCachedBmp("scroll_bar_slider_sm.png");        
        if(sliderLeftButton != null && sliderRightButton != null && slider != null) {
            scrollBarSliderButtonWidth = sliderLeftButton.GetWidth();
            scrollBarHeight = sliderLeftButton.GetHeight();
            scrollBarSliderWidth = slider.GetWidth();
        }
        
        left = 0;
        top = 0;
        bottom = scrollPane.GetHeight();
        right = scrollPane.GetWidth();
        scrollPaneRect = new MmgRect(left, top, bottom, right);
        scrollPaneWidth = scrollPaneRect.GetWidth();
        
        left = scrollBarSliderButtonWidth;
        top = viewPort.GetHeight();
        bottom = viewPort.GetHeight() + scrollBarHeight;
        right = scrollBarSliderButtonWidth + scrollBarSliderWidth;
        sliderRect = new MmgRect(left, top, bottom, right);
        
        left = 0;
        top = viewPort.GetHeight();
        bottom = viewPort.GetHeight() + scrollBarHeight;
        right = scrollBarSliderButtonWidth;
        sliderLeftButtonRect = new MmgRect(left, top, bottom, right);        
                
        left = viewPort.GetWidth() - scrollBarSliderButtonWidth;
        top = viewPort.GetHeight();
        bottom = viewPort.GetHeight() + scrollBarHeight;        
        right = viewPort.GetWidth();
        sliderRightButtonRect = new MmgRect(left, top, bottom, right);   
          
        if(sliderLeftButton != null && sliderRightButton != null && slider != null) {
            sliderLeftButton.SetPosition(sliderLeftButtonRect.GetPosition());
            sliderRightButton.SetPosition(sliderRightButtonRect.GetPosition());            
            slider.SetPosition(sliderRect.GetPosition());
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
        sliderRect.SetPosition(new MmgVector2(inX + scrollBarSliderButtonWidth + offsetXScrollBarSlider, sliderRect.GetTop()));
        sliderLeftButtonRect.SetPosition(new MmgVector2(inX, sliderLeftButtonRect.GetTop()));
        sliderRightButtonRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth() - scrollBarSliderButtonWidth, sliderRightButtonRect.GetTop()));        

        if(sliderLeftButton != null && sliderRightButton != null && slider != null) {
            sliderLeftButton.SetPosition(sliderLeftButtonRect.GetPosition());
            sliderRightButton.SetPosition(sliderRightButtonRect.GetPosition());            
            slider.SetPosition(sliderRect.GetPosition());
        }        
    }
    
    @Override
    public void SetY(int inY) {
        super.SetX(inY);
        viewPortRect.SetPosition(new MmgVector2(viewPortRect.GetLeft(), inY));
        scrollPaneRect.SetPosition(new MmgVector2(scrollPaneRect.GetLeft(), inY));
        sliderRect.SetPosition(new MmgVector2(sliderRect.GetLeft(), inY + viewPort.GetHeight()));
        sliderLeftButtonRect.SetPosition(new MmgVector2(sliderLeftButtonRect.GetLeft(), inY + viewPortRect.GetHeight()));
        sliderRightButtonRect.SetPosition(new MmgVector2(sliderRightButtonRect.GetLeft(), inY + viewPortRect.GetHeight()));

        if(sliderLeftButton != null && sliderRightButton != null && slider != null) {
            sliderLeftButton.SetPosition(sliderLeftButtonRect.GetPosition());
            sliderRightButton.SetPosition(sliderRightButtonRect.GetPosition());            
            slider.SetPosition(sliderRect.GetPosition());
        }
    }
    
    @Override
    public void SetPosition(MmgVector2 inPos) {
        super.SetPosition(inPos);
        viewPortRect.SetPosition(inPos);
        scrollPaneRect.SetPosition(inPos);
        sliderRect.SetPosition(new MmgVector2(inPos.GetX() + scrollBarSliderButtonWidth + offsetXScrollBarSlider, inPos.GetY() + viewPort.GetHeight()));
        sliderLeftButtonRect.SetPosition(new MmgVector2(inPos.GetX(), inPos.GetY() + viewPort.GetHeight()));
        sliderRightButtonRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth() - scrollBarSliderButtonWidth, inPos.GetY() + viewPortRect.GetHeight()));        

        if(sliderLeftButton != null && sliderRightButton != null && slider != null) {
            sliderLeftButton.SetPosition(sliderLeftButtonRect.GetPosition());
            sliderRightButton.SetPosition(sliderRightButtonRect.GetPosition());            
            slider.SetPosition(sliderRect.GetPosition());
        }
    }
         
    public boolean ProcessDpadRelease(int dir) {
        if(scrollBarHorVisible && dir == GameSettings.LEFT) {
            MmgDebug.wr("ProcessDpadRelease.sliderLeftButtonRect click");
            if(offsetXScrollBarSlider - intervalX > viewPort.GetX() + scrollBarSliderButtonWidth) {
                offsetXScrollBarSlider -= intervalX;
                offsetXScrollPane -= (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = 0;
                offsetXScrollPane = 0; 
            }
            
            isDirty = true;
            return true;
            
        } else if(scrollBarHorVisible && dir == GameSettings.RIGHT) {
            MmgDebug.wr("ProcessDpadRelease.sliderRightButtonRect click");
            if(scrollBarSliderButtonWidth + offsetXScrollBarSlider + intervalX < viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarSliderWidth) {
                offsetXScrollBarSlider += intervalX;
                offsetXScrollPane += (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = (viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarSliderButtonWidth - scrollBarSliderWidth);
                offsetXScrollPane = widthDiff;  
            }
            
            isDirty = true;            
            return true;
            
        }
        
        return false;
    }
    
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
                        
        }else if(scrollBarHorVisible && MmgHelper.RectCollision(x, y, sliderLeftButtonRect)) {
            MmgDebug.wr("ProcessScreenClick.sliderLeftButtonRect click");
            if(offsetXScrollBarSlider - intervalX > viewPort.GetX() + scrollBarSliderButtonWidth) {
                offsetXScrollBarSlider -= intervalX;
                offsetXScrollPane -= (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = 0;
                offsetXScrollPane = 0; 
            }
            
            isDirty = true;
            ret = true;
            
        }else if(scrollBarHorVisible && MmgHelper.RectCollision(x, y, sliderRightButtonRect)) {            
            MmgDebug.wr("ProcessScreenClick.sliderRightButtonRect click");
            if(scrollBarSliderButtonWidth + offsetXScrollBarSlider + intervalX < viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarSliderWidth) {
                offsetXScrollBarSlider += intervalX;
                offsetXScrollPane += (int)(widthDiff * intervalPrctX);
            } else {
                offsetXScrollBarSlider = (viewPort.GetWidth() - scrollBarSliderButtonWidth - scrollBarSliderButtonWidth - scrollBarSliderWidth);
                offsetXScrollPane = widthDiff;  
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

    public MmgRect GetScrollPaneRect() {
        return scrollPaneRect;
    }

    public MmgRect GetSliderRect() {
        return sliderRect;
    }
    
    public MmgRect GetSliderLeftButtonRect() {
        return sliderLeftButtonRect;
    }
    
    public MmgRect GetSliderRightButtonRect() {
        return sliderRightButtonRect;
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

    public boolean IsScrollBarVertVisible() {
        return scrollBarHorVisible;
    }

    public void SetScrollBarVertVisible(boolean ScrollBarVertVisible) {
        this.scrollBarHorVisible = ScrollBarVertVisible;
    }

    public MmgColor GetScrollBarColor() {
        return scrollBarColor;
    }

    public void GetScrollBarColor(MmgColor ScrollBarColor) {
        scrollBarColor = ScrollBarColor;
    }

    public MmgColor GetScrollBarSliderColor() {
        return scrollBarSliderColor;
    }

    public void SetScrollBarSliderColor(MmgColor ScrollBarSliderColor) {
        scrollBarSliderColor = ScrollBarSliderColor;
    }

    public int GetScrollBarHeight() {
        return scrollBarHeight;
    }

    public void SetScrollBarHeight(int ScrollBarHeight) {
        scrollBarHeight = ScrollBarHeight;
        PrepDimensions();        
    }

    public int GetScrollBarSliderButtonWidth() {
        return scrollBarSliderButtonWidth;
    }

    public void SetScrollBarSliderButtonWidth(int ScrollBarSliderButtonWidth) {
        scrollBarSliderButtonWidth = ScrollBarSliderButtonWidth;
        PrepDimensions();        
    }
    
    public int GetScrollBarSliderWidth() {
        return scrollBarSliderWidth;
    }

    public void SetScrollBarSliderWidth(int ScrollBarSliderWidth) {
        scrollBarSliderWidth = ScrollBarSliderWidth;
        PrepDimensions();
    }

    public int GetIntervalX() {
        return intervalX;
    }

    public void SetIntervalX(int IntervalX) {
        if(IntervalX != 0) {
            intervalX = IntervalX;
            intervalPrctX = (double)intervalX / (viewPort.GetWidth() - (scrollBarSliderButtonWidth * 2) - scrollBarSliderWidth);
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
        
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        if(GetIsVisible() == true) {
            if(isDirty) {
                sliderRect.SetPosition(new MmgVector2(GetX() + scrollBarSliderButtonWidth + offsetXScrollBarSlider, sliderRect.GetTop()));
                scrollPaneRect.SetPosition(new MmgVector2(GetX() - offsetXScrollPane, scrollPaneRect.GetTop()));
            
                if(slider != null) {
                    slider.SetPosition(sliderRect.GetPosition());                    
                }
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
            if (MmgScrollHor.SHOW_CONTROL_BOUNDING_BOX == true) {
                Color c = p.GetGraphics().getColor();

                //draw obj rect
                p.GetGraphics().setColor(Color.RED);
                p.DrawRect(this);
                p.DrawRect(GetX(), GetY() + GetHeight() - scrollBarHeight, w, scrollBarHeight);

                //draw view port rect
                p.GetGraphics().setColor(Color.BLUE);
                p.DrawRect(viewPortRect);
                
                //draw scroll pane rect
                p.GetGraphics().setColor(Color.GREEN);
                p.DrawRect(scrollPaneRect);
                
                if(scrollBarHorVisible) {
                    //slider
                    p.GetGraphics().setColor(Color.ORANGE);
                    p.DrawRect(sliderRect);

                    //slider button bottom
                    p.GetGraphics().setColor(Color.CYAN);
                    p.DrawRect(sliderRightButtonRect);

                    //slider button top
                    p.GetGraphics().setColor(Color.PINK);
                    p.DrawRect(sliderLeftButtonRect);
                }
                
                p.GetGraphics().setColor(c);
            }

            Graphics g = p.GetGraphics();
            Color ct = g.getColor();
            g.setColor(Color.WHITE);
            g.fillRect(GetX(), GetY(), w, h - scrollBarHeight);
                       
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(GetX(), GetY(), 100, 100);            
            
            g.setColor(Color.cyan);
            g.fillRect(GetX(), GetY(), 50, 50);
            
            g.setColor(ct);
            
            if(scrollBarHorVisible) {            
                if(sliderLeftButton != null) {
                    p.DrawBmp(sliderLeftButton);
                }
                
                if(sliderRightButton != null) {
                    p.DrawBmp(sliderRightButton);
                }
                
                if(slider != null) {
                    p.DrawBmp(slider);
                }                       
            }
                
        } else {
            //do nothing

        }
    }    
}

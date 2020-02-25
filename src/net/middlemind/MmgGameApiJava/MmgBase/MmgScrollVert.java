package net.middlemind.MmgGameApiJava.MmgBase;

import com.middlemind.Odroid.GamePanel;
import com.middlemind.Odroid.GamePanel.GameStates;
import com.middlemind.Odroid.GameSettings;
import com.middlemind.Odroid.GenericEventHandler;
import com.middlemind.Odroid.GenericEventMessage;
import com.middlemind.Odroid.Helper;
import java.awt.Color;

/**
 *
 * @author Victor G. Brusca, Middlemind Games
 * 01/16/2020
 */
public class MmgScrollVert extends MmgObj {
    private MmgObj viewPort = null;
    private MmgRect viewPortRect = null;  
    private int viewPortHeight = 0;
    
    private MmgObj scrollPane = null;
    private MmgRect scrollPaneRect = null;
    private int scrollPaneHeight = 0;
    
    private MmgBmp slider = null;
    private MmgRect sliderRect = null;
    
    private MmgBmp sliderTopButton = null;
    private MmgRect sliderTopButtonRect = null;
    
    private MmgBmp sliderBottomButton = null;
    private MmgRect sliderBottomButtonRect = null;
    
    private int heightDiff = 0;
    private double heightDiffPrct = 0.0;
    
    private boolean scrollBarVertVisible = false;
    private MmgColor scrollBarColor = MmgColor.GetWhite();
    private MmgColor scrollBarSliderColor = MmgColor.GetRed();    
    
    private int scrollBarWidth = MmgHelper.ScaleValue(10);
    private int scrollBarSliderHeight = MmgHelper.ScaleValue(30);
    private int scrollBarSliderButtonHeight = MmgHelper.ScaleValue(15);    
    private int intervalY = 5;
    private double intervalPrctY = 5.0;
    private boolean isDirty = false;
    private int offsetYScrollBarSlider = 0;
    private int offsetYScrollPane = 0;
    
    private GenericEventHandler handler = null;
    private GamePanel.GameStates gameState = GamePanel.GameStates.BLANK;    
    public static boolean SHOW_CONTROL_BOUNDING_BOX = false;    
    public static int SCROLL_VERT_CLICK_EVENT_ID = 1;    
    
    public MmgScrollVert(MmgObj ViewPort, MmgObj ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int ScrollBarWidth, int ScrollBarSliderHeight, int Interval, GameStates GameState) {
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarWidth = ScrollBarWidth;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        scrollBarSliderHeight = ScrollBarSliderHeight;        
        gameState = GameState;          
        PrepDimensions();       

        if(Interval != 0) {
            SetIntervalY(Interval);
        }        
    }

    public MmgScrollVert(MmgObj ViewPort, MmgObj ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int Interval, GameStates GameState) {
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;        
        gameState = GameState;          
        PrepDimensions();
        
        if(Interval != 0) {
            SetIntervalY(Interval);
        }
    }    
    
    public void PrepDimensions() {
        int left = 0;
        int top = 0;
        int bottom = viewPort.GetHeight();
        int right = viewPort.GetWidth();
        viewPortRect = new MmgRect(left, top, bottom, right);
        viewPortHeight = viewPortRect.GetHeight();
        
        sliderBottomButton = Helper.GetBasicCachedBmp("scroll_bar_down.png");
        sliderTopButton = Helper.GetBasicCachedBmp("scroll_bar_up.png");
        slider = Helper.GetBasicCachedBmp("scroll_bar_slider.png");
        if(sliderTopButton != null && sliderBottomButton != null && slider != null) {
            scrollBarSliderButtonHeight = sliderTopButton.GetHeight();
            scrollBarWidth = sliderTopButton.GetWidth();
        }
        
        left = 0;
        top = 0;
        bottom = scrollPane.GetHeight();
        right = scrollPane.GetWidth();
        scrollPaneRect = new MmgRect(left, top, bottom, right);
        scrollPaneHeight = scrollPaneRect.GetHeight();
                
        left = viewPort.GetWidth();
        top = scrollBarSliderButtonHeight;
        bottom = scrollBarSliderButtonHeight + scrollBarSliderHeight;
        right = viewPort.GetWidth() + scrollBarWidth;
        sliderRect = new MmgRect(left, top, bottom, right);
        MmgDebug.wr(sliderRect.GetHeight() + " VertSlideRect: " + sliderRect.ToString());
        
        left = viewPort.GetWidth();
        top = 0;
        bottom = scrollBarSliderButtonHeight;
        right = viewPort.GetWidth() + scrollBarWidth;
        sliderTopButtonRect = new MmgRect(left, top, bottom, right);        
        
        left = viewPort.GetWidth();
        top = viewPort.GetHeight() - scrollBarSliderButtonHeight;
        bottom = viewPort.GetHeight();
        right = viewPort.GetWidth() + scrollBarWidth;
        sliderBottomButtonRect = new MmgRect(left, top, bottom, right);   
                
        PrepScrollPane();        
        isDirty = true;
    }
    
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
        MmgDebug.wr("scrollBarVertVisible: " + scrollBarVertVisible);
    }    
    
    public GenericEventHandler GetHandler() {
        return handler;
    }

    public void SetHandler(GenericEventHandler Handler) {
        handler = Handler;
    }

    public GamePanel.GameStates SetGameState() {
        return gameState;
    }

    public void SetGameState(GamePanel.GameStates GameState) {
        gameState = GameState;
    }    
    
    @Override
    public void SetX(int inX) {
        super.SetX(inX);
        viewPortRect.SetPosition(new MmgVector2(inX, viewPortRect.GetTop()));
        scrollPaneRect.SetPosition(new MmgVector2(inX, scrollPaneRect.GetTop()));
        sliderRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderRect.GetTop()));
        sliderTopButtonRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderTopButtonRect.GetTop()));
        sliderBottomButtonRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderBottomButtonRect.GetTop()));        
        MmgDebug.wr(sliderRect.GetHeight() + " AAA VertSlideRect: " + sliderRect.ToString());    
    }
    
    @Override
    public void SetY(int inY) {
        super.SetX(inY);
        viewPortRect.SetPosition(new MmgVector2(viewPortRect.GetLeft(), inY));
        scrollPaneRect.SetPosition(new MmgVector2(scrollPaneRect.GetLeft(), inY));
        sliderRect.SetPosition(new MmgVector2(sliderRect.GetLeft(), inY + scrollBarSliderButtonHeight + offsetYScrollBarSlider));
        sliderTopButtonRect.SetPosition(new MmgVector2(sliderTopButtonRect.GetLeft(), inY));
        sliderBottomButtonRect.SetPosition(new MmgVector2(sliderBottomButtonRect.GetLeft(), inY + viewPortRect.GetHeight() - scrollBarSliderButtonHeight));
        MmgDebug.wr(sliderRect.GetHeight() + " BBB VertSlideRect: " + sliderRect.ToString());
    }
    
    @Override
    public void SetPosition(MmgVector2 inPos) {
        super.SetPosition(inPos);
        viewPortRect.SetPosition(inPos);
        scrollPaneRect.SetPosition(inPos);
        sliderRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY() + scrollBarSliderButtonHeight + offsetYScrollBarSlider));
        sliderTopButtonRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY()));
        sliderBottomButtonRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY() + viewPortRect.GetHeight() - scrollBarSliderButtonHeight));        
        MmgDebug.wr(sliderRect.GetHeight() + " CCC VertSlideRect: " + sliderRect.ToString());
    }
         
    public boolean ProcessDpadRelease(int dir) {
        if(scrollBarVertVisible && dir == GameSettings.UP) {
            MmgDebug.wr("ProcessDpadRelease.sliderTopButtonRect click");
            if(offsetYScrollBarSlider - intervalY > viewPort.GetY() + scrollBarSliderButtonHeight) {
                offsetYScrollBarSlider -= intervalY;
                offsetYScrollPane -= (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = 0;
                offsetYScrollPane = 0; 
            }
            
            isDirty = true;
            return true;
            
        } else if(scrollBarVertVisible && dir == GameSettings.DOWN) {
            MmgDebug.wr("ProcessDpadRelease.sliderBottomButtonRect click");
            if(scrollBarSliderButtonHeight + offsetYScrollBarSlider + intervalY < viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarSliderHeight) {
                offsetYScrollBarSlider += intervalY;
                offsetYScrollPane += (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = (viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarSliderButtonHeight - scrollBarSliderHeight);
                offsetYScrollPane = heightDiff;  
            }
            
            isDirty = true;            
            return true;
            
        }
        
        return false;
    }
    
    public boolean ProcessScreenClick(int x, int y) {
        boolean ret = false;
        
        if(MmgHelper.RectCollision(x, y, 3, 3, viewPortRect)) {
            MmgDebug.wr("viewPort click");
            if(handler != null) {
                handler.HandleGenericEvent(new GenericEventMessage(MmgScrollHor.SCROLL_HOR_CLICK_EVENT_ID, new MmgVector2(x, y + offsetYScrollPane), gameState));
            }
            ret = true;
            
        //}else if(MmgHelper.RectCollision(x, y, 3, 3, scrollPaneRect)) {
            //MmgDebug.wr("scrollPane click");
            //return true;
                        
        }else if(scrollBarVertVisible && MmgHelper.RectCollision(x, y, 3, 3, sliderTopButtonRect)) {
            MmgDebug.wr("ProcessScreenClick.sliderTopButtonRect click");
            if(offsetYScrollBarSlider - intervalY > viewPort.GetY() + scrollBarSliderButtonHeight) {
                offsetYScrollBarSlider -= intervalY;
                offsetYScrollPane -= (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = 0;
                offsetYScrollPane = 0; 
            }
            
            isDirty = true;
            ret = true;
            
        }else if(scrollBarVertVisible && MmgHelper.RectCollision(x, y, 3, 3, sliderBottomButtonRect)) {            
            MmgDebug.wr("ProcessScreenClick.sliderBottomButtonRect click");
            if(scrollBarSliderButtonHeight + offsetYScrollBarSlider + intervalY < viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarSliderHeight) {
                offsetYScrollBarSlider += intervalY;
                offsetYScrollPane += (int)(heightDiff * intervalPrctY);
            } else {
                offsetYScrollBarSlider = (viewPort.GetHeight() - scrollBarSliderButtonHeight - scrollBarSliderButtonHeight - scrollBarSliderHeight);
                offsetYScrollPane = heightDiff;  
            }
            
            isDirty = true;
            ret = true;
            
        //}else if(MmgHelper.RectCollision(x, y, sliderRect)) {
            //MmgDebug.wr("sliderRect click");            
            //ret = true;
            
        }
        
        return ret;
    }
    
    public MmgObj GetViewPort() {
        return viewPort;
    }

    public void SetViewPort(MmgObj ViewPort) {
        viewPort = ViewPort;
        PrepDimensions();
    }

    public MmgObj GetScrollPane() {
        return scrollPane;
    }

    public void SetScrollPane(MmgObj ScrollPane) {
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
    
    public MmgRect GetSliderTopButtonRect() {
        return sliderTopButtonRect;
    }
    
    public MmgRect GetSliderBottomButtonRect() {
        return sliderBottomButtonRect;
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

    public boolean IsScrollBarVertVisible() {
        return scrollBarVertVisible;
    }

    public void SetScrollBarVertVisible(boolean ScrollBarVertVisible) {
        this.scrollBarVertVisible = ScrollBarVertVisible;
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

    public int GetScrollBarWidth() {
        return scrollBarWidth;
    }

    public void SetScrollBarWidth(int ScrollBarWidth) {
        scrollBarWidth = ScrollBarWidth;
        PrepDimensions();        
    }

    public int GetScrollBarSliderButtonHeight() {
        return scrollBarSliderButtonHeight;
    }

    public void SetScrollBarSliderButtonHeight(int ScrollBarSliderButtonHeight) {
        scrollBarSliderButtonHeight = ScrollBarSliderButtonHeight;
        PrepDimensions();        
    }
    
    public int GetScrollBarSliderHeight() {
        return scrollBarSliderHeight;
    }

    public void SetScrollBarSliderHeight(int ScrollBarSliderHeight) {
        scrollBarSliderHeight = ScrollBarSliderHeight;
        PrepDimensions();
    }

    public int GetIntervalY() {
        return intervalY;
    }

    public void SetIntervalY(int IntervalY) {
        if(IntervalY != 0) {
            intervalY = IntervalY;
            intervalPrctY = (double)intervalY / (viewPort.GetHeight() - (scrollBarSliderButtonHeight * 2) - scrollBarSliderHeight);
        }
    }

    public boolean IsIsDirty() {
        return isDirty;
    }

    public void SetIsDirty(boolean IsDirty) {
        isDirty = IsDirty;
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
                sliderRect.SetPosition(new MmgVector2(sliderRect.GetLeft(), GetY() + this.scrollBarSliderButtonHeight + offsetYScrollBarSlider));
                scrollPaneRect.SetPosition(new MmgVector2(scrollPaneRect.GetLeft(), GetY() - offsetYScrollPane));
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
            if (MmgScrollVert.SHOW_CONTROL_BOUNDING_BOX == true) {
                Color c = p.GetGraphics().getColor();

                //draw obj rect
                p.GetGraphics().setColor(Color.RED);
                p.DrawRect(this);
                p.DrawRect(GetX() + GetWidth() - scrollBarWidth, GetY(), scrollBarWidth, h);
                
                //draw view port rect
                p.GetGraphics().setColor(Color.BLUE);
                p.DrawRect(viewPortRect);
                
                //draw scroll pane rect
                p.GetGraphics().setColor(Color.GREEN);
                p.DrawRect(scrollPaneRect);
                
                if(scrollBarVertVisible) {
                    //slider
                    p.GetGraphics().setColor(Color.ORANGE);
                    p.DrawRect(sliderRect);

                    //slider button bottom
                    p.GetGraphics().setColor(Color.CYAN);
                    p.DrawRect(sliderBottomButtonRect);

                    //slider button top
                    p.GetGraphics().setColor(Color.PINK);
                    p.DrawRect(sliderTopButtonRect);
                }
                
                p.GetGraphics().setColor(c);
            }

        } else {
            //do nothing

        }
    }    
}

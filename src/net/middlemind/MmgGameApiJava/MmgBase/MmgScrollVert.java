package net.middlemind.MmgGameApiJava.MmgBase;

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
    
    private MmgObj slider = null;
    private MmgRect sliderRect = null;
    
    private MmgObj sliderTopButton = null;
    private MmgRect sliderTopButtonRect = null;
    
    private MmgObj sliderBottomButton = null;
    private MmgRect sliderBottomButtonRect = null;
    
    private int heightDiff = 0;
    private double heightDiffPrct = 0.0;
    
    private boolean scrollBarVertVisible = false;
    private MmgColor scrollBarColor = MmgColor.GetWhite();
    private MmgColor scrollBarSliderColor = MmgColor.GetRed();    
    
    private int scrollBarWidth = MmgHelper.ScaleValue(10);
    private int scrollBarSliderHeight = MmgHelper.ScaleValue(30);
    private int scrollBarSliderButtonHeight = MmgHelper.ScaleValue(5);    
    private int interval = 5;
    private boolean isDirty = false;
    private int offsetY = 0;
    
    public static boolean SHOW_CONTROL_BOUNDING_BOX = false;    
    
    public MmgScrollVert(MmgObj ViewPort, MmgObj ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int ScrollBarWidth, int ScrollBarSliderHeight, int Interval) {
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarWidth = ScrollBarWidth;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        scrollBarSliderHeight = ScrollBarSliderHeight;        
        
        PrepDimensions();
                
        interval = Interval;
    }

    public MmgScrollVert(MmgObj ViewPort, MmgObj ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int Interval) {
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;        
        
        PrepDimensions();
        
        /*
        int left = 0;
        int top = 0;
        int bottom = viewPort.GetHeight();
        int right = viewPort.GetWidth();
        viewPortRect = new MmgRect(left, top, bottom, right);
        viewPortHeight = viewPortRect.GetHeight();
        
        left = 0;
        top = 0;
        bottom = scrollPane.GetHeight();
        right = scrollPane.GetWidth();
        scrollPaneRect = new MmgRect(left, top, bottom, right);
        scrollPaneHeight = scrollPaneRect.GetHeight();
                
        left = viewPort.GetWidth();
        top = scrollBarSliderButtonHeight;
        bottom = scrollBarSliderHeight;
        right = viewPort.GetWidth() + scrollBarWidth;
        sliderRect = new MmgRect(left, top, bottom, right);
        
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
        */
        
        interval = Interval;        
    }    
    
    public void PrepDimensions() {
        int left = 0;
        int top = 0;
        int bottom = viewPort.GetHeight();
        int right = viewPort.GetWidth();
        viewPortRect = new MmgRect(left, top, bottom, right);
        viewPortHeight = viewPortRect.GetHeight();
        
        left = 0;
        top = 0;
        bottom = scrollPane.GetHeight();
        right = scrollPane.GetWidth();
        scrollPaneRect = new MmgRect(left, top, bottom, right);
        scrollPaneHeight = scrollPaneRect.GetHeight();
                
        left = viewPort.GetWidth();
        top = scrollBarSliderButtonHeight;
        bottom = scrollBarSliderHeight;
        right = viewPort.GetWidth() + scrollBarWidth;
        sliderRect = new MmgRect(left, top, bottom, right);
        
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
        
        isDirty = true;
    }
    
    @Override
    public void SetX(int inX) {
        super.SetX(inX);
        viewPortRect.SetPosition(new MmgVector2(inX, viewPortRect.GetTop()));
        scrollPaneRect.SetPosition(new MmgVector2(inX, scrollPaneRect.GetTop()));
        sliderRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderRect.GetTop()));
        sliderTopButtonRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderTopButtonRect.GetTop()));
        sliderBottomButtonRect.SetPosition(new MmgVector2(inX + viewPort.GetWidth(), sliderBottomButtonRect.GetTop()));        
    }
    
    @Override
    public void SetY(int inY) {
        super.SetX(inY);
        viewPortRect.SetPosition(new MmgVector2(viewPortRect.GetLeft(), inY));
        scrollPaneRect.SetPosition(new MmgVector2(scrollPaneRect.GetLeft(), inY));
        sliderRect.SetPosition(new MmgVector2(sliderRect.GetLeft(), inY + scrollBarSliderButtonHeight));
        sliderTopButtonRect.SetPosition(new MmgVector2(sliderTopButtonRect.GetLeft(), inY));
        sliderBottomButtonRect.SetPosition(new MmgVector2(sliderBottomButtonRect.GetLeft(), inY + viewPortRect.GetHeight() - scrollBarSliderButtonHeight));
    }
    
    @Override
    public void SetPosition(MmgVector2 inPos) {
        super.SetPosition(inPos);
        viewPortRect.SetPosition(inPos);
        scrollPaneRect.SetPosition(inPos);
        sliderRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY() + scrollBarSliderButtonHeight));
        sliderTopButtonRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY()));
        sliderBottomButtonRect.SetPosition(new MmgVector2(inPos.GetX() + viewPort.GetWidth(), inPos.GetY() + viewPortRect.GetHeight() - scrollBarSliderButtonHeight));        
    }
         
    public boolean ProcessScreenClick(int x, int y) {
        if(MmgHelper.RectCollision(x, y, viewPortRect)) {
            MmgDebug.wr("viewPort click");
            return true;
            
        }else if(MmgHelper.RectCollision(x, y, scrollPaneRect)) {
            MmgDebug.wr("scrollPane click");
            return true;
                        
        }else if(MmgHelper.RectCollision(x, y, sliderTopButtonRect)) {
            MmgDebug.wr("sliderTopButtonRect click");
            if(offsetY - interval >= viewPort.GetX() + scrollBarSliderButtonHeight) {
                offsetY -= interval;
                isDirty = true;
            }            
            return true;            
            
        }else if(MmgHelper.RectCollision(x, y, sliderBottomButtonRect)) {            
            MmgDebug.wr("sliderBottomButtonRect click");
            if(offsetY + interval <= viewPort.GetHeight() - scrollBarSliderButtonHeight) {
                offsetY += interval;
                isDirty = true;                
            }
            return true;
            
        }else if(MmgHelper.RectCollision(x, y, sliderRect)) {
            MmgDebug.wr("sliderRect click");            
            return true;
            
        }
        
        return false;
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

    //public void SetViewPortRect(MmgRect ViewPortRect) {
    //    viewPortRect = ViewPortRect;
    //}

    public MmgRect GetScrollPaneRect() {
        return scrollPaneRect;
    }

    //public void SetScrollPaneRect(MmgRect ScrollPaneRect) {
    //    scrollPaneRect = ScrollPaneRect;
    //}

    public int GetHeightDiff() {
        return heightDiff;
    }

    public void SetHeightDiff(int HeightDiff) {
        heightDiff = HeightDiff;
    }

    public double GetHeightDiffPrct() {
        return heightDiffPrct;
    }

    public void setHeightDiffPrct(double HeightDiffPrct) {
        heightDiffPrct = HeightDiffPrct;
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

    public int GetInterval() {
        return interval;
    }

    public void SetInterval(int Interval) {
        interval = Interval;
    }

    public boolean IsIsDirty() {
        return isDirty;
    }

    public void SetIsDirty(boolean IsDirty) {
        isDirty = IsDirty;
    }

    public int GetOffsetY() {
        return offsetY;
    }

    public void SetOffsetY(int OffsetY) {
        offsetY = OffsetY;
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
    }
    
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        if(GetIsVisible() == true) {
            if(isDirty) {
                sliderRect.SetPosition(new MmgVector2(sliderRect.GetLeft(), GetY() + scrollBarSliderButtonHeight + offsetY));
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
                p.DrawRect(GetX() + GetWidth() - this.scrollBarWidth, GetY(), this.scrollBarWidth, h);
                
                //draw view port rect
                p.GetGraphics().setColor(Color.BLUE);
                p.DrawRect(viewPortRect);
                
                //draw scroll pane rect
                p.GetGraphics().setColor(Color.GREEN);
                p.DrawRect(scrollPaneRect);
                
                //slider
                p.GetGraphics().setColor(Color.ORANGE);
                p.DrawRect(sliderRect);
                
                //slider button bottom
                p.GetGraphics().setColor(Color.CYAN);
                p.DrawRect(sliderBottomButtonRect);
                
                //slider button top
                p.GetGraphics().setColor(Color.PINK);
                p.DrawRect(sliderTopButtonRect);
                
                p.GetGraphics().setColor(c);
            }

        } else {
            //do nothing

        }
    }    
}

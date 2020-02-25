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
        
        scrollBarWidth = ScrollBarWidth;
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        scrollBarSliderHeight = ScrollBarSliderHeight;
        interval = Interval;
    }

    public MmgScrollVert(MmgObj ViewPort, MmgObj ScrollPane, MmgColor ScrollBarColor, MmgColor ScrollBarSliderColor, int Interval) {
        viewPort = ViewPort;
        scrollPane = ScrollPane;
        
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
        
        scrollBarColor = ScrollBarColor;
        scrollBarSliderColor = ScrollBarSliderColor;
        interval = Interval;        
    }    
    
    @Override
    public void SetX(int inX) {
        super.SetX(inX);
        viewPortRect.SetPosition(new MmgVector2(inX, viewPortRect.GetTop()));
        scrollPaneRect.SetPosition(new MmgVector2(inX, viewPortRect.GetTop()));
    }
    
    @Override
    public void SetY(int inY) {
        super.SetX(inY);
        viewPortRect.SetPosition(new MmgVector2(viewPortRect.GetLeft(), inY));
        scrollPaneRect.SetPosition(new MmgVector2(viewPortRect.GetLeft(), inY));
    }    
    
    @Override
    public void SetPosition(MmgVector2 inPos) {
        super.SetPosition(inPos);
        viewPortRect.SetPosition(inPos);
        scrollPaneRect.SetPosition(inPos);
    }
            
    public MmgObj GetViewPort() {
        return viewPort;
    }

    public void SetViewPort(MmgObj ViewPort) {
        viewPort = ViewPort;
    }

    public MmgObj GetScrollPane() {
        return scrollPane;
    }

    public void SetScrollPane(MmgObj ScrollPane) {
        scrollPane = ScrollPane;
    }

    public MmgRect GetViewPortRect() {
        return viewPortRect;
    }

    public void SetViewPortRect(MmgRect ViewPortRect) {
        viewPortRect = ViewPortRect;
    }

    public MmgRect GetScrollPaneRect() {
        return scrollPaneRect;
    }

    public void SetScrollPaneRect(MmgRect ScrollPaneRect) {
        scrollPaneRect = ScrollPaneRect;
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
    }

    public int GetScrollBarSliderButtonHeight() {
        return scrollBarSliderButtonHeight;
    }

    public void SetScrollBarSliderButtonHeight(int ScrollBarSliderButtonHeight) {
        scrollBarSliderButtonHeight = ScrollBarSliderButtonHeight;
    }
    
    public int GetScrollBarSliderHeight() {
        return scrollBarSliderHeight;
    }

    public void SetScrollBarSliderHeight(int ScrollBarSliderHeight) {
        scrollBarSliderHeight = ScrollBarSliderHeight;
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
                
            }
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

                p.GetGraphics().setColor(Color.RED);
                p.DrawRect(this);
                p.DrawRect(GetX() + GetWidth() - this.scrollBarWidth, GetY(), this.scrollBarWidth, h);
                
                p.GetGraphics().setColor(Color.BLUE);
                p.DrawRect(viewPortRect);
                
                p.GetGraphics().setColor(Color.GREEN);
                p.DrawRect(scrollPaneRect);
                
                //slider
                p.GetGraphics().setColor(Color.ORANGE);
                p.DrawRect(GetX() + GetWidth() - this.scrollBarWidth, GetY() + this.offsetY, this.scrollBarWidth, this.scrollBarSliderHeight);   
                
                //slider button bottom
                p.GetGraphics().setColor(Color.CYAN);
                p.DrawRect(GetX() + GetWidth() - this.scrollBarWidth, GetY() + h - this.scrollBarSliderButtonHeight, this.scrollBarWidth, this.scrollBarSliderButtonHeight);                   
                
                //slider button top
                p.GetGraphics().setColor(Color.PINK);
                p.DrawRect(GetX() + GetWidth() - this.scrollBarWidth, GetY(), this.scrollBarWidth, this.scrollBarSliderButtonHeight);                                   
                
                p.GetGraphics().setColor(c);
            }

        } else {
            //do nothing

        }
    }    
}

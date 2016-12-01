package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import static net.middlemind.MmgGameApiJava.MmgBase.MmgBmpScaler.GRAPHICS_CONFIG;

/**
 * Class that provides 9 slice bitmap scaling support.
 * Created by Middlemind Games 
 * 12/01/2016
 * 
 * @author Victor G. Brusca
 */
public final class Mmg9Slice extends MmgObj {
    
    private int offset;
    private MmgBmp src;
    private MmgBmp dest;
    public static final GraphicsConfiguration GRAPHICS_CONFIG = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        
    public Mmg9Slice(int Offset, MmgBmp Src, int w, int h) {
        SetOffset(Offset);
        SetSrc(Src);
        SetWidth(w);
        SetHeight(h);
        SetPosition(null);
        SetIsVisible(true);
        DrawDest();
    }
    
    public Mmg9Slice(int Offset, MmgBmp Src, int w, int h, MmgVector2 Pos) {
        SetOffset(Offset);
        SetSrc(Src);
        SetWidth(w);
        SetHeight(h);
        SetPosition(Pos);
        SetIsVisible(true);
        DrawDest();        
    }    

    public Mmg9Slice(Mmg9Slice m) {
        SetOffset(m.GetOffset());
        SetSrc(m.GetSrc());
        SetWidth(m.GetWidth());
        SetHeight(m.GetHeight());
        SetPosition(m.GetPosition());
        SetIsVisible(m.GetIsVisible());
        DrawDest();        
    }    
    
    public final void SetOffset(int i) {
        offset = i;
    }
    
    public final int GetOffset() {
        return offset;
    }
    
    public final void SetSrc(MmgBmp b) {
        src = b;
    }
    
    public final MmgBmp GetSrc() {
        return src;
    }

    public final void SetDest(MmgBmp b) {
        dest = b;
    }
    
    public final MmgBmp GetDest() {
        return dest;
    }    
    
    public final void DrawDest() {
        boolean alpha = true;
        MmgBmp b = GetSrc();
        Image img = b.GetImage();
        BufferedImage bg = GRAPHICS_CONFIG.createCompatibleImage(GetWidth(), GetHeight(), alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
        Graphics2D g = (Graphics2D) bg.getGraphics();
        
        if(MmgPen.ADV_RENDER_HINTS == true)
        {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        
        int o = offset;
        
        //TOP
        //draw top left
        g.drawImage(img, 0, 0, o, o, 0, 0, o, o, null);
        
        //draw scaled top center
        g.drawImage(img, o, 0, GetWidth() - o, o, o, 0, b.GetWidth() - o, o, null);
        
        //draw top right
        g.drawImage(img, GetWidth() - o, 0, GetWidth(), o, b.GetWidth() - o, 0, b.GetWidth(), o, null);
        
        //MIDDLE
        //draw middle left
        g.drawImage(img, 0, o, o, GetHeight() - o, 0, o, o, b.GetHeight() - o, null);
        
        //draw middle center
        g.drawImage(img, o, o, GetWidth() - o, GetHeight() - o, o, o, b.GetWidth() - o, b.GetHeight() - o, null);
        
        //draw middle right
        g.drawImage(img, GetWidth() - o, o, GetWidth(), GetHeight() - o, b.GetWidth() - o, o, b.GetWidth(), b.GetHeight() - o, null);
        
        //BOTTOM
        //draw bottom left
        g.drawImage(img, 0, GetHeight() - o, o, GetHeight(), 0, b.GetHeight() - o, o, b.GetHeight(), null);
        
        //draw scaled bottom center
        g.drawImage(img, o, GetHeight() - o, GetWidth() - o, GetHeight(), o, b.GetHeight() - o, b.GetWidth() - o, b.GetHeight(), null);
        
        //draw bottom right
        g.drawImage(img, GetWidth() - o, GetHeight() - o, GetWidth(), GetHeight(), b.GetWidth() - o, b.GetHeight() - o, b.GetWidth(), b.GetHeight(), null);
        
        dest = new MmgBmp(bg);
    }
    
    /**
     * Clones this class.
     * 
     * @return      A clone of this class.
     */
    @Override
    public MmgObj Clone() {
        Mmg9Slice ret = new Mmg9Slice(this);
        return (MmgObj) ret;
    }
    
    /**
     * The base drawing method for the bitmap object.
     * 
     * @param p     The MmgPen used to draw this bitmap.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (GetIsVisible() == true) {
            p.DrawBmp(this.dest);
        } else {
            //do nothing
        }
    }    
}

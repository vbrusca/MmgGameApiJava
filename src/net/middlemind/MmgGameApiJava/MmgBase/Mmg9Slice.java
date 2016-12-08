package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

/**
 * Class that provides 9 slice bitmap scaling support. Created by Middlemind
 * Games 12/01/2016
 *
 * @author Victor G. Brusca
 */
@SuppressWarnings("OverridableMethodCallInConstructor")
public class Mmg9Slice extends MmgObj {

    private int offset;
    private MmgBmp src;
    private MmgBmp dest;
    public static final GraphicsConfiguration GRAPHICS_CONFIG = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    public Mmg9Slice(int Offset, MmgBmp Src, int w, int h) {
        super();
        SetOffset(Offset);
        SetSrc(Src);
        SetWidth(w);
        SetHeight(h);
        SetPosition(null);
        SetIsVisible(true);
        DrawDest();
    }

    public Mmg9Slice(int Offset, MmgBmp Src, int w, int h, MmgVector2 Pos) {
        super();
        SetOffset(Offset);
        SetSrc(Src);
        SetWidth(w);
        SetHeight(h);
        SetPosition(Pos);
        SetIsVisible(true);
        DrawDest();
    }

    public Mmg9Slice(Mmg9Slice m) {
        super();
        SetOffset(m.GetOffset());
        SetSrc(m.GetSrc());
        SetWidth(m.GetWidth());
        SetHeight(m.GetHeight());
        SetPosition(m.GetPosition());
        SetIsVisible(m.GetIsVisible());
        DrawDest();
    }

    public void SetOffset(int i) {
        offset = i;
    }

    public int GetOffset() {
        return offset;
    }

    public void SetSrc(MmgBmp b) {
        src = b;
    }

    public MmgBmp GetSrc() {
        return src;
    }

    public void SetDest(MmgBmp b) {
        dest = b;
    }

    public MmgBmp GetDest() {
        return dest;
    }

    public void DrawDest() {
        boolean alpha = true;
        MmgBmp b = GetSrc();
        Image img = b.GetImage();
        BufferedImage bg = GRAPHICS_CONFIG.createCompatibleImage(GetWidth(), GetHeight(), alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
        Graphics2D g = (Graphics2D) bg.getGraphics();

        if (MmgPen.ADV_RENDER_HINTS == true) {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        int fs = offset;

        //TOP
        //draw top left
        g.drawImage(img, 0, 0, fs, fs, 0, 0, fs, fs, null);

        //draw scaled top center
        g.drawImage(img, fs, 0, GetWidth() - fs, fs, fs, 0, b.GetWidth() - fs, fs, null);

        //draw top right
        g.drawImage(img, GetWidth() - fs, 0, GetWidth(), fs, b.GetWidth() - fs, 0, b.GetWidth(), fs, null);

        //MIDDLE
        //draw middle left
        g.drawImage(img, 0, fs, fs, GetHeight() - fs, 0, fs, fs, b.GetHeight() - fs, null);

        //draw middle center
        g.drawImage(img, fs, fs, GetWidth() - fs, GetHeight() - fs, fs, fs, b.GetWidth() - fs, b.GetHeight() - fs, null);

        //draw middle right
        g.drawImage(img, GetWidth() - fs, fs, GetWidth(), GetHeight() - fs, b.GetWidth() - fs, fs, b.GetWidth(), b.GetHeight() - fs, null);

        //BOTTOM
        //draw bottom left
        g.drawImage(img, 0, GetHeight() - fs, fs, GetHeight(), 0, b.GetHeight() - fs, fs, b.GetHeight(), null);

        //draw scaled bottom center
        g.drawImage(img, fs, GetHeight() - fs, GetWidth() - fs, GetHeight(), fs, b.GetHeight() - fs, b.GetWidth() - fs, b.GetHeight(), null);

        //draw bottom right
        g.drawImage(img, GetWidth() - fs, GetHeight() - fs, GetWidth(), GetHeight(), b.GetWidth() - fs, b.GetHeight() - fs, b.GetWidth(), b.GetHeight(), null);

        dest = new MmgBmp(bg);
    }

    /**
     * Clones this class.
     *
     * @return A clone of this class.
     */
    @Override
    public MmgObj Clone() {
        Mmg9Slice ret = new Mmg9Slice(this);
        return (MmgObj) ret;
    }

    /**
     * The base drawing method for the bitmap object.
     *
     * @param p The MmgPen used to draw this bitmap.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (GetIsVisible() == true) {
            p.DrawBmp(this.dest);
        } else {
            //do nothing
        }
    }

    public boolean Equals(Mmg9Slice r) {
        if (GetOffset() == r.GetOffset() && GetSrc().Equals(r.GetSrc()) == true && GetDest().Equals(r.GetDest()) == true) {
            return true;
        } else {
            return false;
        }
    }
}

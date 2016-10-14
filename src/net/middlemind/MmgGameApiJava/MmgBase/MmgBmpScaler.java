/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.middlemind.MmgGameApiJava.MmgBase;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author victor
 */
public class MmgBmpScaler {
    public static final GraphicsConfiguration GRAPHICS_CONFIG = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    
    public static final MmgBmp ScaleMmgBmp(MmgBmp subj, double scale, boolean alpha) {
        int w = subj.GetWidth();
        int h = subj.GetHeight();
        int nw = (int)(w * scale);
        int nh = (int)(h * scale);
        Image img = subj.GetImage();
        
        BufferedImage bg = GRAPHICS_CONFIG.createCompatibleImage(nw, nh, alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
        Graphics2D g = (Graphics2D) bg.getGraphics();
        
        if(MmgPen.ADV_RENDER_HINTS == true)
        {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        
        g.drawImage(img, 0, 0, nw, nh, 0, 0, w, h, null);
        return new MmgBmp(bg);
    }
    
    public static final MmgBmp RotateMmgBmp(MmgBmp subj, int angle, boolean alpha) {
        int w = subj.GetWidth();
        int h = subj.GetHeight();   
        Image img = subj.GetImage();
        BufferedImage bg = GRAPHICS_CONFIG.createCompatibleImage(w, h, alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
        Graphics2D g = (Graphics2D)bg.getGraphics();        
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(angle), (w/2), (h/2));
        
        if(MmgPen.ADV_RENDER_HINTS == true)
        {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }        
        
        g.drawImage(img, 0, 0, null);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        bg = op.filter(bg, null);
        g.dispose();
        return new MmgBmp(bg);
    }
}

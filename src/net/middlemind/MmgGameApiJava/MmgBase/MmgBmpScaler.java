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
import java.awt.Transparency;
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
        g.drawImage(img, 0, 0, nw, nh, 0, 0, w, h, null);
        return new MmgBmp(bg);
    }    
}

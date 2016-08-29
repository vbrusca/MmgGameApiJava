package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * Class that provides high level helper methods.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public class MmgHelper {

    /**
     * Tests for collision of two rectangles.
     * 
     * @param r1x       Rectangle #1, X coordinate.
     * @param r1y       Rectangle #1, Y coordinate.
     * @param w         Rectangle #1, width.
     * @param h         Rectangle #1, height.
     * @param r         Rectangle #2.
     * @return          True if there is a collision of the two rectangles.
     */
    public static boolean RectCollision(int r1x, int r1y, int w, int h, MmgRect r) {
        int r2x = r.GetLeft();
        int r2y = r.GetTop();

        return RectCollision(r1x, r1y, w, h, r2x, r2y, (r.GetRight() - r.GetLeft()), (r.GetBottom() - r.GetTop()));
    }

    /**
     * Tests for collision of two rectangles.
     * 
     * @param src       Rectangle #1.
     * @param dest      Rectangle #2.
     * @return          True if there is a collision of the two rectangles.
     */
    public static boolean RectCollision(MmgRect src, MmgRect dest) {
        return RectCollision(src.GetLeft(), src.GetTop(), src.GetWidth(), src.GetHeight(), dest);
    }

    /**
     * Tests for collisions of two rectangles.
     * 
     * @param r1x       Rectangle #1, X coordinate.
     * @param r1y       Rectangle #1, Y coordinate.
     * @param r1w       Rectangle #1, width.
     * @param r1h       Rectangle #1, height.
     * @param r2x       Rectangle #2, X coordinate.
     * @param r2y       Rectangle #2, Y coordinate.
     * @param r2w       Rectangle #2, width.
     * @param r2h       Rectangle #2, height.
     * @return          True if there is a collision of the two rectangles.
     */
    public static boolean RectCollision(int r1x, int r1y, int r1w, int r1h, int r2x, int r2y, int r2w, int r2h) {

        if(r1x >= r2x && r1x <= (r2x + r2w) && r1y >= r2y && r1y <= (r2y + r2h)) {
            return true;

        }else if((r1x + r1w) >= r2x && (r1x + r1w) <= (r2x + r2w) && r1y >= r2y && r1y <= (r2y + r2h)) {
            return true;

        }else if((r1x + r1w) >= r2x && (r1x + r1w) <= (r2x + r2w) && (r1y + r1h) >= r2y && (r1y + r1h) <= (r2y + r2h)) {
            return true;

        }else if(r1x >= r2x && r1x <= (r2x + r2w) && (r1y + r1h) >= r2y && (r1y + r1h) <= (r2y + r2h)) {
            return true;

        }else if(r2x >= r1x && r2x <= (r1x + r1w) && r2y >= r1y && r2y <= (r1y + r1h)) {
            return true;

        }else if((r2x + r2w) >= r1x && (r2x + r2w) <= (r1x + r1w) && r2y >= r1y && r2y <= (r1y + r1h)) {
            return true;

        }else if((r2x + r2w) >= r1x && (r2x + r2w) <= (r1x + r1w) && (r2y + r2h) >= r1y && (r2y + r2h) <= (r1y + r1h)) {
            return true;

        }else if(r2x >= r1x && r2x <= (r1x + r1w) && (r2y + r2h) >= r1y && (r2y + r2h) <= (r1y + r1h)) {
            return true;

        }

        return false;
    }

    /**
     * The absolute value of the distance between two points.
     * 
     * @param x1        Point #1, X coordinate.
     * @param x2        Point #2, X coordinate.
     * @param y1        Point #1, Y coordinate.
     * @param y2        Point #2, Y coordinate.
     * @return          The absolute value of the calculated distance.
     */
    public static int AbsDistance(int x1, int x2, int y1, int y2) {
        return (int) Math.sqrt((double) (((y1 - x1) * (y1 - x1)) + ((y2 - x2) * (y2 - x2))));
    }

    /**
     * Centers an MmgObj horizontally.
     * 
     * @param obj       The object to center.
     * @return          A centered object.
     * @see MmgScreenData
     */
    public static MmgObj CenterHor(MmgObj obj) {
        MmgVector2 vec = new MmgVector2((int)(MmgScreenData.GetGameLeft() + (MmgScreenData.GetGameWidth() - obj.GetWidth()) / 2), obj.GetPosition().GetY());
        obj.SetPosition(vec);
        return obj;
    }
    
    /**
     * Centers an MmgObj vertically.
     * 
     * @param obj       The object to center.
     * @return          A centered object.
     * @see MmgScreenData
     */
    public static MmgObj CenterVert(MmgObj obj) {
        MmgVector2 vec = new MmgVector2(obj.GetPosition().GetX(), (int)(MmgScreenData.GetGameTop() + (MmgScreenData.GetGameHeight() - obj.GetHeight()) / 2));
        obj.SetPosition(vec);
        return obj;
    }
    
    /**
     * Centers an MmgObj horizontally and vertically.
     * 
     * @param obj       The object to center.
     * @return          A centered object.
     * @see MmgScreenData
     */
    public static MmgObj CenterHorAndVert(MmgObj obj) {
        obj = CenterHor(obj);
        obj = CenterVert(obj);
        return obj;
    }

    /**
     * Centers an MmgObj horizontally and places it at the top of the screen
     * vertically.
     * 
     * @param obj       The object to position.
     * @return          A repositioned object.
     * @see MmgScreenData
     */
    public static MmgObj CenterHorAndTop(MmgObj obj) {
        obj = CenterHor(obj);
        MmgVector2 pos = obj.GetPosition().Clone();
        pos.SetY(MmgScreenData.GetGameTop());
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Centers an MmgObj horizontally and places it at the bottom of the screen
     * vertically.
     * 
     * @param obj       The object to position.
     * @return          A repositioned object.
     */
    public static MmgObj CenterHorAndBot(MmgObj obj) {
        obj = CenterHor(obj);
        MmgVector2 pos = obj.GetPosition().Clone();
        int h = obj.GetHeight();
        pos.SetY((MmgScreenData.GetGameTop() + MmgScreenData.GetGameHeight()) - h);
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Centers an MmgObj horizontally and vertically.
     * 
     * @param obj       The object to position.
     * @return          A repositioned object.
     */
    public static MmgObj CenterHorAndMid(MmgObj obj) {
        return CenterHorAndVert(obj);
    }

    /**
     * Repositions an MmgObj horizontally left and vertically top.
     * 
     * @param obj       The object to position.
     * @return          A repositioned object.
     */
    public static MmgObj LeftHorAndTop(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        pos.SetX(MmgScreenData.GetGameLeft());
        pos.SetY(MmgScreenData.GetGameTop());
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Repositions an MmgObj horizontally left, and vertically top.
     * 
     * @param obj       The object to position.
     * @return          A repositioned object.
     */
    public static MmgObj LeftHorAndMid(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        pos.SetX(MmgScreenData.GetGameLeft());
        obj.SetPosition(pos);
        return CenterVert(obj);
    }

    /**
     * Repositions an MmgObj horizontally left and vertically bottom.
     * 
     * @param obj       The object to reposition.
     * @return          A repositioned object.
     */
    public static MmgObj LeftHorAndBot(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        int h = obj.GetHeight();
        pos.SetY((MmgScreenData.GetGameTop() + MmgScreenData.GetGameHeight()) - h);
        pos.SetX(MmgScreenData.GetGameLeft());
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Repositions an MmgObj horizontally right, and vertically top.
     * 
     * @param obj       The object to reposition.
     * @return          A repositioned object.
     */
    public static MmgObj RightHorAndTop(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        int w = obj.GetWidth();
        pos.SetX((MmgScreenData.GetGameLeft() + MmgScreenData.GetGameWidth()) - w);
        pos.SetY(MmgScreenData.GetGameTop());
        obj.SetPosition(pos);
        return obj;
    }

    /**
     * Repositions an MmgObj horizontally right, and vertically middle.
     * 
     * @param obj       The object to reposition.
     * @return          A repositioned object.
     */
    public static MmgObj RightHorAndMid(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        int w = obj.GetWidth();
        pos.SetX((MmgScreenData.GetGameLeft() + MmgScreenData.GetGameWidth()) - w);
        obj.SetPosition(pos);
        return CenterVert(obj);
    }

    /**
     * Repositions an MmgObj horizontally right, and vertically bottom.
     * 
     * @param obj       The object to reposition.
     * @return          A repositioned object.
     */
    public static MmgObj RightHorAndBot(MmgObj obj) {
        MmgVector2 pos = obj.GetPosition().Clone();
        int h = obj.GetHeight();
        int w = obj.GetWidth();
        pos.SetY((MmgScreenData.GetGameTop() + MmgScreenData.GetGameHeight()) - h);
        pos.SetX((MmgScreenData.GetGameLeft() + MmgScreenData.GetGameWidth()) - w);
        obj.SetPosition(pos);
        return obj;
    }
    
    /**
     * Scaling method, applies the scale X to the given argument.
     * 
     * @param val       The value to scale.
     * @return          A scaled value.
     */
    public static int ScaleValue(int val) {
        return (int)((double)val * MmgScreenData.GetScaleX());
    }
    
    /**
     * Scaling method, applies the scale x to the given argument.
     * 
     * @param val       The value to scale.
     * @return          A scaled value.
     */
    public static float ScaleValue(float val) {
        return (float)((double)val * MmgScreenData.GetScaleX());
    }
    
    /**
     * Scaling method, applies the scale x to the given argument.
     * 
     * @param val       The value to scale.
     * @return          A scaled value.
     */
    public static double ScaleValue(double val) {
        return (double)((double)val * MmgScreenData.GetScaleX());
    }
}

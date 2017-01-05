package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * Class that provides tween support to an underlying MmgObj instance. Created
 * by Middlemind Games 12/01/2016
 *
 * @author Victor G. Brusca
 */
public class MmgSizeTween extends MmgObj {

    public static final int MMG_SIZE_TWEEN_REACH_FINISH = 0;
    public static final int MMG_SIZE_TWEEN_REACH_START = 1;
    public static final int MMG_SIZE_TWEEN_REACH_FINISH_TYPE = 0;
    public static final int MMG_SIZE_TWEEN_REACH_START_TYPE = 1;
    private MmgObj subj;
    private boolean atStart;
    private boolean atFinish;
    private MmgVector2 pixelSizeToChange;
    private float msSizeToChange;
    private float pixelsPerMsToChangeX;
    private float pixelsPerMsToChangeY;
    private MmgVector2 startSize;
    private MmgVector2 finishSize;
    private boolean dirStartToFinish;
    private boolean changing;
    private long msStartChange;
    private MmgVector2 tmpV;
    private MmgEventHandler onReachFinish;
    private MmgEventHandler onReachStart;
    private final MmgEvent reachFinish = new MmgEvent(null, "reach_finish", MmgSizeTween.MMG_SIZE_TWEEN_REACH_FINISH, MmgSizeTween.MMG_SIZE_TWEEN_REACH_FINISH_TYPE, null, null);
    private final MmgEvent reachStart = new MmgEvent(null, "reach_start", MmgSizeTween.MMG_SIZE_TWEEN_REACH_START, MmgSizeTween.MMG_SIZE_TWEEN_REACH_START_TYPE, null, null);

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgSizeTween(MmgObj subj, float msTimeToChange, MmgVector2 startSize, MmgVector2 finishSize) {
        super();

        MmgDebug.wr("MmgSizeTween Found start pos: " + startSize.ToString() + ", " + msTimeToChange);
        MmgDebug.wr("MmgSizeTween Found end pos: " + finishSize.ToString());

        SetSubj(subj);
        SetPixelSizeToChange(new MmgVector2((finishSize.GetX() - startSize.GetX()), (finishSize.GetY() - startSize.GetY())));
        SetMsTimeToChange(msTimeToChange);
        SetPixelsPerMsToChangeX((GetPixelSizeToChange().GetX() / (float) msTimeToChange));
        SetPixelsPerMsToChangeY((GetPixelSizeToChange().GetY() / (float) msTimeToChange));
        SetStartSize(startSize);
        SetFinishSize(finishSize);
        SetWidth(startSize.GetX());
        SetHeight(startSize.GetY());
        SetDirStartToFinish(true);
        SetAtStart(true);
        SetAtFinish(false);
        SetChanging(false);
        MmgDebug.wr("Found pixels per ms Y: " + pixelsPerMsToChangeY);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgSizeTween(float msTimeToChange, MmgVector2 startSize, MmgVector2 finishSize) {
        super();

        MmgDebug.wr("MmgSizeTween Found start pos: " + startSize.ToString() + ", " + msTimeToChange);
        MmgDebug.wr("MmgSizeTween Found end pos: " + finishSize.ToString());

        SetSubj(subj);
        SetPixelSizeToChange(new MmgVector2((finishSize.GetX() - startSize.GetX()), (finishSize.GetY() - startSize.GetY())));
        SetMsTimeToChange(msTimeToChange);
        SetPixelsPerMsToChangeX((GetPixelSizeToChange().GetX() / (float) msTimeToChange));
        SetPixelsPerMsToChangeY((GetPixelSizeToChange().GetY() / (float) msTimeToChange));
        SetStartSize(startSize);
        SetFinishSize(finishSize);
        SetWidth(startSize.GetX());
        SetHeight(startSize.GetY());
        SetDirStartToFinish(true);
        SetAtStart(true);
        SetAtFinish(false);
        SetChanging(false);
        MmgDebug.wr("Found pixels per ms Y: " + pixelsPerMsToChangeY);
    }

    public void SetFinishEventId(int i) {
        if (reachFinish != null) {
            reachFinish.SetEventId(i);
        }
    }

    public void SetStartEventId(int i) {
        if (reachStart != null) {
            reachStart.SetEventId(i);
        }
    }

    public MmgEventHandler GetOnReachFinish() {
        return onReachFinish;
    }

    public void SetOnReachFinish(MmgEventHandler o) {
        onReachFinish = o;
    }

    public MmgEventHandler GetOnReachStart() {
        return onReachStart;
    }

    public void SetOnReachStart(MmgEventHandler o) {
        onReachStart = o;
    }

    public long GetMsStartChange() {
        return msStartChange;
    }

    public void SetMsStartChange(long l) {
        msStartChange = l;
    }

    public float GetPixelsPerMsToChangeX() {
        return pixelsPerMsToChangeX;
    }

    public void SetPixelsPerMsToChangeX(float i) {
        pixelsPerMsToChangeX = i;
    }

    public float GetPixelsPerMsToChangeY() {
        return pixelsPerMsToChangeY;
    }

    public void SetPixelsPerMsToChangeY(float i) {
        pixelsPerMsToChangeY = i;
    }

    public boolean GetChanging() {
        return changing;
    }

    public void SetChanging(boolean b) {
        changing = b;
    }

    public boolean GetDirStartToFinish() {
        return dirStartToFinish;
    }

    public void SetDirStartToFinish(boolean b) {
        dirStartToFinish = b;
    }

    public MmgVector2 GetStartSize() {
        return startSize;
    }

    public void SetStartSize(MmgVector2 v) {
        startSize = v;
    }

    public MmgVector2 GetFinishSize() {
        return finishSize;
    }

    public void SetFinishSize(MmgVector2 v) {
        finishSize = v;
    }

    public MmgVector2 GetPixelSizeToChange() {
        return pixelSizeToChange;
    }

    public void SetPixelSizeToChange(MmgVector2 v) {
        pixelSizeToChange = v;
    }

    public float GetMsTimeToChange() {
        return msSizeToChange;
    }

    public void SetMsTimeToChange(float i) {
        msSizeToChange = i;
    }

    public void SetAtStart(boolean b) {
        atStart = b;
    }

    public boolean GetAtStart() {
        return atStart;
    }

    public void SetAtFinish(boolean b) {
        atFinish = b;
    }

    public boolean GetAtFinish() {
        return atFinish;
    }

    public void SetSubj(MmgObj b) {
        subj = b;
    }

    public MmgObj GetSubj() {
        return subj;
    }

    /**
     * Clones this class.
     *
     * @return A clone of this class.
     */
    @Override
    public MmgObj Clone() {
        MmgSizeTween ret = new MmgSizeTween(GetSubj(), GetMsTimeToChange(), GetStartSize(), GetFinishSize());
        ret.SetAtStart(GetAtStart());
        ret.SetAtFinish(GetAtFinish());
        ret.SetDirStartToFinish(GetDirStartToFinish());
        ret.SetChanging(GetChanging());
        ret.SetMsStartChange(GetMsStartChange());
        ret.SetPixelsPerMsToChangeX(GetPixelsPerMsToChangeX());
        ret.SetPixelsPerMsToChangeY(GetPixelsPerMsToChangeY());
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
            subj.MmgDraw(p);
        } else {
            //do nothing
        }
    }

    /**
     * *
     * Update the current sprite animation frame index.
     *
     * @param updateTick
     * @param currentTimeMs
     * @param msSinceLastFrame
     * @return
     */
    @Override
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        boolean ret = false;

        if (GetIsVisible() == true) {
            if (GetChanging() == true) {
                if (GetDirStartToFinish() == true) {
                    //changing start to finish
                    if ((currentTimeMs - msStartChange) >= msSizeToChange) {
                        SetAtFinish(true);
                        SetAtStart(false);
                        SetChanging(false);
                        SetWidth(finishSize.GetX());
                        SetHeight(finishSize.GetY());                        
                        if (onReachFinish != null) {
                            onReachFinish.MmgHandleEvent(reachFinish);
                        }
                    } else {
                        tmpV = new MmgVector2(startSize.GetX() + (pixelsPerMsToChangeX * (currentTimeMs - msStartChange)), startSize.GetY() + (pixelsPerMsToChangeY * (currentTimeMs - msStartChange)));
                        SetWidth(tmpV.GetX());
                        SetHeight(tmpV.GetY());
                    }

                } else {
                    //changing finish to start
                    MmgDebug.wr("changing finish to start " + (currentTimeMs - msStartChange) + ", " + msSizeToChange);
                    if ((currentTimeMs - msStartChange) >= msSizeToChange) {
                        SetAtFinish(false);
                        SetAtStart(true);
                        SetChanging(false);
                        SetWidth(startSize.GetX());
                        SetHeight(startSize.GetY());                        
                        if (onReachStart != null) {
                            onReachStart.MmgHandleEvent(reachStart);
                        }
                    } else {
                        tmpV = new MmgVector2(finishSize.GetX() - (pixelsPerMsToChangeX * (currentTimeMs - msStartChange)), finishSize.GetY() - (pixelsPerMsToChangeY * (currentTimeMs - msStartChange)));
                        SetWidth(tmpV.GetX());
                        SetHeight(tmpV.GetY());                        
                    }
                }
            }
        } else {
            //do nothing
        }

        return ret;
    }

    @Override
    public int GetWidth() {
        return subj.GetWidth();
    }

    @Override
    public void SetWidth(int w) {
        subj.SetWidth(w);
    }

    @Override
    public int GetHeight() {
        return subj.GetHeight();
    }

    @Override
    public void SetHeight(int h) {
        subj.SetHeight(h);
    }

    @Override
    public MmgVector2 GetPosition() {
        return subj.GetPosition();
    }

    @Override
    public void SetPosition(MmgVector2 v) {
        subj.SetPosition(v);
    }

    public void SetSize(MmgVector2 v) {
        subj.SetWidth(v.GetX());
        subj.SetHeight(v.GetY());
    }
    
    public boolean Equals(MmgSizeTween r) {
        if (GetSubj().Equals(r.GetSubj()) == true
                && GetAtStart() == r.GetAtStart()
                && GetAtFinish() == r.GetAtFinish()
                && GetPixelSizeToChange().Equals(r.GetPixelSizeToChange()) == true
                && GetMsTimeToChange() == r.GetMsTimeToChange()
                && GetPixelsPerMsToChangeX() == r.GetPixelsPerMsToChangeX()
                && GetPixelsPerMsToChangeY() == r.GetPixelsPerMsToChangeY()
                && GetStartSize().Equals(r.GetStartSize()) == true
                && GetFinishSize().Equals(r.GetFinishSize()) == true
                && GetDirStartToFinish() == r.GetDirStartToFinish()
                && GetChanging() == r.GetChanging()) {
            return true;
        } else {
            return false;
        }
    }
}

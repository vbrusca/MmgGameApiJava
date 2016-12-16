package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * Class that provides tween support to an underlying MmgObj instance.
 * Created by Middlemind Games 
 * 12/01/2016
 * 
 * @author Victor G. Brusca
 */
public class MmgPositionTween extends MmgObj {

    public static final int MMG_POSITION_TWEEN_REACH_FINISH = 0;
    public static final int MMG_POSITION_TWEEN_REACH_START = 1;
    public static final int MMG_POSITION_TWEEN_REACH_FINISH_TYPE = 0;
    public static final int MMG_POSITION_TWEEN_REACH_START_TYPE = 1;
    private MmgObj subj;
    private boolean atStart;
    private boolean atFinish;
    private MmgVector2 pixelDistToMove;
    private float msTimeToMove;
    private float pixelsPerMsToMoveX;
    private float pixelsPerMsToMoveY;
    private MmgVector2 startPosition;
    private MmgVector2 finishPosition;
    private boolean dirStartToFinish;
    private boolean moving;
    private long msStartMove;
    private MmgEventHandler onReachFinish;
    private MmgEventHandler onReachStart;
    private final MmgEvent reachFinish = new MmgEvent(null, "reach_finish", MmgPositionTween.MMG_POSITION_TWEEN_REACH_FINISH, MmgPositionTween.MMG_POSITION_TWEEN_REACH_FINISH_TYPE, null, null);
    private final MmgEvent reachStart = new MmgEvent(null, "reach_start", MmgPositionTween.MMG_POSITION_TWEEN_REACH_START, MmgPositionTween.MMG_POSITION_TWEEN_REACH_START_TYPE, null, null);

    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgPositionTween(MmgObj subj, float msTimeToMove, MmgVector2 startPos, MmgVector2 finishPos) {
        super();
        
        MmgDebug.wr("MmgPositionTween Found start pos: " + startPos.ToString() + ", " + msTimeToMove);
        MmgDebug.wr("MmgPositionTween Found end pos: " + finishPos.ToString());
        
        SetSubj(subj);
        SetPixelDistToMove(new MmgVector2((finishPos.GetX() - startPos.GetX()), (finishPos.GetY() - startPos.GetY())));
        SetMsTimeToMove(msTimeToMove);
        SetPixelsPerMsToMoveX((GetPixelDistToMove().GetX() / (float)msTimeToMove));
        SetPixelsPerMsToMoveY((GetPixelDistToMove().GetY() / (float)msTimeToMove));
        SetStartPosition(startPos);
        SetFinishPosition(finishPos);
        SetPosition(startPos);
        SetDirStartToFinish(true);
        SetAtStart(true);
        SetAtFinish(false);
        SetMoving(false);
        MmgDebug.wr("Found pixels per ms Y: " + pixelsPerMsToMoveY);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgPositionTween(float msTimeToMove, MmgVector2 startPos, MmgVector2 finishPos) {
        super();

        MmgDebug.wr("MmgPositionTween Found start pos: " + startPos.ToString());
        MmgDebug.wr("MmgPositionTween Found end pos: " + finishPos.ToString());
        
        SetSubj(subj);
        SetPixelDistToMove(new MmgVector2((finishPos.GetX() - startPos.GetX()), (finishPos.GetY() - startPos.GetY())));
        SetMsTimeToMove(msTimeToMove);
        SetPixelsPerMsToMoveX((int)(GetPixelDistToMove().GetX() / msTimeToMove));
        SetPixelsPerMsToMoveY((int)(GetPixelDistToMove().GetY() / msTimeToMove));
        SetStartPosition(startPos);
        SetFinishPosition(finishPos);
        SetPosition(startPos);
        SetDirStartToFinish(true);
        SetAtStart(true);
        SetAtFinish(false);
        SetMoving(false);
    }

    public void SetFinishEventId(int i) {
        if(reachFinish != null) {
            reachFinish.SetEventId(i);
        }
    }
    
    public void SetStartEventId(int i) {
        if(reachStart != null) {
            reachStart.SetEventId(i);
        }
    }
    
    public MmgEventHandler GetOnReachFinish() {
        return onReachFinish;
    }

    public void SetOnReachFinish(MmgEventHandler onReachFinish) {
        this.onReachFinish = onReachFinish;
    }

    public MmgEventHandler GetOnReachStart() {
        return onReachStart;
    }

    public void SetOnReachStart(MmgEventHandler onReachStart) {
        this.onReachStart = onReachStart;
    }   
    
    public long GetMsStartMove() {
        return msStartMove;
    }

    public void SetMsStartMove(long l) {
        msStartMove = l;
    }
    
    public float GetPixelsPerMsToMoveX() {
        return pixelsPerMsToMoveX;
    }
    
    public void SetPixelsPerMsToMoveX(float i) {
        pixelsPerMsToMoveX = i;
    }
    
    public float GetPixelsPerMsToMoveY() {
        return pixelsPerMsToMoveY;
    }
    
    public void SetPixelsPerMsToMoveY(float i) {
        pixelsPerMsToMoveY = i;
    }
    
    public boolean GetMoving() {
        return moving;
    }
    
    public void SetMoving(boolean b) {
        moving = b;
    }
    
    public boolean GetDirStartToFinish() {
        return dirStartToFinish;
    }
    
    public void SetDirStartToFinish(boolean b) {
        dirStartToFinish = b;
    }
    
    public MmgVector2 GetStartPosition() {
        return startPosition;
    }
    
    public void SetStartPosition(MmgVector2 v) {
        startPosition = v;
    }
    
    public MmgVector2 GetFinishPosition() {
        return finishPosition;
    }
    
    public void SetFinishPosition(MmgVector2 v) {
        finishPosition = v;
    }
    
    public MmgVector2 GetPixelDistToMove() {
        return pixelDistToMove;
    }

    public void SetPixelDistToMove(MmgVector2 v) {
        pixelDistToMove = v;
    }

    public float GetMsTimeToMove() {
        return msTimeToMove;
    }

    public void SetMsTimeToMove(float i) {
        msTimeToMove = i;
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
     * @return      A clone of this class.
     */
    @Override
    public MmgObj Clone() {
        MmgPositionTween ret = new MmgPositionTween(GetSubj(), GetMsTimeToMove(), GetStartPosition(), GetFinishPosition());
        ret.SetAtStart(GetAtStart());
        ret.SetAtFinish(GetAtFinish());
        ret.SetDirStartToFinish(GetDirStartToFinish());
        ret.SetMoving(GetMoving());
        ret.SetMsStartMove(GetMsStartMove());
        ret.SetPixelsPerMsToMoveX(GetPixelsPerMsToMoveX());
        ret.SetPixelsPerMsToMoveY(GetPixelsPerMsToMoveY());
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
            subj.MmgDraw(p);
        } else {
            //do nothing
        }
    }
    
    /***
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
            if(GetMoving() == true) {
                if(GetDirStartToFinish() == true) {
                    //moving start to finish
                    if((currentTimeMs - msStartMove) >= msTimeToMove) {
                        SetAtFinish(true);
                        SetAtStart(false);
                        SetMoving(false);
                        SetPosition(finishPosition);
                        if(onReachFinish != null) {
                            onReachFinish.MmgHandleEvent(reachFinish);
                        }
                    }else{
                        SetPosition(new MmgVector2(startPosition.GetX() + (pixelsPerMsToMoveX * (currentTimeMs - msStartMove)), startPosition.GetY() + (pixelsPerMsToMoveY * (currentTimeMs - msStartMove))));
                    }
                    
                }else {
                    //moving finish to start
                    MmgDebug.wr("moving finish to start " + (currentTimeMs - msStartMove) + ", " + msTimeToMove);
                    if((currentTimeMs - msStartMove) >= msTimeToMove) {
                        SetAtFinish(false);
                        SetAtStart(true);
                        SetMoving(false);
                        SetPosition(startPosition);
                        if(onReachStart != null) {
                            onReachStart.MmgHandleEvent(reachStart);
                        }
                    }else{
                        SetPosition(new MmgVector2(finishPosition.GetX() - (pixelsPerMsToMoveX * (currentTimeMs - msStartMove)), finishPosition.GetY() - (pixelsPerMsToMoveY * (currentTimeMs - msStartMove))));
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
    
    public boolean Equals(MmgPositionTween r) {
        if(GetSubj().Equals(r.GetSubj()) == true 
            && GetAtStart() == r.GetAtStart()
            && GetAtFinish() == r.GetAtFinish()
            && GetPixelDistToMove().Equals(r.GetPixelDistToMove()) == true
            && GetMsTimeToMove() == r.GetMsTimeToMove()
            && GetPixelsPerMsToMoveX() == r.GetPixelsPerMsToMoveX()
            && GetPixelsPerMsToMoveY() == r.GetPixelsPerMsToMoveY()
            && GetStartPosition().Equals(r.GetStartPosition()) == true 
            && GetFinishPosition().Equals(r.GetFinishPosition()) == true
            && GetDirStartToFinish() == r.GetDirStartToFinish()
            && GetMoving() == r.GetMoving()
        ) {
            return true;
        }else {
            return false;
        }
    }
}

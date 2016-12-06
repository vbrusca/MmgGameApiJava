package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * Class that provides tween support to an underlying MmgObj instance.
 * Created by Middlemind Games 
 * 12/01/2016
 * 
 * @author Victor G. Brusca
 */
public class MmgPositionTween extends MmgObj {

    private MmgObj subj;
    private boolean atStart;
    private boolean atFinish;
    private MmgVector2 pixelDistToMove;
    private int msTimeToMove;
    private int pixelsPerMsToMoveX;
    private int pixelsPerMsToMoveY;
    private MmgVector2 startPosition;
    private MmgVector2 finishPosition;
    private boolean dirStartToFinish;
    private boolean moving;
    private long msStartMove;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgPositionTween(MmgObj subj, MmgVector2 pixelDistToMove, int msTimeToMove, MmgVector2 startPos, MmgVector2 finishPos) {
        super();
        SetSubj(subj);
        SetPixelDistToMove(pixelDistToMove);
        SetMsTimeToMove(msTimeToMove);
        SetPixelsPerMsToMoveX((int)(pixelDistToMove.GetX() / msTimeToMove));
        SetPixelsPerMsToMoveY((int)(pixelDistToMove.GetY() / msTimeToMove));
        SetStartPosition(startPos);
        SetFinishPosition(finishPos);
        SetPosition(startPos);
        SetDirStartToFinish(true);
        SetAtStart(true);
        SetAtFinish(false);
        SetMoving(false);
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgPositionTween(MmgVector2 pixelDistToMove, int msTimeToMove, MmgVector2 startPos, MmgVector2 finishPos) {
        super();
        SetPixelDistToMove(pixelDistToMove);
        SetMsTimeToMove(msTimeToMove);
        SetPixelsPerMsToMoveX((int)(pixelDistToMove.GetX() / msTimeToMove));
        SetPixelsPerMsToMoveY((int)(pixelDistToMove.GetY() / msTimeToMove));
        SetStartPosition(startPos);
        SetFinishPosition(finishPos);
        SetPosition(startPos);
        SetDirStartToFinish(true);
        SetAtStart(true);
        SetAtFinish(false);
        SetMoving(false);
    }
    
    public long GetMsStartMove() {
        return msStartMove;
    }

    public void SetMsStartMove(long l) {
        msStartMove = l;
    }
    
    public int GetPixelsPerMsToMoveX() {
        return pixelsPerMsToMoveX;
    }
    
    public void SetPixelsPerMsToMoveX(int i) {
        pixelsPerMsToMoveX = i;
    }
    
    public int GetPixelsPerMsToMoveY() {
        return pixelsPerMsToMoveY;
    }
    
    public void SetPixelsPerMsToMoveY(int i) {
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

    public int GetMsTimeToMove() {
        return msTimeToMove;
    }

    public void SetMsTimeToMove(int i) {
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
        MmgPositionTween ret = new MmgPositionTween(GetSubj(), GetPixelDistToMove(), GetMsTimeToMove(), GetStartPosition(), GetFinishPosition());
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
                        SetPosition(finishPosition);
                    }else{
                        SetPosition(new MmgVector2(startPosition.GetX() + (pixelsPerMsToMoveX * (currentTimeMs - msStartMove)), startPosition.GetY() + (pixelsPerMsToMoveY * (currentTimeMs - msStartMove))));
                    }
                    
                }else {
                    //moving finish to start
                    if((currentTimeMs - msStartMove) >= msTimeToMove) {
                        SetPosition(startPosition);
                    }else{
                        SetPosition(new MmgVector2(finishPosition.GetX() - (pixelsPerMsToMoveX * (currentTimeMs - msStartMove)), startPosition.GetY() - (pixelsPerMsToMoveY * (currentTimeMs - msStartMove))));
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

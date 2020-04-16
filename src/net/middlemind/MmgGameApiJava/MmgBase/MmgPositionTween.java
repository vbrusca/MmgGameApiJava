package net.middlemind.MmgGameApiJava.MmgBase;

//TODO: Finish documentation

/**
 * Class that provides tween support to an underlying MmgObj instance.
 * Created by Middlemind Games 12/01/2016
 * 
 * @author Victor G. Brusca
 */
public class MmgPositionTween extends MmgObj {

    /**
     * The event id for a position tween finish event. 
     */
    public static int MMG_POSITION_TWEEN_REACH_FINISH = 0;
    
    /**
     * The event id for a position tween start event.
     */
    public static int MMG_POSITION_TWEEN_REACH_START = 1;
    
    /**
     * The event type for a position tween finish event.
     */
    public static int MMG_POSITION_TWEEN_REACH_FINISH_TYPE = 0;
    
    /**
     * The event type for a position tween start event.
     */
    public static int MMG_POSITION_TWEEN_REACH_START_TYPE = 1;
    
    /**
     * The subject of this position tween.
     */
    private MmgObj subj;

    /**
     * A boolean indicating the tween is at the start position.
     */
    private boolean atStart;
    
    /**
     * A boolean indicating the tween is at the finish position.
     */
    private boolean atFinish;
    
    /**
     * An MmgVector2 that holds the distance to move in X, Y values.
     */
    private MmgVector2 pixelDistToMove;
    
    /**
     * A float indicating the milliseconds allowed to move the specified distance.
     */
    private float msTimeToMove;
    
    /**
     * The pixels per millisecond to move in the X direction.
     */
    private float pixelsPerMsToMoveX;
    
    /**
     * The pixels per millisecond to move in the Y direction.
     */
    private float pixelsPerMsToMoveY;
    
    /**
     * An MmgVector2 that holds the start position.
     */
    private MmgVector2 startPosition;
    
    /**
     * An MmgVector2 that holds the finish position.
     */
    private MmgVector2 finishPosition;
    
    /**
     * A boolean indicating the direction to move, start to finish or finish to start.
     */
    private boolean dirStartToFinish;
    
    /**
     * A boolean indicating if the object is moving.
     */
    private boolean moving;
    
    /**
     * A long value that determines how long to wait before moving.
     */
    private long msStartMove;
    
    /**
     * An event handler for the reach finish event.
     */
    private MmgEventHandler onReachFinish;
    
    /**
     * An event handler for the reach start event.
     */
    private MmgEventHandler onReachStart;
    
    /**
     * An event template for the reach finish event.
     */
    private MmgEvent reachFinish = new MmgEvent(null, "reach_finish", MmgPositionTween.MMG_POSITION_TWEEN_REACH_FINISH, MmgPositionTween.MMG_POSITION_TWEEN_REACH_FINISH_TYPE, null, null);
    
    /**
     * An event template for the reach start event.
     */
    private MmgEvent reachStart = new MmgEvent(null, "reach_start", MmgPositionTween.MMG_POSITION_TWEEN_REACH_START, MmgPositionTween.MMG_POSITION_TWEEN_REACH_START_TYPE, null, null);
    
    /**
     * A private boolean flag for the MmgUpdate method indicating if any work was done.
     */
    private boolean lret;
    
    /**
     * A basic constructor for the MmgPositionTween object.
     * 
     * @param subj              The MmgObj that is the subject of the tween.
     * @param msTimeToMove      The number of milliseconds to move the object.
     * @param startPos          The start position of the tween.
     * @param finishPos         The finish position of the tween.
     */
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

    /**
     * A constructor for the MmgPositionTween object that doesn't set the MmgObj subject.
     * You'll have to specify this object before starting the tween movement.
     * 
     * @param msTimeToMove      The number of milliseconds to move the object.  
     * @param startPos          The start position of the tween.
     * @param finishPos         The finish position of the tween.
     */
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

    /**
     * 
     * 
     * @param obj 
     */
    public MmgPositionTween(MmgPositionTween obj) {
        super();
        
        if(obj.GetSubj() == null) {
            SetSubj(obj.GetSubj());
        } else {
            SetSubj(obj.GetSubj().Clone());
        }
        
        SetAtStart(obj.GetAtStart());
        SetAtFinish(obj.GetAtStart());
        
        if(obj.GetPixelDistToMove() == null) {
            SetPixelDistToMove(obj.GetPixelDistToMove());
        } else {
            SetPixelDistToMove(obj.GetPixelDistToMove().Clone());
        }
        
        SetMsTimeToMove(obj.GetMsTimeToMove());
        SetPixelsPerMsToMoveX(obj.GetPixelsPerMsToMoveX());
        SetPixelsPerMsToMoveX(obj.GetPixelsPerMsToMoveY());
        
        if(obj.GetStartPosition() == null) {
            SetStartPosition(obj.GetStartPosition());
        } else {
            SetStartPosition(obj.GetStartPosition().Clone());            
        }
        
        if(obj.GetFinishPosition() == null) {
            SetFinishPosition(obj.GetFinishPosition());
        } else {
            SetFinishPosition(obj.GetFinishPosition().Clone());            
        }    
        
        SetDirStartToFinish(obj.GetDirStartToFinish());
        SetMoving(obj.GetMoving());
        SetMsStartMove(obj.GetMsStartMove());
        SetOnReachStart(obj.GetOnReachStart());
        SetOnReachFinish(obj.GetOnReachFinish());
    }
    
    /**
     * Sets the finish event id.
     * 
     * @param i     The finish event id.
     */
    public void SetFinishEventId(int i) {
        if(reachFinish != null) {
            reachFinish.SetEventId(i);
        }
    }
    
    /**
     * Sets the start event id.
     * 
     * @param i     The start event id.
     */
    public void SetStartEventId(int i) {
        if(reachStart != null) {
            reachStart.SetEventId(i);
        }
    }
    
    /**
     * Gets the on reach finish event handler.
     * 
     * @return      The on reach finish event handler.
     */
    public MmgEventHandler GetOnReachFinish() {
        return onReachFinish;
    }

    /**
     * Sets the on reach finish event handler.
     * 
     * @param o     The on reach finish event handler. 
     */
    public void SetOnReachFinish(MmgEventHandler o) {
        onReachFinish = o;
    }

    /**
     * Gets the on reach start event handler.
     * 
     * @return      The on reach start event handler.
     */
    public MmgEventHandler GetOnReachStart() {
        return onReachStart;
    }

    /**
     * Sets the on reach start event handler.
     * 
     * @param o     The on reach start event handler.
     */
    public void SetOnReachStart(MmgEventHandler o) {
        onReachStart = o;
    }   
    
    /**
     * Gets the milliseconds to wait before starting the movement.
     * 
     * @return      The milliseconds to wait before starting the movement.
     */
    public long GetMsStartMove() {
        return msStartMove;
    }

    /**
     * Sets the milliseconds to wait before starting the movement.
     * 
     * @param l     The milliseconds to wait before starting the movement.
     */
    public void SetMsStartMove(long l) {
        msStartMove = l;
    }
    
    /**
     * Gets the pixels per millisecond to move on the X axis.
     * 
     * @return      The pixels per millisecond to move on the X axis.
     */
    public float GetPixelsPerMsToMoveX() {
        return pixelsPerMsToMoveX;
    }
    
    /**
     * Sets the pixels per millisecond to move on the X axis.
     * 
     * @param i     The pixels per millisecond to move on the X axis.
     */
    public void SetPixelsPerMsToMoveX(float i) {
        pixelsPerMsToMoveX = i;
    }
    
    /**
     * Gets the pixels per millisecond to move on the Y axis.
     * 
     * @return      The pixels per millisecond to move on the Y axis.
     */
    public float GetPixelsPerMsToMoveY() {
        return pixelsPerMsToMoveY;
    }
    
    /**
     * Sets the pixels per millisecond to move on the Y axis.
     * 
     * @param i     The pixels per millisecond to move on the Y axis.
     */
    public void SetPixelsPerMsToMoveY(float i) {
        pixelsPerMsToMoveY = i;
    }
    
    /**
     * Gets a boolean indicating if the subject is moving.
     * 
     * @return      A boolean indicating if the subject is moving.
     */
    public boolean GetMoving() {
        return moving;
    }
    
    /**
     * Sets a boolean indicating if the subject is moving.
     * 
     * @param b     A boolean indicating if the subject is moving.
     */
    public void SetMoving(boolean b) {
        moving = b;
    }
    
    /**
     * Gets a boolean indicating the direction of the tween movement, start to finish or finish to start.
     * 
     * @return      A boolean indicating the direction of the tween movement.
     */
    public boolean GetDirStartToFinish() {
        return dirStartToFinish;
    }
    
    /**
     * Sets a boolean indicating the direction of the tween movement, start to finish or finish to start.
     * 
     * @param b     A boolean indicating the direction of the tween movement.
     */
    public void SetDirStartToFinish(boolean b) {
        dirStartToFinish = b;
    }
    
    /**
     * Gets the start position of the movement tween.
     * 
     * @return      The start position of the movement tween.
     */
    public MmgVector2 GetStartPosition() {
        return startPosition;
    }
    
    /**
     * Sets the start position of the movement tween.
     * 
     * @param v     The start position of the movement tween.
     */
    public void SetStartPosition(MmgVector2 v) {
        startPosition = v;
    }
    
    /**
     * Gets the finish position of the movement tween.
     * 
     * @return      The finish position of the movement tween.
     */
    public MmgVector2 GetFinishPosition() {
        return finishPosition;
    }
    
    /**
     * Sets the finish position of the movement tween
     * 
     * @param v     The finish position of the movement tween.
     */
    public void SetFinishPosition(MmgVector2 v) {
        finishPosition = v;
    }
    
    /**
     * Gets the pixel distance to move on the X and Y axis.
     * 
     * @return      The pixel distance to move on the X and Y axis.
     */
    public MmgVector2 GetPixelDistToMove() {
        return pixelDistToMove;
    }

    /**
     * Sets the pixel distance to move on the X and Y axis.
     * 
     * @param v     The pixel distance to move on the X and Y axis.
     */
    public void SetPixelDistToMove(MmgVector2 v) {
        pixelDistToMove = v;
    }

    /**
     * Gets the millisecond time to complete the movement.
     * 
     * @return      The millisecond time to complete the movement.
     */
    public float GetMsTimeToMove() {
        return msTimeToMove;
    }

    /**
     * Sets the millisecond time to complete the movement.
     * 
     * @param i     The millisecond time to complete the movement.
     */
    public void SetMsTimeToMove(float i) {
        msTimeToMove = i;
    }
    
    /**
     * Sets a boolean indicating that the tween is at the start position.
     * 
     * @param b     A boolean indicating that the tween is at the start position.
     */
    public void SetAtStart(boolean b) {
        atStart = b;
    }
    
    /**
     * Gets a boolean indicating that the tween is at the start position.
     * 
     * @return      A boolean indicating that the tween is at the start position.
     */
    public boolean GetAtStart() {
        return atStart;
    }
    
    /**
     * Sets a boolean indicating that the tween is at the finish position.
     * 
     * @param b     A boolean indicating that the tween is at the finish position.
     */    
    public void SetAtFinish(boolean b) {
        atFinish = b;
    }
    
    /**
     * Gets a boolean indicating that the tween is at the finish position.
     * 
     * @return      A boolean indicating that the tween is at the finish position.
     */    
    public boolean GetAtFinish() {
        return atFinish;
    }
    
    /**
     * Sets the subject of the movement tween.
     * 
     * @param b     The subject of the movement tween
     */
    public void SetSubj(MmgObj b) {
        subj = b;
    }
    
    /**
     * Gets the subject of the movement tween.
     * 
     * @return      The subject of the movement tween.
     */
    public MmgObj GetSubj() {
        return subj;
    }
        
    /**
     * Creates a basic clone of this class.
     * 
     * @return      A clone of this class.
     */
    @Override
    public MmgObj Clone() {
        MmgPositionTween ret = new MmgPositionTween(this);
        return (MmgObj) ret;
    }
    
    
    /**
     * Creates a typed clone of this class.
     * 
     * @return      A typed clone of this class.
     */
    @Override
    public MmgPositionTween CloneTyped() {
        return new MmgPositionTween(this);
    }    
    
    /**
     * The base drawing method for the bitmap object.
     * 
     * @param p     The MmgPen used to draw this bitmap.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (isVisible == true) {
            subj.MmgDraw(p);
        }
    }
    
     /**
     * The MmgUpdate method used to call the update method of the child objects.
     * 
     * @param updateTicks           The update tick number. 
     * @param currentTimeMs         The current time in the game in milliseconds.
     * @param msSinceLastFrame      The number of milliseconds between the last frame and this frame.
     * @return 
     */
    @Override
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        lret = false;
        
        if (isVisible == true) {
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
                        lret = true;
                    }else{
                        SetPosition(new MmgVector2(startPosition.GetX() + (pixelsPerMsToMoveX * (currentTimeMs - msStartMove)), startPosition.GetY() + (pixelsPerMsToMoveY * (currentTimeMs - msStartMove))));
                        lret = true;
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
                        lret = true;
                    }else{
                        SetPosition(new MmgVector2(finishPosition.GetX() - (pixelsPerMsToMoveX * (currentTimeMs - msStartMove)), finishPosition.GetY() - (pixelsPerMsToMoveY * (currentTimeMs - msStartMove))));
                        lret = true;
                    }
                }
            }
        }
        
        return lret;
    }
    
    /**
     * Gets the width of this object.
     * 
     * @return      The width of this object.
     */
    @Override
    public int GetWidth() {
        return subj.GetWidth();
    }
    
    /**
     * Sets the width of this object.
     * 
     * @param w     The width of this object.
     */
    @Override
    public void SetWidth(int w) {
        subj.SetWidth(w);
    }
    
    /**
     * Gets the height of this object.
     * 
     * @return      The height of this object.
     */
    @Override
    public int GetHeight() {
        return subj.GetHeight();
    }
    
    /**
     * Sets the height of this object.
     * 
     * @param h     The height of this object.
     */
    @Override
    public void SetHeight(int h) {
        subj.SetHeight(h);
    }
    
    /**
     * Gets the position of this object.
     * Sets the position of the movement tween subject.
     * 
     * @return      The position of the subject.
     */
    @Override
    public MmgVector2 GetPosition() {
        return subj.GetPosition();
    }
    
    /**
     * Sets the position of this object.
     * 
     * @param v     The position of the subject.
     */
    @Override
    public void SetPosition(MmgVector2 v) {
        subj.SetPosition(v);
    }
    
    /**
     * A method used to check the equality of this MmgPositionTween when compared to another MmgPositionTween.
     * Compares object fields to determine equality.
     * 
     * @param r     The MmgPositionTween object to compare to.
     * @return      A boolean indicating if the two objects are equal or not.
     */
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

package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * Class that represents a pulse or changing value. Created on June 1, 2005,
 * 10:57 PM by Middlemind Games Created by Middlemind Games
 *
 * @author Victor G. Brusca
 */
public class MmgPulse {

    /**
     * If the pulse if growing 1 or shrinking -1.
     */
    private int direction; //growing or shrinking, 1 or -1

    /**
     * How much time until the direction is flipped.
     */
    private long timeFlip; //how much time until the direction is flipped

    /**
     * How much total time in the oscillation.
     */
    private long timeTotal; //how much total time in the oscillation

    /**
     * The start time for the oscillation.
     */
    private long timeStart;

    /**
     * Total change that must occur.
     */
    private double change;

    /**
     * Total change that must occur per millisecond.
     */
    private double changePerMs;

    /**
     * Time difference.
     */
    private long timeDiff;

    /**
     * Scaling baseline values.
     */
    private MmgVector2 baseLineScaling;

    /**
     * Adjusted scaling values.
     */
    private MmgVector2 adjScaling;

    /**
     * A class helper variable.
     */
    private double tmpX;

    /**
     * A class helper variable.
     */
    private double tmpY;

    /**
     * Constructor that sets the start direction, the total milliseconds, the
     * change, and a baseline scaling value.
     *
     * @param startDir The oscillation start direction.
     * @param totalMs The total milliseconds to use for the entire oscillation.
     * @param chng The change to apply to the object.
     * @param blS A baseline scaling value.
     */
    public MmgPulse(int startDir, long totalMs, double chng, MmgVector2 blS) {
        direction = startDir;
        timeTotal = totalMs;
        timeFlip = totalMs / 2;
        timeStart = 0;
        change = chng;
        baseLineScaling = blS;
        adjScaling = new MmgVector2(baseLineScaling.GetXDouble() + (baseLineScaling.GetXDouble() * change * direction), baseLineScaling.GetYDouble() + (baseLineScaling.GetYDouble() * change * direction));

        changePerMs = (adjScaling.GetXDouble() - baseLineScaling.GetXDouble()) / timeFlip;

        MmgDebug.wr("Direction: " + direction);
        MmgDebug.wr("TimeTotal: " + timeTotal);
        MmgDebug.wr("TimeFlip: " + timeFlip);
        MmgDebug.wr("TimeStart: " + timeStart);
        MmgDebug.wr("Change: " + change);
        MmgDebug.wr("Change/MS: " + changePerMs);
        MmgDebug.wr("MaxX: " + adjScaling.GetXDouble());
        MmgDebug.wr("MaxY: " + adjScaling.GetYDouble());
        MmgDebug.wr("BaseX: " + baseLineScaling.GetXDouble());
        MmgDebug.wr("BaseY: " + baseLineScaling.GetYDouble());
    }

    /**
     * Gets the direction of the oscillation.
     *
     * @return The direction of the oscillation.
     */
    public int GetDirection() {
        return direction;
    }

    /**
     * Sets the direction of the oscillation.
     *
     * @param d The direction of the oscillation.
     */
    public void SetDirection(int d) {
        direction = d;
    }

    /**
     * Gets the oscillation time flip for this object.
     *
     * @return The oscillation time flip.
     */
    public long GetTimeFlip() {
        return timeFlip;
    }

    /**
     * Sets the oscillation time flip for this object.
     *
     * @param l The oscillation time flip.
     */
    public void SetTimeFlip(long l) {
        timeFlip = l;
    }

    /**
     * Gets the total oscillation time.
     *
     * @return The total oscillation time.
     */
    public long GetTimeTotal() {
        return timeTotal;
    }

    /**
     * Sets the total oscillation time.
     *
     * @param l The total oscillation time.
     */
    public void SetTimeTotal(long l) {
        timeTotal = l;
    }

    /**
     * Gets the oscillation start time.
     *
     * @return The oscillation start time.
     */
    public long GetTimeStart() {
        return timeStart;
    }

    /**
     * Sets the oscillation start time.
     *
     * @param l The oscillation start time.
     */
    public void SetTimeStart(long l) {
        timeStart = l;
    }

    /**
     * Gets the rate of change.
     *
     * @return The rate of change.
     */
    public double GetChange() {
        return change;
    }

    /**
     * Sets the rate of change.
     *
     * @param c The rate of change.
     */
    public void SetChange(double c) {
        change = c;
    }

    /**
     * Updates the current pulse state.
     *
     * @param v The current object position.
     */
    public void Update(MmgVector2 v) {
        if (timeStart == 0) {
            timeStart = System.currentTimeMillis();
        }

        timeDiff = (long) (System.currentTimeMillis() - timeStart);
        tmpX = 0;
        tmpY = 0;

        if (direction == 1) {
            tmpX = baseLineScaling.GetXDouble() + ((double) changePerMs * (double) timeDiff);
            tmpY = baseLineScaling.GetYDouble() + ((double) changePerMs * (double) timeDiff);

        } else if (direction == -1) {
            tmpX = adjScaling.GetXDouble() - ((double) changePerMs * (double) timeDiff);
            tmpY = adjScaling.GetYDouble() - ((double) changePerMs * (double) timeDiff);
        }

        v.SetX(tmpX);
        v.SetY(tmpY);

        if (timeDiff >= timeFlip) {
            timeStart = 0;
            direction *= -1;
        }
    }
}

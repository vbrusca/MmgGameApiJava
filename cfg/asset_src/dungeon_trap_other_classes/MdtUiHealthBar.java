package game.jam.DungeonTrap;

import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;

/**
 * A class that represents a UI element, the health bar.
 * 
 * @author Victor G. Brusca, Middlemind Games
 * 09/22/2020
 */
public class MdtUiHealthBar extends MdtBase {

    /**
     * The subject of this UI element.
     */
    public MmgBmp subj = null;
    
    /**
     * 
     */
    public MmgBmp subjBack = null;
    
    /**
     * A boolean flag indicating if the associated character is dead.
     */
    public boolean isDead = false;
    
    /**
     * The maximum health value.
     */
    public int healthMax = 4;
    
    /**
     * The current health value.
     */
    public int healthCurrent = 4;
    
    /**
     * A private variable used in internal methods.
     */
    private boolean lret = false;    
    
    /**
     * 
     */
    public ScreenGame screen = null;
    
    /**
     * 
     */
    public MdtPlayerType player = MdtPlayerType.NONE;
        
    /**
     * 
     */
    public MmgColor backColor = null;
    
    /**
     * A base constructor that takes no arguments and loads object resources automatically.
     */
    public MdtUiHealthBar(MdtPlayerType Player, ScreenGame Screen, MmgColor BackColor) {
        SetSubj(MmgHelper.GetBasicCachedBmp("health_ui_lg.png"));
        SetMdtType(MdtObjType.UI);
        SetMdtSubType(MdtObjSubType.UI_HEALTH_BAR);
        SetWidth(subj.GetWidth());
        SetHeight(subj.GetHeight());   
        SetPlayer(Player);
        SetScreen(Screen);
        SetBackColor(BackColor);
        SetSubjBack(MmgHelper.CreateFilledBmp((int)(subj.GetWidth() * 0.80) - 2, (int)(subj.GetHeight() * 0.90), BackColor));
        SetCurrentHealth(healthMax);
    }
    
    /**
     * A constructor that allows you to specify the subject of the object.
     * 
     * @param Subj      The subject of the object.
     */
    public MdtUiHealthBar(MmgBmp Subj, MdtPlayerType Player, ScreenGame Screen, MmgColor BackColor) {
        SetSubj(Subj);
        SetMdtType(MdtObjType.UI);
        SetMdtSubType(MdtObjSubType.UI_HEALTH_BAR);
        SetWidth(subj.GetWidth());
        SetHeight(subj.GetHeight());
        SetPlayer(Player);
        SetScreen(Screen);
        SetBackColor(BackColor);
        SetSubjBack(MmgHelper.CreateFilledBmp((int)(subj.GetWidth() * 0.80) - 2, (int)(subj.GetHeight() * 0.90), BackColor));        
        SetCurrentHealth(healthMax);
    }

    public void TakeDamage(int i) {
        healthCurrent -= i;
        if(healthCurrent < 0) {
            healthCurrent = 0;
        }
        SetCurrentHealth(healthCurrent);
    }
    
    /**
     * 
     */
    public void RestoreAllHealth() {
        SetCurrentHealth(healthMax);
    }
    
    /**
     * 
     * 
     * @param i 
     */
    public void SetCurrentHealth(int i) {
        if(i <= 0) {
            //player is dead
            SetSubjBack(MmgHelper.CreateFilledBmp(1, (int)(subj.GetHeight() * 0.90), backColor));
        } else {
            SetSubjBack(MmgHelper.CreateFilledBmp(i * 30, (int)(subj.GetHeight() * 0.90), backColor));
        }
        subjBack.SetPosition(new MmgVector2(subj.GetX() + MmgHelper.ScaleValue(37), subj.GetY() + (int)(subj.GetHeight() * 0.10)/2));        
    }

    /**
     * 
     * 
     * @return 
     */
    public MmgColor GetBackColor() {
        return backColor;
    }

    /**
     * 
     * 
     * @param c 
     */
    public void SetBackColor(MmgColor c) {
        backColor = c;
    }
    
    /**
     * 
     * 
     * @return 
     */
    public MmgBmp GetSubjBack() {
        return subjBack;
    }

    /**
     * 
     * 
     * @param b 
     */
    public void SetSubjBack(MmgBmp b) {
        subjBack = b;
    }

    /**
     * 
     * 
     * @return 
     */
    public ScreenGame GetScreen() {
        return screen;
    }

    /**
     * 
     * 
     * @param s 
     */
    public void SetScreen(ScreenGame s) {
        screen = s;
    }

    /**
     * 
     * 
     * @return 
     */
    public MdtPlayerType GetPlayer() {
        return player;
    }

    /**
     * 
     * 
     * @param p 
     */
    public void SetPlayer(MdtPlayerType p) {
        player = p;
    }

    /**
     * Gets the subject of the object.
     * 
     * @return      The subject of the object.
     */
    public MmgBmp GetSubj() {
        return subj;
    }

    /**
     * Sets the subject of the object.
     * 
     * @param Subj      The subject of the object. 
     */
    public void SetSubj(MmgBmp Subj) {
        subj = Subj;
    }

    /**
     * Gets a boolean flag indicating the associated character is dead.
     * 
     * @return      A boolean indicating if the character is dead.
     */
    public boolean GetIsDead() {
        return isDead;
    }

    /**
     * Sets a boolean flag indicating the associated character is dead.
     * 
     * @param b     A boolean indicating if the character is dead. 
     */
    public void SetIsDead(boolean b) {
        isDead = b;
    }

    /**
     * Gets the maximum health of the character.
     * 
     * @return      The maximum health of the character.
     */
    public int GetHealthMax() {
        return healthMax;
    }

    /**
     * Sets the maximum health of the character.
     * 
     * @param h     The maximum health of the character. 
     */
    public void SetHealthMax(int h) {
        healthMax = h;
    }

    /**
     * Gets the current health of the character.
     * 
     * @return      The current health of the character.
     */
    public int GetHealthCurrent() {
        return healthCurrent;
    }

    /**
     * Sets the current health of the character.
     * 
     * @param h     The current health of the character.
     */
    public void SetHealthCurrent(int h) {
        healthCurrent = h;
    }
    
    /**
     * Sets the position of the object.
     * 
     * @param v     The position of the object.
     */
    @Override
    public void SetPosition(MmgVector2 v) {
        super.SetPosition(v);
        subj.SetPosition(v);
        subjBack.SetPosition(new MmgVector2(subj.GetX() + MmgHelper.ScaleValue(37), subj.GetY() + (int)(subj.GetHeight() * 0.10)/2));
    }
    
    /**
     * Sets the position of the object.
     * 
     * @param x     The X coordinate of the object.
     * @param y     The Y coordinate of the object.
     */
    @Override
    public void SetPosition(int x, int y) {
        super.SetPosition(x, y);
        subj.SetPosition(x, y);
        subjBack.SetPosition(new MmgVector2(subj.GetX() + MmgHelper.ScaleValue(37), subj.GetY() + (int)(subj.GetHeight() * 0.10)/2));        
    }
    
    /**
     * Sets the X coordinate of the object.
     * 
     * @param i     The X coordinate of the object.
     */
    @Override
    public void SetX(int i) {
        super.SetX(i);
        subj.SetX(i);
        subjBack.SetX(subj.GetX() + MmgHelper.ScaleValue(37));
    }
    
    /**
     * Sets the Y coordinate of the object.
     * 
     * @param i     The Y coordinate of the object. 
     */
    @Override
    public void SetY(int i) {
        super.SetY(i);
        subj.SetY(i);
        subjBack.SetY(subj.GetY() + (int)(subj.GetHeight() * 0.10)/2);        
    }       
   
    /**
     * The MmgUpdate method used to call the update method of the child objects.
     * 
     * @param updateTicks           The update tick number. 
     * @param currentTimeMs         The current time in the game in milliseconds.
     * @param msSinceLastFrame      The number of milliseconds between the last frame and this frame.
     * @return                      A boolean indicating if any work was done this game frame.
     */
    @Override
    public boolean MmgUpdate(int updateTick, long currentTimeMs, long msSinceLastFrame) {
        lret = false;
        if (isVisible == true) {
            subj.MmgUpdate(updateTick, currentTimeMs, msSinceLastFrame); 
            lret = true;
        }
        return lret;
    }
        
    /**
     * Base draw method, handles drawing this class.
     *
     * @param p     The MmgPen used to draw this object.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (isVisible == true) {
            subjBack.MmgDraw(p);
            subj.MmgDraw(p);
        }
    }    
}
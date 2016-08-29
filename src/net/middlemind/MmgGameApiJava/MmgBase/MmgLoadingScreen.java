package net.middlemind.MmgGameApiJava.MmgBase;

/**
 * Class that represents a game loading screen.
 * Created by Middlemind Games
 * 
 * @author Victor G. Brusca
 */
public class MmgLoadingScreen extends MmgGameScreen {

    /**
     * A loading bar to use with this loading screen.
     */
    private MmgLoadingBar loadingBar;
    
    /**
     * A loading bar vertical offset value.
     */
    private final float loadingBarOffsetBottom = 0.10f;

    /**
     * Constructor for this class that sets the loading bar and the loading bar's
     * vertical offset.
     * 
     * @param LoadingBar    The loading bar to use.
     * @param lBarOff       The loading bar vertical offset.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgLoadingScreen(MmgLoadingBar LoadingBar, float lBarOff) {
        super();
        SetLoadingBar(LoadingBar, lBarOff);
    }

    /**
     * Constructor for this class that sets the value of local attributes based on the attributes
     * of the given argument.
     * 
     * @param mls   The MmgLoadingScreen to use to set local attributes.
     */
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MmgLoadingScreen(MmgLoadingScreen mls) {
        super(mls);
        SetLoadingBar(mls.GetLoadingBar(), mls.GetLoadingBarOffsetBottom());
        SetBackground(mls.GetBackground());
        SetFooter(mls.GetFooter());
        SetHeader(mls.GetHeader());
        SetHeight(mls.GetHeight());
        SetIsVisible(mls.GetIsVisible());
        SetLeftCursor(mls.GetLeftCursor());
        SetRightCursor(mls.GetRightCursor());
        SetWidth(mls.GetWidth());
    }

    /**
     * Constructor for this class.
     */
    public MmgLoadingScreen() {
        super();
    }
    
    /**
     * Clones this class.
     * 
     * @return      A clone of this class. 
     */
    @Override
    public MmgObj Clone() {
        MmgLoadingScreen ret = new MmgLoadingScreen(this);
        return (MmgObj) ret;
    }

    /**
     * Sets the background object of this game screen
     * centered horizontally and vertically.
     * 
     * @param b     The object to use as the background.
     */
    @Override
    public void SetCenteredBackground(MmgObj b) {
        MmgHelper.CenterHorAndVert(b);
        super.SetBackground(b);
    }

    /**
     * Gets the loading bar object of this class.
     * 
     * @return      A loading bar. 
     */
    public MmgLoadingBar GetLoadingBar() {
        return loadingBar;
    }

    /**
     * Get the loading bar's bottom offset.
     * 
     * @return      The loading bar's bottom offset. 
     */
    public float GetLoadingBarOffsetBottom() {
        return loadingBarOffsetBottom;
    }

    /**
     * Sets the loading bar and loading bar's bottom offset.
     * 
     * @param lb            The loading bar to use for this game screen.
     * @param lBarOff       The bottom offset to use for the loading bar.
     */
    public void SetLoadingBar(MmgLoadingBar lb, float lBarOff) {
        loadingBar = lb;
        if(loadingBar != null) {
            MmgHelper.CenterHorAndBot(loadingBar);
            loadingBar.GetPosition().SetY(GetPosition().GetY() + GetHeight() - loadingBar.GetHeight() - ((float) GetHeight() * (float) loadingBarOffsetBottom));
            loadingBar.GetLoadingBarBack().SetPosition(loadingBar.GetPosition());
            loadingBar.GetLoadingBarFront().SetPosition(loadingBar.GetPosition());
        }
    }

    /**
     * The base drawing class for this game screen.
     * 
     * @param p     The MmgPen object that is used to draw this screen. 
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if(GetIsVisible() == true) {
            super.MmgDraw(p);
            
            if(loadingBar != null) {
                loadingBar.MmgDraw(p);
            }
        }else {
            //do nothing
        }
    }
}

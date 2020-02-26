package com.middlemind.Odroid;

import com.middlemind.Odroid.GamePanel.GameStates;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp.MmgBmpDrawMode;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgLoadingBar;
import net.middlemind.MmgGameApiJava.MmgBase.MmgLoadingScreen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;

/**
 * A game screen object, ScreenLoading, that extends the MmgLoadingScreen base
 * class. This game screen is for displaying a loading screen. Created on August
 * 1, 2015, 10:57 PM by Middlemind Games. Created by Middlemind Games.
 *
 * @author Victor G. Brusca
 */
public class ScreenLoading extends MmgLoadingScreen implements LoadResourceUpdateHandler {

    /**
     * Event load complete id.
     */
    public static int EVENT_LOAD_COMPLETE = 0;

    /**
     * Wrapper class for running a worker thread.
     */
    protected RunResourceLoad datLoad;

    /**
     * Helper class for displaying a loading bar.
     */
    protected MmgLoadingBar loadingBar;

    /**
     * The game state this screen has.
     */
    protected final GameStates state;

    /**
     * Event handler for firing generic events. Events would fire when the
     * screen has non UI actions to broadcast.
     */
    protected GenericEventHandler handler;

    /**
     * The GamePanel that owns this game screen. Usually a JPanel instance that
     * holds a reference to this game screen object.
     */
    protected final GamePanel owner;

    protected long slowDown;
    
    /**
     * Constructor, sets the loading bar, the loading bar offset, the game state
     * of this game screen, and the GamePanel that owns this game screen.
     *
     * @param LoadingBar A loading bar object, MmgLoadingBar, to use as this
     * screen's loading bar.
     * @param lBarOff An offset used in drawing the loading bar.
     * @param State The game state of this game screen.
     * @param Owner The owner of this game screen.
     */
    public ScreenLoading(MmgLoadingBar LoadingBar, float lBarOff, GameStates State, GamePanel Owner) {
        super(LoadingBar, lBarOff);
        pause = false;
        ready = false;
        state = State;
        owner = Owner;
        slowDown = 0;
    }

    /**
     * Constructor, sets the game state associated with this screen, and sets
     * the owner GamePanel instance.
     *
     * @param State The game state of this game screen.
     * @param Owner The owner of this game screen.
     */
    public ScreenLoading(GameStates State, GamePanel Owner) {
        pause = false;
        ready = false;
        state = State;
        owner = Owner;
        slowDown = 0;
    }

    public long GetSlowDown() {
        return slowDown;
    }

    public void SetSlowDown(long l) {
        slowDown = l;
    }
   
    /**
     * Sets a generic event handler that will receive generic events from this
     * object.
     *
     * @param Handler A class that implements the GenericEventHandler interface.
     */
    public void SetGenericEventHandler(GenericEventHandler Handler) {
        handler = Handler;
    }

    public GenericEventHandler GetGenericEventHandler() {
        return handler;
    }

    public RunResourceLoad GetLoader() {
        return datLoad;
    }

    public void SetLoader(RunResourceLoad DatLoad) {
        datLoad = DatLoad;
    }

    public MmgLoadingBar GetLoadingBar() {
        return loadingBar;
    }

    public void SetLoadingBar(MmgLoadingBar LoadingBar) {
        loadingBar = LoadingBar;
    }
    
    /**
     * Loads all the resources needed to display this game screen.
     */
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        pause = true;
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());

        MmgBmp tB = null;
        MmgBmp tB1 = null;
        MmgPen p;
        MmgLoadingBar lb = null;
        int lbOffSet = 5;
        p = new MmgPen();
        p.SetCacheOn(false);

        tB = Helper.GetBasicBmp("../cfg/drawable/odroid_logo2.png");
        if (tB != null) {
            SetCenteredBackground(tB);
        }

        tB = Helper.GetBasicBmp("../cfg/drawable/loading_bar.png");
        tB1 = Helper.GetBasicBmp("../cfg/drawable/blue_square.png");
        if (tB1 != null) {
            tB1.DRAW_MODE = MmgBmpDrawMode.DRAW_BMP_FULL;
        }

        if (tB != null && tB1 != null) {
            lb = new MmgLoadingBar(tB1, tB);
            lb.SetMmgColor(null);
            lb.SetWidth(tB.GetWidth() - MmgHelper.ScaleValue(10));
            lb.SetHeight(tB.GetHeight() - MmgHelper.ScaleValue(12));
            lb.SetFillAmt(0.0f);
            lb.SetPaddingX(MmgHelper.ScaleValue(8));
            lb.SetPaddingY(MmgHelper.ScaleValue(4));
            lb.SetFillHeight(tB.GetHeight() - MmgHelper.ScaleValue(10));
            lb.SetFillWidth(tB.GetWidth() - MmgHelper.ScaleValue(12));
            loadingBar = lb;
            super.SetLoadingBar(lb, lbOffSet);
        }

        ready = true;
        pause = false;
    }

    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetLoadingBar(null, 0);
        SetBackground(null);
        ClearObjs();

        loadingBar = null;
        datLoad = null;
        ready = false;
    }

    /**
     * Returns the game state of this game screen.
     *
     * @return The game state of this game screen.
     */
    public GameStates GetGameState() {
        return state;
    }

    /**
     * The main drawing routine.
     *
     * @param p An MmgPen object to use for drawing this game screen.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (pause == false && GetIsVisible() == true) {
            super.MmgDraw(p);
        } else {
            //do nothing
        }
    }

    /**
     * Loads the DAT file data from the file system.
     *
     * @return A byte array representation of the DAT file.
     */
    protected boolean GetResourceFileData() {
        try {
            Helper.wr("GetResourceFileData: " + GameSettings.AUTO_IMAGE_LOAD_DIR);
            File ald = new File(GameSettings.AUTO_IMAGE_LOAD_DIR);
            File[] files = ald.listFiles();
            
            if(files != null && files.length > 0) {
                return true;
            }
            
        } catch (Exception e) {
            Helper.wrErr(e);
        }

        return false;
    }

    /**
     * Starts the DAT loading worker thread.
     */
    public void StartDatLoad() {
        if (GetResourceFileData()) {
            datLoad = new RunResourceLoad();
            datLoad.SetSlowDown(slowDown);
            datLoad.SetUpdateHandler(this);

            Runnable r = datLoad;
            Thread t = new Thread(r);
            t.start();
        } else {
            Helper.wr("No data found to load.");
        }
    }

    /**
     * Stops the DAT load process.
     */
    public void StopDatLoad() {
        if (datLoad != null) {
            datLoad.StopLoad();
        }
    }

    /**
     * Handles load DAT update events, LoadDatUpdateMessage. Fires a generic
     * event when the load has completed.
     *
     * @param obj The load DAT update message sent.
     */
    @Override
    public void HandleUpdate(LoadResourceUpdateMessage obj) {
        if (obj != null) {
            float prct = (float) obj.GetPos() / (float) obj.GetLen();

            if (loadingBar != null) {
                loadingBar.SetFillAmt(prct);
            }

            if (GetLoadComplete() == true) {
                if (handler != null) {
                    handler.HandleGenericEvent(new GenericEventMessage(ScreenLoading.EVENT_LOAD_COMPLETE, null, GetGameState()));
                }
            }
            
            Helper.wr("LoadingScreen POS: " + obj.GetPos() + " LEN: " + obj.GetLen() + " PRCT: " + prct + " LR: " + GetLoadResult() + " LC: " + GetLoadComplete());
        }
    }

    /**
     * Gets the DAT read result value.
     *
     * @return True if the read was successful, false otherwise.
     */
    public boolean GetLoadResult() {
        if (datLoad != null) {
            return datLoad.GetReadResult();
        } else {
            Helper.wr("datLoad is NULL");
            return false;
        }
    }

    /**
     * Gets the DAT load complete value.
     *
     * @return True if the load was successful, false otherwise.
     */
    public boolean GetLoadComplete() {
        if (datLoad != null) {
            return datLoad.GetReadComplete();
        } else {
            Helper.wr("datLoad is NULL");
            return false;
        }
    }
}
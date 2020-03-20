package com.middlemind.Odroid_Tutorial2_Pong;

import com.middlemind.Odroid.GamePanel.GameStates;
import com.middlemind.Odroid.GameSettings;
import com.middlemind.Odroid.GenericEventMessage;
import com.middlemind.Odroid.Helper;
import com.middlemind.Odroid.Screen;
import java.util.Random;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmp;
import net.middlemind.MmgGameApiJava.MmgBase.MmgBmpScaler;
import net.middlemind.MmgGameApiJava.MmgBase.MmgColor;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFont;
import net.middlemind.MmgGameApiJava.MmgBase.MmgFontData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgPen;
import net.middlemind.MmgGameApiJava.MmgBase.MmgScreenData;
import net.middlemind.MmgGameApiJava.MmgBase.MmgHelper;
import net.middlemind.MmgGameApiJava.MmgBase.MmgSound;
import net.middlemind.MmgGameApiJava.MmgBase.MmgVector2;

/**
 * A game screen object, ScreenGame, that extends the MmgGameScreen base
 * class. This class is for testing new UI widgets, etc.
 *
 * @author Victor G. Brusca
 * 03/15/2020
 */
public class ScreenGame extends Screen {

    /**
     * The game state this screen has.
     */
    protected final GameStates gameState;

    /**
     * The GamePanel that owns this game screen. Usually a JPanel instance that
     * holds a reference to this game screen object.
     */
    protected final GamePanel owner;
        
    private MmgBmp bground;
    private MmgBmp paddle1;
    private MmgBmp paddle2;
    private MmgVector2 paddle1Pos;
    private MmgVector2 paddle2Pos;    
    
    private enum NumberState {
        NONE,
        NUMBER_1,
        NUMBER_2,
        NUMBER_3
    };
    
    private enum State {
        NONE,
        SHOW_GAME,
        SHOW_COUNT_DOWN,
        SHOW_GAME_OVER,
        SHOW_GAME_EXIT
    };
    
    private State statePrev = State.NONE;
    private State state = State.NONE;
    
    private NumberState numberState = NumberState.NONE;
    private long timeNumberMs = 0L;
    private long timeNumberDisplayMs = 1000;
    private long timeTmpMs = 0L;
    
    private MmgSound bounceNorm;
    private MmgSound bounceSuper;
    
    private MmgBmp ball;
    private MmgVector2 ballPos;
    private int ballSpeedMin = 30;
    private int ballSpeedMax = 120;
    private int ballCurrentSpeedX = ballSpeedMin;
    private int ballCurrentSpeedY = ballSpeedMin;    
    private int ballMovePerFrameX = (int)(ballCurrentSpeedX * (MmgPongClone.FPS/60.0f));
    private int ballMovePerFrameY = (int)(ballCurrentSpeedY * (MmgPongClone.FPS/60.0f));
    
    private MmgBmp number1;
    private MmgBmp number2;
    private MmgBmp number3;    
    
    private MmgFont scoreLeft;
    private MmgFont scoreRight;
    private MmgFont exit;
    
    private float paddleAccelMin = 16.0f;
    private float paddleAccelMax = 40.0f;
    private float paddle1CurrentAccel = paddleAccelMin;
    private float paddle2CurrentAccel = paddleAccelMin;    
    private float paddleAccelChange = 8.0f;
    
    private int paddleSpeedMin = 30;
    private int paddleSpeedMax = 60;
    private int paddle1CurrentSpeed = paddleSpeedMin;
    private int paddle2CurrentSpeed = paddleSpeedMin;    
    
    private int paddle1MovePerFrame = (int)(paddle1CurrentSpeed * (MmgPongClone.FPS/60.0f));
    private int paddle2MovePerFrame = (int)(paddle2CurrentSpeed * (MmgPongClone.FPS/60.0f));
    private boolean paddle1MoveUp = false;
    private boolean paddle1MoveDown = false;    
    
    private Random rand;
    private int dir;
    
    /**
     * Constructor, sets the game state associated with this screen, and sets
     * the owner GamePanel instance.
     *
     * @param State The game state of this game screen.
     * @param Owner The owner of this game screen.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public ScreenGame(GameStates State, GamePanel Owner) {
        super(State, Owner);
        pause = false;
        ready = false;
        gameState = State;
        owner = Owner;
        Helper.wr("Odroid.ScreenGame: Constructor");
    }

    /**
     * Loads all the resources needed to display this game screen.
     */
    @Override
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        pause = true;
        
        rand = new Random((int)System.currentTimeMillis());
        
        SetHeight(MmgScreenData.GetGameHeight());
        SetWidth(MmgScreenData.GetGameWidth());
        SetPosition(MmgScreenData.GetPosition());

        String imgId = "";
        String sndId = "";
        MmgSound sval = null;
        
        imgId = "game_board.png";
        bground = MmgHelper.GetBasicCachedBmp(imgId);
        bground = MmgBmpScaler.ScaleMmgBmpToGameScreen(bground, false);
        MmgHelper.CenterHorAndVert(bground);
        AddObj(bground);
        
        sndId = "jump1.wav";
        sval = MmgHelper.GetBasicCachedSound(sndId);
        bounceNorm = sval;        
        
        sndId = "jump2.wav";
        sval = MmgHelper.GetBasicCachedSound(sndId);
        bounceSuper = sval;                
        
        paddle1 = MmgHelper.CreateFilledBmp(MmgHelper.ScaleValue(20), h/5, MmgColor.GetWhite());
        paddle1.SetPosition(MmgHelper.ScaleValue(20), GetY() + (h - paddle1.GetHeight())/2);
        paddle1Pos = paddle1.GetPosition();
        AddObj(paddle1);
                
        paddle2 = MmgHelper.CreateFilledBmp(MmgHelper.ScaleValue(20), h/5, MmgColor.GetWhite());
        paddle2.SetPosition((w - paddle2.GetWidth() - MmgHelper.ScaleValue(20)), GetY() + (h - paddle1.GetHeight())/2);        
        paddle2Pos = paddle2.GetPosition();
        AddObj(paddle2);
        
        imgId = "pong_ball.png";
        ball = MmgHelper.GetBasicCachedBmp(imgId);
        MmgHelper.CenterHorAndVert(ball);
        ball.SetIsVisible(false);
        ballPos = ball.GetPosition();
        AddObj(ball);        
        
        scoreLeft = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreLeft.SetText("00");
        scoreLeft.SetMmgColor(MmgColor.GetRed());
        scoreLeft.SetPosition(MmgHelper.ScaleValue(10) + paddle1.GetX() + paddle1.GetWidth(), GetY() + scoreLeft.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(scoreLeft);
        
        scoreRight = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreRight.SetText("00");
        scoreRight.SetMmgColor(MmgColor.GetRed());
        scoreRight.SetPosition(w - scoreRight.GetWidth() - MmgHelper.ScaleValue(10) - paddle1.GetX() - paddle2.GetWidth(), GetY() + scoreRight.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(scoreRight);        
        
        exit = MmgFontData.CreateDefaultBoldMmgFontLg();
        exit.SetText("Press B to Exit");
        exit.SetMmgColor(MmgColor.GetRed());
        exit.SetPosition((w - exit.GetWidth())/2, GetY() + exit.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(exit);        
                        
        imgId = "num_1_lrg.png";
        number1 = MmgHelper.GetBasicCachedBmp(imgId);
        MmgHelper.CenterHorAndVert(number1);
        number1.SetIsVisible(false);
        AddObj(number1);
        
        imgId = "num_2_lrg.png";
        number2 = MmgHelper.GetBasicCachedBmp(imgId);
        MmgHelper.CenterHorAndVert(number2);
        number2.SetIsVisible(false);
        AddObj(number2);

        imgId = "num_3_lrg.png";
        number3 = MmgHelper.GetBasicCachedBmp(imgId);
        MmgHelper.CenterHorAndVert(number3);
        number3.SetIsVisible(false);
        AddObj(number3);       
        
        SetState(State.SHOW_COUNT_DOWN);
        
        ready = true;
        pause = false;
    }

    private void SetPaddle1MovePerFrame(int speed) {
        paddle1MovePerFrame = (int)(speed * (MmgPongClone.FPS/60.0f));
    }
    
    private void SetPaddle2MovePerFrame(int speed) {
        paddle2MovePerFrame = (int)(speed * (MmgPongClone.FPS/60.0f));
    }
    
    private void SetBallMovePerFrameX(int speed) {
        ballMovePerFrameX = (int)(speed * (MmgPongClone.FPS/60.0f));
    }

    private void SetBallMovePerFrameY(int speed) {
        ballMovePerFrameY = (int)(speed * (MmgPongClone.FPS/60.0f));
    }    
    
    @Override
    public boolean ProcessScreenPress(MmgVector2 v) {
        return ProcessScreenPress(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessScreenPress(int x, int y) {
        return true;
    }

    @Override
    public boolean ProcessScreenRelease(MmgVector2 v) {
        return ProcessScreenPress(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessScreenRelease(int x, int y) {
        return true;
    }
    
    @Override
    public boolean ProcessAClick() {
        return true;
    }
    
    @Override
    public boolean ProcessBClick() {
        return true;
    }
    
    @Override
    public void ProcessDebugClick() {

    }

    @Override
    public boolean ProcessDpadPress(int dir) {
        //Helper.wr("ProcessDpadPress: " + dir);
        if(dir == GameSettings.DOWN) {
            paddle1CurrentAccel += paddleAccelChange;
            if(paddle1CurrentAccel > paddleAccelMax) {
                paddle1CurrentAccel = paddleAccelMax;
            }
            
            paddle1CurrentSpeed = paddle1MovePerFrame + (int)(paddle1CurrentAccel);
            if(paddle1CurrentSpeed > paddleSpeedMax) {
                paddle1CurrentSpeed = paddleSpeedMax;
            }
            
            paddle1MoveDown = true;
            return true;
            
        } else if(dir == GameSettings.UP) {
            paddle1CurrentAccel += paddleAccelChange;
            if(paddle1CurrentAccel > paddleAccelMax) {
                paddle1CurrentAccel = paddleAccelMax;
            }
            
            paddle1CurrentSpeed = paddle1MovePerFrame + (int)(paddle1CurrentAccel);              
            if(paddle1CurrentSpeed > paddleSpeedMax) {
                paddle1CurrentSpeed = paddleSpeedMax;
            }            
            
            paddle1MoveUp = true;
            return true;
            
        }
        
        return false;
    }

    @Override
    public boolean ProcessDpadRelease(int dir) {
        Helper.wr("ProcessDpadRelease: " + dir);        
        if(dir == GameSettings.DOWN) {
            paddle1CurrentAccel = paddleAccelMin;
            paddle1CurrentSpeed = 0;
            paddle1MoveDown = false;
            return true;
            
        } else if(dir == GameSettings.UP) {
            paddle1CurrentAccel = paddleAccelMin;
            paddle1CurrentSpeed = 0;
            paddle1MoveUp = false;
            return true;
            
        }
        
        return false;
    }
    
    @Override
    public boolean ProcessDpadClick(int dir) {
        return true;
    }
    
    @Override
    public boolean ProcessScreenClick(MmgVector2 v) {
        return ProcessScreenClick(v.GetX(), v.GetY());
    }

    @Override
    public boolean ProcessScreenClick(int x, int y) {        
        return false;
    }    

    private void SetState(State in) {
        Helper.wr("SetState: " + in);
        
        //clean up prev state
        switch(statePrev) {
            case NONE:
                break;
                
            case SHOW_GAME:
                break;
                
            case SHOW_COUNT_DOWN:
                break;
                
            case SHOW_GAME_OVER:
                break;
                
            case SHOW_GAME_EXIT:
                break;                
        }
        
        statePrev = state;
        state = in;
        
        switch(state) {
            case NONE:
                bground.SetIsVisible(false);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);
                scoreLeft.SetIsVisible(false);
                scoreRight.SetIsVisible(false);
                
                ball.SetIsVisible(false);                
                MmgHelper.CenterHorAndVert(ball);
                ballPos = ball.GetPosition();
                ballCurrentSpeedX = ballSpeedMin;
                ballCurrentSpeedY = ballSpeedMin;

                paddle1.SetIsVisible(false);                
                MmgHelper.CenterVert(paddle1);
                paddle1Pos = paddle1.GetPosition();
                paddle1CurrentAccel = paddleAccelMin;
                paddle1CurrentSpeed = paddleSpeedMin;

                paddle2.SetIsVisible(false);                
                MmgHelper.CenterVert(paddle2);
                paddle2Pos = paddle2.GetPosition();
                paddle2CurrentAccel = paddleAccelMin;
                paddle2CurrentSpeed = paddleSpeedMin;                
                
                dirty = false;                
                break;
                
            case SHOW_GAME:
                paddle1.SetIsVisible(true);
                paddle2.SetIsVisible(true);
                bground.SetIsVisible(true);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);
                scoreLeft.SetIsVisible(true);
                scoreRight.SetIsVisible(true);
                ball.SetIsVisible(true);
                
                if(rand.nextInt(11) % 2 == 0) {
                    dir = 1;
                } else {
                    dir = -1;
                }
                
                SetBallMovePerFrameX((ballSpeedMin + rand.nextInt(ballSpeedMin)) * dir);
                SetBallMovePerFrameY((ballSpeedMin + rand.nextInt(ballSpeedMin)) * dir);
                SetPaddle1MovePerFrame(paddle1CurrentSpeed);
                SetPaddle2MovePerFrame(paddle2CurrentSpeed);                
                dirty = true;
                break;
                
            case SHOW_COUNT_DOWN:
                paddle1.SetIsVisible(false);
                paddle2.SetIsVisible(false);
                bground.SetIsVisible(false);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);                
                scoreLeft.SetIsVisible(true);
                scoreRight.SetIsVisible(true);
                ball.SetIsVisible(false);
                numberState = NumberState.NONE;
                dirty = true;
                break;
                
            case SHOW_GAME_OVER:
                dirty = true;                
                break;
                
            case SHOW_GAME_EXIT:
                dirty = true;                
                break;                
        }        
    }
    
    @Override
    public void DrawScreen() {
        //run each game frame
        pause = true;
        
        switch(state) {
            case NONE:
                break;
                
            case SHOW_COUNT_DOWN:
                switch(numberState) {
                    case NONE:
                        timeNumberMs = System.currentTimeMillis();
                        numberState = NumberState.NUMBER_3;
                        number1.SetIsVisible(false);
                        number2.SetIsVisible(false);
                        number3.SetIsVisible(true);                        
                        break;
                        
                    case NUMBER_1:
                        timeTmpMs = System.currentTimeMillis();
                        if(timeTmpMs - timeNumberMs >= timeNumberDisplayMs) {
                            timeNumberMs = timeTmpMs;
                            numberState = NumberState.NONE;
                            number1.SetIsVisible(false);
                            number2.SetIsVisible(false);
                            number3.SetIsVisible(false);
                            SetState(State.SHOW_GAME);
                            bounceNorm.Play();                            
                        }
                        break;
                        
                    case NUMBER_2:
                        timeTmpMs = System.currentTimeMillis();
                        if(timeTmpMs - timeNumberMs >= timeNumberDisplayMs) {
                            timeNumberMs = timeTmpMs;
                            numberState = NumberState.NUMBER_1;                            
                            number1.SetIsVisible(true);
                            number2.SetIsVisible(false);
                            number3.SetIsVisible(false);
                            bounceNorm.Play();                            
                        }
                        break;
                        
                    case NUMBER_3:
                        timeTmpMs = System.currentTimeMillis();
                        if(timeTmpMs - timeNumberMs >= timeNumberDisplayMs) {
                            timeNumberMs = timeTmpMs;
                            numberState = NumberState.NUMBER_2;
                            number1.SetIsVisible(false);
                            number2.SetIsVisible(true);
                            number3.SetIsVisible(false);
                            bounceNorm.Play();
                        }
                        break;
                        
                }
                break;
                
            case SHOW_GAME:
                if(paddle1MoveUp) {
                    if(paddle1Pos.GetY() - paddle1CurrentSpeed < GetY()) {
                        paddle1Pos.SetY(GetY());                
                    } else {
                        paddle1Pos.SetY(paddle1Pos.GetY() - paddle1CurrentSpeed);
                    }
                    
                } else if(paddle1MoveDown) {
                    if(paddle1Pos.GetY() + paddle1.GetHeight() + paddle1CurrentSpeed > GetY() + GetHeight()) {
                        paddle1Pos.SetY(GetY() + GetHeight() - paddle1.GetHeight());
                    } else {
                       paddle1Pos.SetY(paddle1Pos.GetY() + paddle1CurrentSpeed);                            
                    }
                    
                }
                
                ballPos.SetX(ballPos.GetX() + ballMovePerFrameX);
                ballPos.SetY(ballPos.GetY() + ballMovePerFrameX);                
                
                break;
        }
        
        pause = false;
    }
    
    /**
     * Unloads resources needed to display this game screen.
     */
    public void UnloadResources() {
        pause = true;
        SetBackground(null);
        
        paddle1 = null;
        paddle1Pos = null;
        paddle2 = null;
        paddle2Pos = null;
        scoreLeft = null;
        scoreRight = null;
        ball = null;
        ballPos = null;
        bounceNorm = null;
        bounceSuper = null;
        exit = null;
        
        ClearObjs();
        ready = false;
    }

    /**
     * Returns the game state of this game screen.
     *
     * @return The game state of this game screen.
     */
    public GameStates GetGameState() {
        return gameState;
    }

    /**
     * The main drawing routine.
     *
     * @param p An MmgPen object to use for drawing this game screen.
     */
    @Override
    public void MmgDraw(MmgPen p) {
        if (pause == false && GetIsVisible() == true) {            
            //super.MmgDraw(p);                        
            super.GetObjects().MmgDraw(p);
        } else {
            //do nothing
        }
    }

    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        //handle generic event
    }
}

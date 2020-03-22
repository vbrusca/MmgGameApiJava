package com.middlemind.Odroid_Tutorial2_Pong;

import com.middlemind.Odroid.GamePanel.GameStates;
import com.middlemind.Odroid.GameSettings;
import com.middlemind.Odroid.GenericEventMessage;
import com.middlemind.Odroid.Helper;
import com.middlemind.Odroid.Screen;
import java.util.Random;
import net.middlemind.MmgGameApiJava.MmgBase.Mmg9Slice;
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
        SHOW_COUNT_DOWN_IN_GAME,        
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
    
    private MmgBmp bground;
    private MmgBmp paddle1;
    private MmgBmp paddle2;
    private MmgVector2 paddle1Pos;
    private MmgVector2 paddle2Pos;    
    
    private MmgBmp ball;
    private MmgVector2 ballPos;
    private int ballMovePerFrameMin = GetSpeedPerFrame(150);
    private int ballMovePerFrameMax = GetSpeedPerFrame(425);
    private int ballDirX = 1;
    private int ballDirY = 1;
    private int ballMovePerFrameX = 0;
    private int ballMovePerFrameY = 0;
    
    private MmgBmp number1;
    private MmgBmp number2;
    private MmgBmp number3;    
    
    private MmgFont scoreLeft;
    private int score1 = 0;
    private MmgFont scoreRight;
    private int score2 = 0;
    private MmgFont exit;
    private MmgBmp exitBground;
        
    private int paddle1MovePerFrame = GetSpeedPerFrame(400);
    private boolean paddle1MoveUp = false;
    private boolean paddle1MoveDown = false;
    
    private int paddle2MovePerFrame = GetSpeedPerFrame(400);
    private boolean paddle2MoveUp = false;
    private boolean paddle2MoveDown = false;        
    
    private Random rand;
    private int ballNewX;
    private int ballNewY;
    private boolean bounced = false;
    private MmgVector2 screenPos;
    
    private int lastX;
    private int lastY;
    private boolean mousePos = true;
    
    private MmgBmp bgroundPopupSrc;
    private Mmg9Slice bgroundPopup;
    private MmgFont txtOk;
    private MmgFont txtCancel;
    
    private int padding = MmgHelper.ScaleValue(4);
    
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
        screenPos = GetPosition();
        
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
        
        imgId = "pong_ball.png";
        ball = MmgHelper.GetBasicCachedBmp(imgId);
        MmgHelper.CenterHorAndVert(ball);
        ball.SetIsVisible(false);
        ballPos = ball.GetPosition();
        AddObj(ball);        
        
        paddle1 = MmgHelper.CreateFilledBmp(MmgHelper.ScaleValue(20), h/6, MmgColor.GetWhite());
        paddle1.SetPosition(MmgHelper.ScaleValue(20), screenPos.GetY() + (h - paddle1.GetHeight())/2);
        paddle1Pos = paddle1.GetPosition();
        AddObj(paddle1);
                
        paddle2 = MmgHelper.CreateFilledBmp(MmgHelper.ScaleValue(20), h/6, MmgColor.GetWhite());
        paddle2.SetPosition((w - paddle2.GetWidth() - MmgHelper.ScaleValue(20)), screenPos.GetY() + (h - paddle1.GetHeight())/2);        
        paddle2Pos = paddle2.GetPosition();
        AddObj(paddle2);
                
        scoreLeft = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreLeft.SetText("00");
        scoreLeft.SetMmgColor(MmgColor.GetRed());
        scoreLeft.SetPosition(MmgHelper.ScaleValue(10) + paddle1.GetX() + paddle1.GetWidth(), screenPos.GetY() + scoreLeft.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(scoreLeft);
        
        scoreRight = MmgFontData.CreateDefaultBoldMmgFontLg();
        scoreRight.SetText("00");
        scoreRight.SetMmgColor(MmgColor.GetRed());
        scoreRight.SetPosition(w - scoreRight.GetWidth() - MmgHelper.ScaleValue(10) - paddle1.GetX() - paddle2.GetWidth(), screenPos.GetY() + scoreRight.GetHeight() + MmgHelper.ScaleValue(5));
        AddObj(scoreRight);        
                
        exit = MmgFontData.CreateDefaultBoldMmgFontLg();
        exit.SetText("Press B to Exit");
        exit.SetMmgColor(MmgColor.GetRed());
        exit.SetPosition((w - exit.GetWidth())/2, screenPos.GetY() + exit.GetHeight() + MmgHelper.ScaleValue(5));
        
        exitBground = MmgHelper.CreateFilledBmp(exit.GetWidth() + (padding * 2), exit.GetHeight() + (padding * 2), MmgColor.GetBlack());
        exitBground.SetPosition(exit.GetX() - padding, exit.GetY() - exit.GetHeight());
        AddObj(exitBground);        
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
        
        int totalWidth = 300;
        int totalHeight = 120;        
        bgroundPopupSrc = MmgHelper.GetBasicCachedBmp("popup_window_base.png");
        
        bgroundPopup = new Mmg9Slice(16, bgroundPopupSrc, totalWidth, totalHeight);
        bgroundPopup.SetPosition(MmgVector2.GetOriginVec());
        bgroundPopup.SetWidth(totalWidth);
        bgroundPopup.SetHeight(totalHeight);
        MmgHelper.CenterHorAndVert(bgroundPopup);
        AddObj(bgroundPopup);
        bgroundPopup.SetIsVisible(false);
        
        SetState(State.SHOW_COUNT_DOWN);        
        ready = true;
        pause = false;
    }

    private static int GetSpeedPerFrame(int speed) {
        return (int)(speed/(MmgPongClone.FPS - 4));        
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
        owner.SwitchGameState(GameStates.MAIN_MENU);
        return true;
    }
    
    @Override
    public void ProcessDebugClick() {

    }

    @Override
    public boolean ProcessKeyPress(char c) {
        if(c == 'x' || c == 'X') {            
            paddle1MoveUp = false;
            paddle1MoveDown = true;
            return true;
            
        } else if(c == 's' || c == 'S') {            
            paddle1MoveUp = true;
            paddle1MoveDown = false;            
            return true;
            
        }
        
        return false;        
    }
    
    @Override
    public boolean ProcessKeyRelease(char c) {
        if(c == 'x' || c == 'X') {
            paddle1MoveDown = false;
            return true;
            
        } else if(c == 's' || c == 'S') {
            paddle1MoveUp = false;
            return true;
            
        }
        
        return false;       
    }
    
    @Override
    public boolean ProcessKeyClick(char c) {
        return false;
    }    
    
    @Override
    public boolean ProcessDpadPress(int dir) {
        if(dir == GameSettings.DOWN) {            
            paddle2MoveUp = false;
            paddle2MoveDown = true;
            return true;
            
        } else if(dir == GameSettings.UP) {            
            paddle2MoveUp = true;
            paddle2MoveDown = false;            
            return true;
                        
        }
        
        return false;
    }

    @Override
    public boolean ProcessDpadRelease(int dir) {
        if(dir == GameSettings.DOWN) {
            paddle2MoveDown = false;
            return true;
            
        } else if(dir == GameSettings.UP) {
            paddle2MoveUp = false;
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
        return true;
    }    
    
    public boolean ProcessMouseMove(int x, int y) {        
        if(y >= screenPos.GetY() && y <= (screenPos.GetY() + GetHeight() - paddle1.GetHeight())) {
            lastX = x;
            lastY = y;
            mousePos = true;
        }

        return true;
    }    
    
    private void ResetGame() {
        ball.SetIsVisible(true);                
        MmgHelper.CenterHorAndVert(ball);
        ballPos = ball.GetPosition();
        ballMovePerFrameX = ballMovePerFrameMin;
        ballMovePerFrameY = ballMovePerFrameMin;

        paddle1.SetIsVisible(true);                
        MmgHelper.CenterVert(paddle1);
        paddle1Pos = paddle1.GetPosition();
        paddle1MoveDown = false;
        paddle1MoveUp = false;

        paddle2.SetIsVisible(true);                
        MmgHelper.CenterVert(paddle2);
        paddle2Pos = paddle2.GetPosition();
        paddle2MoveDown = false;
        paddle2MoveUp = false;                

        lastX = 0;
        lastY = 0;
        mousePos = false;        
    }
    
    private void SetState(State in) {        
        //clean up prev state
        switch(statePrev) {
            case NONE:
                break;
                
            case SHOW_GAME:
                break;
                
            case SHOW_COUNT_DOWN:
                break;
                
            case SHOW_COUNT_DOWN_IN_GAME:
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
                ResetGame();
                
                ball.SetIsVisible(false);
                paddle1.SetIsVisible(false);
                paddle2.SetIsVisible(false);                
                bground.SetIsVisible(false);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);
                scoreLeft.SetIsVisible(false);
                scoreRight.SetIsVisible(false);
                                                
                score1 = 0;
                score2 = 0;
                
                pause = false;                
                dirty = false;                
                break;
                
            case SHOW_GAME:
                ResetGame();
                
                ball.SetIsVisible(true);                
                paddle1.SetIsVisible(true);
                paddle2.SetIsVisible(true);
                bground.SetIsVisible(true);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);
                scoreLeft.SetIsVisible(true);
                scoreRight.SetIsVisible(true);
                
                if(rand.nextInt(11) % 2 == 0) {
                    ballDirX = 1;
                } else {
                    ballDirX = -1;
                }
                ballMovePerFrameX = ballMovePerFrameMin;
                
                if(rand.nextInt(11) % 2 == 0) {
                    ballDirY = 1;
                } else {
                    ballDirY = -1;
                }         
                ballMovePerFrameY = ballMovePerFrameMin;
                                
                pause = false;
                dirty = true;
                break;
                
            case SHOW_COUNT_DOWN_IN_GAME:
                ball.SetIsVisible(true);               
                paddle1.SetIsVisible(true);
                paddle2.SetIsVisible(true);
                bground.SetIsVisible(true);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);                
                scoreLeft.SetIsVisible(true);
                scoreRight.SetIsVisible(true);
                
                numberState = NumberState.NONE;
                pause = false;
                dirty = true;                
                break;
                
            case SHOW_COUNT_DOWN:
                ball.SetIsVisible(false);
                paddle1.SetIsVisible(false);
                paddle2.SetIsVisible(false);
                bground.SetIsVisible(false);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);                
                scoreLeft.SetIsVisible(true);
                scoreRight.SetIsVisible(true);
                
                numberState = NumberState.NONE;
                pause = false;
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
    
    private void SetScore1Text(int score) {
        String tmp = score + "";
        if(tmp.length() != 2) {
            tmp = "0" + tmp;
        }
        scoreLeft.SetText(tmp);
    }
    
    private void SetScore2Text(int score) {
        String tmp = score + "";
        if(tmp.length() != 2) {
            tmp = "0" + tmp;
        }
        scoreRight.SetText(tmp);
    }    
    
    @Override
    public void DrawScreen() {
        //run each game frame
        pause = true;
        
        switch(state) {
            case NONE:
                break;
                
            case SHOW_COUNT_DOWN_IN_GAME:
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
                if(mousePos) {
                    paddle1Pos.SetY(lastY);
                }
                
                if(paddle1MoveUp) {
                    if(paddle1Pos.GetY() - paddle1MovePerFrame < screenPos.GetY()) {
                        paddle1Pos.SetY(screenPos.GetY());                
                    } else {
                        paddle1Pos.SetY(paddle1Pos.GetY() - paddle1MovePerFrame);
                    }
                    
                } else if(paddle1MoveDown) {
                    if(paddle1Pos.GetY() + paddle1.GetHeight() + paddle1MovePerFrame > screenPos.GetY() + GetHeight()) {
                        paddle1Pos.SetY(screenPos.GetY() + GetHeight() - paddle1.GetHeight());
                    } else {
                       paddle1Pos.SetY(paddle1Pos.GetY() + paddle1MovePerFrame);                            
                    }
                    
                }
                
                if(paddle2MoveUp) {
                    if(paddle2Pos.GetY() - paddle2MovePerFrame < screenPos.GetY()) {
                        paddle2Pos.SetY(screenPos.GetY());                
                    } else {
                        paddle2Pos.SetY(paddle2Pos.GetY() - paddle2MovePerFrame);
                    }
                    
                } else if(paddle2MoveDown) {
                    if(paddle2Pos.GetY() + paddle2.GetHeight() + paddle2MovePerFrame > screenPos.GetY() + GetHeight()) {
                        paddle2Pos.SetY(screenPos.GetY() + GetHeight() - paddle2.GetHeight());
                    } else {
                       paddle2Pos.SetY(paddle2Pos.GetY() + paddle2MovePerFrame);                            
                    }
                    
                }                
                
                //calculate where the ball will be
                ballNewX = ballPos.GetX() + (ballMovePerFrameX * ballDirX);
                ballNewY = ballPos.GetY() + (ballMovePerFrameY * ballDirY);

                //board collision
                if(ballNewY < screenPos.GetY()) {
                    //top
                    ballDirY = 1;
                    ballNewX = (ballPos.GetX() + (ballMovePerFrameX * ballDirX));
                    ballNewY = ((screenPos.GetY()));
                    
                } else if(ballNewY + ball.GetHeight() > screenPos.GetY() + GetHeight()) {
                    //bottom
                    ballDirY = -1;
                    ballNewX = (ballPos.GetX() + (ballMovePerFrameX * ballDirX));
                    ballNewY = ((screenPos.GetY() + GetHeight() - ball.GetHeight()));
                    
                }else if(ballNewX < screenPos.GetX()) {
                    //left
                    ballDirX = 1;
                    ballNewX = ((screenPos.GetX()));
                    ballNewY = (ballPos.GetY() + (ballMovePerFrameY * ballDirY));
                    score2 += 1;
                    SetScore2Text(score2);
                    SetState(State.SHOW_COUNT_DOWN_IN_GAME);
                    
                } else if(ballNewX + ball.GetWidth() > screenPos.GetX() + GetWidth()) {
                    //right
                    ballDirX = -1;
                    ballNewX = ((screenPos.GetX() + GetWidth() - ball.GetWidth()));
                    ballNewY = (ballPos.GetY() + (ballMovePerFrameY * ballDirY));                                                                            
                    score1 += 1;
                    SetScore1Text(score1);                    
                    SetState(State.SHOW_COUNT_DOWN_IN_GAME);
                    
                }
                
                bounced = false;
                //paddle1 collision
                if(ballNewX <= paddle1Pos.GetX() + paddle1.GetWidth() && ballDirX == -1) {
                    if(ballPos.GetY() + ball.GetHeight()/2 >= paddle1Pos.GetY() + paddle1.GetHeight()/3 && ballPos.GetY() + ball.GetHeight()/2 <= paddle1Pos.GetY() + (paddle1.GetHeight()/3 * 2)) {
                        //middle
                        ballMovePerFrameX *= 1.5;
                        ballMovePerFrameY = rand.nextInt(ballMovePerFrameMin);
                        ballDirX = 1;
                        if(rand.nextInt() % 2 == 0) {
                            ballDirY = 1;
                        } else {
                            ballDirY = -1;
                        }
                        bounced = true;
                        ballNewX = (paddle1Pos.GetX() + paddle1.GetWidth());

                    } else if(ballPos.GetY() + ball.GetHeight()/2 > paddle1Pos.GetY() && ballPos.GetY() + ball.GetHeight()/2 < paddle1Pos.GetY() + paddle1.GetHeight()/3) {
                        //top
                        ballMovePerFrameX *= 1.5;
                        ballDirX = 1;
                        ballDirY = -1;

                        if(ballMovePerFrameY == 0) {
                            ballMovePerFrameY = ballMovePerFrameMin + paddle1MovePerFrame + rand.nextInt(ballMovePerFrameMin);
                        } else {                                                
                            ballMovePerFrameY *= 1.5;
                        }
                        bounced = true;
                        ballNewX = (paddle1Pos.GetX() + paddle1.GetWidth());

                    } else if(ballPos.GetY() + ball.GetHeight()/2 > paddle1Pos.GetY() + (paddle1.GetHeight()/3 * 2) && ballPos.GetY() + ball.GetHeight()/2 < paddle1Pos.GetY() + paddle1.GetHeight()) {
                        //bottom
                        ballMovePerFrameX *= 1.5;
                        ballDirX = 1;                        
                        ballDirY = 1;

                        if(ballMovePerFrameY == 0) {
                            ballMovePerFrameY = ballMovePerFrameMin + paddle1MovePerFrame + rand.nextInt(ballMovePerFrameMin);
                        } else {                        
                            ballMovePerFrameY *= 1.5;
                        }
                        bounced = true;
                        ballNewX = (paddle1Pos.GetX() + paddle1.GetWidth());

                    }
                }

                //paddle2 collision
                if(ballNewX + ball.GetWidth() >= paddle2Pos.GetX() && ballDirX == 1) {
                    if(ballPos.GetY() + ball.GetHeight()/2 >= paddle2Pos.GetY() + paddle2.GetHeight()/3 && ballPos.GetY() + ball.GetHeight()/2 <= paddle2Pos.GetY() + (paddle2.GetHeight()/3 * 2)) {
                        //middle
                        ballMovePerFrameX *= 1.5;
                        ballMovePerFrameY =  rand.nextInt(ballMovePerFrameMin);
                        ballDirX = -1;
                        if(rand.nextInt() % 2 == 0) {
                            ballDirY = 1;
                        } else {
                            ballDirY = -1;
                        }
                        bounced = true;
                        ballNewX = (paddle2Pos.GetX() - ball.GetWidth());

                    } else if(ballPos.GetY() + ball.GetHeight()/2 > paddle2Pos.GetY() && ballPos.GetY() + ball.GetHeight()/2 < paddle2Pos.GetY() + paddle2.GetHeight()/3) {
                        //top
                        ballMovePerFrameX *= 1.5;
                        ballDirX = -1;
                        ballDirY = -1;

                        if(ballMovePerFrameY == 0) {
                            ballMovePerFrameY = ballMovePerFrameMin + paddle1MovePerFrame + rand.nextInt(ballMovePerFrameMin);
                        } else {                       
                            ballMovePerFrameY *= 1.5;                            
                        }
                        bounced = true;
                        ballNewX = (paddle2Pos.GetX() - ball.GetWidth());

                    } else if(ballPos.GetY() + ball.GetHeight()/2 > paddle2Pos.GetY() + (paddle2.GetHeight()/3 * 2) && ballPos.GetY() + ball.GetHeight()/2 < paddle2Pos.GetY() + paddle2.GetHeight()) {
                        //bottom
                        ballMovePerFrameX *= 1.5;
                        ballDirX = -1;
                        ballDirY = 1;

                        if(ballMovePerFrameY == 0) {
                            ballMovePerFrameY = ballMovePerFrameMin + paddle1MovePerFrame + rand.nextInt(ballMovePerFrameMin);
                        } else {                       
                            ballMovePerFrameY *= 1.5;                            
                        }
                        bounced = true;
                        ballNewX = (paddle2Pos.GetX() - ball.GetWidth());

                    }
                }                

                //set limits on the ball's speed
                if(ballMovePerFrameX > ballMovePerFrameMax) {
                    ballMovePerFrameX = ballMovePerFrameMax;
                }

                if(ballMovePerFrameY > ballMovePerFrameMax) {
                    ballMovePerFrameY = ballMovePerFrameMax;
                }

                if(bounced) {
                    if(ballMovePerFrameY == ballMovePerFrameMax || ballMovePerFrameX == ballMovePerFrameMax) {
                        bounceSuper.Play();
                    } else {
                        bounceNorm.Play();
                    }
                }
                                
                ballPos.SetX(ballNewX);
                ballPos.SetY(ballNewY);                
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
        bgroundPopup = null;
        bgroundPopupSrc = null;
        
        txtCancel = null;
        txtOk = null;
        
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

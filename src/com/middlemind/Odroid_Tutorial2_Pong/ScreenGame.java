package com.middlemind.Odroid_Tutorial2_Pong;

import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameStates;
import net.middlemind.MmgGameApiJava.MmgCore.GamePanel.GameType;
import net.middlemind.MmgGameApiJava.MmgCore.GameSettings;
import net.middlemind.MmgGameApiJava.MmgCore.GenericEventMessage;
import net.middlemind.MmgGameApiJava.MmgCore.Helper;
import net.middlemind.MmgGameApiJava.MmgCore.Screen;
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
     * An enumeration that tracks which number is visible during game start countdown and in-game countdown.
     */
    private enum NumberState {
        NONE,
        NUMBER_1,
        NUMBER_2,
        NUMBER_3
    };
    
    /**
     * An enumeration that tracks which state this Screen is currently rendering.
     * Allows the Screen to support different views like in-game, countdown, game over, game start, etc.
     */
    private enum State {
        NONE,
        SHOW_GAME,
        SHOW_COUNT_DOWN,
        SHOW_COUNT_DOWN_IN_GAME,        
        SHOW_GAME_OVER,
        SHOW_GAME_EXIT
    };
        
    private GameType gameType = GameType.GAME_ONE_PLAYER;
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
    
    private int scoreGameWin = 7;
    private MmgFont scoreLeft;
    private int score1 = 0;
    private MmgFont scoreRight;
    private int score2 = 0;
    private MmgFont exit;
    private MmgBmp exitBground;
        
    private int paddle1MovePerFrame = GetSpeedPerFrame(400);
    private boolean paddle1MoveUp = false;
    private boolean paddle1MoveDown = false;
    
    private int aiMaxSpeed = 440;
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
    
    private MmgFont txtGoal;
    private MmgFont txtDirecP1;
    private MmgFont txtDirecP2;    
 
    private MmgFont txtGameOver1;
    private MmgFont txtGameOver2;
    
    private int padding = MmgHelper.ScaleValue(4);
    private int popupTotalWidth = 300;
    private int popupTotalHeight = 120; 
    
    private boolean infiniteBounce = false;
    
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
        owner = Owner;
    }

    /**
     * Loads all the resources needed to display this game screen and support all Screen states.
     */
    @Override
    @SuppressWarnings("UnusedAssignment")
    public void LoadResources() {
        pause = true;
        
        rand = new Random((int)System.currentTimeMillis());
        
        if(gameType == GameType.GAME_TWO_PLAYER) {
            paddle2MovePerFrame = GetSpeedPerFrame(aiMaxSpeed);            
        }
        
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
        
        txtGoal = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtGoal.SetText("Goal: First player to 7 points wins.");
        txtGoal.SetMmgColor(MmgColor.GetWhite());  
        MmgHelper.CenterHorAndVert(txtGoal);
        txtGoal.SetY(number1.GetY() - txtGoal.GetHeight() + MmgHelper.ScaleValue(5));
        txtGoal.SetIsVisible(false);
        AddObj(txtGoal);        
        
        txtDirecP1 = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtDirecP1.SetText("Player 1: Right paddle, use UP, DOWN keys to move paddle.");
        txtDirecP1.SetMmgColor(MmgColor.GetWhite());  
        MmgHelper.CenterHorAndVert(txtDirecP1);
        txtDirecP1.SetY(number1.GetY() + number1.GetHeight() + txtDirecP1.GetHeight() + MmgHelper.ScaleValue(10));
        txtDirecP1.SetIsVisible(false);
        AddObj(txtDirecP1);
        
        txtDirecP2 = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtDirecP2.SetText("Player 2: Left paddle, use MOUSE to move paddle.");
        txtDirecP2.SetMmgColor(MmgColor.GetWhite());
        MmgHelper.CenterHorAndVert(txtDirecP2);
        txtDirecP2.SetY(txtDirecP1.GetY() + txtDirecP1.GetHeight() + MmgHelper.ScaleValue(10));
        txtDirecP2.SetIsVisible(false);        
        AddObj(txtDirecP2);        
        
        txtGameOver1 = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtGameOver1.SetText("Player 1 has won the game. Press A or B to exit.");
        txtGameOver1.SetMmgColor(MmgColor.GetWhite());  
        MmgHelper.CenterHorAndVert(txtGameOver1);
        txtGameOver1.SetIsVisible(false);
        AddObj(txtGameOver1);
        
        txtGameOver2 = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtGameOver2.SetText("Player 2 has won the game. Press A or B to exit.");
        txtGameOver2.SetMmgColor(MmgColor.GetWhite());  
        MmgHelper.CenterHorAndVert(txtGameOver2);
        txtGameOver2.SetIsVisible(false);
        AddObj(txtGameOver2);        
        
        bgroundPopupSrc = MmgHelper.GetBasicCachedBmp("popup_window_base.png");
        
        bgroundPopup = new Mmg9Slice(16, bgroundPopupSrc, popupTotalWidth, popupTotalHeight);
        bgroundPopup.SetPosition(MmgVector2.GetOriginVec());
        bgroundPopup.SetWidth(popupTotalWidth);
        bgroundPopup.SetHeight(popupTotalHeight);
        MmgHelper.CenterHorAndVert(bgroundPopup);
        AddObj(bgroundPopup);
        bgroundPopup.SetIsVisible(false);
        
        txtOk = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtOk.SetText("Exit Game (A)");
        txtOk.SetMmgColor(MmgColor.GetWhite()); 
        MmgHelper.CenterHorAndVert(txtOk);
        txtOk.SetY(bgroundPopup.GetY() + txtOk.GetHeight() + MmgHelper.ScaleValue(20));
        txtOk.SetIsVisible(false);
        AddObj(txtOk);
        
        txtCancel = MmgFontData.CreateDefaultBoldMmgFontLg();
        txtCancel.SetText("Cancel Exit (B)");
        txtCancel.SetMmgColor(MmgColor.GetWhite()); 
        MmgHelper.CenterHorAndVert(txtCancel);
        txtCancel.SetY(txtOk.GetY() + txtOk.GetHeight() + MmgHelper.ScaleValue(20));
        txtCancel.SetIsVisible(false);
        AddObj(txtCancel);        
        
        score1 = 0;
        score2 = 0;        
        SetScore1Text(score1);        
        SetScore2Text(score2);
        
        SetState(State.SHOW_COUNT_DOWN);        
        ready = true;
        pause = false;
    }

    public void SetGameType(GameType gt) {
        gameType = gt;
    }
    
    public GameType GetGameType() {
        return gameType;
    }
    
    /**
     * Converts the given speed to a uniform speed per frame so that the game movement will
     * be the same even if the game runs at different frame rates.
     * 
     * @param speed     The target speed to convert to a speed per frame.
     * 
     * @return          A converted speed that represents the speed per frame of the given input speed. 
     */
    private static int GetSpeedPerFrame(int speed) {
        return (int)(speed/(MmgPongClone.FPS - 4));        
    }
    
    /**
     * A method to handle mouse press events from the MainFrame class.
     * 
     * @param v     The 2D coordinates of the mouse press event.
     * 
     * @return      A boolean indicating if this Screen has handled the mouse press event. 
     */
    @Override
    public boolean ProcessMousePress(MmgVector2 v) {
        return ProcessMousePress(v.GetX(), v.GetY());
    }

    /**
     * A method to handle mouse press events from the MainFrame class.
     * 
     * @param x     The X coordinate of the mouse press event.
     * @param y     The Y coordinate of the mouse press event.
     * 
     * @return      A boolean indicating if this Screen has handled the mouse press event. 
     */
    @Override
    public boolean ProcessMousePress(int x, int y) {
        return true;
    }

    /**
     * A method to handle mouse release events from the MainFrame class.
     * 
     * @param v     The 2D coordinates of the mouse release event.
     * 
     * @return      A boolean indicating if this Screen has handled the mouse release event. 
     */    
    @Override
    public boolean ProcessMouseRelease(MmgVector2 v) {
        return ProcessMousePress(v.GetX(), v.GetY());
    }

    /**
     * A method to handle mouse press events from the MainFrame class.
     * 
     * @param x     The X coordinate of the mouse press event.
     * @param y     The Y coordinate of the mouse press event.
     * 
     * @return      A boolean indicating if this Screen has handled the mouse press event. 
     */    
    @Override
    public boolean ProcessMouseRelease(int x, int y) {
        return true;
    }

    /**
     * A method to handle A button click events from the MainFrame class.
     * 
     * @return      A boolean indicating if this Screen has handled the A click event.
     */
    @Override
    public boolean ProcessAClick() {
        if(state == State.SHOW_GAME_EXIT) {
            owner.SwitchGameState(GameStates.MAIN_MENU);
            return true;
            
        } else if(state == State.SHOW_GAME_OVER) {
            owner.SwitchGameState(GameStates.MAIN_MENU);
            return true;
            
        }
        
        return false;
    }
    
    /**
     * A method to handle B button click events from the MainFrame class.
     * 
     * @return      A boolean indicating if this Screen has handled the B click event.
     */    
    @Override
    public boolean ProcessBClick() {
        if(state == State.SHOW_GAME_OVER) {
            owner.SwitchGameState(GameStates.MAIN_MENU);
            return true;
            
        } else {
            if(state != State.SHOW_GAME_EXIT) {        
                SetState(State.SHOW_GAME_EXIT);
                return true;
            
            } else {
                SetState(statePrev);
                return true;
            }
        }
    }
    
    /**
     * A method to handle debug click events from the MainFrame class, the D key on the keyboard.
     * You can use this method to turn on different debugging helpers.
     */
    @Override
    public void ProcessDebugClick() {
        if(scoreGameWin > 1) {
            Helper.wr("Setting game win score to 1.");
            scoreGameWin = 1;
        } else if(scoreGameWin == 1 && infiniteBounce == false) {
            Helper.wr("Setting infinite bounce to true.");
            infiniteBounce = true;
        } else if(scoreGameWin == 1 && infiniteBounce == true) {
            Helper.wr("Setting game win score to 7 and infinite bounce to false.");
            scoreGameWin = 7;
            infiniteBounce = false;
        }
    }

    /**
     * A method to handle key press events from the MainFrame class.
     * 
     * @param c     The character of the key that was pressed on the keyboard.
     * 
     * @return      A boolean indicating if the key press event was handled by this Screen.
     */
    @Override
    public boolean ProcessKeyPress(char c) {
        if(state == State.SHOW_GAME && pause == false) {
            if(gameType == GameType.GAME_TWO_PLAYER) {
                if(c == 'x' || c == 'X') {            
                    paddle1MoveUp = false;
                    paddle1MoveDown = true;
                    return true;

                } else if(c == 's' || c == 'S') {            
                    paddle1MoveUp = true;
                    paddle1MoveDown = false;            
                    return true;

                }
            }
        }
        
        return false;        
    }
    
    /**
     * A method to handle key release events from the MainFrame class.
     * 
     * @param c     The character of the key that was released on the keyboard.
     * 
     * @return      A boolean indicating if the key release event was handled by this Screen.
     */    
    @Override
    public boolean ProcessKeyRelease(char c) {
        if(state == State.SHOW_GAME && pause == false) {        
            if(gameType == GameType.GAME_TWO_PLAYER) {        
                if(c == 'x' || c == 'X') {
                    paddle1MoveDown = false;
                    return true;

                } else if(c == 's' || c == 'S') {
                    paddle1MoveUp = false;
                    return true;

                }
            }
        }
        
        return false;       
    }
    
    /**
     * A method to handle key click events from the MainFrame class.
     * 
     * @param c     The character of the key that was clicked on the keyboard.
     * 
     * @return      A boolean indicating if the key click event was handled by this Screen.
     */    
    @Override
    public boolean ProcessKeyClick(char c) {
        return false;
    }    
    
    /**
     * A method to handle dpad press events from the MainFrame class.
     * 
     * @param dir   The dpad code, UP, DOWN, LEFT, RIGHT of the direction that was pressed on the keyboard.
     * 
     * @return      A boolean indicating if the dpad press was handled by this Screen.
     */
    @Override
    public boolean ProcessDpadPress(int dir) {
        if(state == State.SHOW_GAME && pause == false) {        
            if(dir == GameSettings.DOWN) {
                paddle2MoveUp = false;
                paddle2MoveDown = true;
                return true;

            } else if(dir == GameSettings.UP) {            
                paddle2MoveUp = true;
                paddle2MoveDown = false;            
                return true;

            }
        }
        
        return false;
    }

    /**
     * A method to handle dpad release events from the MainFrame class.
     * 
     * @param dir   The dpad code, UP, DOWN, LEFT, RIGHT of the direction that was released on the keyboard.
     * 
     * @return      A boolean indicating if the dpad release was handled by this Screen.
     */    
    @Override
    public boolean ProcessDpadRelease(int dir) {
        if(state == State.SHOW_GAME && pause == false) {        
            if(dir == GameSettings.DOWN) {
                paddle2MoveDown = false;
                return true;

            } else if(dir == GameSettings.UP) {
                paddle2MoveUp = false;
                return true;

            }
        }
        
        return false;
    }
    
    /**
     * A method to handle dpad click events from the MainFrame class.
     * 
     * @param dir       The dpad code, UP, DOWN, LEFT, RIGHT of the direction that was clicked on the keyboard.
     * 
     * @return          A boolean indicating if the dpad click was handled by this Screen.
     */
    @Override
    public boolean ProcessDpadClick(int dir) {
        return true;
    }
    
    /**
     * A method to handle mouse click events from the MainFrame class.
     * 
     * @param v         The 2D mouse coordinates of the click event.
     * 
     * @return          A boolean indicating if the mouse click event was handled by this Screen.
     */
    @Override
    public boolean ProcessMouseClick(MmgVector2 v) {
        return ProcessMouseClick(v.GetX(), v.GetY());
    }

    /**
     * A method to handle the mouse click events from the MainFrame class.
     * 
     * @param x         The X coordinate of the mouse click event.
     * 
     * @param y         The Y coordinate of the mouse click event.
     * 
     * @return          A boolean indicating if the mouse click event was handled by this Screen.
     */
    @Override
    public boolean ProcessMouseClick(int x, int y) {        
        return true;
    }    
    
    /**
     * Processes mouse movement for player 2's input, the left hand paddle.
     * 
     * @param x     The X position of the mouse with the Screen's offset taken into account.
     * @param y     The Y position of the mouse with the Screen's offset taken into account.
     * 
     * @return      Returns a boolean indicating if the event was consumed by this game Screen. 
     */
    @Override
    public boolean ProcessMouseMove(int x, int y) {
        if(state == State.SHOW_GAME && pause == false) {        
            if(gameType == GameType.GAME_TWO_PLAYER) {
                if(y >= screenPos.GetY() && y <= (screenPos.GetY() + GetHeight() - paddle1.GetHeight())) {
                    lastX = x;
                    lastY = y;
                    mousePos = true;
                    return true;
                }
            }            
        }
        
        mousePos = false;
        return false;
    }    
    
    /**
     * Resets certain aspects of the UI that are related to the actual game.
     * Some aspects of the UI are left visible during the in-game count down.
     */
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
    
    /**
     * Sets the Screen's current state. The state is used to prepare what MmgObj's are visible for the given state.
     * 
     * @param in        The desired State to set the Screen to.
     */
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
                txtGoal.SetIsVisible(false);                
                txtDirecP1.SetIsVisible(false);
                txtDirecP2.SetIsVisible(false);                
                scoreLeft.SetIsVisible(false);
                scoreRight.SetIsVisible(false);
                exit.SetIsVisible(false);                
                bgroundPopup.SetIsVisible(false);
                txtOk.SetIsVisible(false);
                txtCancel.SetIsVisible(false);
                
                score1 = 0;
                score2 = 0;        
                SetScore1Text(score1);        
                SetScore2Text(score2);  
                
                pause = false;                
                dirty = false;                
                break;
                
            case SHOW_GAME_OVER:
                ball.SetIsVisible(true);               
                paddle1.SetIsVisible(true);
                paddle2.SetIsVisible(true);
                bground.SetIsVisible(false);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);                
                txtGoal.SetIsVisible(false);                
                txtDirecP1.SetIsVisible(false);
                txtDirecP2.SetIsVisible(false);                
                scoreLeft.SetIsVisible(true);
                scoreRight.SetIsVisible(true);
                exit.SetIsVisible(true);                
                bgroundPopup.SetIsVisible(false);
                txtOk.SetIsVisible(false);
                txtCancel.SetIsVisible(false);                
                
                if(score1 == scoreGameWin) {
                    txtGameOver1.SetIsVisible(true);
                    txtGameOver2.SetIsVisible(false);
                    
                } else if(score2 == scoreGameWin) {
                    txtGameOver1.SetIsVisible(false);
                    txtGameOver2.SetIsVisible(true);
                    
                }
                numberState = NumberState.NONE;
                
                pause = false;
                dirty = true;                
                break;
                
            case SHOW_GAME:
                if(statePrev != State.SHOW_GAME_EXIT) {
                    timeNumberMs = System.currentTimeMillis();
                    ResetGame();
                }
                
                ball.SetIsVisible(true);                
                paddle1.SetIsVisible(true);
                paddle2.SetIsVisible(true);
                bground.SetIsVisible(true);
                number1.SetIsVisible(false);
                number2.SetIsVisible(false);
                number3.SetIsVisible(false);
                txtGoal.SetIsVisible(false);                
                txtDirecP1.SetIsVisible(false);
                txtDirecP2.SetIsVisible(false);                
                scoreLeft.SetIsVisible(true);
                scoreRight.SetIsVisible(true);
                exit.SetIsVisible(true);                
                bgroundPopup.SetIsVisible(false);
                txtOk.SetIsVisible(false);
                txtCancel.SetIsVisible(false);
                txtGameOver1.SetIsVisible(false);
                txtGameOver2.SetIsVisible(false);
                
                if(statePrev != State.SHOW_GAME_EXIT) {                
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
                }
                
                pause = false;
                dirty = true;
                break;
                
            case SHOW_COUNT_DOWN_IN_GAME:                
                ball.SetIsVisible(true);               
                paddle1.SetIsVisible(true);
                paddle2.SetIsVisible(true);
                bground.SetIsVisible(true);
                
                if(statePrev != State.SHOW_GAME_EXIT) {
                    number1.SetIsVisible(false);
                    number2.SetIsVisible(false);
                    number3.SetIsVisible(false);
                }
                
                txtGoal.SetIsVisible(false);                
                txtDirecP1.SetIsVisible(false);
                txtDirecP2.SetIsVisible(false);                
                scoreLeft.SetIsVisible(true);
                scoreRight.SetIsVisible(true);
                exit.SetIsVisible(true);                
                bgroundPopup.SetIsVisible(false);
                txtOk.SetIsVisible(false);
                txtCancel.SetIsVisible(false);
                txtGameOver1.SetIsVisible(false);
                txtGameOver2.SetIsVisible(false);                
                
                if(statePrev != State.SHOW_GAME_EXIT) {
                    numberState = NumberState.NONE;
                } else {
                    //reset this number count down
                    timeNumberMs = System.currentTimeMillis();
                }
                
                pause = false;
                dirty = true;                
                break;
                
            case SHOW_COUNT_DOWN:
                ball.SetIsVisible(false);
                paddle1.SetIsVisible(false);
                paddle2.SetIsVisible(false);
                bground.SetIsVisible(false);
                
                if(statePrev != State.SHOW_GAME_EXIT) {                
                    number1.SetIsVisible(false);
                    number2.SetIsVisible(false);
                    number3.SetIsVisible(false);
                    txtGoal.SetIsVisible(false);                
                    txtDirecP1.SetIsVisible(false);
                    txtDirecP2.SetIsVisible(false);
                }
                
                scoreLeft.SetIsVisible(true);
                scoreRight.SetIsVisible(true);
                exit.SetIsVisible(true);
                bgroundPopup.SetIsVisible(false);
                txtOk.SetIsVisible(false);
                txtCancel.SetIsVisible(false);
                txtGameOver1.SetIsVisible(false);
                txtGameOver2.SetIsVisible(false);
                
                if(statePrev != State.SHOW_GAME_EXIT) {                
                    numberState = NumberState.NONE;
                } else {
                    //reset this number count down
                    timeNumberMs = System.currentTimeMillis();
                }
                
                pause = false;
                dirty = true;
                break;
                                
            case SHOW_GAME_EXIT:
                bgroundPopup.SetIsVisible(true);
                txtOk.SetIsVisible(true);
                txtCancel.SetIsVisible(true);
                dirty = true;                
                break;                
        }        
    }
    
    /**
     * Updates player2's score, right hand paddle.
     * 
     * @param score     The score to set for player two.
     */    
    private void SetScore2Text(int score) {
        String tmp = score + "";
        if(tmp.length() != 2) {
            tmp = "0" + tmp;
        }
        scoreLeft.SetText(tmp);
    }
    
    /**
     * Updates player1's score, right hand paddle.
     * 
     * @param score     The score to set for player one.
     */
    private void SetScore1Text(int score) {
        String tmp = score + "";
        if(tmp.length() != 2) {
            tmp = "0" + tmp;
        }
        scoreRight.SetText(tmp);
    }    
    
    /**
     * The DrawScreen method gets called by the MmgUpdate method if the Screen is not paused and is responsible for drawing the current screen state.
     */
    @Override
    public void DrawScreen() {
        //run each game frame
        pause = true;
        
        switch(state) {
            case NONE:
                break;
                
            case SHOW_GAME_EXIT:

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
                        if(state == State.SHOW_COUNT_DOWN) {
                            txtGoal.SetIsVisible(true);                            
                            txtDirecP1.SetIsVisible(true);
                            if(gameType == GameType.GAME_TWO_PLAYER) {
                                txtDirecP2.SetIsVisible(true);
                            } else {
                                txtDirecP2.SetIsVisible(false);
                            }
                        } else {
                            txtGoal.SetIsVisible(false);
                            txtDirecP1.SetIsVisible(false);
                            txtDirecP2.SetIsVisible(false);
                        }
                        break;
                        
                    case NUMBER_1:
                        timeTmpMs = System.currentTimeMillis();
                        if(timeTmpMs - timeNumberMs >= timeNumberDisplayMs) {
                            timeNumberMs = timeTmpMs;
                            numberState = NumberState.NONE;
                            number1.SetIsVisible(false);
                            number2.SetIsVisible(false);
                            number3.SetIsVisible(false);
                            txtDirecP1.SetIsVisible(false);
                            txtDirecP2.SetIsVisible(false);
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
                            txtDirecP1.SetIsVisible(true);
                            if(state == State.SHOW_COUNT_DOWN) {
                                txtGoal.SetIsVisible(true);
                                if(gameType == GameType.GAME_TWO_PLAYER) {
                                    txtDirecP2.SetIsVisible(true);
                                } else {
                                    txtDirecP2.SetIsVisible(false);
                                }
                            } else {
                                txtGoal.SetIsVisible(false);
                                txtDirecP1.SetIsVisible(false);
                                txtDirecP2.SetIsVisible(false);
                            }
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
                            if(state == State.SHOW_COUNT_DOWN) {
                                txtGoal.SetIsVisible(true);
                                if(gameType == GameType.GAME_TWO_PLAYER) {
                                    txtDirecP2.SetIsVisible(true);
                                } else {
                                    txtDirecP2.SetIsVisible(false);
                                }
                            } else {
                                txtGoal.SetIsVisible(false);
                                txtDirecP1.SetIsVisible(false);
                                txtDirecP2.SetIsVisible(false);
                            }                                
                            bounceNorm.Play();
                        }
                        break;
                        
                }
                break;
                
            case SHOW_GAME:
                if(gameType == GameType.GAME_TWO_PLAYER) {
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
                    
                } else {                    
                    //AI
                    if(ballPos.GetY() + ball.GetHeight()/2 < paddle1.GetY()) {
                        if(paddle1Pos.GetY() - paddle1MovePerFrame < screenPos.GetY()) {
                            paddle1Pos.SetY(screenPos.GetY());                
                        } else {
                            paddle1Pos.SetY(paddle1Pos.GetY() - paddle1MovePerFrame);
                        }
                        
                    }else if(ballPos.GetY() + ball.GetHeight()/2 > paddle1.GetY() + paddle1.GetHeight()) {
                        if(paddle1Pos.GetY() + paddle1.GetHeight() + paddle1MovePerFrame > screenPos.GetY() + GetHeight()) {
                            paddle1Pos.SetY(screenPos.GetY() + GetHeight() - paddle1.GetHeight());
                        } else {
                           paddle1Pos.SetY(paddle1Pos.GetY() + paddle1MovePerFrame);                            
                        }
                        
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
                    score1 += 1;
                    SetScore1Text(score1);
                    
                    if(infiniteBounce == false) {
                        if(score1 == scoreGameWin) {
                            SetState(State.SHOW_GAME_OVER);                        
                        } else {
                            SetState(State.SHOW_COUNT_DOWN_IN_GAME);
                        }
                    }
                    
                } else if(ballNewX + ball.GetWidth() > screenPos.GetX() + GetWidth()) {
                    //right
                    ballDirX = -1;
                    ballNewX = ((screenPos.GetX() + GetWidth() - ball.GetWidth()));
                    ballNewY = (ballPos.GetY() + (ballMovePerFrameY * ballDirY));                                                                            
                    score2 += 1;
                    SetScore2Text(score2);
                    
                    if(infiniteBounce == false) {
                        if(score2 == scoreGameWin) {
                            SetState(State.SHOW_GAME_OVER);                        
                        } else {
                            SetState(State.SHOW_COUNT_DOWN_IN_GAME);
                        }
                    }
                    
                }
                
                bounced = false;
                //paddle1 collision
                if(ballNewX <= paddle1Pos.GetX() + paddle1.GetWidth() && ballDirX == -1) {
                    if(ballNewY + ball.GetHeight()/2 >= paddle1Pos.GetY() + paddle1.GetHeight()/3 && ballNewY + ball.GetHeight()/2 <= paddle1Pos.GetY() + ((paddle1.GetHeight()/3) * 2)) {
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

                    } else if(ballNewY + ball.GetHeight() >= paddle1Pos.GetY() && ballNewY + ball.GetHeight() <= paddle1Pos.GetY() + paddle1.GetHeight()/2) {
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

                    } else if(ballNewY >= paddle1Pos.GetY() + paddle1.GetHeight()/2 && ballNewY <= paddle1Pos.GetY() + paddle1.GetHeight()) {
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
                    if(ballNewY + ball.GetHeight()/2 >= paddle2Pos.GetY() + paddle2.GetHeight()/3 && ballNewY + ball.GetHeight()/2 <= paddle2Pos.GetY() + ((paddle2.GetHeight()/3) * 2)) {
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

                    } else if(ballNewY + ball.GetHeight() >= paddle2Pos.GetY() && ballNewY + ball.GetHeight() <= paddle2Pos.GetY() + paddle2.GetHeight()/2) {
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

                    } else if(ballNewY >= paddle2Pos.GetY() + paddle2.GetHeight()/2 && ballNewY <= paddle2Pos.GetY() + paddle2.GetHeight()) {
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
        
        ResetGame();
        state = State.NONE;
        statePrev = State.NONE;        
        
        bounceNorm = null;
        bounceSuper = null;
        bground = null;
        
        paddle1 = null;
        paddle2 = null;
        paddle1Pos = null;
        paddle2Pos = null;
        
        ball = null;
        ballPos = null;
        number1 = null;
        number2 = null;
        number3 = null;
        
        scoreLeft = null;
        scoreRight = null;
        exit = null;
        exitBground = null;
        
        rand = null;
        screenPos = null;
        bgroundPopupSrc = null;
        bgroundPopup = null;
        
        txtOk = null;
        txtCancel = null;
        txtGoal = null;
        txtDirecP1 = null;
        txtDirecP2 = null;
        txtGameOver1 = null;
        txtGameOver2 = null;
                        
        ClearObjs();
        ready = false;
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

    /**
     * Handles generic events sent to this class.
     * 
     * @param obj       A GenericEventMessage class instance.
     */
    @Override
    public void HandleGenericEvent(GenericEventMessage obj) {
        //handle generic event
    }
}

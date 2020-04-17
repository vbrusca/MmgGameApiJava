package net.middlemind.MmgGameApiJava.MmgCore;

//TODO: Finish documentation

import java.io.IOException;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.ControllerEvent;
import net.java.games.input.ControllerListener;
import net.middlemind.MmgGameApiJava.MmgCore.GamePadInput.GamePadButton;

/**
 * The GamePadHub class is used to provide access to up to 6 buttons from a USB game pad.
 * Created by Middlemind Games 01/05/2020
 * 
 * @author Victor G. Brusca
 */
public class GamePadHub {
    
    /**
     * A static integer for describing the default length of gamepad input.
     */
    public static int LEN = 6;
    
    /**
     * A static integer for tracking the UP button on the gamepad.
     */
    public static int UP = 0;
    
    /**
     * A static integer for tracking the DOWN button on the gamepad.
     */
    public static int DOWN = 1;
    
    /**
     * A static integer for tracking the LEFT button on the gamepad.
     */
    public static int LEFT = 2;
    
    /**
     * A static integer for tracking the RIGHT button on the gamepad.
     */
    public static int RIGHT = 3;
    
    /**
     * A static integer for tracking the A button on the gamepad.
     */
    public static int A = 4;
    
    /**
     * A static integer for tracking the B button on the gamepad.
     */
    public static int B = 5;
        
    /**
     * An array of GamePadInput instances used to indicate dpad, A button, and B button input.
     */
    public GamePadInput[] buttons = null;
            
    /**
     * A boolean that indicates if the GpioHub has been properly prepared.
     */
    public boolean prepped = false;
    
    /**
     * A boolean that indicates if GPIO input is enabled.
     */
    public boolean gamePadEnabled = false;
          
    /**
     * A JInput controller to read gamepad data from.
     */
    public Controller gamePad = null;
    
    /**
     * An array of components supported by this gamepad.
     */
    public Component[] components = null;
    
    /**
     * 
     */
    public int gamePadIdx = 0;
    
    /**
     * 
     */
    public int gamePadSrc = GameSettings.SRC_GAMEPAD_1;
    
    /**
     * 
     */
    public int gamePadUp = GameSettings.UP_GAMEPAD_1;
    
    /**
     * 
     */
    public int gamePadDown = GameSettings.DOWN_GAMEPAD_1;
    
    /**
     * 
     */
    public int gamePadLeft = GameSettings.LEFT_GAMEPAD_1;
    public int gamePadRight = GameSettings.RIGHT_GAMEPAD_1;    
    
    /**
     * 
     */
    private int i;
    
    /**
     * 
     */
    private int j;    
    
    /**
     * 
     */
    private int k;    
    
    /**
     * 
     */
    private GamePadInput btn1;
    
    /**
     * 
     */
    private GamePadInput btn2;    
    
    /**
     * 
     */
    private GamePadInput btn3;    
    
    /**
     * 
     */
    private Controller[] ca;
    
    /**
     * 
     */
    private boolean btmp;
    
    /**
     * A default constructor for the GpioHub class that checks to see if GPIO is supported on the system.
     * Creates a default array of 6 GpioPin instances using values from the GameSettings class to set the GPIO pin numbers
     * and the events that should be tracked.
     */
    public GamePadHub(int GamePadIndex) {        
        gamePadIdx = GamePadIndex;

        ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
        if(ca != null && gamePadIdx >= 0 && gamePadIdx < ca.length) {        
            gamePad = ca[gamePadIdx];
        } else {
            gamePad = null;
        }
        
        gamePadSrc = GameSettings.SRC_GAMEPAD_1;
        gamePadUp = GameSettings.UP_GAMEPAD_1;
        gamePadDown = GameSettings.DOWN_GAMEPAD_1;
        gamePadLeft = GameSettings.LEFT_GAMEPAD_1;
        gamePadRight = GameSettings.RIGHT_GAMEPAD_1;   
        
        buttons = new GamePadInput[6];
        buttons[0] = new GamePadInput(GameSettings.GAMEPAD_1_UP_INDEX, GameSettings.GAMEPAD_1_UP_VALUE_ON, GameSettings.GAMEPAD_1_UP_VALUE_OFF,             GamePadButton.BtnUp, GameSettings.GAMEPAD_1_UP_CHECK_PRESS, GameSettings.GAMEPAD_1_UP_CHECK_RELEASE, GameSettings.GAMEPAD_1_UP_CHECK_CLICK);
        buttons[1] = new GamePadInput(GameSettings.GAMEPAD_1_DOWN_INDEX, GameSettings.GAMEPAD_1_DOWN_VALUE_ON, GameSettings.GAMEPAD_1_DOWN_VALUE_OFF,       GamePadButton.BtnDown, GameSettings.GAMEPAD_1_DOWN_CHECK_PRESS, GameSettings.GAMEPAD_1_DOWN_CHECK_RELEASE, GameSettings.GAMEPAD_1_DOWN_CHECK_CLICK);
        buttons[2] = new GamePadInput(GameSettings.GAMEPAD_1_LEFT_INDEX, GameSettings.GAMEPAD_1_LEFT_VALUE_ON, GameSettings.GAMEPAD_1_LEFT_VALUE_OFF,       GamePadButton.BtnLeft, GameSettings.GAMEPAD_1_LEFT_CHECK_PRESS, GameSettings.GAMEPAD_1_LEFT_CHECK_RELEASE, GameSettings.GAMEPAD_1_LEFT_CHECK_CLICK);
        buttons[3] = new GamePadInput(GameSettings.GAMEPAD_1_RIGHT_INDEX, GameSettings.GAMEPAD_1_RIGHT_VALUE_ON, GameSettings.GAMEPAD_1_RIGHT_VALUE_OFF,    GamePadButton.BtnRight, GameSettings.GAMEPAD_1_RIGHT_CHECK_PRESS, GameSettings.GAMEPAD_1_RIGHT_CHECK_RELEASE, GameSettings.GAMEPAD_1_RIGHT_CHECK_CLICK);
        buttons[4] = new GamePadInput(GameSettings.GAMEPAD_1_A_INDEX, GameSettings.GAMEPAD_1_A_VALUE_ON, GameSettings.GAMEPAD_1_A_VALUE_OFF,                GamePadButton.BtnA, GameSettings.GAMEPAD_1_A_CHECK_PRESS, GameSettings.GAMEPAD_1_A_CHECK_RELEASE, GameSettings.GAMEPAD_1_A_CHECK_CLICK);
        buttons[5] = new GamePadInput(GameSettings.GAMEPAD_1_B_INDEX, GameSettings.GAMEPAD_1_B_VALUE_ON, GameSettings.GAMEPAD_1_B_VALUE_OFF,                GamePadButton.BtnB, GameSettings.GAMEPAD_1_B_CHECK_PRESS, GameSettings.GAMEPAD_1_B_CHECK_RELEASE, GameSettings.GAMEPAD_1_B_CHECK_CLICK);
        
        AddListener();        
        Prep();
    }

    /**
     * 
     * 
     * @param player1
     * @param GamePadIndex 
     */
    public GamePadHub(boolean player1, int GamePadIndex) {               
        gamePadIdx = GamePadIndex;

        ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
        if(ca != null && gamePadIdx >= 0 && gamePadIdx < ca.length) {        
            gamePad = ca[gamePadIdx];
        } else {
            gamePad = null;
        }        
                
        buttons = new GamePadInput[6];
        
        if(player1) {
            gamePadSrc = GameSettings.SRC_GAMEPAD_1;
            gamePadUp = GameSettings.UP_GAMEPAD_1;
            gamePadDown = GameSettings.DOWN_GAMEPAD_1;
            gamePadLeft = GameSettings.LEFT_GAMEPAD_1;
            gamePadRight = GameSettings.RIGHT_GAMEPAD_1;
            
            buttons[0] = new GamePadInput(GameSettings.GAMEPAD_1_UP_INDEX, GameSettings.GAMEPAD_1_UP_VALUE_ON, GameSettings.GAMEPAD_1_UP_VALUE_OFF,             GamePadButton.BtnUp, GameSettings.GAMEPAD_1_UP_CHECK_PRESS, GameSettings.GAMEPAD_1_UP_CHECK_RELEASE, GameSettings.GAMEPAD_1_UP_CHECK_CLICK);
            buttons[1] = new GamePadInput(GameSettings.GAMEPAD_1_DOWN_INDEX, GameSettings.GAMEPAD_1_DOWN_VALUE_ON, GameSettings.GAMEPAD_1_DOWN_VALUE_OFF,       GamePadButton.BtnDown, GameSettings.GAMEPAD_1_DOWN_CHECK_PRESS, GameSettings.GAMEPAD_1_DOWN_CHECK_RELEASE, GameSettings.GAMEPAD_1_DOWN_CHECK_CLICK);
            buttons[2] = new GamePadInput(GameSettings.GAMEPAD_1_LEFT_INDEX, GameSettings.GAMEPAD_1_LEFT_VALUE_ON, GameSettings.GAMEPAD_1_LEFT_VALUE_OFF,       GamePadButton.BtnLeft, GameSettings.GAMEPAD_1_LEFT_CHECK_PRESS, GameSettings.GAMEPAD_1_LEFT_CHECK_RELEASE, GameSettings.GAMEPAD_1_LEFT_CHECK_CLICK);
            buttons[3] = new GamePadInput(GameSettings.GAMEPAD_1_RIGHT_INDEX, GameSettings.GAMEPAD_1_RIGHT_VALUE_ON, GameSettings.GAMEPAD_1_RIGHT_VALUE_OFF,    GamePadButton.BtnRight, GameSettings.GAMEPAD_1_RIGHT_CHECK_PRESS, GameSettings.GAMEPAD_1_RIGHT_CHECK_RELEASE, GameSettings.GAMEPAD_1_RIGHT_CHECK_CLICK);
            buttons[4] = new GamePadInput(GameSettings.GAMEPAD_1_A_INDEX, GameSettings.GAMEPAD_1_A_VALUE_ON, GameSettings.GAMEPAD_1_A_VALUE_OFF,                GamePadButton.BtnA, GameSettings.GAMEPAD_1_A_CHECK_PRESS, GameSettings.GAMEPAD_1_A_CHECK_RELEASE, GameSettings.GAMEPAD_1_A_CHECK_CLICK);
            buttons[5] = new GamePadInput(GameSettings.GAMEPAD_1_B_INDEX, GameSettings.GAMEPAD_1_B_VALUE_ON, GameSettings.GAMEPAD_1_B_VALUE_OFF,                GamePadButton.BtnB, GameSettings.GAMEPAD_1_B_CHECK_PRESS, GameSettings.GAMEPAD_1_B_CHECK_RELEASE, GameSettings.GAMEPAD_1_B_CHECK_CLICK);
            
        } else {
            gamePadSrc = GameSettings.SRC_GAMEPAD_2;
            gamePadUp = GameSettings.UP_GAMEPAD_2;
            gamePadDown = GameSettings.DOWN_GAMEPAD_2;
            gamePadLeft = GameSettings.LEFT_GAMEPAD_2;
            gamePadRight = GameSettings.RIGHT_GAMEPAD_2;
        
            buttons[0] = new GamePadInput(GameSettings.GAMEPAD_2_UP_INDEX, GameSettings.GAMEPAD_2_UP_VALUE_ON, GameSettings.GAMEPAD_2_UP_VALUE_OFF,             GamePadButton.BtnUp, GameSettings.GAMEPAD_2_UP_CHECK_PRESS, GameSettings.GAMEPAD_2_UP_CHECK_RELEASE, GameSettings.GAMEPAD_2_UP_CHECK_CLICK);
            buttons[1] = new GamePadInput(GameSettings.GAMEPAD_2_DOWN_INDEX, GameSettings.GAMEPAD_2_DOWN_VALUE_ON, GameSettings.GAMEPAD_2_DOWN_VALUE_OFF,       GamePadButton.BtnDown, GameSettings.GAMEPAD_2_DOWN_CHECK_PRESS, GameSettings.GAMEPAD_2_DOWN_CHECK_RELEASE, GameSettings.GAMEPAD_2_DOWN_CHECK_CLICK);
            buttons[2] = new GamePadInput(GameSettings.GAMEPAD_2_LEFT_INDEX, GameSettings.GAMEPAD_2_LEFT_VALUE_ON, GameSettings.GAMEPAD_2_LEFT_VALUE_OFF,       GamePadButton.BtnLeft, GameSettings.GAMEPAD_2_LEFT_CHECK_PRESS, GameSettings.GAMEPAD_2_LEFT_CHECK_RELEASE, GameSettings.GAMEPAD_2_LEFT_CHECK_CLICK);
            buttons[3] = new GamePadInput(GameSettings.GAMEPAD_2_RIGHT_INDEX, GameSettings.GAMEPAD_2_RIGHT_VALUE_ON, GameSettings.GAMEPAD_2_RIGHT_VALUE_OFF,    GamePadButton.BtnRight, GameSettings.GAMEPAD_2_RIGHT_CHECK_PRESS, GameSettings.GAMEPAD_2_RIGHT_CHECK_RELEASE, GameSettings.GAMEPAD_2_RIGHT_CHECK_CLICK);
            buttons[4] = new GamePadInput(GameSettings.GAMEPAD_2_A_INDEX, GameSettings.GAMEPAD_2_A_VALUE_ON, GameSettings.GAMEPAD_2_A_VALUE_OFF,                GamePadButton.BtnA, GameSettings.GAMEPAD_2_A_CHECK_PRESS, GameSettings.GAMEPAD_2_A_CHECK_RELEASE, GameSettings.GAMEPAD_2_A_CHECK_CLICK);
            buttons[5] = new GamePadInput(GameSettings.GAMEPAD_2_B_INDEX, GameSettings.GAMEPAD_2_B_VALUE_ON, GameSettings.GAMEPAD_2_B_VALUE_OFF,                GamePadButton.BtnB, GameSettings.GAMEPAD_2_B_CHECK_PRESS, GameSettings.GAMEPAD_2_B_CHECK_RELEASE, GameSettings.GAMEPAD_2_B_CHECK_CLICK);
            
        }
        
        AddListener();
        Prep();
    }
    
    /**
     * A constructor for the GpioHub that takes an array of GpioPin instances which is used to set the buttons class field.
     * 
     * @param Buttons       An array of 6 GpioPin instances used to set the buttons class field.
     */
    public GamePadHub(GamePadInput[] Buttons, Controller GamePad) {
        gamePad = GamePad;
        buttons = Buttons;

        AddListener();
        Prep();
    }

    /**
     * 
     */
    private void AddListener() {
        ControllerEnvironment.getDefaultEnvironment().addControllerListener(new ControllerListener() {
            @Override
            public void controllerRemoved(ControllerEvent ev) {
                System.out.println("Gamepad removed, running scan to see if input is still valid.");
                ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
                if(ca != null && gamePadIdx >= 0 && gamePadIdx < ca.length) {
                    if(ca[gamePadIdx] != null && ca[gamePadIdx].equals(gamePad) == false) {
                        gamePad = ca[gamePadIdx];
                        gamePadEnabled = false;
                        Prep();
                        
                    } else {
                        gamePadEnabled = true;                        
                    }
                    
                } else {
                    gamePadEnabled = false;
                    
                }
            }

            @Override
            public void controllerAdded(ControllerEvent ev) {
                System.out.println("Gamepad added, running scan to see if input is still valid.");                
                ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
                if(ca != null && gamePadIdx >= 0 && gamePadIdx < ca.length) {
                    if(ca[gamePadIdx] != null && ca[gamePadIdx].equals(gamePad) == false) {
                        gamePad = ca[gamePadIdx];
                        gamePadEnabled = false;                        
                        Prep();
                        
                    } else {
                        gamePadEnabled = true;                        
                    }
                    
                } else {
                    gamePadEnabled = false;
                    
                }
            }
        });
    }
    
    /**
     * 
    */
    private void Prep() {
        try {
            prepped = false;
            if(gamePad == null) {
                System.out.println("Gamepad is null, disabling gamepad.");
                gamePadEnabled = false;

            } else {                
                components = gamePad.getComponents();
                if(components == null) {
                    System.out.println("Gamepad components is null, disabling gamepad.");
                    gamePadEnabled = false;
             
                } else {
                    for(i = 0; i < LEN; i++) {
                        btn1 = buttons[i];
                        if(btn1.btnIdx < 0 || btn1.btnIdx >= components.length) {
                            System.out.println("Gamepad button is out of the component range, " + btn1.btnIdx + ", disabling gamepad.");                            
                            gamePadEnabled = false;
                        } 
                    }
                    gamePadEnabled = true;
                    
                }
            }
            
            prepped = true;
            System.out.println("Gamepad hub prep is complete. State: Prepped: " + prepped + " Enabled: " + gamePadEnabled + "");

        } catch(Exception e) {
            prepped = true;
            Helper.wrErr(e);
            
        }        
    }
    
    /**
     * A method used to determine if GPIO is enabled on the current environment.
     * 
     * @return      A boolean flag indicating if GPIO is enabled on the current environment.
     */
    public boolean IsEnabled() {
        return gamePadEnabled;
    }

    /**
     * A method used to set the boolean flag to determine if the GPIO is enabled on the current environment.
     * 
     * @param b     A boolean argument used to set if GPIO is enabled on the current environment.
     */
    public void SetEnabled(boolean b) {
        gamePadEnabled = b;
    }
    
    /**
     * A method that returns the current array of GpioPin instances.
     * 
     * @return      An array of GpioPin instances used by the GpioHub for state monitoring.
     */
    public GamePadInput[] GetButtons() {
        return buttons;
    }
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the given array index, i, is enabled.
     * 
     * @param i     An argument indicating which array index, GpioPin, to verify is enabled.
     * 
     * @return      A boolean indicating if the GpioPin at the given index is enabled. 
     */
    public boolean ButtonEnabled(int i) {
        if(buttons != null && i >= 0 && i < buttons.length && buttons[i] != null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the DOWN index position
     * of the buttons array is pressed.
     * 
     * @return      A boolean indicating if the DOWN GpioPin is pressed or not.
     */
    public boolean GetDownPressed() {
        if(ButtonEnabled(DOWN) && buttons[DOWN].checkPressed == true) {
            return buttons[DOWN].pressed;
        } else {
            return false;
        }
    }
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the DOWN index position
     * of the buttons array is released.
     * 
     * @return      A boolean indicating if the DOWN GpioPin is released or not.
     */    
    public boolean GetDownReleased() {
        if(ButtonEnabled(DOWN) && buttons[DOWN].checkReleased == true) {
            return buttons[DOWN].released;
        } else {
            return false;
        }
    }    
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the DOWN index position
     * of the buttons array has been clicked.
     * 
     * @return      A boolean indicating if the DOWN GpioPin has been clicked or not.
     */    
    public boolean GetDownClicked() {
        if(ButtonEnabled(DOWN) && buttons[DOWN].checkClicked == true) {
            return buttons[DOWN].clicked;
        } else {
            return false;
        }
    }
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the UP index position
     * of the buttons array is pressed.
     * 
     * @return      A boolean indicating if the UP GpioPin is pressed or not.
     */    
    public boolean GetUpPressed() {
        if(ButtonEnabled(UP) && buttons[UP].checkPressed == true) {
            return buttons[UP].pressed;
        } else {
            return false;
        }
    }
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the UP index position
     * of the buttons array is released.
     * 
     * @return      A boolean indicating if the UP GpioPin is released or not.
     */        
    public boolean GetUpReleased() {
        if(ButtonEnabled(UP) && buttons[UP].checkReleased == true) {
            return buttons[UP].released;
        } else {
            return false;
        }
    }    
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the UP index position
     * of the buttons array has been clicked.
     * 
     * @return      A boolean indicating if the UP GpioPin has been clicked or not.
     */            
    public boolean GetUpClicked() {
        if(ButtonEnabled(UP) && buttons[UP].checkClicked == true) {
            return buttons[UP].clicked;
        } else {
            return false;
        }
    }    
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the LEFT index position
     * of the buttons array is pressed.
     * 
     * @return      A boolean indicating if the LEFT GpioPin is pressed or not.
     */
    public boolean GetLeftPressed() {
        if(ButtonEnabled(LEFT) && buttons[LEFT].checkPressed == true) {
            return buttons[LEFT].pressed;
        } else {
            return false;
        }
    }
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the LEFT index position
     * of the buttons array is released.
     * 
     * @return      A boolean indicating if the LEFT GpioPin is released or not.
     */    
    public boolean GetLeftReleased() {
        if(ButtonEnabled(LEFT) && buttons[LEFT].checkReleased == true) {
            return buttons[LEFT].released;
        } else {
            return false;
        }
    }    
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the LEFT index position
     * of the buttons array has been clicked.
     * 
     * @return      A boolean indicating if the LEFT GpioPin has been clicked or not.
     */    
    public boolean GetLeftClicked() {
        if(ButtonEnabled(LEFT) && buttons[LEFT].checkClicked == true) {
            return buttons[LEFT].clicked;
        } else {
            return false;
        }
    }    
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the RIGHT index position
     * of the buttons array is pressed.
     * 
     * @return      A boolean indicating if the RIGHT GpioPin is pressed or not.
     */    
    public boolean GetRightPressed() {
        if(ButtonEnabled(RIGHT) && buttons[RIGHT].checkPressed == true) {
            return buttons[RIGHT].pressed;
        } else {
            return false;
        }
    }
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the RIGHT index position
     * of the buttons array is released.
     * 
     * @return      A boolean indicating if the RIGHT GpioPin is released or not.
     */    
    public boolean GetRightReleased() {
        if(ButtonEnabled(RIGHT) && buttons[RIGHT].checkReleased == true) {
            return buttons[RIGHT].released;
        } else {
            return false;
        }
    }    
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the RIGHT index position
     * of the buttons array has been clicked.
     * 
     * @return      A boolean indicating if the RIGHT GpioPin has been clicked or not.
     */    
    public boolean GetRightClicked() {
        if(ButtonEnabled(RIGHT) && buttons[RIGHT].checkClicked == true) {
            return buttons[RIGHT].clicked;
        } else {
            return false;
        }
    }    
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the A index position
     * of the buttons array is pressed.
     * 
     * @return      A boolean indicating if the A GpioPin is pressed or not.
     */    
    public boolean GetAPressed() {
        if(ButtonEnabled(A) && buttons[A].checkPressed == true) {
            return buttons[A].pressed;
        } else {
            return false;
        }
    }
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the A index position
     * of the buttons array is released.
     * 
     * @return      A boolean indicating if the A GpioPin is released or not.
     */      
    public boolean GetAReleased() {
        if(ButtonEnabled(A) && buttons[A].checkReleased == true) {
            return buttons[A].released;
        } else {
            return false;
        }
    }    
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the A index position
     * of the buttons array has been clicked.
     * 
     * @return      A boolean indicating if the A GpioPin has been clicked or not.
     */    
    public boolean GetAClicked() {
        if(ButtonEnabled(A) && buttons[A].checkClicked == true) {
            return buttons[A].clicked;
        } else {
            return false;
        }
    }    
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the B index position
     * of the buttons array is pressed.
     * 
     * @return      A boolean indicating if the B GpioPin is pressed or not.
     */     
    public boolean GetBPressed() {
        if(ButtonEnabled(B) && buttons[B].checkPressed == true) {
            return buttons[B].pressed;
        } else {
            return false;
        }
    }
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the B index position
     * of the buttons array is released.
     * 
     * @return      A boolean indicating if the B GpioPin is released or not.
     */    
    public boolean GetBReleased() {
        if(ButtonEnabled(B) && buttons[B].checkReleased == true) {
            return buttons[B].released;
        } else {
            return false;
        }
    }    
    
    /**
     * A method that returns a boolean flag indicating if the GpioPin at the B index position
     * of the buttons array has been clicked.
     * 
     * @return      A boolean indicating if the B GpioPin has been clicked or not.
     */     
    public boolean GetBClicked() {
        if(ButtonEnabled(B) && buttons[B].checkClicked == true) {
            return buttons[B].clicked;
        } else {
            return false;
        }
    }    
            
    /**
     * A method that returns the prepped state of the GPIO pins, buttons array.
     * 
     * @return      A boolean value indicating that the GPIO pins have been prepped.
     */
    public boolean IsPrepped() {
        return prepped;
    }
    
    /**
     * A method that cleans up all the pins so that their state can be used to determine if a press, release, or click
     * has happened for a given GPIO pin.
     */
    public void CleanUp() {
        for(j = 0; j < LEN; j++) {
            btn2 = buttons[j];
            btn2.pressed = false;
            btn2.clicked = false;
            btn2.released = false;
        }   
    }
    
    /**
     * A method to determine the state of the GPIO pins. The methods scans all the GPIO pins in the buttons
     * array to determine the state of each button.
     * 
     * @throws IOException 
     */
    public void GetState() throws Exception { 
        if(gamePadEnabled == true) {
            btmp = gamePad.poll();
            if(btmp == true) {
                components = gamePad.getComponents();

                for(k = 0; k < LEN; k++) {
                    btn3 = buttons[k];

                    if(components[btn3.btnIdx].getPollData() == btn3.btnOn) {
                        btn3.stateTmp = true;

                    } else {
                        btn3.stateTmp = false;                

                    }

                    btn3.statePrev = btn3.stateCurrent;
                    btn3.stateCurrent = btn3.stateTmp;

                    if(btn3.stateCurrent == false && btn3.statePrev == true) {
                        btn3.released = true;
                        btn3.clicked = true;
                        btn3.pressed = false;

                    } else if(btn3.stateCurrent == true && btn3.statePrev == false) {
                        btn3.released = false;
                        btn3.clicked = false;
                        btn3.pressed = true;

                    }
                }
                
            } else {
                gamePadEnabled = false;
                throw new Exception("Gamepad poll failed!");
            
            }
        }
    }
}

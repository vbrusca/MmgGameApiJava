//package net.java.games.input.test;
package net.middlemind.MmgGameApiJava.MmgTestSpace;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import javax.swing.JFrame;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ControllerScanTest extends JFrame {

    public static Controller player1Controller = null;
    public static Controller player2Controller = null;
    public static Scanner scanner = null;
    public static boolean runScan = true;
    public static Component lastButtonPress = null;
    public static PrintStream origErr = System.err;
    public static PrintStream newErr = null;
    
    
    public static ControllerEnvironment createDefaultEnvironment() throws ReflectiveOperationException {
        // Find constructor (class is package private, so we can't access it directly)
        Constructor<ControllerEnvironment> constructor = (Constructor<ControllerEnvironment>)
            Class.forName("net.java.games.input.DefaultControllerEnvironment").getDeclaredConstructors()[0];

        // Constructor is package private, so we have to deactivate access control checks
        constructor.setAccessible(true);

        // Create object with default constructor
        return constructor.newInstance();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //newErr = new PrintStream(baos);
        //System.setErr(newErr);
                
        System.out.println("Controller Scan Test");        
        new Thread(new Runnable() {
            public void run() {                
                while(runScan) {
                    scanner = new Scanner(System.in);
                    System.out.println("Press Q to Quit:");
                    String txt = scanner.nextLine();
                    if(txt != null && txt.trim().toLowerCase().equals("q")) {
                        runScan = false;
                        break;
                    }
                }
            }
        }).start();
        
        /*
        new Thread(new Runnable() {
            public void run() {
                while(runScan) {
                    try {
                        Thread.sleep(100);
                    } catch(Exception e) {

                    }
                
                    try {
                        baos.flush();
                    }catch(Exception e){

                    }

                    if(baos.toString().indexOf("Failed to poll device") != -1) {
                        System.out.println("Controller IO Error... exiting");
                        runScan = false;
                        //System.exit(1);
                        break;
                    }
                }
            }
        }).start();        
        */
        
        new Thread(new Runnable() {
            public void run() {
                int cnt = 0;
                while(runScan) {
                    
                    try {
                        Controller[] ca = null;                    
                        boolean found = false;

                        try {
                            if(cnt % 100 == 0) {
                                ca = ControllerScanTest.createDefaultEnvironment().getControllers();
                            } else {
                                ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
                            }
                        }catch(Exception e) {
                            System.out.println("Error1");
                            e.printStackTrace();
                        }

                        if(player1Controller != null) {
                            for(int i = 0; i < ca.length; i++) {
                                if(ca[i] != null && ca[i].getPortNumber() == player1Controller.getPortNumber()) {
                                    found = true;
                                }
                            }
                        }

                        if(!found && player1Controller != null) {
                            //player1Controller = null;
                            System.out.println("Player 1 controller previously connected at port '" + player1Controller.getPortNumber() + "' has been removed.");
                        }
                        
                        /*
                        try {
                            ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
                        }catch(Exception e) {
                            System.out.println("Error2");                            
                            e.printStackTrace();
                        }
                        */
                        
                        if(player2Controller != null) {
                            for(int i = 0; i < ca.length; i++) {
                                if(ca[i] != null && ca[i].getPortNumber() == player2Controller.getPortNumber()) {
                                    found = true;
                                }
                            }
                        }

                        if(!found && player2Controller != null) {
                            //player2Controller = null;
                            System.out.println("Player 2 controller previously connected at port '" + player2Controller.getPortNumber() + "' has been removed.");
                        }                    

                        /*
                        try {
                            ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
                        }catch(Exception e) {
                            System.out.println("Error3");                            
                            e.printStackTrace();
                        }
                        */
                        
                        for(int i = 0; i < ca.length; i++) {
                            if(ca[i].getType().toString().toLowerCase().equals("gamepad") || ca[i].getType().toString().toLowerCase().equals("stick")) {
                                boolean pollSuccessful = false;

                                try {
                                    ca[i].poll();
                                    pollSuccessful = true;                                    
                                } catch(Exception e) {
                                    pollSuccessful = false;
                                }

                                if(pollSuccessful == true) {
                                    Component[] components = ca[i].getComponents();
                                    for(int j = 0; j < components.length; j++) {
                                        if(components[j].getPollData() == 1.0 && components[j].isAnalog() == false) {

                                            if(player1Controller == null) {
                                                System.out.println("Found player 1 controller connected with input type '" + ca[i].getType().toString().toLowerCase() + "' at port '" + ca[i].getPortNumber() + "'");
                                                player1Controller = ca[i];
                                                System.out.println("Notice: Player 1 controller was attached to port number '" + player1Controller.getPortNumber() + "'.");

                                                for(int z = 0; z < components.length; z++) {
                                                    System.out.println("Component " + z + ": " + components[z].getName());
                                                    System.out.println("\t\tIdentifier: " + components[z].getIdentifier().getName());
                                                    System.out.println("\t\tIsAnalog: " + components[z].isAnalog());
                                                    System.out.println("\t\tIsRelative: " + components[z].isRelative());
                                                    System.out.println("\t\tData: " + components[z].getPollData());
                                                    System.out.println("\t\tDeadZone: " + components[z].getDeadZone());
                                                }
                                                
                                            } else if(player2Controller == null && ca[i].equals(player1Controller) == false) {
                                                System.out.println("Found player 2 controller connected with input type '" + ca[i].getType().toString().toLowerCase() + "' at port '" + ca[i].getPortNumber() + "'");                                            
                                                player2Controller = ca[i];
                                                System.out.println("Notice: Player 2 controller was attached to port number '" + player2Controller.getPortNumber() + "'.");


                                                for(int z = 0; z < components.length; z++) {
                                                    System.out.println("Component " + z + ": " + components[z].getName());
                                                    System.out.println("\t\tIdentifier: " + components[z].getIdentifier().getName());
                                                    System.out.println("\t\tIsAnalog: " + components[z].isAnalog());
                                                    System.out.println("\t\tIsRelative: " + components[z].isRelative());
                                                    System.out.println("\t\tData: " + components[z].getPollData());
                                                    System.out.println("\t\tDeadZone: " + components[z].getDeadZone());
                                                }
                                                
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                    }catch(Exception e) {
                        System.out.println("Error4");                        
                        e.printStackTrace();
                    }
                    
                    cnt++;
                }
            }
        }).start();
    }
}
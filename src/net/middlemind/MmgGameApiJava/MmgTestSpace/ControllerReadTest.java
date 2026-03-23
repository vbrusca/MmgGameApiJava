//package net.java.games.input.test;
package net.middlemind.MmgGameApiJava.MmgTestSpace;

import java.util.Hashtable;
import javax.swing.JFrame;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.ControllerEvent;
import net.java.games.input.ControllerListener;

public class ControllerReadTest extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
                Hashtable<String, Float> currentData = new Hashtable<String, Float>();                    
                int z = 20;                    

                ControllerEnvironment.getDefaultEnvironment().addControllerListener(new ControllerListener() {
                    @Override
                    public void controllerRemoved(ControllerEvent ev) {
                        System.out.println("ControllerRemoved");
                    }

                    @Override
                    public void controllerAdded(ControllerEvent ev) {
                        System.out.println("ControllerAdded");
                    }
                });

                System.out.println("Scanning controllers for one with active input."); 
                int targetController = 0;
                while(z > 0) {
                    for(int i = 0; i < ca.length; i++) {
                        if(ca[i].getType().toString().toLowerCase().equals("gamepad") || ca[i].getType().toString().toLowerCase().equals("stick")) {
                            ca[i].poll();
                            Component[] components = ca[i].getComponents();
                            System.out.println("Controller Index: " + i + " Component Count: " + components.length);
                            for(int j = 0; j < components.length; j++) {
                                if(components[j].getPollData() != 0.0) {
                                    targetController = i;
                                    z = -1;
                                    System.out.println("Found connected controller with input '" + ca[i].getType().toString().toLowerCase() + "' at port '" + ca[i].getPortNumber() + "'");
                                    break;
                                }
                            }
                        }
                    }
                    
                    try {
                        Thread.sleep(1000);
                    }catch(Exception e) {

                    }

                    z--;
                }                    
                
                z = 20;
                while(z > 0) {
                    int i = targetController;
                    ca[i].poll();
                    Component[] components = ca[i].getComponents();
                    System.out.println("GamePad Index: " + i + " Component Count: " + components.length + " Controller Count: " + ca[i].getControllers().length);
                    for(int j = 0; j < components.length; j++) {
                        if(components[j].getPollData() != 0.0) {
                            System.out.println("Component " + j + ": " + components[j].getName());
                            System.out.println("\t\tIdentifier: " + components[j].getIdentifier().getName());
                            System.out.println("\t\tIsAnalog: " + components[j].isAnalog());
                            System.out.println("\t\tIsRelative: " + components[j].isRelative());
                            System.out.println("\t\tData: " + components[j].getPollData());
                            System.out.println("\t\tDeadZone: " + components[j].getDeadZone());
                        }
                    }

                    try {
                        Thread.sleep(1000);
                    }catch(Exception e) {

                    }

                    z--;
                }
            }
        }).start();
    }
}

package net.middlemind.MmgGameApiJava.MmgTestSpace;

import de.gurkenlabs.input4j.InputDevices;
import de.gurkenlabs.input4j.components.Axis;
import de.gurkenlabs.input4j.components.XInput;

/**
 *
 * @author brusc
 */
public class ControllerScanTestInput4jPoll {
    /*
    old runtime config
    -Djava.library.path=C:\FILES\OIT_LAPTOP_BACKUP\DOCUMENTS\GitHub\MmgGameApiJava\lib\jinput-platform\native-libs\
    */

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, java.io.IOException {
        try (var inputDevices = InputDevices.init()) {
            while (!inputDevices.getAll().isEmpty()) {
                // iterate all available input devices and poll their data every second
                for (var inputDevice : inputDevices.getAll()) {
                    inputDevice.poll();
                    System.out.println(inputDevice.getID() + ":" + inputDevice.getProductName() + ":" + inputDevice.getName() + ":" + inputDevice.getComponents());
                }

                Thread.sleep(1000);
            }
        }
    }
}

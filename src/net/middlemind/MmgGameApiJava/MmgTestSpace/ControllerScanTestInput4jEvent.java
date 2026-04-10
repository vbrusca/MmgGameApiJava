package net.middlemind.MmgGameApiJava.MmgTestSpace;

import de.gurkenlabs.input4j.InputDevices;
import de.gurkenlabs.input4j.components.Axis;
import de.gurkenlabs.input4j.components.XInput;

/**
 *
 * @author brusc
 */
public class ControllerScanTestInput4jEvent {
    /*
    old runtime config
    -Djava.library.path=C:\FILES\OIT_LAPTOP_BACKUP\DOCUMENTS\GitHub\MmgGameApiJava\lib\jinput-platform\native-libs\
    */

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, java.io.IOException {
        try (var devices = InputDevices.init()) {
          var device = devices.getAll().stream().findFirst().orElse(null);
          if (device == null) {
            System.out.println("No input devices found.");
            return;
          }

          device.onInputValueChanged(e -> System.out.println("Value changed: " + e.component() + " -> " + e.newValue()));
          device.onButtonPressed(XInput.X, () -> System.out.println("X button pressed"));
          device.onAxisChanged(Axis.AXIS_X, value -> System.out.println("X axis: " + value));

          // simulate external polling loop
          while (true) {
            device.poll();
            Thread.sleep(1000);
          }
        }    
    }
}

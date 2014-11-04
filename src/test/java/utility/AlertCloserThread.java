package utility;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by nklimotc on 28.10.2014.
 */
public class AlertCloserThread extends Thread {
    public AlertCloserThread() { super(new AlertCloser());}
}
class AlertCloser implements Runnable {

    public void run() {
        try {
            Thread.sleep(4000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
        }
    }
}

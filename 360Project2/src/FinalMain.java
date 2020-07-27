import Driver.ISS;
import gui.controller.Controller;

import java.io.IOException;


/**
 * Main class to run the sensors, ISS software, and GUI.
 * @author Hung Vu
 *
 */
public class FinalMain {
  /**
   * Default main method.
   * @param args ignored
   * @throws IOException ignored
   */
  public static void main(String[] args) throws IOException {
          
    ISS iss = new ISS();
    Thread thread = new Thread(iss);
    thread.start();
    
    Controller ctrl = new Controller();
    Thread guiThread = new Thread(ctrl);
    guiThread.start();

  }

}

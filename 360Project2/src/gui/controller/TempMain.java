package gui.controller;

import Driver.ISS;
import java.io.IOException;


/**
 * Temporary class to test interaction between ISS software
 * and the GUI.
 * @author Hung Vu
 *
 */
public class TempMain {
  /**
   * Default main method.
   * @param args ignored
   * @throws IOException ignored
   */
  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
          
    ISS iss = new ISS();
    Thread thread = new Thread(iss);
    thread.start();
    
    Controller ctrl = new Controller();
    Thread guiThread = new Thread(ctrl);
    guiThread.start();
    
    
  }

}

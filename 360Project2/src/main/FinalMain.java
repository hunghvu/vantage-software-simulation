package main;

import gui.controller.Controller;
import iss.IssSoftware;
import java.io.IOException;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * Main class to run the sensors, ISS software, and GUI.
 * 
 * @author Hung Vu
 *
 */
@SuppressWarnings({

    "PMD.UseUtilityClass", "PMD.DoNotUseThreads"

})
public class FinalMain {
  /**
   * Default main method.
   * 
   * @param args ignored
   * @throws IOException ignored
   */
  public static void main(final String[] args) throws IOException {

    final IssSoftware iss = new IssSoftware();
    final Thread thread = new Thread(iss);
    thread.start();

    final Controller ctrl = new Controller();
    final Thread guiThread = new Thread(ctrl);
    guiThread.start();

  }

}

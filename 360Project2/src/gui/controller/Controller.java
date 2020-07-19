package gui.controller;

import gui.model.DeserializedData;
import gui.view.Frame;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.TimerTask;



/**
 * This class changes the GUI based on given weather data.
 * @author Hung Vu
 *
 */
@SuppressWarnings("deprecation")
public class Controller extends TimerTask implements Observer {
  
  /**
   * The deserialized data.
   */
  private static final DeserializedData DESERIALIZED_DATA =
      new DeserializedData();
  
  /**
   * The GUI's display.
   */
  private static final Frame GUI = new Frame();
  
  /**
   * Default constructor for controller.
   */
  public Controller() {
    
    DESERIALIZED_DATA.addObserver(this);
        
  }
  
  /**
   * Update the GUI after the data has been changed.
   */
  @Override
  public void update(Observable o, Object arg) {
    // TODO Auto-generated method stub
    
  }
  
  /**
   * Get data after a certain time.
   */
  @Override
  public void run() {
    
    receiver();
    
  }
  
  /**
   * Receive the data, deserialize then put it inside model.
   */
  private void receiver() {
    
    DESERIALIZED_DATA.getData().clear();
    
    try {
      
      FileInputStream dataInput =  new FileInputStream("data.ser");
      ObjectInputStream objectInput = new ObjectInputStream(dataInput);
      
      
    } catch (IOException e) {
      
      // TODO Auto-generated catch block
      e.printStackTrace();
      
    }
    
    
  }  
  
}

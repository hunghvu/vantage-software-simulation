package gui.controller;

import gui.model.DeserializedData;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.Observer;



/**
 * This class changes the GUI based on given weather data.
 * @author Hung Vu
 *
 */
@SuppressWarnings("deprecation")
public class Controller implements Observer, Runnable {
  
  /**
   * The deserialized data.
   */
  private static final DeserializedData DESERIALIZED_DATA = new DeserializedData();
  
  /**
   * Sleep time for a thread.
   */
  private static final long SLEEP_TIME = 3000L;
  
//  /**
//   * The GUI's display.
//   */
//  private static final Frame GUI = new Frame();
//  Will revisit this after GUI is implemented.
  
  /**
   * Default constructor for controller.
   */
  public Controller() {
    
    DESERIALIZED_DATA.addObserver(this);
        
  }
  


  
  /**
   * Get data after a certain time.
   */
  @Override
  public void run() {
    
    
    try {
      
      receiver();
      Thread.sleep(SLEEP_TIME);
      run();
      
    } catch (InterruptedException e) {
      
      System.out.println("Controller thread error");
      
    }
    
    
  }
  
  /**
   * Update the GUI after the data has been changed.
   */
  @Override
  public void update(Observable o, Object arg) {
         
    System.out.println(DESERIALIZED_DATA.toString());     
    
  }
  
  /**
   * Receive the data, deserialize then update the model.
   */
  public void receiver() {
    
    //Clear the model from last run.
    DESERIALIZED_DATA.getData().clear();
    
    //Deserialization process.
    try {
      
      FileInputStream dataInput =  new FileInputStream("data.ser");
      ObjectInputStream objectInput = new ObjectInputStream(dataInput);
      
      //Read all object in the stream.
      while (true) {
        
        try {
          
          //Received data: String format - "<DataName>: <value>"
          String data = (String) objectInput.readObject();
          String[] keyAndValue = data.split(": ");
          
          //Update model with new key and value.
          DESERIALIZED_DATA.setData(keyAndValue[0], keyAndValue[1]);
          
        } catch (EOFException e) {
          //End of file, break the loop.
          
          break;
          
        } catch (ClassNotFoundException e) {
          
          System.out.println("Error occured in controller.Controller.receiver()");
          
        }
        
      }
      
      objectInput.close();
      dataInput.close();
      
    } catch (IOException e) {
      
      System.out.println("Error occured in controller.Controller.receiver()");
      
    }
        
  }  
  
}

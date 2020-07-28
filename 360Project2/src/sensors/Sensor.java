package sensors;

import java.util.Random;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * Sensor interface that provides outline for the sensors, it extends Runnable.
 * 
 * @author Zitao Yu
 *
 */
public interface Sensor extends Runnable {
  /**
   * Static random object for the sensors.
   */
  Random RANDOM = new Random();

  /**
   * Method to return first data as a String.
   * 
   * @return the first data for the sensor.
   */
  String getDataOne();

  /**
   * Method to return second data as a String.
   * 
   * @return the second data for the sensor.
   */
  String getDataTwo();

  /**
   * Update data.
   */
  void updateData();

  @Override
  String toString();

  @Override
  void run();
}
package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.TemperatureSensor;
import sensors.WindDirectionSensor;

/**
 * Junit testing for Sensors
 *
 * @author Kim Seungku
 * @version Jul 26, 2020
 */
class WindDirectionSensorTest {

  WindDirectionSensor WDtest = new WindDirectionSensor();

  /** test toString method */
  @Test
  void testToString() {
    assertEquals(WDtest.toString(), "Wind Direction Sensor", "Wind Direction sensor's toString method is not working");
  }

  /** test data of the sensor */
  @Test
  void testUpdateData() {
    for (int i = 0; i < 100000; i++) {
      // check if data is within the range
      assertTrue(0 <= Double.valueOf(WDtest.getDataOne()), "value of myData is negative number");
      assertTrue(WDtest.getDataTwo() == "East" || WDtest.getDataTwo() == "SouthEast" || WDtest.getDataTwo() == "South"
          || WDtest.getDataTwo() == "SouthWest" || WDtest.getDataTwo() == "West" || WDtest.getDataTwo() == "NorthWest"
          || WDtest.getDataTwo() == "North" || WDtest.getDataTwo() == "NorthEast", "Wind direction is undetermined");
      // update the data
      WDtest.updateData();
      // check again if data is within the range
      assertTrue(0 <= Double.valueOf(WDtest.getDataOne()), "value of myData is negative number");
      assertTrue(WDtest.getDataTwo() == "East" || WDtest.getDataTwo() == "SouthEast" || WDtest.getDataTwo() == "South"
          || WDtest.getDataTwo() == "SouthWest" || WDtest.getDataTwo() == "West" || WDtest.getDataTwo() == "NorthWest"
          || WDtest.getDataTwo() == "North" || WDtest.getDataTwo() == "NorthEast", "Wind direction is undetermined");
    }
  }

  /** test running method */
  @Test
  void testRun() throws InterruptedException {
    Thread thread = new Thread(WDtest);
    thread.start();
    Thread.sleep(3000L);
  }
}

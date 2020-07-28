
package junit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import sensors.BarometricSensor;

/**
 * Junit testing for Sensors.
 *
 * @author Kim Seungku
 * @version Jul 26, 2020
 */

class BarometricSensorTest {
  /** Object for setting up purpose **/
  private BarometricSensor baroTest = new BarometricSensor();

  /** test toString method **/
  @Test
  void testToString() {
    assertEquals(baroTest.toString(), "Barometric Sensor",
        "There is something wrong with Barometric Sensor toString method");
  }

  /** test data of the sensor. **/
  @Test
  void testUpdateData() {
    for (int i = 0; i < 10_000; i++) {
      // check if data is within the range
      assertTrue(16.00 <= Double.valueOf(baroTest.getDataOne()) && Double.valueOf(baroTest.getDataOne()) <= 32.50,
          "the Barometric data is not within the range");

      assertTrue(
          "Rapidly falling".equals(baroTest.getDataTwo()) || "Slowly falling".equals(baroTest.getDataTwo())
              || "Steady".equals(baroTest.getDataTwo()) || "Slowly rising".equals(baroTest.getDataTwo())
              || "Rapidly rising".equals(baroTest.getDataTwo()),
          "Barometric sensor cannot determines the rate of falling");
      // update the data
      baroTest.updateData();
      // check again if data is within the range
      assertTrue(16.00 <= Double.valueOf(baroTest.getDataOne()) && Double.valueOf(baroTest.getDataOne()) <= 32.50,
          "the Barometric data is not within the range");
      assertTrue(
          "Rapidly falling".equals(baroTest.getDataTwo()) || "Slowly falling".equals(baroTest.getDataTwo())
              || "Steady".equals(baroTest.getDataTwo()) || "Slowly rising".equals(baroTest.getDataTwo())
              || "Rapidly rising".equals(baroTest.getDataTwo()),
          "Barometric sensor cannot determines the rate of falling");
    }
  }

  /** test running method */
  @Test
  void testRun() throws InterruptedException {
    final Thread thread = new Thread(baroTest);
    thread.start();
    Thread.sleep(3000L);
  }
}


package junit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
	// check if data is within the range
	assertTrue(16.00 <= Double.valueOf(baroTest.getDataOne()) && Double.valueOf(baroTest.getDataOne()) <= 32.50,
        "the Barometric data is not within the range");
	assertTrue(
	    "Rapidly falling".equals(baroTest.getDataTwo()) || "Slowly falling".equals(baroTest.getDataTwo())
	    || "Steady".equals(baroTest.getDataTwo()) || "Slowly rising".equals(baroTest.getDataTwo())
	    || "Rapidly rising".equals(baroTest.getDataTwo()),
		"Barometric sensor cannot determines the rate of falling");  
    for (int i = 0; i < 100000; i++) {
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
    String dataOne = baroTest.getDataOne();
    
	Thread thread = new Thread(baroTest);
	thread.start();
	Thread.sleep(1000L);
	    
	// test if data is updated
	assertFalse(dataOne.equals(baroTest.getDataOne()), "Baro pressure have not updated.");
	    
    assertTrue(16.00 <= Double.valueOf(baroTest.getDataOne()) && Double.valueOf(baroTest.getDataOne()) <= 32.50,
	    "The updated baro pressure is not within the range");
    assertTrue(
        "Rapidly falling".equals(baroTest.getDataTwo()) || "Slowly falling".equals(baroTest.getDataTwo())
        || "Steady".equals(baroTest.getDataTwo()) || "Slowly rising".equals(baroTest.getDataTwo())
        || "Rapidly rising".equals(baroTest.getDataTwo()),
    	"Barometric sensor cannot determines the rate of falling");
  }
}

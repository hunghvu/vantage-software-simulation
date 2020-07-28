package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.SolarSensor;

/**
 * Junit testing for Sensors
 *
 * @author Kim Seungku
 * @version Jul 26, 2020
 */
class SolarSensorTest {

  SolarSensor SStest = new SolarSensor();

  /** test toString method */
  @Test
  void testToString() {
    assertEquals(SStest.toString(), "Solar Radiation Sensor", "Solar radiation sensor toString method is not working");
  }

  /** test data of the sensor */
  @Test
  void testUpdateData() {
    for (int i = 0; i < 100000; i++) {
      // check if data is within the range
      assertTrue(0.00 <= Double.valueOf(SStest.getDataOne()), "Solar sensor's watt is below 0");
      assertNull(SStest.getDataTwo(), "Solar second data is not null");
      // update the data
      SStest.updateData();
      // check again if data is within the range
      assertTrue(0.00 <= Double.valueOf(SStest.getDataOne()), "Solar sensor's watt is below 0");
      assertNull(SStest.getDataTwo(), "Solar second data is not null");
    }
  }

  /** test running method */
  @Test
  void testRun() throws InterruptedException {
	String dataOne = SStest.getDataOne();
	
    Thread thread = new Thread(SStest);
    thread.start();
    Thread.sleep(1000L);
    
    // test if data is updated
    assertFalse(dataOne.equals(SStest.getDataOne()), "Temp out have not updated.");
    
    assertTrue(0.00 <= Double.valueOf(SStest.getDataOne()) && Double.valueOf(SStest.getDataOne()) <= 1800
    	, "Solar sensor's watt is below 0");
  }
}

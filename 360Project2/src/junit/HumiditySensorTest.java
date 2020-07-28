package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.HumiditySensor;

/**
 * Junit testing for Sensors
 *
 * @author Kim Seungku
 * @version Jul 26, 2020
 */
class HumiditySensorTest {

  HumiditySensor HStest = new HumiditySensor();

  /** test toString method */
  @Test
  void testToString() {
    assertEquals(HStest.toString(), "Humidity Sensor", "Humidity sensor is not able to call right toString method");
  }

  /** test data of the sensor */
  @Test
  void testUpdateData() {
	// check if data is within the range
    assertTrue(0.00 <= Double.valueOf(HStest.getDataOne()) && Double.valueOf(HStest.getDataOne()) <= 100.00,
        "Humidity out is not within the range of the sensor");
    assertTrue(40.00 <= Double.valueOf(HStest.getDataTwo()) && Double.valueOf(HStest.getDataTwo()) <= 50.00,
        "Humidity in is not within the range of the sensor");
    for (int i = 0; i < 100000; i++) {
      // update the data
      HStest.updateData();
      // check again if data is within the range
      assertTrue(0.00 <= Double.valueOf(HStest.getDataOne()) && Double.valueOf(HStest.getDataOne()) <= 100.00,
          "Humidity out is not within the range of the sensor");
      assertTrue(40.00 <= Double.valueOf(HStest.getDataTwo()) && Double.valueOf(HStest.getDataTwo()) <= 50.00,
          "Humidity in is not within the range of the sensor");
    }
  }

  /** test running method */
  @Test
  void testRun() throws InterruptedException {
    String dataOne = HStest.getDataOne();
    String dataTwo = HStest.getDataTwo();
		
	Thread thread = new Thread(HStest);
	thread.start();
	Thread.sleep(1000L);
	    
	// test if data is updated
	assertFalse(dataOne.equals(HStest.getDataOne()), "Hum out have not updated.");
	assertFalse(dataTwo.equals(HStest.getDataOne()), "Hum in have not updated.");
	    
	assertTrue(0.00 <= Double.valueOf(HStest.getDataOne()) && Double.valueOf(HStest.getDataOne()) <= 100.00,
	    "Updated hum out is not within the range of the sensor");
	assertTrue(40.00 <= Double.valueOf(HStest.getDataTwo()) && Double.valueOf(HStest.getDataTwo()) <= 50.00,
	    "Updated hum in is not within the range of the sensor");
  }
}

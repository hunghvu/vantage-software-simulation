package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.RainSensor;

/**
 * Junit testing for Sensors
 *
 * @author Kim Seungku
 * @version Jul 26, 2020
 */
class RainSensorTest {

  RainSensor RStest = new RainSensor();

  /** test toString method */
  @Test
  void testToString() {
    assertEquals(RStest.toString(), "Rain Sensor", "Rain Sensor toString method is not working");
  }

  /** test data of the sensor */
  @Test
  void testUpdateData() {
	// check if data is within the range
	assertTrue(0.00 <= Double.valueOf(RStest.getDataOne()) && Double.valueOf(RStest.getDataOne()) <= 30.00,
		"Rain rate is not withing in the range of 0 to 30.");
	assertTrue(0.00 <= Double.valueOf(RStest.getDataTwo()) && Double.valueOf(RStest.getDataTwo()) <= 99.99,
        "Rain falling meter is not within range of 0 to 99.99.");  
    for (int i = 0; i < 100000; i++) {
      // update the data
      RStest.updateData();
      // check again if data is within the range
      assertTrue(0.00 <= Double.valueOf(RStest.getDataOne()) && Double.valueOf(RStest.getDataOne()) <= 30.00,
          "Rain rate is not withing in the range of 0 to 30.");
      assertTrue(0.00 <= Double.valueOf(RStest.getDataTwo()) && Double.valueOf(RStest.getDataTwo()) <= 99.99,
          "Rain falling meter is not within range of 0 to 99.99.");
    }
  }

  /** test running method */
  @Test
  void testRun() throws InterruptedException {
	String dataOne = RStest.getDataOne();
	String dataTwo = RStest.getDataTwo();
	
    Thread thread = new Thread(RStest);
    thread.start();
    Thread.sleep(1000L);
    
    // test if data is updated
    assertFalse(dataOne.equals(RStest.getDataOne()), "Rain rate have not updated.");
    assertFalse(dataTwo.equals(RStest.getDataOne()), "Rain fall have not updated.");
    
    assertTrue(0.00 <= Double.valueOf(RStest.getDataOne()) && Double.valueOf(RStest.getDataOne()) <= 30.00,
        "Updated rain rate is not withing in the range of 0 to 30.");
    assertTrue(0.00 <= Double.valueOf(RStest.getDataTwo()) && Double.valueOf(RStest.getDataTwo()) <= 99.99,
        "Updated rain falling meter is not within range of 0 to 99.99.");
  }
}

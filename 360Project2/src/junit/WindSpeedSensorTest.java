package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.TemperatureSensor;
import sensors.WindSpeedSensor;

/**
 * Junit testing for Sensors
 *
 * @author Kim Seungku
 * @version Jul 26, 2020
 */
class WindSpeedSensorTest {

  WindSpeedSensor WStest = new WindSpeedSensor();

  /** test toString method */
  @Test
  void testToString() {
    assertEquals(WStest.toString(), "Wind Speed Sensor", "Wind Speed Sensor's toString method is not working");
  }

  /** test data of the sensor */
  @Test
  void testUpdateData() {
	// check if data is within the range
	assertTrue(0.00 <= Double.valueOf(WStest.getDataOne()) && Double.valueOf(WStest.getDataOne()) <= 200.00,
	    "Wind speed is not between 0 to 200");
	assertTrue(-110.00 <= Double.valueOf(WStest.getDataTwo()) && Double.valueOf(WStest.getDataTwo()) <= 135.00,
		"Chillyness is not between -110 to 135");  
    for (int i = 0; i < 10000; i++) {
      // update the data
      WStest.updateData();
      // check again if data is within the range
      assertTrue(0.00 <= Double.valueOf(WStest.getDataOne()) && Double.valueOf(WStest.getDataOne()) <= 200.00,
          "Wind speed is not between 0 to 200");
      assertTrue(-110.00 <= Double.valueOf(WStest.getDataTwo()) && Double.valueOf(WStest.getDataTwo()) <= 135.00,
          "Chillyness is not between -110 to 135");
    }
  }

  /** test running method */
  @Test
  void testRun() throws InterruptedException {
	String dataOne = WStest.getDataOne();
	String dataTwo = WStest.getDataTwo();
		
	Thread thread = new Thread(WStest);
	thread.start();
	Thread.sleep(1000L);
	    
	// test if data is updated
	assertFalse(dataOne.equals(WStest.getDataOne()), "Wind speed have not updated.");
	assertFalse(dataTwo.equals(WStest.getDataOne()), "Wind chill have not updated.");
	    
	assertTrue(0.00 <= Double.valueOf(WStest.getDataOne()) && Double.valueOf(WStest.getDataOne()) <= 200.00,
	    "Updated wind speed is not between 0 to 200");
	assertTrue(-110.00 <= Double.valueOf(WStest.getDataTwo()) && Double.valueOf(WStest.getDataTwo()) <= 135.00,
	    "Updated wind chill is not between -110 to 135");
  }

}

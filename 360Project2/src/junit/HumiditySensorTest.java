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
	
	/**test toString method*/
	@Test
	void testToString() {
		assertEquals(HStest.toString(), "Humidity Sensor", "Humidity sensor is not able to call right toString method");
	}
	
	/**test data of the sensor*/
	@Test
	void testUpdateData() {
		for(int i = 0; i < 100000; i ++) {
			//check if data is within the range
			assertTrue("Humidity out is not within the range of the sensor",
				   0.00 <= Double.valueOf(HStest.getDataOne()) && Double.valueOf(HStest.getDataOne()) <= 100.00);
			assertTrue("Humidity in is not within the range of the sensor",
				   40.00 <= Double.valueOf(HStest.getDataTwo()) && Double.valueOf(HStest.getDataTwo()) <= 50.00);
			//update the data
			HStest.updateData();
			//check again if data is within the range
			assertTrue("Humidity out is not within the range of the sensor",
				0.00 <= Double.valueOf(HStest.getDataOne()) && Double.valueOf(HStest.getDataOne()) <= 100.00);
			assertTrue("Humidity in is not within the range of the sensor",
				   40.00 <= Double.valueOf(HStest.getDataTwo()) && Double.valueOf(HStest.getDataTwo()) <= 50.00);
		}
	}
	
	/**test running method*/
	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(HStest);
		thread.start();
		Thread.sleep(3000L);
	}
}

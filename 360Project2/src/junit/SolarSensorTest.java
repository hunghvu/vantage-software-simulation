package junit;

import static org.junit.Assert.assertNull;
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
	
	/**test toString method*/
	@Test
	void testToString() {
		assertEquals(SStest.toString(), "Solar Radiation Sensor", "Solar radiation sensor toString method is not working");
	}
	
	/**test data of the sensor*/
	@Test
	void testUpdateData() {
		for(int i = 0; i < 100000; i ++) {
			//check if data is within the range
			assertTrue("Solar sensor's watt is below 0",
				   0.00 <= Double.valueOf(SStest.getDataOne()));
			assertNull("Solar second data is not null", SStest.getDataTwo());
			//update the data
			SStest.updateData();
			//check again if data is within the range
			assertTrue("Solar sensor's watt is below 0", 
				   0.00 <= Double.valueOf(SStest.getDataOne()));
			assertNull("Solar second data is not null", SStest.getDataTwo());
		}
	}
	
	/**test running method*/
	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(SStest);
		thread.start();
		Thread.sleep(3000L);
	}
}

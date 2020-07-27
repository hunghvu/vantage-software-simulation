

package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.BarometricSensor;

/**
 * Junit testing for Sensors
 *
 * @author Kim Seungku
 * @version Jul 26, 2020
 */

class BarometricSensorTest {

	BarometricSensor BStest = new BarometricSensor();
	
	/**test toString method*/
	@Test
	void testToString() {
		assertEquals(BStest.toString(), "Barometric Sensor");
	}
	
	/**test data of the sensor*/
	@Test
	void testUpdateData() {
		for(int i = 0; i < 10000; i ++) {
			//check if data is within the range
			assertTrue(16.00 <= Double.valueOf(BStest.getDataOne()) && Double.valueOf(BStest.getDataOne()) <= 32.50);
			assertTrue(BStest.getDataTwo() == "Rapidly falling" || BStest.getDataTwo() == "Slowly falling" 
					|| BStest.getDataTwo() == "Steady" || BStest.getDataTwo() == "Slowly rising" || BStest.getDataTwo() == "Rapidly rising");
			//update the data
			BStest.updateData();
			//check again if data is within the range
			assertTrue(16.00 <= Double.valueOf(BStest.getDataOne()) && Double.valueOf(BStest.getDataOne()) <= 32.50);
			assertTrue(BStest.getDataTwo() == "Rapidly falling" || BStest.getDataTwo() == "Slowly falling" 
					|| BStest.getDataTwo() == "Steady" || BStest.getDataTwo() == "Slowly rising" || BStest.getDataTwo() == "Rapidly rising");
		}
	}
	
	/**test running method*/
	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(BStest);
		thread.start(); 
		Thread.sleep(3000L);
	}
}

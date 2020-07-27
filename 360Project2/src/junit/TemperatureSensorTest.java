package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.BarometricSensor;
import sensors.TemperatureSensor;

/**
 * Junit testing for Sensors
 *
 * @author Kim Seungku
 * @version Jul 26, 2020
 */
class TemperatureSensorTest {

	TemperatureSensor TStest = new TemperatureSensor();

	/**test toString method*/
	@Test
	void testToString() {
		assertEquals(TStest.toString(), "Temperature Sensor");
	}

	/**test data of the sensor*/
	@Test
	void testUpdateData() {
		for(int i = 0; i < 10000; i ++) {
			//check if data is within the range
			assertTrue(-40.00 <= Double.valueOf(TStest.getDataOne()) && Double.valueOf(TStest.getDataOne()) <= 150.00);
			assertTrue(68.00 <= Double.valueOf(TStest.getDataTwo()) && Double.valueOf(TStest.getDataTwo()) <= 76.00);
			assertTrue(68.00 <= TStest.getTempIn() && TStest.getTempIn() <= 76.00);
			//update the data
			TStest.updateData();
			//check again if data is within the range
			assertTrue(-40.00 <= Double.valueOf(TStest.getDataOne()) && Double.valueOf(TStest.getDataOne()) <= 150.00);
			assertTrue(68.00 <= Double.valueOf(TStest.getDataTwo()) && Double.valueOf(TStest.getDataTwo()) <= 76.00);
			assertTrue(68.00 <= TStest.getTempIn() && TStest.getTempIn() <= 76.00);
		}
	}

	/**test running method*/
	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(TStest);
		thread.start();
		Thread.sleep(3000L);
	}
}

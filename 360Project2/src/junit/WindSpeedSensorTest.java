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

	/**test toString method*/
	@Test
	void testToString() {
		assertEquals(WStest.toString(), "Wind Speed Sensor");
	}

	/**test data of the sensor*/
	@Test
	void testUpdateData() {
		for(int i = 0; i < 10000; i ++) {
			//check if data is within the range
			assertTrue(0.00 <= Double.valueOf(WStest.getDataOne()) && Double.valueOf(WStest.getDataOne()) <= 200.00);
			assertTrue(-110.00 <= Double.valueOf(WStest.getDataTwo()) && Double.valueOf(WStest.getDataTwo()) <= 135.00);
			//update the data
			WStest.updateData();
			//check again if data is within the range
			assertTrue(0.00 <= Double.valueOf(WStest.getDataOne()) && Double.valueOf(WStest.getDataOne()) <= 200.00);
			assertTrue(-110.00 <= Double.valueOf(WStest.getDataTwo()) && Double.valueOf(WStest.getDataTwo()) <= 135.00);
		}
	}

	/**test running method*/
	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(WStest);
		thread.start();
		Thread.sleep(3000L);
	}

}

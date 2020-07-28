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
		assertEquals(WStest.toString(), "Wind Speed Sensor", "Wind Speed Sensor's toString method is not working");
	}

	/**test data of the sensor*/
	@Test
	void testUpdateData() {
		for(int i = 0; i < 10000; i ++) {
			//check if data is within the range
			assertTrue("Wind speed is not between 0 to 200",
				0.00 <= Double.valueOf(WStest.getDataOne()) && Double.valueOf(WStest.getDataOne()) <= 200.00);
			assertTrue("Chillyness is not between -110 to 135",
				-110.00 <= Double.valueOf(WStest.getDataTwo()) && Double.valueOf(WStest.getDataTwo()) <= 135.00);
			//update the data
			WStest.updateData();
			//check again if data is within the range
			assertTrue("Wind speed is not between 0 to 200",
				   0.00 <= Double.valueOf(WStest.getDataOne()) && Double.valueOf(WStest.getDataOne()) <= 200.00);
			assertTrue("Chillyness is not between -110 to 135",
				   -110.00 <= Double.valueOf(WStest.getDataTwo()) && Double.valueOf(WStest.getDataTwo()) <= 135.00);
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

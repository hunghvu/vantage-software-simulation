package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.TemperatureSensor;
import sensors.WindSpeedSensor;

class WindSpeedSensorTest {

	WindSpeedSensor WStest = new WindSpeedSensor();

	@Test
	void testToString() {
		assertEquals(WStest.toString(), "Wind Speed Sensor");
	}

	@Test
	void testUpdateData() {
		for(int i = 0; i < 10000; i ++) {
			assertTrue(0.00 <= Double.valueOf(WStest.getDataOne()) && Double.valueOf(WStest.getDataOne()) <= 200.00);
			assertTrue(-110.00 <= Double.valueOf(WStest.getDataTwo()) && Double.valueOf(WStest.getDataTwo()) <= 135.00);
			WStest.updateData();
			assertTrue(0.00 <= Double.valueOf(WStest.getDataOne()) && Double.valueOf(WStest.getDataOne()) <= 200.00);
			assertTrue(-110.00 <= Double.valueOf(WStest.getDataTwo()) && Double.valueOf(WStest.getDataTwo()) <= 135.00);
		}
	}

	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(WStest);
		thread.start();
		Thread.sleep(3000L);
	}

}

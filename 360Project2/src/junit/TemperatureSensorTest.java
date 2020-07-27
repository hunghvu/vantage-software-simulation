package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.BarometricSensor;
import sensors.TemperatureSensor;

class TemperatureSensorTest {

	TemperatureSensor TStest = new TemperatureSensor();

	@Test
	void testToString() {
		assertEquals(TStest.toString(), "Temperature Sensor");
	}

	@Test
	void testUpdateData() {
		for(int i = 0; i < 10000; i ++) {
			assertTrue(-40.00 <= Double.valueOf(TStest.getDataOne()) && Double.valueOf(TStest.getDataOne()) <= 150.00);
			assertTrue(68.00 <= Double.valueOf(TStest.getDataTwo()) && Double.valueOf(TStest.getDataTwo()) <= 76.00);
			assertTrue(68.00 <= TStest.getTempIn() && TStest.getTempIn() <= 76.00);
			TStest.updateData();
			assertTrue(-40.00 <= Double.valueOf(TStest.getDataOne()) && Double.valueOf(TStest.getDataOne()) <= 150.00);
			assertTrue(68.00 <= Double.valueOf(TStest.getDataTwo()) && Double.valueOf(TStest.getDataTwo()) <= 76.00);
			assertTrue(68.00 <= TStest.getTempIn() && TStest.getTempIn() <= 76.00);
		}
	}

	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(TStest);
		thread.start();
		Thread.sleep(3000L);
	}
}

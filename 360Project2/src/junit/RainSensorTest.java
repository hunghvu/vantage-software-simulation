package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.RainSensor;

class RainSensorTest {

	RainSensor RStest = new RainSensor();
	
	@Test
	void testToString() {
		assertEquals(RStest.toString(), "Rain Sensor");
	}
	
	@Test
	void testUpdateData() {
		for(int i = 0; i < 100000; i ++) {
			assertTrue(0.00 <= Double.valueOf(RStest.getDataOne()) && Double.valueOf(RStest.getDataOne()) <= 30.00);
			assertTrue(0.00 <= Double.valueOf(RStest.getDataTwo()) && Double.valueOf(RStest.getDataTwo()) <= 99.99);
			RStest.updateData();
			assertTrue(0.00 <= Double.valueOf(RStest.getDataOne()) && Double.valueOf(RStest.getDataOne()) <= 30.00);
			assertTrue(0.00 <= Double.valueOf(RStest.getDataTwo()) && Double.valueOf(RStest.getDataTwo()) <= 99.99);
		}
	}
	
	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(RStest);
		thread.start();
		Thread.sleep(3000L);
	}
}

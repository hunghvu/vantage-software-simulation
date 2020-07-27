package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.HumiditySensor;

class HumiditySensorTest {

	HumiditySensor HStest = new HumiditySensor();
	
	@Test
	void testToString() {
		assertEquals(HStest.toString(), "Humidity Sensor");
	}
	
	@Test
	void testUpdateData() {
		for(int i = 0; i < 100000; i ++) {
			assertTrue(0.00 <= Double.valueOf(HStest.getDataOne()) && Double.valueOf(HStest.getDataOne()) <= 100.00);
			assertTrue(40.00 <= Double.valueOf(HStest.getDataTwo()) && Double.valueOf(HStest.getDataTwo()) <= 50.00);
			HStest.updateData();
			assertTrue(0.00 <= Double.valueOf(HStest.getDataOne()) && Double.valueOf(HStest.getDataOne()) <= 100.00);
			assertTrue(40.00 <= Double.valueOf(HStest.getDataTwo()) && Double.valueOf(HStest.getDataTwo()) <= 50.00);
		}
	}
	
	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(HStest);
		thread.start();
		Thread.sleep(3000L);
	}
}

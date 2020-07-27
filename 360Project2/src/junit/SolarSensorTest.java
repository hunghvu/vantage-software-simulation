package junit;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.SolarSensor;

class SolarSensorTest {

	SolarSensor SStest = new SolarSensor();
	
	@Test
	void testToString() {
		assertEquals(SStest.toString(), "Solar Radiation Sensor");
	}
	
	@Test
	void testUpdateData() {
		for(int i = 0; i < 100000; i ++) {
			assertTrue(0.00 <= Double.valueOf(SStest.getDataOne()));
			assertNull(SStest.getDataTwo());
			SStest.updateData();
			assertTrue(0.00 <= Double.valueOf(SStest.getDataOne()));
			assertNull(SStest.getDataTwo());
		}
	}
	
	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(SStest);
		thread.start();
		Thread.sleep(3000L);
	}
}

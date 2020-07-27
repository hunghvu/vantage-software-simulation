package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.BarometricSensor;

class BarometricSensorTest {

	BarometricSensor BStest = new BarometricSensor();
	
	@Test
	void testToString() {
		assertEquals(BStest.toString(), "Barometric Sensor");
	}
	
	@Test
	void testUpdateData() {
		for(int i = 0; i < 10000; i ++) {
			assertTrue(16.00 <= Double.valueOf(BStest.getDataOne()) && Double.valueOf(BStest.getDataOne()) <= 32.50);
			assertTrue(BStest.getDataTwo() == "Rapidly falling" || BStest.getDataTwo() == "Slowly falling" 
					|| BStest.getDataTwo() == "Steady" || BStest.getDataTwo() == "Slowly rising" || BStest.getDataTwo() == "Rapidly rising");
			BStest.updateData();
			assertTrue(16.00 <= Double.valueOf(BStest.getDataOne()) && Double.valueOf(BStest.getDataOne()) <= 32.50);
			assertTrue(BStest.getDataTwo() == "Rapidly falling" || BStest.getDataTwo() == "Slowly falling" 
					|| BStest.getDataTwo() == "Steady" || BStest.getDataTwo() == "Slowly rising" || BStest.getDataTwo() == "Rapidly rising");
		}
	}
	
	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(BStest);
		thread.start(); 
		Thread.sleep(3000L);
	}
}

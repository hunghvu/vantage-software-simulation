package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sensors.TemperatureSensor;
import sensors.WindDirectionSensor;

class WindDirectionSensorTest {

	WindDirectionSensor WDtest = new WindDirectionSensor();

	@Test
	void testToString() {
		assertEquals(WDtest.toString(), "Wind Direction Sensor");
	}

	@Test
	void testUpdateData() {
		for(int i = 0; i < 100000; i ++) {
			assertTrue(0 <= Double.valueOf(WDtest.getDataOne()));
			assertTrue(WDtest.getDataTwo() == "East" || WDtest.getDataTwo() == "SouthEast" ||
					WDtest.getDataTwo() == "South" || WDtest.getDataTwo() == "SouthWest" ||
					WDtest.getDataTwo() == "West" || WDtest.getDataTwo() == "NorthWest" ||
					WDtest.getDataTwo() == "North" || WDtest.getDataTwo() == "NorthEast");
			WDtest.updateData();
			assertTrue(0 <= Double.valueOf(WDtest.getDataOne()));
			assertTrue(WDtest.getDataTwo() == "East" || WDtest.getDataTwo() == "SouthEast" ||
					WDtest.getDataTwo() == "South" || WDtest.getDataTwo() == "SouthWest" ||
					WDtest.getDataTwo() == "West" || WDtest.getDataTwo() == "NorthWest" ||
					WDtest.getDataTwo() == "North" || WDtest.getDataTwo() == "NorthEast");
		}
	}

	@Test
	void testRun() throws InterruptedException {
		Thread thread = new Thread(WDtest);
		thread.start();
		Thread.sleep(3000L);
	}
}

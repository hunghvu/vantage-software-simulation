package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import iss.IssSoftware;
import sensors.Sensor;

/**
 * Test class for ISS.
 * @author Zitao Yu
 *
 */
public class ISSTest {
	
	/**
	 * Testing the constructor of ISS.
	 * @throws IOException
	 */
	@Test
	public void testIssConstructor() throws IOException {
		IssSoftware iss = new IssSoftware();
		for(int i = 0; i < 60; i++) {
			iss = new IssSoftware();
		}
		List<Sensor> sensors = iss.getSensors();
		
		for(Sensor s : sensors) {
			if(s.toString().equals("Wind Direction Sensor")) {
				assertTrue(iss.getDataMap().get("Wind direction: ").equals(s.getDataTwo()));
			}  else if(s.toString().equals("Temperature Sensor")) {
				assertTrue(iss.getDataMap().get("Temp out: ").equals(s.getDataOne()));
				assertTrue(iss.getDataMap().get("Temp in: ").equals(s.getDataTwo()));
			} else if(s.toString().equals("Humidity Sensor")) {
				assertTrue(iss.getDataMap().get("Hum out: ").equals(s.getDataOne()));
				assertTrue(iss.getDataMap().get("Hum in: ").equals(s.getDataTwo()));
			} else if(s.toString().equals("Wind Speed Sensor")){
				assertTrue(iss.getDataMap().get("Wind speed: ").equals(s.getDataOne()));
			} else if(s.toString().equals("Barometric Sensor")) {
				assertTrue(iss.getDataMap().get("Baro pressure: ").equals(s.getDataOne()));
				assertTrue(iss.getDataMap().get("Baro trend: ").equals(s.getDataTwo()));
			} else if(s.toString().equals("Rain Sensor")) {
				assertTrue(iss.getDataMap().get("Rain rate: ").equals(s.getDataOne()));
				assertTrue(iss.getDataMap().get("Rain: ").equals(s.getDataTwo()));
			}
		}
		// test case for sunrise time
		String sunrise = iss.getDataMap().get("Sunrise time: ");
		String[] list = sunrise.split(":");
		assertTrue(Double.valueOf(list[0]) >= 4 && Double.valueOf(list[0]) <= 9);
		assertTrue(Double.valueOf(list[1]) >= 0 && Double.valueOf(list[1]) <= 60);
		
		// test case for station number
		int stationNum = Integer.valueOf(iss.getDataMap().get("Station number: "));
		assertTrue(stationNum > 9999 && stationNum < 100000);
		
		// test case for rain data points
		String[] rainList = iss.getDataMap().get("Rain graph: ").split(",");
		for(String s : rainList) {
			assertTrue(Double.valueOf(s) >= 0 && Double.valueOf(s) <= 30);
		}
	}
	
	/**
	 * Testing updateData() and run() methods in ISS
	 * @throws IOException
	 */
	@Test
	public void testRun() throws IOException {
		final IssSoftware iss = new IssSoftware();
		Thread thread = new Thread(iss);
		thread.start();
		List<Sensor> sensors = iss.getSensors();

		for(Sensor s : sensors) {
			iss.updateMap();
			if(s.toString().equals("Wind Direction Sensor")) {
				assertTrue(iss.getDataMap().get("Wind direction: ").equals(s.getDataTwo()));
			}  else if(s.toString().equals("Temperature Sensor")) {
				assertTrue(iss.getDataMap().get("Temp out: ").equals(s.getDataOne()));
				assertTrue(iss.getDataMap().get("Temp in: ").equals(s.getDataTwo()));
			} else if(s.toString().equals("Humidity Sensor")) {
				assertTrue(iss.getDataMap().get("Hum out: ").equals(s.getDataOne()));
				assertTrue(iss.getDataMap().get("Hum in: ").equals(s.getDataTwo()));
			} else if(s.toString().equals("Wind Speed Sensor")){
				assertTrue(iss.getDataMap().get("Wind speed: ").equals(s.getDataOne()));
			} else if(s.toString().equals("Barometric Sensor")) {
				assertTrue(iss.getDataMap().get("Baro pressure: ").equals(s.getDataOne()));
				assertTrue(iss.getDataMap().get("Baro trend: ").equals(s.getDataTwo()));
			} else if(s.toString().equals("Rain Sensor")) {
				assertTrue(iss.getDataMap().get("Rain rate: ").equals(s.getDataOne()));
				assertTrue(iss.getDataMap().get("Rain: ").equals(s.getDataTwo()));
			}
		}
	}
	
	/**
	 * Testing writeSerializedData() method in ISS.
	 * @throws IOException
	 */
	@Test
	public void testWriteSerializedData() throws IOException {
		final IssSoftware iss = new IssSoftware();
		Thread thread = new Thread(iss);
		thread.start();
		try {
			thread.sleep(1000L);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, String> dataMap = iss.getDataMap();
		Map<String, String> deserializedMap = new HashMap<>(14);
		FileInputStream dataInput;
		try {
			dataInput = new FileInputStream("data.ser");
			final ObjectInputStream objectInput = new ObjectInputStream(dataInput);
			
			while(true) {
				try {
					final String data = (String) objectInput.readObject();
					final String[] list = data.split(": ");
					deserializedMap.put(list[0], list[1]);
				} catch (IOException e) {
					break;
				} 
			}
			objectInput.close();
			dataInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// test if two map have the same data
		assertTrue(dataMap.get("Wind direction: ").equals(deserializedMap.get("Wind direction")));
		assertTrue(dataMap.get("Wind speed: ").equals(deserializedMap.get("Wind speed")));
		assertTrue(dataMap.get("Sunrise time: ").equals(deserializedMap.get("Sunrise time")));
		assertTrue(dataMap.get("Baro pressure: ").equals(deserializedMap.get("Baro pressure")));
		assertTrue(dataMap.get("Baro trend: ").equals(deserializedMap.get("Baro trend")));
		assertTrue(dataMap.get("Wind chill: ").equals(deserializedMap.get("Wind chill")));
		assertTrue(dataMap.get("Temp in: ").equals(deserializedMap.get("Temp in")));
		assertTrue(dataMap.get("Temp out: ").equals(deserializedMap.get("Temp out")));
		assertTrue(dataMap.get("Hum in: ").equals(deserializedMap.get("Hum in")));
		assertTrue(dataMap.get("Hum out: ").equals(deserializedMap.get("Hum out")));
		assertTrue(dataMap.get("Rain rate: ").equals(deserializedMap.get("Rain rate")));
		assertTrue(dataMap.get("Rain: ").equals(deserializedMap.get("Rain")));
		assertTrue(dataMap.get("Station number: ").equals(deserializedMap.get("Station number")));
		assertTrue(dataMap.get("Rain graph: ").equals(deserializedMap.get("Rain graph")));
	}
}

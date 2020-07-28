package junit;


import static org.junit.jupiter.api.Assertions.assertTrue;

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
 * 
 * @author Zitao Yu
 *
 */
public class ISSTest {

  /**
   * Testing the constructor of ISS.
   * 
   * @throws IOException
   */
  @Test
  public void testIssConstructor() throws IOException {
    IssSoftware iss = new IssSoftware();
    for (int i = 0; i < 60; i++) {
      iss = new IssSoftware();
    }
    List<Sensor> sensors = iss.getSensors();

    for (Sensor s : sensors) {
      if (s.toString().equals("Wind Direction Sensor")) {
        assertTrue(iss.getDataMap().get("Wind direction: ").equals(s.getDataTwo())
        	, "Wind direction did not store correctly into the data map.");
      } else if (s.toString().equals("Temperature Sensor")) {
        assertTrue(iss.getDataMap().get("Temp out: ").equals(s.getDataOne())
        	, "Temp out did not store correctly into the data map.");
        assertTrue(iss.getDataMap().get("Temp in: ").equals(s.getDataTwo())
        	, "Temp in did not store correctly into the data map.");
      } else if (s.toString().equals("Humidity Sensor")) {
        assertTrue(iss.getDataMap().get("Hum out: ").equals(s.getDataOne())
        	, "Hum out did not store correctly into the data map.");
        assertTrue(iss.getDataMap().get("Hum in: ").equals(s.getDataTwo())
        	, "Hum in did not store correctly into the data map.");
      } else if (s.toString().equals("Wind Speed Sensor")) {
        assertTrue(iss.getDataMap().get("Wind speed: ").equals(s.getDataOne())
        	, "Wind speed did not store correctly into the data map.");
      } else if (s.toString().equals("Barometric Sensor")) {
        assertTrue(iss.getDataMap().get("Baro pressure: ").equals(s.getDataOne())
        	, "Baro pressure did not store correctly into the data map.");
        assertTrue(iss.getDataMap().get("Baro trend: ").equals(s.getDataTwo())
        	, "Baro trend did not store correctly into the data map.");
      } else if (s.toString().equals("Rain Sensor")) {
        assertTrue(iss.getDataMap().get("Rain rate: ").equals(s.getDataOne())
        	, "Rain rate did not store correctly into the data map.");
        assertTrue(iss.getDataMap().get("Rain: ").equals(s.getDataTwo())
        	, "Rain fall did not store correctly into the data map.");
      }
    }
    // test case for sunrise time
    String sunrise = iss.getDataMap().get("Sunrise time: ");
    String[] list = sunrise.split(":");
	assertTrue(Double.valueOf(list[0]) >= 4 && Double.valueOf(list[0]) <= 9
		, "Hour of sunrise is out of range.");
	assertTrue(Double.valueOf(list[1]) >= 0 && Double.valueOf(list[1]) <= 60
		, "Minute of sunrise is out of range.");

    // test case for station number
    int stationNum = Integer.valueOf(iss.getDataMap().get("Station number: "));
    assertTrue(stationNum > 9999 && stationNum < 100000
    		, "Statuib number is not a five digits number.");

    // test case for rain data points
    String[] rainList = iss.getDataMap().get("Rain graph: ").split(",");
    for (String s : rainList) {
    	assertTrue(Double.valueOf(s) >= 0 && Double.valueOf(s) <= 30
    		, "Rain rate is out of range.");
    }
  }

  /**
   * Testing updateData() and run() methods in ISS
   * 
   * @throws IOException
   */
  @Test
  public void testRun() throws IOException {
    final IssSoftware iss = new IssSoftware();
    Thread thread = new Thread(iss);
    thread.start();
    List<Sensor> sensors = iss.getSensors();

    for (Sensor s : sensors) {
      iss.updateMap();
      if (s.toString().equals("Wind Direction Sensor")) {
        assertTrue(iss.getDataMap().get("Wind direction: ").equals(s.getDataTwo())
        	, "Wind direction did not update correctly.");
      } else if (s.toString().equals("Temperature Sensor")) {
        assertTrue(iss.getDataMap().get("Temp out: ").equals(s.getDataOne())
        	, "Temp out did not update correctly.");
        assertTrue(iss.getDataMap().get("Temp in: ").equals(s.getDataTwo())
        	, "Temp in did not update correctly.");
      } else if (s.toString().equals("Humidity Sensor")) {
        assertTrue(iss.getDataMap().get("Hum out: ").equals(s.getDataOne())
        	, "Hum out did not update correctly.");
        assertTrue(iss.getDataMap().get("Hum in: ").equals(s.getDataTwo())
        	, "Hum in did not update correctly.");
      } else if (s.toString().equals("Wind Speed Sensor")) {
        assertTrue(iss.getDataMap().get("Wind speed: ").equals(s.getDataOne())
        	, "Wind speed did not update correctly.");
      } else if (s.toString().equals("Barometric Sensor")) {
        assertTrue(iss.getDataMap().get("Baro pressure: ").equals(s.getDataOne())
        	, "Baro pressure did not update correctly.");
        assertTrue(iss.getDataMap().get("Baro trend: ").equals(s.getDataTwo())
        	, "Baro trend did not update correctly.");
      } else if (s.toString().equals("Rain Sensor")) {
        assertTrue(iss.getDataMap().get("Rain rate: ").equals(s.getDataOne())
        	, "Rain rate did not update correctly.");
        assertTrue(iss.getDataMap().get("Rain: ").equals(s.getDataTwo())
        	, "Rain fall did not update correctly.");
      }
    }
  }

  /**
   * Testing writeSerializedData() method in ISS.
   * 
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

      while (true) {
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
    assertTrue(dataMap.get("Wind direction: ").equals(deserializedMap.get("Wind direction"))
    	, "Wind direction data did not serialize correctly.");
    assertTrue(dataMap.get("Wind speed: ").equals(deserializedMap.get("Wind speed"))
    	, "Wind speed data did not serialize correctly.");
    assertTrue(dataMap.get("Sunrise time: ").equals(deserializedMap.get("Sunrise time"))
        , "Sunrise data did not serialize correctly.");
    assertTrue(dataMap.get("Baro pressure: ").equals(deserializedMap.get("Baro pressure"))
       	, "Baro pressure data did not serialize correctly.");
    assertTrue(dataMap.get("Baro trend: ").equals(deserializedMap.get("Baro trend"))
       	, "Baro trend data did not serialize correctly.");
    assertTrue(dataMap.get("Wind chill: ").equals(deserializedMap.get("Wind chill"))
       	, "Wind chill data did not serialize correctly.");
    assertTrue(dataMap.get("Temp in: ").equals(deserializedMap.get("Temp in"))
       	, "Temp in data did not serialize correctly.");
    assertTrue(dataMap.get("Temp out: ").equals(deserializedMap.get("Temp out"))
       	, "Temp out data did not serialize correctly.");
    assertTrue(dataMap.get("Hum in: ").equals(deserializedMap.get("Hum in"))
       	, "Hum in data did not serialize correctly.");
    assertTrue(dataMap.get("Hum out: ").equals(deserializedMap.get("Hum out"))
       	, "Hum out data did not serialize correctly.");
    assertTrue(dataMap.get("Rain rate: ").equals(deserializedMap.get("Rain rate"))
       	, "Rain rate data did not serialize correctly.");
    assertTrue(dataMap.get("Rain: ").equals(deserializedMap.get("Rain"))
       	, "Rain fall data did not serialize correctly.");
    assertTrue(dataMap.get("Station number: ").equals(deserializedMap.get("Station number"))
       	, "Station number data did not serialize correctly.");
    assertTrue(dataMap.get("Rain graph: ").equals(deserializedMap.get("Rain graph"))
       	, "Rain graph data did not serialize correctly.");
  }
}

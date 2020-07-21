package Driver;
/*
 * ISS.java
 * Seungku Kim
 */


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sensors.HumiditySensor;
import sensors.Sensor;
import sensors.SolarSensor;
import sensors.TemperatureSensor;
import sensors.WindDirectionSensor;
import sensors.WindSpeedSensor;

/**
 * ISS Class implemented serializable, it will receive data from Sensor class
 * and send it out to serialized data for output devices to use
 * @author Seungku Kim, Zitao Yu
 */
public class ISS implements Serializable, Runnable{
	/**
	 * List for sensors.
	 */
	private List<Sensor> mySensors;
	
	/**
	 * List for threads.
	 */
	private Set<Thread> myThreads;
	
	/**
	 * A map for sensors data.
	 */
	private Map<String, String> myDataMap;;

	/**
	 * Constructor for the class
	 * @throws IOException is thrown if there is no data receiving to the ISS device
	 */
	public ISS() throws IOException {
		TemperatureSensor tempSensor = new TemperatureSensor();
		HumiditySensor humiditySensor = new HumiditySensor();
		SolarSensor solarSensor = new SolarSensor();
		WindSpeedSensor windSpeedSensor = new WindSpeedSensor();
		WindDirectionSensor windDirectionSensor = new WindDirectionSensor();
		// add all sensors to a list for references
		mySensors = new ArrayList<Sensor>();
		mySensors.add(tempSensor);
		mySensors.add(humiditySensor);
		mySensors.add(solarSensor);
		mySensors.add(windSpeedSensor);
		mySensors.add(windDirectionSensor);
		
		// set up myDataMap and myThreads
		myDataMap = new HashMap<String, String>();
		myThreads = new HashSet<Thread>();
		for(Sensor s : mySensors) {
			myThreads.add(new Thread(s));
			
			// check is the sensor is WindDirectionSensor
			if(s.getHeader().equals("Wind Direction: ")) {
				myDataMap.put(s.getHeader(), ((WindDirectionSensor) s).getDirection() );
			} else {
				myDataMap.put(s.getHeader(), String.valueOf(s.getData()));
			}
		}
		// call start() on each threads
		for(Thread t : myThreads) {
			t.start();
		}
	}
	
	/**
	 * Update myDataMap.
	 */
	public void updateMap() {
		for(Sensor s : mySensors) {
			if(s.getHeader().equals("Wind Direction: ")) {
				myDataMap.replace(s.getHeader(), ((WindDirectionSensor) s).getDirection() );
			} else {
				myDataMap.replace(s.getHeader(), String.valueOf(s.getData()));
			}
		}
	}
	
	/**
	 * helper method to write serialized data with sensor data received
	 * 
	 * @throws IOException if there are no data received
	 */
	public void writeSerializedData() throws IOException {

		File f = new File("data.ser");
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		for(Map.Entry<String, String> e : myDataMap.entrySet()){
			oos.writeObject(e.getKey() + e.getValue());
		}
		oos.close();
	}
 
	/**
	 * Update myDataMap and write serialize data.
	 */
	@Override
	public void run() {
		try {
			updateMap();
			writeSerializedData();
			// for testing
			System.out.println(getSensorsInfo());
			
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.run();
	}
		
	/**
	 * --For testing purposes--
	 * Prints out the information of mySensors and myDataMap.
	 * @return sensors information
	 */
	public String getSensorsInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("----Console Receiver Output----");
		System.out.println("Map:");
		for(Sensor s : mySensors) {
			sb.append("\n");
			sb.append(s.toString());
			// print map
			System.out.println(s.getHeader() + myDataMap.get(s.getHeader()));
		}
		System.out.println();
		sb.append("\n");
		return sb.toString();
	}
}
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
	 * list for sensors
	 */
	private List<Sensor> mySensors;
	
	private Set<Thread> myThreads;
	
	private Map<String, String> myDataMap = new HashMap<String, String>();

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
		
		mySensors = new ArrayList<Sensor>();
		mySensors.add(tempSensor);
		mySensors.add(humiditySensor);
		mySensors.add(solarSensor);
		mySensors.add(windSpeedSensor);
		mySensors.add(windDirectionSensor);
		
		// add all sensor threads to the set
		myThreads = new HashSet<Thread>();
		for(Sensor s : mySensors) {
			myThreads.add(new Thread(s));
		}
		// call start() on each threads
		for(Thread t : myThreads) {
			t.start();
		}

		//writeSerializedData(); //serializing class
		// if there is output device another code should be written here to send out serialized data to the output devices
	}
	
	/**
	 * get data that is stored in Sensors and return it as a double array
	 * 
	 * @return array of data
	 */
	public double[] getSensorData() {
		double[] list = new double[5];
		for(int i = 0; i < mySensors.size(); i++) {
			list[i] = mySensors.get(i).getData();
		}
		return list;
	}
	
//	/**
//	 * update all sensors
//	 */
//	public void updateSensors() {
//		for(Sensor s : mySensors) {
//			s.updateData();
//		}
//	}
	
	/**
	 * @return sensors information
	 */
	public String getSensorsInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("----Console Receiver Output----");
		for(Sensor s : mySensors) {
			sb.append("\n");
			sb.append(s.toString());
		}
		sb.append("\n");
		return sb.toString();
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

		for(double data : getSensorData()) {
			oos.writeObject(data);
		}
		oos.close();
	}
 
	// Sensors are updating data on threads every 3 seconds, 
	// to be implement:
	// -> Store sensors data information to myDataMap every 3 seconds
	// -> Use data from the HashMap to write information to data.ser text file every 3 seconds
	@Override
	public void run() {
		
		
	}
}

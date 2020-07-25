package Driver;
/*
 * ISS.java
 * Seungku Kim
 */


import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import sensors.BarometricSensor;
import sensors.HumiditySensor;
import sensors.RainSensor;
import sensors.Sensor;
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
	 * Queue for rain data point.
	 */
	private Queue<Double> myRainDataPoint;
	
	/**
	 * Constructor for the class
	 * @throws IOException is thrown if there is no data receiving to the ISS device
	 */
	@SuppressWarnings("deprecation")
	public ISS() throws IOException {
		TemperatureSensor tempSensor = new TemperatureSensor();
		HumiditySensor humSensor = new HumiditySensor();
		WindSpeedSensor windSpdSensor = new WindSpeedSensor();
		WindDirectionSensor windDirSensor = new WindDirectionSensor();
		BarometricSensor baroSensor = new BarometricSensor();
		RainSensor	rainSensor = new RainSensor();
		
		// add all sensors to a list for references
		mySensors = new ArrayList<Sensor>();
		mySensors.add(windDirSensor);
		mySensors.add(windSpdSensor);
		mySensors.add(tempSensor);
		mySensors.add(humSensor);
		mySensors.add(baroSensor);
		mySensors.add(rainSensor);
		
		// set up myDataMap and myThreads
		myDataMap = new LinkedHashMap<String, String>();
		myDataMap.put("Wind direction: ", windDirSensor.getDataTwo() );
		myDataMap.put("Wind speed: ", windSpdSensor.getDataOne());
		// generate random sunrise time
		Random random = new Random();
		int hour = random.nextInt(6) + 4;
		int min = random.nextInt(60);
		String sunriseTime = "0" + String.valueOf(hour) + ":" + String.valueOf(min);
		myDataMap.put("Sunrise time: ", sunriseTime);
		myDataMap.put("Baro pressure: ", baroSensor.getDataOne());
		myDataMap.put("Baro trend: ", baroSensor.getDataTwo());
		myDataMap.put("Wind chill: ", windSpdSensor.getDataTwo());
		myDataMap.put("Temp in: ", tempSensor.getDataTwo());
		myDataMap.put("Temp out: ", tempSensor.getDataOne());
		myDataMap.put("Hum in: ", humSensor.getDataTwo());
		myDataMap.put("Hum out: ", humSensor.getDataOne());
		myDataMap.put("Rain rate: ", rainSensor.getDataOne());
		myDataMap.put("Rain: ", rainSensor.getDataTwo());
		// generate random station number
		int num = random.nextInt(100000);
		String formatted = String.format("%05d", num); 
		myDataMap.put("Station number: ", formatted);
		myRainDataPoint = new LinkedList<>();
		// generate 10 rain rate data points
		for(int i = 0; i< 10; i++) {
			rainSensor.updateData();
			myRainDataPoint.add(new Double(rainSensor.getDataOne()));
		}
		String dataPoint = myRainDataPoint.toString().substring(1);
		dataPoint = dataPoint.substring(0, dataPoint.length() - 1);
		myDataMap.put("Rain graph: ", dataPoint);
		
		myThreads = new HashSet<Thread>();
		for(Sensor s : mySensors) {
			myThreads.add(new Thread(s));
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
			if(s.toString().equals("Wind Direction Sensor")) {
				myDataMap.replace("Wind direction: ", s.getDataTwo());
			}  else if(s.toString().equals("Temperature Sensor")) {
				myDataMap.replace("Temp out: ", s.getDataOne());
				myDataMap.replace("Temp in: ", s.getDataTwo());
			} else if(s.toString().equals("Humidity Sensor")) {
				myDataMap.replace("Hum out: ", s.getDataOne());
				myDataMap.replace("Hum in: ", s.getDataTwo());
			} else if(s.toString().equals("Wind Speed Sensor")){
				myDataMap.replace("Wind speed: ", s.getDataOne());
				myDataMap.replace("Wind chill: ", s.getDataTwo());
			} else if(s.toString().equals("Barometric Sensor")) {
				myDataMap.replace("Baro pressure: ", s.getDataOne());
				myDataMap.replace("Baro trend: ", s.getDataTwo());
			} else if(s.toString().equals("Rain Sensor")) {
				myDataMap.replace("Rain rate: ", s.getDataOne());
				myDataMap.replace("Rain: ", s.getDataTwo());
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
			printSensorsInfo();
			
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
		
	/** --For testing purposes--
	 * Prints out the information of myDataMap.
	 */
	private void printSensorsInfo() {
		System.out.println("*******************************************");
		for(Map.Entry<String, String> e : myDataMap.entrySet()){
			System.out.println(e.getKey() + e.getValue());
		}
		System.out.println("*******************************************");
	}
}
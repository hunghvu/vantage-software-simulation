package sensors;

import java.util.Random;

public interface Sensor {
	/**
	 * Static random object for the sensors
	 */
	static Random RANDOM = new Random();
	
	/**
	 * Method to return data for that specific sensor
	 * @return the data for the sensor
	 */
	public int getData();
	
	/**
	 * Update data
	 */
	public void updateData();
	
	// might be useful
	public default String sensorType(){
		return this.getClass().getSimpleName();
	};
}
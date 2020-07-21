package sensors;

import java.util.Random;

/**
 * Sensor interface that provides outline for the sensors,
 * it extends Runnable.
 * @author Zitao Yu
 *
 */
public interface Sensor extends Runnable{
	/**
	 * Static random object for the sensors.
	 */
	static Random RANDOM = new Random();
	
	/**
	 * Method to return data for that specific sensor.
	 * @return the data for the sensor.
	 */
	public double getData();
	
	/**
	 * Method to return the header for the sensor.
	 * @return the header.
	 */
	public String getHeader();
	
	/**
	 * Update data.
	 */
	public void updateData();

	@Override
	public void run();
}
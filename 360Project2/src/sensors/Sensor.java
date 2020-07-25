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
	 * Method to return first data as a String.
	 * @return the first data for the sensor.
	 */
	public String getDataOne();
	
	/**
	 * Method to return second data as a String.
	 * @return the second data for the sensor.
	 */
	public String getDataTwo();
	
	/**
	 * Update data.
	 */
	public void updateData();
	
	@Override
	public String toString();

	@Override
	public void run();
}
package sensors;

import sensors.Sensor;

public class WindSpeedSensor implements Sensor{
	
	/** Wind speed for wind speed sensor. **/
	private int mySpeed;
	
	/**
	 * Constructor.
	 */
	public WindSpeedSensor() {
		mySpeed = RANDOM.nextInt(200);
	}
	
	@Override
	public int getData() {
		return mySpeed;
	}
	@Override
	public void updateData() {
		mySpeed += RANDOM.nextInt(21) - 10;
	}

	@Override
	public String toString() {
		return "Wind Speed(MPH): " + mySpeed;
	}
}

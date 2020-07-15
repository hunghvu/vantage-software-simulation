package sensors;

import sensors.Sensor;

public class TemperatureSensor implements Sensor{
	
	/** Temperature for temperature sensor. **/
	private int myTemp;
	
	/**
	 * Constructor.
	 */
	public TemperatureSensor() {
		myTemp = RANDOM.nextInt(191) - 40;
	}
	
	@Override
	public int getData() {
		return myTemp;
	}

	@Override
	public void updateData() {
		myTemp += RANDOM.nextInt(5) - 2;
	}
	
	@Override
	public String toString() {
		return "Temperature(F): " + myTemp;
	}
}

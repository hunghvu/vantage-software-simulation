package sensors;

import sensors.Sensor;

/**
 * Sensor to detect temperature.
 * @author Zitao Yu
 *
 */
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
	
	@Override
	public void run() {
		updateData();
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.run();
	}
}

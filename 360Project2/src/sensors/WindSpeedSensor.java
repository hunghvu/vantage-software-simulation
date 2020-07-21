package sensors;

import sensors.Sensor;

/**
 * Sensor to detect wind speed.
 * @author Zitao Yu
 *
 */
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

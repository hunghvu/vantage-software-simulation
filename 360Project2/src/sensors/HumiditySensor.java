package sensors;

import sensors.Sensor;

/**
 * Sensor to detect humidity.
 * @author Zitao Yu
 *
 */
public class HumiditySensor implements Sensor{
	
	/** Humidity percentage for humidity sensor. **/
	private int myHumidity;
	
	/**
	 * Constructor.
	 */
	public HumiditySensor() {
		myHumidity = RANDOM.nextInt(100) + 1;
	}

	@Override
	public int getData() {
		return myHumidity;
	}

	@Override
	public void updateData() {
		myHumidity += RANDOM.nextInt(3) - 1;
	}
	
	@Override
	public String toString() {
		return "Humidity: " + myHumidity + "%";
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

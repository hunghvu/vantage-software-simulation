package sensors;

import sensors.Sensor;

/**
 * Sensor to detect humidity.
 * @author Zitao Yu
 *
 */
public class HumiditySensor implements Sensor{
	
	/** Humidity percentage for humidity sensor. **/
	private double myHumidity;
	
	/**
	 * Constructor.
	 */
	public HumiditySensor() {
		myHumidity = RANDOM.nextInt(100) + 1;
		myHumidity += RANDOM.nextDouble();
		myHumidity = Math.round(myHumidity * 100.0) / 100.0;
	}

	@Override
	public double getData() {
		return myHumidity;
	}

	@Override
	public void updateData() {
		myHumidity += RANDOM.nextInt(3) - 2;
		myHumidity += RANDOM.nextDouble();
		myHumidity = Math.round(myHumidity * 100.0) / 100.0;
	}
	
	@Override
	public String toString() {
		return "Humidity: " + myHumidity + "%";
	}
	
	@Override
	public String getHeader() {
		return "Humidity: ";
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

package sensors;

import sensors.Sensor;

/**
 * Sensor to detect temperature.
 * @author Zitao Yu
 *
 */
public class TemperatureSensor implements Sensor{
	
	/** Temperature for temperature sensor. **/
	private double myTemp;
	
	/**
	 * Constructor.
	 */
	public TemperatureSensor() {
		myTemp = RANDOM.nextInt(191) - 40;
		myTemp += RANDOM.nextDouble();
		myTemp = Math.round(myTemp * 100.0) / 100.0;
	}
	
	@Override
	public double getData() {
		return myTemp;
	}

	@Override
	public void updateData() {
		myTemp += RANDOM.nextInt(5) - 1;
		myTemp += RANDOM.nextDouble();
		myTemp = Math.round(myTemp * 100.0) / 100.0;
	}
	
	@Override
	public String toString() {
		return "Temperature(F): " + myTemp;
	}
	
	@Override
	public String getHeader() {
		return "Temperature: ";
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

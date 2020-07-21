package sensors;

import sensors.Sensor;

public class SolarSensor implements Sensor{
	
	/** Watts for solar sensor. **/
	private int myWatts;
	
	/**
	 * Constructor.
	 */
	public SolarSensor() {
		myWatts = RANDOM.nextInt(1801);
	}

	@Override
	public int getData() {
		return myWatts;
	}

	@Override
	public void updateData() {
		myWatts += RANDOM.nextInt(41) - 20;
	}

	@Override
	public String toString() {
		return "Solar Radiation(Watts/sq meter): " + myWatts;
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

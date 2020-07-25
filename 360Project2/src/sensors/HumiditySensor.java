package sensors;

import sensors.Sensor;

/**
 * Sensor to detect humidity.
 * @author Zitao Yu
 *
 */
public class HumiditySensor implements Sensor{
	
	/** Outdoor humidity percentage. Range: 0% - 100% **/
	private double myHumOut;
	
	/** Indoor humidity percentage. Range: 40% - 50% **/
	private double myHumIn;
	
	/**
	 * Constructor.
	 */
	public HumiditySensor() {
		myHumOut = RANDOM.nextInt(100) + 1;
		myHumOut += RANDOM.nextDouble();
		myHumOut = Math.round(myHumOut * 100.0) / 100.0;
		myHumIn = RANDOM.nextInt(11) + 40;
		myHumIn += RANDOM.nextDouble();
		myHumIn = Math.round(myHumIn * 100.0) / 100.0;
	}

	@Override
	public String getDataOne() {
		return String.valueOf(myHumOut);
	}
	
	@Override
	public String getDataTwo() {
		return String.valueOf(myHumIn);
	}
	
	@Override
	public void updateData() {
		myHumOut += RANDOM.nextInt(4) - 2;
		myHumOut += RANDOM.nextDouble();
		myHumOut = Math.round(myHumOut * 100.0) / 100.0;
		if(myHumOut > 100) {
			myHumOut = 100;
		} else if(myHumOut < 0) {
			myHumOut = 0;
		}
		myHumIn += RANDOM.nextInt(3) - 2;
		myHumIn += RANDOM.nextDouble();
		myHumIn = Math.round(myHumIn * 100.0) / 100.0;
		if(myHumIn > 50) {
			myHumIn = 50;
		} else if(myHumIn < 40) {
			myHumIn = 40;
		}
	}
	
	@Override
	public String toString() {
		return "Humidity Sensor";
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

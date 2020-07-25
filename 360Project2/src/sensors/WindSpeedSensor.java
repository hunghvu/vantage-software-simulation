package sensors;

import sensors.Sensor;

/**
 * Sensor to detect wind speed.
 * @author Zitao Yu
 *
 */
public class WindSpeedSensor implements Sensor{
	
	/** Wind speed for wind speed sensor. Range: 0 to 200mph **/
	private double mySpeed;
	
	/** Wind chill for wind speed sensor. Range: -110 to +135°F **/
	private double myChill;
	/**
	 * Constructor.
	 */
	public WindSpeedSensor() {
		mySpeed = RANDOM.nextInt(201);
		mySpeed += RANDOM.nextDouble();
		mySpeed = Math.round(mySpeed * 100.0) / 100.0;
		myChill = RANDOM.nextInt(246) - 110;
		myChill += RANDOM.nextDouble();
		myChill = Math.round(myChill * 100.0) / 100.0;
	}
	
	@Override
	public String getDataOne() {
		return String.valueOf(mySpeed);
	}
	
	@Override
	public String getDataTwo() {
		return String.valueOf(myChill);
	}
	
	@Override
	public void updateData() {
		mySpeed += RANDOM.nextInt(20) - 10;
		mySpeed += RANDOM.nextDouble();
		mySpeed = Math.round(mySpeed * 100.0) / 100.0;
		if(mySpeed > 200) {
			mySpeed = 200;
		} else if(mySpeed < 0) {
			mySpeed = 0;
		}
		
		myChill += RANDOM.nextInt(20) - 10;
		myChill += RANDOM.nextDouble();
		myChill = Math.round(myChill * 100.0) / 100.0;
		if(myChill > 135) {
			myChill = 135;
		} else if(myChill < -110) {
			myChill = -110;
		}
	}

	@Override
	public String toString() {
		return "Wind Speed Sensor";
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

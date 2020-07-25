package sensors;

/**
 * Sensor to detect Rain.
 * @author Zitao Yu
 *
 */
public class RainSensor implements Sensor{
		
	/** Rain fall. Range: 0 to 99.99" **/
	private double myRainFall;
	
	/** Rain rate. Range: 0 to 30"/hr **/
	private double myRainRate;
	
	/**
	 * Constructor.
	 */
	public RainSensor() {
		myRainRate = RANDOM.nextInt(100);
		myRainRate += RANDOM.nextDouble();
		myRainRate = Math.round(myRainRate * 100.0) / 100.0;
		
		myRainFall = RANDOM.nextInt(30);
		myRainFall += RANDOM.nextDouble();
		myRainFall = Math.round(myRainFall * 100.0) / 100.0;
	}
	
	@Override
	public String getDataOne() {
		return String.valueOf(myRainRate);
	}

	@Override
	public String getDataTwo() {
		return String.valueOf(myRainFall);
	}

	@Override
	public void updateData() {
		myRainRate += RANDOM.nextInt(4) - 2;
		myRainRate += RANDOM.nextDouble();
		myRainRate = Math.round(myRainRate * 100.0) / 100.0;
		if(myRainRate > 30) {
			myRainRate = 30;
		} else if(myRainRate < 0) {
			myRainRate = 0;
		}
		myRainFall += RANDOM.nextInt(4) - 2;
		myRainFall += RANDOM.nextDouble();
		myRainFall = Math.round(myRainFall * 100.0) / 100.0;
		if(myRainFall > 99.99) {
			myRainFall = 99.99;
		} else if(myRainFall < 0) {
			myRainFall = 0;
		}
	}

	@Override
	public String toString() {
		return "Rain Sensor";
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

package sensors;

/**
 * Sensor to detect Barometric pressure.
 * @author Zitao Yu
 *
 */
public class BarometricSensor implements Sensor{
	
	/** Barometric pressure. Range: 16.00" to 32.50"Hg */
	private double myBaroPressure;
	
	/** A number to represent barometric trend. */
	private int myData;
	
	/** Barometric trend. */
	private String myTrend;
	
	/**
	 * Constructor.
	 */
	public BarometricSensor() {
		myBaroPressure = RANDOM.nextInt(17) + 16;
		myBaroPressure += RANDOM.nextDouble();
		myBaroPressure = Math.round(myBaroPressure * 100.0) / 100.0;
		myData = RANDOM.nextInt(5);
		updateTrend();
	}
	
	@Override
	public String getDataOne() {
		return String.valueOf(myBaroPressure);
	}

	@Override
	public String getDataTwo() {
		return myTrend;
	}

	@Override
	public void updateData() {
		myBaroPressure += RANDOM.nextInt(2) - 1;
		myBaroPressure += RANDOM.nextDouble();
		myBaroPressure = Math.round(myBaroPressure * 100.0) / 100.0;
		if(myBaroPressure > 32.50) {
			myBaroPressure = 32.50;
		} else if(myBaroPressure < 16) {
			myBaroPressure = 16;
		}
		
		if(myData % 5 == 0) {
			myData += RANDOM.nextInt(2);
		} else if(myData % 5 == 4) {
			myData -= RANDOM.nextInt(2);
		} else {
			myData += RANDOM.nextInt(3) - 1;
		}
		updateTrend();
	}
	
	@Override
	public String toString() {
		return "Barometric Sensor";
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
	
	/**
	 * Helper method to determine baro trend.
	 */
	private void updateTrend() {
		if(myData == 0) {
			myTrend = "Rapidly falling";
		} else if(myData == 1) {
			myTrend = "Slowly falling";
		} else if(myData == 2) {
			myTrend = "Steady";
		} else if(myData == 3) {
			myTrend = "Slowly rising";
		} else {
			myTrend = "Rapidly rising";
		}
	}
}

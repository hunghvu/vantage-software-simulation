package sensors;

import sensors.Sensor;

/**
 * Sensor to detect wind direction.
 * @author Zitao Yu
 *
 */
public class WindDirectionSensor implements Sensor{
	
	/** A data value that can convert to a direction. **/
	private double myData;
	
	/** Wind direction. **/
	private String myDirection;
	
	/**
	 * Constructor.
	 */
	public WindDirectionSensor() {
		myData = RANDOM.nextInt(8);
		updateDirection();
	}

	@Override
	public String getDataOne() {
		return String.valueOf(myData);
	}
	
	@Override
	public String getDataTwo() {
		return myDirection;
	}
	
	@Override
	public void updateData() {
		myData += RANDOM.nextInt(3) - 1;
		if(myData == -1) {
			myData = 7;
		}
		myData = myData % 8;
		updateDirection();
	}
	
	@Override
	public String toString() {
		return "Wind Direction Sensor";
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
	 * Helper method to determine direction with the data value.
	 */
	private void updateDirection() {
		if(myData == 0) {
			myDirection = "East";
		} else if(myData == 1) {
			myDirection = "SouthEast";
		} else if(myData == 2) {
			myDirection = "South";
		} else if(myData == 3) {
			myDirection = "SouthWest";
		} else if(myData == 4) {
			myDirection = "West";
		} else if(myData == 5) {
			myDirection = "NorthWest";
		} else if(myData == 6) {
			myDirection = "North";
		} else {
			myDirection = "NorthEast";
		}
	}
}

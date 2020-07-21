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

	/**
	 * Getter for direction.
	 * @return myDirection.
	 */
	public String getDirection() {
		return myDirection;
	}
	
	@Override
	public double getData() {
		return myData;
	}
	
	@Override
	public void updateData() {
		if (myData == 0) {
			switch(RANDOM.nextInt(3)) {
				case 0:
					myData++;
					break;
				case 1:	
					myData = 7;
					break;
			}
		} else if (myData == 7) {
			switch(RANDOM.nextInt(3)) {
				case 0:
					myData--;
					break;
				case 1:	
					myData = 0;
					break;
			}
		} else {
			myData += RANDOM.nextInt(3) - 1;
		}
		updateDirection();
	}
	
	@Override
	public String toString() {
		return "Wind Direction(Cardinal): " + myDirection;
	}
	
	@Override
	public String getHeader() {
		return "Wind Direction: ";
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

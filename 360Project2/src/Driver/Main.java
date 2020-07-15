package Driver;

import java.io.IOException;

import Driver.ISS;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		
		ISS iss = new ISS(); //Instantiate ISS Class
		
		System.out.println(iss.getSensorsInfo());
		
		// testing:
		// update sensors for 20 times
		for(int i = 0; i < 20; i++) {
			iss.updateSensors();
			System.out.println(iss.getSensorsInfo());
		}
	}

}

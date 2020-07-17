package Driver;

import java.awt.*;
import java.io.IOException;

import Driver.ISS;
import gui.view.Frame;

public class Main {

//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		System.out.println("Hello");
//
//		ISS iss = new ISS(); //Instantiate ISS Class
//
//		System.out.println(iss.getSensorsInfo());
//
//		// testing:
//		// update sensors for 20 times
//		for(int i = 0; i < 20; i++) {
//			iss.updateSensors();
//			System.out.println(iss.getSensorsInfo());
//		}
//	}
	private Main() {
	}
	public static void main(final String... theArgs) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Frame();
			}
		});
	}

}

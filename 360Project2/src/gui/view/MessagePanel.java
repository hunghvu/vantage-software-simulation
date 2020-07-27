package gui.view;

import gui.model.Connect;

import javax.swing.*;
import java.awt.*;

/**
 * A panel to show weather message
 * @author My Huynh
 */
public class MessagePanel extends JPanel implements Connect {
    /** MessagePanel dimensions (width, height). */
    private static final Dimension BOARD_SIZE = new Dimension(700, 100);
    /** Font size. */
    private static final Font dataFontBig = new Font("Courier New", Font.BOLD, 26);

    /** Label for weather message. */
    private JLabel myMessage;

    /** Temperature value. */
    private double myTemp;
    /** Humidity value. */
    private double myHum;
    /** Rain rate value. */
    private double myRainRate;

    /**
     * Constructs the message panel.
     */
    public MessagePanel() {
        super( );
        this.setPreferredSize(BOARD_SIZE);
        this.setMinimumSize(BOARD_SIZE);
        this.setVisible(true);

        myMessage = new JLabel();
        myMessage.setFont(dataFontBig);
        determineWeather();
    }

    /**
     * Determine the weather based on current weather data.
     */
    private void determineWeather() {

        boolean cloud = myHum > 75;
        boolean rain = myRainRate > 0.75;
        boolean cold = myTemp < 32;

        if (cold && rain) {
            myMessage.setText("COLD AND RAINY");
        }
        if (cold && cloud && !rain) {
            myMessage.setText("CLOUDY AND COLD");
        }
        if (!cold && cloud && !rain) {
            myMessage.setText("PARTLY CLOUDY");
        }
        if (!rain && !cloud) {
            myMessage.setText("CLEARING COOLER");
        }
        if (rain && !cold) {
            myMessage.setText("RAINY");
        }
        this.add(myMessage);

    }
    @Override
    public void changeDisplay(String data, String value) {

    }

    @Override
    public void changeDisplay(String value1, String value2, String value3) {
        myTemp = Double.parseDouble(value1);
        myHum = Double.parseDouble(value2);
        myRainRate = Double.parseDouble(value3);
        determineWeather();
    }
}

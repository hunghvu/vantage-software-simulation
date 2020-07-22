package gui.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel that can visually represent current weather, moon phase, time and date.
 * Weather icon can be updated based on current weather data. Moon phase will
 * just be static first quarter.
 *
 * @author My Huynh
 */
public class WeatherPanel extends JPanel {
    private static final Dimension BOARD_SIZE = new Dimension(700, 100);
    private static final Font dataFontMed = new Font("Courier New", Font.BOLD, 20);
    private static final EmptyBorder border = new EmptyBorder(5, 20, 5, 20);

    /** Label for the weather icon.*/
    private final JLabel myWeatherIcon;
    /** Label for the moon icon.*/
    private final JLabel myMoonIcon;
    /** Label for the current time.*/
    private final JLabel myTime;
    /** Label for the current date.*/
    private final JLabel myDate;

    /**
     * Constructs the weather panel
     */
    public WeatherPanel() {
        super( );
        this.setPreferredSize(BOARD_SIZE);
        this.setMinimumSize(BOARD_SIZE);
        this.setVisible(true);

        myWeatherIcon = new JLabel( );
        myMoonIcon = new JLabel( );
        myTime = new JLabel( );
        myDate = new JLabel( );

        myWeatherIcon.setBorder(border);
        myMoonIcon.setBorder(border);
        myTime.setBorder(border);
        myDate.setBorder(border);

        determineWeather();
        generateRandForMoonPhase();
        findCurrentDateAndTime();
    }

    /**
     * Generate a random number between 0 and 8 and choose a moon phase icon based on that random number.
     */
    private void generateRandForMoonPhase() {
        int randNum = (int) (Math.random() * 8);
        myMoonIcon.setIcon(new ImageIcon(new ImageIcon(getClass()
                .getResource("/icons/moon"+ randNum +".png"))
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        this.add(myMoonIcon);
    }


    /**
     * Collects current date and time
     */
    private void findCurrentDateAndTime() {
        myDate.setFont(dataFontMed);
        myTime.setFont(dataFontMed);
        String[] date = java.time.LocalDate.now( ).toString( ).split("-");
        myDate.setText(date[1] + "/" + date[2]);
        String[] time = java.time.LocalTime.now( ).toString( ).split(":");
        int hr = Integer.parseInt(time[0]);
        String ampm;
        if (hr >= 12) {
            ampm = "pm";
            hr -= 12;
            if (hr == 0) {
                hr = 12;
            }
        } else {
            ampm = "am";
        }
        myTime.setText(hr + ":" + time[1] + ampm);

        this.add(myTime);
        this.add(myDate);
    }

    //method to change the weather icon
    //need to implement a model class to stores data from sensors

    /**
     * Takes the current weather data and choose an appropriate weather icon
     */
    private void determineWeather(){
        double exampleTemp = 50.0;
        double exampleHumidity = 80.0;
        double exampleRainRate = 0.70;

        boolean cloud = exampleHumidity > 75;
        boolean rain = exampleRainRate > 0.75;
        boolean cold = exampleTemp < 32;

        String weather = "sun";
        if (cold && rain){
            weather = "snow";
        }
        if (cold && cloud && !rain) {
            weather = "cloudy";
        }
        if (!cold && cloud && !rain) {
            weather = "partlyCloudy";
        }
        if (!rain && !cloud) {
            weather = "sun";
        }
        if (rain && !cold) {
            weather = "rain";
        }
        myWeatherIcon.setIcon(new ImageIcon(new ImageIcon(getClass()
                .getResource("/icons/"+ weather +".png"))
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

        this.add(myWeatherIcon);

    }

}

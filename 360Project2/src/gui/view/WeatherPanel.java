package gui.view;

import gui.model.Connect;

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
public class WeatherPanel extends JPanel implements Connect {
    private static final Dimension BOARD_SIZE = new Dimension(700, 100);
    private static final Font dataFontMed = new Font("Courier New", Font.BOLD, 20);
    private static final EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
    
    //Adjustment to myWeatherIcon to interact with button. (Hung Vu)
    /** Label for the weather icon.*/
    private static final JLabel myWeatherIcon = new JLabel( );
    /** Label for the moon icon.*/
    private final JLabel myMoonIcon;
    /** Label for the current time.*/
    private final JLabel myTime;
    /** Label for the current date.*/
    private final JLabel myDate;

    private double myTemp;
    private double myHum;
    private double myRainRate;

    /**
     * Constructs the weather panel
     */
    public WeatherPanel() {
        super( );
        this.setPreferredSize(BOARD_SIZE);
        this.setMinimumSize(BOARD_SIZE);
        this.setVisible(true);


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
    
    //Getter to access weather icon.
    public static JLabel getMyweathericon() {
      return myWeatherIcon;
    }

    /**
     * Generate a random number from 1 to 6 and choose a moon phase icon based on that random number.
     */
    private void generateRandForMoonPhase() {
        int randNum = (int) (Math.random() * 6);
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

    /**
     * Takes the current weather data and choose an appropriate weather icon
     */
    private void determineWeather(){

        boolean cloud = myHum > 75;
        boolean rain = myRainRate > 0.75;
        boolean cold = myTemp < 32;

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

    @Override
    public void changeDisplay(String data, String value) {
    }

    @Override
    public void changeDisplay(String value1, String value2, String value3) {

      myTemp = Double.parseDouble(value1);
      myHum = Double.parseDouble(value2);
      myRainRate = Double.parseDouble(value3);
      determineWeather();

      repaint();
      revalidate();
        this.add(myMoonIcon);
        this.add(myTime);
        this.add(myDate);

    }
}

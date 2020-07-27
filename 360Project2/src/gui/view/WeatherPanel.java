package gui.view;

import gui.model.Connect;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * Panel that can visually represent current weather, moon phase, time and date.
 * Weather icon can be updated based on current weather data. Moon phase will
 * just be static first quarter.
 *
 * @author My Huynh
 */
@SuppressWarnings({ 
  
    "serial", "PMD.NPathComplexity", "PMD.LawOfDemeter",
    "PMD.DataflowAnomalyAnalysis", "PMD.CyclomaticComplexity"
    
})
public class WeatherPanel extends JPanel implements Connect {
  /** WeatherPanel dimensions (width, height). */
  private static final Dimension BOARD_SIZE = new Dimension(700, 100);
  /** Font size. */
  private static final Font DATA_FONT_MED = new Font("Courier New", Font.BOLD, 20);
  /** Border between panels. */
  private static final EmptyBorder BORDER = new EmptyBorder(5, 20, 5, 20);
  
  /** A helper constant used in getting current time operations. **/
  private static final int HOUR_COMPARISON = 12;

  /** Label for the weather icon. */
  private static final JLabel MY_WEATHER_ICON = new JLabel();
  /** Label for the moon icon. */
  private final JLabel myMoonIcon;
  /** Label for the current time. */
  private final JLabel myTime;
  /** Label for the current date. */
  private final JLabel myDate;

  /** Temperature value. */
  private double myTemp;
  /** Humidity value. */
  private double myHum;
  /** Rain rate value. */
  private double myRainRate;

  /**
   * Constructs the weather panel.
   */
  public WeatherPanel() {
    super();
    this.setPreferredSize(BOARD_SIZE);
    this.setMinimumSize(BOARD_SIZE);
    this.setVisible(true);

    myMoonIcon = new JLabel();
    myTime = new JLabel();
    myDate = new JLabel();

    MY_WEATHER_ICON.setBorder(BORDER);
    myMoonIcon.setBorder(BORDER);
    myTime.setBorder(BORDER);
    myDate.setBorder(BORDER);

    determineWeather();
    generateRandForMoonPhase();
    findCurrentDateAndTime();
  }

  /**
   * Getter to access weather icon.
   */
  public static JLabel getMyweathericon() {
    return MY_WEATHER_ICON;
  }

  /**
   * Generate a random number from 1 to 6 and choose a moon phase icon based on
   * that random number.
   */
  private void generateRandForMoonPhase() {
    final int randNum = (int) (Math.random() * 6);
    myMoonIcon.setIcon(new ImageIcon(new ImageIcon(
        getClass().getResource("/icons/moon" + randNum + ".png")).getImage()
        .getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
    this.add(myMoonIcon);
  }

  /**
   * Collects current date and time.
   */
  private void findCurrentDateAndTime() {
    myDate.setFont(DATA_FONT_MED);
    myTime.setFont(DATA_FONT_MED);
    final String[] date = java.time.LocalDate.now().toString().split("-");
    myDate.setText(date[1] + "/" + date[2]);
    final String[] time = java.time.LocalTime.now().toString().split(":");
    int hour = Integer.parseInt(time[0]);
    String ampm;
    if (hour >= HOUR_COMPARISON) {
      ampm = "pm";
      hour -= HOUR_COMPARISON;
      if (hour == 0) {
        hour = HOUR_COMPARISON;
      }
    } else {
      ampm = "am";
    }
    myTime.setText(hour + ":" + time[1] + ampm);

    this.add(myTime);
    this.add(myDate);
  }

  /**
   * Takes the current weather data and choose an appropriate weather icon.
   */
  private void determineWeather() {

    final boolean cloud = myHum > 75;
    final boolean rain = myRainRate > 0.75;
    final boolean cold = myTemp < 32;

    String weather = "sun";
    if (cold && rain) {
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
    MY_WEATHER_ICON.setIcon(new ImageIcon(new ImageIcon(
        getClass().getResource("/icons/" + weather + ".png")).getImage()
        .getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

    this.add(MY_WEATHER_ICON);

  }

  // Ignore.
  @Override
  public void changeDisplay(final String data, final String value) {
    // Ignore.
  }

  @Override
  public void changeDisplay(final String value1, final String value2, final String value3) {

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

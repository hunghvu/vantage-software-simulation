package gui.view;

import gui.model.Connect;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * A panel to show weather message.
 * 
 * @author My Huynh
 */
@SuppressWarnings({ "serial", "PMD.NPathComplexity", "PMD.CyclomaticComplexity" })
public class MessagePanel extends JPanel implements Connect {
  /** MessagePanel dimensions (width, height). */
  private static final Dimension BOARD_SIZE = new Dimension(700, 100);
  /** Font size. */
  private static final Font DATA_FONT_BIG = new Font("Courier New", Font.BOLD, 26);

  /** Label for weather message. */
  private final JLabel myMessage;

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
    super();
    this.setPreferredSize(BOARD_SIZE);
    this.setMinimumSize(BOARD_SIZE);
    this.setVisible(true);

    myMessage = new JLabel();
    myMessage.setFont(DATA_FONT_BIG);
    determineWeather();
  }

  /**
   * Determine the weather based on current weather data.
   */
  private void determineWeather() {

    final boolean cloud = myHum > 75;
    final boolean rain = myRainRate > 0.75;
    final boolean cold = myTemp < 32;

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
  }
}

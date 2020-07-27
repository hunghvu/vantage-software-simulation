package gui.view;

import gui.model.Connect;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * A panel to show all the console data.
 * 
 * @author My Huynh
 */
@SuppressWarnings({
    "serial", "PMD.CyclomaticComplexity", "PMD.ExcessiveMethodLength",
    "PMD.LawOfDemeter", "PMD.AvoidLiteralsInIfCondition", "PMD.ModifiedCyclomaticComplexity",
    "PMD.NPathComplexity", "PMD.NcssCount", "PMD.NcssMethodCount",
    "PMD.StdCyclomaticComplexity"
    
})
public class DataPanel extends JPanel implements Connect {
  /** DataPanel dimensions (width, height). */
  private static final Dimension BOARD_SIZE = new Dimension(700, 500);
  /** Small font size. */
  private static final Font DATA_FONT_SMALL = new Font("Courier New", Font.BOLD, 16);
  /** Big font size. */
  private static final Font DATA_FONT_BIG = new Font("Courier New", Font.BOLD, 26);
  
  /** 2 decimal place format for double value. **/
  private static final String STRING_FORMAT = "%.2f";

  /** Label for temp out. */
  private final JLabel myTempOut;
  /** Label for temp in. */
  private final JLabel myTempIn;
  /** Label for hum out. */
  private final JLabel myHumOut;
  /** Label for hum in. */
  private final JLabel myHumIn;
  /** Label for barometric. */
  private final JLabel myBaro;
  /** Label for wind chill. */
  private final JLabel myChill;
  /** Label for daily rain. */
  private final JLabel myDailyRain;
  /** Label for rain rate. */
  private final JLabel myRainRate;
  /** Label for umbrella icon. */
  private final JLabel myUmbrella;
  /** Label for baro trend icon. */
  private final JLabel myBaroTrend;
  /** Label for station number. */
  private final JLabel myStationNumber;

  /** Unit indicator for temperature. */
  private static boolean myTempUnit = true;
  /** Unit indicator for wind chill. */
  private static boolean myChillUnit = true;
  /** Unit indicator for barometric. */
  private static boolean myBaroUnit = true;
  /** Unit indicator for rain rate. */
  private static boolean myRainRateUnit = true;

  /**
   * Construct the data panel.
   */
  public DataPanel() {
    super();
    this.setPreferredSize(BOARD_SIZE);
    this.setMinimumSize(BOARD_SIZE);
    this.setVisible(true);

    myTempOut = new JLabel();
    myTempIn = new JLabel();
    myHumOut = new JLabel();
    myHumIn = new JLabel();
    myBaro = new JLabel();
    myChill = new JLabel();
    myDailyRain = new JLabel();
    myRainRate = new JLabel();
    myUmbrella = new JLabel();
    myBaroTrend = new JLabel();
    myStationNumber = new JLabel();

    setupGui();
  }

  /**
   * Set up the GUI.
   */
  private void setupGui() {

    // initialize the data labels
    final JLabel tempOutLabel = new JLabel("TEMP OUT");
    final JLabel tempInLabel = new JLabel("TEMP IN");
    final JLabel humOutLabel = new JLabel("HUM OUT");
    final JLabel humInLabel = new JLabel("HUM IN");
    final JLabel baroLabel = new JLabel("BAROMETER");
    final JLabel chillLabel = new JLabel("CHILL");
    final JLabel dailyRainLabel = new JLabel("DAILY");
    final JLabel rainRateLabel = new JLabel("RAIN RATE");
    final JLabel stationNumLabel = new JLabel("STATION NO.");

    // set font for each label
    tempOutLabel.setFont(DATA_FONT_SMALL);
    tempInLabel.setFont(DATA_FONT_SMALL);
    humOutLabel.setFont(DATA_FONT_SMALL);
    baroLabel.setFont(DATA_FONT_SMALL);
    humInLabel.setFont(DATA_FONT_SMALL);
    chillLabel.setFont(DATA_FONT_SMALL);
    dailyRainLabel.setFont(DATA_FONT_SMALL);
    rainRateLabel.setFont(DATA_FONT_SMALL);
    stationNumLabel.setFont(DATA_FONT_SMALL);

    // set font for data
    myTempOut.setFont(DATA_FONT_BIG);
    myTempIn.setFont(DATA_FONT_BIG);
    myHumOut.setFont(DATA_FONT_BIG);
    myBaro.setFont(DATA_FONT_BIG);
    myHumIn.setFont(DATA_FONT_BIG);
    myChill.setFont(DATA_FONT_BIG);
    myDailyRain.setFont(DATA_FONT_BIG);
    myRainRate.setFont(DATA_FONT_BIG);
    myStationNumber.setFont(DATA_FONT_SMALL);

    generateUmbrellaSymbol();

    final JPanel tempOutPanel = new JPanel(new BorderLayout());
    tempOutPanel.add(tempOutLabel, BorderLayout.NORTH);
    tempOutPanel.add(myTempOut, BorderLayout.SOUTH);

    final JPanel tempInPanel = new JPanel(new BorderLayout());
    tempInPanel.add(tempInLabel, BorderLayout.NORTH);
    tempInPanel.add(myTempIn, BorderLayout.SOUTH);

    final JPanel humOutPanel = new JPanel(new BorderLayout());
    humOutPanel.add(humOutLabel, BorderLayout.NORTH);
    humOutPanel.add(myHumOut, BorderLayout.SOUTH);

    final JPanel baroPanel = new JPanel(new BorderLayout());
    baroPanel.add(baroLabel, BorderLayout.NORTH);
    baroPanel.add(myBaroTrend, BorderLayout.EAST);
    baroPanel.add(myBaro, BorderLayout.SOUTH);

    final JPanel humInPanel = new JPanel(new BorderLayout());
    humInPanel.add(humInLabel, BorderLayout.NORTH);
    humInPanel.add(myHumIn, BorderLayout.SOUTH);

    final JPanel chillPanel = new JPanel(new BorderLayout());
    chillPanel.add(chillLabel, BorderLayout.NORTH);
    chillPanel.add(myChill, BorderLayout.SOUTH);

    final JPanel dailyRainPanel = new JPanel(new BorderLayout());
    dailyRainPanel.add(dailyRainLabel, BorderLayout.NORTH);
    dailyRainPanel.add(myDailyRain, BorderLayout.SOUTH);

    final JPanel rainRatePanel = new JPanel(new BorderLayout());
    rainRatePanel.add(rainRateLabel, BorderLayout.NORTH);
    rainRatePanel.add(myRainRate, BorderLayout.CENTER);

    final JPanel umbrellaPanel = new JPanel(new BorderLayout());
    umbrellaPanel.add(myUmbrella, BorderLayout.SOUTH);

    final JPanel stationNumPanel = new JPanel(new BorderLayout());
    stationNumPanel.add(stationNumLabel, BorderLayout.WEST);
    stationNumPanel.add(myStationNumber, BorderLayout.EAST);

    // layout data
    final GridBagLayout gbl = new GridBagLayout();
    this.setLayout(gbl);
    final GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(30, 30, 0, 30);
    gbc.gridwidth = 2;
    gbc.gridx = 0;
    gbc.gridy = 0;
    this.add(tempOutPanel, gbc);
    gbc.gridwidth = 2;
    gbc.gridx = 2;
    gbc.gridy = 0;
    this.add(humOutPanel, gbc);
    gbc.gridwidth = 3;
    gbc.gridx = 4;
    gbc.gridy = 0;
    this.add(baroPanel, gbc);
    gbc.gridwidth = 3;
    gbc.gridx = 0;
    gbc.gridy = 1;
    this.add(tempInPanel, gbc);
    gbc.gridwidth = 2;
    gbc.gridx = 2;
    gbc.gridy = 1;
    this.add(humInPanel, gbc);
    gbc.gridwidth = 2;
    gbc.gridx = 4;
    gbc.gridy = 1;
    this.add(chillPanel, gbc);
    gbc.gridwidth = 2;
    gbc.gridx = 0;
    gbc.gridy = 2;
    this.add(dailyRainPanel, gbc);
    gbc.gridwidth = 2;
    gbc.gridx = 2;
    gbc.gridy = 2;
    this.add(umbrellaPanel, gbc);
    gbc.gridwidth = 2;
    gbc.gridx = 4;
    gbc.gridy = 2;
    this.add(rainRatePanel, gbc);
    gbc.gridwidth = 2;
    gbc.gridx = 0;
    gbc.gridy = 3;
    this.add(stationNumPanel, gbc);

  }

  /**
   * Generate random number. If number is even, set label as an umbrella, set to
   * plain text otherwise.
   */
  private void generateUmbrellaSymbol() {
    final Random rdm = new Random();
    final int rand = rdm.nextInt();
    if (rand % 2 == 0) {
      myUmbrella.setIcon(new ImageIcon(new ImageIcon(
          this.getClass().getResource("/icons/umbrella.png")
          ).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
    } else {
      myUmbrella.setText(" ");
    }

  }

  /** Setter for temperature unit. */
  public static void setMyTempUnit(final boolean theTempUnit) {
    DataPanel.myTempUnit = theTempUnit;
  }

  /** Setter for barometric unit. */
  public static void setMyBaroUnit(final boolean theBaroUnit) {
    DataPanel.myBaroUnit = theBaroUnit;
  }

  /** Setter for wind chill unit. */
  public static void setMyChillUnit(final boolean theChillUnit) {
    DataPanel.myChillUnit = theChillUnit;
  }

  /** Setter for rain rate unit. */
  public static void setMyRainRateUnit(final boolean theRainRateUnit) {
    DataPanel.myRainRateUnit = theRainRateUnit;
  }

  // Update to reflect the buttons interaction. (Hung Vu)
  @Override
  public void changeDisplay(final String data, final String value) {

    // Add inner cases to work with unit states. (Hung Vu)

    if ("Temp out".equals(data)) {
      if (myTempUnit) {
        myTempOut.setText(value + "F");
      } else {
        final double celcius = (Double.valueOf(value) - 32) * 5 / 9;
        myTempOut.setText(String.format(STRING_FORMAT, celcius) + "C");
      }
    }
    if ("Temp in".equals(data)) {
      if (myTempUnit) {
        myTempIn.setText(value + "F");
      } else {
        final double celcius = (Double.valueOf(value) - 32) * 5 / 9;
        myTempIn.setText(String.format(STRING_FORMAT, celcius) + "C");
      }
    }
    if ("Hum out".equals(data)) {
      myHumOut.setText(value + "%");
    }
    if ("Baro pressure".equals(data)) {
      if (myBaroUnit) {
        myBaro.setText(value + "\"Hg");
      } else {
        final double mmHg = Double.valueOf(value) * 25.4;
        myBaro.setText(String.format(STRING_FORMAT, mmHg) + "mmHG");
      }
    }
    if ("Hum in".equals(data)) {
      myHumIn.setText(value + "%");
    }
    if ("Wind chill".equals(data)) {
      if (myChillUnit) {
        myChill.setText(value + "F");
      } else {
        final double celcius = (Double.valueOf(value) - 32) * 5 / 9;
        myChill.setText(String.format(STRING_FORMAT, celcius) + "C");
      }

    }
    if ("Rain".equals(data)) {
      myDailyRain.setText(value + "in");
    }
    if ("Rain rate".equals(data)) {
      if (myRainRateUnit) {
        myRainRate.setText(value + "in/hr");
      } else {
        final double mmHr = Double.valueOf(value) * 25.4;
        myRainRate.setText(String.format(STRING_FORMAT, mmHr) + "mm/hr");
      }
    }
    if ("Baro trend".equals(data)) {
      if ("Steady".equals(value)) {
        myBaroTrend.setIcon(new ImageIcon(new ImageIcon(
            getClass().getResource("/icons/rightarrow.png")
            ).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
      }
      if ("Rapidly falling".equals(value) || "Slowly falling".equals(value)) {
        myBaroTrend.setIcon(new ImageIcon(new ImageIcon(
            getClass().getResource("/icons/downarrow.png")
            ).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
      }
      if ("Rapidly rising".equals(value) || "Slowly rising".equals(value)) {
        myBaroTrend.setIcon(new ImageIcon(new ImageIcon(
            getClass().getResource("/icons/uparrow.png")
            ).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
      }
    }

    if ("Station number".equals(data)) {
      myStationNumber.setText(value);
    }
  }

  @Override
  public void changeDisplay(final String value1, final String value2, final String value3) {
    // Ignore.

  }

}

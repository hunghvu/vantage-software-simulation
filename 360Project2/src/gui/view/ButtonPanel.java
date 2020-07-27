package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * A class for the console buttons.
 *
 * @author Khue Nguyen, Hung Vu
 * @version Jul 18, 2020
 */
@SuppressWarnings({

    "serial", "PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter", 
    "PMD.RedundantFieldInitializer", "PMD.TooManyFields"

})
public class ButtonPanel extends JPanel {

  /** ButtonPanel dimensions (width, height). */
  private static final Dimension PANEL_SIZE = new Dimension(250, 700);

  /** Left and right subpanel dimensions (width, height). */
  private static final Dimension SUBPANEL_SIZE = new Dimension(PANEL_SIZE.width / 2, 500);

  /** Button dimension. */
  private static final Dimension BUTTON_SIZE = new Dimension(90, 35);

  /** Font for button label. */
  private static final Font FONT = new Font("Avenir", Font.PLAIN, 12);

  // primary buttons on the left side
  /** Prompts to display temperature. */
  private final JButton myTempBtn = new JButton("TEMP");

  /** Prompts to display humidity. */
  private final JButton myHumidityBtn = new JButton("HUM");

  /** Prompts to display wind data. */
  private final JButton myWindBtn = new JButton("WIND");

  /** Prompts to display daily rain level. */
  private final JButton myRainDayBtn = new JButton("RAIN DAY");

  /** Prompts to display yearly rain level. */
  private final JButton myRainYrBtn = new JButton("RAIN YR");

  /** Prompts to display barometric data. */
  private final JButton myBarBtn = new JButton("BAR");

  // primary buttons on the right side.
  /** Prompts to show secondary function buttons. */
  private final JButton mySecondBtn = new JButton("2ND");

  /** Prompts to display the weather forecast icons. */
  private final JButton myForecastBtn = new JButton("FORECAST");

  /** Prompts to display the graph. */
  private final JButton myGraphBtn = new JButton("GRAPH");

  /** Prompts to display the hi/low temps. */
  private final JButton myHiLowBtn = new JButton("HI/LOW");

  /** Allows to set alarms. */
  private final JButton myAlarmBtn = new JButton("ALARM");

  /** Allows a certain process to be finished. */
  private final JButton myDoneBtn = new JButton("DONE");

  // arrays of primary buttons
  /** Contains all primary buttons on left side. */
  private final JButton[] left = {

      myTempBtn, myHumidityBtn, myWindBtn, myRainDayBtn, myRainYrBtn, myBarBtn

  };

  /** Contains all primary buttons on right side. */
  private final JButton[] right = {

      mySecondBtn, myForecastBtn, myGraphBtn, myHiLowBtn, myAlarmBtn, myDoneBtn

  };

  // secondary buttons on the left side
  /** Prompts to display heat. */
  private final JButton myHeatBtn = new JButton("HEAT");

  /** Prompts to display dew point. */
  private final JButton myDewBtn = new JButton("DEW");

  /** Prompts to display wind chill. */
  private final JButton myChillBtn = new JButton("CHILL");

  /** Prompts to display solar intensity. */
  private final JButton mySolarBtn = new JButton("SOLAR");

  /** Prompts to display UV level. */
  private final JButton myUvBtn = new JButton("UV");

  /** Prompts to display evapotranspiration. */
  private final JButton myEtBtn = new JButton("ET");

  // secondary buttons on the right side
  /** Prompts to display the weather forecast icons. */
  private final JButton myTimeBtn = new JButton("TIME");

  /** Changes units of measure. */
  private final JButton myUnitsBtn = new JButton("UNITS");

  /** Clears settings. */
  private final JButton myClearBtn = new JButton("CLEAR");

  /** Confirm to set alarms. */
  private final JButton mySetBtn = new JButton("SET");

  // arrays of primary buttons
  /** Contains all secondary buttons on left side. */
  private final JButton[] left2nd = {

      myHeatBtn, myDewBtn, myChillBtn, mySolarBtn, myUvBtn, myEtBtn

  };

  /** Contains all secondary buttons on right side. */
  private final JButton[] right2nd = {

      mySecondBtn, myTimeBtn, myUnitsBtn, myClearBtn, mySetBtn, myDoneBtn

  };

  // arrow buttons
  /** Up arrow button. */
  private final JButton myUpBtn = new JButton();

  /** Down arrow button. */
  private final JButton myDownBtn = new JButton();

  /** Left arrow button. */
  private final JButton myLeftBtn = new JButton();

  /** Right arrow button. */
  private final JButton myRightBtn = new JButton();

  // panels
  /** Panel to contain all buttons on left side. */
  private final JPanel leftPanel = new JPanel();

  /** Panel to contain all buttons on right side. */
  private final JPanel rightPanel = new JPanel();

  /** Combines leftPanel and rightPanel and puts it at top. */
  private final JPanel topPanel = new JPanel();

  /** Contains arrow buttons. */
  private final JPanel bottomPanel = new JPanel();

  /**
   * State of TEMP buttons, represents how unit will be changed after a press.
   * (Hung Vu)
   */
  private static boolean myTempUnit = false;

  /**
   * State of CHILL buttons, represents how unit will be changed after a press.
   * (Hung Vu)
   */
  private static boolean myChillUnit = false;

  /**
   * State of BAR buttons, represents how unit will be changed after a press.
   * (Hung Vu)
   */
  private static boolean myBarUnit = false;

  /**
   * State of RAINyr buttons, represents how unit will be changed after a press.
   * (Hung Vu)
   */
  private static boolean myRainyrUnit = false;

  /**
   * State of FORE buttons, represents how unit will be changed after a press.
   * (Hung Vu)
   */
  private static boolean myDisplayForecast = false;
  // Not up to expectation implementation (Hung Vu)
  // private static boolean myWindUnit = false;

  /** Constructor that instantiates the buttons. */
  public ButtonPanel() {
    super();

    this.setUpPanel();
    this.addListeners();
    this.setUpLayout();
    this.addComponents();
  }

  /** Method that sets up main and subpanels' properties. */
  private void setUpPanel() {
    this.setPreferredSize(PANEL_SIZE);
    this.setMinimumSize(PANEL_SIZE);
    this.setLayout(new BorderLayout());

    topPanel.setLayout(new BorderLayout());
    topPanel.setVisible(true);

    leftPanel.setPreferredSize(SUBPANEL_SIZE);
    leftPanel.setVisible(true);
    leftPanel.setLayout(new GridBagLayout());

    rightPanel.setPreferredSize(SUBPANEL_SIZE);
    rightPanel.setVisible(true);
    rightPanel.setLayout(new GridBagLayout());

    bottomPanel.setLayout(new GridBagLayout());
    bottomPanel.setVisible(true);
  }

  /** Method that adds listeners to buttons. */
  // Add action listener for Temp, Wind, Chill, Bar, Rainyr (Hung Vu)
  private void addListeners() {

    int[] secClick = { 0 }; // gets around enclosing variable error when using lambdas
    mySecondBtn.addActionListener(theEvent -> {
      if (secClick[0] % 2 == 0) {
        for (int i = 0; i < left.length; i++) {
          left[i].setVisible(false);
          left[i].setEnabled(false);
          left2nd[i].setVisible(true);
          left2nd[i].setEnabled(true);

          if (!right[i].equals(right2nd[i])) {
            right[i].setVisible(false);
            right[i].setEnabled(false);
            right2nd[i].setVisible(true);
            right2nd[i].setEnabled(true);
          }
        }
      } else {
        for (int i = 0; i < left.length; i++) {
          left[i].setVisible(true);
          left[i].setEnabled(true);
          left2nd[i].setVisible(false);
          left2nd[i].setEnabled(false);

          if (!right[i].equals(right2nd[i])) {
            right[i].setVisible(true);
            right[i].setEnabled(true);
            right2nd[i].setVisible(false);
            right2nd[i].setEnabled(false);
          }
        }
      }
      secClick[0] = secClick[0] + 1;
    });

    // Main function for buttons (Hung Vu).
    myTempBtn.addActionListener(theEvent -> {
      DataPanel.setMyTempUnit(myTempUnit);
      myTempUnit = !myTempUnit;
    });

    myChillBtn.addActionListener(theEvent -> {
      DataPanel.setMyChillUnit(myChillUnit);
      myChillUnit = !myChillUnit;
    });

    myBarBtn.addActionListener(theEvent -> {
      DataPanel.setMyBaroUnit(myBarUnit);
      myBarUnit = !myBarUnit;
    });

    myRainYrBtn.addActionListener(theEvent -> {
      DataPanel.setMyRainRateUnit(myRainyrUnit);
      myRainyrUnit = !myRainyrUnit;
    });

    // Not up to expectation (Hung Vu)
    // myWindBtn.addActionListener(theEvent -> {
    // WindCompassPanel.setMyWindSpeedUnit(myWindUnit);
    // myWindUnit = !myWindUnit;
    // });

    myForecastBtn.addActionListener(theEvent -> {
      WeatherPanel.getMyweathericon().setVisible(myDisplayForecast);
      myDisplayForecast = !myDisplayForecast;
    });

  }

  /** Method that sets up layout of buttons. */
  private void setUpLayout() {
    final GridBagConstraints con = new GridBagConstraints();
    con.gridx = 0;
    con.insets = new Insets(0, 0, 30, 30);

    // left
    for (int i = 0; i < left.length; i++) {
      left[i].setFont(FONT);
      left[i].setPreferredSize(BUTTON_SIZE);

      left2nd[i].setFont(FONT);
      left2nd[i].setPreferredSize(BUTTON_SIZE);
      left2nd[i].setVisible(false);
      left2nd[i].setEnabled(false);

      con.gridy = i;
      leftPanel.add(left[i], con);
      leftPanel.add(left2nd[i], con);

    }

    // right
    for (int i = 0; i < right.length; i++) {
      right[i].setFont(FONT);
      right[i].setPreferredSize(BUTTON_SIZE);

      right2nd[i].setFont(FONT);
      right2nd[i].setPreferredSize(BUTTON_SIZE);

      if (!right[i].equals(right2nd[i])) {
        right2nd[i].setVisible(false);
        right2nd[i].setEnabled(false);
      }

      con.gridy = i;
      rightPanel.add(right[i], con);
      rightPanel.add(right2nd[i], con);
    }

    // arrow buttons
    con.insets = new Insets(2, 2, 2, 2);

    myUpBtn.setIcon(new ImageIcon(
        new ImageIcon(getClass().getResource("/icons/upbutton.png"))
        .getImage().getScaledInstance(25, 50, Image.SCALE_SMOOTH)));
    con.gridx = 1;
    con.gridy = 0;
    bottomPanel.add(myUpBtn, con);

    myDownBtn.setIcon(new ImageIcon(
        new ImageIcon(getClass().getResource("/icons/downbutton.png"))
        .getImage().getScaledInstance(25, 50, Image.SCALE_SMOOTH)));
    con.gridx = 1;
    con.gridy = 2;
    bottomPanel.add(myDownBtn, con);

    myLeftBtn.setIcon(new ImageIcon(
        new ImageIcon(getClass().getResource("/icons/leftbutton.png"))
        .getImage().getScaledInstance(50, 25, Image.SCALE_SMOOTH)));
    con.gridx = 0;
    con.gridy = 1;
    bottomPanel.add(myLeftBtn, con);

    myRightBtn.setIcon(new ImageIcon(
        new ImageIcon(getClass().getResource("/icons/rightbutton.png"))
        .getImage().getScaledInstance(50, 25, Image.SCALE_SMOOTH)));
    con.gridx = 2;
    con.gridy = 1;
    bottomPanel.add(myRightBtn, con);

  }

  private void addComponents() {
    topPanel.add(leftPanel, BorderLayout.WEST);
    topPanel.add(rightPanel, BorderLayout.EAST);
    this.add(topPanel, BorderLayout.NORTH);
    this.add(bottomPanel, BorderLayout.SOUTH);
  }

}

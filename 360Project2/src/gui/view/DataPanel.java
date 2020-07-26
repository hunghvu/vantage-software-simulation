package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import gui.model.Connect;
import gui.model.DeserializedData;

/**
 * A panel to show all the console data
 * @author My Huynh
 */
public class DataPanel extends JPanel implements Connect {
    private static final Dimension BOARD_SIZE = new Dimension(700, 500);
    private static final Font dataFontSmall = new Font("Courier New", Font.BOLD, 16);
    private static final Font dataFontBig = new Font("Courier New", Font.BOLD, 26);

    private final JLabel myTempOut;
    private final JLabel myTempIn;
    private final JLabel myHumOut;
    private final JLabel myHumIn;
    private final JLabel myBaro;
    private final JLabel myChill;
    private final JLabel myDailyRain;
    private final JLabel myRainRate;
    private final JLabel myUmbrella;
    private final JLabel myBaroTrend;
    private final JLabel myStationNumber;

    /**
     * Construct the data panel
     */
    public DataPanel() {
        super( );
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

        setupGUI();
    }

    /**
     * Set up the GUI
     */
    private void setupGUI() {

        // initialize the data labels
        JLabel tempOutLabel = new JLabel("TEMP OUT");
        JLabel tempInLabel = new JLabel("TEMP IN");
        JLabel humOutLabel = new JLabel("HUM OUT");
        JLabel humInLabel = new JLabel("HUM IN");
        JLabel baroLabel = new JLabel("BAROMETER");
        JLabel chillLabel = new JLabel("CHILL");
        JLabel dailyRainLabel = new JLabel("DAILY");
        JLabel rainRateLabel = new JLabel("RAIN RATE");
        JLabel stationNumberLabel = new JLabel("STATION NO.");

        // set font for each label
        tempOutLabel.setFont(dataFontSmall);
        tempInLabel.setFont(dataFontSmall);
        humOutLabel.setFont(dataFontSmall);
        baroLabel.setFont(dataFontSmall);
        humInLabel.setFont(dataFontSmall);
        chillLabel.setFont(dataFontSmall);
        dailyRainLabel.setFont(dataFontSmall);
        rainRateLabel.setFont(dataFontSmall);
        stationNumberLabel.setFont(dataFontSmall);

        // set font for data
        myTempOut.setFont(dataFontBig);
        myTempIn.setFont(dataFontBig);
        myHumOut.setFont(dataFontBig);
        myBaro.setFont(dataFontBig);
        myHumIn.setFont(dataFontBig);
        myChill.setFont(dataFontBig);
        myDailyRain.setFont(dataFontBig);
        myRainRate.setFont(dataFontBig);
        myStationNumber.setFont(dataFontSmall);

        generateUmbrellaSymbol();

        JPanel tempOutPanel = new JPanel(new BorderLayout());
        tempOutPanel.add(tempOutLabel, BorderLayout.NORTH);
        tempOutPanel.add(myTempOut, BorderLayout.SOUTH);

        JPanel tempInPanel = new JPanel(new BorderLayout());
        tempInPanel.add(tempInLabel, BorderLayout.NORTH);
        tempInPanel.add(myTempIn, BorderLayout.SOUTH);

        JPanel humOutPanel = new JPanel(new BorderLayout());
        humOutPanel.add(humOutLabel, BorderLayout.NORTH);
        humOutPanel.add(myHumOut, BorderLayout.SOUTH);

        JPanel baroPanel = new JPanel(new BorderLayout());
        baroPanel.add(baroLabel, BorderLayout.NORTH);
        baroPanel.add(myBaroTrend, BorderLayout.EAST);
        baroPanel.add(myBaro, BorderLayout.SOUTH);

        JPanel humInPanel = new JPanel(new BorderLayout());
        humInPanel.add(humInLabel, BorderLayout.NORTH);
        humInPanel.add(myHumIn, BorderLayout.SOUTH);

        JPanel chillPanel = new JPanel(new BorderLayout());
        chillPanel.add(chillLabel, BorderLayout.NORTH);
        chillPanel.add(myChill, BorderLayout.SOUTH);

        JPanel dailyRainPanel = new JPanel(new BorderLayout());
        dailyRainPanel.add(dailyRainLabel, BorderLayout.NORTH);
        dailyRainPanel.add(myDailyRain, BorderLayout.SOUTH);

        JPanel rainRatePanel = new JPanel(new BorderLayout());
        rainRatePanel.add(rainRateLabel, BorderLayout.NORTH);
        rainRatePanel.add(myRainRate, BorderLayout.CENTER);


        JPanel umbrellaPanel = new JPanel(new BorderLayout());
        umbrellaPanel.add(myUmbrella, BorderLayout.SOUTH);

        JPanel stationNumberPanel = new JPanel(new BorderLayout());
        stationNumberPanel.add(stationNumberLabel, BorderLayout.WEST);
        stationNumberPanel.add(myStationNumber, BorderLayout.EAST);


        // layout data
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
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
        this.add(stationNumberPanel, gbc);

    }

    /**
     * Generate random number. If number is even, set label as an umbrella, set to plain text otherwise.
     */
    private void generateUmbrellaSymbol() {
        Random rd = new Random();
        int rand = rd.nextInt();
        if (rand % 2 == 0) {
            myUmbrella.setIcon(new ImageIcon(new ImageIcon(this.getClass( )
                    .getResource("/icons/umbrella.png"))
                    .getImage( ).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        }
        else {
            myUmbrella.setText(" ");
        }

    }

    @Override
    public void changeDisplay(String data, String value) {

        String fahrenheit = "F";
        if(data.equals("Temp out")) {
            myTempOut.setText(value + fahrenheit);
        }
        if(data.equals("Temp in")) {
            myTempIn.setText(value + fahrenheit);
        }
        if(data.equals("Hum out")) {
            myHumOut.setText(value + "%");
        }
        if(data.equals("Baro pressure")) {
            myBaro.setText(value + "\"Hg");
        }
        if(data.equals("Hum in")) {
            myHumIn.setText(value + "%");
        }
        if(data.equals("Wind chill")) {
            myChill.setText(value + fahrenheit);
        }
        if(data.equals("Rain")) {
            myDailyRain.setText(value + "in");
        }
        if(data.equals("Rain rate")) {
            myRainRate.setText(value + "in/hr");
        }
        if(data.equals("Baro trend")) {
            if (value.equals("Steady")) {
                myBaroTrend.setIcon(new ImageIcon(new ImageIcon(getClass()
                        .getResource("/icons/rightarrow.png"))
                        .getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
            } if (value.equals("Rapidly falling") || value.equals("Slowly falling")) {
                myBaroTrend.setIcon(new ImageIcon(new ImageIcon(getClass()
                        .getResource("/icons/downarrow.png"))
                        .getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
            }  if (value.equals("Rapidly rising") || value.equals("Slowly rising")) {
                myBaroTrend.setIcon(new ImageIcon(new ImageIcon(getClass( )
                        .getResource("/icons/uparrow.png"))
                        .getImage( ).getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
            }
        }

        if(data.equals("Station number")) {
            myStationNumber.setText(value);
        }
    }

    @Override
    public void changeDisplay(String value1, String value2, String value3) {
      // TODO Auto-generated method stub
      
    }

}


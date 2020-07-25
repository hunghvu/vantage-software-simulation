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
import javax.swing.border.EmptyBorder;

import gui.model.Connect;
import gui.model.DeserializedData;

/**
 * A panel to show all the console data
 * @author My Huynh
 */
public class DataPanel extends JPanel implements Connect {
    private static final Dimension BOARD_SIZE = new Dimension(700, 600);
    private static final Dimension PANEL_SIZE = new Dimension(170, 200);
    private static final Font dataFontSmall = new Font("Courier New", Font.BOLD, 16);
    private static final Font dataFontBig = new Font("Courier New", Font.BOLD, 26);
    private static final EmptyBorder border = new EmptyBorder(5, 10, 5, 10);

    //Display values with decimal format of 1 decimal place.
  //  private DecimalFormat df = new DecimalFormat("0.0");

    private final JLabel myTempOut;
    private final JLabel myTempIn;
    private final JLabel myHumOut;
//    private final JLabel myHumIn;
    private final JLabel myBaro;
    private final JLabel myUV;
    private final JLabel myDewPoint;
    private final JLabel myDailyRain;
    private final JLabel myMonthlyRain;
    private final JLabel myUmbrella;

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
   //     myHumIn = new JLabel();
        myBaro = new JLabel();
        myUV = new JLabel();
        myDewPoint = new JLabel();
        myDailyRain = new JLabel();
        myMonthlyRain = new JLabel();
        myUmbrella = new JLabel();

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
    //    JLabel humInLabel = new JLabel("HUM IN");
        JLabel baroLabel = new JLabel("BAROMETER");
        JLabel uVLabel = new JLabel("UV");
        JLabel dewPointLabel = new JLabel("DEW POINT");
        JLabel dailyRainLabel = new JLabel("DAILY RAIN");
        JLabel monthlyRainLabel = new JLabel("MO");

        // set font for each label
        tempOutLabel.setFont(dataFontSmall);
        tempInLabel.setFont(dataFontSmall);
        humOutLabel.setFont(dataFontSmall);
        baroLabel.setFont(dataFontSmall);
        uVLabel.setFont(dataFontSmall);
        dewPointLabel.setFont(dataFontSmall);
        dailyRainLabel.setFont(dataFontSmall);
        monthlyRainLabel.setFont(dataFontSmall);

        // set font for data
        myTempOut.setFont(dataFontBig);
        myTempIn.setFont(dataFontBig);
        myHumOut.setFont(dataFontBig);
        myBaro.setFont(dataFontBig);
        myUV.setFont(dataFontBig);
        myDewPoint.setFont(dataFontBig);
        myDailyRain.setFont(dataFontBig);
        myMonthlyRain.setFont(dataFontBig);

        generateUmbrellaSymbol();

        JPanel tempOutPanel = new JPanel(new BorderLayout());
        //tempOutPanel.setPreferredSize(PANEL_SIZE);
        tempOutPanel.add(tempOutLabel, BorderLayout.NORTH);
        tempOutPanel.add(myTempOut, BorderLayout.SOUTH);
        //tempOutPanel.setBorder(border);

        JPanel tempInPanel = new JPanel(new BorderLayout());
        //tempInPanel.setPreferredSize(PANEL_SIZE);
        tempInPanel.add(tempInLabel, BorderLayout.NORTH);
        tempInPanel.add(myTempIn, BorderLayout.SOUTH);
        //tempInPanel.setBorder(border);

        JPanel humOutPanel = new JPanel(new BorderLayout());
        //humOutPanel.setPreferredSize(PANEL_SIZE);
        humOutPanel.add(humOutLabel, BorderLayout.NORTH);
        humOutPanel.add(myHumOut, BorderLayout.SOUTH);
        //humOutPanel.setBorder(border);

        JPanel baroPanel = new JPanel(new BorderLayout());
        //baroPanel.setPreferredSize(PANEL_SIZE);
        baroPanel.add(baroLabel, BorderLayout.NORTH);
        baroPanel.add(myBaro, BorderLayout.SOUTH);
        //baroPanel.setBorder(border);

        JPanel uVPanel = new JPanel(new BorderLayout());
        //uVPanel.setPreferredSize(PANEL_SIZE);
        uVPanel.add(uVLabel, BorderLayout.NORTH);
        uVPanel.add(myUV, BorderLayout.SOUTH);
        //uVPanel.setBorder(border);

        JPanel dewPointPanel = new JPanel(new BorderLayout());
        //dewPointPanel.setPreferredSize(PANEL_SIZE);
        dewPointPanel.add(dewPointLabel, BorderLayout.NORTH);
        dewPointPanel.add(myDewPoint, BorderLayout.SOUTH);
        //dewPointPanel.setBorder(border);

        JPanel dailyRainPanel = new JPanel(new BorderLayout());
        //dailyRainPanel.setPreferredSize(PANEL_SIZE);
        dailyRainPanel.add(dailyRainLabel, BorderLayout.NORTH);
        dailyRainPanel.add(myDailyRain, BorderLayout.SOUTH);
        //dailyRainPanel.setBorder(border);

        JPanel monthlyRainPanel = new JPanel(new BorderLayout());
        //monthlyRainPanel.setPreferredSize(PANEL_SIZE);
        monthlyRainPanel.add(monthlyRainLabel, BorderLayout.NORTH);
        monthlyRainPanel.add(myMonthlyRain, BorderLayout.CENTER);
        //monthlyRainPanel.setBorder(border);

        JPanel umbrellaPanel = new JPanel(new BorderLayout());
        //umbrellaPanel.setPreferredSize(PANEL_SIZE);
        umbrellaPanel.add(myUmbrella, BorderLayout.SOUTH);
        //monthlyRainLabel.setBorder(border);

        // layout data
        GridBagLayout gbl = new GridBagLayout();
//        gbl.columnWeights = new double[] {1.0, 1.0, 1.0, Double.MIN_VALUE};
//        gbl.rowWeights = new double[] {1.0, 1.0, 1.0, Double.MIN_VALUE};
        this.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
//        myTempOut.setText("100.0" + "\u2109");
//        myTempIn.setText("100.0" + "\u2109");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 30, 30, 30);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(tempOutPanel, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(humOutPanel, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 4;
        gbc.gridy = 0;
        this.add(baroPanel, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(tempInPanel, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(uVPanel, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 4;
        gbc.gridy = 1;
        this.add(dewPointPanel, gbc);
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
        this.add(monthlyRainPanel, gbc);

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
            myBaro.setText(value + "in");
        }
        if(data.equals("Solar")) {
            myUV.setText(value + "index");
        }
        if(data.equals("Dew point")) {
            myDewPoint.setText(value + fahrenheit);
        }
        if(data.equals("Rain")) {
            myDailyRain.setText(value + "in");
        }
        if(data.equals("Monthly rain")) {
            myMonthlyRain.setText(value + "in");
        }
    }

    @Override
    public void changeDisplay(String value1, String value2, String value3) {
      // TODO Auto-generated method stub
      
    }

}


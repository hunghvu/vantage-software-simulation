package gui.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * A panel to show all the console data
 * @author My Huynh
 */
public class DataPanel extends JPanel {
    private static final Dimension BOARD_SIZE = new Dimension(700, 500);
    private static final Font dataFontSmall = new Font("Courier New", Font.BOLD, 16);
    private static final Font dataFontBig = new Font("Courier New", Font.BOLD, 30);
    private static final EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
    //Display values with decimal format of 1 decimal place.
    private DecimalFormat df = new DecimalFormat("0.0");


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

        updateDataVals();
        generateUmbrellaSymbol();

        JPanel tempOutPanel = new JPanel(new BorderLayout());
        tempOutPanel.add(tempOutLabel, BorderLayout.NORTH);
        tempOutPanel.add(myTempOut, BorderLayout.SOUTH);
        tempOutPanel.setBorder(border);

        JPanel tempInPanel = new JPanel(new BorderLayout());
        tempInPanel.add(tempInLabel, BorderLayout.NORTH);
        tempInPanel.add(myTempIn, BorderLayout.SOUTH);
        tempInPanel.setBorder(border);

        JPanel humOutPanel = new JPanel(new BorderLayout());
        humOutPanel.add(humOutLabel, BorderLayout.NORTH);
        humOutPanel.add(myHumOut, BorderLayout.SOUTH);
        humOutPanel.setBorder(border);

        JPanel baroPanel = new JPanel(new BorderLayout());
        baroPanel.add(baroLabel, BorderLayout.NORTH);
        baroPanel.add(myBaro, BorderLayout.SOUTH);
        baroPanel.setBorder(border);

        JPanel uVPanel = new JPanel(new BorderLayout());
        uVPanel.add(uVLabel, BorderLayout.NORTH);
        uVPanel.add(myUV, BorderLayout.SOUTH);
        uVPanel.setBorder(border);

        JPanel dewPointPanel = new JPanel(new BorderLayout());
        dewPointPanel.add(dewPointLabel, BorderLayout.NORTH);
        dewPointPanel.add(myDewPoint, BorderLayout.SOUTH);
        dewPointPanel.setBorder(border);

        JPanel dailyRainPanel = new JPanel(new BorderLayout());
        dailyRainPanel.add(dailyRainLabel, BorderLayout.NORTH);
        dailyRainPanel.add(myDailyRain, BorderLayout.SOUTH);
        dailyRainPanel.setBorder(border);

        JPanel monthlyRainPanel = new JPanel(new BorderLayout());
        monthlyRainPanel.add(monthlyRainLabel, BorderLayout.NORTH);
        monthlyRainPanel.add(myMonthlyRain, BorderLayout.CENTER);
        monthlyRainPanel.setBorder(border);

        JPanel umbrellaPanel = new JPanel(new BorderLayout());
        umbrellaPanel.add(myUmbrella, BorderLayout.NORTH);
        monthlyRainLabel.setBorder(border);

        // layout data
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 0, 50, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(tempOutPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(humOutPanel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(baroPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(tempInPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(uVPanel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(dewPointPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(dailyRainPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(umbrellaPanel, gbc);
        gbc.gridx = 2;
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

    /**
     * Update data values
     */
    private void updateDataVals() {
        double exampleData = 30;
        myTempOut.setText(df.format(exampleData));
        myTempIn.setText(df.format(exampleData));
        myHumOut.setText(df.format(exampleData));
        myBaro.setText(df.format(exampleData));
        myUV.setText(df.format(exampleData));
        myDewPoint.setText(df.format(exampleData));
        myDailyRain.setText(df.format(exampleData));
        myMonthlyRain.setText(df.format(exampleData));
    }
}


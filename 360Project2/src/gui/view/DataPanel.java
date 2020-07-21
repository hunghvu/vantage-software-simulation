package gui.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Data panel
 * @author My Huynh
 */
public class DataPanel extends JPanel {
    private static final Dimension BOARD_SIZE = new Dimension(700, 500);
    private static final Font dataFontSmall = new Font("Courier New", Font.BOLD, 16);
    private static final Font dataFontBig = new Font("Courier New", Font.BOLD, 30);
    private static final EmptyBorder border = new EmptyBorder(5, 20, 5, 20);
    //Display values with decimal format of 1 decimal place.
    private DecimalFormat df = new DecimalFormat("0.0");


    private JLabel myTemp;
    private JLabel myHum;
    private JLabel myUV;
    private JLabel myChill;

    public DataPanel() {
        super( );
        this.setPreferredSize(BOARD_SIZE);
        this.setMinimumSize(BOARD_SIZE);
        this.setVisible(true);

        myTemp = new JLabel();
        myHum = new JLabel();
        myUV = new JLabel();
        myChill = new JLabel();

        setupGUI();
    }
    private void setupGUI() {

        JLabel tempLabel = new JLabel("TEMP");
        JLabel humLabel = new JLabel("HUM");
        JLabel uVLabel = new JLabel("UV");
        JLabel chillLabel = new JLabel("CHILL");

        tempLabel.setFont(dataFontSmall);
        humLabel.setFont(dataFontSmall);
        uVLabel.setFont(dataFontSmall);
        chillLabel.setFont(dataFontSmall);

        myTemp.setFont(dataFontBig);
        myHum.setFont(dataFontBig);
        myUV.setFont(dataFontBig);
        myChill.setFont(dataFontBig);

        updateDataVals();

        JPanel tempPanel = new JPanel(new BorderLayout());
        tempPanel.add(tempLabel, BorderLayout.NORTH);
        tempPanel.add(myTemp, BorderLayout.SOUTH);
        tempPanel.setBorder(border);

        JPanel humPanel = new JPanel(new BorderLayout());
        humPanel.add(humLabel, BorderLayout.NORTH);
        humPanel.add(myHum, BorderLayout.SOUTH);
        humPanel.setBorder(border);

        JPanel uVPanel = new JPanel(new BorderLayout());
        uVPanel.add(uVLabel, BorderLayout.NORTH);
        uVPanel.add(myUV, BorderLayout.SOUTH);
        uVPanel.setBorder(border);

        JPanel chillPanel = new JPanel(new BorderLayout());
        chillPanel.add(chillLabel, BorderLayout.NORTH);
        chillPanel.add(myChill, BorderLayout.SOUTH);
        chillPanel.setBorder(border);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 0, 50, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(tempPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(humPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(uVPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(chillPanel, gbc);

    }

    private void updateDataVals() {
        double exampleData = 30;
        myTemp.setText(df.format(exampleData));
        myHum.setText(df.format(exampleData));
        myUV.setText(df.format(exampleData));
        myChill.setText(df.format(exampleData));
    }
}


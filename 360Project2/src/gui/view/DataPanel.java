package gui.view;

import javax.swing.*;
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

        JPanel tempPanel = new JPanel(new BorderLayout());
        tempPanel.add(tempLabel, BorderLayout.NORTH);
        tempLabel.add(myTemp, BorderLayout.SOUTH);

        JPanel humPanel = new JPanel(new BorderLayout());
        humPanel.add(humLabel, BorderLayout.NORTH);
        humPanel.add(myHum, BorderLayout.SOUTH);

        JPanel uVPanel = new JPanel(new BorderLayout());
        uVPanel.add(uVLabel, BorderLayout.NORTH);
        uVPanel.add(myUV, BorderLayout.SOUTH);

        JPanel chillPanel = new JPanel(new BorderLayout());
        chillPanel.add(chillLabel, BorderLayout.NORTH);
        chillPanel.add(myChill, BorderLayout.SOUTH);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 0, 50, 0);


    }
}

package gui.view;

import javax.swing.*;
import java.awt.*;

/**
 * GUI view
 *
 * @author My Huynh
 */
public class Frame extends JFrame {
    private static final String TITLE = "Wireless Vantage Pro2 Console Receiver";

    private WindCompassPanel myWindCompassPanel;

    private GraphPanel myGraphPanel;

    private WeatherPanel myWeatherPanel;

    private DataPanel myDataPanel;

    private ButtonPanel myButtonPanel;

    private JPanel myLeftPanel;
    private JPanel myCenterPanel;
    private JPanel myRightPanel;

    public Frame() {
        super(TITLE);
        setSize(new Dimension(1000,700));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setupGUI();

    }
    private void setupGUI() {
        myWindCompassPanel = new WindCompassPanel();
        myGraphPanel = new GraphPanel();
        myWeatherPanel = new WeatherPanel();
        myDataPanel = new DataPanel();
        myButtonPanel = new ButtonPanel();

        myLeftPanel = new JPanel();
        myCenterPanel = new JPanel();
        myRightPanel = new JPanel();

        myLeftPanel.add(myWindCompassPanel, BorderLayout.NORTH);
        myLeftPanel.add(myGraphPanel, BorderLayout.SOUTH);

        myCenterPanel.add(myWeatherPanel, BorderLayout.NORTH);
        myCenterPanel.add(myDataPanel, BorderLayout.SOUTH);

        myRightPanel.add(myButtonPanel, BorderLayout.CENTER);


        this.add(myLeftPanel, BorderLayout.WEST);
        this.add(myCenterPanel, BorderLayout.CENTER);
        this.add(myRightPanel, BorderLayout.EAST);
    }



}

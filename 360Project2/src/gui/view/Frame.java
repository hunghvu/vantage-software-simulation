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
        this.add(myWeatherPanel, BorderLayout.NORTH);
        this.add(myDataPanel, BorderLayout.SOUTH);
    }



}

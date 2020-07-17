package gui.view;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private static final String TITLE = "Wireless Vantage Pro2 Console Receiver";

    private JPanel myPanel;

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

        myPanel = new JPanel();
        this.add(myPanel, BorderLayout.NORTH);
        setupGUI();

    }
    private void setupGUI() {
        myWindCompassPanel = new WindCompassPanel();
        myGraphPanel = new GraphPanel();
        myWeatherPanel = new WeatherPanel();
        myDataPanel = new DataPanel();
        myButtonPanel = new ButtonPanel();
        myPanel.add(myWeatherPanel);
    }



}

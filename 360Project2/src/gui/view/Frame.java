package gui.view;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private static final String TITLE = "Wireless Vantage Pro2 Console Receiver";

    private WindCompassPanel myWindCompassPanel;

    private GraphPanel myGraphPanel;

    private WeatherPanel myWeatherPanel;

    private DataPanel myDataPanel;

    private ButtonPanel myButtonPanel;

    public Frame() {
        super(TITLE);
        setSize(new Dimension(1000,1000));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupGUI();

    }
    private void setupGUI() {
        myWindCompassPanel = new WindCompassPanel();
        myGraphPanel = new GraphPanel();
        myWeatherPanel = new WeatherPanel();
        myDataPanel = new DataPanel();
        myButtonPanel = new ButtonPanel();
        this.setResizable(false);
    }



}

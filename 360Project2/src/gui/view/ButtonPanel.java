package gui.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import gui.controller.Controller;
/**
 * A class for the console buttons.
 *
 * @author Khue Nguyen
 * @version Jul 18, 2020
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {

    /** Panel dimensions (width, height). */
    private static final Dimension PANEL_SIZE = new Dimension(300,700);
    
    /** Prompts to display wind speed in mph. */
    private JButton myWindSpeedBtn;

    /** Prompts to display wind direction in the compass rose via arrow ticks. */
    private JButton myWindDirection;

    /** Prompts to display outside temperature in °F. */
    private JButton myTempBtn;

    /** Prompts to display outside humidity as a percentage. */
    private JButton myHumidityBtn;

    /** Prompts to display the solar radiation in W/m^2. */
    private JButton mySolarBtn;

    /** Prompts to display the weather forecast icons. */
    private JButton myForecastBtn;
    
    /** Sends over deserialized data. Used when implementing ActionListeners. */
    private Controller myController;

    /**
     * Constructor that instantiates the buttons.
     */
    public ButtonPanel() {
        super();
        
        myController = new Controller();
        
        myWindSpeedBtn = new JButton("Wind Speed");
        myWindDirection = new JButton("Wind Direction");
        myTempBtn = new JButton("Temperature");
        myHumidityBtn = new JButton("Humidity");
        mySolarBtn = new JButton("Solar Radiation");
        myForecastBtn = new JButton("Forecast");

        addListeners();
        setUpPanel();
    }

    private void addListeners() {
        myWindSpeedBtn.addActionListener(theEvent -> {
            
        });
        
        myWindDirection.addActionListener(theEvent -> {
            
        });
        
        myTempBtn.addActionListener(theEvent -> {
            
        });
        
        myHumidityBtn.addActionListener(theEvent -> {
            
        });
        
        mySolarBtn.addActionListener(theEvent -> {
            
        });
        
        myForecastBtn.addActionListener(theEvent -> {
            
        });
        
    }

    private void setUpPanel() {
        setPreferredSize(PANEL_SIZE);
        setVisible(true);
        setMinimumSize(PANEL_SIZE);
                
        add(myWindSpeedBtn);
        add(myWindDirection);
        add(myTempBtn);
        add(myHumidityBtn);
        add(mySolarBtn);
        add(myForecastBtn);
        
    }

}

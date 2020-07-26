package gui.view;

<<<<<<< HEAD
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
=======
import gui.model.Connect;

import javax.swing.*;
>>>>>>> 0afd6523d7173001a1a6dfc578db5f00cce4480a

import javax.swing.JPanel;

/**
 * 
 *
 * @author Khue Nguyen
 * @version Jul 25, 2020
 */
@SuppressWarnings("serial")
public class GraphPanel extends JPanel implements Connect {
    
    private static final Dimension PANEL_SIZE = new Dimension(300, 250);
    /** Constructor that sets up components. */
    public GraphPanel() {
        super();
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
    }
    
    /** Sets up panel properties. */
    private void setUpPanel() {
       this.setPreferredSize(PANEL_SIZE); 


    @Override
    public void changeDisplay(String data, String value) {

    }

    @Override
    public void changeDisplay(String value1, String value2, String value3) {

    }
}


//https://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java

package gui.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.model.Connect;

/**
 * 
 *
 * @author Khue Nguyen
 * @version Jul 25, 2020
 */
@SuppressWarnings("serial")
public class GraphPanel extends JPanel implements Connect {
    
    /** GraphPanel's size. */
    private static final Dimension PANEL_SIZE = new Dimension(500, 450);
    
    private static final int PADDING = 15;
    
    private static final int LABEL_PADDING = 20;
    
    private static final Font FONT = new Font("Avenir", Font.PLAIN ,8);
    
    /** Contains graph data with 20 values. */
    private List<Double> myData = new ArrayList<>(20);
    
    /** Translated data to points. */
    private List<Point> myDataPoints = new ArrayList<>();
    
    private double myMin = 0;
    
    private double myMax = 0;
    
    /** Flag of graph's current data type being sent in. Here for future implementation. */
    private String myFlag;
    
    /** Constructor that sets up components. */
    public GraphPanel() {
        super();
        setUpPanel();
        //this.setBackground(Color.black); //test size
        addLabels();
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       
        createPoints(g2);
        createGrid(g2);
        createAxes(g2);
        connectPoints(g2);
        
        
    }
    
    private void createPoints(Graphics2D g2) { 
        double xScale = (this.getWidth() - 2 * PADDING - LABEL_PADDING) / (myData.size() - 1);
        double yScale = (this.getHeight() - 3 * PADDING - 3 * LABEL_PADDING) / 50; 
        //50 is just an arbitrary max value 
        
        for (int i = 0; i < myData.size(); i++) {
            int x = (int) (i * xScale + PADDING + LABEL_PADDING);
            int y = (int) (i * yScale + PADDING + LABEL_PADDING);
            myDataPoints.add(new Point(x, y));
        }
    }
    
    private void createGrid(Graphics2D g2) { 
        
    }
    
    private void createAxes(Graphics2D g2) { 
        //x axis
        g2.drawLine(PADDING + LABEL_PADDING, this.getHeight() - 2*PADDING - 2*LABEL_PADDING, 
                this.getWidth() - PADDING, this.getHeight() - 2*PADDING - 2*LABEL_PADDING);
        //y axis
        g2.drawLine(PADDING + LABEL_PADDING, this.getHeight() - 2*PADDING - 2*LABEL_PADDING, 
                PADDING + LABEL_PADDING, PADDING + LABEL_PADDING);
        
        
    }
    
    private void connectPoints(Graphics2D g2) { 
        
    }
    
    private void addLabels() {
        JLabel min_max = new JLabel("MIN: " + myMin + "\tMAX: " + myMax);
        min_max.setFont(FONT);
        //set location
      //this.add(min_max);
        
        JLabel period = new JLabel("Last 10 hours");
        period.setFont(FONT);
        //set location
        //this.add(period);
        
        JLabel interval = new JLabel("Every 30 minutes");
        interval.setFont(FONT);
        //set location
        //this.add(interval);
        
        JLabel title = new JLabel("RAIN RATE/HR");
        title.setFont(new Font("Avenir", Font.BOLD, 12));
        title.setLocation(0, this.getY()+PADDING);
        //this.add(title);
        
    }
    /** Sets up panel properties. */
    private void setUpPanel() {
       this.setPreferredSize(PANEL_SIZE); 
       this.setMinimumSize(PANEL_SIZE);
    }
    
    private void determineMinMax() {
        for (double d : myData) {
            if (d < myMin) 
                myMin = d;
            if (d > myMax)
                myMax = d;
        }
    }
    
    @Override
    public void changeDisplay(String data, String value) {
        myFlag = data;
        try {
            String[] split = value.split(",");
            for (int i = 0; i < split.length; i++) {
                myData.set(i, Double.parseDouble(split[i]));
            }
            determineMinMax();
        } catch(NumberFormatException theNFE) {
            System.out.println("Problem parsing String to double. Location: GraphPanel, changeDisplay(String data, String value)");     
        }
        this.repaint();
    }

    @Override
    public void changeDisplay(String value1, String value2, String value3) {

    }
}


//https://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java

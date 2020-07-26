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
import javax.swing.JOptionPane;
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
    private static final Dimension PANEL_SIZE = new Dimension(450, 400);
    
    /** Font for lettered labels. */
    private static final Font FONT = new Font("Avenir", Font.PLAIN ,8);
    
    /** Padding for whole GraphPannel. */
    private static final int PADDING = 15;
    
    /** Padding for all labels. */
    private static final int LABEL_PADDING = 20;
    
    /** Tick width if y tick marks, tick height if x tick marks. */
    private static final int TICK = 6;
    
    
    /** Contains graph data with 20 values. */
    private List<Double> myData = new ArrayList<>(20);
    
    /** Translated data to points. */
    private List<Point> myDataPoints = new ArrayList<>();
    
    /* Min data value. */
    private double myMin = 0;
    
    /* Max data value. */
    private double myMax = 0;
    
    /** Graph width. */
    private int myGraphWidth;
    
    /** Graph height. */
    private int myGraphHeight;
    
    /** Flag of graph's current data type being sent in. Here for future implementation. */
    private String myFlag;
    
    
    /** Constructor that sets up components. */
    public GraphPanel() {
        super();
        
        for (int i = 0; i < 20; i++) {
            myData.add(0.0);
        }
        
        setUpPanel();
        
        
        this.setBackground(Color.black); //test size
        addLabels();
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        myGraphWidth = this.getWidth() - 2 * PADDING - LABEL_PADDING;
        myGraphHeight = this.getHeight() - 3 * LABEL_PADDING - 3 * PADDING;
        
        createPoints(g2);
        createGrid(g2);
        createAxes(g2);
        connectPoints(g2);
        
        
    }
    
    private void createPoints(Graphics2D g2) { 
        double xScale = (myGraphWidth) / (myData.size() - 1);
        double yScale = (myGraphHeight) / 45; //45 is just an arbitrary max value 
         
        for (int i = 0; i < myData.size(); i++) {
            int x = (int) (i * xScale + PADDING + LABEL_PADDING);
            int y = (int) (i * yScale + PADDING + LABEL_PADDING);
            myDataPoints.add(new Point(x, y));
        }
    }
    
    private void createGrid(Graphics2D g2) { 
        
    }
    
    private void createAxes(Graphics2D g2) {
        g2.setColor(Color.white);
        //x axis
        g2.drawLine(PADDING + LABEL_PADDING, this.getHeight() - 2*PADDING - 2*LABEL_PADDING, 
                this.getWidth() - PADDING, this.getHeight() - 2*PADDING - 2*LABEL_PADDING);
        //y axis
        g2.drawLine(PADDING + LABEL_PADDING, this.getHeight() - 2*PADDING - 2*LABEL_PADDING, 
                PADDING + LABEL_PADDING, PADDING + LABEL_PADDING);
        
        g2.drawLine(PADDING, this.getHeight() - PADDING, PADDING, PADDING); //see padding
        g2.drawLine(PADDING, this.getHeight() - PADDING, this.getWidth()-PADDING, this.getHeight()-PADDING); //padding
        g2.drawLine(PADDING, this.getHeight()-PADDING-LABEL_PADDING, this.getWidth()-PADDING, this.getHeight()-PADDING-LABEL_PADDING); //label padding
        
        
        // x tick marks
        for (int i = 0; i < myData.size(); i++) {
            int x1 = PADDING + LABEL_PADDING + i*myGraphWidth/(myData.size()-1); //19 blocks but 20 ticks
            int x2 = x1;
            int y1 = this.getHeight() - 2*PADDING - 2*LABEL_PADDING;
            int y2 = y1 + TICK;
            g2.drawLine(x1, y1, x2, y2);
            
            
       
        }
        // y tick marks
        for (int i = 0; i < myData.size()* 59 / 80 + 1; i++) { //the graph has a 59:80 ratio
            int x1 = PADDING + LABEL_PADDING; //19 blocks but 20 ticks
            int x2 = x1 - TICK;
            int y1 = this.getHeight() - 2*PADDING - 2*LABEL_PADDING - i*myGraphHeight/(myData.size()* 59 / 80);
            int y2 = y1;
            g2.drawLine(x1, y1, x2, y2);
        }
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
            String[] split = value.split(", ");
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

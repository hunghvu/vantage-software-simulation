package gui.view;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

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
    private static final Dimension PANEL_SIZE = new Dimension(405, 360);
    
    /** Font for lettered labels. */
    private static final Font FONT = new Font("Courier New", Font.BOLD, 10);
    
    /** Color for axes, tick, numberings. */
    private static final Color LINE = Color.black;
    
    /** Color for grid lines. */
    private static final Color GRID = Color.gray;
    
    /** Color for graph line. */
    private static final Color GRAPH = new Color(71, 160, 255);

    /** Padding for whole GraphPannel. */
    private static final int PADDING = 15;
    
    /** Padding for all labels. */
    private static final int LABEL_PADDING = 30;
    
    /** Tick width if y tick marks, tick height if x tick marks. */
    private static final int TICK = 6;
    
    
    /** Contains graph data with 20 values. */
    private List<Double> myData = new ArrayList<>(10);
    
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
        //String test = "26.21, 23.7, 21.92, 20.71, 21.28, 23.21, 26.99, 25.45, 25.48, 25.28"; //test values
        //String[] temp = test.split(", "); //test values
        
        for (int i = 0; i < 10; i++) {
            myData.add(0.0);
            //myData.add(Double.parseDouble(temp[i])); //test values
        }
        
        setUpPanel();
        
        
        //this.setBackground(Color.black); //test size
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        myGraphWidth = this.getWidth() - 2 * PADDING - LABEL_PADDING;
        myGraphHeight = this.getHeight() - 3 * LABEL_PADDING - 3 * PADDING;
        
        createPoints(g2);
        createAxesGrid(g2);
        connectPoints(g2);
        addLabels(g2);

    }
    
    private void createPoints(Graphics2D g2) { 
        double xScale = (myGraphWidth) / (myData.size() - 1);
        double yScale = (myGraphHeight) / 42; //42 is the y axis max value 
        final int marginOfError = 6;
        
        for (int i = 0; i < myData.size(); i++) {
            int x = (int) (i * xScale + PADDING + LABEL_PADDING);
            int y = (this.getHeight() - 2 * PADDING - 2*LABEL_PADDING) - (int) (myData.get(i) * yScale) ;
            if (myData.get(i) > 0.0)
                y -= marginOfError;
            myDataPoints.add(new Point(x, y));
        }
    }
    
    
    private void createAxesGrid(Graphics2D g2) {
        g2.setColor(LINE);
        //x axis
        g2.drawLine(PADDING + LABEL_PADDING, this.getHeight() - 2*PADDING - 2*LABEL_PADDING, 
                this.getWidth() - PADDING, this.getHeight() - 2*PADDING - 2*LABEL_PADDING);
        //y axis
        g2.drawLine(PADDING + LABEL_PADDING, this.getHeight() - 2*PADDING - 2*LABEL_PADDING, 
                PADDING + LABEL_PADDING, PADDING + LABEL_PADDING);
        
        
        // x axis
        for (int i = 0; i < myData.size(); i++) {
            //tick marks
            g2.setColor(LINE);
            int x1 = PADDING + LABEL_PADDING + i*myGraphWidth/(myData.size()-1); //10 ticks
            int x2 = x1;
            int y1 = this.getHeight() - 2*PADDING - 2*LABEL_PADDING;
            int y2 = y1 + TICK;
            g2.drawLine(x1, y1, x2, y2);
            
            //numberings
            String xlab = Integer.toString(i);
            g2.setFont(FONT);
            FontMetrics metrics = g2.getFontMetrics();
            int labelwidth = metrics.stringWidth(xlab);
            int labelheight = metrics.getHeight();
            g2.drawString(xlab, x1 - labelwidth/2, y1 + labelheight + 3);
            
            //grid
            if (i > 0)
                g2.setColor(GRID);
            g2.drawLine(x1, y1, x2, PADDING + LABEL_PADDING);
        }
        
        // y axis
        for (int i = 0; i < 2* myData.size()* 59 / 80 + 1; i++) { //the graph has a 59:80 ratio
            //ticks
            g2.setColor(LINE);
            int x1 = PADDING + LABEL_PADDING;
            int x2 = x1 - TICK;
            int y1 = this.getHeight() - 2*PADDING - 2*LABEL_PADDING - i*myGraphHeight/(2 * myData.size()* 59 / 80); //15 ticks
            int y2 = y1;
            g2.drawLine(x1, y1, x2, y2);
            
            //numberings
            String ylab = Integer.toString(i*3);
            g2.setFont(FONT);
            FontMetrics metrics = g2.getFontMetrics();
            int labelwidth = metrics.stringWidth(ylab);
            int labelheight = metrics.getHeight();
            g2.drawString(ylab, x1 - labelwidth - TICK - 5, y1 + labelheight/2 -3);
            
            //grid
            if (i > 0)
                g2.setColor(Color.gray);
            g2.drawLine(x1, y1, this.getWidth() - PADDING, y2);
            
        }
    }
    
    private void connectPoints(Graphics2D g2) { 
        g2.setColor(GRAPH);
        g2.setStroke(new BasicStroke(2f));
        
        for (int i = 0; i < myDataPoints.size() - 1; i++) {
            g2.drawLine(myDataPoints.get(i).x, myDataPoints.get(i).y, myDataPoints.get(i+1).x, myDataPoints.get(i+1).y);
        }  
    }
    
    private void addLabels(Graphics2D g2) {
        g2.setColor(LINE);
        g2.setFont(new Font("Courier New", Font.BOLD, 14));
        
        String title = new String("RAIN RATE/HR");
        g2.drawString(title, PADDING, PADDING);
        
        String min_max = "MIN: " + myMin + "        " + "MAX: " + myMax;
        g2.drawString(min_max, PADDING, this.getHeight() - PADDING - LABEL_PADDING/2);
        
        g2.setFont(FONT);
        String period = new String("Last 10 hours");
        g2.drawString(period, PADDING + LABEL_PADDING, PADDING + LABEL_PADDING - g2.getFontMetrics().getHeight()/2);
        
        String interval = "Every 30 minutes";
        g2.drawString(interval, PADDING + LABEL_PADDING + myGraphWidth*4/9, PADDING + LABEL_PADDING - g2.getFontMetrics().getHeight()/2);
          
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
        if (data.equals("Rain graph")) {
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
    }

    @Override
    public void changeDisplay(String value1, String value2, String value3) {

    }
}


//https://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java

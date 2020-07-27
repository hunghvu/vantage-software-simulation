package gui.view;

import gui.model.Connect;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * This class contains graph section the GUI.
 *
 * @author Khue Nguyen
 * @version Jul 25, 2020
 */
@SuppressWarnings({

    "serial", "PMD.UnusedFormalParameter", "PMD.SystemPrintln", 
    "PMD.RedundantFieldInitializer", "PMD.LawOfDemeter",
    "PMD.ImmutableField", "PMD.ForLoopCanBeForeach", "PMD.DataflowAnomalyAnalysis", 
    "PMD.AvoidLiteralsInIfCondition", "PMD.AvoidInstantiatingObjectsInLoops", 
    "PMD.AvoidFinalLocalVariable"

})
public class GraphPanel extends JPanel implements Connect {

  /** GraphPanel's size. */
  private static final Dimension PANEL_SIZE = new Dimension(405, 360);

  /** Font for small labels and numberings. */
  private static final Font FONT = new Font("Courier New", Font.BOLD, 10);

  /** Color for axes, tick, numberings. */
  private static final Color LINE = Color.black;

  /** Color for grid lines. */
  private static final Color GRID = Color.gray;

  /** Color for graph points. */
  private static final Color GRAPH = new Color(0, 123, 255);

  /** Padding for whole GraphPannel. */
  private static final int PADDING = 15;

  /** Padding for all labels. */
  private static final int LABEL_PADDING = 30;

  /** Tick width if y tick marks, tick height if x tick marks. */
  private static final int TICK = 6;

  /** Contains graph data with 20 values. */
  private List<Double> myData = new ArrayList<>(10);

  /** Translated data to points. */
  private List<Ellipse2D> myPoints = new ArrayList<>();

  /** Min data value. **/
  private double myMin = 0;

  /** Max data value. **/
  private double myMax = 0;

  /** Graph width. */
  private int myGraphWidth;

  /** Graph height. */
  private int myGraphHeight;

  /** Constructor that sets up components. */
  public GraphPanel() {
    super();

    for (int i = 0; i < 10; i++) {
      myData.add(0.0);
    }

    setUpPanel();
  }

  @Override
  protected void paintComponent(final Graphics theG) {
    super.paintComponent(theG);
    final Graphics2D theG2 = (Graphics2D) theG;
    theG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    myGraphWidth = this.getWidth() - 2 * PADDING - LABEL_PADDING;
    myGraphHeight = this.getHeight() - 3 * LABEL_PADDING - 3 * PADDING;

    this.removeAll();
    createPoints(theG2);
    createAxesGrid(theG2);
    drawPoints(theG2);
    addLabels(theG2);

  }

  /**
   * Calculates the x and y position of the Points to be drawn. Takes into
   * consideration scaling and margin of error for visual accuracy.
   * 
   * @param theG2 Graphics2D component used to draw with
   */
  private void createPoints(final Graphics2D theG2) {

    final double xScale = (myGraphWidth) / (myData.size() - 1);
    final double yScale = (myGraphHeight) / 42; // 42 is the y axis max value
    final int marginOfError = 6;
    final int pointWidth = 6;

    for (int i = 0; i < myData.size(); i++) {
      final double xDraw = i * xScale + PADDING + LABEL_PADDING + 2;

      double y1 = this.getHeight() - 2 * PADDING - 2 * LABEL_PADDING - myData.get(i) * yScale;

      if (myData.get(i) > 0.0) {
        y1 -= marginOfError;
      }

      final Ellipse2D point = new Ellipse2D.Double(
          xDraw - pointWidth / 2, y1 - pointWidth / 2, pointWidth, pointWidth);
      myPoints.add(point);
    }

  }

  /**
   * Draws the points.
   * 
   * @param theG2 Graphics2D component used to draw with
   */
  private void drawPoints(final Graphics2D theG2) {
    theG2.setColor(GRAPH);

    for (final Ellipse2D e : myPoints) {
      theG2.fill(e);
    }
  }

  /**
   * Draws x and y axes, tick marks, and numberings for each ticks.
   * 
   * @param theG2 Graphics2D component used to draw with
   * @return
   */
  private void createAxesGrid(final Graphics2D theG2) {
    theG2.setColor(LINE);
    // x axis
    theG2.drawLine(PADDING + LABEL_PADDING, this.getHeight() - 2 * PADDING - 2 * LABEL_PADDING,
        this.getWidth() - PADDING, this.getHeight() - 2 * PADDING - 2 * LABEL_PADDING);
    // y axis
    theG2.drawLine(PADDING + LABEL_PADDING, 
        this.getHeight() - 2 * PADDING - 2 * LABEL_PADDING, PADDING + LABEL_PADDING,
        PADDING + LABEL_PADDING);

    // x axis
    for (int i = 0; i < myData.size(); i++) {
      // tick marks
      theG2.setColor(LINE);
      final int x1Mark = PADDING + LABEL_PADDING 
          + i * myGraphWidth / (myData.size() - 1); // 10 ticks
      final int x2Mark = x1Mark;
      final int y1Mark = this.getHeight() - 2 * PADDING - 2 * LABEL_PADDING;
      final int y2Mark = y1Mark + TICK;
      theG2.drawLine(x1Mark, y1Mark, x2Mark, y2Mark);

      // numberings
      final String xlab = Integer.toString(i);
      theG2.setFont(FONT);
      final FontMetrics metrics = theG2.getFontMetrics();
      final int labelwidth = metrics.stringWidth(xlab);
      final int labelheight = metrics.getHeight();
      theG2.drawString(xlab, x1Mark - labelwidth / 2, y1Mark + labelheight + 3);

      // grid
      if (i > 0) {
        theG2.setColor(GRID);
      }
      theG2.drawLine(x1Mark, y1Mark, x2Mark, PADDING + LABEL_PADDING);
    }

    // y axis
    for (int i = 0; i < 2 * myData.size() * 59 / 80 + 1; i++) { // the graph has a 59:80 ratio
      // ticks
      theG2.setColor(LINE);
      final int x1Point = PADDING + LABEL_PADDING;
      final int x2Point = x1Point - TICK;
      final int y1Point = this.getHeight() - 2 * PADDING - 2 * LABEL_PADDING
          - i * myGraphHeight / (2 * myData.size() * 59 / 80); // 15
      // ticks
      final int y2Point = y1Point;
      theG2.drawLine(x1Point, y1Point, x2Point, y2Point);

      // numberings
      final String ylab = Integer.toString(i * 3);
      theG2.setFont(FONT);
      final FontMetrics metrics = theG2.getFontMetrics();
      final int labelwidth = metrics.stringWidth(ylab);
      final int labelheight = metrics.getHeight();
      theG2.drawString(ylab, x1Point - labelwidth - TICK - 5, y1Point + labelheight / 2 - 3);

      // grid
      if (i > 0) {
        theG2.setColor(Color.gray);
      }
      theG2.drawLine(x1Point, y1Point, this.getWidth() - PADDING, y2Point);

    }
  }

  /**
   * Adds labels that provide background info on the graph via drawString. Also
   * provides min and max data.
   * 
   * @param theG2 Graphics2D component used to draw with
   * @return
   */
  private void addLabels(final Graphics2D theG2) {
    theG2.setColor(LINE);
    theG2.setFont(new Font("Courier New", Font.BOLD, 14));

    final String title = "RAIN RATE/HR";
    theG2.drawString(title, PADDING, PADDING);

    final String minMax = "MIN: " + myMin + "        " + "MAX: " + myMax;
    theG2.drawString(minMax, PADDING, this.getHeight() - PADDING - LABEL_PADDING / 2);

    theG2.setFont(FONT);
    final String period = "Last 10 hours";
    theG2.drawString(period, 
        PADDING + LABEL_PADDING, PADDING + LABEL_PADDING - theG2.getFontMetrics().getHeight() / 2);

    final String interval = "Every 30 minutes";
    theG2.drawString(interval, PADDING + LABEL_PADDING + myGraphWidth * 4 / 9,
        PADDING + LABEL_PADDING - theG2.getFontMetrics().getHeight() / 2);

  }

  /** Sets up panel properties. */
  private void setUpPanel() {
    this.setPreferredSize(PANEL_SIZE);
    this.setMinimumSize(PANEL_SIZE);
  }

  /** Determine min and max data. */
  private void determineMinMax() {
    myMin = myData.get(0);
    myMax = myData.get(0);

    for (int i = 0; i < myData.size(); i++) {
      if (myData.get(i) < myMin) {
        myMin = myData.get(i);
      }
      if (myData.get(i) > myMax) {
        myMax = myData.get(i);
      }
    }
  }

  @Override
  public void changeDisplay(final String data, final String value) {
    if ("Rain graph".equals(data)) {

      myMin = 0;
      myMax = 0;

      myPoints.clear();
      this.repaint();

      try {
        final String[] split = value.split(", ");
        for (int i = 0; i < split.length; i++) {
          myData.set(i, Double.parseDouble(split[i]));
        }
        determineMinMax();
      } catch (NumberFormatException theNfe) {
        System.out.println(
            "Problem parsing String to double. Location: "
            + "GraphPanel, " + "changeDisplay(String data, String value)");
      }

      this.repaint();
    }
  }

  // This class use changeDisplay(String, String), which is implemented above.
  // This one is redundant, can be ignored.
  @Override
  public void changeDisplay(final String value1, final String value2, final String value3) {

    // Ignore.

  }
}

//Source: 
//https://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java

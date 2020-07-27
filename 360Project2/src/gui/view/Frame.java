package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * GUI view.
 *
 * @author My Huynh
 */
@SuppressWarnings({

    "serial", "PMD.DataClass", "PMD.MissingSerialVersionUID", "PMD.SingularField"

})
public class Frame extends JFrame {
  /** Title of the frame. */
  private static final String TITLE = "Wireless Vantage Pro2 Console Receiver";
  /** Panel for wind compass. */
  private WindCompassPanel myWindCompPanel;
  /** Panel for rain graph. */
  private GraphPanel myGraphPanel;
  /** Panel for weather icon, moon phase, time and date. */
  private WeatherPanel myWeatherPanel;
  /** Panel for data. */
  private DataPanel myDataPanel;
  /** Panel for buttons. */
  private ButtonPanel myButtonPanel;
  /** Panel for weather message. */
  private MessagePanel myMessagePanel;

  /** Left Panel to contain wind compass and rain graph. */
  private JPanel myLeftPanel;
  /** Center Panel to contain weather, data and message panels. */
  private JPanel myCenterPanel;
  /** Right Panel to contain buttons. */
  private JPanel myRightPanel;

  /**
   * Constructs the GUI frame.
   */
  public Frame() {
    super(TITLE);
    setSize(new Dimension(1300, 800));
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    setupGui();

  }

  /**
   * Method to sets up GUI components.
   */
  private void setupGui() {
    myWindCompPanel = new WindCompassPanel();
    myGraphPanel = new GraphPanel();
    myWeatherPanel = new WeatherPanel();
    myDataPanel = new DataPanel();
    myButtonPanel = new ButtonPanel();
    myMessagePanel = new MessagePanel();

    myLeftPanel = new JPanel();
    myCenterPanel = new JPanel();
    myRightPanel = new JPanel();

    myLeftPanel.setLayout(new BorderLayout());
    myLeftPanel.add(myWindCompPanel, BorderLayout.NORTH);
    myLeftPanel.add(myGraphPanel, BorderLayout.SOUTH);

    myCenterPanel.add(myWeatherPanel, BorderLayout.NORTH);
    myCenterPanel.add(myDataPanel, BorderLayout.CENTER);
    myCenterPanel.add(myMessagePanel, BorderLayout.SOUTH);

    myRightPanel.add(myButtonPanel, BorderLayout.CENTER);

    this.add(myLeftPanel, BorderLayout.WEST);
    this.add(myCenterPanel, BorderLayout.CENTER);
    this.add(myRightPanel, BorderLayout.EAST);

  }

  /**
   * Get method for wind compass panel.
   * 
   * @return wind compass panel
   */
  public WindCompassPanel getMyWindCompassPanel() {
    return myWindCompPanel;
  }

  /**
   * Get method for rain graph panel.
   * 
   * @return rain graph panel
   */
  public GraphPanel getMyGraphPanel() {
    return myGraphPanel;
  }

  /**
   * Get method for weather panel.
   * 
   * @return weather panel
   */
  public WeatherPanel getMyWeatherPanel() {
    return myWeatherPanel;
  }

  /**
   * Get method for data panel.
   * 
   * @return data panel
   */
  public DataPanel getMyDataPanel() {
    return myDataPanel;
  }

  /**
   * Get method for buttons panel.
   * 
   * @return buttons panel
   */
  public ButtonPanel getMyButtonPanel() {
    return myButtonPanel;
  }

  /**
   * Get method for message panel.
   * 
   * @return message panel
   */
  public MessagePanel getMyMessagePanel() {
    return myMessagePanel;
  }

}

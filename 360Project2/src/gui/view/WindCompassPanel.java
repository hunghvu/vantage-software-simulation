package gui.view;

import gui.model.Connect;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * Panel to show the wind direction and wind speed.
 *
 * @author My Huynh
 */
@SuppressWarnings({
  
    "serial", "PMD.LawOfDemeter"
  
})
public class WindCompassPanel extends JLayeredPane implements Connect {
  /** WindCompassPanel dimensions (width, height). */
  private static final Dimension BOARD_SIZE = new Dimension(300, 300);

  /** Label for the compass. */
  public final JLabel myCompassLabel;
  /** Label for wind speed. */
  public JLabel mySpeedLabel;
  
  // Not up to expectation. (Hung Vu)
  //    // Speed unit indicator. (Hung vu)
  //    private static boolean myWindSpeedUnit = true;
  //
  //    public static void setMyWindSpeedUnit(boolean myWindSpeedUnit) {
  //      WindCompassPanel.myWindSpeedUnit = myWindSpeedUnit;
  //    }

  /**
   * Construct the panel.
   */
  public WindCompassPanel() {
    super();
    this.setPreferredSize(BOARD_SIZE);
    this.setMinimumSize(BOARD_SIZE);
    this.setVisible(true);

    myCompassLabel = new JLabel();

    final ImageIcon compassIcon = new ImageIcon(new ImageIcon(
        getClass().getResource("/icons/North.png")).getImage()
        .getScaledInstance(300, 300, Image.SCALE_SMOOTH));

    myCompassLabel.setIcon(compassIcon);
    mySpeedLabel = new JLabel("0", SwingConstants.CENTER);
    mySpeedLabel.setFont(new Font("Courier New", Font.BOLD, 30));

    // determine speedLabel position
    final int speedX = this.getX() + 120;
    final int speedY = this.getY() + 135;

    mySpeedLabel.setLocation(speedX, speedY);

    myCompassLabel.setBounds(0, 0, compassIcon.getIconWidth(), compassIcon.getIconHeight());
    mySpeedLabel.setBounds(speedX, speedY, 80, 80);

    this.add(myCompassLabel);
    this.add(mySpeedLabel);

  }

  @Override
  public void changeDisplay(final String data, final String value) {
    if ("Wind direction".equals(data)) {
      myCompassLabel.setIcon(new ImageIcon(new ImageIcon(
          getClass().getResource("/icons/" + value + ".png")).getImage()
          .getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
      repaint();
      revalidate();
    }
    if ("Wind speed".equals(data)) {
      final long displaySpeed = Math.round(Float.parseFloat(value));
      mySpeedLabel.setText(Long.toString(displaySpeed));
    }
  }
  
  // Ignore.
  @Override
  public void changeDisplay(final String value1, final String value2, final String value3) {
    // Ignore.

  }
}

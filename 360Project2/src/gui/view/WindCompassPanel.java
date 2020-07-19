package gui.view;

import javax.swing.*;
import java.awt.*;

public class WindCompassPanel extends JPanel {
    private static final Dimension BOARD_SIZE = new Dimension(300, 300);

    public final JLabel myCompassLabel;
    public final JLabel mySpeedLabel;
    public WindCompassPanel() {
        super( );
        this.setPreferredSize(BOARD_SIZE);
        this.setMinimumSize(BOARD_SIZE);
        this.setVisible(true);

        myCompassLabel = new JLabel();
        mySpeedLabel = new JLabel();
    }
}

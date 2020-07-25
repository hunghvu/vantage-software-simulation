package gui.view;

import gui.model.Connect;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel implements Connect {
    private static final Dimension BOARD_SIZE = new Dimension(1200, 100);
    private static final Font dataFontBig = new Font("Courier New", Font.BOLD, 26);

    private JLabel myMessage;

    public MessagePanel() {
        super( );
        this.setPreferredSize(BOARD_SIZE);
        this.setMinimumSize(BOARD_SIZE);
        this.setVisible(true);

        myMessage = new JLabel();
        this.add(myMessage, BorderLayout.CENTER);
    }

    @Override
    public void changeDisplay(String data, String value) {

    }

    @Override
    public void changeDisplay(String value1, String value2, String value3) {

    }
}

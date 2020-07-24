package gui.view;

import gui.model.Connect;

import javax.swing.*;
import java.awt.*;

public class WindCompassPanel extends JLayeredPane implements Connect {
    private static final Dimension BOARD_SIZE = new Dimension(300, 300);

    public final JLabel myCompassLabel;
    public JLabel mySpeedLabel;
    
    public WindCompassPanel() {
        super( );
        this.setPreferredSize(BOARD_SIZE);
        this.setMinimumSize(BOARD_SIZE);
        this.setVisible(true);

        myCompassLabel = new JLabel();
        mySpeedLabel = new JLabel();

        ImageIcon compassIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/icons/North.png"))
                .getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));

        myCompassLabel.setIcon(compassIcon);
        mySpeedLabel = new JLabel("0", SwingConstants.CENTER);
        mySpeedLabel.setFont(new Font("Courier New", Font.BOLD, 30));

        //determine speedLabel position
        int speedX = this.getX() + 120;
        int speedY = this.getY() + 135;

        mySpeedLabel.setLocation(speedX, speedY);

        myCompassLabel.setBounds( 0, 0,compassIcon.getIconWidth(), compassIcon.getIconHeight() );
        mySpeedLabel.setBounds( speedX, speedY,  80, 80 );

        this.add(myCompassLabel);
        this.add(mySpeedLabel);

    }

    @Override
    public void changeDisplay(String data, String value) {
        if(data.equals("Wind direction")) {
            myCompassLabel.removeAll();
            myCompassLabel.setIcon(new ImageIcon(new ImageIcon(getClass()
                    .getResource("/icons/"+ value +".png"))
                    .getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        }
        if(data.equals("Wind speed")) {
            long displaySpeed = Math.round(Float.parseFloat(value));
            mySpeedLabel.setText(Long.toString(displaySpeed));
        }
    }
}

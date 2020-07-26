package gui.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * 
 *
 * @author Khue Nguyen
 * @version Jul 25, 2020
 */
@SuppressWarnings("serial")
public class GraphPanel extends JPanel {
    
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
    }
}

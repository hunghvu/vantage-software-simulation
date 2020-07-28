package junit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gui.view.Frame;

/**
 * JUnit test for the Frame.
 *
 * @author Khue Nguyen
 * @version Jul 27, 2020
 */
class FrameTest {
    
    /** Object for setting up. */
    private Frame myFrame;
    
    @BeforeEach
    void setUp() throws Exception {
        myFrame = new Frame();
    }

    @Test
    void testPanelsExist() {
        assertNotNull(myFrame.getMyButtonPanel());
        assertNotNull(myFrame.getMyDataPanel());
        assertNotNull(myFrame.getMyGraphPanel());
        assertNotNull(myFrame.getMyMessagePanel());
        assertNotNull(myFrame.getMyWeatherPanel());
        assertNotNull(myFrame.getMyWindCompassPanel());
    }
    
    

}

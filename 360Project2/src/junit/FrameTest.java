package junit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import gui.view.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * JUnit test for the Frame.
 *
 * @author Khue Nguyen
 * @version Jul 27, 2020
 */
@SuppressWarnings({

    "PMD.JUnitTestContainsTooManyAsserts", "PMD.AtLeastOneConstructor",
    "PMD.BeanMembersShouldSerialize"

})
class FrameTest {

  /** Object for setting up. */
  private Frame myFrame;

  @BeforeEach
  /* default */void setUp() throws Exception {
    myFrame = new Frame();
  }

  @Test
  /* default */void testPanelsExist() {
    assertNotNull(myFrame.getMyButtonPanel(), "Button Panel doesn't exist.");
    assertNotNull(myFrame.getMyDataPanel(), "Data Panel doesn't exist.");
    assertNotNull(myFrame.getMyGraphPanel(), "Graph Panel doesn't exist.");
    assertNotNull(myFrame.getMyMessagePanel(), "Message Panel doesn't exist.");
    assertNotNull(myFrame.getMyWeatherPanel(), "Weather Panel doesn't exist.");
    assertNotNull(myFrame.getMyWindCompassPanel(), "Wind Compass Panel doesn't exist.");
  }

}

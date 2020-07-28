package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import sensors.WindSpeedSensor;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * Junit testing for Sensors.
 *
 * @author Kim Seungku
 * @version Jul 26, 2020
 */
@SuppressWarnings({
  
    "PMD.ImmutableField", "PMD.DoNotUseThreads", "PMD.JUnitTestsShouldIncludeAssert",
    "PMD.JUnitTestContainsTooManyAsserts", "PMD.AtLeastOneConstructor",
    "PMD.BeanMembersShouldSerialize", "PMD.LawOfDemeter"
  
})
class WindSpeedSensorTest {
  /** Object for setting up purpose. **/
  private WindSpeedSensor wsTest = new WindSpeedSensor();

  /** test toString method. */
  @Test
  /* default */void testToString() {
    assertEquals(wsTest.toString(), 
        "Wind Speed Sensor", "Wind Speed Sensor's toString method is not working");
  }

  /** test data of the sensor. */
  @Test
  /* default */void testUpdateData() {
    // check if data is within the range
    assertTrue(0.00 <= Double.valueOf(wsTest.getDataOne()) 
        && Double.valueOf(wsTest.getDataOne()) <= 200.00,
        "Wind speed is not between 0 to 200");
    assertTrue(-110.00 <= Double.valueOf(wsTest.getDataTwo()) 
        && Double.valueOf(wsTest.getDataTwo()) <= 135.00,
        "Chillyness is not between -110 to 135");
    for (int i = 0; i < 10_000; i++) {
      // update the data
      wsTest.updateData();
      // check again if data is within the range
      assertTrue(0.00 <= Double.valueOf(wsTest.getDataOne()) 
          && Double.valueOf(wsTest.getDataOne()) <= 200.00,
          "Wind speed is not between 0 to 200");
      assertTrue(-110.00 <= Double.valueOf(wsTest.getDataTwo()) 
          && Double.valueOf(wsTest.getDataTwo()) <= 135.00,
          "Chillyness is not between -110 to 135");
    }
  }

  /** test running method. */
  @Test
  /* default */void testRun() throws InterruptedException {
    final String dataOne = wsTest.getDataOne();
    final String dataTwo = wsTest.getDataTwo();

    final Thread thread = new Thread(wsTest);
    thread.start();
    Thread.sleep(1000L);

    // test if data is updated
    assertFalse(dataOne.equals(wsTest.getDataOne()), "Wind speed have not updated.");
    assertFalse(dataTwo.equals(wsTest.getDataOne()), "Wind chill have not updated.");

    assertTrue(0.00 <= Double.valueOf(wsTest.getDataOne()) 
        && Double.valueOf(wsTest.getDataOne()) <= 200.00,
        "Updated wind speed is not between 0 to 200");
    assertTrue(-110.00 <= Double.valueOf(wsTest.getDataTwo()) 
        && Double.valueOf(wsTest.getDataTwo()) <= 135.00,
        "Updated wind chill is not between -110 to 135");
  }

}

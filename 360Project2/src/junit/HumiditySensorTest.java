package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import sensors.HumiditySensor;

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
class HumiditySensorTest {

  /** Object for setting up purpose. **/
  private HumiditySensor hsTest = new HumiditySensor();

  /** test toString method. */
  @Test
  /* default */void testToString() {
    assertEquals(hsTest.toString(), "Humidity Sensor",
        "Humidity sensor is not able to call right toString method");
  }

  /** test data of the sensor. */
  @Test
  /* default */void testUpdateData() {
    // check if data is within the range
    assertTrue(0.00 <= Double.valueOf(hsTest.getDataOne())
        && Double.valueOf(hsTest.getDataOne()) <= 100.00,
        "Humidity out is not within the range of the sensor");
    assertTrue(40.00 <= Double.valueOf(hsTest.getDataTwo())
        && Double.valueOf(hsTest.getDataTwo()) <= 50.00,
        "Humidity in is not within the range of the sensor");
    for (int i = 0; i < 100_000; i++) {
      // update the data
      hsTest.updateData();
      // check again if data is within the range
      assertTrue(0.00 <= Double.valueOf(hsTest.getDataOne())
          && Double.valueOf(hsTest.getDataOne()) <= 100.00,
          "Humidity out is not within the range of the sensor");
      assertTrue(40.00 <= Double.valueOf(hsTest.getDataTwo())
          && Double.valueOf(hsTest.getDataTwo()) <= 50.00,
          "Humidity in is not within the range of the sensor");
    }
  }

  /** test running method. */
  @Test
  /* default */void testRun() throws InterruptedException {
    final String dataOne = hsTest.getDataOne();
    final String dataTwo = hsTest.getDataTwo();

    final Thread thread = new Thread(hsTest);
    thread.start();
    Thread.sleep(1000L);

    // test if data is updated
    assertFalse(dataOne.equals(hsTest.getDataOne()), "Hum out have not updated.");
    assertFalse(dataTwo.equals(hsTest.getDataOne()), "Hum in have not updated.");

    assertTrue(0.00 <= Double.valueOf(hsTest.getDataOne())
        && Double.valueOf(hsTest.getDataOne()) <= 100.00,
        "Updated hum out is not within the range of the sensor");
    assertTrue(40.00 <= Double.valueOf(hsTest.getDataTwo())
        && Double.valueOf(hsTest.getDataTwo()) <= 50.00,
        "Updated hum in is not within the range of the sensor");
  }
}

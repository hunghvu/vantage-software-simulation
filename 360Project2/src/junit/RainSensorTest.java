package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import sensors.RainSensor;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * Junit testing for Sensors.
 *
 * @author Kim Seungku
 * @version Jul 26, 2020
 */
@SuppressWarnings({

    "PMD.ImmutableField", "PMD.LawOfDemeter", "PMD.AvoidFileStream",
    "PMD.AtLeastOneConstructor", "PMD.AvoidInstantiatingObjectsInLoops",
    "PMD.BeanMembersShouldSerialize", "PMD.CyclomaticComplexity",
    "PMD.DataflowAnomalyAnalysis", "PMD.DoNotUseThreads", "PMD.JUnitTestContainsTooManyAsserts",
    "PMD.ModifiedCyclomaticComplexity", "PMD.StdCyclomaticComplexity", "PMD.SystemPrintln",
    "PMD.LiteralsFirstInComparisons", "PMD.UseEqualsToCompareStrings"

})
class RainSensorTest {
  /** Object for setting up purpose. **/
  private RainSensor rsTest = new RainSensor();

  /** test toString method. */
  @Test
  /* default */void testToString() {
    assertEquals(rsTest.toString(), "Rain Sensor", "Rain Sensor toString method is not working");
  }

  /** test data of the sensor. */
  @Test
  /* default */void testUpdateData() {
    // check if data is within the range
    assertTrue(0.00 <= Double.valueOf(rsTest.getDataOne())
        && Double.valueOf(rsTest.getDataOne()) <= 30.00,
        "Rain rate is not withing in the range of 0 to 30.");
    assertTrue(0.00 <= Double.valueOf(rsTest.getDataTwo())
        && Double.valueOf(rsTest.getDataTwo()) <= 99.99,
        "Rain falling meter is not within range of 0 to 99.99.");
    for (int i = 0; i < 100_000; i++) {
      // update the data
      rsTest.updateData();
      // check again if data is within the range
      assertTrue(0.00 <= Double.valueOf(rsTest.getDataOne())
          && Double.valueOf(rsTest.getDataOne()) <= 30.00,
          "Rain rate is not withing in the range of 0 to 30.");
      assertTrue(0.00 <= Double.valueOf(rsTest.getDataTwo())
          && Double.valueOf(rsTest.getDataTwo()) <= 99.99,
          "Rain falling meter is not within range of 0 to 99.99.");
    }
  }

  /** test running method. */
  @Test
  /* default */void testRun() throws InterruptedException {
    final String dataOne = rsTest.getDataOne();
    final String dataTwo = rsTest.getDataTwo();

    final Thread thread = new Thread(rsTest);
    thread.start();
    Thread.sleep(1000L);

    // test if data is updated
    assertFalse(dataOne.equals(rsTest.getDataOne()), "Rain rate have not updated.");
    assertFalse(dataTwo.equals(rsTest.getDataOne()), "Rain fall have not updated.");

    assertTrue(0.00 <= Double.valueOf(rsTest.getDataOne())
        && Double.valueOf(rsTest.getDataOne()) <= 30.00,
        "Updated rain rate is not withing in the range of 0 to 30.");
    assertTrue(0.00 <= Double.valueOf(rsTest.getDataTwo())
        && Double.valueOf(rsTest.getDataTwo()) <= 99.99,
        "Updated rain falling meter is not within range of 0 to 99.99.");
  }
}

package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import sensors.WindDirectionSensor;

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
class WindDirectionSensorTest {
  /** Object for setting up purpose. **/
  private WindDirectionSensor wdTest = new WindDirectionSensor();

  /** test toString method. */
  @Test
  /* default */void testToString() {
    assertEquals(wdTest.toString(), "Wind Direction Sensor",
        "Wind Direction sensor's toString method is not working");
  }

  /** test data of the sensor. */
  @Test
  /* default */void testUpdateData() {
    // check if data is within the range
    assertTrue(0 <= Double.valueOf(wdTest.getDataOne()), "value of myData is negative number");
    assertTrue(wdTest.getDataTwo() == "East"
        || wdTest.getDataTwo() == "SouthEast"
        || wdTest.getDataTwo() == "South"
        || wdTest.getDataTwo() == "SouthWest"
        || wdTest.getDataTwo() == "West"
        || wdTest.getDataTwo() == "NorthWest"
        || wdTest.getDataTwo() == "North"
        || wdTest.getDataTwo() == "NorthEast",
        "Wind direction is undetermined");
    for (int i = 0; i < 100_000; i++) {
      // update the data
      wdTest.updateData();
      // check again if data is within the range
      assertTrue(0 <= Double.valueOf(wdTest.getDataOne()), "value of myData is negative number");
      assertTrue(wdTest.getDataTwo() == "East"
          || wdTest.getDataTwo() == "SouthEast"
          || wdTest.getDataTwo() == "South"
          || wdTest.getDataTwo() == "SouthWest"
          || wdTest.getDataTwo() == "West"
          || wdTest.getDataTwo() == "NorthWest"
          || wdTest.getDataTwo() == "North"
          || wdTest.getDataTwo() == "NorthEast",
          "Wind direction is undetermined");
    }
  }

  /** test running method. */
  @Test
  /* default */void testRun() throws InterruptedException {
    final Thread thread = new Thread(wdTest);
    thread.start();
    Thread.sleep(1000L);

    // test if data is updated
    assertTrue(wdTest.getDataTwo() == "East"
        || wdTest.getDataTwo() == "SouthEast"
        || wdTest.getDataTwo() == "South"
        || wdTest.getDataTwo() == "SouthWest"
        || wdTest.getDataTwo() == "West"
        || wdTest.getDataTwo() == "NorthWest"
        || wdTest.getDataTwo() == "North"
        || wdTest.getDataTwo() == "NorthEast",
        "Wind direction is undetermined");
  }
}

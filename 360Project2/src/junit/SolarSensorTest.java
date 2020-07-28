package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import sensors.SolarSensor;

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
class SolarSensorTest {
  
  /** Object for setting up purpose. **/
  private SolarSensor ssTest = new SolarSensor();

  /** test toString method. */
  @Test
  /* default */void testToString() {
    assertEquals(ssTest.toString(), 
        "Solar Radiation Sensor", "Solar radiation sensor toString method is not working");
  }

  /** test data of the sensor. */
  @Test
  /* default */void testUpdateData() {
    for (int i = 0; i < 100_000; i++) {
      // check if data is within the range
      assertTrue(0.00 <= Double.valueOf(ssTest.getDataOne()), "Solar sensor's watt is below 0");
      assertNull(ssTest.getDataTwo(), "Solar second data is not null");
      // update the data
      ssTest.updateData();
      // check again if data is within the range
      assertTrue(0.00 <= Double.valueOf(ssTest.getDataOne()), "Solar sensor's watt is below 0");
      assertNull(ssTest.getDataTwo(), "Solar second data is not null");
    }
  }

  /** test running method. */
  @Test
  /* default */void testRun() throws InterruptedException {
    final String dataOne = ssTest.getDataOne();

    final Thread thread = new Thread(ssTest);
    thread.start();
    Thread.sleep(1000L);

    // test if data is updated
    assertFalse(dataOne.equals(ssTest.getDataOne()), "Temp out have not updated.");

    assertTrue(0.00 <= Double.valueOf(ssTest.getDataOne()) 
        && Double.valueOf(ssTest.getDataOne()) <= 1800,
        "Solar sensor's watt is below 0");
  }
}

package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import sensors.TemperatureSensor;

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
class TemperatureSensorTest {
  
  /** Object for setting up purpose. **/
  private TemperatureSensor tsTest = new TemperatureSensor();

  /** test toString method. */
  @Test
  /* default */void testToString() {
    assertEquals(tsTest.toString(), "Temperature Sensor", 
        "Temperature Sensor toString methods is not working");
  }

  /** test data of the sensor. */
  @Test
  /* default */void testUpdateData() {
    assertTrue(-40.00 <= Double.valueOf(tsTest.getDataOne()) 
        && Double.valueOf(tsTest.getDataOne()) <= 150.00,
        "Temperature out data is not within the range of -40 to 150");
    assertTrue(68.00 <= Double.valueOf(tsTest.getDataTwo()) 
        && Double.valueOf(tsTest.getDataTwo()) <= 76.00,
        "Temperature in data is not within the range of 68 to 76");
    for (int i = 0; i < 10_000; i++) {
      // update the data
      tsTest.updateData();
      // check again if data is within the range
      assertTrue(-40.00 <= Double.valueOf(tsTest.getDataOne()) 
          && Double.valueOf(tsTest.getDataOne()) <= 150.00,
          "Temperature out data is not within the range of -40 to 150");
      assertTrue(68.00 <= Double.valueOf(tsTest.getDataTwo()) 
          && Double.valueOf(tsTest.getDataTwo()) <= 76.00,
          "Temperature in data is not within the range of 68 to 76");
    }
  }

  /** test running method. */
  @Test
  /* default */void testRun() throws InterruptedException {
    final String dataOne = tsTest.getDataOne();
    final String dataTwo = tsTest.getDataTwo();

    final Thread thread = new Thread(tsTest);
    thread.start();
    Thread.sleep(1000L);

    // test if data is updated
    assertFalse(dataOne.equals(tsTest.getDataOne()), "Temp out have not updated.");
    assertFalse(dataTwo.equals(tsTest.getDataOne()), "Temp in have not updated.");

    assertTrue(-40.00 <= Double.valueOf(tsTest.getDataOne()) 
        && Double.valueOf(tsTest.getDataOne()) <= 150.00,
        "Updated temp out data is not within the range of -40 to 150");
    assertTrue(68.00 <= Double.valueOf(tsTest.getDataTwo()) 
        && Double.valueOf(tsTest.getDataTwo()) <= 76.00,
        "Updated temp in data is not within the range of 68 to 76");
  }
}

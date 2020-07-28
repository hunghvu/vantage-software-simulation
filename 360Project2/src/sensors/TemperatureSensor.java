package sensors;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * Sensor to detect temperature.
 * 
 * @author Zitao Yu
 *
 */
@SuppressWarnings({
  
    "PMD.SystemPrintln", "PMD.BeanMembersShouldSerialize", 
    "PMD.AvoidLiteralsInIfCondition"

})
public class TemperatureSensor implements Sensor {

  /** Outdoor Temperature. Range: -40 to 150°F. **/
  private double myTempOut;

  /** Indoor Temperature. Range: 68 to 76°F. **/
  private double myTempIn;

  /**
   * Constructor.
   */
  public TemperatureSensor() {
    myTempOut = RANDOM.nextInt(191) - 40;
    myTempOut += RANDOM.nextDouble();
    myTempOut = Math.round(myTempOut * 100.0) / 100.0;
    myTempIn = RANDOM.nextInt(8) + 68;
    myTempIn += RANDOM.nextDouble();
    myTempIn = Math.round(myTempIn * 100.0) / 100.0;
  }

  @Override
  public String getDataOne() {
    return String.valueOf(myTempOut);
  }

  @Override
  public String getDataTwo() {
    return String.valueOf(myTempIn);
  }
  
  @Override
  public void updateData() {
    myTempOut += RANDOM.nextInt(5) - 1;
    myTempOut += RANDOM.nextDouble();
    myTempOut = Math.round(myTempOut * 100.0) / 100.0;
    if (myTempOut > 150) {
      myTempOut = 150;
    } else if (myTempOut < -40) {
      myTempOut = -40;
    }
    myTempIn += RANDOM.nextInt(3) - 1;
    myTempIn += RANDOM.nextDouble();
    myTempIn = Math.round(myTempIn * 100.0) / 100.0;
    if (myTempIn > 76) {
      myTempIn = 76;
    } else if (myTempIn < 68) {
      myTempIn = 68;
    }
  }

  @Override
  public String toString() {
    return "Temperature Sensor";
  }

  @Override
  public void run() {
    updateData();
    try {
      Thread.sleep(3000L);
    } catch (InterruptedException e) {
      
      System.out.println("Interrupted Exception in TemperatureSensor. Destructive error.");
      
    }
    this.run();
  }
}

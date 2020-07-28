package sensors;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * Sensor to detect Rain.
 * 
 * @author Zitao Yu
 *
 */
@SuppressWarnings({
  
    "PMD.SystemPrintln", "PMD.BeanMembersShouldSerialize", 

})
public class SolarSensor implements Sensor {

  /** Watts for solar sensor. **/
  private double myWatts;

  /**
   * Constructor.
   */
  public SolarSensor() {
    myWatts = RANDOM.nextInt(1801);
    myWatts += RANDOM.nextDouble();
    myWatts = Math.round(myWatts * 100.0) / 100.0;
  }

  @Override
  public String getDataOne() {
    return String.valueOf(myWatts);
  }

  @Override
  public void updateData() {
    myWatts += RANDOM.nextInt(41) - 19;
    myWatts += RANDOM.nextDouble();
    myWatts = Math.round(myWatts * 100.0) / 100.0;
  }

  @Override
  public String toString() {
    return "Solar Radiation Sensor";
  }

  @Override
  public void run() {
    updateData();
    try {
      Thread.sleep(3000L);
    } catch (InterruptedException e) {
      
      System.out.println("Interrupted Exception in SolarSensor. Destructive error.");
      
    }
    this.run();
  }

  @Override
  public String getDataTwo() {
    
    return null;
    
  }

}

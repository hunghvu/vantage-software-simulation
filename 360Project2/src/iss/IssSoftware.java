package iss;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import sensors.BarometricSensor;
import sensors.HumiditySensor;
import sensors.RainSensor;
import sensors.Sensor;
import sensors.TemperatureSensor;
import sensors.WindDirectionSensor;
import sensors.WindSpeedSensor;

// Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * ISS Class implemented serializable, it will receive data from Sensor class
 * and send it out to serialized data for output devices to use.
 * 
 * @author Seungku Kim, Zitao Yu
 */
@SuppressWarnings({

    "serial", "PMD.AvoidFileStream", "PMD.SystemPrintln", "PMD.AvoidInstantiatingObjectsInLoops",
    "PMD.CloseResource", "PMD.DoNotUseThreads", "PMD.ImmutableField", "PMD.LawOfDemeter",
    "PMD.RedundantFieldInitializer"


})
public class IssSoftware implements Serializable, Runnable {

  /**
   * Random object to generate random number.
   */
  private static final Random RANDOM = new Random();
  
  /**
   * This help formatting sunrise minute.
   */
  private static final int MIN_FORMAT = 10;

  /**
   * List for sensors.
   */
  private List<Sensor> mySensors;

  /**
   * List for threads.
   */
  private Set<Thread> myThreads;

  /**
   * A map for sensors data.
   */
  private Map<String, String> myDataMap;

  /**
   * Queue for rain data point.
   */
  private Queue<Double> myRainDataPoint;

  /**
   * A boolean to determine whether or not the sensors are started on own thread.
   */
  private boolean myThreadStart = false;

  /**
   * Constructor for the class.
   * 
   * @throws IOException is thrown if there is no data receiving to the ISS
   *                     device.
   */
  @SuppressWarnings("deprecation")
  public IssSoftware() throws IOException {

    final TemperatureSensor tempSensor = new TemperatureSensor();
    final HumiditySensor humSensor = new HumiditySensor();
    final WindSpeedSensor windSpdSensor = new WindSpeedSensor();
    final WindDirectionSensor windDirSensor = new WindDirectionSensor();
    final BarometricSensor baroSensor = new BarometricSensor();
    final RainSensor rainSensor = new RainSensor();

    // add all sensors to a list for references
    mySensors = new ArrayList<>();
    mySensors.add(windDirSensor);
    mySensors.add(windSpdSensor);
    mySensors.add(tempSensor);
    mySensors.add(humSensor);
    mySensors.add(baroSensor);
    mySensors.add(rainSensor);

    // set up myDataMap and myThreads
    myDataMap = new LinkedHashMap<>();
    // generate 10 rain rate data points
    myRainDataPoint = new LinkedList<>();
    for (int i = 0; i < 10; i++) {
      rainSensor.updateData();
      myRainDataPoint.add(new Double(rainSensor.getDataOne()));
    }
    String dataPoint = myRainDataPoint.toString().substring(1);
    dataPoint = dataPoint.substring(0, dataPoint.length() - 1);
    myDataMap.put("Wind direction: ", windDirSensor.getDataTwo());
    myDataMap.put("Wind speed: ", windSpdSensor.getDataOne());

    // generate random sunrise time
    final int hour = RANDOM.nextInt(6) + 4;
    final int min = RANDOM.nextInt(60);
    String sunriseTime;
    if (min < MIN_FORMAT) {
      sunriseTime = "0" + hour + ":0" + min;
    } else {
      sunriseTime = "0" + hour + ":" + min;
    }
    myDataMap.put("Sunrise time: ", sunriseTime);

    myDataMap.put("Baro pressure: ", baroSensor.getDataOne());
    myDataMap.put("Baro trend: ", baroSensor.getDataTwo());

    // calculate wind chill
    double windChill = Double.parseDouble(tempSensor.getDataOne()) - (RANDOM.nextInt(66) + 15);
    windChill = Math.round(windChill * 100.0) / 100.0;
    myDataMap.put("Wind chill: ", String.valueOf(windChill));

    myDataMap.put("Temp in: ", tempSensor.getDataTwo());
    myDataMap.put("Temp out: ", tempSensor.getDataOne());
    myDataMap.put("Hum in: ", humSensor.getDataTwo());
    myDataMap.put("Hum out: ", humSensor.getDataOne());
    myDataMap.put("Rain rate: ", rainSensor.getDataOne());
    myDataMap.put("Rain: ", rainSensor.getDataTwo());
    // generate random station number
    final int num = RANDOM.nextInt(100_000);
    final String formatted = String.format("%05d", num);
    myDataMap.put("Station number: ", formatted);
    myDataMap.put("Rain graph: ", dataPoint);

    myThreads = new HashSet<>();
    for (final Sensor s : mySensors) {
      myThreads.add(new Thread(s));
    }
  }

  /**
   * Getter for myDataMap.
   * 
   * @return myDataMap.
   */
  public Map<String, String> getDataMap() {
    return myDataMap;
  }

  /**
   * Getter for mySensors.
   * 
   * @return mySensors.
   */
  public List<Sensor> getSensors() {
    return mySensors;
  }

  /**
   * Update myDataMap.
   */
  @SuppressWarnings("deprecation")
  public void updateMap() {
    for (final Sensor s : mySensors) {
      if ("Wind Direction Sensor".equals(s.toString())) {
        
        myDataMap.replace("Wind direction: ", s.getDataTwo());
        
      } else if ("Temperature Sensor".equals(s.toString())) {
        
        myDataMap.replace("Temp out: ", s.getDataOne());
        myDataMap.replace("Temp in: ", s.getDataTwo());
        double windChill = Double.parseDouble(s.getDataOne()) - (RANDOM.nextInt(66) + 15);
        windChill = Math.round(windChill * 100.0) / 100.0;
        myDataMap.replace("Wind chill: ", String.valueOf(windChill));
        
      } else if ("Humidity Sensor".equals(s.toString())) {
        
        myDataMap.replace("Hum out: ", s.getDataOne());
        myDataMap.replace("Hum in: ", s.getDataTwo());
        
      } else if ("Wind Speed Sensor".equals(s.toString())) {
        
        myDataMap.replace("Wind speed: ", s.getDataOne());
        // myDataMap.replace("Wind chill: ", s.getDataTwo());
        
      } else if ("Barometric Sensor".equals(s.toString())) {
        
        myDataMap.replace("Baro pressure: ", s.getDataOne());
        myDataMap.replace("Baro trend: ", s.getDataTwo());
        
      } else if ("Rain Sensor".equals(s.toString())) {
        
        myDataMap.replace("Rain rate: ", s.getDataOne());
        myDataMap.replace("Rain: ", s.getDataTwo());
        myRainDataPoint.remove();
        myRainDataPoint.add(new Double(s.getDataOne()));
        
      }
    }
    String dataPoint = myRainDataPoint.toString().substring(1);
    dataPoint = dataPoint.substring(0, dataPoint.length() - 1);
    myDataMap.replace("Rain graph: ", dataPoint);
  }

  /**
   * Helper method to write serialized data with sensor data received.
   * 
   * @throws IOException if there are no data received
   */
  public void writeSerializedData() throws IOException {

    final File file = new File("data.ser");
    final FileOutputStream fos = new FileOutputStream(file);
    final ObjectOutputStream oos = new ObjectOutputStream(fos);

    for (final Map.Entry<String, String> e : myDataMap.entrySet()) {
      oos.writeObject(e.getKey() + e.getValue());
    }
    oos.close();
  }

  /**
   * Update myDataMap and write serialize data.
   */
  @Override
  public void run() {
    if (!myThreadStart) {
      // call start() on each threads
      for (final Thread t : myThreads) {
        t.start();
      }
      myThreadStart = true;
    }
    try {
      updateMap();
      writeSerializedData();
      // for testing
      printSensorsInfo();
      Thread.sleep(3000L);
    } catch (InterruptedException e) {

      System.out.println("Interrupted exception in IssSoftware. Destructive error.");
      
    } catch (IOException e) {

      System.out.println("IO Exception in IssSoftware. Destructive error.");
    }
    this.run();
  }

  /**
   * --For testing purposes-- Prints out the information of myDataMap.
   */
  private void printSensorsInfo() {
    System.out.println("*******************************************");
    for (final Map.Entry<String, String> e : myDataMap.entrySet()) {
      System.out.println(e.getKey() + e.getValue());
    }
    System.out.println("*******************************************");
  }
}
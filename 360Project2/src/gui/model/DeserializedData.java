package gui.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * This class contains weather data after deserialization process.
 * 
 * @author Hung Vu
 *
 */
@SuppressWarnings({
  
    "deprecation", "PMD.UseConcurrentHashMap", "PMD.UnnecessaryConstructor",
    "PMD.InsufficientStringBufferDeclaration"
  
  })
public class DeserializedData extends Observable {

  /**
   * Number of given data.
   */
  private static final int MAP_SIZE = 14;

  /**
   * A hash map contains data name and its respective value. <br>
   * For example, key: Sky, value: Cloudy.
   * 
   */
  private static final Map<String, String> DATA_MAP = new HashMap<>(MAP_SIZE);

  /**
   * A default constructor.
   */
  public DeserializedData() {

    super();

  }

  /**
   * Update the deserialized data.
   * 
   * @param theName  data's name
   * @param theValue data's value
   */
  public void setData(final String theName, final String theValue) {

    DATA_MAP.put(theName, theValue);

    if (DATA_MAP.size() == MAP_SIZE) {

      setChanged();
      notifyObservers();

    }

  }

  /**
   * Return the deserialized data.
   * 
   * @return
   */
  public Map<String, String> getData() {

    return DATA_MAP;

  }

  /**
   * Print deserialized data to console, for testing purpose only.
   */
  @Override
  public String toString() {
    final StringBuilder tempString = new StringBuilder();
    tempString.append("DESERIALIZED \n");
    for (final String key : DATA_MAP.keySet()) {

      tempString.append(key + " " + DATA_MAP.get(key) + "\n");

    }
    return tempString.toString();
  }

}

package gui.model;

//Refactoring, checkstyle and PMD: done - Hung Vu.
/**
 * An interface with method(s) that facilitates updates between the Controller
 * and GUI components.
 *
 * @author Khue Nguyen, Hung Vu
 * @version Jul 24, 2020
 */
public interface Connect {

  /**
   * This method will update the GUI components to the value indicated only if it
   * matches the data type.
   * 
   * @param data  The data type
   * @param value The value updated
   */
  void changeDisplay(String data, String value);

  /**
   * This method will update the GUI components to the value indicated based on
   * the received data value. Only used for methods which doesn't need data name.
   * 
   * @param value1 The first value (E.g: temp in, temp out, etc)
   * @param value2 The second value (E.g: temp in, temp out, etc)
   * @param value3 The third value (E.g: temp in, temp out, etc)
   */
  void changeDisplay(String value1, String value2, String value3);
}

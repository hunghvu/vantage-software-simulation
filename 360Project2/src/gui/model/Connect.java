package gui.model;

/**
 * An interface with method(s) that facilitates updates between 
 * the Controller and GUI components.
 *
 * @author Khue Nguyen
 * @version Jul 24, 2020
 */
public interface Connect {
    
    /**
     * This method will update the GUI components to the value 
     * indicated only if it matches the data type.
     * 
     * @param data The data type 
     * @param value The value updated
     */
    public void changeDisplay(String data, String value);
}

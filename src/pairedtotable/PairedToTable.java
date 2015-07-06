package pairedtotable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class reads a list of String triplets and returns a text table in which
 * the first element of each triplet is a column (or row) name the second 
 * element is a row (or column) name and the third is a value.
 * <br>
 * Example:
 * <br>
 * input:
 * <pre>
 * A X 1
 * A Y 2
 * B X 3
 * B Y 4
 * </pre>
 * output:
 * <pre>
 *   X Y
 * A 1 2
 * B 3 4
 * </pre>
 
 * @author javier
 *
 */
public class PairedToTable {
  //////////////////////////////////////////////////////////////////////////////
  // Class Constants
  private static final String sepChar = "\t";
  private static final String lineSep = System.getProperty("line.separator");
  ////////////////////////////////////////////////////////////////////////////
  
  //////////////////////////////////////////////////////////////////////////////
  // Public interface
  public Map<String, Map<String, String>> convertToMap(
      List<List<String>> triplets, boolean firstIsColumn) throws Exception {

    if (!this.checkTriplets(triplets)) {
      throw new Exception("One of more triplets has a size different from three.");
    }
    ////////////////////////////////////////////////////////////////////////////
    // Create data structures used to store values and indexes
    Map<String,Map<String,String>> values = new HashMap<>();
    ////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////
    // Reallocate values from list to map.
    for (List<String> triplet: triplets) {
      String indexA = (firstIsColumn) ? triplet.get(0): triplet.get(1);
      String indexB = (firstIsColumn) ? triplet.get(1): triplet.get(0);
      if (!values.containsKey(indexA)) {
        values.put(indexA, new HashMap<String,String>());
      }
      values.get(indexA).put(indexB, triplet.get(2));
    }
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    // Return the generated Map
    return values;
    ////////////////////////////////////////////////////////////////////////////
  }
  public String convertToTextTable(
      List<List<String>> triplets, boolean firstIsColumn) throws Exception {
    ////////////////////////////////////////////////////////////////////////////
    // Create map
    Map<String, Map<String, String>> map = this.convertToMap(
      triplets, firstIsColumn);
    ////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////
    // Collect indexes
    List<String>indexesA = new ArrayList<>();
    List<String>indexesB = new ArrayList<>();
    Set<String>indexesBSet = new HashSet<>();
    indexesA.addAll(map.keySet());
    for (String indexA : map.keySet()) {
      indexesBSet.addAll(map.get(indexA).keySet());
    }
    indexesB.addAll(indexesBSet);
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    // Sort indexes
    Collections.sort(indexesA);
    Collections.sort(indexesB);
    ////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////
    // Generate output text
    StringBuilder sbOut = new StringBuilder();
    // Add header line
    for (String indexA :indexesA) {
      sbOut.append(PairedToTable.sepChar);
      sbOut.append(indexA);
    }
    sbOut.append(PairedToTable.lineSep);
    // Add the rest of the lines
    for (String indexB :indexesB) {
      sbOut.append(indexB);
      for (String indexA :indexesA) {
        sbOut.append(PairedToTable.sepChar);
        if (map.get(indexA).containsKey(indexB)) {
          String currentValue = map.get(indexA).get(indexB);
          sbOut.append(currentValue);
        } else {
          sbOut.append("-");
        }
      }
      sbOut.append(PairedToTable.lineSep);
    }
    return sbOut.toString();
    ////////////////////////////////////////////////////////////////////////////
    
  }
  //////////////////////////////////////////////////////////////////////////////
 
  //////////////////////////////////////////////////////////////////////////////
  // Protected methods
  /**
   * This method check that all list contains three elements
   * @param triplets
   * @return
   */
  protected boolean checkTriplets (List<List<String>> triplets) {
    for (List<String> triplet : triplets) {
      if (triplet.size() != 3) {
        return false;
      }
    }
    return true;
  }
  //////////////////////////////////////////////////////////////////////////////
  
}

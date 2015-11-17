package io.onelinelister.examplelister;

import java.util.HashMap;
import java.util.Map;

import io.onelinelister.LineReducer;

/**
 * This class is an example of use of LineReducer Class.
 * It creates a map from integer to string from a text line.
 * @author javier
 *
 */
public class MapperReducer implements LineReducer<String, Map<Integer,String>>{
  
  //////////////////////////////////////////////////////////////////////////////
  // Instance variables
  private Map<Integer,String> map;
  private int currentIndex;
  //////////////////////////////////////////////////////////////////////////////
  
  //////////////////////////////////////////////////////////////////////////////
  // Constructor
  public MapperReducer() {
    this.map = this.getInitialAccumulator();
    this.currentIndex = 0;
  }
  //////////////////////////////////////////////////////////////////////////////

  //////////////////////////////////////////////////////////////////////////////
  // Public interface
  @Override
  public Map<Integer, String> getInitialAccumulator() {
    return new HashMap<Integer, String>();
  }

  @Override
  public void reduce(String currentElement) {
    this.map.put(currentIndex, currentElement);
    currentIndex++;
  }

  @Override
  public Map<Integer, String> getResult() {
    return this.map;
  }
  //////////////////////////////////////////////////////////////////////////////

}

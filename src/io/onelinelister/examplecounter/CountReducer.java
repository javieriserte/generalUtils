package io.onelinelister.examplecounter;

import io.onelinelister.LineReducer;

/**
 * Example class for LineReducer.
 * This class with CounterParser are used to count de number of lines of a text
 * file. This class contains the accumulator 'counter' and adds the result of 
 * CounterParser to it each time a new line is processed,
 * 
 * @author javier
 *
 */
public class CountReducer implements LineReducer<Integer, Integer> {

  /////////////////////////////////////////////////////////////////////////////
  // Instance variables
  // Counter is the accumulator.
  private int counter = 0;
  /////////////////////////////////////////////////////////////////////////////
  
  //////////////////////////////////////////////////////////////////////////////
  // Public interface
  /**
   * Gets the value of the accumulator before the process starts.
   */
  @Override
  public Integer getInitialAccumulator() {
    return 0;
  }
  /**
   * Process the next element parsed by CounterParser
   */
  @Override
  public void reduce(Integer currentElement) {
    
    this.counter += currentElement + counter;
    
  }
  /**
   * Get the result stored in the accumulator
   */
  @Override
  public Integer getResult() {
    
    return counter;
    
  }
  //////////////////////////////////////////////////////////////////////////////

}

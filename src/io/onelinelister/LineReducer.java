package io.onelinelister;

/**
 * This class is used to perform a reduce (or fold-like) operation of a text file
 * operating line by line.
 * @author javier
 *
 * @param <T> Is the Type of the accumulator.
 * @param <S> Is the Type of the objects resulting after parsing a text line.
 */
public interface LineReducer<T, S> {

  /**
   * @return the value of the starting value of the reduce operation.
   */
  public S getInitialAccumulator();
  
  /**
   * Process the next element of a <code>LineParser</code> with the current 
   * value of the accumulator to get the next value of the accumulator. 
   * @param currentElement 
   */
  public void reduce(T currentElement);
  
  /**
   * @return the value of the accumulator.
   */
  public S getResult();
  
}

package io.onelinelister;

public class CountReducer implements LineReducer<Integer, Integer> {

  private int counter = 0;
  
  @Override
  public Integer getDefaultAccumulator() {
    return null;
  }

  @Override
  public void reduce(Integer currentElement) {
    
    this.counter += currentElement + counter;
    
  }

  @Override
  public Integer getResult() {
    
    return counter;
    
  }

}

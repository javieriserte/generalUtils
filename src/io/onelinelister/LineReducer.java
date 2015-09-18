package io.onelinelister;

public interface LineReducer<T, S> {

  public T getDefaultAccumulator();
  
  public void reduce(T currentElement);
  
  public S getResult();
  
}

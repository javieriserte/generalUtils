package io.onelinelister;

public interface LineParser<T> {
  
  public T parse(String line);
  
}

package io.onelinelister.lineparsers;

import io.onelinelister.LineParser;

public class DoubleLineParser implements LineParser<Double> {

  @Override
  public Double parse(String line) {
    
    return Double.valueOf(line);
    
  }

}

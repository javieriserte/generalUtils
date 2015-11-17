package io.onelinelister.lineparsers;

import io.onelinelister.LineParser;

public class IntegerLineParser implements LineParser<Integer> {

  @Override
  public Integer parse(String line) {
    
    return Integer.valueOf(line);
    
  }

}

package io.onelinelister.examplecounter;

import io.onelinelister.LineParser;

public class CounterParser implements LineParser<Integer> {

  @Override
  public Integer parse(String line) {
    return 1;
  }

}

package io.onelinelister;

import io.onelinelister.OneLineListReader.LineParser;

public class CounterParser implements LineParser<Integer> {

  @Override
  public Integer parse(String line) {
    return 1;
  }

}

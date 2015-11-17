package io.onelinelister.lineparsers;

import io.onelinelister.LineParser;

public class StringLineParser implements LineParser<String> {

  @Override
  public String parse(String line) {
    return line;
  }

}

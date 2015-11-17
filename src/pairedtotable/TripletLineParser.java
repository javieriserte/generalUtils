package pairedtotable;

import java.util.ArrayList;
import java.util.List;

import io.onelinelister.LineParser;

public class TripletLineParser implements LineParser<List<String>> {

  @Override
  public List<String> parse(String line) {
    String[] split = line.split("\\s");
    if (split.length == 3) {
      List<String> triplet = new ArrayList<>();
      triplet.add(split[0]);
      triplet.add(split[1]);
      triplet.add(split[2]);
      return triplet;
    }
    return null;
  }
}

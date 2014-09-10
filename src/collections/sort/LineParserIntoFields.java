package collections.sort;

import java.util.ArrayList;
import java.util.List;

import io.onelinelister.OneLineListReader.LineParser;

/**
 * LineParserIntoFields parses a String representing a line of text into a 
 * List of Strings.
 * 
 * @author javier
 *
 */
public class LineParserIntoFields implements LineParser<List<String>> {

	@Override
	public List<String> parse(String line) {
		
		List<String> result = new ArrayList<String>();
		
		String[] data = line.split("\\s");
		
		for (String string : data) {
			result.add(string);
		}
		return result;
	}

}

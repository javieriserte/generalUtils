package matrix;

import java.util.ArrayList;
import java.util.List;

import io.onelinelister.OneLineListReader;

/**
 * Parses a string into a list of strings.
 * The tab character is used as separator.
 * 
 * @author javier
 *
 */
public class StringListParser implements
		OneLineListReader.LineParser<List<String>> {
	@Override
	public List<String> parse(String line) {
		String[] strings = line.split("\t");
		// Divide the line into fields using <TAB> as separating character
		
		List<String> result = new ArrayList<String>();
		// Create the data structure to store the results;
		
		for (int i = 0; i < strings.length; i++) {
			result.add(strings[i]);
			// Adds every field into the result list
			
		}
		return result;
	}
}

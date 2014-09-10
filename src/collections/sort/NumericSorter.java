package collections.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.onelinelister.OneLineListReader;
import io.onelinelister.OneLineListReader.LineParser;

/**
 * Sort text line in a given input text data according to the numeric value of
 * a given field. Fields are separated by tabs or blank spaces.
 * 
 * @author javier
 *
 */
public class NumericSorter {

	/**
	 * Sorts data in the given BufferedReader by the numeric value of the field.
	 * The order can be reversed. 
	 * Each text line is split into text fields using a given line parser. 
	 * @param lines
	 * @param lineparser
	 * @param sortingField
	 * @param reverse
	 * @return
	 */
	public List<List<String>> sortLines(BufferedReader lines, LineParser<List<String>> lineparser, int sortingField, boolean reverse) {
		
		final int field_index = sortingField-1;
		
		Comparator<List<String>> linesComparator = new TextLineFieldsComparator(field_index);
		
		OneLineListReader<List<String>> reader = new OneLineListReader<List<String>>(lineparser);
		
		try {
			List<List<String>> data = reader.read(lines);
			
			Collections.sort(data, linesComparator);
			
			if (reverse)  {
				
				Collections.reverse(data);
				
			}
			
			return data;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
}

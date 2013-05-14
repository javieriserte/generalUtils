package stringeditor.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class alignstring {

	/**
	 * Uses a given character to align several lines of text according to that character.<br>
	 * 
	 * Example with char '#':
	 * <pre>
	 * Source:
	 * Reg1#Text1#Text2
	 * This is Reg2#This is the Text1# And This is Text2
	 * 
	 * Result:
	 * Reg1        #Text1            #Text2             
	 * This is Reg2#This is the Text1# And This is Text2
 	 * </pre>
	 * @param in
	 * @param c
	 * @return
	 */
	public static String alignWithCharacter(String in, char c) {
		
		// Example:
		// in:
		// Reg1#Text1#Text2
		// This is Reg2#This is the Text1# And This is Text2
		//

		List<String> lines = stripByLines(in);
		// Example:
		// lines:
		// lines.get(0) = "Reg1#Text1#Text2"
		// lines.get(1) = "This is Reg2#This is the Text1# And This is Text2"
		//
		
		List<List<String>> fields = getFields(c, lines);
		// For the example.
		// fields = [ "Reg1", "Text1" , "Text2"]
        //          [ "This is Reg2" , "This is the Text1", "And This is Text2"]		 
		// the '-1' are guards!
		
		fields = sparseLines(fields);
		// fields = [ "Reg1        ", "Text1            ", "Text2            "]
        //          [ "This is Reg2", "This is the Text1", "And This is Text2"]
		
		String result = joinSparsedLines(fields, c);
		// result = "Reg1         # Text1             # Text2            \n
        //           This is Reg2 # This is the Text1 # And This is Text2"

		return result;
		
	}
	
	///////////////////////////
	// Private methods
	private static String joinSparsedLines(List<List<String>> fields, char separator) {
		
		StringBuilder result = new StringBuilder();
		
		String systemLineSepator = System.getProperty("line.separator"); 
		
		for (List<String> lineFields : fields) {
			
			StringBuilder joinedLine = new StringBuilder();
			
			boolean newLine = true;
			
			for (String field : lineFields) {
				
				if (!newLine) joinedLine.append(" " + separator + " " );
				
				joinedLine.append(field);
				
				newLine = false;

			}
			
			result.append(joinedLine.toString().trim()+systemLineSepator);
			
		}
		
		return result.toString();
		
	}

	private static List<List<String>> sparseLines(List<List<String>> fields) {
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		List<Integer> maxlengths = getMaxLengths(fields);
		
		for (List<String> lineFields : fields) {
			
			List<String> newLineFields = new ArrayList<String>();

			for (int i = 0; i<lineFields.size(); i++) {

				StringBuilder newField = new StringBuilder();

				newField.append(lineFields.get(i));
				
				for (int j=lineFields.get(i).length(); j<maxlengths.get(i);j++) {
					
					newField.append(' ');
					
				}
				
				newLineFields.add(newField.toString());
				
			}
			
			result.add(newLineFields);
			
		}
		
		return result;
		
	}

	private static List<Integer> getMaxLengths(List<List<String>> fields) {
		List<Integer> maxlengths = new ArrayList<Integer>();


		int maxFields = 0;
		
		for (List<String> lineFields : fields ) {

			maxFields = Math.max(lineFields.size(),maxFields);			
		}
		
		for (int i =0 ; i<maxFields; i++) { 
		
			maxlengths.add(0);
			
		}
		
		for (List<String> lineFields : fields ) {

			for (int i =0 ; i<lineFields.size(); i++) { 
				
				maxlengths.set(i, Math.max(maxlengths.get(i), lineFields.get(i).length()));
				
			}
			
		}
		return maxlengths;
	}

	private static List<List<String>> getFields(char separatorChar, List<String> lines) {
		
		List<List<String>> fields = new ArrayList<List<String>>();

		for (String line : lines) {
			
			List<String> currentLineFields = new ArrayList<String>();

			String[] lineStrings = line.split(String.valueOf(separatorChar));
			
			for (String field : lineStrings) {
				
				currentLineFields.add(field.trim());
				
			}
			
			fields.add(currentLineFields);
			
		}
		
		return fields;
		
	}

	private static List<String> stripByLines(String in) {
		
		List<String> lines = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new StringReader(in));
		
		String currentline=null;
		
		try { 
			
			while ((currentline = br.readLine()) != null) {
			
				lines.add(currentline);
			
			}
		
		} catch (IOException e) { e.printStackTrace(); }
		
		return lines;
	}
	
}

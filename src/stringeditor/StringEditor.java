package stringeditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides Features to edit Strings.
 * Like different capitalize options, etc.  
 * 
 * @author Javier Iserte 
 *
 */
public class StringEditor {



	//////////////////////////
	// Instance Variables
	private String currentString;
	

	///////////////////////////
	// Constructor
	public StringEditor(String currentString) {
		super();
		this.setCurrentString(currentString);
	}
	
	public StringEditor() {
		super();
		this.setCurrentString("");
	}
	
	///////////////////////////
	// Getter & Setters
	public String setCurrentString(String currentString) {
		this.currentString = currentString;
		return currentString;
	}


	public String getCurrentString() {
		return currentString;
	}

	///////////////////////////
	// Public interface

	/**
	 * Capitalize a string.
	 * 
	 * All letters, after a whitespace or a special symbol is converted to upper case,
	 * otherwise the letter is converted to lower case.
	 */
	public String capitalize(String specialCharsList) {
		char[] chars = this.getCurrentString().toLowerCase().toCharArray();
		char[] specialChars = null;
		if (specialCharsList != null && specialCharsList!="") {
			specialChars = specialCharsList.toCharArray();
		} else {
			return this.setCurrentString("");
		}
		boolean found = false;
		for (int i = 0; i < chars.length; i++) {
			boolean nextToSpecialChar = Character.isWhitespace(chars[i]);
			int j=0;
			while(!nextToSpecialChar && j<specialChars.length) {
				nextToSpecialChar = nextToSpecialChar ||  chars[i]== specialChars[j];
				j++;
			}
			if (!found && Character.isLetter(chars[i])) {
				chars[i] = Character.toUpperCase(chars[i]);
				found = true;
			} else if (nextToSpecialChar) 
				found = false;
			
		}
		
		return this.setCurrentString(String.valueOf(chars));
		
	}
	
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
	public String alignWithCharacter(String in, char c) {
		
		// Example:
		// in:
		// Reg1#Text1#Text2
		// This is Reg2#This is the Text1# And This is Text2
		//
		List<String> lines = new ArrayList<String>();
		
		List<String> result = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new StringReader(in));
		
		String currentline=null;
		
		try { while ((currentline = br.readLine()) != null) {
			
			lines.add(currentline);
			
		}
		} catch (IOException e) { e.printStackTrace(); }

		// Example:
		// lines:
		// lines.get(0) = "Reg1#Text1#Text2"
		// lines.get(1) = "This is Reg2#This is the Text1# And This is Text2"
		//
		int maxApp =0;
		
		for (int i=0; i<lines.size(); i++) {

			String s = lines.get(i) + c;
			
			int counter = 0;
			
			for (int j=0 ; j<s.length(); j++) {
				
				if (s.charAt(j)==c) counter++;
				
			}
			
			maxApp = Math.max(counter, maxApp);
			
		}
		
		// For the example:
		// maxApp = 2
		//
		
		Integer[][] fields = new Integer[lines.size()][maxApp+1];
		for (Integer[] integers : fields) {
			Arrays.fill(integers, -1);
		}
		
		
		for (int i=0; i<lines.size(); i++) {
			
			String s = lines.get(i) + c;
			
			int counter = 1;
			
			for (int j=0 ; j<s.length(); j++) {
				
				if (s.charAt(j)==c) {
					
					fields[i][counter] = j;
					
					counter++;
					
				}
				
			}
			
		}
		
		// For the example.
		// fields = [ -1 , 4 , 10, 16 ]
        //          [ -1 , 12, 30, 49 ]		 
		//
		
		int[] maxlengths = new int[fields[0].length];

		int total = 0;
		
		maxlengths[0] = 0;

		for (int j=1; j<fields[0].length; j++ ) {

			maxlengths[j] = 0;

			for (int i = 0; i<fields.length ;i++) {
			
				int current = Math.max(fields[i][j] - fields[i][j-1],0)-1; 
				
				if (current>maxlengths[j]) maxlengths[j] = current;
				
			}
			
			total = total + maxlengths[j];
			
		}
		
		// maxlengths = [0 , 13, 8, 19]
		
		for (int i = 0; i<fields.length ;i++) {
		
			StringBuilder sb = new StringBuilder(total);
			
			char[] spaces = new char[total + fields[0].length - 2];
			
			Arrays.fill(spaces,' ');
			
			sb.append(new String(spaces));
			
			int current_x = maxlengths[0];
			
			for (int j=1;j<fields[i].length; j++) {
				
				Integer px = fields[i][j-1]+1;
				
				Integer py = Math.max(fields[i][j],px);
				
				String word = lines.get(i).substring(px,py).trim();
				
				sb.insert(current_x, word);
				
				if (j < fields[i].length) {
				
					current_x = current_x + maxlengths[j] + j-1;
				
					sb.insert(current_x , c);
					
					current_x = current_x +1;
				
				}
				
			}
			
			result.add(sb.toString());
			
			
		}
		
		String finalR = "";
		
		for (String si : result) {
			finalR = finalR + si.substring(0,total+fields[0].length-1) + "\n"; 
		}
		
		return finalR;
		
	}
	
	public String capitalize() {
		
		return this.capitalize(".\\_|");
		
	}
	
	public static void main(String[] arg ) {
		
		
		StringEditor se = new StringEditor();
		
		se.setCurrentString("F1#f2#f3\nAA1#aa2#sdfaa3");
		
		System.out.println(se.alignWithCharacter(se.getCurrentString(), '#'));
		
		
	}
	
}

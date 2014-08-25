package stringeditor;

import java.io.IOException;

import stringeditor.functions.alignstring;

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
	
	public String alignWithCharacter(char separator) {
		
		return alignstring.alignWithCharacter(getCurrentString(), separator);
		
	}
	
	
	public String capitalize() {
		
		return this.capitalize(".\\_|");
		
	}
	
	
	/**
	 * Do the same work of perl's join function.
	 * Requires auxiliary classes: LineAppender and FirstLineAppender.
	 * 
	 * 
	 * @param elements
	 * @param delimiter
	 * @return
	 */
	public static String join (Iterable<String> elements, String delimiter) {
		
		LineAppender l1 = new FirstLineAppender();
		
		StringBuilder sb = new StringBuilder();
		
		for (String string : elements) {
			
			l1 = l1.append(sb, delimiter, string);
			
		}
		
		return sb.toString();
		
	}
	private static class LineAppender {
		
		protected LineAppender append(StringBuilder sb, String delimiter, String string) {
			
			sb.append(delimiter);
			sb.append(string);
			return this;
		}
		
	}
	
	private static class FirstLineAppender extends  LineAppender {
		
		protected LineAppender append(StringBuilder sb, String delimiter, String string) {
			
			sb.append(string);
			return new LineAppender();
			
		}
		
	}
	
	
	public static void main(String[] arg ) {
		
		StringEditor se = new StringEditor();

		StringBuilder wholeText = new StringBuilder();
		
		int c = -1;
		
		try {
			while ((c = System.in.read())!=-1) {
				
				wholeText.append(Character.toChars(c));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		se.setCurrentString(wholeText.toString());
		
		System.out.println(se.alignWithCharacter('#'));
		
	}
	
}

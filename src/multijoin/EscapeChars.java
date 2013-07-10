package multijoin;

public class EscapeChars {

	public static String escape(String input){
	
		input = input.replaceAll("\\\\t", "\t");
		
		input = input.replaceAll("\\\\n", "\n");
		
		input = input.replaceAll("\\\\", "\\");

		return input;
		
	}
	
}

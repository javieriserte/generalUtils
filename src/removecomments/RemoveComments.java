package removecomments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class RemoveComments {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		RemoveComments rc = new RemoveComments();
		
		rc.uncomment(System.in, System.out);
		
	}

	public String uncomment(InputStream in) {
		
	String line = null;
		
		BufferedReader inb = new BufferedReader(new InputStreamReader(in));
		
		StringBuilder sb = new StringBuilder();
		
		try {

			while((line = inb.readLine())!=null) {
				
			if (!line.trim().startsWith("#")) {
				
				sb.append(line);

				sb.append(System.getProperty("line.separator"));
				
				}
			
			}
			
		} catch (IOException e) {

			e.printStackTrace();

		}
		
		return sb.toString();
		
	}
	
	public void uncomment(InputStream in, PrintStream out) {
		String line = null;
		
		BufferedReader inb = new BufferedReader(new InputStreamReader(in));
		
		try {

			while((line = inb.readLine())!=null) {
				
			if (!line.trim().startsWith("#")) {
				
				System.out.println(line);
				
				}
			
			}
			
		} catch (IOException e) {

			e.printStackTrace();

		}
		
	}
	
	
	

}

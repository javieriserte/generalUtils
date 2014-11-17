package removecomments;

import io.bufferreaders.UncommenterBufferedReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

/**
 * Removes commented lines from a file.
 * Lines starting with zero or more white spaces or tabs and then a '#' char 
 * are considered commented. 
 * @author javier iserte
 *
 */
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
	
	private void uncomment(InputStream in, PrintStream out) {
		String line = null;
		
		BufferedReader inb = new UncommenterBufferedReader(new InputStreamReader(in));
		BufferedWriter outb = new BufferedWriter(new OutputStreamWriter(out),1024*1024);

		try {

			while((line = inb.readLine())!=null) {
				outb.write(line);

				outb.newLine();
			
			}
			
			outb.flush();
			inb.close();
			outb.close();
			
		} catch (IOException | NullPointerException e) {
			
		} 
		
	}

}

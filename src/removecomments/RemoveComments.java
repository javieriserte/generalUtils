package removecomments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveComments {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String line = null;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {

			while((line = in.readLine())!=null) {
				
			if (!line.trim().startsWith("#")) {
				
				System.out.println(line);
				
				}
			
			}
			
		} catch (IOException e) {

			e.printStackTrace();

		}
		
	}

}

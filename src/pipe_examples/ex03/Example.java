package pipe_examples.ex03;

import java.io.IOException;

public class Example {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		
		DataSource dataSource = new DataSource();
		
		String currentLine= null;
		
		while( (currentLine = dataSource.readLine()) != null) {

			System.out.println(currentLine);
			
		}
		
	}

}

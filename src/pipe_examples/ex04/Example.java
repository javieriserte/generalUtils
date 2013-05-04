package pipe_examples.ex04;

import java.io.IOException;

public class Example {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		
		DataSource dataSource = new DataSource();
		
		DataElement currentElement= null;
		
		int counter = 0;
		
		try {
			while( (currentElement = dataSource.readElement()) != null) {

				counter ++ ;
				System.out.println(counter + " : " + currentElement);
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

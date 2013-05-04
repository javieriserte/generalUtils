package pipe_examples.ex05;

import java.io.IOException;
import java.io.ObjectInputStream;

public class DataConsumer {
	
	public static void main (String[] args) {
		
		DataElement currentElement= null;
		
		int counter = 0;
		
		try {

			ObjectInputStream ois = new ObjectInputStream(System.in);
			
			while( (currentElement = (DataElement) ois.readObject()) != null) {

				counter ++ ;
				
				System.err.println("Received:" + counter + " : " + currentElement);
				
			}
		} catch (ClassNotFoundException e) {
			
		} catch (IOException e) {

		}
		
	}
	
}

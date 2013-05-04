package pipe_examples.ex01;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Example {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		
		int BUFFER = 32;
		 
		PipedInputStream convertPipe = new PipedInputStream(BUFFER);
		PipedOutputStream dataPipe = new PipedOutputStream(convertPipe);
		 
		DataSource dataSource = new DataSource(dataPipe);
		DataConsumer dataConsumer = new DataConsumer(convertPipe);
		
		Thread thread1= new Thread(dataSource);
		
		Thread thread2= new Thread(dataConsumer);
		
		
		thread1.start();
		
		thread2.start();
		
		try {
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

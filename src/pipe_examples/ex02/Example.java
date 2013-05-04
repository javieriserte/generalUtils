package pipe_examples.ex02;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Example {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		
		int BUFFER = 2048;
		 
		PipedInputStream convertPipe = new PipedInputStream(BUFFER);
		PipedOutputStream dataPipe = new PipedOutputStream(convertPipe);
		 
		DataSource dataSource = new DataSource(dataPipe);
		DataConsumer dataConsumer = new DataConsumer(convertPipe);
		
		Thread thread1= new Thread(dataSource);
		
		thread1.start();
		
		dataConsumer.run();
		
	}

}

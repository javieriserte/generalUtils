package pipe_examples.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
 
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
 
public class DataSource implements Runnable
{
    private OutputStream outputStream = null;
    private String [] words = {"crack","hi","you","what","snort","me"}; 
    private XMLStreamWriter writer = null;
    
	PipedInputStream convertPipe = new PipedInputStream(2048);
	
	BufferedReader br = new BufferedReader(new InputStreamReader(convertPipe));
	
    public DataSource() throws IOException
    {
        outputStream = new PipedOutputStream(convertPipe);
        
		Thread thread1= new Thread(this);
		
		thread1.start();

    }
    
    public String readLine() throws IOException {
    	
		return br.readLine();
    	
    }
 
    @Override
    public void run()
    {
        try
        {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            writer = output.createXMLStreamWriter( outputStream );
            writer.writeStartElement("root");
            for (int counter = 0; counter < 100; counter++)
            {
                int result = (int)(Math.random()*10) % 6;
                String word = words[result];
                writer.writeStartElement(counter + "word");
                writer.writeCharacters(word);
                writer.writeEndElement();
                writer.writeCharacters("\n");
            }
            writer.writeEndElement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                writer.flush();

                outputStream.flush();

                writer.close();

                outputStream.close();
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
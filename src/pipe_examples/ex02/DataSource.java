package pipe_examples.ex02;

import java.io.OutputStream;
 
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
 
public class DataSource implements Runnable
{
    private OutputStream outputStream = null;
    private String [] words = {"crack","hi","you","what","snort","me"}; 
    private XMLStreamWriter writer = null;
 
    public DataSource(OutputStream stream)
    {
        outputStream = stream;
    }
 
    @Override
    public void run()
    {
        try
        {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            writer = output.createXMLStreamWriter( outputStream );
            writer.writeStartElement("root");
            for (int counter = 0; counter < 100000; counter++)
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
                outputStream.flush();
                outputStream.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
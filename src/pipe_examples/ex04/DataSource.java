package pipe_examples.ex04;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
 
public class DataSource implements Runnable
{
    private ObjectOutputStream outputStream = null;
    
    private String [] names = {"Thermo","Promega","Fermentas","Eppendorf","Biorad","Biodybamics"};
    private String [] properties = {"Cheap","Support","Good Quality","Equipment","Consumables","Fast Delivery"}; 
    
	private PipedInputStream convertPipe;
	
	private ObjectInputStream oip;
	
    public DataSource() throws IOException
    {
		convertPipe = new PipedInputStream(2048);

        outputStream = new ObjectOutputStream(new PipedOutputStream(convertPipe));
        
		oip = new ObjectInputStream(convertPipe);

		Thread thread1= new Thread(this);

		thread1.start();

    }
    
    public DataElement readElement() throws IOException, ClassNotFoundException{

    	DataElement result = null;
    	
    	try {

    		result = (DataElement) oip.readObject();

    		
		} catch (EOFException e) {
			
			result = null;
			
		}
	
    	return result;
    	
    }
 
    @Override
    public void run()
    {
        try
        {
            for (int counter = 0; counter < 10000; counter++)
            {
                int result_names = (int)(Math.random()*10) % 6;
                String name = names[result_names];
            	
                int result_nprop = (int)(Math.random()*10) % 4+1;
                String[] props = new String[result_nprop];

                for(int i =0;i<result_nprop;i++) {
                	
                    int result_prop = (int)(Math.random()*10) % 3;

                    props[i] =  properties[result_prop];
                	
                }
                
                int result_value = (int)(Math.random()*1000);
                
            	DataElement element = new DataElement(name, result_value, props);
                
            	outputStream.writeObject(element);
                
            }
            
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
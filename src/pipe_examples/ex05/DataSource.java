package pipe_examples.ex05;

import java.io.IOException;
import java.io.ObjectOutputStream;
 
public class DataSource 
{
    private ObjectOutputStream outputStream;
    
    private String [] names = {"Thermo","Promega","Fermentas","Eppendorf","Biorad","Biodybamics"};
    
    private String [] properties = {"Cheap","Support","Good Quality","Equipment","Consumables","Fast Delivery"}; 
    
    public DataSource() throws IOException
    {
        outputStream = new ObjectOutputStream(System.out);
        
    }
    
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

            	System.err.println("Send Object:" + element);
            	
            	outputStream.writeObject(element);
            	
            	outputStream.flush();
            	
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
    
    public static void main (String[] args ) {
    	
    	try {
			DataSource data = new DataSource();
			
			data.run();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }
}
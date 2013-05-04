package pipe_examples.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
public class DataConsumer implements Runnable 
{
    private InputStream inputStream=null;
 
    public DataConsumer(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }
 
    @Override
    public void run()
    {
    	
        int counter=0;
        try
        {
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ));
            String temp=null;
            
            System.out.println(inputStream);
            
            System.out.println(bufferedReader);
            
//            while(!bufferedReader.ready()){};
            
            while((temp=bufferedReader.readLine())!=null)
            {
            	counter++;
                System.out.println(temp);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        System.out.println("End:"+ counter);

    }
}
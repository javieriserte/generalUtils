package pipe_examples.ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
public class DataConsumer {
    private InputStream inputStream=null;
 
    public DataConsumer(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }
 
    public void run()
    {
        try
        {
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ));
            String temp=null;
            while((temp=bufferedReader.readLine())!=null)
            {
                System.out.println(temp);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
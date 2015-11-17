package io.onelinelister;

import java.io.IOException;
import java.io.Reader;

import io.onelinelister.LineParser;

public class OneLineListReducer <T,S> {

    private LineParser<T> parser;
    private LineReducer<T,S> reducer; 
    
    public S reduce( Reader reader ) {

      OneLineBufferedReader<T> br = null;
      
      try {
        
        br = new OneLineBufferedReader<T>(reader, this.parser);

        T currentT = null;
        
        while((currentT=br.readObjectLine())!=null) {
          
          reducer.reduce(currentT);
         
        }
        
        br.close();

      } catch (IOException e) {

        e.printStackTrace();
        
      }
      
      
      return reducer.getResult();
      
    }
    
    
  
}

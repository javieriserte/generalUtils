package io.onelinelister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import io.onelinelister.OneLineListReader.LineParser;

public class OneLineListReducer <T,S> {

    private LineParser<T> parser;
    private LineReducer<T,S> reducer; 
    
    public S reduce( File file ) {

      BufferedReader br = null;
      try {
        br = new BufferedReader(new FileReader(file));
      } catch (FileNotFoundException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      
      String currentline = null;
      
      try {
        while((currentline=br.readLine())!=null) {
          
          T currentElement = parser.parse(currentline);
          
          reducer.reduce(currentElement);
          
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
      return reducer.getResult();
      
    }
    
    
  
}

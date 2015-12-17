package io.bufferreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UncommenterBufferedReader extends BufferedReader {

	public UncommenterBufferedReader(Reader in) {
		
		super(in);
		
	}

	@Override
	public String readLine() throws IOException {
		
    String currentLine = null; 
    
    while ( (currentLine = super.readLine())!= null) {
      
      if (!this.skipThisLine(currentLine)) {
        return currentLine;
      }

    }
    
    return null;

	}

	private boolean skipThisLine(String currentLine) {
	  
	  Pattern pattern = Pattern.compile("^\\s*#");
	  
	  Matcher matcher = pattern.matcher(currentLine);
	  
	  return matcher.matches();
	
	}

}

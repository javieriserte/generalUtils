package io.bufferreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class UncommenterBufferedReader extends BufferedReader {

	public UncommenterBufferedReader(Reader in) {
		
		super(in);
		
	}

	@Override
	public String readLine() throws IOException {
		
		String currentLine = null; 
				
		while ((currentLine = this.getNextLine()) != null);
				
		return currentLine;
			
	}
	
	
	private String getNextLine() throws IOException {
		
		String currentLine = null; 
		
		while ( (currentLine = super.readLine())!= null) {
			
			if (!this.skipThisLine(currentLine)) {
				return currentLine;
			}

		}
		
		return null;

	}

	private boolean skipThisLine(String currentLine) {
		
		for (int i = 0; i< currentLine.length() ; i++) {
			
			char currentChar = currentLine.charAt(i);
			
			if ( currentChar==' ' || currentChar=='\t') {
				continue;
			}

			return currentChar=='#';
			
		}
		return false;
	
	}

}

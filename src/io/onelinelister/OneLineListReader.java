package io.onelinelister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads input data that is conformed by one value per line, and returns 
 * a list of values.
 * 
 * TODO Create a similar class that extends from BufferedReader
 * @author javier
 *
 */
public class OneLineListReader<T> {
    
	////////////////////////////
	// Instance Variables
	private final LineParser<T> parser;
	

	/////////////////////////////
	// Constructor
	public OneLineListReader(LineParser<T> parser) {
		
		this.parser = parser;
		
	}
	
	///////////////////////////////
	// Public Interface
	public List<T> read(File file) {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			return this.read(br);
			
		} catch (FileNotFoundException e) {
			
			System.err.println("There was an error trying to read input file: "+e.getMessage());
			
			System.exit(1);
			
		} catch (IOException e) {
			
			System.err.println("There was an error trying to read input file: "+e.getMessage());
			
			System.exit(1);
			
		}
		
		return null;
		
	}

	public List<T> read(BufferedReader br) throws IOException {
		
		List<T> result = new ArrayList<T>();
		
		String currentline = null;
		
		while((currentline=br.readLine())!=null) {
			
			result.add( this.parser.parse(currentline.trim()));
			
		}
		
		return result;
		
	}
	
	public List<T> readZipped(InputStream input) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(input)); 
		
		List<T> result = new ArrayList<T>();
		
		String currentline = null;
		
		while((currentline=br.readLine())!=null) {
			
			result.add( this.parser.parse(currentline.trim()));
			
		}
		
		return result;
		
	}

	////////////////////////////////
	// Auxiliary Classes
	public interface LineParser<T> {
		
		public T parse(String line);
		
	}
	
	public static class IntegerLineParser implements LineParser<Integer>{

		@Override
		public Integer parse(String line) {
			
			return Integer.valueOf(line);
			
		}
		
	}
	
	public static class DoubleLineParser implements LineParser<Double>{

		@Override
		public Double parse(String line) {
			
			return Double.valueOf(line);
			
		}
		
	}
	
	public static class StringLineParser implements LineParser<String> {

		@Override
		public String parse(String line) {
			return line;
		}
		
		
	}
	
	
	/////////////////////////////////
	// Factory Methods
	public static OneLineListReader<String> createOneLineListReaderForString() {
		
		return new OneLineListReader<String>(new StringLineParser());
		
	}
	
	public static OneLineListReader<Double> createOneLineListReaderForDouble() {
		
		return new OneLineListReader<Double>(new DoubleLineParser());
		
	}
	
	public static OneLineListReader<Integer> createOneLineListReaderForInteger() {
		
		return new OneLineListReader<Integer>(new IntegerLineParser());
		
	}

}

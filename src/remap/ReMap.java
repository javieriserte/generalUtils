package remap;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.onelinelister.OneLineListReader;
import cmdGA.Parser;
import cmdGA.SingleOption;
import cmdGA.exceptions.IncorrectParameterTypeException;
import cmdGA.parameterType.InFileParameter;
import cmdGA.parameterType.InputStreamParameter;
import cmdGA.parameterType.PrintStreamParameter;


/**
 * Reads a dictionary from a file 
 * and replaces ocurrences in a input file with the
 * dictionary.
 * 
 */
public class ReMap {

	/**
	 * Main executable method
	 * 
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		
		/////////////////////////////
		// Create Command line Parser
		Parser parser = new Parser();
		
		/////////////////////////////
		// Add command line options
		SingleOption inOpt = new SingleOption(parser, System.in, "-infile", InputStreamParameter.getParameter());
		
		SingleOption outOpt = new SingleOption(parser, System.out, "-outfile", PrintStreamParameter.getParameter());
		
		SingleOption mapOpt = new SingleOption(parser, null, "-map", InFileParameter.getParameter());
		
		try {
			
			//////////////////////
			// Parse command line
			parser.parseEx(args);
			
			if (!mapOpt.isPresent()) {
				
				System.err.println("-map option is mandatory");
				
				System.exit(1);
				
			}
			
			//////////////////////
			// Get values from 
			// Command line
			BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) inOpt.getValue()));
			
			PrintStream out = (PrintStream) outOpt.getValue();
			
			File mapfile = (File) mapOpt.getValue();
			
			
			//////////////////////////////////
			// Read input values to be mapped
			List<String> values = OneLineListReader.createOneLineListReaderForString().read(in);
			
			//////////////////////////////////
			// Creates a map from a file
			Map<String, String> map = createMap(mapfile);
			
			//////////////////////////////////
			// Substitute values with the 
			// dictionary map
			performReplacement(out, values, map);
			
		} catch (IncorrectParameterTypeException e) {
			
			System.err.println("There was an error trying to parse the comand line: "+e.getMessage());
			
			System.exit(1);
			
		} catch (IOException e) {
			
			System.err.println("There was an error reading input data: "+e.getMessage());
			
			System.exit(1);
			
		}

	}

	///////////////////////////////
	// Private methods
	/**
	 * Reads an input file a creates a Map.
	 * 
	 * The input file must has two columns separated by a tab char.
	 * The first column has the key values and the second the mapped values 
	 * 
	 * @param mapfile
	 * @return
	 */
	private static Map<String, String> createMap(File mapfile) {
		
		Map<String,String> map = new HashMap<String, String>();
		
		List<String> tmpMapRep = OneLineListReader.createOneLineListReaderForString().read(mapfile);

		for (String string : tmpMapRep) {
			
			String[] data = string.split("\t");

			map.put(data[0].trim(), data[1].trim());
			
		}
		
		return map;
	}
	
	/**
	 * Replaces every ocurrence of a input key value with 
	 * the mapped value if it is found in the dictionary map.
	 * Prints the results in a given PrintStream. 
	 * If the value is not found nothing is printed.
	 * 
	 * @param out a PrintStream where the results are printed.
	 * @param values a list of input values.
	 * @param map a key-value dictionary. 
	 */
	private static void performReplacement(PrintStream out, List<String> values, 	Map<String, String> map) {
		
		for (String string : values) {
			
			string = string.trim();
			
			if (map.containsKey(string)) {
				
				out.println(map.get(string));
				
			}
			
		}
		
	}



}

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
import cmdGA2.CommandLine;
import cmdGA2.NoArgumentOption;
import cmdGA2.OptionsFactory;
import cmdGA2.SingleArgumentOption;
import cmdGA2.returnvalues.InfileValue;


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
		
		CommandLine cmdline = new CommandLine();
//		Parser parser = new Parser();
		
		/////////////////////////////
		// Add command line options
//		SingleOption inOpt = new SingleOption(parser, System.in, "-infile", InputStreamParameter.getParameter());
		SingleArgumentOption<InputStream> inOpt = OptionsFactory.createBasicInputStreamArgument(cmdline);

//		SingleOption outOpt = new SingleOption(parser, System.out, "-outfile", PrintStreamParameter.getParameter());
		SingleArgumentOption<PrintStream> outOpt = OptionsFactory.createBasicPrintStreamArgument(cmdline);
		
		SingleArgumentOption<File> mapOpt = new SingleArgumentOption<File>(cmdline, "-map", new InfileValue(), null);
//		SingleOption mapOpt = new SingleOption(parser, null, "-map", InFileParameter.getParameter());
		
		NoArgumentOption invOpt     = new NoArgumentOption(cmdline, "-inverse");
		
		SingleArgumentOption<String> defOpt= OptionsFactory.createBasicStringArgument(cmdline, "-def", null);
		
		//////////////////////
		// Parse command line
		cmdline.readAndExitOnError(args);
			
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
		
		boolean inverse = invOpt.isPresent();
		
		String defaultValue = defOpt.getValue();
		
		//////////////////////////////////
		// Read input values to be mapped
		List<String> values = null;
		try {
			values = OneLineListReader.createOneLineListReaderForString().read(in);
		} catch (IOException e) {
			System.err.println("There was an error trying to read the input file.");
			System.exit(1);
		}
		
		//////////////////////////////////
		// Creates a map from a file
		Map<String, String> map = createMap(mapfile,inverse);
		
		//////////////////////////////////
		// Substitute values with the 
		// dictionary map
		performReplacement(out, values, map, defaultValue);

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
	private static Map<String, String> createMap(File mapfile, boolean inverse) {
		
		Map<String,String> map = new HashMap<String, String>();
		
		List<String> tmpMapRep = OneLineListReader.createOneLineListReaderForString().read(mapfile);

		for (String string : tmpMapRep) {
			
			String[] data = string.split("\t");
			
			int index_domain = (inverse)?1:0;
			   // If reading an inverse map, then
			   // the first element read is the 
			   // codomain, and the second is the 
			   // domain.

			int index_codomain = 1 - index_domain;

			map.put(data[index_domain].trim(), data[index_codomain].trim());
			
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
	private static void performReplacement(PrintStream out, List<String> values, 	Map<String, String> map, String defaultValue) {
		
		for (String string : values) {
			
			string = string.trim();
			
			if (map.containsKey(string)) {
				
				out.println(map.get(string));
				
			} else if (defaultValue!=null) {
				
				out.println(defaultValue);
				
			}
			
		}
		
	}



}

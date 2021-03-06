package remap;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
 * and replaces occurrences in a input file with the
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
		
		////////////////////////////////////////////////////////////////////////////
		// Create Command line Parser
		CommandLine cmdline = new CommandLine();
		// Add command line options
		SingleArgumentOption<InputStream> inOpt = 
		    OptionsFactory.createBasicInputStreamArgument(cmdline);
		SingleArgumentOption<PrintStream> outOpt = 
		    OptionsFactory.createBasicPrintStreamArgument(cmdline);
		SingleArgumentOption<File> mapOpt = new SingleArgumentOption<File>(cmdline,
		    "-map", new InfileValue(), null);
		NoArgumentOption invOpt = new NoArgumentOption(cmdline, "-inverse");
		SingleArgumentOption<String> defOpt =
		    OptionsFactory.createBasicStringArgument(cmdline, "-def", null);
		NoArgumentOption keyOpt = new NoArgumentOption(cmdline, "-keys");
		// Parse command line
		cmdline.readAndExitOnError(args);
    ////////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////////
		// Verifiy command line
		if (!mapOpt.isPresent()) {
			System.err.println("-map option is mandatory");
			System.exit(1);
		}
    ////////////////////////////////////////////////////////////////////////////
			
    ////////////////////////////////////////////////////////////////////////////
		// Get values from 
		// Command line
		BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) inOpt.getValue()));
		PrintStream out = (PrintStream) outOpt.getValue();
		File mapfile = (File) mapOpt.getValue();
		boolean inverse = invOpt.isPresent();
		String defaultValue = defOpt.getValue();
		boolean includeKeys = keyOpt.isPresent();
    ////////////////////////////////////////////////////////////////////////////
		
    ////////////////////////////////////////////////////////////////////////////
		// Read input values to be mapped
		List<String> values = null;
		try {
			values = OneLineListReader.createOneLineListReaderForString().read(in);
		} catch (IOException e) {
			System.err.println("There was an error trying to read the input file.");
			System.exit(1);
		}
    ////////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////
		// Create MapReader Object
		MapReader mapReader= new MapReader();
    ////////////////////////////////////////////////////////////////////////////
		
    ////////////////////////////////////////////////////////////////////////////
		// Creates a map from a file
		Map<String, String> map = mapReader.createMap(mapfile,inverse);
    ////////////////////////////////////////////////////////////////////////////
		
    ////////////////////////////////////////////////////////////////////////////
		// Substitute values with the 
		// dictionary map
		performReplacement(out, values, map, defaultValue,includeKeys);
    ////////////////////////////////////////////////////////////////////////////

	}
 
  //////////////////////////////////////////////////////////////////////////////
	// Private methods
	/**
	 * Replaces every occurrence of a input key value with 
	 * the mapped value if it is found in the dictionary map.
	 * Prints the results in a given PrintStream. 
	 * If the value is not found nothing is printed.
	 * 
	 * @param out a PrintStream where the results are printed.
	 * @param values a list of input values.
	 * @param map a key-value dictionary. 
	 */
	private static void performReplacement(PrintStream out, List<String> values, 
	    Map<String, String> map, String defaultValue, boolean includeKeys) {
		
		for (String string : values) {
			String lineHeader =includeKeys ? string.trim() + "\t" :"";
			if (map.containsKey(string)) {
				out.println(lineHeader + map.get(string));
			} else if (defaultValue!=null) {
				out.println(lineHeader + defaultValue);
			}
		}
		
	}
  //////////////////////////////////////////////////////////////////////////////

}

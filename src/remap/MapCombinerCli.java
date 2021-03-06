package remap;

import io.resources.ResourceContentAsString;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cmdGA2.CommandLine;
import cmdGA2.MultipleArgumentOption;
import cmdGA2.NoArgumentOption;
import cmdGA2.OptionsFactory;
import cmdGA2.SingleArgumentOption;
import cmdGA2.returnvalues.InfileValue;
import cmdGA2.returnvalues.StringValue;

/**
 * MapCombiner executable class
 * @author javier
 *
 */
public class MapCombinerCli {

	
	public static void main(String[] args) {

		////////////////////////////////////////////////////////////////////////
		// Create Command Line object
		CommandLine cmd = new CommandLine();
		////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////
		// Add Options to the command line
		SingleArgumentOption<PrintStream> outOpt = OptionsFactory.createBasicPrintStreamArgument(cmd);
		MultipleArgumentOption<File> inputMapOpt = new MultipleArgumentOption<File>(cmd, "--maps", ',', new ArrayList<File>(), new InfileValue());
		SingleArgumentOption<String> defValueOpt = new SingleArgumentOption<String>(cmd, "--def", new StringValue(), null);
		NoArgumentOption helpOpt = new NoArgumentOption(cmd, "--help");		
		////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////
		// Parse Command Line
		cmd.readAndExitOnError(args);
		////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////
		// check for help
		if (helpOpt.isPresent()) {
			String content = new ResourceContentAsString().readContents("help", MapCombinerCli.class);
			System.err.println(content);
			System.exit(1);
		}
		////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////
		// Get values from command line options
		PrintStream out = outOpt.getValue();
		List<File> mapFiles = inputMapOpt.getValues();
		String defaultValue = defValueOpt.getValue();
		////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////
		// Create MapCombiner Object
		MapCombiner combiner = new MapCombiner();
		////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////
		// Read all input maps
		List<Map<String,String>> maps = readAllMaps(mapFiles);
		////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////
		// Compute the combined Map
		Map<String, String> combinedMap = combiner.combineMaps(maps, defaultValue);
		////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////
		// Export results
		for (String key : combinedMap.keySet()) {
			out.println(key + "\t" + combinedMap.get(key));
		}
		////////////////////////////////////////////////////////////////////////
		
	}

	////////////////////////////////////////////////////////////////////////////
	// Private Methods
	private static List<Map<String, String>> readAllMaps(List<File> mapFiles) {
		
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		
		MapReader mapReader = new MapReader();
		
		for (File file : mapFiles) {
			
			result.add(mapReader.createMap(file, false));
			
		}
	
		return result;
	}
	////////////////////////////////////////////////////////////////////////////

}

package apps.maptable;

import io.onelinelister.LineParserIntoFields;
import io.onelinelister.OneLineListReader;
import io.resources.ResourceContentAsString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pair.Pair;
import remap.MapByColumnValue;
import remap.MapTable;
import cmdGA2.CommandLine;
import cmdGA2.MultipleArgumentOption;
import cmdGA2.NoArgumentOption;
import cmdGA2.OptionsFactory;
import cmdGA2.SingleArgumentOption;

public class MapTableCli {

	public static void main(String[] args) {
		////////////////////////////////////////////////////////////////////////
		// Create Command Line object
		CommandLine cmd = new CommandLine();
		////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////
		// Add Options to the command line
		SingleArgumentOption<InputStream> inOpt = 
		    OptionsFactory.createBasicInputStreamArgument(cmd);
		SingleArgumentOption<PrintStream> outOpt = 
		    OptionsFactory.createBasicPrintStreamArgument(cmd);
		MultipleArgumentOption<Pair<Integer,Pair<String,Map<String,String>>>> 
		    inputMapOpt = new MultipleArgumentOption<Pair<Integer,Pair
		    <String,Map<String,String>>>>(cmd, "--maps", ',', 
		        new ArrayList<Pair<Integer,Pair<String,Map<String,String>>>>(), 
		        new MapByColumnValue());
		NoArgumentOption helpOpt = new NoArgumentOption(cmd, "--help");
		////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////
		// Parse Command Line
		cmd.readAndExitOnError(args);
		////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////
    // Check for help
		if (helpOpt.isPresent()) {
		  String helpText = new ResourceContentAsString().readContents("help", 
		      MapTableCli.class);
		  System.out.println(helpText);
		  System.exit(0);
		}
    ////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////
		// Get values from command line options
		BufferedReader in = new BufferedReader(new InputStreamReader(inOpt.getValue()));
		PrintStream out = outOpt.getValue();
		List<Pair<Integer, Pair<String, Map<String, String>>>> mapColumnPairs = inputMapOpt.getValues();
		////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////
		// Read input data;
		List<List<String>> data = null;
		try {
			data = new OneLineListReader<List<String>>(new LineParserIntoFields()).read(in);
		} catch (IOException e) {
			System.err.println("There was an error trying to read input data.");
			System.exit(1);
		}
		
		////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////
		// Map the table at the given columns
		MapTable mapTable = new MapTable();
		List<List<String>> mapped = mapTable.map(data, mapColumnPairs);
		////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////
		// Print out results
		StringBuilder sb;
		for (List<String> list : mapped) {
			
			sb = new StringBuilder();
			String sep = "";
			for (String string : list) {
				sb.append(sep+string); 
				sep = " ";
			}
			
			out.println(sb.toString());
			
		} 
		////////////////////////////////////////////////////////////////////////

	}

}

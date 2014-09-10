package collections.sort;

import io.onelinelister.LineParserIntoFields;
import io.onelinelister.OneLineListReader.LineParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import cmdGA2.CommandLine;
import cmdGA2.NoArgumentOption;
import cmdGA2.OptionsFactory;
import cmdGA2.SingleArgumentOption;
import cmdGA2.returnvalues.IntegerValue;
/**
 * Command line executable of Numeric Sorter.
 * 
 * @author javier
 *
 */
public class NumericSorterCli {

	public static void main(String[] args) {
		
		CommandLine cmd = new CommandLine();
		
		SingleArgumentOption<InputStream> inOpt = OptionsFactory.createBasicInputStreamArgument(cmd);
		SingleArgumentOption<PrintStream> outopt = OptionsFactory.createBasicPrintStreamArgument(cmd);
		SingleArgumentOption<Integer> fieldNumberopt = new SingleArgumentOption<Integer>(cmd, "--n", new IntegerValue(), 1);
		NoArgumentOption revOpt= new NoArgumentOption(cmd, "--r");
		
		cmd.readAndExitOnError(args);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(inOpt.getValue()));
		PrintStream out = outopt.getValue();
		int fieldNumber = fieldNumberopt.getValue();
		
		
		LineParser<List<String>> lineparser = new LineParserIntoFields(); 
		
		NumericSorter ns = new NumericSorter();
		
		List<List<String>> result = ns.sortLines(in, lineparser, fieldNumber, revOpt.isPresent());
		
		StringBuilder sb;
		
		for (List<String> list : result) {
			
			sb = new StringBuilder();
			String sep = "";
			for (String string : list) {
				sb.append(sep+string); 
				sep = " ";
			}
			
			out.println(sb.toString());
			
		} 
		
	}
	
}

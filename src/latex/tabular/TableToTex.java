package latex.tabular;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cmdGA.Parser;
import cmdGA.SingleOption;
import cmdGA.exceptions.IncorrectParameterTypeException;
import cmdGA.parameterType.InFileParameter;
import cmdGA.parameterType.PrintStreamParameter;
import cmdGA.parameterType.StringParameter;


/**
 * Exports tab-spearated text tables in a latex tabular template envirement.
 * 
 */
public class TableToTex {
	
	public static void main(String[]  arg) {
		
		// Define command line Parser and options 
		Parser parser = new Parser();
		
		SingleOption inOpt = new SingleOption(parser, System.in, "-infile", InFileParameter.getParameter());
		
		SingleOption outOpt = new SingleOption(parser, System.out, "-outfile", PrintStreamParameter.getParameter());
		
		SingleOption sepCharOpt = new SingleOption(parser, "\t", "-s", StringParameter.getParameter());
		
		
		// Parse command line
		parseCommandLine(arg, parser);
		

		// Get values from command line options
		BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) inOpt.getValue()));
		
		PrintStream out = (PrintStream) outOpt.getValue();
		
		String sep = (String) sepCharOpt.getValue();
		
		// Read data from input
		List<String[]> data = readData(in, sep);
		
		// Print output
		printHeader(out, data);
		
		printFields(out, data);
		
		printFooter(out);
		
	}

	public static void printFooter(PrintStream out) {
		
		String[] footer = new String[]{"\\end{tabular}","\\caption{tablecaption}","\\label{tablelabel}","\\end{table}"};

		printFooterOrHeader(out, footer);
		
	}

	public static void printHeader(PrintStream out, List<String[]> data) {

		int width = getWidth(data);
		
		char[] c = new char[width];
		
		Arrays.fill(c, 'l');
		
		String[] header = new String[]{"\\begin{table}","\\begin{tabular}{"+String.valueOf(c)+"}"};

		printFooterOrHeader(out, header);
		
	}

	public static int getWidth(List<String[]> data) {
		
		int width = data.get(0).length;
		
		return width;
		
	}

	public static void parseCommandLine(String[] arg, Parser parser) {
		try {
			parser.parseEx(arg);
		} catch (IncorrectParameterTypeException e) {
			
			System.err.println("There was an error parding the command line: \n" + e.getMessage());
			 
			System.exit(1);
			
		}
	}

	public static List<String[]> readData(BufferedReader in, String sep) {
		List<String[]> data = new ArrayList<String[]>();
		
		String currentline = null;
		
		try {
			
			while((currentline=in.readLine()) != null) {
				
				String[] words = currentline.split(sep);
				
				data.add(words);
				
			}
			
		} catch (IOException e) {
			
			System.err.println("There was an error trying to read input data:" + e.getMessage());
			
			System.exit(1);
		}
		return data;
	}

	public static void printFooterOrHeader(PrintStream out, String[] footer) {
		for (String string : footer) {
			
			out.println(string);
			
		}
	}

	public static void printFields(PrintStream out, List<String[]> data) {
		
		int width = getWidth(data);
		
		int[] widths = calculateFieldWeigths(data, width);
		
		for (String[] line : data) {
			
			StringBuilder sb = new StringBuilder();

			for (int i =0 ; i<line.length;i++) {
			
				String string = line[i];
				
				if (i>0) {
					
					sb.append(" & ");
					
				} 
				
				sb.append(String.format("%"+widths[i]+"s", string.trim()));
				
			}
			
			out.println(sb.toString() + " \\\\");
			
		}
	}

	public static int[] calculateFieldWeigths(List<String[]> data, int width) {
		int[] widths = new int[width];
		
		Arrays.fill(widths, 0);

		for (int i = 0; i<widths.length; i++) {
			
			for (int j=0; j< data.size(); j++) {
				
				if (i<data.get(j).length) { 
					
					widths[i] = Math.max(widths[i], data.get(j)[i].trim().length());
					
				}
				
			}
			
		}
		return widths;
	}
	
}

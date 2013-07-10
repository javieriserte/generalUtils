package multijoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmdGA.MultipleOption;
import cmdGA.NoOption;
import cmdGA.Parser;
import cmdGA.SingleOption;
import cmdGA.exceptions.IncorrectParameterTypeException;
import cmdGA.parameterType.InFileParameter;
import cmdGA.parameterType.PrintStreamParameter;
import cmdGA.parameterType.StringParameter;

public class MultiJoin {

	/**
	 * MultiJoin is an extension of Join command from GNU-Utils. 
	 * Instead of join only two files, MultiJoin permits join many files at once. 
	 * 
	 */
	public static void main(String[] args) {
		
		Parser parser = new Parser();
		
		SingleOption guideOpt = new SingleOption(parser, null, "-g", InFileParameter.getParameter());
		
		MultipleOption filesOpt = new MultipleOption(parser, null, "-f", ',', InFileParameter.getParameter());
		
		SingleOption outOpt = new SingleOption(parser, System.out, "-o", PrintStreamParameter.getParameter());
		
		SingleOption dataPrinterOpt = new SingleOption(parser, new CommonDataPrinter(), "-p", DataPrinterParameter.getParameter());
		
		NoOption exportGuideOpt = new NoOption(parser, "-e");

		SingleOption osepCharOpt = new SingleOption(parser, null, "-os", StringParameter.getParameter());
		
		SingleOption sepCharOpt = new SingleOption(parser, "\t", "-s", StringParameter.getParameter());
		
		NoOption helpOpt = new NoOption(parser, "-h");

		try {
			
			parser.parseEx(args);
		
		} catch (IncorrectParameterTypeException e) {
			
			System.err.println("There was an error parding the command line: \n" + e.getMessage());
			 
			System.exit(1);
			
		}
		
		PrintStream out = (PrintStream) outOpt.getValue();
		
		boolean showHelp = helpOpt.isPresent();
		
		if (showHelp) {
			
			showHelp(out);
			
			System.exit(1);
			
		}
		
		if (!guideOpt.isPresent() || !filesOpt.isPresent()) {
			
			System.err.println("Options -g (guide file) and -f (data files) are mandatory.");
			
			System.exit(1);
			
		}
		
		File guideFile = (File) guideOpt.getValue();
		
		if (guideFile==null) {
			
			System.err.println("Guide file can not be opened");
			
			System.exit(1);
			
		}
		
		String sep = EscapeChars.escape((String) sepCharOpt.getValue());
		
		String osep = EscapeChars.escape((String) osepCharOpt.getValue());
		
		if (osep==null) osep = sep;
		
		List<String> guide = null;
		
		try {
			
			 guide = MultiJoin.parseGuideFile(guideFile);
			
		} catch (IOException e) {

			System.err.println("There was an error with guide file:" + e.getMessage());
			
			System.exit(1);
			
		}
		

		boolean exportGuide = exportGuideOpt.isPresent();
		
		DataPrinter prettyPrint = (DataPrinter) dataPrinterOpt.getValue();
		
		Map<String, List<String>> data = null;
		
		try {
			
			data = MultiJoin.parseDataFile(filesOpt, sep);
			
		} catch (Exception e) {
			
			System.err.println("Data File can be opened: " + e.getMessage());
			
			System.exit(1);

		}
		
		prettyPrint.initialize(out, sep, osep, data, exportGuide);
		
		prettyPrint.printData(guide);
		
		
	}
	
	private static Map<String, List<String>> parseDataFile(MultipleOption filesOpt, String sep) throws Exception {

		Map<String, List<String>> data = new HashMap<String, List<String>>();
		
		for (int i = 0; i<filesOpt.count(); i++) {
			
			File f = (File) filesOpt.getValue(i);
			
			if (f==null) {
				
				throw new Exception("Data file can not be oppened\n");
				
			}
			
			BufferedReader in = new BufferedReader(new FileReader(f));
			
			String currentline = null;
			
			while((currentline=in.readLine())!=null) {
				
				List<String> l = new ArrayList<String>();
				
				String[] lineData = currentline.split(sep);
				
				String guideValue = lineData[0].trim();

				for(int k=1;k<lineData.length;k++) {
					
					l.add(lineData[k]);
					
				}
				
				if (!data.containsKey(guideValue)) {

					data.put(guideValue, new ArrayList<String>());

				}					

				List<String> currentdata = data.get(guideValue);
					
				currentdata.addAll(l);
					
			}
			
			in.close();
			
		}
		
		return data;
		
	}
	
	private static void showHelp(PrintStream out) {
		
		BufferedReader in = new BufferedReader( new InputStreamReader(DataPrinter.class.getResourceAsStream("helpfile")));
		
		String currentline = null;
		
		try {
			while((currentline = in.readLine())!= null) {
				
				out.println(currentline);
				
			}
		} catch (IOException e) {
			
			System.err.println("There was a problem printing the help:" + e.getMessage());
			
		}
		
	}

	private static List<String> parseGuideFile(File guideFile) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(guideFile));
		
		String currentline = null;
		
		List<String> results = new ArrayList<String>();
		
		while((currentline=in.readLine())!=null) {
			
			String trim = currentline.trim();
			
			if (!trim.equals("")) {
			
				results.add(trim);
				
			}
			
		}
		
		in.close();
		
		return results;
		
	}

}

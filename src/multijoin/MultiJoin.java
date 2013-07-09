package multijoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

	private Map<String,List<String>> data;
	
	private List<String> guide;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Parser parser = new Parser();
		
		SingleOption guideOpt = new SingleOption(parser, null, "-g", InFileParameter.getParameter());
		
		MultipleOption filesOpt = new MultipleOption(parser, null, "-f", ',', InFileParameter.getParameter());
		
		SingleOption outOpt = new SingleOption(parser, null, "-o", PrintStreamParameter.getParameter());
		
		NoOption prettyPrintOpt = new NoOption(parser, "-pp");
		
		NoOption exportGuideOpt = new NoOption(parser, "-eg");
		
		SingleOption sepCharOpt = new SingleOption(parser, "\t", "-s", StringParameter.getParameter());
		
		NoOption helpOpt = new NoOption(parser, "-help");

		try {
			
			parser.parseEx(args);
		
		} catch (IncorrectParameterTypeException e) {
			
			System.err.println("There was an error parding the command line: \n" + e.getMessage());
			
			System.exit(1);
			
		}
		
		if (helpOpt.isPresent()) {
			
			
			
			System.exit(1);
			
		}
		
		if (!guideOpt.isPresent() || !filesOpt.isPresent()) {
			
			System.err.println("Options -g (guide file) and -f (data files) are mandatory.");
			
			System.exit(1);
			
		}
		
		File guideFile = (File) guideOpt.getValue();
		
		if (guideFile!=null) {
			
			System.err.println("Guide file can not be opened\n");
			
			System.exit(1);
			
		}
		
		String sep = (String) sepCharOpt.getValue();
		
		MultiJoin mj = new MultiJoin();
		
		mj.setGuide(MultiJoin.parseGuideFile(guideFile));
		
		PrintStream out = (PrintStream) outOpt.getValue();
		
		boolean exportGuide = exportGuideOpt.isPresent();
		
		boolean prettyPrint = prettyPrintOpt.isPresent();
		
		try {
			
			mj.setData(MultiJoin.parseDataFile(filesOpt, sep));
			
		} catch (Exception e) {
			
			System.err.println("Data File can be opened");
			
			System.exit(1);

		}

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
				
				String guideValue = lineData[0];

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

	private static List<String> parseGuideFile(File guideFile) {
		// TODO Auto-generated method stub
		return null;
	}

	///////////////////////////////////////////
	// Getters and Setters

	public Map<String, List<String>> getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}

	public List<String> getGuide() {
		return guide;
	}

	public void setGuide(List<String> guide) {
		this.guide = guide;
	}

}

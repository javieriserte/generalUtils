package math.ranks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import cmdGA.NoOption;
import cmdGA.Parser;
import cmdGA.SingleOption;
import cmdGA.exceptions.IncorrectParameterTypeException;
import cmdGA.parameterType.InFileParameter;
import cmdGA.parameterType.PrintStreamParameter;

public class CompareRanks {
	
	public static void main(String[] arg) throws IncorrectParameterTypeException {
		
		Parser parser = new Parser();
		
		
		SingleOption rank1fileOpt = new SingleOption(parser, null, "-rank1", InFileParameter.getParameter());
		
		SingleOption rank2fileOpt = new SingleOption(parser, null, "-rank2", InFileParameter.getParameter());
		
		SingleOption outOpt = new SingleOption(parser, System.out, "-outfile", PrintStreamParameter.getParameter());
		
		NoOption srccOpt = new NoOption(parser, "-Spearman");
		
		NoOption pearOpt = new NoOption(parser, "-Pearson");
		
		parser.parseEx(arg);
		
		RankingComparator rc = null;
		
		if (srccOpt.isPresent()) {
			
			rc = new SpearmanCorrelation();
			
		} else 
		if (pearOpt.isPresent()) {
			
			rc = new PearsonComparator();
			
		} else{
			
			rc = new DifferenceCorrelation();
			
		}
		
		PrintStream out = (PrintStream) outOpt.getValue();
		
		if ( rank1fileOpt.isPresent() && rank2fileOpt.isPresent()) {
			
			File r1file = (File) rank1fileOpt.getValue();
			
			File r2file = (File) rank2fileOpt.getValue();
			
			Map<Integer, Integer> rank1 = readRank(r1file);
			
			Map<Integer,Integer> rank2 = readRank(r2file);

			out.println(rc.compare(rank1, rank2));
			
		} else {
			
			System.err.println("-rank1 and -rank2 options are mandatory.");
			
		}
		
	}

	private static Map<Integer, Integer> readRank(File file) {
		
		try {
			Map<Integer,Integer> map = new HashMap<Integer, Integer>(); 
			
			BufferedReader br = new BufferedReader(new FileReader(file)) ;
			
			String currenline = null;
			
			int counter = 1;
			
			while((currenline= br.readLine())!=null) {
				
				map.put(Integer.valueOf(currenline), Integer.valueOf(counter));
				
				counter++;
				
			}
			
			br.close();
			
			return map;
			
		} catch (FileNotFoundException e) {
			System.err.println("There was an erro trying to open a file:" + e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("There was an erro trying to open a file:" + e.getMessage());
			System.exit(1);
		} 
		
		return null;
		
	}
	
}

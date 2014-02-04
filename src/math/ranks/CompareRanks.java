package math.ranks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cmdGA.exceptions.IncorrectParameterTypeException;
import cmdGA2.CommandLine;
import cmdGA2.NoArgumentOption;
import cmdGA2.OptionsFactory;
import cmdGA2.SingleArgumentOption;
import cmdGA2.returnvalues.InfileValue;

/**
 * Compares the order of two rankings.<br>
 * Comparison can be made by three methods: 
 * <ol>
 * <li> Spearman ranking correlation score. (default)
 * <li> Pearson coefficient.
 * <li> Absolute difference in rank positions. 
 * </ol>
 * 
 * 
 * @author Javier Iserte
 * 
 */
public class CompareRanks {
	
	public static void main(String[] arg) throws IncorrectParameterTypeException {
		
		///////////////////////////
		// Creates command line 
		CommandLine commandLine = new CommandLine();
		
		////////////////////////////
		// Add options to the command line
		SingleArgumentOption<File> rank1FileOpt = new SingleArgumentOption<File>(commandLine, "--rank1", new InfileValue() , null);
		SingleArgumentOption<File> rank2FileOpt = new SingleArgumentOption<File>(commandLine, "--rank2", new InfileValue() , null);
		SingleArgumentOption<PrintStream> outOpt = OptionsFactory.createBasicPrintStreamArgument(commandLine);
		NoArgumentOption spearmamOpt = new NoArgumentOption(commandLine, "--spearman");
		NoArgumentOption pearsonOpt = new NoArgumentOption(commandLine, "--pearson");
		NoArgumentOption diffOpt = new NoArgumentOption(commandLine, "--diff");
		NoArgumentOption helpOpt = new NoArgumentOption(commandLine, "--help");
		
		/////////////////////////////
		// Parse command line
		commandLine.readAndExitOnError(arg);
		
		///////////////////////////////
		// Check if help flag is present
		
		if ( helpOpt.isPresent()) {
			
			CompareRanks.printHelp();
			
			System.exit(0);
			
		}
		
		/////////////////////////////
		// Validate command line
		if (! (rank1FileOpt.isPresent() && rank2FileOpt.isPresent())) {
			
			System.err.println("--rank1 and --rank2 options are mandatory.");
			
			System.exit(0);
			
		}
		
		///////////////////////////////
		// Create output print stream
		PrintStream out = outOpt.getValue();
		
		//////////////////////////////
		// Create a Ranking Comparator
		RankingComparator rc = null;
		rc = getRankingComparator(spearmamOpt, pearsonOpt, diffOpt);

		///////////////////////////////
		// Read input files
		File rank1File = rank1FileOpt.getValue();
		File rank2File = rank2FileOpt.getValue();
		Map<Integer, Integer> rank1 = readRank(rank1File);
		Map<Integer,Integer> rank2 = readRank(rank2File);
		
		//////////////////////////////
		// Check the both ranks are the same size.
		if (rank1.size() != rank2.size()) {
			System.err.println("Ranks have different sizes");
			System.exit(1);
		}
		//////////////////////////////
		// Check that both ranks use the same labels
		
		Iterator<Integer> it = rank1.keySet().iterator();
		boolean found=true;
		while(it.hasNext() && found) {
			Integer testedKey = it.next();
			found = rank2.containsKey(testedKey);
		}
		if (!found) {
			System.err.println("Ranks have different labels");
			System.exit(1);
		}

		///////////////////////////////
		// Compare ranks and prints output
		out.println(rc.compare(rank1, rank2));
		
	}

	/**
	 * Get the corresponding ranking comparator from the given command line 
	 * arguments
	 * @param spearmamOpt
	 * @param pearsonOpt
	 * @param diffOpt
	 * @param rc
	 * @return
	 */
	private static RankingComparator getRankingComparator(
			NoArgumentOption spearmamOpt, 
			NoArgumentOption pearsonOpt,
			NoArgumentOption diffOpt) {

		RankingComparator rc;
		
		if (pearsonOpt.isPresent()) {
			
			rc = new PearsonComparator();
			
		} else
		if (diffOpt.isPresent()){
			
			rc = new DifferenceCorrelation();
			
		} else {
			
			rc = new SpearmanCorrelation();
			
		}  
		return rc;
	}

	/**
	 * Reads a rank from a file. The keys in the return map are labels, 
	 * the values are the positions in the ranking.
	 * @param file
	 * @return
	 */
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
			System.err.println("There was an error trying to open a file:" + e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("There was an error trying to open a file:" + e.getMessage());
			System.exit(1);
		} 
		
		return null;
		
	}

	/**
	 * Prints help into the standard error buffer.
	 */
	public static void printHelp() {
		
		InputStream helpStream = CompareRanks.class.getResourceAsStream("help");
		
		PrintStream ps = new PrintStream(System.err);
		
		byte current = -1;

		try {
			
			while ((current = (byte) helpStream.read())!=-1) {
			
				ps.write(current);
			
			}
		
		ps.flush();
		
		ps.close();
			
		} catch (IOException e) {
			
			System.err.println("There was an error trying to print the help.");
			
		}
	}
	
}

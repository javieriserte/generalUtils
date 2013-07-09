package stringeditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cmdGA.Parser;
import cmdGA.SingleOption;
import cmdGA.exceptions.IncorrectParameterTypeException;
import cmdGA.parameterType.InputStreamParameter;
import cmdGA.parameterType.PrintStreamParameter;
import cmdGA.parameterType.StringParameter;

/**
 * Given a text matrix, this class returns the transposed matrix.
 * Swaps, columns by rows.
 * 
 * @author javier
 *
 */
public class Transpose {

	private static String lineSeparator = System.getProperty("line.separator");
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Parser parser = new Parser();
		
		SingleOption inOpt = new SingleOption(parser, System.in, "-infile", InputStreamParameter.getParameter());
		
		SingleOption outOpt = new SingleOption(parser, System.out, "-outfile", PrintStreamParameter.getParameter());
		
		SingleOption sepOpt = new SingleOption(parser, "\t", "-sep", StringParameter.getParameter());
		
		try {
			
			parser.parseEx(args);
			
		} catch (IncorrectParameterTypeException e1) {
			
			e1.printStackTrace();
			
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader( (InputStream) inOpt.getValue()));
		
		PrintStream out = (PrintStream) outOpt.getValue();
		
		String sepString = (String) sepOpt.getValue();

		Transpose tr = new  Transpose();
		
		BufferedReader sr = new BufferedReader(new StringReader(tr.transpose(in,sepString)));
		
		String currentLine = null;
		
		try {
			
			while ((currentLine = sr.readLine())!= null) {
				
				out.println(currentLine);
				
				
			}
		} catch (IOException e) {
			
			System.err.println(e.getMessage());
			
		}
		
	}

	public String transpose(BufferedReader in, String sepString) {

		List<String[]> temp ;
		
		StringBuilder result = new StringBuilder();

		try {
			
			temp = readInputMatrix(in, sepString);
			
			System.err.println("Matrix Contains:" + this.getRowCount(temp) + " rows");
			
			System.err.println("Matrix Contains:" + this.getColumnCount(temp) + " columns");
			
			for(int column=0; column< this.getColumnCount(temp) ;column++) {
				
				boolean first = true;
				
				for (int row=0 ; row< this.getRowCount(temp ) ;row++) {

					if (!first) {
						
						result.append(" "  + sepString +" ");
						
					}
					first = false;
					
					result.append(this.getValue(row,column,temp));
					
				}
				
				result.append(lineSeparator);
				
			}
			
		} catch (Exception ex) {
		
			System.err.println(ex.getMessage());
			
			return "";
			
		}
		
		return result.toString();
		
	}

	public List<String[]> readInputMatrix(BufferedReader in, String sepString) throws IOException {
		
		String currentline;
		
		List<String[]> temp = new ArrayList<String[]>();
		
		while((currentline=in.readLine())!=null) {
			
			String[] split = currentline.split(sepString);

			for(int i = 0 ; i< split.length;i++)

			split[i] = split[i].trim();
				
			temp.add(split);
			
		}
		
		return temp;
		
	}
	
	

	private String getValue(int row, int column, List<String[]> tempData) {
		
		if (row<tempData.size() && column<tempData.get(row).length) {
		
			return tempData.get(row)[column];
			
		} else {
			
			return " ";
		}	
	}

	/**
	 * Return the size of the largest row in input data
	 * 
	 * 
	 * @param tempData
	 * @return
	 */
	private int getColumnCount(List<String[]> tempData) {

		int max = 0;
		
		for (String[] strings : tempData) {
			
			max = Math.max(strings.length, max);
			
		}
		
		return max;
		
		
	}

	/**
	 * 
	 * 
	 * @param tempData
	 * @return
	 */
	private int getRowCount(List<String[]> tempData) {
		
		return tempData.size();
		
	}

}

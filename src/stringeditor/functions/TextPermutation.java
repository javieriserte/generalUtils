package stringeditor.functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cmdGA.MultipleOption;
import cmdGA.Parser;
import cmdGA.exceptions.IncorrectParameterTypeException;
import cmdGA.parameterType.InFileParameter;

public class TextPermutation {
	
	public static void main(String[] arg) throws IncorrectParameterTypeException {
		
		Parser parser = new Parser();
		
		MultipleOption file1Opt = new MultipleOption(parser, null, "-f", ',', InFileParameter.getParameter());
		
		parser.parseEx(arg);
		
		
		List<File> files = new ArrayList<File>();
		
		for (int i=0; i<file1Opt.count();i++) {
			
			files.add((File) file1Opt.getValue(i));
			
		}
		
		List<List<String>> lists = new ArrayList<List<String>>();
		
		for(File f: files) {
			
			lists.add(readFile(f));
			
		}
		
		List<String> temp = lists.get(0);
		
		for (int i=1;i<lists.size();i++) {
			
			temp = iteratePermutation(temp,lists.get(i));
			
		}
		
		for (String string : temp) {
			
			System.out.println(string);
			
		}
		
	}

	private static List<String> iteratePermutation(List<String> temp, List<String> list) {
		
		List<String> result = new ArrayList<String>();
		
		
		for (String t:temp) {
			
			for(String l: list) {
				
				result.add( t+"\t"+l );
				
			}
			
		}
		
		return result;
		
		
	}

	private static List<String> readFile(File f) {
		
		List<String> result = new ArrayList<String>();		

		String currentline = null;
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(f));
		
			while((currentline = br.readLine())!=null) {
				
				result.add(currentline.trim());
				   
			}
			
			br.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

}

package multijoin;

import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PrettyDataPrinter extends DataPrinter {

	int elementLengths;
	
	/////////////////////////////
	// Constructor
	public PrettyDataPrinter(PrintStream out, String sep, Map<String,List<String>> data) {
		
		super(out, sep, data);
		
		this.elementLengths = this.getElementLengths(); 
		
	}

	private int getElementLengths() {
		
		Collection<List<String>> a = this.getData().values();
		
		int max = 0;
		
		for (List<String> list : a) {
		
			for (String string : list) {

				max = Math.max(max, string.length());
				
			}
			
		}
		
		return max;
		
	}

	//////////////////////////////
	// Protected Methods
	@Override
	protected String getDataLine(String string) {
		
		StringBuilder bs = new StringBuilder();
		
		boolean first = true;
		
		if (this.isShowGuide()) {
			
			bs.append(string);
			
			first = false;
			
		}
		
		for (String element : this.getData().get(string)) {
			
			if (!first) {
				
				bs.append(this.getSep());
				
			} else {
				
				first = false;
				
			}
			
			bs.append(String.format("%"+this.elementLengths+"s", element));
			
		}
		
		return bs.toString();
		
	}
	
}

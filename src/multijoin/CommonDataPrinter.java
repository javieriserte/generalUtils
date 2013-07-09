package multijoin;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class CommonDataPrinter extends DataPrinter {

	////////////////////////////////////////
	// Constructor
	public CommonDataPrinter(PrintStream out, String sep, Map<String,List<String>> data) {
		super(out,sep, data);
	}

	////////////////////////////////////////
	// Private Methods
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
			
			bs.append(element);
			
		}
		
		return bs.toString();
		
	}


}

package multijoin;

import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PrettyDataPrinter extends DataPrinter {

	//////////////////////////////
	// Instance Variables
	int elementLengths;
	
	//////////////////////////////
	// Public Interface
	@Override	
	public void initialize(PrintStream out, String sep, String osep, Map<String, List<String>> data, boolean showGuide) {
		
		super.initialize(out, sep, osep, data,showGuide);
			
		this.elementLengths = this.getElementLengths();
		
	}

	//////////////////////////////
	// Protected And Private Methods
	@Override
	protected String getDataLine(String string) {
		
		StringBuilder bs = new StringBuilder();
		
		boolean first = true;
		
		if (this.isShowGuide()) {
			
			bs.append(string);
			
			first = false;
			
		}
		
		Map<String, List<String>> data = this.getData();
		
		List<String> list = data.get(string);
		
		if(list!=null) {
		
			for (String element : list) {
			
				if (!first) {
				
					bs.append(" ");
				
				} else {
				
					first = false;
				
				}
			
				bs.append(String.format("%"+this.elementLengths+"s", element));
			
			}
		
		}
		
		return bs.toString();
		
	}
	
	/**
	 * Return the maximum length of any field 
	 * 
	 * @return
	 */
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
	
}

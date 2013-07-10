package multijoin;

import java.util.List;
import java.util.Map;

public class CommonDataPrinter extends DataPrinter {

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
		
		Map<String, List<String>> data = this.getData();
		
		List<String> list = data.get(string);
		
		if (list!=null) {
		
			for (String element : list) {
				
				if (!first) {
					
					bs.append(this.getOsep());
					
				} else {
					
					first = false;
					
				}
				
				bs.append(element);
				
			}
		
		}
		return bs.toString();
		
	}


}

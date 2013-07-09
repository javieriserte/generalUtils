package multijoin;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public abstract class DataPrinter {

	private PrintStream out;
	private boolean showGuide = false;
	private String sep;
	private Map<String,List<String>> data;

	///////////////////////////////////
	// Constructor
	
	public DataPrinter(PrintStream out, String sep, Map<String,List<String>> data) {
		super();
		this.out = out;
		this.sep = sep;
		this.data = data;
	}
	
	//////////////////////////////////////
	// Getters And Setters
	
	public boolean isShowGuide() {
		return showGuide;
	}

	public void setShowGuide(boolean showGuide) {
		this.showGuide = showGuide;
	}
	
	public String getSep() {
		
		return sep;
		
	}
	
	protected PrintStream getPrintStream() {
		return this.out;
	}
	
	protected Map<String,List<String>> getData() {
		
		return this.data;
		
	}
	
	///////////////////////////////////////
	// Public Interface
	
	public void printData(List<String> guide) {
	
		for (String string : guide) {
			
			this.getPrintStream().println(this.getDataLine(string));
			
		}
		
	}

	////////////////////////////////////////
	// Private Methods
	
	protected abstract String getDataLine(String string);



}

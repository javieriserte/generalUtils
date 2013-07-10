package multijoin;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public abstract class DataPrinter {

	private PrintStream out;
	private boolean showGuide = false;
	private String sep;
	private String osep;
	private Map<String,List<String>> data;

	///////////////////////////////////
	// Constructor

	public DataPrinter() {
		super();
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
	public String getOsep() {
		return osep;
	}

	public void setOsep(String osep) {
		this.osep = osep;
	}

	
	///////////////////////////////////////
	// Public Interface
	
	public void initialize(PrintStream out, String sep, String osep, Map<String, List<String>> data, boolean showGuide) {
		this.out = out;
		this.sep = sep;
		this.setOsep(osep);
		this.data = data;
		this.showGuide = showGuide;
	}


	public void printData(List<String> guide) {
	
		for (String string : guide) {
			
			String dataLine = this.getDataLine(string);
			
			if (dataLine!=null) {
			
				this.getPrintStream().println(dataLine);
			
			}
			
		}
		
	}

	////////////////////////////////////////
	// Private Methods
	
	protected abstract String getDataLine(String string);




}

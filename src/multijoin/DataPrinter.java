package multijoin;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

/**
 * Prints out data in a table-like fashion.
 * This class gives the general behaviour, 
 * and is expected that user extends its own 
 * particular class. 
 * 
 * @author javier
 *
 */
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

		String header = this.getHeader();
		
		String footer = this.getFooter();
		
		if (header!=null) {
			
			this.getPrintStream().println(header);
			
		}
		
		for (String string : guide) {
			
			String dataLine = this.getDataLine(string);
			
			if (dataLine!=null) {
			
				this.getPrintStream().println(dataLine);
			
			}
			
		}
		
		if (footer!= null) {
			
			this.getPrintStream().println(footer);
			
		}
		
		
	}

	////////////////////////////////////////
	// protected Methods
	
	protected abstract String getDataLine(String string);

	protected String getHeader() {
		
		return null;
		
	}
	
	protected String getFooter() {
		
		return null;
		
	}


}

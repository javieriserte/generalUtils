package multijoin;

import cmdGA.parameterType.ParameterType;

public class DataPrinterParameter extends ParameterType {

	
	protected static ParameterType singleton = new DataPrinterParameter();
	
	@Override
	protected Object parse(String parameter) {
		
		String st = parameter.trim();

		if (st.equalsIgnoreCase("p")) {// Pretty Printing Option was selected
			
			return new PrettyDataPrinter();
			
		} else 

		if (st.equalsIgnoreCase("c")) { // Common Printing Option was selected
		
			return new CommonDataPrinter();
			
		} else {

			return null;
			
		}
	}
	
	public static DataPrinterParameter getParameter() {
		
		return (DataPrinterParameter) singleton;
		
	}

}

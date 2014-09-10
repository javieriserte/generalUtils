package collections.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Compares two text lines, represented as Lists of Strings, by the numeric 
 * value of one of the fields. 
 * @author javier
 *
 */
public class TextLineFieldsComparator implements Comparator<List<String>>{

	////////////////////////////////////////////////////////////////////////////
	// Instance Variables
	private int field_index;
	////////////////////////////////////////////////////////////////////////////
		
	////////////////////////////////////////////////////////////////////////////
	// Constructor
	public TextLineFieldsComparator(int field_index) {
		super();
		this.field_index = field_index;
	}
	////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////
	// Public Interface
	@Override
	public int compare(List<String> o1, List<String> o2) {
			
			Double d1 = Double.valueOf(o1.get(field_index));
			Double d2 = Double.valueOf(o2.get(field_index));
			
			int diff = d1.compareTo(d2);
			
			if (diff!=0) {
				
				return diff;
				
			} else {
				
			String s1 = o1.toString();
			String s2 = o2.toString();
				
			return s1.compareTo(s2);
			
		}
	}
	////////////////////////////////////////////////////////////////////////////
}
	

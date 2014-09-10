package collections.rangemap;

import java.util.Comparator;

/**
 * This class represents a Range of values.
 * 
 * @author Javier
 *
 * @param <K>
 */
public class Range<K> {
	////////////////////////////////////////////////////////////////////////////
	// Instance Variables
	private K upperBound;
	private K lowerBound;
	private boolean upperIsClosed;
	private boolean lowerIsClosed;
	private Comparator<K> comparator=null;
	////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////
	// Constructor
	/**
	 * Create a new Range object. This implementation do not manages well
	 * range with zero length. I.e. (1,1), [1,1) or (1,1].
	 * The equals methods is used to test if two ranges overlaps. This 
	 * Range is intended to be used only as part of RangeMap. Do not use it for
	 * other work. 
	 *  
	 * @param upperBound
	 * @param lowerBound
	 * @param upperIsClosed
	 * @param lowerIsClosed
	 */
	public Range(K lowerBound, K upperBound, boolean lowerIsClosed, boolean upperIsClosed) {
		this.setLowerBound(lowerBound);
		this.setUpperBound(upperBound);
		this.setUpperIsClosed(upperIsClosed);
		this.setLowerIsClosed(lowerIsClosed);
		this.setComparator(null);
	}
	////////////////////////////////////////////////////////////////////////////
	// Constructor
	/**
	 * Create a new Range object.
	 *  
	 * @param upperBound
	 * @param lowerBound
	 * @param upperIsClosed
	 * @param lowerIsClosed
	 */
	public Range(K lowerBound, K upperBound, boolean lowerIsClosed, boolean upperIsClosed, Comparator<K> comparator) {
		this.setLowerBound(lowerBound);
		this.setUpperBound(upperBound);
		this.setUpperIsClosed(upperIsClosed);
		this.setLowerIsClosed(lowerIsClosed);
		this.setComparator(comparator);
	}
	////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////
	// Public Interface
	/**
	 * Checks if a given value is in the range.
	 * This methods assumes that K implementes Comparable interface,
	 * if not, use <code>inRange(K key, Comparator<K> comp)</code> method with
	 * a custom Comparator object.
	 * @param key
	 * @return true if the key is between the upper and lower bounds of the Range.
	 */
	@SuppressWarnings("unchecked")
	public boolean inRange(K key) {

		if (this.comparator==null) {
		
			Comparable<? super K> k = (Comparable<? super K>) key;

			int checkUpper = k.compareTo(this.getUpperBound());
			int checkLower = k.compareTo(this.getLowerBound());
			
			boolean res = (((checkUpper < 0) || (checkUpper == 0 && upperIsClosed)) && 
					       ((checkLower > 0) || (checkLower == 0 && lowerIsClosed)));
			return res;

		} else {
			
			int checkUpper = this.getComparator().compare(key,this.getUpperBound());
			int checkLower = this.getComparator().compare(key,this.getLowerBound());
			
			boolean res = (((checkUpper < 0) || (checkUpper == 0 && upperIsClosed)) && 
					       ((checkLower > 0) || (checkLower == 0 && lowerIsClosed)));
			return res;
		}
		
	}

	
	public K getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(K upperBound) {
		this.upperBound = upperBound;
	}

	public K getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(K lowerBound) {
		this.lowerBound = lowerBound;
	}

	public boolean isUpperIsClosed() {
		return upperIsClosed;
	}

	public void setUpperIsClosed(boolean upperIsClosed) {
		this.upperIsClosed = upperIsClosed;
	}

	public boolean isLowerIsClosed() {
		return lowerIsClosed;
	}

	public void setLowerIsClosed(boolean lowerIsClosed) {
		this.lowerIsClosed = lowerIsClosed;
	}
	
	protected Comparator<K> getComparator() {
		return comparator;
	}
	protected void setComparator(Comparator<K> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj==null) {
			return false;
		}
		if (obj.getClass()!=this.getClass()) {
			return false;
		}
		@SuppressWarnings("unchecked")
		Range<K> o2 = (Range<K>) obj;
		
		K o1_l1 = this.getLowerBound();
		K o1_u1 = this.getUpperBound();
		K o2_l2 = o2.getLowerBound();
		K o2_u2 = o2.getUpperBound();
		
		////////////////////////////////////////////////////////////////////////
		// Checks if two ranges shares upper and lower bound limits
		if (this.comparator==null) {
			
			@SuppressWarnings("unchecked")
			Comparable<? super K> k1 = (Comparable<? super K>) o1_l1;
			@SuppressWarnings("unchecked")
			Comparable<? super K> k2 = (Comparable<? super K>) o1_u1;
			
			if (k1.compareTo(o2_l2)==0 && k2.compareTo(o2_u2)==0) {
				
				return true;
				
			}
			
		} else {
			
			if (this.getComparator().compare(o1_l1,o2_l2) == 0 && this.getComparator().compare(o1_u1,o2_u2) == 0 ) {
				
				return true;
				
			}
			
		}
		////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////
		// Checks if two ranges intersects each other
		boolean o1_l1_c = o2.inRange(o1_l1); 
		boolean o1_u1_c = o2.inRange(o1_u1);
		boolean o2_l2_c = this.inRange(o2_l2);
		boolean o2_u2_c = this.inRange(o2_u2);
		
		int intersections= (o1_l1_c?1:0)  + (o1_u1_c?1:0) + (o2_l2_c?1:0) + (o2_u2_c?1:0);
		
		return intersections>=2;
		////////////////////////////////////////////////////////////////////////
		
	}
	////////////////////////////////////////////////////////////////////////////
	

	
	

}

package validator.examples.comparables;

import validator.conditions.SingleObjectCondition;

public class SmallerThanCondition<T extends Comparable<T>> extends SingleObjectCondition<T> {

	T maxValue;

	public SmallerThanCondition(T maxValue) {
		super();
		this.maxValue = maxValue;
	}

		@Override
		public boolean verify(T object) {

			return object.compareTo(maxValue)<0;
			
		}

	
}

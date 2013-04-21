package validator.examples.comparables;

import validator.conditions.SingleObjectCondition;

public class GreaterThanCondition<T extends Comparable<T>> extends SingleObjectCondition<T> {

	T minValue;

	public GreaterThanCondition(T minValue) {
		super();
		this.minValue = minValue;
	}


	@Override
	public boolean verify(T object) {

		return object.compareTo(minValue)>0;
		
//		if (object.getClass() == Integer.class) return ((Integer)object) > ((Integer)this.minValue);
//		if (object.getClass() == Double.class) return ((Double)object) > ((Double)this.minValue);
//		if (object.getClass() == Float.class) return ((Float)object) > ((Float)this.minValue);
//		if (object.getClass() == Byte.class) return ((Byte)object) > ((Byte)this.minValue);
//		if (object.getClass() == Long.class) return ((Long)object) > ((Long)this.minValue);
//		if (object.getClass() == Short.class) return ((Short)object) > ((Short)this.minValue);
		
//		return object.doubleValue() > this.minValue.doubleValue();
//		return false;
		
	}

}

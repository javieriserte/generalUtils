package validator.examples.integers;

import validator.conditions.SingleObjectCondition;

public class IntegerSmallerCondition extends SingleObjectCondition<Integer> {

	private int minsize;
	
	
	public IntegerSmallerCondition(int minsize) {
		super();
		this.minsize = minsize;
	}

	@Override
	public boolean verify(Integer object) {
		
		return object < minsize;
		
	}

}

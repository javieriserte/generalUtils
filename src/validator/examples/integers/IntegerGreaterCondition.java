package validator.examples.integers;

import validator.conditions.SingleObjectCondition;

public class IntegerGreaterCondition extends SingleObjectCondition<Integer> {

	private int minsize;
	
	
	public IntegerGreaterCondition(int minsize) {
		super();
		this.minsize = minsize;
	}

	@Override
	public boolean verify(Integer object) {
		
		return object > minsize;
		
	}

}

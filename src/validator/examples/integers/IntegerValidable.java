package validator.examples.integers;

import java.util.List;

import validator.Validable;

public class IntegerValidable extends Validable<Integer> {

	public IntegerValidable(List<Integer> elements) {
		
		super(elements);
		
	}

	public IntegerValidable(Integer element) {

		super(element);

	}

	
	
}

package validator.examples.integers;

import validator.ValidateCondition;
import validator.Validator;
import validator.booleanOp.Validate_OR;
import validator.conditions.Condition;

public class IntegerValidatorExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Condition<Integer> cond1 = new IntegerGreaterCondition(200);
		Condition<Integer> cond2 = new IntegerSmallerCondition(100);
		
		
		Validator<Integer> val = new Validate_OR<Integer>(new ValidateCondition<Integer>(cond1), new ValidateCondition<Integer>(cond2));
		
		
		System.out.println(val.validate(new IntegerValidable(50)));
		System.out.println(val.validate(new IntegerValidable(150)));
		System.out.println(val.validate(new IntegerValidable(250)));

		
		
	}

}

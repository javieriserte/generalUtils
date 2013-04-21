package validator.examples.comparables;

import java.util.ArrayList;
import java.util.List;

import validator.ValidateCondition;
import validator.Validator;
import validator.booleanOp.Validate_AND;
import validator.conditions.Condition;

public class ComparableValidatorExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		// First Step  : Create a custom Condition classes, extending from Condition<T> or SingleObjectCondition<T>
        //               		               
		// Second Step : Create custom Validable class, extending from Validable<T>, restrict T to Types that needed to be evaluated.
		//

		
		// Third Step  : Instanciate the custom conditions you want.
		Condition<Integer> greaterThanCondition = new GreaterThanCondition<Integer>(100);
		
		SmallerThanCondition<Integer> smallerThanCondition = new SmallerThanCondition<Integer>(200);

		
		// Fourth step : Instanciate the a Validator<T> object
		ValidateCondition<Integer> v1 = new ValidateCondition<Integer>(greaterThanCondition); 
		
		ValidateCondition<Integer> v2 = new ValidateCondition<Integer>(smallerThanCondition);
		
		Validator<Integer> v = new Validate_AND<Integer>(v1, v2);
		
		
		// Fifth step  : Instanciate the custom Validable<T> object
		List<Integer> a = new ArrayList<Integer>();
		
		a.add(120);
		
		ComparableValidable<Integer> comp = new ComparableValidable<Integer>(a);
		
		
		// Sixth step  : Perform the validation
		System.out.println(v.validate(comp));
		
		System.out.println(v.validate(new ComparableValidable<Integer>(233)));

	}

}

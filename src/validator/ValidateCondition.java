
package validator;

import java.lang.reflect.Array;

import validator.conditions.Condition;


/**
 * This class represents simple conditions that will be used to test objects.
 * @author Javier Iserte <jiserte@unq.edu.ar>
 */
public class ValidateCondition<T> extends Validator<T> {
	
	private Condition<T> condition;

	public ValidateCondition(Condition<T> condition) {
		super();
		this.condition = condition;
	}

	@Override
	public boolean validate(Validable<T> p) {
		
		T firstElement = p.getElements().get(0);

		if (firstElement != null) {
			
			int size = p.getElements().size();
		
			@SuppressWarnings("unchecked")
			T[] objects = (T[]) Array.newInstance(firstElement.getClass(),size );
			
			for(int i=0; i<size;i++) objects[i] = p.getElement(i);
		
			return this.condition.verify(objects);
		}
		
		return false;
		
	}
	
	
	
	
	
	
} 

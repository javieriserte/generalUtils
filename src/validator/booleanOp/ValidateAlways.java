

package validator.booleanOp;

import validator.Validable;

/**
 * This class represents a dummy condition that is always true. 
 * @author Javier Iserte <jiserte@unq.edu.ar>
 * 
 */
public class ValidateAlways<T> extends ValidateBoolean<T> {

	@Override
	/**
	 * Do not perform any task, just return true.
	 */
	public boolean validate(Validable<T> p) {
		return true;
	}

	@Override
	public String toString() {
		return "ValidateAlways []";
	}

	
	
}

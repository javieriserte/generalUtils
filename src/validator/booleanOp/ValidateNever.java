package validator.booleanOp;

import validator.Validable;

public class ValidateNever<T> extends ValidateBoolean<T> {

	@Override
	public boolean validate(Validable<T> p) {
		return false;
	}

	@Override
	public String toString() {
		return "ValidateNever []";
	}
	
	
}

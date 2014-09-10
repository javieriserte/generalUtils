package validator.conditions;

public abstract class Condition<T> {

	public abstract boolean verify(@SuppressWarnings("unchecked") T ... objects);
	
	
}

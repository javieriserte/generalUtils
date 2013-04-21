package validator.conditions;

public abstract class Condition<T> {

	public abstract boolean verify(T ... objects);
	
	
}

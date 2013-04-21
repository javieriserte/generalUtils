package validator.conditions;

public abstract class SingleObjectCondition<T> extends Condition<T> {

	@Override
	public boolean verify(T... objects) {
		
		if(objects.length > 0) {
			
			return this.verify(objects[0]);
			
		}
		
		return false;
		
	}

	public abstract boolean verify(T object);
	
}

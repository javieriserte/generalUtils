package validator;

public abstract class Validator<T> {
		/**
		 * Validate method is used to verify if an Validable object satisfy a Condition.
		 * @param p the Validable object that is tested.
		 * @return a boolean. True if the condition is satisfied, false otherwise.
		 */
		public abstract boolean validate(Validable<T> p);

	
}

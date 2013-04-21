

package validator.booleanOp;

import validator.Validable;
import validator.Validator;

/**
 * This class represents the boolean operation AND to use with Validator Objects
 * 
 * @author "Javier Iserte <jiserte@unq.edu.ar>"
 * 
 */
public class Validate_AND<T> extends ValidateBoolean<T> {

	private Validator<T> op1;
	private Validator<T> op2;
	
	// COSNTRUCTOR
	/**
	 * Creates a new instance of Validate_AND.
	 * Two Validators are necessary.
	 */
	public Validate_AND(Validator<T> op1, Validator<T> op2) {
		super();
		this.op1 = op1;
		this.op2 = op2;
	}
	
	@Override
	/**
	 * This methods performs the boolean evaluation 'AND' of the two Validators. 
	 */
	public boolean validate(Validable<T> p) {
		return (this.op1.validate(p) && this.op2.validate(p));
	}

	@Override
	public String toString() {
		return "Validate_AND [op1=" + op1 + ", op2=" + op2 + "]";
	}
	
	


}

package validator;

import java.util.ArrayList;
import java.util.List;

public class Validable<T> {

	private List<T> elements;
	
	
	///////////////////////
	// Constructors
	public Validable(List<T> elements) {
		super();
		this.elements = elements;
	}


	public Validable(T element) {
		super();
		
		List<T> list = new ArrayList<T>();
		
		list.add(element);
		
		this.elements = list;
		
	}


	///////////////////////
	// Public Interface
	public T getElement(int index) {
		
		if (index<elements.size() && index>=0) {
			
			return elements.get(index);
			
		}
		
		return null;
		
	}
	
	public int count() {
		return elements.size();
	}


	public List<T> getElements() {
		return elements;
	}
	
}

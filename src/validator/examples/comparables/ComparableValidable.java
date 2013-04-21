package validator.examples.comparables;

import java.util.List;

import validator.Validable;

public class ComparableValidable<T extends Comparable<T>> extends Validable<T> {

	public ComparableValidable(List<T> elements) {
		super(elements);
	}

	public ComparableValidable(T element) {
		super(element);
	}

}

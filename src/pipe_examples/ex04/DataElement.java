package pipe_examples.ex04;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataElement implements Serializable {

	private static final long serialVersionUID = -7204859844491632721L;
	
	private String name;
	
	private int value;
	
	private List<String> properties;

	public DataElement(String name, int value, String ... properties) {
		super();
		this.name = name;
		
		this.value = value;
		
		this.properties = new ArrayList<String>();
		
		for (String string : properties) {
			
			this.properties.add(string);
			
		}
		
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public List<String> getProperties() {

		List<String> newArrayList = new ArrayList<String>();

		for (String string : properties) {
			
			newArrayList.add(string);
			
		}
		
		return newArrayList;
		
	}

	@Override
	public String toString() {
		return "DataElement [name=" + name + ", value=" + value
				+ ", properties=" + properties + "]";
	}
	
	
}

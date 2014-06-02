package io.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Reads the content of a given resource file 
 * as a String.
 * @author javier
 *
 */
public class ResourceContentAsString {

	/**
	 * Reads the content of a source as a String.
	 * 
	 * @param resourceName is the name of the resource to be read.
	 * @return a String with the entire content of the resource as a String.
	 */
	public String readContents(String resourceName, Class<?> c) { 
	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(c.getResourceAsStream(resourceName)));
		
		StringBuilder sb = new StringBuilder();
		
		int currentChar = -1;
		
		try {
			while ((currentChar=br.read())!=-1) {
				
				sb.append((char)currentChar);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	
	}
	
}

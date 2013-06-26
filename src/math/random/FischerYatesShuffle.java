package math.random;

import java.util.List;


/**
 * An implementation of Fisher-Yates in place shuffle algorithm. 
 *
 * @author javier isertes
 */
public class FischerYatesShuffle {

	/**
	 * Performs the shuffling
	 * 
	 * @param elementsShuffled the number of elements to be shuffled.
	 * @param elements the objects to be shuffled
	 */
	public static void shuffle(int elementsShuffled ,Object[] elements) {
		
		for(int i=0; i<elementsShuffled; i++) {
	        
			int rand = (int) (Math.random() * (elements.length-i));

			Object temp = elements[i];
	        
			elements[i] = elements[rand];

			elements[rand] = temp;

		}
	
	}
	
	/**
	 * Performs the shuffling
	 * @param <T>
	 * 
	 * @param elementsShuffled the number of elements to be shuffled.
	 * @param elements the objects to be shuffled
	 */
	public static <T> void shuffle(int elementsShuffled ,List<T> elements) {
		
		for(int i=0; i<elementsShuffled; i++) {
	        
			int rand = (int) (Math.random() * (elements.size()-i));

			T temp = elements.get(i);
			
			elements.set(i, temp);
	        
			elements.set(i,elements.get(rand));

			elements.set(rand,temp);

		}
	
	}
	
}

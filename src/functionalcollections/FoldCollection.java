package functionalcollections;

import java.util.Collection;

public class FoldCollection <A,B>{

	public B fold(B initial, BinaryFunction<B, A> f, Collection<A> as) {

		B result = initial;
		
		for (A a: as) {
			
			result = f.f(initial, a);
			
		}
		
		return result; 
		
	}
	
}

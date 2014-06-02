package functionalcollections;

import java.util.Collection;

public class CollectionMap <A,B>  {

	public void map(UnaryFunction<A,B> f, Collection<A> as, Collection<B> emptyBs) {
		
		for (A a : as) {
			
			emptyBs.add(f.f(a));
			
		}
		
	}
	
}

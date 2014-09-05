package collections.rangemap;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RangeMap<K,V> {
	
	private Map<Range<K>,V> dataMap;
	private Comparator<K>  comparator;
	
	public RangeMap() {
		super();
		this.dataMap = new TreeMap<Range<K>, V>();
		this.comparator=null;
	}
	
	public RangeMap(Comparator<K> comparator) {
		super();
		this.dataMap = new TreeMap<Range<K>, V>();
		this.comparator = comparator;
	}


	public int size() {
		return this.dataMap.size();
	}

	public boolean isEmpty() {
		return this.dataMap.isEmpty();
	}

	public boolean containsValue(Object value) {
		return this.dataMap.containsValue(value);
	}

	public V get(K key) {
		Range<K> rangeKey = new Range<K>(key, key, true, true,this.comparator);
		return this.dataMap.get(rangeKey);
		
	}

	public V put(K lowerBound, K upperBound, boolean lowerIsClosed, boolean upperIsClosed, V value) {
		Range<K> range= new Range<K>(lowerBound, upperBound, lowerIsClosed, upperIsClosed, this.comparator);
		return this.dataMap.put(range, value);
	}

	public V remove(Range<K> keyRange) {
		return this.dataMap.remove(keyRange);
	}

	public void clear() {
		this.dataMap.clear();
		
	}

	public Set<Range<K>> keyRangeSet() {
		return this.dataMap.keySet();
	}

	public Collection<V> values() {
		return this.dataMap.values();
	}
	
}

package collections.rangemap.test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import collections.rangemap.RangeMap;
import datatypes.range.Range;

public class RangeMapTest {

	@Test
	public void testSize() {
		
		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		assertEquals(0, map.size());

		map.put(new Range<Integer>(1,9,true,true), "Menores a diez");
		map.put(new Range<Integer>(10,20,true,true), "Entre diez y veinte");
		map.put(new Range<Integer>(21,49,true,true), "mayores de veinte y menores a 50");
		map.put(new Range<Integer>(100,100,true,true), "Solo cien");
		
		assertEquals(4, map.size());

		
	}

	@Test
	public void testIsEmpty() {
		
		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		assertTrue(map.isEmpty());

		map.put(new Range<Integer>(10, 11,true	, true), "Diez y Once");
		
		assertFalse(map.isEmpty());
		
	}

	@Test
	public void testContainsValue() {

		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		map.put(new Range<Integer>(1,9,true,true), "Menores a diez");
		map.put(new Range<Integer>(10,20,true,true), "Entre diez y veinte");
		map.put(new Range<Integer>(21,49,true,true), "mayores de veinte y menores a 50");
		map.put(new Range<Integer>(100,100,true,true), "Solo cien");
		
		assertTrue(map.containsValue("Menores a diez"));
		assertFalse(map.containsValue("Menores a dies"));
		
	}

	@Test
	public void testGet() {
		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		map.put(new Range<Integer>(0,9,true,true), "Menores a diez");
		map.put(new Range<Integer>(10,20,true,true), "Entre diez y veinte");
		map.put(new Range<Integer>(21,49,true,true), "mayores de veinte y menores a 50");
		map.put(new Range<Integer>(100,101,false,true), "CientoUno");
		map.put(new Range<Integer>(100,100,true,true), "Solo cien");
		
		assertEquals("Menores a diez",map.get(0));
		assertEquals("Menores a diez",map.get( 1));
		assertEquals("Menores a diez",map.get( 9));
		assertEquals("Entre diez y veinte", map.get(10));
		assertEquals("Solo cien", map.get(100));
		assertEquals("CientoUno", map.get(101)); 
	}

	@Test
	public void testPut() {
		
		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		assertEquals(0, map.size());
		map.put(new Range<Integer>(1,5,true,true), "Menores a diez");
		assertEquals(1, map.size());
		map.put(new Range<Integer>(6,10,true,true), "Entre diez y veinte");
		assertEquals(2, map.size());
		map.put(new Range<Integer>(11,15,true,true), "mayores de veinte y menores a 50");
		assertEquals(3, map.size());
		map.put(new Range<Integer>(16,20,true,true), "Solo cien");
		assertEquals(4, map.size());
		map.put(new Range<Integer>(16,20,true,true), "Solo cien");
		assertEquals(4, map.size());
		map.put(new Range<Integer>(16,20,true,true), "Solo cien");
		assertEquals(4, map.size());
		
	}



	@Test
	public void testClear() {
		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		assertEquals(0, map.size());
		map.put(new Range<Integer>(1,5,true,true), "Menores a diez");
		assertEquals(1, map.size());
		map.put(new Range<Integer>(6,10,true,true), "Entre diez y veinte");
		assertEquals(2, map.size());
		
		map.clear();
		assertEquals(0, map.size());

		assertEquals(0, map.size());
		map.put(new Range<Integer>(1,5,true,true), "Menores a diez");
		assertEquals(1, map.size());
		map.put(new Range<Integer>(6,10,true,true), "Entre diez y veinte");
		assertEquals(2, map.size());
	}

	@Test
	public void testKeyRangeSet() {
		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		Range<Integer> key1 = new Range<Integer>(1,5,true,true);
		Range<Integer> key2 = new Range<Integer>(6,10,true,true);

		assertEquals(0, map.size());
		map.put(key1, "Menores a diez");
		assertEquals(1, map.size());
		map.put(key2, "Entre diez y veinte");
		assertEquals(2, map.size());
		
		Set<Range<Integer>> krs = map.keyRangeSet();
		
		assertEquals(2, krs.size());
		assertTrue(krs.contains(key1));
		assertTrue(krs.contains(key2));
	}

	@Test
	public void testValues() {
		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		assertEquals(0, map.size());
		map.put(new Range<Integer>(1,5,true,true), "12345");
		assertEquals(1, map.size());
		map.put(new Range<Integer>(6,10,true,true), "678910");
		assertEquals(2, map.size());
		
		Set<String> values = map.values();
		
		assertEquals(2, values.size());
		assertTrue(values.contains("12345"));
		assertTrue(values.contains("678910"));
		
		
	}
	
	@Test
	public void testDepth() {

		RangeMap<Integer, String> map = new RangeMap<Integer, String>();
		
		int[] testvalues = new int[]{10,100,1000,10000,100000,1000000};
		for (int j : testvalues) {
			for (int i=j; i>=1; i--) {
				
				Range<Integer> currentRange = new Range<Integer>(i,i,true,true);
				map.put(currentRange, String.valueOf(i));
				
			}
			
			int minDepth = (int) Math.floor(Math.log(j+1) / Math.log(2));
			
			int depth = map.getDepth();
			
			assertTrue(depth>=minDepth && depth<=minDepth+5) ;
			

		}

		
	}

}

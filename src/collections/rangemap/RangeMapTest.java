package collections.rangemap;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RangeMapTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSize() {
		
		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		assertEquals(0, map.size());

		map.put(0,10, true, false, "Menores a diez");
		map.put(10,20, true, true, "Entre diez y veinte");
		map.put(21,50, true, true, "mayores de veinte y menores a 50");
		map.put(100,100, true, true, "Solo cien");
		
		assertEquals(4, map.size());
		
	}

	@Test
	public void testIsEmpty() {
		
		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		assertTrue(map.isEmpty());

		map.put(0,10, true, false, "Menores a diez");
		
		assertFalse(map.isEmpty());
		
	}

	@Test
	public void testContainsValue() {

		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		map.put(0,10, true, false, "Menores a diez");
		map.put(10,20, true, true, "Entre diez y veinte");
		map.put(21,50, true, true, "mayores de veinte y menores a 50");
		map.put(100,100, true, true, "Solo cien");
		
		assertTrue(map.containsValue("Menores a diez"));
		assertFalse(map.containsValue("Menores a dies"));
		
	}

	@Test
	public void testGet() {
		RangeMap<Integer, String> map = new RangeMap<Integer, String>();

		map.put(0,10, true, false, "Menores a diez");
		map.put(10,20, true, true, "Entre diez y veinte");
		map.put(21,50, true, true, "mayores de veinte y menores a 50");
		map.put(100,100, true, true, "Solo cien");
		
		assertEquals("Menores a diez",map.get(0));
		assertEquals("Menores a diez",map.get( 1));
		assertEquals("Menores a diez",map.get( 9));
		assertEquals("Entre diez y veinte", map.get(10));
		assertEquals("Solo cien", map.get(100));
	}

	@Test
	public void testPut() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	@Test
	public void testKeyRangeSet() {
		fail("Not yet implemented");
	}

	@Test
	public void testValues() {
		fail("Not yet implemented");
	}

}

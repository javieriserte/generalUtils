package remap.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import remap.MapCombiner;

public class MapCombinerTest extends MapCombiner {

	@Test
	public void testCombineMaps() {
		MapCombiner combiner = new MapCombiner();
		
		Map<String,String> m1 = new HashMap<String, String>();
		Map<String,String> m2 = new HashMap<String, String>();		
		Map<String,String> m4 = new HashMap<String, String>();		

		
		m1.put("A", "1");
		m1.put("B", "2");
		m1.put("C", "3");

		m2.put("1", "Uno");
		m2.put("2", "Dos");
		
		m4.put("Uno", "A");
		m4.put("Dos", "B");

		List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
		
		maps.add(m1);
		maps.add(m2);
		
		Map<String, String> m3 = combiner.combineMaps(maps, "NOT FOUND");
		
		assertEquals("Uno", m3.get("A"));
		assertEquals("Dos", m3.get("B"));
		assertEquals("NOT FOUND", m3.get("C"));
		
		maps.add(m4);
		m3 = combiner.combineMaps(maps, "NOT FOUND");
		assertEquals("A", m3.get("A"));
		assertEquals("B", m3.get("B"));
		assertEquals("NOT FOUND", m3.get("C"));
	}

}

package remap.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;

import org.junit.Test;

import pair.Pair;
import remap.MapByColumnValue;

public class MapByColumnValueTest extends MapByColumnValue {

	@Test
	public void test() {
		String testPathFile = "src"+File.separator+ "remap" +File.separator+"tests" +File.separator+"test_file_01.txt";  
		String s1 = " 1:"+testPathFile+" ";
		String s2 = " 2i:"+testPathFile+" ";
		String s3 = " 33i:"+testPathFile+" ";
		
		MapByColumnValue a = new MapByColumnValue(); 
		
		Pair<Integer, Pair<String, Map<String, String>>> p1 = a.parse(s1);
		Pair<Integer, Pair<String, Map<String, String>>> p2 = a.parse(s2);
		Pair<Integer, Pair<String, Map<String, String>>> p3 = a.parse(s3);
		
		assertEquals((int)p1.getFirst(),(int)1);
		assertEquals((int)p2.getFirst(),(int)2d);
		assertEquals((int)p3.getFirst(),(int)33d);

		assertTrue(p1.getSecond().getSecond().keySet().contains("A"));
		assertTrue(p1.getSecond().getSecond().keySet().contains("B"));
		assertTrue(p1.getSecond().getSecond().keySet().contains("C"));
		assertFalse(p1.getSecond().getSecond().keySet().contains("D"));
		
		assertFalse(p2.getSecond().getSecond().keySet().contains("A"));
		assertTrue(p2.getSecond().getSecond().keySet().contains("1"));
		assertTrue(p2.getSecond().getSecond().keySet().contains("2"));
		assertTrue(p2.getSecond().getSecond().keySet().contains("3"));


		
	}

}

package collections.rangemap.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import collections.rangemap.Range;
import pair.Pair;

public class RangeTest  {

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInRangeK() {
		
		Range<Integer> r1 = new Range<Integer>(1,3, true, true);
		Range<Integer> r2 = new Range<Integer>(1,3, false, false);
		
		
		assertFalse(r1.inRange(0)); 
		assertTrue(r1.inRange(1));
		assertTrue(r1.inRange(2));
		assertTrue(r1.inRange(3));
		assertFalse(r1.inRange(4));
		
		assertFalse(r2.inRange(0)); 
		assertFalse(r2.inRange(1));
		assertTrue(r2.inRange(2));
		assertFalse(r2.inRange(3));
		assertFalse(r2.inRange(4));
		
		
		Comparator<Pair<Integer,Integer>> c = new Comparator<Pair<Integer,Integer>>() {

			@Override
			public int compare(Pair<Integer, Integer> o1,
					Pair<Integer, Integer> o2) {
				Integer p1 = o1.getFirst() + o1.getSecond();
				Integer p2 = o2.getFirst() + o2.getSecond();
				return p1.compareTo(p2);
			}
		};
		
		Range<Pair<Integer,Integer>> r3 = new Range<Pair<Integer,Integer>>(new Pair<Integer, Integer>(0, 1),new Pair<Integer, Integer>(3, 4), true, true,c);
		Range<Pair<Integer,Integer>> r4 = new Range<Pair<Integer,Integer>>(new Pair<Integer, Integer>(0, 1),new Pair<Integer, Integer>(3, 4),false, false,c);
		

		
		assertFalse(r3.inRange(new Pair<Integer, Integer>(0, 0))); 
		assertTrue(r3.inRange(new Pair<Integer, Integer>(0, 1)));
		assertTrue(r3.inRange(new Pair<Integer, Integer>(1, 2)));
		assertTrue(r3.inRange(new Pair<Integer, Integer>(3, 4)));
		assertFalse(r3.inRange(new Pair<Integer, Integer>(4, 4)));
		
		assertFalse(r4.inRange(new Pair<Integer, Integer>(0, 0))); 
		assertFalse(r4.inRange(new Pair<Integer, Integer>(0, 1)));
		assertTrue(r4.inRange(new Pair<Integer, Integer>(1, 2)));
		assertFalse(r4.inRange(new Pair<Integer, Integer>(3, 4)));
		assertFalse(r4.inRange(new Pair<Integer, Integer>(4, 4)));
		
	}
	
	@Test
	public void testEquals() {

		Range<Integer> rt1 = new Range<Integer>(2,3, true, true);
		Range<Integer> rt2 = new Range<Integer>(2,3, false, false);
		Range<Integer> rt3 = new Range<Integer>(2,3, true, false);
		Range<Integer> rt4 = new Range<Integer>(2,3, false, true);
		
		ArrayList<Range<Integer>> testRanges = new ArrayList<Range<Integer>>();
		testRanges.add(rt1);
		testRanges.add(rt2);
		testRanges.add(rt3);
		testRanges.add(rt4);
			

		Range<Integer> r_cc_01 = new Range<Integer>(0,1, true, true);
		Range<Integer> r_cc_12 = new Range<Integer>(1,2, true, true);
		Range<Integer> r_cc_23 = new Range<Integer>(2,3, true, true);
		Range<Integer> r_cc_34 = new Range<Integer>(3,4, true, true);
		Range<Integer> r_cc_45 = new Range<Integer>(4,5, true, true);

		ArrayList<Range<Integer>> ccRanges = new ArrayList<Range<Integer>>();
		ccRanges.add(r_cc_01);
		ccRanges.add(r_cc_12);
		ccRanges.add(r_cc_23);
		ccRanges.add(r_cc_34);
		ccRanges.add(r_cc_45);
		
		Range<Integer> r_aa_01 = new Range<Integer>(0,1, false, false);
		Range<Integer> r_aa_12 = new Range<Integer>(1,2, false, false);
		Range<Integer> r_aa_23 = new Range<Integer>(2,3, false, false);
		Range<Integer> r_aa_34 = new Range<Integer>(3,4, false, false);
		Range<Integer> r_aa_45 = new Range<Integer>(4,5, false, false);
		
		ArrayList<Range<Integer>> aaRanges = new ArrayList<Range<Integer>>();
		aaRanges.add(r_aa_01);
		aaRanges.add(r_aa_12);
		aaRanges.add(r_aa_23);
		aaRanges.add(r_aa_34);
		aaRanges.add(r_aa_45);

		
		Range<Integer> r_ac_01 = new Range<Integer>(0,1, false, true);
		Range<Integer> r_ac_12 = new Range<Integer>(1,2, false, true);
		Range<Integer> r_ac_23 = new Range<Integer>(2,3, false, true);
		Range<Integer> r_ac_34 = new Range<Integer>(3,4, false, true);
		Range<Integer> r_ac_45 = new Range<Integer>(4,5, false, true);
		
		ArrayList<Range<Integer>> acRanges = new ArrayList<Range<Integer>>();
		acRanges.add(r_ac_01);
		acRanges.add(r_ac_12);
		acRanges.add(r_ac_23);
		acRanges.add(r_ac_34);
		acRanges.add(r_ac_45);

		
		Range<Integer> r_ca_01 = new Range<Integer>(0,1, true, false);
		Range<Integer> r_ca_12 = new Range<Integer>(1,2, true, false);
		Range<Integer> r_ca_23 = new Range<Integer>(2,3, true, false);
		Range<Integer> r_ca_34 = new Range<Integer>(3,4, true, false);
		Range<Integer> r_ca_45 = new Range<Integer>(4,5, true, false);

		ArrayList<Range<Integer>> caRanges = new ArrayList<Range<Integer>>();
		caRanges.add(r_ca_01);
		caRanges.add(r_ca_12);
		caRanges.add(r_ca_23);
		caRanges.add(r_ca_34);
		caRanges.add(r_ca_45);
		
		List<List<Range<Integer>>> ranges = new ArrayList<List<Range<Integer>>>();
		ranges.add(ccRanges);
		ranges.add(aaRanges);
		ranges.add(acRanges);
		ranges.add(caRanges);

		Boolean[] r_cc_1 = new Boolean[]{false,true,true, true,false};
		Boolean[] r_cc_2 = new Boolean[]{false,false,true, false,false};
		Boolean[] r_cc_3 = new Boolean[]{false,true,true, false,false};
		Boolean[] r_cc_4 = new Boolean[]{false,false,true, true,false};
		Boolean[][] r_cc = new Boolean[][]{r_cc_1,r_cc_2,r_cc_3,r_cc_4}; 
		
		Boolean[] r_aa_1 = new Boolean[]{false,false,true, false,false};
		Boolean[] r_aa_2 = new Boolean[]{false,false,true, false,false};
		Boolean[] r_aa_3 = new Boolean[]{false,false,true, false,false};
		Boolean[] r_aa_4 = new Boolean[]{false,false,true, false,false};
		Boolean[][] r_aa = new Boolean[][]{r_aa_1,r_aa_2,r_aa_3,r_aa_4}; 
		
		Boolean[] r_ac_1 = new Boolean[]{false,false,true, true,false};
		Boolean[] r_ac_2 = new Boolean[]{false,false,true, false,false};
		Boolean[] r_ac_3 = new Boolean[]{false,false,true, false,false};
		Boolean[] r_ac_4 = new Boolean[]{false,false,true, true,false};
		Boolean[][] r_ac = new Boolean[][]{r_ac_1,r_ac_2,r_ac_3,r_ac_4}; 
		
		Boolean[] r_ca_1 = new Boolean[]{false,true,true, false,false};
		Boolean[] r_ca_2 = new Boolean[]{false,false,true, false,false};
		Boolean[] r_ca_3 = new Boolean[]{false,true,true, false,false};
		Boolean[] r_ca_4 = new Boolean[]{false,false,true, false,false};
		Boolean[][] r_ca = new Boolean[][]{r_ca_1,r_ca_2,r_ca_3,r_ca_4}; 
		
		Boolean [][][] res = new Boolean[][][]{r_cc,r_aa,r_ac,r_ca};
		for (int j =1 ;j<2; j++) {

			for (int i =0 ;i<4; i++) {
				int counter = 0;
				for (Range<Integer> r : ranges.get(j)) {
				
					assertEquals(res[j][i][counter], testRanges.get(i).equals(r));
				
					counter++;
				
				}
			
			}
			
		}
		

		
		
	}
}

package collections.intervalTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntervalTreeExamples {
  public void example() {
    

    // Create a new interval tree
    IntervalTree<Integer> t = new IntervalTree<>();
    
    // Set the random generator seed
    Random generator = new Random( System.currentTimeMillis() );

    // Add 1000 random interval to the tree
    for (int i=0 ; i < 1000; i++) {
      int s =0;
      int e =0;
      while( e==s ) {
        s =  generator.nextInt(10000)+1;
        e =  generator.nextInt(10000)+1;
      }
      if (s >= e) {
        int tm = s;
        s=e;
        e=tm;
      }
      t.add(new Interval<Integer>(s,e));
    }
    
    // 
    List<Interval<Integer>> a = t.search(50);
    System.out.println("Number of intervals containing 50: " + a.size());
    
    List<Interval<Integer>> b = t.overlaps( new Interval<Integer>(50, 2500));
    System.out.println("Number of intervals containing [50,2500]: " + b.size());
    
    Interval<Integer> f = t.searchFirstOverlapping(new Interval<Integer>(50,2500)  );
    
    System.out.println("First Overlapping Interval :" + f);
    
    System.out.println("Tree Size :" + t.size());
    
    t.remove(f);

    List<Interval<Integer>> b2 = t.overlaps( new Interval<Integer>(50, 2500));
    System.out.println("Number of intervals containing [50,2500]: " + b2.size());

    List<Interval<Integer>> l1 = new ArrayList<>();
    for (Interval<Integer> i: t) {
      l1.add(i);
    }
    
    List<Interval<Integer>> l2 = t.getIntervals();
    
    System.out.println("Contains all: " + l1.containsAll(l2));
    
  }
}

package math.ranks;

import java.util.Map;

public class DifferenceCorrelation extends RankingComparator {

	@Override
	public double compare(Map<Integer,Integer> rank1, Map<Integer,Integer> rank2) {
		
		double sumDiff = 0;
		
		for (Integer label : rank1.keySet()) {
			
			sumDiff = sumDiff + Math.abs(rank1.get(label) - rank2.get(label)); 
			
		} 		
		
		return sumDiff;
	
	}

}

package math.ranks;

import java.util.Map;

public abstract class RankingComparator {

	public abstract double compare(Map<Integer,Integer> rank1, Map<Integer,Integer> rank2);
	
}

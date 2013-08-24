package math.ranks;

import java.util.Map;

import math.linearregression.LinearRegression;

public class PearsonComparator extends RankingComparator {

	@Override
	public double compare(Map<Integer, Integer> rank1, Map<Integer, Integer> rank2) {
		
		double x[] = new double[rank1.size()];
		double y[] = new double[rank2.size()];

		int counter=0;
		for (Integer label : rank1.keySet()) {

			x[counter] = rank1.get(label);
			y[counter] = rank2.get(label);
			
			counter++;
			
		}
		
		LinearRegression lr = new LinearRegression(x, y);
		
		lr.calculate();
		
		return Math.sqrt(lr.getR2());
		
	}

}

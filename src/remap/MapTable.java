package remap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pair.Pair;

public class MapTable {

	public List<List<String>> map (List<List<String>> data, List<Pair<Integer, Pair<String, Map<String, String>>>> mapColumnPairs) {
		
		List<Integer> columnIndexes = new ArrayList<Integer>();
		
		for (Pair<Integer, Pair<String, Map<String, String>>> pair : mapColumnPairs) {
			columnIndexes.add(pair.getFirst());
		}
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		for (List<String> list : data) {
			
		  if (list.size() >= 2) {
  			int counter = 0;
  			
  			List<String> line = new ArrayList<String>();			
  
  			for (String fieldValue : list) {
  	
  				if (columnIndexes.contains(counter)) {
  					
  					int index = columnIndexes.indexOf(counter);
  					
  					String mappedValue = mapColumnPairs.get(index).getSecond().getSecond().get(fieldValue);
  					if (mappedValue==null) {
  					  mappedValue = mapColumnPairs.get(index).getSecond().getFirst();
  					}
            line.add(mappedValue);
  					
  				} else {
  					
  					line.add(fieldValue);
  					
  				}
  
  				counter++;
  				
  			}
  			
  			result.add(line);
  			
  		}
		}
		return result;
		
	}
	
}
